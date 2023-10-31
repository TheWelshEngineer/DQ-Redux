package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class TwoHandedFinesseWeapon extends TwoHandedMeleeWeapon{

	public TwoHandedFinesseWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setIsFinesse(true);
	}

}
