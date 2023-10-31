package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class ThrownFinesseWeapon extends BasicThrownWeapon{

	public ThrownFinesseWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, Dice thrownDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, thrownDice, goldValue, id);
		this.setIsFinesse(true);
	}

	

}
