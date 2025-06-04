package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import at.fhv.sysarch.lab3.utils.ColoringUtil;
import javafx.scene.paint.Color;

public class ColoringFilter extends AbstractPushFilter<Face, ColoredFace> {
    private final Color color;

    public ColoringFilter(Color color) {
        this.color = color;
    }

    @Override
    protected ColoredFace process(Face input) {
        return ColoringUtil.colorFace(input, color);
    }
}
