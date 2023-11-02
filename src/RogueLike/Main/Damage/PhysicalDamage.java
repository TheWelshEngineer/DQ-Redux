package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class PhysicalDamage extends Damage{

	public PhysicalDamage(int value, boolean silent, EffectFactory factory, boolean status) {
		super(value, false, silent, Damage.physical, factory, status);
		this.setStatusEffect(factory.paralyzed());
	}

}
