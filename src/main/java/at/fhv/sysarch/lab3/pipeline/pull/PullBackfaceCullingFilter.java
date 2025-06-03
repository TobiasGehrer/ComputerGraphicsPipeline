package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import com.hackoeur.jglm.Vec4;

import java.util.Optional;

public class PullBackfaceCullingFilter extends AbstractPullFilter<Face, Face> {
    @Override
    protected Optional<Face> process(Face input) {
        Vec4 vertex = input.getV1();
        Vec4 normal = input.getN1();

        double dot = vertex.getX() * normal.getX() +
                vertex.getY() * normal.getY() +
                vertex.getZ() * normal.getZ();

        if (dot > 0.0) {
            return Optional.empty();
        }

        return Optional.of(input);
    }

    @Override
    public Optional<Face> pull() {
        while (true) {
            if (source == null) {
                return Optional.empty();
            }

            Optional<Face> input = source.pull();
            if (input.isEmpty()) {
                return Optional.empty();
            }

            Optional<Face> result = process(input.get());
            if (result.isPresent()) {
                return result;
            }
        }
    }
}
