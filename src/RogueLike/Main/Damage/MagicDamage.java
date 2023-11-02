package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class MagicDamage extends Damage{

	public MagicDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.magic, factory);
		this.setStatusEffect(factory.confused());
	}

}
