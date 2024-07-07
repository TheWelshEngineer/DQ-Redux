package RogueLike.Main.Worldgen.Blueprints;

import RogueLike.Main.AoE.Line;
import RogueLike.Main.AoE.Point;
import RogueLike.Main.Entities.Teleporter;
import RogueLike.Main.Tile;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Worldgen.Blueprint;

import java.util.*;

public class MerchantFloor extends Blueprint {
	private final int depth;
	private final int lowerX;
	private final int lowerY;
	private final int upperX;
	private final int upperY;

	private final HashMap<Point, Point> teleporters = new HashMap<>();
	private final int roomCenterX;
	private final int roomCenterY;

	public MerchantFloor(WorldBuilder builder, int depth) {
		super(builder);
		this.depth=depth;

		this.lowerX = (builder.width()/2)-5;
		this.lowerY = (builder.height()/2)-5;
		this.upperX = (builder.width()/2)+5;
		this.upperY = (builder.height()/2)+5;
		this.roomCenterX = (builder.width()/2);
		this.roomCenterY = (builder.height()/2);
	}

	private boolean isAboveMerchantRoom(int x, int y, int margin) {
		return (
			x >= lowerX - margin
			&& x < upperX + margin
			&& y >= lowerY - margin
			&& y < upperY + margin
		);
	}

	@Override
	public void onAdd() {
		builder.markDepthAsSpecial(depth);
	}

	@Override
	public void onTileGeneration() {
		// Initialise - fill everything with walls
		for(int x = 0; x < builder.width(); x++) {
			for(int y = 0; y < builder.height(); y++) {
				builder.setTile(x, y, depth, Tile.WALL);
			}
		}

		// Create the central room
		for(int x = lowerX; x < upperX; x++) {
			for(int y = lowerY; y < upperY; y++) {
				builder.setTile(x, y, depth, Tile.FLOOR);
			}
		}
	}

	@Override
	public void onPostRegionConnection() {
		// Force add stairs leading downwards.
		builder.setTile(upperX-1, upperY-1, depth, Tile.STAIRS_DOWN);
		builder.setTilesCircle(Tile.FLOOR, upperX-1, upperY-1, 7, depth+1);
		builder.setTile(upperX-1, upperY-1, depth+1, Tile.STAIRS_UP);

		// Add stairs in random locations to the floor above
		List<Point> possibleStairsLocations = new ArrayList<>();
		int margin = 4; // margin away from the edge
		for (int x = margin; x < builder.width() - margin; x++) {
			for (int y = margin; y < builder.height() - margin; y++) {
				if (
					builder.getTile(x, y, depth-1) == Tile.FLOOR
					&& !isAboveMerchantRoom(x, y, margin + 2)
				) {
					possibleStairsLocations.add(new Point(x, y, depth-1));
				}
			}
		}
		Collections.shuffle(possibleStairsLocations);

		// always place at least 4 stairs
		int numStairs = Math.max(possibleStairsLocations.size() / 500, 4);
		for (int i=0; i < numStairs; i++) {
			Point point = possibleStairsLocations.get(i);
			generateStairsUpRoom(point.x, point.y);
		}
	}

	private void generateStairsUpRoom(int centerX, int centerY) {
		// Carve out floor area
		System.out.printf("Generating stairs room at (%d, %d, %d) %n", centerX, centerY, depth);
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 3; dy ++) {
				builder.setTile(centerX + dx, centerY + dy, depth, Tile.FLOOR);
			}
		}
		// Add the stairs
		builder.setTile(centerX, centerY, depth, Tile.STAIRS_UP);
		builder.setTile(centerX, centerY, depth-1, Tile.STAIRS_DOWN);

		// Add the teleporter
		// TODO: this is a Janky usage of Line - maybe have this be a dedicated utility function?
		// Find the closest point in the central room to the stairs room
		Line line = new Line(centerX, centerY, roomCenterX, roomCenterY);
		int targetX = -1;
		int targetY = -1;
		for (Point point: line) {
			if (isAboveMerchantRoom(point.x, point.y, -1)) {
				targetX = point.x;
				targetY = point.y;
				break;
			}
		}
		if (targetX == -1 ) {
			throw new IllegalStateException("No point was in the room!");
		}
		int returnX = -1;
		int returnY = -1;
		for (Point point: line) {
			if (isAboveMerchantRoom(point.x, point.y, 0)) {
				returnX = point.x;
				returnY = point.y;
				break;
			}
		}
		if (returnX == -1 ) {
			throw new IllegalStateException("No point was in the room!");
		}

		// stairs -> merchant teleporter
		teleporters.put(
			new Point(centerX, centerY + 2, depth),
			new Point(targetX, targetY, depth)
		);
		// merchant -> stairs teleporter
		// TODO: Smarter handling for overlapping teleporters (i.e. shunt one over a bit to fit?)
		// For now, we just don't add it if it overlaps
		if (!teleporters.containsKey(new Point(returnX, returnY, depth))) {
			teleporters.put(
				new Point(returnX, returnY, depth),
				new Point(centerX, centerY + 1, depth)
			);
		}
	}

	@Override
	public void onPostTileGeneration() {}

	public void onBuildWorld(World world) {
		teleporters.forEach((source, target) -> {
			System.out.printf("Adding teleporter from %s to %s%n", source, target);
			world.forceAdd(
				new Teleporter(source.x, source.y, depth, world, target.x, target.y)
			);
		});
	}

}
