package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Dice;

public class BasicFinesseWeapon extends BasicMeleeWeapon{

	private static final long serialVersionUID = 3114849003141226497L;

	public BasicFinesseWeapon(char glyph, Color color, String name, String appearance, Dice damageDice, int goldValue, int id) {
		super(glyph, color, name, appearance, damageDice, goldValue, id);
		this.setIsFinesse(true);
	}

}
