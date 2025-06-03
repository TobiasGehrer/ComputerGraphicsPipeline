package at.fhv.sysarch.lab3.pipeline.data;

import com.hackoeur.jglm.Vec2;
import javafx.scene.paint.Color;

public class ScreenFace {
    private Vec2 v1;
    private Vec2 v2;
    private Vec2 v3;
    private Color color;

    public ScreenFace(Vec2 v1, Vec2 v2, Vec2 v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }

    public Vec2 getV1() {
        return v1;
    }

    public Vec2 getV2() {
        return v2;
    }

    public Vec2 getV3() {
        return v3;
    }

    public Color getColor() {
        return color;
    }
}
