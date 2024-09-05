package RogueLike.Main.Worldgen.Structures.StructureUtils;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Tile;
import RogueLike.Main.Utils.PointShapes.HollowRectangle;
import RogueLike.Main.Utils.PointShapes.Rectangle;
import RogueLike.Main.WorldBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LockedRoom {
	private final Rectangle floorArea;
	private final HollowRectangle barsArea;

	public LockedRoom(Rectangle floorArea) {
		this.floorArea = floorArea;
		this.barsArea = HollowRectangle.surrounding(floorArea);
	}

	public void placeTiles(WorldBuilder builder) {
		floorArea.forEach(p -> builder.setTile(p, Tile.FLOOR));
		barsArea.forEach(p -> builder.setTile(p, Tile.BARS_CROSS));

		// Place the door at a random point along the top or bottom of the cell
		List<Point> doorLocations = potentialDoorLocations();
		if (doorLocations.isEmpty()) {
			throw new IllegalArgumentException("Cell is too small.");
		}

		Random rng = new Random();
		Point door = doorLocations.get(rng.nextInt(doorLocations.size()));
		builder.setTile(door, Tile.BARS_DOOR);
	}

	private List<Point> potentialDoorLocations() {
		List<Point> points = new ArrayList<>();

		for (int dx = 1; dx < barsArea.height - 1; dx++) {
			points.add(new Point(barsArea.topX + dx, barsArea.topY, barsArea.z));
			points.add(new Point(barsArea.topX + dx, barsArea.topY + barsArea.height - 1, barsArea.z));
		}

		return points;
	}
}
