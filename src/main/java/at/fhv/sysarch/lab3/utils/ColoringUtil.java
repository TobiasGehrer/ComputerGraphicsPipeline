package at.fhv.sysarch.lab3.utils;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import javafx.scene.paint.Color;

public class ColoringUtil {
    public static ColoredFace colorFace(Face face, Color color) {
        return new ColoredFace(face, color);
    }
}

