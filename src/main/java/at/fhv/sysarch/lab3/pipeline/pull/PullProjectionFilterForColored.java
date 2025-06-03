package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

import java.util.Optional;

public class PullProjectionFilterForColored extends AbstractPullFilter<ColoredFace, ColoredFace> {
    private final Mat4 projectionMatrix;

    public PullProjectionFilterForColored(Mat4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    @Override
    protected Optional<ColoredFace> process(ColoredFace input) {
        Face face = input.getFace();

        Vec4 v1 = projectionMatrix.multiply(face.getV1());
        Vec4 v2 = projectionMatrix.multiply(face.getV2());
        Vec4 v3 = projectionMatrix.multiply(face.getV3());

        Face projectedFace = new Face(v1, v2, v3, face);

        return Optional.of(new ColoredFace(projectedFace, input.getColor()));
    }
}
