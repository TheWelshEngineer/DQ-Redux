package RogueLike.Main.AoE;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.World;

import java.util.List;

public class SinglePointAoE extends AoE {
    public SinglePointAoE() {
        super(1);
    }

    @Override
    public String typeName() {
        return "Point";
    }

    @Override
    public InstantiatedAoE instantiate(Point source, Point target, World world) {
        return new InstantiatedAoE(List.of(target), world);
    }
}
