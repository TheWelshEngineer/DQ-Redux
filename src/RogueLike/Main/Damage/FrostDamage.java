package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class FrostDamage extends Damage{

	public FrostDamage(int value, boolean healing, boolean silent, EffectFactory factory) {
		super(value, healing, silent, Damage.frost, factory);
		this.setStatusEffect(factory.frozen());
	}

}
