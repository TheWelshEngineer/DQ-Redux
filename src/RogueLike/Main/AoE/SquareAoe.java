package RogueLike.Main.AoE;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Utils.PointShapes.Square;
import RogueLike.Main.World;

public class SquareAoe extends AoE {

    public SquareAoe(int size) {
        super(size);
    }

    @Override
    public String typeName() {
        return "Square";
    }

    @Override
    public InstantiatedAoE instantiate(Point source, Point target, World world) {
        return new InstantiatedAoE(new Square(target.x, target.y, target.z, size), world);
    }
}
