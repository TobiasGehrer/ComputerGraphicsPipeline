package at.fhv.sysarch.lab3;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.obj.ObjLoader;
import at.fhv.sysarch.lab3.pipeline.PipelineData;
import at.fhv.sysarch.lab3.pipeline.PullPipelineFactory;
import at.fhv.sysarch.lab3.pipeline.PushPipelineFactory;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int VIEW_WIDTH = 860;
    private static final int VIEW_HEIGHT = 540;
    private static final int SCENE_WIDTH = VIEW_WIDTH * 2;
    private static final int SCENE_HEIGHT = VIEW_HEIGHT * 2;
    private static final boolean USE_PUSH_PIPELINE = true;

    private enum ViewConfig {
        ORANGE(Color.ORANGE, RenderingMode.POINT, false),
        GREEN(Color.DARKGREEN, RenderingMode.WIREFRAME, false),
        RED(Color.RED, RenderingMode.FILLED, false),
        BLUE(Color.BLUE, RenderingMode.FILLED, true);

        final Color color;
        final RenderingMode mode;
        final boolean lighting;

        ViewConfig(Color color, RenderingMode mode, boolean lighting) {
            this.color = color;
            this.mode = mode;
            this.lighting = lighting;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Optional<Model> optModel = ObjLoader.loadModel(new File("resources/teapot.obj"));
        if (optModel.isEmpty()) return;

        Model model = optModel.get();
        Group root = new Group();
        GridPane grid = new GridPane();

        int i = 0;
        for (ViewConfig config : ViewConfig.values()) {
            int row = i / 2, col = i % 2;
            Canvas canvas = new Canvas(VIEW_WIDTH, VIEW_HEIGHT);
            grid.add(canvas, col, row);

            PipelineData pd = new PipelineData.Builder(canvas, model, VIEW_WIDTH, VIEW_HEIGHT)
                    .setModelColor(config.color)
                    .setRenderingMode(config.mode)
                    .setPerformLighting(config.lighting)
                    .build();

            AnimationTimer anim = USE_PUSH_PIPELINE
                    ? PushPipelineFactory.createPipeline(pd)
                    : PullPipelineFactory.createPipeline(pd);

            anim.start();
            i++;
        }

        root.getChildren().add(grid);
        stage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK));
        stage.setTitle("Simple CG Pipeline");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}