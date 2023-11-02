package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class FireDamage extends Damage{

	public FireDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.fire, factory);
		this.setStatusEffect(factory.ignited());
	}

}
