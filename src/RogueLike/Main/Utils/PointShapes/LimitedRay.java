package RogueLike.Main.Utils.PointShapes;

import java.util.List;
import java.util.stream.Collectors;

public class LimitedRay extends PointShape {
	public final Point source;
	public final Point target;
	public final int length;

	public LimitedRay(Point source, Point target, int length) {
		this.source = source;
		this.target = target;
		this.length = length;

		if (source.z != target.z) {
			throw new IllegalArgumentException(String.format("source.z == %d != %d == target.z", source.z, target.z));
		}
	}


	@Override
	protected List<Point> generatePoints() {
		int lengthSquared = length * length;
		return new Line(source.x, source.y, target.x, target.y, source.z).stream()
			.filter(p -> p.squareDistanceTo(source) <= lengthSquared)
			.collect(Collectors.toList());
	}
}
