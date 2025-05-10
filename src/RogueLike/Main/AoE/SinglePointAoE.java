package RogueLike.Main.AoE;

import java.util.List;

import RogueLike.Main.Utils.PointShapes.Point;

public class SinglePointAoE extends AoE {
	public SinglePointAoE() {
		super(1);
	}

	@Override
	public String typeName() {
		return "Point";
	}

	@Override
	public InstantiatedAoE instantiate(Point source, Point target ) {
		return new InstantiatedAoE(List.of(target));
	}
}
