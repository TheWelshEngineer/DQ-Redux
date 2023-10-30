package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class ShockDamage extends Damage{

	public ShockDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.shock, factory);
		this.setStatusEffect(factory.electrified());
	}

}
