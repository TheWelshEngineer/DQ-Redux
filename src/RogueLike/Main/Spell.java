package RogueLike.Main;

public class Spell {
	
	private String name;
	public String name() {
		return name;
	}
	
	private int manaCost;
	public int manaCost() {
		return manaCost;
	}
	
	private int id;
	public int id() {
		return id;
	}
	
	private Creature caster;
	public Creature caster() {
		return caster;
	}
	
	private boolean isSelfCast;
	public boolean isSelfCast() {
		return isSelfCast;
	}
	public void setSelfCast(boolean value) {
		isSelfCast = value;
	}
	
	private boolean castOnTile;
	public boolean castOnTile() {
		return castOnTile;
	}
	public void setCastOnTile(boolean value) {
		castOnTile = value;
	}
	
	private Effect effect;
	public Effect effect() {
		return (Effect) effect.clone();
		
	}
	
	public void makeEffectNegative() {
		effect.modifyIsNegative(1);
	}
	
	public void makeEffectIgnited() {
		effect.modifyIsIgnited(1);
	}
	
	public void makeEffectFrozen() {
		effect.modifyIsFrozen(1);
	}
	
	public void makeEffectElectrified() {
		effect.modifyIsElectrified(1);
	}
	
	public void makeEffectParalysis() {
		effect.modifyIsParalysis(1);
	}
	
	public void makeEffectCorrosion() {
		effect.modifyIsCorrosion(1);
	}
	
	public void makeEffectPoison() {
		effect.modifyIsPoison(1);
	}
	
	public void makeEffectConfused() {
		effect.modifyIsConfused(1);
	}
	
	public Spell(String name, int id, int manaCost, Creature caster, Effect effect, boolean selfCast, boolean tileCast) {
		this.name = name;
		this.id = id;
		this.manaCost = manaCost;
		this.caster = caster;
		this.effect = effect;
		this.isSelfCast = selfCast;
		this.castOnTile = tileCast;
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
