package at.fhv.sysarch.lab3.utils;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;
import javafx.scene.paint.Color;

public class ScreenSpaceUtil {

    private static Vec4 perspectiveDivide(Vec4 v) {
        if (Math.abs(v.getW()) > 1e-6) {
            return new Vec4(v.getX() / v.getW(), v.getY() / v.getW(), v.getZ() / v.getW(), 1.0f);
        }
        return v;
    }

    public static ScreenFace toScreenFace(Face face, Color color, Mat4 viewportMatrix) {
        Vec4 v1 = viewportMatrix.multiply(perspectiveDivide(face.getV1()));
        Vec4 v2 = viewportMatrix.multiply(perspectiveDivide(face.getV2()));
        Vec4 v3 = viewportMatrix.multiply(perspectiveDivide(face.getV3()));
        return new ScreenFace(v1.toScreen(), v2.toScreen(), v3.toScreen(), color);
    }
}