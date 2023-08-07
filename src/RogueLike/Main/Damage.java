package RogueLike.Main;

public class Damage {
	
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
	
	private boolean isPhysical;
	public boolean isPhysical() {
		return isPhysical;
	}
	public void setPhysical(boolean value) {
		isPhysical = value;
	}
	
	private boolean isFire;
	public boolean isFire() {
		return isFire;
	}
	public void setFire(boolean value) {
		isFire = value;
	}
	
	private boolean isFrost;
	public boolean isFrost() {
		return isFrost;
	}
	public void setFrost(boolean value) {
		isFrost = value;
	}
	
	private boolean isShock;
	public boolean isShock() {
		return isShock;
	}
	public void setShock(boolean value) {
		isShock = value;
	}
	
	private boolean isPoison;
	public boolean isPoison() {
		return isPoison;
	}
	public void setPoison(boolean value) {
		isPoison = value;
	}
	
	private boolean isAcid;
	public boolean isAcid() {
		return isAcid;
	}
	public void setAcid(boolean value) {
		isAcid = value;
	}
	
	private boolean isMagic;
	public boolean isMagic() {
		return isMagic;
	}
	public void setMagic(boolean value) {
		isMagic = value;
	}
	
	private boolean isChaos;
	public boolean isChaos() {
		return isChaos;
	}
	public void setChaos(boolean value) {
		isChaos = value;
	}
	
	public Damage(int value, boolean healing, boolean silent) {
		amount = value;
		isHealing = healing;
		isSilent = silent;
	}
	

}
