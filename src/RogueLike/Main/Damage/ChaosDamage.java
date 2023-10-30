package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class ChaosDamage extends Damage{

	public ChaosDamage(int value, boolean healing, boolean silent, EffectFactory factory) {
		super(value, healing, silent, Damage.chaos, factory);
		this.setStatusEffect(factory.devoured());
	}

}
