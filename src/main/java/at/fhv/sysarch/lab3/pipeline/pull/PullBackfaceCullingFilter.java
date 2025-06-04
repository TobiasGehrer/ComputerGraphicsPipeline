package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import at.fhv.sysarch.lab3.utils.BackfaceCullingUtil;
import com.hackoeur.jglm.Vec4;

import java.util.Optional;

public class PullBackfaceCullingFilter extends AbstractPullFilter<Face, Face> {
    @Override
    protected Optional<Face> process(Face input) {
        return BackfaceCullingUtil.isBackface(input) ? Optional.empty() : Optional.of(input);
    }

    @Override
    public Optional<Face> pull() {
        while (source != null) {
            Optional<Face> input = source.pull();
            if (input.isEmpty()) return Optional.empty();

            Optional<Face> result = process(input.get());
            if (result.isPresent()) return result;
        }

        return Optional.empty();
    }
}
