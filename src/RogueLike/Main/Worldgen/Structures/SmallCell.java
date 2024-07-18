package RogueLike.Main.Worldgen.Structures;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Utils.PointShapes.Rectangle;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Worldgen.Structure;
import RogueLike.Main.Worldgen.Structures.StructureUtils.LockedRoom;

import java.util.stream.Stream;

public class SmallCell extends Structure {
	public static final int internalWidth = 1;
	public static final int internalHeight = 1;

	public SmallCell(WorldBuilder builder, int x, int y, int depth) {
		super(builder, x, y, depth);
	}

	@Override
	public void onBuildStructures() {
		Rectangle floorArea = new Rectangle(new Point(x, y, z), internalWidth, internalHeight);
		new LockedRoom(floorArea).placeTiles(builder);
	}

	@Override
	public void onBuildWorld(World world) {
		// add a random magic item in the cell
		world.addAtEmptySpace(world.factory().randomMagicItem(z, world.player(), false), x, y, z);
	}

	@Override
	public void onBuildWorldLate(World world) {
		// add an iron key to this floor so the cell can be opened
		world.factory().itemFactory.newIronKey(z, true);
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
