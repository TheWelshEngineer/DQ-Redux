package RogueLike.Main.Items;

import java.awt.Color;

public class BasicShield extends BasicArmor{

	private static final long serialVersionUID = 7000500851996906207L;

	public BasicShield(char glyph, Color color, String name, String appearance, int armorClass, int goldValue, int id) {
		super(glyph, color, name, appearance, armorClass, goldValue, id);
		this.setIsArmor(false);
		this.setIsShield(true);
	}



}
