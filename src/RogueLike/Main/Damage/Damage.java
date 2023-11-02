package RogueLike.Main.Damage;

import RogueLike.Main.Effect;
import RogueLike.Main.Factories.EffectFactory;

public class Damage {
	
	public static String physical = "Physical";
	public static String fire = "Fire";
	public static String frost = "Frost";
	public static String shock = "Shock";
	public static String poison = "Poison";
	public static String acid = "Acid";
	public static String magic = "Magic";
	public static String chaos = "Chaos";
	
	private String typeString;
	public String typeString() {
		return typeString;
	}
	public void setTypeString(String value) {
		typeString = value;
	}
	
	private Effect statusEffect;
	public Effect statusEffect() {
		return statusEffect;
	}
	public void setStatusEffect(Effect effect) {
		statusEffect = effect;
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
	
	private boolean isHealing;
	public boolean isHealing() {
		return isHealing;
	}
	
	private boolean isSilent;
	public boolean isSilent() {
		return isSilent;
	}
	
	private boolean applyStatus;
	public boolean canApplyStatus() {
		return applyStatus;
	}
	
	

	public boolean isPhysical() {
		return (typeString.equals(Damage.physical));
	}

	public boolean isFire() {
		return (typeString.equals(Damage.fire));
	}
	
	public boolean isFrost() {
		return (typeString.equals(Damage.frost));
	}
	
	public boolean isShock() {
		return (typeString.equals(Damage.shock));
	}
	
	public boolean isPoison() {
		return (typeString.equals(Damage.poison));
	}
	
	public boolean isAcid() {
		return (typeString.equals(Damage.acid));
	}
	
	public boolean isMagic() {
		return (typeString.equals(Damage.magic));
	}
	
	public boolean isChaos() {
		return (typeString.equals(Damage.chaos));
	}
	
	public Damage(int value, boolean healing, boolean silent, String type, EffectFactory factory, boolean applyStatus) {
		amount = value;
		isHealing = healing;
		isSilent = silent;
		typeString = type;
		effectFactory = factory;
		this.applyStatus = applyStatus;
	}
	

}
