package RogueLike.Main.Worldgen;

import RogueLike.Main.AoE.Point;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;

import java.util.Optional;
import java.util.Random;

public abstract class Structure {
	protected final WorldBuilder builder;
	protected int x;
	protected int y;
	protected int z;

	public Structure(WorldBuilder builder, int x, int y, int depth) {
		this.builder = builder;
		this.x = x;
		this.y = y;
		this.z = depth;
	}

	private Structure setPosition(Point p) {
		x = p.x;
		y = p.y;
		z = p.z;
		return this;
	}

	/**
	 * Randomise the position of this structure.
	 * @return This if a location was found, Empty if no location was found.
	 */
	public Optional<Structure> randomisePosition() {
		Optional<Point> loc = builder.pickRandomLocation(z, p -> this.setPosition(p).isLocationAcceptable());
		return loc.map(this::setPosition);
	}

	public abstract void onBuildStructures();
	public abstract void onBuildWorld(World world);
	public abstract void onBuildWorldLate(World world);
	public abstract boolean isLocationAcceptable();
}