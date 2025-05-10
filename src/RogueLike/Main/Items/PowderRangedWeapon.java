package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class PowderRangedWeapon extends BasicRangedWeapon{

	private static final long serialVersionUID = 3104962000297629016L;

	public PowderRangedWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setUsesPowderAmmunition(true);
	}

}
