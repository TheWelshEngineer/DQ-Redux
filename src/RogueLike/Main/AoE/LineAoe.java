package RogueLike.Main.AoE;

import RogueLike.Main.Utils.PointShapes.Line;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.World;

public class LineAoe extends AoE {
	public LineAoe(int size) {
		super(size);
	}

	@Override
	public String typeName() {
		return "Line";
	}

	@Override
	public InstantiatedAoE instantiate(Point source, Point target, World world) {
		return new InstantiatedAoE(new Line(source.x, source.y, target.x, target.y), world);
	}
}
