package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class PoisonDamage extends Damage{

	public PoisonDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.poison, factory);
		this.setStatusEffect(factory.poisoned());
	}

}
