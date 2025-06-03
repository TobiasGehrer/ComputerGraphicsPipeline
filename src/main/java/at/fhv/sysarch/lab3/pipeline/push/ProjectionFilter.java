package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class ProjectionFilter extends AbstractPushFilter<Object, Face> {
    private final Mat4 projectionMatrix;

    public ProjectionFilter(Mat4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    @Override
    protected Face process(Object input) {
        Face face;

        if (input instanceof LitFace) {
            face = ((LitFace) input).getFace();
        } else if (input instanceof ColoredFace) {
            face = ((ColoredFace) input).getFace();
        } else {
            face = (Face) input;
        }

        Vec4 v1 = projectionMatrix.multiply(face.getV1());
        Vec4 v2 = projectionMatrix.multiply(face.getV2());
        Vec4 v3 = projectionMatrix.multiply(face.getV3());

        return new Face(v1, v2, v3, face);
    }
}
