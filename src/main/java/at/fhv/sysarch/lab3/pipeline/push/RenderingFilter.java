package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.pipeline.data.ScreenFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.PushPipe;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import at.fhv.sysarch.lab3.utils.RenderingUtil;
import javafx.scene.canvas.GraphicsContext;

public class RenderingFilter implements PushPipe<ScreenFace> {
    private final GraphicsContext gc;
    private final RenderingMode mode;

    public RenderingFilter(GraphicsContext gc, RenderingMode mode) {
        this.gc = gc;
        this.mode = mode;
    }

    @Override
    public void push(ScreenFace face) {
        RenderingUtil.renderFace(face, gc, mode);
    }
}
