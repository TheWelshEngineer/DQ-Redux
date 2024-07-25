package RogueLike.Main.Worldgen.Structures;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Tile;
import RogueLike.Main.Utils.PointShapes.Rectangle;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Worldgen.Structure;

import java.util.stream.Stream;

public class PitItem extends Structure {
	public static final int pitRadius = 1;
	public static final int pitDiameter = pitRadius * 2 + 1;

	public PitItem(WorldBuilder builder, int x, int y, int depth) {
		super(builder, x, y, depth);
	}

	@Override
	public void onBuildStructures() {
		Rectangle pitArea = new Rectangle(new Point(x, y, z), pitDiameter, pitDiameter);

		//otherwise, generate the Pit
		for (Point point: pitArea) {
			builder.setTile(point, Tile.PIT);
		}
		// and the platform in the center
		builder.setTile(x, y, z, Tile.FLOOR);
	}

	@Override
	public void onBuildWorld(World world) {
		// Generate a random magic item to put on the pit's platform
		Item item = world.factory().randomMagicItem(z, world.player(), false);
		world.addAtEmptySpace(item, x, y, z);
	}

	@Override
	public void onBuildWorldLate(World world) {
		// Add a potion of levitation somewhere this floor, so that the pit is traversable
		world.factory().itemFactory.newPotionOfLevitation(z, true);
	}

	@Override
	public boolean isCenterAcceptable() {
		return builder.isInBoundsMargin(x, y, pitRadius + 1)
			&& builder.getTile(x, y, z).isGround();
	}

	@Override
	protected Stream<Point> getReservedArea() {
		return new Rectangle(new Point(x, y, z), pitDiameter, pitDiameter).stream();
	}
}
