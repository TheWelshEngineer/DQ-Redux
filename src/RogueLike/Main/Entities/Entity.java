package RogueLike.Main.Entities;

import RogueLike.Main.AoE.Point;
import RogueLike.Main.Creatures.Creature;

public abstract class Entity {
	// TODO: Should Creature extend this?
	public int x; // TODO: make protected
	public int y; // TODO: make protected
	public int z; // TODO: make protected

	public abstract char glyph();

	public Entity(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point location() {return new Point(x, y, z);}
	public int x() {return x;}
	public int y() {return y;}
	public int z() {return z;}

	public void onSteppedOnBy(Creature other) {}
}
