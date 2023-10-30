package RogueLike.Main.Damage;

import RogueLike.Main.EffectFactory;

public class MagicDamage extends Damage{

	public MagicDamage(int value, boolean healing, boolean silent, EffectFactory factory) {
		super(value, healing, silent, Damage.magic, factory);
		this.setStatusEffect(factory.confused());
	}

}
