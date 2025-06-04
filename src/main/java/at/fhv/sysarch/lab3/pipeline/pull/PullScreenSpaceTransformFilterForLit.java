package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import at.fhv.sysarch.lab3.utils.ScreenSpaceUtil;
import com.hackoeur.jglm.Mat4;

import java.util.Optional;

public class PullScreenSpaceTransformFilterForLit extends AbstractPullFilter<LitFace, ScreenFace> {
    private final Mat4 viewportMatrix;

    public PullScreenSpaceTransformFilterForLit(Mat4 viewportMatrix) {
        this.viewportMatrix = viewportMatrix;
    }

    @Override
    protected Optional<ScreenFace> process(LitFace input) {
        return Optional.of(ScreenSpaceUtil.toScreenFace(input.getFace(), input.getFinalColor(), viewportMatrix));
    }
}
