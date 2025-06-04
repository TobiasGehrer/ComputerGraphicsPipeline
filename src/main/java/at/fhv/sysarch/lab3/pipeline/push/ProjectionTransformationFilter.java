package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class ProjectionTransformationFilter extends AbstractPushFilter<Object, Object> {
    private final Mat4 projectionMatrix;

    public ProjectionTransformationFilter(Mat4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    @Override
    protected Object process(Object input) {
        Face face;

        // Handle LitFace
        if (input instanceof LitFace litFace) {
            face = litFace.getFace();

            Vec4 v1 = projectionMatrix.multiply(face.getV1());
            Vec4 v2 = projectionMatrix.multiply(face.getV2());
            Vec4 v3 = projectionMatrix.multiply(face.getV3());

            Face projectedFace = new Face(v1, v2, v3, face);
            return new LitFace(projectedFace, litFace.getColor(), litFace.getLightingFactor());
        }

        // Handle ColoredFace
        if (input instanceof ColoredFace coloredFace) {
            face = coloredFace.getFace();

            Vec4 v1 = projectionMatrix.multiply(face.getV1());
            Vec4 v2 = projectionMatrix.multiply(face.getV2());
            Vec4 v3 = projectionMatrix.multiply(face.getV3());

            Face projectedFace = new Face(v1, v2, v3, face);
            return new ColoredFace(projectedFace, coloredFace.getColor());
        }

        // Handle raw Face
        if (input instanceof Face rawFace) {
            Vec4 v1 = projectionMatrix.multiply(rawFace.getV1());
            Vec4 v2 = projectionMatrix.multiply(rawFace.getV2());
            Vec4 v3 = projectionMatrix.multiply(rawFace.getV3());

            return new Face(v1, v2, v3, rawFace);
        }

        // Fallback if unknown type
        return null;
    }
}
