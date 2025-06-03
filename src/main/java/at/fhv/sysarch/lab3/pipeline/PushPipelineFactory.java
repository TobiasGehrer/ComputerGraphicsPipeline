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
        ModelViewTransformFilter mvTransform = new ModelViewTransformFilter();
        BackfaceCullingFilter backfaceCuller = new BackfaceCullingFilter();
        ColoringFilter coloring = new ColoringFilter(pd.getModelColor());

        LightingFilter lighting = null;
        if (pd.isPerformLighting()) {
            lighting = new LightingFilter(pd.getLightPos());
        }

        ProjectionFilter projection = new ProjectionFilter(pd.getProjTransform());
        ScreenSpaceTransformFilter screenTransform = new ScreenSpaceTransformFilter(pd.getViewportTransform());
        RenderingSink renderer = new RenderingSink(pd.getGraphicsContext(), pd.getRenderingMode());

        mvTransform.setTarget(backfaceCuller);
        backfaceCuller.setTarget(coloring);

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

                Mat4 modelMatrix = pd.getModelTranslation().multiply(rotationMatrix);

                Mat4 modelViewMatrix = pd.getViewTransform().multiply(modelMatrix);

                mvTransform.setModelViewMatrix(modelViewMatrix);

                List<Face> faces = model.getFaces();
                for (Face face : faces) {
                    mvTransform.push(face);
                }
            }
        };
    }
}