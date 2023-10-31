package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class VersatileMeleeWeapon extends BasicMeleeWeapon{

	public VersatileMeleeWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, Dice versatileDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setIsVersatile(true);
		this.setVersatileDamageDice(versatileDice);
	}

}
