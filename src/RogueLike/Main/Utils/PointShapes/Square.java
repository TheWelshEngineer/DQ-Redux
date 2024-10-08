package RogueLike.Main.Utils.PointShapes;

import java.util.List;

public class Square extends PointShape {
	public final int x;
	public final int y;
	public final int z;
	public final int radius;
	private Rectangle rect;

	public Square(int x, int y, int z, int radius) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.radius = radius;

		this.rect = new Rectangle(new Point(x, y, z), radius*2+1, radius*2+1);
	}

	public Square(Point p, int radius) {
		this(p.x, p.y, p.z, radius);
	}

	@Override
	protected List<Point> generatePoints() {
		return rect.generatePoints();
	}
}
