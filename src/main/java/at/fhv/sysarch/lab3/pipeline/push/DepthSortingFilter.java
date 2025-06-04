package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.interfaces.AbstractPushFilter;
import com.hackoeur.jglm.Vec4;

import java.util.ArrayList;
import java.util.List;

public class DepthSortingFilter extends AbstractPushFilter<Face, Face> {
    private List<Face> faces = new ArrayList<>();

    public void startNewFrame() {
        faces.clear();
    }

    @Override
    protected Face process(Face input) {
        faces.add(input);
        return null;
    }

    public void processSortedFaces() {
        faces.sort((face1, face2) -> {
            double maxZ1 = getMaxZ(face1);
            double maxZ2 = getMaxZ(face2);
            return Double.compare(maxZ1, maxZ2);
        });

        for (Face face : faces) {
            if (target != null) {
                target.push(face);
            }
        }
    }

    private double getMaxZ(Face face) {
        Vec4 v1 = face.getV1();
        Vec4 v2 = face.getV2();
        Vec4 v3 = face.getV3();
        return Math.max(v1.getZ(), Math.max(v2.getZ(), v3.getZ()));
    }
}
