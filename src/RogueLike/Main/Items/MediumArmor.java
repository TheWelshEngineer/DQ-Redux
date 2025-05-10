package RogueLike.Main.Items;

import java.awt.Color;

public class MediumArmor extends BasicArmor{

	private static final long serialVersionUID = -6626458051792039957L;

	public MediumArmor(char glyph, Color color, String name, String appearance, int armorClass, int goldValue, int id) {
		super(glyph, color, name, appearance, armorClass, goldValue, id);
		this.setIsMediumArmor(true);
	}

}
