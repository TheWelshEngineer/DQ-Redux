package RogueLike.Main.Items;

import java.awt.Color;

public class IronKey extends BasicKey{

	public IronKey(char glyph, Color color, String name, String appearance, int depth, int id) {
		super(glyph, color, name, appearance, depth, id);
		this.setIsIronKey(true);
		this.changeName(String.format("%s (from Depth %d)", name, depth+1));
	}

}
