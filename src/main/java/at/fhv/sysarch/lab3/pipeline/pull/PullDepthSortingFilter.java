package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import com.hackoeur.jglm.Vec4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Pull filter that buffers all incoming faces per frame,
 * sorts them by maxZ, and then delivers them one by one.
 */
public class PullDepthSortingFilter extends AbstractPullFilter<Face, Face> {
    private List<Face> buffer = new ArrayList<>();
    private int index = 0;    // Indicates the next face to deliver

    /**
     * Must be called once before each new frame to reset buffer and index.
     * For example, in PullPipelineFactory.render() before the first pull loop.
     */
    public void startNewFrame() {
        buffer.clear();
        index = 0;
        sourceExhausted = false;
    }

    @Override
    protected Optional<Face> process(Face input) {
        buffer.add(input);
        return Optional.empty();
    }

    @Override
    public Optional<Face> pull() {
        // If still in the collecting phase, continue pulling from the source:
        if (!sourceExhausted) {
            Optional<Face> next = source.pull();
            if (next.isPresent()) {
                process(next.get());
                return pull();
            } else {
                sourceExhausted = true;
                Collections.sort(buffer, Comparator.comparingDouble(this::getMaxZ));
                index = 0;
            }
        }

        // After sorting, deliver one face at a time from the buffer:
        if (index < buffer.size()) {
            Face f = buffer.get(index);
            index++;
            return Optional.of(f);
        } else {
            return Optional.empty();
        }
    }

    private double getMaxZ(Face face) {
        Vec4 v1 = face.getV1();
        Vec4 v2 = face.getV2();
        Vec4 v3 = face.getV3();
        // Returns the largest Z value (closest to the camera)
        return Math.max(v1.getZ(), Math.max(v2.getZ(), v3.getZ()));
    }

    // Helper flag to remember if the source (e.g. ModelSource → BackfaceCuller → ...) is exhausted
    private boolean sourceExhausted = false;
}
