package at.fhv.sysarch.lab3.utils;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import com.hackoeur.jglm.Vec3;
import com.hackoeur.jglm.Vec4;

public class LightingUtil {
    public static LitFace computeLighting(ColoredFace input, Vec3 lightPosition) {
        Face face = input.getFace();

        Vec4 center = new Vec4(
                (face.getV1().getX() + face.getV2().getX() + face.getV3().getX()) / 3.0f,
                (face.getV1().getY() + face.getV2().getY() + face.getV3().getY()) / 3.0f,
                (face.getV1().getZ() + face.getV2().getZ() + face.getV3().getZ()) / 3.0f,
                1.0f
        );

        Vec4 normal = new Vec4(
                (face.getN1().getX() + face.getN2().getX() + face.getN3().getX()) / 3.0f,
                (face.getN1().getY() + face.getN2().getY() + face.getN3().getY()) / 3.0f,
                (face.getN1().getZ() + face.getN2().getZ() + face.getN3().getZ()) / 3.0f,
                0.0f
        );

        Vec3 lightDirection = new Vec3(
                lightPosition.getX() - center.getX(),
                lightPosition.getY() - center.getY(),
                lightPosition.getZ() - center.getZ()
        ).getUnitVector();

        Vec3 norm = new Vec3(normal.getX(), normal.getY(), normal.getZ()).getUnitVector();

        double lightingFactor = Math.max(0.0, lightDirection.dot(norm));

        return new LitFace(face, input.getColor(), lightingFactor);
    }
}
