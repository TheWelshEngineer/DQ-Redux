package RogueLike.Main.AoE;

import java.io.Serializable;

import RogueLike.Main.Utils.PointShapes.Point;

public abstract class AoE implements Serializable {
	private static final long serialVersionUID = 7719063343166733151L;
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
	public abstract InstantiatedAoE instantiate(Point source, Point target);
}
