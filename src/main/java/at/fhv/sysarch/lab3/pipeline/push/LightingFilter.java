package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import at.fhv.sysarch.lab3.utils.LightingUtil;
import com.hackoeur.jglm.Vec3;

public class LightingFilter extends AbstractPushFilter<ColoredFace, LitFace> {
    private final Vec3 lightPosition;

    public LightingFilter(Vec3 lightPosition) {
        this.lightPosition = lightPosition;
    }

    @Override
    protected LitFace process(ColoredFace input) {
        return LightingUtil.computeLighting(input, lightPosition);
    }
}
