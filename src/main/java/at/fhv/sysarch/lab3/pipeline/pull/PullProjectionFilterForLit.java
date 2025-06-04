package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import at.fhv.sysarch.lab3.utils.ProjectionUtil;
import com.hackoeur.jglm.Mat4;

import java.util.Optional;

public class PullProjectionFilterForLit extends AbstractPullFilter<LitFace, LitFace> {
    private final Mat4 projectionMatrix;

    public PullProjectionFilterForLit(Mat4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    @Override
    protected Optional<LitFace> process(LitFace input) {
        return Optional.of(ProjectionUtil.project(input, projectionMatrix));
    }

}
