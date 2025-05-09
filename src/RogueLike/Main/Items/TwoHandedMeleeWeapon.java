package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class TwoHandedMeleeWeapon extends BasicMeleeWeapon{

	private static final long serialVersionUID = -7696428901667355034L;

	public TwoHandedMeleeWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setIsTwoHanded(true);
	}

}
