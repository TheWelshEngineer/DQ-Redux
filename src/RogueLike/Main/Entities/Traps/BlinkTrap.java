package RogueLike.Main.Entities.Traps;

import java.awt.Color;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Factories.FactoryManager;

public class BlinkTrap extends Trap {

	public BlinkTrap(int x, int y, int z) {
		super(x, y, z);
	}

	@Override
	public Effect effect() {
		return FactoryManager.getEffectFactory().blink();
	}

	@Override
	public Effect exploitEffect() {
		return FactoryManager.getEffectFactory().bounce();
	}

	@Override
	public String name() {
		return "Blink Trap";
	}

	@Override
	protected Color trueColor() {
		return ExtendedAsciiPanel.pink;
	}
}
