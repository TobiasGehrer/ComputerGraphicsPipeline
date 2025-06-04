package at.fhv.sysarch.lab3.utils;

import at.fhv.sysarch.lab3.obj.Face;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class TransformUtil {

    public static Face transformFace(Mat4 matrix, Face input) {
        Vec4 v1 = matrix.multiply(input.getV1());
        Vec4 v2 = matrix.multiply(input.getV2());
        Vec4 v3 = matrix.multiply(input.getV3());

        Vec4 n1 = matrix.multiply(input.getN1());
        Vec4 n2 = matrix.multiply(input.getN2());
        Vec4 n3 = matrix.multiply(input.getN3());

        return new Face(v1, v2, v3, n1, n2, n3);
    }
}

