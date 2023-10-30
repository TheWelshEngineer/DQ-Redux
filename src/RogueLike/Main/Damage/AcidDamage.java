package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class AcidDamage extends Damage{

	public AcidDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.acid, factory);
		this.setStatusEffect(factory.corroded());
	}

}
