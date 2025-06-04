package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import at.fhv.sysarch.lab3.utils.TransformUtil;
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
        return Optional.of(modelViewMatrix == null ? input : TransformUtil.transformFace(modelViewMatrix, input));
    }
}
