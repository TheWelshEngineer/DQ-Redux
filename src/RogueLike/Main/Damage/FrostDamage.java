package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class FrostDamage extends Damage{

	public FrostDamage(int value, boolean silent, EffectFactory factory) {
		super(value, false, silent, Damage.frost, factory);
		this.setStatusEffect(factory.frozen());
	}

}
