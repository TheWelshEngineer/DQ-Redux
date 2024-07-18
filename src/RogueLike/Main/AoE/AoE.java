package RogueLike.Main.AoE;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.World;

import java.util.List;

public abstract class AoE {
	protected int size;

	public AoE(int size) {
		this.size = size;
	}

	public String toString() {
		return String.format("%d-tile %s", size, typeName());
	}
	public int size() {
		return size;
	}

	public abstract String typeName();
	// TODO: actually use instantiate() to link spell AoEs to their actual areas of effect
	public abstract InstantiatedAoE instantiate(Point source, Point target, World world);
}
