package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import com.hackoeur.jglm.Vec4;

public class BackfaceCullingFilter extends AbstractPushFilter<Face, Face> {
    @Override
    protected Face process(Face input) {
        Vec4 vertex = input.getV1();
        Vec4 normal = input.getN1();

        double dot = vertex.getX() * normal.getX() +
                vertex.getY() * normal.getY() +
                vertex.getZ() * normal.getZ();

        return dot > 0.0 ? null : input;
    }
}
