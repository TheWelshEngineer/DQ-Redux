package RogueLike.Main.Entities.Traps;

import java.awt.Color;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Factories.FactoryManager;

public class AcidTrap extends Trap {

	public AcidTrap(int x, int y, int z) {
		super(x, y, z);
	}

	@Override
	public Effect effect() {
		return FactoryManager.getEffectFactory().causticVapor(3+z+ Dice.d6.roll());
	}

	@Override
	public Effect exploitEffect() {
		return FactoryManager.getEffectFactory().causticWard(10);
	}

	@Override
	public String name() {
		return "Acid Trap";
	}

	@Override
	protected Color trueColor() {
		return ExtendedAsciiPanel.lime;
	}
}
