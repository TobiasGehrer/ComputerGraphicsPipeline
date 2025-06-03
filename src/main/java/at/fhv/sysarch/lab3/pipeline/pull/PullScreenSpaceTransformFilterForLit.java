package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import com.hackoeur.jglm.Mat;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

import java.util.Optional;

public class PullScreenSpaceTransformFilterForLit extends AbstractPullFilter<LitFace, ScreenFace> {
    private final Mat4 viewportMatrix;

    public PullScreenSpaceTransformFilterForLit(Mat4 viewportMatrix) {
        this.viewportMatrix = viewportMatrix;
    }

    @Override
    protected Optional<ScreenFace> process(LitFace input) {
        Face face = input.getFace();

        Vec4 v1 = perspectiveDivide(face.getV1());
        Vec4 v2 = perspectiveDivide(face.getV2());
        Vec4 v3 = perspectiveDivide(face.getV3());

        v1 = viewportMatrix.multiply(v1);
        v2 = viewportMatrix.multiply(v2);
        v3 = viewportMatrix.multiply(v3);

        return Optional.of(new ScreenFace(v1.toScreen(), v2.toScreen(), v3.toScreen(), input.getFinalColor()));
    }

    private Vec4 perspectiveDivide(Vec4 v) {
        if (Math.abs(v.getW()) > 1e-6) {
            return new Vec4(v.getX() / v.getW(), v.getY() / v.getW(), v.getZ() / v.getW(), 1.0f);
        }

        return v;
    }
}
