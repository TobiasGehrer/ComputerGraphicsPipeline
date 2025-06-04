package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import at.fhv.sysarch.lab3.utils.BackfaceCullingUtil;

public class BackfaceCullingFilter extends AbstractPushFilter<Face, Face> {
    @Override
    protected Face process(Face input) {
        return BackfaceCullingUtil.isBackface(input) ? null : input;
    }
}
