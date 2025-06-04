package at.fhv.sysarch.lab3.utils;

import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import javafx.scene.canvas.GraphicsContext;

public class RenderingUtil {

    public static void renderFace(ScreenFace face, GraphicsContext gc, RenderingMode mode) {
        double[] xPoints = {face.getV1().getX(), face.getV2().getX(), face.getV3().getX()};
        double[] yPoints = {face.getV1().getY(), face.getV2().getY(), face.getV3().getY()};

        switch (mode) {
            case POINT -> {
                gc.setStroke(face.getColor());
                for (int i = 0; i < 3; i++) {
                    gc.strokeLine(xPoints[i], yPoints[i], xPoints[i], yPoints[i]);
                }
            }
            case WIREFRAME -> {
                gc.setStroke(face.getColor());
                gc.strokePolygon(xPoints, yPoints, 3);
            }
            case FILLED -> {
                gc.setFill(face.getColor());
                gc.fillPolygon(xPoints, yPoints, 3);
            }
        }
    }
}
