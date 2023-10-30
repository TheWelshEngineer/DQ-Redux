package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class ChaosDamage extends Damage{

	public ChaosDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.chaos, factory);
		this.setStatusEffect(factory.devoured());
	}

}
