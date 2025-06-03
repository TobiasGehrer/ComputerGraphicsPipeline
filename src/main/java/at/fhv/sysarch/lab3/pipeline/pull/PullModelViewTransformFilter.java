package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

import java.util.Optional;

public class PullModelViewTransformFilter extends AbstractPullFilter<Face, Face> {
    private Mat4 modelViewMatrix;

    public void setModelViewMatrix(Mat4 matrix) {
        this.modelViewMatrix = matrix;
    }

    @Override
    protected Optional<Face> process(Face input) {
        if (modelViewMatrix == null) {
            return Optional.of(input);
        }

        Vec4 v1 = modelViewMatrix.multiply(input.getV1());
        Vec4 v2 = modelViewMatrix.multiply(input.getV2());
        Vec4 v3 = modelViewMatrix.multiply(input.getV3());

        Vec4 n1 = modelViewMatrix.multiply(input.getN1());
        Vec4 n2 = modelViewMatrix.multiply(input.getN2());
        Vec4 n3 = modelViewMatrix.multiply(input.getN3());

        return Optional.of(new Face(v1, v2, v3, n1, n2, n3));
    }
}
