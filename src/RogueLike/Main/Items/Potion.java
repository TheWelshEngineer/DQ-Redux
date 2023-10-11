package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Description;
import RogueLike.Main.Effect;
import RogueLike.Main.ObjectFactory;
import asciiPanel.AsciiPanel;

public class Potion extends Item{
	
	/**
	 * 
	 * @param factory The ObjectFactory from which to reference the Potion's appearances.
	 * @param glyph A Code Page 437 char which visually represents the Potion.
	 * @param name A string with the first letter of each word capitalised.
	 * @param appearance An integer representing a key in factory.potionAppearances.
	 * @param inventoryStatusEffectName A string with the first letter of each word capitalised, used to display the Potion's effect in the inventory.
	 * @param statusEffect An Effect to be applied when the Potion is consumed. 
	 * @param goldValue The base value in gold of the Potion.
	 * @param id The unique identifying integer of the item.
	 */
	public Potion(ObjectFactory factory, char glyph, String name, int appearance, String inventoryStatusEffectName, Effect statusEffect, int goldValue, int id) {
		super(glyph, AsciiPanel.white, name, null);
		this.setAppearance(factory.potionAppearances.get(appearance));
		Description description = factory.potionColors.get(this.appearance());
		description.setPotionEffectName(inventoryStatusEffectName.toLowerCase());
		this.setDescription(description);
		this.setPotionName(inventoryStatusEffectName);
		this.setColor(description.getColor());
		this.setQuaffEffect(statusEffect);
		this.setBaseGoldValue(goldValue);
		this.setCurrentGoldValue(this.baseGoldValue());
		this.setIsStackable(true);
		this.setIsPotion(true);
		this.setQuickslottable(true);
		this.setID(id);
	}
	

}
