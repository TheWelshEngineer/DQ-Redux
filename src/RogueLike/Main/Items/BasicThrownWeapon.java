package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class BasicThrownWeapon extends BasicMeleeWeapon{

	private static final long serialVersionUID = 1989224138148789951L;

	public BasicThrownWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, Dice thrownDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setIsThrownWeapon(true);
		this.setThrownDamageDice(thrownDice);
	}
}
