package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;
import javafx.scene.paint.Color;

public class ScreenSpaceTransformFilter extends AbstractPushFilter<Object, ScreenFace> {
    private final Mat4 viewportMatrix;

    public ScreenSpaceTransformFilter(Mat4 viewportMatrix) {
        this.viewportMatrix = viewportMatrix;
    }

    @Override
    protected ScreenFace process(Object input) {
        Face face;
        Color color;

        if (input instanceof LitFace) {
            LitFace litFace = (LitFace) input;
            face = litFace.getFace();
            color = litFace.getFinalColor();
        } else if (input instanceof ColoredFace) {
            ColoredFace coloredFace = (ColoredFace) input;
            face = coloredFace.getFace();
            color = coloredFace.getColor();
        } else {
            face = (Face) input;
            color = Color.WHITE;
        }

        Vec4 v1 = perspectiveDivide(face.getV1());
        Vec4 v2 = perspectiveDivide(face.getV2());
        Vec4 v3 = perspectiveDivide(face.getV3());

        v1 = viewportMatrix.multiply(v1);
        v2 = viewportMatrix.multiply(v2);
        v3 = viewportMatrix.multiply(v3);

        return new ScreenFace(v1.toScreen(), v2.toScreen(), v3.toScreen(), color);
    }

    private Vec4 perspectiveDivide(Vec4 v) {
        if (Math.abs(v.getW()) > 1e-6) {
            return new Vec4(v.getX() / v.getW(), v.getY() / v.getW(), v.getZ() / v.getW(), 1.0f);
        }

        return v;
    }
}
