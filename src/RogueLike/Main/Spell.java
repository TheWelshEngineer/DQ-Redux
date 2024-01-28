package RogueLike.Main;

import RogueLike.Main.Creatures.Creature;

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
		effect.setIsNegative(true);
	}
	
	public void makeEffectIgnited() {
		effect.setIsIgnited(true);
	}
	
	public void makeEffectFrozen() {
		effect.setIsFrozen(true);
	}
	
	public void makeEffectElectrified() {
		effect.setIsElectrified(true);
	}
	
	public void makeEffectParalysis() {
		effect.setIsParalysis(true);
	}
	
	public void makeEffectCorrosion() {
		effect.setIsCorrosion(true);
	}
	
	public void makeEffectPoison() {
		effect.setIsPoison(true);
	}
	
	public void makeEffectConfused() {
		effect.setIsConfused(true);
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
