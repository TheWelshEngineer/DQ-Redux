package RogueLike.Main.Worldgen;

import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;

public abstract class Blueprint {
    protected final WorldBuilder builder;

    public Blueprint(WorldBuilder builder) {
        this.builder = builder;
    }
    ;

    public abstract void onAdd();

    public abstract void onTileGeneration();

    public abstract void onPostTileGeneration();

    public abstract void onPostRegionConnection();

    public abstract void onBuildWorld(World world);
}
