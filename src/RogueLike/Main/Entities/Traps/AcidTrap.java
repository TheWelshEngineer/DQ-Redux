package RogueLike.Main.Entities.Traps;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.EffectFactory;
import RogueLike.Main.World;

import java.awt.*;

public class AcidTrap extends Trap {

	public AcidTrap(int x, int y, int z, World world) {
		super(x, y, z, world);
	}

	@Override
	public Effect effect(EffectFactory factory) {
		return factory.causticVapor(3+z+ Dice.d6.roll());
	}

	@Override
	public Effect exploitEffect(EffectFactory factory) {
		return factory.causticWard(10);
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
