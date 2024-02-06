package RogueLike.Main;

import RogueLike.Main.Creatures.Creature;

public class Effect implements Cloneable{
	
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
	
	protected boolean isIgnited;
	public boolean isIgnited() {
		return isIgnited;
	}
	
	public void setIsIgnited(boolean value) {
		isIgnited = value;
	}
	
	protected boolean isFrozen; //TODO continue converting int to boolean
	public boolean isFrozen() {
		return isFrozen;
	}
	
	public void setIsFrozen(boolean value) {
		isFrozen = value;
	}
	
	protected boolean isElectrified;
	public boolean isElectrified() {
		return isElectrified;
	}
	
	public void setIsElectrified(boolean value) {
		isElectrified = value;
	}
	
	protected boolean isParalysis;
	public boolean isParalysis() {
		return isParalysis;
	}
	
	public void setIsParalysis(boolean value) {
		isParalysis = value;
	}
	
	protected boolean isCorrosion;
	public boolean isCorrosion() {
		return isCorrosion;
	}
	
	public void setIsCorrosion(boolean value) {
		isCorrosion = value;
	}
	
	protected boolean isPoison;
	public boolean isPoison() {
		return isPoison;
	}
	
	public void setIsPoison(boolean value) {
		isPoison = value;
	}
	
	protected boolean isInvisible;
	public boolean isInvisible() {
		return isInvisible;
	}
	
	public void setIsInvisible(boolean value) {
		isInvisible = value;
	}
	
	protected boolean isGiantStrength;
	public boolean isGiantStrength() {
		return isGiantStrength;
	}
	
	public void setIsGiantStrength(boolean value) {
		isGiantStrength = value;
	}
	
	protected boolean isBeastForm;
	public boolean isBeastForm() {
		return isBeastForm;
	}
	
	public void setIsBeastForm(boolean value) {
		isBeastForm = value;
	}
	
	protected boolean isMindVision;
	public boolean isMindVision() {
		return isMindVision;
	}
	
	public void setIsMindVision(boolean value) {
		isMindVision = value;
	}
	
	protected boolean isArcWard;
	public boolean isArcWard() {
		return isArcWard;
	}
	
	public void setIsArcWard(boolean value) {
		isArcWard = value;
	}
	
	protected boolean isMagmaWard;
	public boolean isMagmaWard() {
		return isMagmaWard;
	}
	
	public void setIsMagmaWard(boolean value) {
		isMagmaWard = value;
	}
	
	protected boolean isChillWard;
	public boolean isChillWard() {
		return isChillWard;
	}
	
	public void setIsChillWard(boolean value) {
		isChillWard = value;
	}
	
	protected boolean isTruesight;
	public boolean isTruesight() {
		return isTruesight;
	}
	
	public void setIsTruesight(boolean value) {
		isTruesight = value;
	}
	
	protected boolean isConfused;
	public boolean isConfused() {
		return isConfused;
	}
	
	public void setIsConfused(boolean value) {
		isConfused = value;
	}
	
	protected boolean isLevitating;
	public boolean isLevitating() {
		return isLevitating;
	}
	
	public void setIsLevitating(boolean value) {
		isLevitating = value;
	}
	
	protected boolean isDevoured;
	public boolean isDevoured() {
		return isDevoured;
	}
	
	public void setIsDevoured(boolean value) {
		isDevoured = value;
	}
	
	protected boolean isBlinded;
	public boolean isBlinded() {
		return isBlinded;
	}
	
	public void setIsBlinded(boolean value) {
		isBlinded = value;
	}
	
	private Creature other;
	public Creature other() {
		return other;
	}
	
	public Effect(int duration, String name, boolean negative, Creature other) {
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
