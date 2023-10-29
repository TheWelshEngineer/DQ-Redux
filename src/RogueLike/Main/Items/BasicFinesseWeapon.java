package RogueLike.Main.Items;

import java.awt.Color;

public class BasicFinesseWeapon extends BasicMeleeWeapon{

	public BasicFinesseWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setIsFinesse(true);
	}

}
