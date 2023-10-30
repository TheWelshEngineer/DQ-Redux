package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class PoisonDamage extends Damage{

	public PoisonDamage(int value, boolean healing, boolean silent, EffectFactory factory) {
		super(value, healing, silent, Damage.poison, factory);
		this.setStatusEffect(factory.poisoned());
	}

}
