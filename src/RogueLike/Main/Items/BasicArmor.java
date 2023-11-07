package RogueLike.Main.Items;

import java.awt.Color;

public class BasicArmor extends Item{

	public BasicArmor(char glyph, Color color, String name, String appearance, int armorClass, int goldValue, int id) {
		super(glyph, color, name, appearance);
		this.setIsArmor(true);
		this.modifyArmorClass(armorClass);
		this.setIsEquippable(true);
		this.setBaseGoldValue(goldValue);
		this.setCurrentGoldValue(this.baseGoldValue());
		this.setID(id);
	}

}
