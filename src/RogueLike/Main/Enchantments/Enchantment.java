package RogueLike.Main.Enchantments;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.Creatures.Creature;

public class Enchantment {
	
	private String name;
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private int activationChance = 10;
	public int activationChance() {
		return activationChance;
	}
	public void setActivationChance(int value) {
		activationChance = value;
	}
	
	public boolean checkActivation(int bonus) {
		return Dice.d100.roll() >= (100 - activationChance - bonus);
	}
	
	private String damageTypeString = null;
	public String damageTypeString() {
		return damageTypeString;
	}
	public void setDamageTypeString(String type) {
		damageTypeString = type;
	}
	
	private Effect effect;
	public Effect effect() {
		return (Effect) effect.clone();
	}
	public void setEffect(Effect effect) {
		this.effect = (Effect) effect.clone();
	}
	
	public void applyToCreature(Creature creature) {
		creature.addEffect(effect());
	}
	
	public Enchantment(String name, Effect effect, String type) {
		this.name = name;
		this.effect = effect;
		this.damageTypeString = type;
	}
	
	public Enchantment(String name, Effect effect) {
		this.name = name;
		this.effect = effect;
	}

}
