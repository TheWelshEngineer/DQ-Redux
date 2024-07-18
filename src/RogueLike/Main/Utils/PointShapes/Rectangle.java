package RogueLike.Main.Utils.PointShapes;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends PointShape {
	public final int topX;
	public final int topY;
	public final int z;
	public final int width;
	public final int height;

	private Rectangle(int topX, int topY, int z, int width, int height) {
		this.topX = topX;
		this.topY = topY;
		this.z = z;
		this.width = width;
		this.height = height;

		// validation
		if (width <= 0) {
			throw new IllegalArgumentException("Width was negative");
		}
		if (height <= 0) {
			throw new IllegalArgumentException("Height was negative");
		}
	}

	public Rectangle(Point center, int width, int height) {
		this(center.x - (width/2), center.y - (height/2), center.z, width, height );
	}

	public Rectangle(Point topLeft, Point bottomRight) {
		this(
			topLeft.x,
			topLeft.y,
			topLeft.z,
			1 + bottomRight.x - topLeft.x,
			1 + bottomRight.y - topLeft.y
		);

		// validation
		if (topLeft.z != bottomRight.z) {
			throw new IllegalArgumentException(String.format("z-values don't match (%d!=%d)", topLeft.z, bottomRight.z));
		}
	}

	@Override
	protected List<Point> generatePoints() {
		List<Point> points = new ArrayList<>();
		for (int dx = 0; dx < width; dx++) {
			for (int dy = 0; dy < height; dy++) {
				points.add(new Point(topX + dx, topY + dy, z));
			}
		}
		return points;
	}
}
