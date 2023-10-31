package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class ThrownVersatileWeapon extends VersatileMeleeWeapon{

	public ThrownVersatileWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, Dice versatileDice, Dice thrownDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, versatileDice, goldValue, id);
		this.setIsThrownWeapon(true);
		this.setThrownDamageDice(thrownDice);
	}

}
