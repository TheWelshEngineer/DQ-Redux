package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class AcidDamage extends Damage{

	public AcidDamage(int value, boolean silent, EffectFactory factory, boolean status) {
		super(value, false, silent, Damage.acid, factory, status);
		this.setStatusEffect(factory.corroded(value));
	}

}
