package at.fhv.sysarch.lab3.pipeline.data;

import at.fhv.sysarch.lab3.obj.Face;
import javafx.scene.paint.Color;

public class ColoredFace {
    private Face face;
    private Color color;

    public ColoredFace(Face face, Color color) {
        this.face = face;
        this.color = color;
    }

    public Face getFace() {
        return face;
    }

    public Color getColor() {
        return color;
    }
}
