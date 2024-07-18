package RogueLike.Main.Utils.PointShapes;

import RogueLike.Main.AoE.Point;

import java.util.ArrayList;
import java.util.List;

public class HollowRectangle extends PointShape {
	public final int topX;
	public final int topY;
	public final int z;
	public final int width;
	public final int height;

	private HollowRectangle(int topX, int topY, int z, int width, int height) {
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

	public HollowRectangle(Point center, int width, int height) {
		this(center.x - (width/2), center.y - (height/2), center.z, width, height );
	}

	public HollowRectangle(Point topLeft, Point bottomRight) {
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

	public static HollowRectangle surrounding(Rectangle rect) {
		return new HollowRectangle(
			rect.topX - 1,
			rect.topY - 1,
			rect.z,
			rect.width + 2,
			rect.height + 2
		);
	}

	@Override
	protected List<Point> generatePoints() {
		// Generating this shape:
		// ooo
		// o.o
		// o.o
		// ooo

		// First generate the top and bottom:
		// ###
		// o.o
		// o.o
		// ###
		List<Point> points = new ArrayList<>();
		for (int dx = 0; dx < width; dx++) {
			points.add(new Point(topX + dx, topY, z));
			points.add(new Point(topX + dx, topY + height - 1, z));
		}

		// Then generate the sides, but not the top or bottom
		// ooo
		// #.#
		// #.#
		// ooo
		for (int dy = 1; dy < width - 1; dy++) {
			points.add(new Point(topX, topY + dy, z));
			points.add(new Point(topX + width - 1, topY + dy, z));
		}
		return points;
	}
}
