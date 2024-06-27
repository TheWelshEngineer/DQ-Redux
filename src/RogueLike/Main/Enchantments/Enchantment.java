package RogueLike.Main.Enchantments;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Items.Item;

public class Enchantment {
	
	private String name;
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private Item hostItem;
	public Item hostItem() {
		return hostItem;
	}
	public void setHostItem(Item item) {
		hostItem = item;
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
	
	public final DamageType damageType;

	private Effect effect;
	public Effect effect() {
		return (Effect) effect.clone();
	}
	public void setEffect(Effect effect) {
		this.effect = (Effect) effect.clone();
	}
	
	private int baseEffectDuration;
	public int baseEffectDuration() {
		return baseEffectDuration;
	}
	public void setBaseEffectDuration(int value) {
		baseEffectDuration = value;
	}
	
	public void updateEffectDuration() {
		this.effect.setDuration(baseEffectDuration + this.hostItem.upgradeLevel());
	}
	
	public void applyToCreature(Creature creature) {
		creature.addEffect(effect());
	}
	
	public Enchantment(String name, Effect effect, int duration, Item host, DamageType type) {
		this.name = name;
		this.effect = effect;
		this.damageType = type;
		this.hostItem = host;
		this.baseEffectDuration = duration;
	}
	
	public Enchantment(String name, Effect effect, int duration, Item host) {
		this(name, effect, duration, host, null);
	}

}
