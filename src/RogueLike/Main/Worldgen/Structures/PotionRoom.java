package RogueLike.Main.Worldgen.Structures;

import java.util.stream.Stream;

import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Utils.PointShapes.Rectangle;
import RogueLike.Main.Worldgen.Structure;
import RogueLike.Main.Worldgen.Structures.StructureUtils.LockedRoom;

public class PotionRoom extends Structure {
	public static final int internalWidth = 3;
	public static final int internalHeight = 2;

	public PotionRoom(WorldBuilder builder, int x, int y, int depth) {
		super(builder, x, y, depth);
	}

	@Override
	public void onBuildStructures() {
		Rectangle floorArea = new Rectangle(new Point(x, y, z), internalWidth, internalHeight);
		new LockedRoom(floorArea).placeTiles(builder);
	}

	@Override
	public void onBuildWorld() {
		World world = World.getInstance();
		// Add three potions into the cell, one of which is guaranteed positive
		world.addAtEmptySpace(FactoryManager.getObjectFactory().randomPotion(z, false), x+1, y-1, z);
		world.addAtEmptySpace(FactoryManager.getObjectFactory().randomPositivePotion(z, false), x, y-1, z);
		world.addAtEmptySpace(FactoryManager.getObjectFactory().randomPotion(z, false), x-1, y-1, z);
	}

	@Override
	public void onBuildWorldLate() {
		// add an iron key to this floor so the cell can be opened
		FactoryManager.getItemFactory().newIronKey(z, true);
	}

	@Override
	public boolean isCenterAcceptable() {
		return builder.isInBoundsMargin(x, y, internalWidth/2 + 3, internalHeight/2 + 3)
			&& builder.getTile(x, y, z).isGround();
	}

	@Override
	protected Stream<Point> getReservedArea() {
		return new Rectangle(new Point(x, y, z), internalWidth + 4, internalHeight + 4).stream();
	}
}
