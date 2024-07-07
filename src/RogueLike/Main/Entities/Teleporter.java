package RogueLike.Main.Entities;

import RogueLike.Main.AoE.Point;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.ExtendedAsciiPanel;

public class Teleporter extends Entity {
	public final int targetX;
	public final int targetY;

	public Teleporter(int x, int y, int z, int targetX, int targetY) {
		super(x, y, z);
		this.targetX = targetX;
		this.targetY = targetY;
	}

	@Override
	public char glyph() {
		return ExtendedAsciiPanel.getGlyphFromPage(21, 1);
	}

	@Override
	public void onSteppedOnBy(Creature other) {
		if (other.isPlayer()) {
			int mx = targetX - other.x;
			int my = targetY - other.y;
			other.moveBy(mx, my, 0, false);

			other.notify("The world warps around you as you are teleported!");
		}
	}
}
