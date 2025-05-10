package RogueLike.Main.Items;

import java.awt.Color;

public class HeavyArmor extends BasicArmor{

	private static final long serialVersionUID = -8804087714847410276L;

	public HeavyArmor(char glyph, Color color, String name, String appearance, int armorClass, int goldValue, int id) {
		super(glyph, color, name, appearance, armorClass, goldValue, id);
		this.setIsHeavyArmor(true);
	}

}
