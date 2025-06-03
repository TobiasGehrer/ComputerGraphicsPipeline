package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.animation.AnimationRenderer;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.pull.*;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Matrices;
import javafx.animation.AnimationTimer;

public class PullPipelineFactory {
    public static AnimationTimer createPipeline(PipelineData pd) {
        ModelSource modelSource = new ModelSource(pd.getModel());

        PullModelViewTransformFilter mvTransform = new PullModelViewTransformFilter();
        PullBackfaceCullingFilter backfaceCuller = new PullBackfaceCullingFilter();
        PullColoringFilter coloring = new PullColoringFilter(pd.getModelColor());

        PullLightingFilter lighting = null;
        if (pd.isPerformLighting()) {
            lighting = new PullLightingFilter(pd.getLightPos());
        }

        PullProjectionFilter projection = new PullProjectionFilter(pd.getProjTransform());
        PullScreenTransformFilter screenTransform = new PullScreenTransformFilter(pd.getViewportTransform());
        PullRenderingSink renderer = new PullRenderingSink(pd.getGraphicsContext(), pd.getRenderingMode());

        mvTransform.setSource(modelSource);
        backfaceCuller.setSource(mvTransform);

        if (pd.isPerformLighting()) {
            coloring.setSource(backfaceCuller);
            lighting.setSource(coloring);
            projection.setSource(lighting);
        } else {
            coloring.setSource(backfaceCuller);
            projection.setSource(coloring);
        }

        screenTransform.setSource(projection);
        renderer.setSource(screenTransform);

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

                Mat4 modelViewMatrix = pd.getViewportTransform().multiply(modelMatrix);

                mvTransform.setModelViewMatrix(modelViewMatrix);

                modelSource.reset();

                renderer.render();
            }
        };
    }
}