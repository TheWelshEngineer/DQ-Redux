package RogueLike.Main.Items;

import RogueLike.Main.Description;
import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.FactoryManager;

public class Potion extends Item{
	
	
	
	private static final long serialVersionUID = -815805817569438200L;

	/**
	 * 
	 * @param factory The ObjectFactory from which to reference the Potion's appearances.
	 * @param glyph A Code Page 437 char which visually represents the Potion.
	 * @param name A string with the first letter of each word capitalised.
	 * @param appearance An integer representing a key in FactoryManager.getObjectFactory().potionAppearances.
	 * @param inventoryStatusEffectName A string with the first letter of each word capitalised, used to display the Potion's effect in the inventory.
	 * @param statusEffect An Effect to be applied when the Potion is consumed. 
	 * @param goldValue The base value in gold of the Potion.
	 * @param id The unique identifying integer of the item.
	 */
	public Potion( char glyph, String name, int appearance, String inventoryStatusEffectName, Effect statusEffect, boolean negative, int goldValue, int id) {
		super(glyph, ExtendedAsciiPanel.white, name, null);
		this.setAppearance(FactoryManager.getObjectFactory().potionAppearances.get(appearance));
		Description description = FactoryManager.getObjectFactory().potionColors.get(this.getAppearance());
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
		this.setIsNegativePotion(negative);
		this.setID(id);
	}
	

}
