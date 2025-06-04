package at.fhv.sysarch.lab3.utils;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class ProjectionUtil {

    public static Face projectFace(Face face, Mat4 projectionMatrix) {
        Vec4 v1 = projectionMatrix.multiply(face.getV1());
        Vec4 v2 = projectionMatrix.multiply(face.getV2());
        Vec4 v3 = projectionMatrix.multiply(face.getV3());
        return new Face(v1, v2, v3, face);
    }

    public static ColoredFace project(ColoredFace colored, Mat4 projectionMatrix) {
        return new ColoredFace(projectFace(colored.getFace(), projectionMatrix), colored.getColor());
    }

    public static LitFace project(LitFace lit, Mat4 projectionMatrix) {
        return new LitFace(projectFace(lit.getFace(), projectionMatrix), lit.getColor(), lit.getLightingFactor());
    }
}
