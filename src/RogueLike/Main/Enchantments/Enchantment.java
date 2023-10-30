package RogueLike.Main.Enchantments;

import RogueLike.Main.Effect;

public class Enchantment {
	
	private String name;
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private Effect effect;
	public Effect effect() {
		return effect;
	}
	public void setEffect(Effect effect) {
		this.effect = effect;
	}

}
