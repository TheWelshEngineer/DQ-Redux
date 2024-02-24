package RogueLike.Main;

import RogueLike.Main.Creatures.Creature;

public class Effect implements Cloneable{
	
	public static String ignited = "Ignited";
	public static String frozen = "Frozen";
	public static String electrified = "Electrified";
	public static String paralysed = "Paralysed";
	public static String corroded = "Corroded";
	public static String poisoned = "Poisoned";
	public static String invisible = "Invisible";
	public static String giantStrength = "Giant Strength";
	public static String beastForm = "Beast Form";
	public static String mindVision = "Mind Vision";
	public static String arcWard = "Arc Ward";
	public static String magmaWard = "Magma Ward";
	public static String chillWard = "Chill Ward";
	public static String truesight = "Truesight";
	public static String confused = "Confused";
	public static String levitating = "Levitating";
	public static String devoured = "Devoured";
	public static String blinded = "Blinded";
	
	public static String venomousWard = "Venomous Ward";
	public static String causticWard = "Caustic Ward";
	public static String arcaneWard = "Arcane Ward";
	public static String eldritchWard = "Eldritch Ward";
	public static String bladeWard = "Blade Ward";
	public static String bleeding = "Bleeding";
	public static String illuminated = "Illuminated";
	
	public static String[] effectTypeList = new String[]{ignited, frozen, electrified, paralysed, corroded, poisoned, invisible, giantStrength, beastForm, mindVision, arcWard, magmaWard, chillWard, truesight, confused, levitating, devoured, blinded, venomousWard, causticWard, arcaneWard, eldritchWard, bladeWard, bleeding, illuminated};
	
	protected int duration;
	public int duration() {
		return duration;
	}
	
	protected int durationBase;
	public int durationBase() {
		return durationBase;
	}
	
	public void modifyDuration(int amount) {
		duration += amount;
	}
	
	public void setDuration(int amount) {
		duration = amount;
		durationBase = amount;
	}
	
	private String name;
	public String name() {
		return name;
	}
	
	private String enchantmentName;
	public String enchantmentName() {
		return enchantmentName;
	}
	
	public void setEnchantmentName(String name) {
		enchantmentName = name;
	}
	
	private String curseName;
	public String curseName() {
		return curseName;
	}
	
	public void setCurseName(String name) {
		curseName = name;
	}
	
	private String backgroundName;
	public String enchantmentEffectName() {
		return backgroundName;
	}
	
	public void setBackgroundName(String name) {
		backgroundName = name;
	}
	
	public boolean isDone() {
		return duration < 1;
	}
	
	protected boolean isNegative;
	public boolean isNegative() {
		return isNegative;
	}
	
	public void setIsNegative(boolean value) {
		isNegative = value;
	}
	
	protected boolean showInMenu = true;
	public boolean showInMenu() {
		return showInMenu;
	}
	
	public void setShowInMenu(boolean value) {
		showInMenu = value;
	}
	
	private Creature other;
	public Creature other() {
		return other;
	}
	
	private String type;
	public String type() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Effect(int duration, String name, boolean negative, Creature other) {
		this.duration = duration;
		this.durationBase = duration;
		this.name = name;
		this.isNegative = negative;
		this.other = other;
	}
	
	public Effect(int duration, String name, boolean negative, Creature other, String type) {
		this.duration = duration;
		this.durationBase = duration;
		this.name = name;
		this.isNegative = negative;
		this.other = other;
		this.type = type;
	}
	
	public Effect(int duration, String name, boolean negative, Creature other, int innerDuration) {
		this.duration = duration;
		this.durationBase = duration;
		this.name = name;
		this.isNegative = negative;
		this.other = other;
	}
	
	public Effect(Effect other) {
		this.duration = other.duration;
	}
	
	public void update(Creature creature) {
		duration--;
	}
	
	public void start(Creature creature) {
		//temp
		creature.doAction("not effective");
	}
	
	public void end(Creature creature) {
		
	}
	
	public Object clone(){
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			// This should never happen
			throw new InternalError(e.toString());
		}
	}

}
