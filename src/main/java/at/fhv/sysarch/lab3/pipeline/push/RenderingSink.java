package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.PushPipe;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import javafx.scene.canvas.GraphicsContext;

public class RenderingSink implements PushPipe<ScreenFace> {
    private final GraphicsContext gc;
    private final RenderingMode mode;

    public RenderingSink(GraphicsContext gc, RenderingMode mode) {
        this.gc = gc;
        this.mode = mode;
    }

    @Override
    public void push(ScreenFace face) {
        double[] xPoints = {face.getV1().getX(), face.getV2().getX(), face.getV3().getX()};
        double[] yPoints = {face.getV1().getY(), face.getV2().getY(), face.getV3().getY()};

        switch (mode) {
            case POINT:
                gc.setStroke(face.getColor());
                gc.strokeLine(xPoints[0], yPoints[0], xPoints[0], yPoints[0]);
                gc.strokeLine(xPoints[1], yPoints[1], xPoints[1], yPoints[1]);
                gc.strokeLine(xPoints[2], yPoints[2], xPoints[2], yPoints[2]);
                break;
            case WIREFRAME:
                gc.setStroke(face.getColor());
                gc.strokePolygon(xPoints, yPoints, 3);
                break;
            case FILLED:
                gc.setFill(face.getColor());
                gc.fillPolygon(xPoints, yPoints, 3);
                break;
        }
    }
}
