package RogueLike.Main.Worldgen;

import RogueLike.Main.Tile;
import RogueLike.Main.WorldBuilder;

public abstract class Blueprint {
	protected final WorldBuilder builder;

	public Blueprint(WorldBuilder builder) {
		this.builder = builder;
	}

	public void onAdd() {}
	public void onTileGeneration() {}
	public void onPostTileGeneration() {}
	public void onPostRegionConnection() {}
}
