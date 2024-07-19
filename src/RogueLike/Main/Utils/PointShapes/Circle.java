package RogueLike.Main.Utils.PointShapes;

import java.util.List;
import java.util.stream.Collectors;

public class Circle extends PointShape {
	private final Point center;
	private final int radius;

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	protected List<Point> generatePoints() {
		int squareRadius = radius * radius;
		return new Square(center, radius).stream()
			.filter(p -> (p.squareDistanceTo(center) <= squareRadius))
			.collect(Collectors.toList());
	}
}
