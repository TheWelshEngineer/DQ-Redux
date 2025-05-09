package RogueLike.Main.AoE;

import RogueLike.Main.Utils.PointShapes.Circle;
import RogueLike.Main.Utils.PointShapes.Point;

public class CircleAoE extends AoE {

	public CircleAoE(int size) {
		super(size);
	}

	@Override
	public String typeName() {
		return "Circle";
	}

	@Override
	public InstantiatedAoE instantiate(Point source, Point target ) {
		return new InstantiatedAoE(new Circle(target, size));
	}
}
