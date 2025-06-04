package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import at.fhv.sysarch.lab3.utils.TransformUtil;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class ModelViewTransformationFilter extends AbstractPushFilter<Face, Face> {
    private Mat4 modelViewMatrix;

    public void setModelViewMatrix(Mat4 matrix) {
        this.modelViewMatrix = matrix;
    }

    @Override
    protected Face process(Face input) {
        return modelViewMatrix == null ? input : TransformUtil.transformFace(modelViewMatrix, input);
    }
}