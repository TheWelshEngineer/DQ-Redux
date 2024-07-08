package RogueLike.Main.Entities;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.ParticleFactory;
import RogueLike.Main.World;

import java.awt.*;

public class Teleporter extends Entity {
	public final int targetX;
	public final int targetY;

	public Teleporter(World world, int x, int y, int z, int targetX, int targetY) {
		super(world, x, y, z);
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
			other.notify("The world warps around you as you are teleported!");

			ParticleFactory factory = new ParticleFactory();
			world.setParticleAtLocation(factory.vortex(ExtendedAsciiPanel.white, 2), x, y, z);
			world.setParticleAtLocation(factory.vortex(ExtendedAsciiPanel.white, 2), targetX, targetY, z);
		}
	}
}
