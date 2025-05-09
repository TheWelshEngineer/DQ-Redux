package RogueLike.Main.Items;

import java.awt.Color;

public class LightArmor extends BasicArmor{

	private static final long serialVersionUID = -8890699298904221821L;

	public LightArmor(char glyph, Color color, String name, String appearance, int armorClass, int goldValue, int id) {
		super(glyph, color, name, appearance, armorClass, goldValue, id);
		this.setIsLightArmor(true);
	}

}
