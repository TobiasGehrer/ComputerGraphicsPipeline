package at.fhv.sysarch.lab3.pipeline.pull;

import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPullFilter;
import at.fhv.sysarch.lab3.utils.LightingUtil;
import com.hackoeur.jglm.Vec3;

import java.util.Optional;

public class PullLightingFilter extends AbstractPullFilter<ColoredFace, LitFace> {
    private final Vec3 lightPosition;

    public PullLightingFilter(Vec3 lightPosition) {
        this.lightPosition = lightPosition;
    }

    @Override
    protected Optional<LitFace> process(ColoredFace input) {
        return Optional.of(LightingUtil.computeLighting(input, lightPosition));
    }
}
