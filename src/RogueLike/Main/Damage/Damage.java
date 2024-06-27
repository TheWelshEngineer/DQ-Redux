package RogueLike.Main.Damage;

import RogueLike.Main.Effect;
import RogueLike.Main.Factories.EffectFactory;

public class Damage {
	public final DamageType type;
	private final Effect statusEffect;
	public Effect statusEffect() {
		return (Effect) statusEffect.clone();
	}

	private final int amount;
	public int amount() {
		return amount;
	}
	
	public final boolean isSilent;
	public final boolean applyStatus;
	
	public Damage(int value, boolean silent, DamageType type, EffectFactory effectFactory, boolean applyStatus) {
		amount = value;
		isSilent = silent;
		this.type = type;
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
