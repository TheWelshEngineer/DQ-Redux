package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class VersatileFinesseWeapon extends VersatileMeleeWeapon{

	private static final long serialVersionUID = 3829177715032040523L;

	public VersatileFinesseWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, Dice versatileDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, versatileDice, goldValue, id);
		this.setIsFinesse(true);
	}

}
