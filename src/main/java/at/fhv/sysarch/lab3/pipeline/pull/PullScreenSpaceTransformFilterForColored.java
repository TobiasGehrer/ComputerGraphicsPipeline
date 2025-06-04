package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import at.fhv.sysarch.lab3.utils.ScreenSpaceUtil;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

import java.util.Optional;

public class PullScreenSpaceTransformFilterForColored extends AbstractPullFilter<ColoredFace, ScreenFace> {
    private final Mat4 viewportMatrix;

    public PullScreenSpaceTransformFilterForColored(Mat4 viewportMatrix) {
        this.viewportMatrix = viewportMatrix;
    }

    @Override
    protected Optional<ScreenFace> process(ColoredFace input) {
        return Optional.of(ScreenSpaceUtil.toScreenFace(input.getFace(), input.getColor(), viewportMatrix));
    }


    private Vec4 perspectiveDivide(Vec4 v) {
        if (Math.abs(v.getW()) > 1e-6) {
            return new Vec4(v.getX() / v.getW(), v.getY() / v.getW(), v.getZ() / v.getW(), 1.0f);
        }

        return v;
    }
}
