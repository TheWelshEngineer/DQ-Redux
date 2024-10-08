package RogueLike.Main.Worldgen.Structures;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Tile;
import RogueLike.Main.Utils.PointShapes.Rectangle;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Worldgen.Structure;

import java.util.Random;
import java.util.stream.Stream;

public class GrassPatch extends Structure {
	public static final int width = 5;
	public static final int height = 5;
	public static final double density = 0.5;

	public GrassPatch(WorldBuilder builder, int x, int y, int depth) {
		super(builder, x, y, depth);
	}

	@Override
	public void onBuildStructures() {
		Rectangle area = new Rectangle(new Point(x, y, z), width, height);
		Random rng = new Random();
		for (Point point: area) {
			if (builder.getTile(point).isGround() && rng.nextDouble() < density) {
				builder.setTile(point, Tile.GRASS_TALL);
			}
		}
	}

	@Override
	public void onBuildWorld(World world) {
		// do nothing
	}

	@Override
	public void onBuildWorldLate(World world) {
		// do nothing
	}

	@Override
	public boolean isCenterAcceptable() {
		return builder.isInBoundsMargin(x, y, width/2 + 1, height/2 + 1)
			&& builder.getTile(x, y, z).isGround();
	}

	@Override
	protected Stream<Point> getReservedArea() {
		return new Rectangle(new Point(x, y, z), width, height).stream();
	}
}
