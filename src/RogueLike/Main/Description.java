package RogueLike.Main;

import java.awt.Color;

public class Description {
	
	private Color color;
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	private String mainAdjective;
	public String getMainAdjective() {
		return mainAdjective;
	}
	public void setMainAdjective(String adjective) {
		this.mainAdjective = adjective;
	}
	
	private String potionAdjective;
	public String getPotionAdjective() {
		return potionAdjective;
	}
	public void setPotionAdjective(String adjective) {
		this.potionAdjective = adjective;
	}
	
	private String potionBottle;
	public String getPotionBottle() {
		return potionBottle;
	}
	public void setPotionBottle(String bottle) {
		this.potionBottle = bottle;
	}
	
	private String potionEffectName;
	public String getPotionEffectName() {
		return potionEffectName;
	}
	public void setPotionEffectName(String effectName) {
		this.potionEffectName = effectName;
	}
	
	public Description(String adjective, Color color) {
		this.mainAdjective = adjective;
		this.color = color;
		this.potionAdjective = randomPotionAdjective();
		this.potionBottle = randomPotionBottle();
	}
	
	public String getPotionDescriptionBase() {
		return String.format("This is a %s %s potion held within a %s flask.", potionAdjective, mainAdjective, potionBottle);
	}
	public String getPotionDescriptionUnknown() {
		return "Who knows what it'll do if you drink it?";
	}
	public String getPotionDescriptionKnown() {
		return String.format("You know this to be a potion of %s.", potionEffectName);
	}
	
	public String randomPotionAdjective() {
		switch(ExtraMaths.diceRoll(1, 8)) {
		case 1: return "sparkling";
		case 2: return "fizzy";
		case 3: return "bubbling";
		case 4: return "still";
		case 5: return "flat";
		case 6: return "murky";
		case 7: return "sludgy";
		case 8: return "thick";
		default: return "sparkling";
		}
	}
	
	public String randomPotionBottle() {
		switch(ExtraMaths.diceRoll(1, 8)) {
		case 1: return "round";
		case 2: return "square";
		case 3: return "conical";
		case 4: return "intricate";
		case 5: return "simple";
		case 6: return "chipped";
		case 7: return "leaky";
		case 8: return "pristine";
		default: return "round";
		}
	}

}
