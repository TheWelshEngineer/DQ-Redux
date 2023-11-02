package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class PoisonDamage extends Damage{

	public PoisonDamage(int value, boolean silent, EffectFactory factory, boolean status) {
		super(value, false, silent, Damage.poison, factory, status);
		this.setStatusEffect(factory.poisoned());
	}

}
