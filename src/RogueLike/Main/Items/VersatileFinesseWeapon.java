package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class VersatileFinesseWeapon extends VersatileMeleeWeapon{

	public VersatileFinesseWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, Dice versatileDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, versatileDice, goldValue, id);
		this.setIsFinesse(true);
	}

}
