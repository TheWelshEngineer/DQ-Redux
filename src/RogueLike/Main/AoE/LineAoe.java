package RogueLike.Main.AoE;

import RogueLike.Main.Utils.PointShapes.Line;
import RogueLike.Main.Utils.PointShapes.Point;

public class LineAoe extends AoE {
	public LineAoe(int size) {
		super(size);
	}

	@Override
	public String typeName() {
		return "Line";
	}

	@Override
	public InstantiatedAoE instantiate(Point source, Point target ) {
		return new InstantiatedAoE(new Line(source.x, source.y, target.x, target.y));
	}
}
