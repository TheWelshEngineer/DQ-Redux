package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class BasicThrownWeapon extends BasicMeleeWeapon{

	public BasicThrownWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, Dice thrownDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setIsThrownWeapon(true);
		this.setThrownDamageDice(thrownDice);
	}

}
