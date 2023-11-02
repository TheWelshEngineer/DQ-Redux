package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class FireDamage extends Damage{

	public FireDamage(int value, boolean silent, EffectFactory factory, boolean status) {
		super(value, false, silent, Damage.fire, factory, status);
		this.setStatusEffect(factory.ignited());
	}

}
