package RogueLike.Main.Damage;

import RogueLike.Main.Factories.EffectFactory;

public class FrostDamage extends Damage{

	public FrostDamage(int value, boolean silent, EffectFactory factory, boolean status) {
		super(value, false, silent, Damage.frost, factory, status);
		this.setStatusEffect(factory.frozen(value));
	}

}
