package RogueLike.Main.Damage;

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
	
	public Damage(int value, boolean healing, boolean silent, String type) {
		amount = value;
		isHealing = healing;
		isSilent = silent;
		this.typeString = type;
	}
	

}
