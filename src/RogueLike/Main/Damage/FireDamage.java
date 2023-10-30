package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class FireDamage extends Damage{

	public FireDamage(int value, boolean healing, boolean silent, EffectFactory factory) {
		super(value, healing, silent, Damage.fire, factory);
		this.setStatusEffect(factory.ignited());
	}

}
