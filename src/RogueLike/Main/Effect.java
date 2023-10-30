package RogueLike.Main;

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
	
	protected int isElectrified;
	public int isElectrified() {
		return isElectrified;
	}
	
	public void modifyIsElectrified(int amount) {
		isElectrified += amount;
	}
	
	protected int isParalysis;
	public int isParalysis() {
		return isParalysis;
	}
	
	public void modifyIsParalysis(int amount) {
		isParalysis += amount;
	}
	
	protected int isCorrosion;
	public int isCorrosion() {
		return isCorrosion;
	}
	
	public void modifyIsCorrosion(int amount) {
		isCorrosion += amount;
	}
	
	protected int isPoison;
	public int isPoison() {
		return isPoison;
	}
	
	public void modifyIsPoison(int amount) {
		isPoison += amount;
	}
	
	protected int isInvisible;
	public int isInvisible() {
		return isInvisible;
	}
	
	public void modifyIsInvisible(int amount) {
		isInvisible += amount;
	}
	
	protected int isGiantStrength;
	public int isGiantStrength() {
		return isGiantStrength;
	}
	
	public void modifyIsGiantStrength(int amount) {
		isGiantStrength += amount;
	}
	
	protected int isBeastForm;
	public int isBeastForm() {
		return isBeastForm;
	}
	
	public void modifyIsBeastForm(int amount) {
		isBeastForm += amount;
	}
	
	protected int isMindVision;
	public int isMindVision() {
		return isMindVision;
	}
	
	public void modifyIsMindVision(int amount) {
		isMindVision += amount;
	}
	
	protected int isArcWard;
	public int isArcWard() {
		return isArcWard;
	}
	
	public void modifyIsArcWard(int amount) {
		isArcWard += amount;
	}
	
	protected int isMagmaWard;
	public int isMagmaWard() {
		return isMagmaWard;
	}
	
	public void modifyIsMagmaWard(int amount) {
		isMagmaWard += amount;
	}
	
	protected int isChillWard;
	public int isChillWard() {
		return isChillWard;
	}
	
	public void modifyIsChillWard(int amount) {
		isChillWard += amount;
	}
	
	protected int isTruesight;
	public int isTruesight() {
		return isTruesight;
	}
	
	public void modifyIsTruesight(int amount) {
		isTruesight += amount;
	}
	
	protected int isConfused;
	public int isConfused() {
		return isConfused;
	}
	
	public void modifyIsConfused(int amount) {
		isConfused += amount;
	}
	
	protected int isLevitating;
	public int isLevitating() {
		return isLevitating;
	}
	
	public void modifyIsLevitating(int amount) {
		isLevitating += amount;
	}
	
	protected int isDevoured;
	public int isDevoured() {
		return isDevoured;
	}
	
	public void modifyIsDevoured(int amount) {
		isDevoured += amount;
	}
	
	protected int isBlinded;
	public int isBlinded() {
		return isBlinded;
	}
	
	public void modifyIsBlinded(int amount) {
		isBlinded += amount;
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
