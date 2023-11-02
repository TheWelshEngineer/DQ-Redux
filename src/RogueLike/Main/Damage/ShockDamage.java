package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class ShockDamage extends Damage{

	public ShockDamage(int value, boolean silent, EffectFactory factory, boolean status) {
		super(value, false, silent, Damage.shock, factory, status);
		this.setStatusEffect(factory.electrified());
	}

}
