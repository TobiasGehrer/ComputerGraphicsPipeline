package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import at.fhv.sysarch.lab3.utils.ProjectionUtil;
import com.hackoeur.jglm.Mat4;

import java.util.Optional;

public class PullProjectionFilterForColored extends AbstractPullFilter<ColoredFace, ColoredFace> {
    private final Mat4 projectionMatrix;

    public PullProjectionFilterForColored(Mat4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    @Override
    protected Optional<ColoredFace> process(ColoredFace input) {
        return Optional.of(ProjectionUtil.project(input, projectionMatrix));
    }

}
