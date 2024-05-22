package RogueLike.Main.Damage;

import RogueLike.Main.Effect;
import RogueLike.Main.Enums.DamageType;
import RogueLike.Main.Factories.EffectFactory;

public class Damage {
	public final DamageType type;
	private final Effect statusEffect;
	public Effect statusEffect() {
		return (Effect) statusEffect.clone();
	}

	private EffectFactory effectFactory;
	public EffectFactory effectFactory() {
		return effectFactory;
	}
	public void setEffectFactory(EffectFactory factory) {
		effectFactory = factory;
	}
	
	private int amount;
	public int amount() {
		return amount;
	}
	public void setAmount(int value) {
		amount = value;
	}
	public void modifyAmount(int value) {
		amount += value;
	}
	
	public final boolean isSilent;
	public final boolean applyStatus;
	
	public Damage(int value, boolean silent, DamageType type, EffectFactory factory, boolean applyStatus) {

		amount = value;
		isSilent = silent;
		this.type = type;
		effectFactory = factory;
		this.applyStatus = applyStatus;


		switch (type) {
			case ACID: statusEffect = effectFactory.corroded(value); break;
			case CHAOS: statusEffect = effectFactory.devoured(value); break;
			case FIRE: statusEffect = effectFactory.ignited(value); break;
			case FROST: statusEffect = effectFactory.frozen(value); break;
			case MAGIC: statusEffect = effectFactory.confused(value); break;
			case PHYSICAL: statusEffect = effectFactory.bleeding(value); break;
			case POISON: statusEffect = effectFactory.poisoned(value); break;
			case SHOCK: statusEffect = effectFactory.electrified(value); break;
			case TRUE: statusEffect = null; break; // TODO: or a "no effect" effect instead?
			default: throw new IllegalArgumentException(type.toString());
		}
	}
	

}
