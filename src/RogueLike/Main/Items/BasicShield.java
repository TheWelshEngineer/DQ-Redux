package RogueLike.Main.Items;

import java.awt.Color;

public class BasicShield extends BasicArmor{

	public BasicShield(char glyph, Color color, String name, String appearance, int armorClass, int goldValue, int id) {
		super(glyph, color, name, appearance, armorClass, goldValue, id);
		this.setIsArmor(false);
		this.setIsShield(true);
	}



}
