package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class ArrowsRangedWeapon extends BasicRangedWeapon{


	private static final long serialVersionUID = 1594382654633866724L;

	public ArrowsRangedWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setUsesArrowAmmunition(true);
	}

}
