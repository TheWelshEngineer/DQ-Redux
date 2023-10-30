package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class PhysicalDamage extends Damage{

	public PhysicalDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.physical, factory);
		this.setStatusEffect(factory.paralyzed());
	}

}
