package RogueLike.Main.Items;

import java.awt.Color;

public class BasicKey extends Item{

	public BasicKey(char glyph, Color color, String name, String appearance, int depth, int id) {
		super(glyph, color, name, appearance);
		this.setID(id);
		this.setKeyDepth(depth);
	}

}
