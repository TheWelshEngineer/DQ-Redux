package RogueLike.Main.Items;

import java.awt.Color;

public class BasicMeleeWeapon extends Item{

	public BasicMeleeWeapon(char glyph, Color color, String name, String appearance, int goldValue, int id) {
		super(glyph, color, name, appearance);
		
		this.setBaseGoldValue(goldValue);
		this.setCurrentGoldValue(this.baseGoldValue());
		this.setID(id);	}

}
