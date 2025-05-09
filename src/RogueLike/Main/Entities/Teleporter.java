package RogueLike.Main.Entities;

import java.awt.Color;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;

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
	public Color color() {
		return ExtendedAsciiPanel.magenta;
	}

	@Override
	public String name() {
		return "teleporter";
	}

	@Override
	public void onSteppedOnBy(Creature other) {
		if (other.isPlayer()) {
			int mx = targetX - other.x;
			int my = targetY - other.y;
			other.moveBy(mx, my, 0, false);
			other.notify("The World warps around you as you are teleported!");

			World.setParticleAtLocation(FactoryManager.getParticleFactory().vortex(ExtendedAsciiPanel.white, 2), x, y, z);
			World.setParticleAtLocation(FactoryManager.getParticleFactory().vortex(ExtendedAsciiPanel.white, 2), targetX, targetY, z);
		}
	}
}
