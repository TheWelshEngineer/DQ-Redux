package RogueLike.Main.Entities.Traps;

import java.awt.Color;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.World;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Factories.FactoryManager;

public class SummoningTrap extends Trap {

	public SummoningTrap(int x, int y, int z) {
		super(x, y, z);
	}

	@Override
	public Effect effect() {
		return FactoryManager.getEffectFactory().summonMonstersScroll(World.player());
	}

	@Override
	public Effect exploitEffect() {
		return FactoryManager.getEffectFactory().invisible(10);
	}

	@Override
	public String name() {
		return "Summoning Trap";
	}

	@Override
	protected Color trueColor() {
		return ExtendedAsciiPanel.brightWhite;
	}
}
