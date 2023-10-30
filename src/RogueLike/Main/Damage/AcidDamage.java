package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class AcidDamage extends Damage{

	public AcidDamage(int value, boolean healing, boolean silent, EffectFactory factory) {
		super(value, healing, silent, Damage.acid, factory);
		this.setStatusEffect(factory.corroded());
	}

}
