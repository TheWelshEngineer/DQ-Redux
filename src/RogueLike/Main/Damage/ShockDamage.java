package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class ShockDamage extends Damage{

	public ShockDamage(int value, boolean healing, boolean silent, EffectFactory factory) {
		super(value, healing, silent, Damage.shock, factory);
		this.setStatusEffect(factory.electrified());
	}

}
