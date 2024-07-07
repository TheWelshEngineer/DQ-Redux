package RogueLike.Main.Worldgen.Blueprints;

import RogueLike.Main.Tile;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Worldgen.Blueprint;

public class CaveFloor extends Blueprint {
	private final int depth;
	private final int numSmoothingIterations = 9;

	public CaveFloor(WorldBuilder builder, int depth) {
		super(builder);
		this.depth = depth;
	}

	@Override
	public void onAdd() {}

	@Override
	public void onTileGeneration() {
		// Randomly fill floor with tiles
		Tile[][] tiles = new Tile[builder.width()][builder.height()];
		for(int x = 0; x < builder.width(); x++) {
			for(int y = 0; y < builder.height(); y++) {
				tiles[x][y] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
			}
		}

		// Smoothing
		for (int i = 0; i < numSmoothingIterations; i++) {
			tiles = smoothTiles(tiles);
			System.out.printf("Smoothing iteration %d done for depth %d.%n", i, depth);
		}

		// Apply the generated tiles
		for(int x = 0; x < builder.width(); x++) {
			for(int y = 0; y < builder.height(); y++) {
				builder.setTile(x, y, depth, tiles[x][y]);
			}
		}
	}

	private Tile[][] smoothTiles(Tile[][] tiles) {
		Tile[][] tiles2 = new Tile[builder.width()][builder.height()];
		for (int x = 0; x < builder.width(); x++) {
			for (int y = 0; y < builder.height(); y++) {
				int floors = 0;
				int rocks = 0;
				for (int ox = -1; ox < 2; ox++) {
					for (int oy = -1; oy < 2; oy++) {
						if (!builder.isInBounds(x+ox, y+oy)) {continue;}
						if (tiles[x+ox][y+oy] == Tile.FLOOR) {
							floors++;
						}
						else {
							rocks++;
						}
					}
				}
				tiles2[x][y] = floors >= rocks ? Tile.FLOOR : Tile.WALL;
			}
		}
		return tiles2;
	}

	@Override
	public void onPostTileGeneration() {

	}

	@Override
	public void onPostRegionConnection() {

	}
}
