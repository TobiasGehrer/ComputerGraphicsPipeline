package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.data.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.data.LitFace;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import at.fhv.sysarch.lab3.utils.ProjectionUtil;
import com.hackoeur.jglm.Mat4;

public class ProjectionTransformationFilter extends AbstractPushFilter<Object, Object> {
    private final Mat4 projectionMatrix;

    public ProjectionTransformationFilter(Mat4 projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    @Override
    protected Object process(Object input) {
        if (input instanceof LitFace lit) {
            return ProjectionUtil.project(lit, projectionMatrix);
        } else if (input instanceof ColoredFace colored) {
            return ProjectionUtil.project(colored, projectionMatrix);
        } else if (input instanceof Face face) {
            return ProjectionUtil.projectFace(face, projectionMatrix);
        }
        return null;
    }

}
