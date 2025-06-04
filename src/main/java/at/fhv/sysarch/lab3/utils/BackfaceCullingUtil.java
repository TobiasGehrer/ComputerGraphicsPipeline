package at.fhv.sysarch.lab3.utils;

import at.fhv.sysarch.lab3.obj.Face;
import com.hackoeur.jglm.Vec4;

public class BackfaceCullingUtil {
    public static boolean isBackface(Face face) {
        Vec4 vertex = face.getV1();
        Vec4 normal = face.getN1();

        double dot = vertex.getX() * normal.getX() +
                vertex.getY() * normal.getY() +
                vertex.getZ() * normal.getZ();

        return dot > 0.0;
    }
}
