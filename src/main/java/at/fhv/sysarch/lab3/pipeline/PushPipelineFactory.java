package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.animation.AnimationRenderer;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.push.*;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import javafx.animation.AnimationTimer;

import java.util.List;

public class PushPipelineFactory {
    public static AnimationTimer createPipeline(PipelineData pd) {
        ModelViewTransformationFilter mvTransform = new ModelViewTransformationFilter();
        BackfaceCullingFilter backfaceCuller = new BackfaceCullingFilter();
        DepthSortingFilter depthSorter = new DepthSortingFilter();
        ColoringFilter coloring = new ColoringFilter(pd.getModelColor());

        LightingFilter lighting = null;
        if (pd.isPerformLighting()) {
            lighting = new LightingFilter(pd.getLightPos());
        }

        ProjectionTransformationFilter projection = new ProjectionTransformationFilter(pd.getProjTransform());
        ScreenSpaceTransformationFilter screenTransform = new ScreenSpaceTransformationFilter(pd.getViewportTransform());
        RenderingFilter renderer = new RenderingFilter(pd.getGraphicsContext(), pd.getRenderingMode());

        mvTransform.setTarget(backfaceCuller);
        backfaceCuller.setTarget(depthSorter);
        depthSorter.setTarget(coloring);

        if (pd.isPerformLighting()) {
            coloring.setTarget(lighting);
            lighting.setTarget(projection);
        } else {
            coloring.setTarget(projection);
        }

        projection.setTarget(screenTransform);
        screenTransform.setTarget(renderer);

        return new AnimationRenderer(pd) {
            private float rotation = 0.0f;

            /** This method is called for every frame from the JavaFX Animation
             * system (using an AnimationTimer, see AnimationRenderer). 
             * @param fraction the time which has passed since the last render call in a fraction of a second
             * @param model    the model to render 
             */
            @Override
            protected void render(float fraction, Model model) {

                rotation += fraction * 1.0f;

                Mat4 rotationMatrix = Matrices.rotate(rotation, pd.getModelRotAxis());

                // IMPORTANT: The order of matrix multiplication matters!
                // We rotate the model first so it spins around its own axis.
                // Then we move (translate) it to its position in the world.
                // If we do it the other way (translate * rotate), the model will spin around the world origin,
                // not around itself
                Mat4 modelMatrix = rotationMatrix.multiply(pd.getModelTranslation());

                Mat4 modelViewMatrix = pd.getViewTransform().multiply(modelMatrix);

                mvTransform.setModelViewMatrix(modelViewMatrix);

                depthSorter.startNewFrame();

                for (Face face : model.getFaces()) {
                    mvTransform.push(face);
                }

                depthSorter.processSortedFaces();
            }
        };
    }
}