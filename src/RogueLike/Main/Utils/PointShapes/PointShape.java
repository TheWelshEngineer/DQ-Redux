package RogueLike.Main.Utils.PointShapes;

import RogueLike.Main.AoE.Point;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class PointShape implements Iterable<Point> {
	private List<Point> points;

	protected abstract List<Point> generatePoints();

	public List<Point> points() {
		// generate the points if not already done, but reuse them if already generated
		if (points == null) {
			points = generatePoints();
		}
		return points;
	}

	@Override
	public Iterator<Point> iterator() {
		return points().iterator();
	}
}
