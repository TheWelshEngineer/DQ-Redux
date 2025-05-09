package RogueLike.Main.Damage;

import RogueLike.Main.Effect;
import RogueLike.Main.Factories.FactoryManager;

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
	
	public Damage(int value, boolean silent, DamageType type, boolean applyStatus) {
		amount = value;
		isSilent = silent;
		this.type = type;
		this.applyStatus = applyStatus;

		switch (type) {
			case ACID: statusEffect = FactoryManager.getEffectFactory().corroded(value); break;
			case CHAOS: statusEffect = FactoryManager.getEffectFactory().devoured(value); break;
			case FIRE: statusEffect = FactoryManager.getEffectFactory().ignited(value); break;
			case FROST: statusEffect = FactoryManager.getEffectFactory().frozen(value); break;
			case MAGIC: statusEffect = FactoryManager.getEffectFactory().confused(value); break;
			case PHYSICAL: statusEffect = FactoryManager.getEffectFactory().bleeding(value); break;
			case POISON: statusEffect = FactoryManager.getEffectFactory().poisoned(value); break;
			case SHOCK: statusEffect = FactoryManager.getEffectFactory().electrified(value); break;
			case TRUE: statusEffect = null; break; // TODO: or a "no effect" effect instead?
			default: throw new IllegalArgumentException(type.toString());
		}
	}
	

}
