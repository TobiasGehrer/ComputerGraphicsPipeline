package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import at.fhv.sysarch.lab3.utils.ScreenSpaceUtil;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class ScreenSpaceTransformationFilter extends AbstractPushFilter<Object, ScreenFace> {
    private final Mat4 viewportMatrix;

    public ScreenSpaceTransformationFilter(Mat4 viewportMatrix) {
        this.viewportMatrix = viewportMatrix;
    }

    @Override
    protected ScreenFace process(Object input) {
        if (input instanceof LitFace lit) {
            return ScreenSpaceUtil.toScreenFace(lit.getFace(), lit.getFinalColor(), viewportMatrix);
        } else if (input instanceof ColoredFace colored) {
            return ScreenSpaceUtil.toScreenFace(colored.getFace(), colored.getColor(), viewportMatrix);
        } else if (input instanceof Face face) {
            return ScreenSpaceUtil.toScreenFace(face, javafx.scene.paint.Color.WHITE, viewportMatrix);
        }
        return null;
    }

    private Vec4 perspectiveDivide(Vec4 v) {
        if (Math.abs(v.getW()) > 1e-6) {
            return new Vec4(v.getX() / v.getW(), v.getY() / v.getW(), v.getZ() / v.getW(), 1.0f);
        }

        return v;
    }
}
