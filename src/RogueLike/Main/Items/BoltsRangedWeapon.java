package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class BoltsRangedWeapon extends BasicRangedWeapon{

	private static final long serialVersionUID = 3528148627133898856L;

	public BoltsRangedWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setUsesBoltAmmunition(true);
	}

}
