package RogueLike.Main.Worldgen.Blueprints;

import RogueLike.Main.Tile;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Worldgen.Blueprint;

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
	public void onPostTileGeneration() {
		// Force add stairs
		builder.setTile(upperX-1, upperY-1, depth, Tile.STAIRS_DOWN);
		builder.setTilesCircle(Tile.FLOOR, upperX-1, upperY-1, 7, depth+1);
		builder.setTile(upperX-1, upperY-1, depth+1, Tile.STAIRS_UP);

		// TODO(diamond): integrate with natural generation to add multiple stairs down into the merchant area
		//  with teleporters that take you between the stairs and the merchant's room
		builder.setTile(lowerX, lowerY, depth, Tile.STAIRS_UP);
		builder.setTilesCircle(Tile.FLOOR, lowerX, lowerY, 7, depth-1);
		builder.setTile(lowerX, lowerY, depth-1, Tile.STAIRS_DOWN);
	}


}
