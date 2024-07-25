package RogueLike.Main.AoE;

import RogueLike.Main.Utils.PointShapes.Cone;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.World;

public class ConeAoE extends AoE {
    private final double angle;

    public ConeAoE(int size, double angle) {
        super(size);
        this.angle = angle;
    }

    public double angle() {
        return angle;
    }

    @Override
    public String typeName() {
        return "Cone";
    }

    @Override
    public String toString() {
        // (char)258 is the degrees symbol
        return String.format("%d-tile %d%c cone", size, Math.round(angle), (char) 258);
    }

    @Override
    public InstantiatedAoE instantiate(Point source, Point target, World world) {
        return new InstantiatedAoE(new Cone(source, target, size, angle), world);
    }
}
