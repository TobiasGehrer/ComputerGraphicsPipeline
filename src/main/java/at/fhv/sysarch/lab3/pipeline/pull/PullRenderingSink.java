package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.PullPipe;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import at.fhv.sysarch.lab3.utils.RenderingUtil;
import javafx.scene.canvas.GraphicsContext;

import java.util.Optional;

public class PullRenderingSink {
    private final GraphicsContext gc;
    private final RenderingMode mode;
    private PullPipe<ScreenFace> source;

    public PullRenderingSink(GraphicsContext gc, RenderingMode mode) {
        this.gc = gc;
        this.mode = mode;
    }

    public void setSource(PullPipe<ScreenFace> source) {
        this.source = source;
    }

    public void render() {
        if (source == null) return;

        Optional<ScreenFace> faceOpt;
        while ((faceOpt = source.pull()).isPresent()) {
            RenderingUtil.renderFace(faceOpt.get(), gc, mode);
        }
    }
}
