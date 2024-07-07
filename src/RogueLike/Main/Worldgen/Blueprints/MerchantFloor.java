package RogueLike.Main.Worldgen.Blueprints;

import RogueLike.Main.AoE.Point;
import RogueLike.Main.Tile;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Worldgen.Blueprint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MerchantFloor extends Blueprint {
	private final int depth;
	private final int lowerX;
	private final int lowerY;
	private final int upperX;
	private final int upperY;

	public MerchantFloor(WorldBuilder builder, int depth) {
		super(builder);
		this.depth=depth;

		this.lowerX = (builder.width()/2)-5;
		this.lowerY = (builder.height()/2)-5;
		this.upperX = (builder.width()/2)+5;
		this.upperY = (builder.height()/2)+5;
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
		System.out.printf("Stairs up generated at (%d, %d) %n", centerX, centerY);
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 3; dy ++) {
				builder.setTile(centerX + dx, centerY + dy, depth, Tile.FLOOR);
			}
		}
		// Add the stairs
		builder.setTile(centerX, centerY, depth, Tile.STAIRS_UP);
		builder.setTile(centerX, centerY, depth-1, Tile.STAIRS_DOWN);
	}

	@Override
	public void onPostTileGeneration() {}


}
