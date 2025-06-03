package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import javafx.scene.paint.Color;

import java.util.Optional;

public class PullColoringFilter extends AbstractPullFilter<Face, ColoredFace> {
    private final Color color;

    public PullColoringFilter(Color color) {
        this.color = color;
    }

    @Override
    protected Optional<ColoredFace> process(Face input) {
        return Optional.of(new ColoredFace(input, color));
    }
}
