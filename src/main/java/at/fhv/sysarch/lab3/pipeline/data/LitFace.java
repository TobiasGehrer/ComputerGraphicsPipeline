package at.fhv.sysarch.lab3.pipeline.data;

import at.fhv.sysarch.lab3.obj.Face;
import javafx.scene.paint.Color;

public class LitFace {
    private Face face;
    private Color color;
    private double lightingFactor;

    public LitFace(Face face, Color color, double lightingFactor) {
        this.face = face;
        this.color = color;
        this.lightingFactor = lightingFactor;
    }

    public Face getFace() {
        return face;
    }

    public Color getColor() {
        return color;
    }

    public double getLightingFactor() {
        return lightingFactor;
    }

    public Color getFinalColor() {
        if (lightingFactor <= 0) {
            return Color.BLACK;
        }
        return Color.color(
                Math.min(1.0, color.getRed() * lightingFactor),
                Math.min(1.0, color.getGreen() * lightingFactor),
                Math.min(1.0, color.getBlue() * lightingFactor)
        );
    }
}
