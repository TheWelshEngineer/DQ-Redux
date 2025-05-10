package RogueLike.Main.Entities.Traps;

import java.awt.Color;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Factories.FactoryManager;

public class SmokeTrap extends Trap {

	public SmokeTrap(int x, int y, int z) {
		super(x, y, z);
	}

	@Override
	public Effect effect() {
		return FactoryManager.getEffectFactory().smokeTrap();
	}

	@Override
	public Effect exploitEffect() {
		return FactoryManager.getEffectFactory().mindVision(10);
	}

	@Override
	public String name() {
		return "Smoke Trap";
	}

	@Override
	protected Color trueColor() {
		return ExtendedAsciiPanel.smoke;
	}
}

