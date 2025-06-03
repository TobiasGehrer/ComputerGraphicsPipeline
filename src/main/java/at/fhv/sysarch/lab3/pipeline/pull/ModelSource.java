package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.interfaces.PullPipe;

import java.util.List;
import java.util.Optional;

public class ModelSource implements PullPipe<Face> {
    private final List<Face> faces;
    private int currentIndex = 0;

    public ModelSource(Model model) {
        this.faces = model.getFaces();
    }

    public void reset() {
        currentIndex = 0;
    }

    @Override
    public Optional<Face> pull() {
        if (currentIndex < faces.size()) {
            return Optional.of(faces.get(currentIndex++));
        }

        return Optional.empty();
    }
}
