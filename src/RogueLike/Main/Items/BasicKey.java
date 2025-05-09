package RogueLike.Main.Items;

import java.awt.Color;

public class BasicKey extends Item{

	private static final long serialVersionUID = 1344408275724237313L;

	public BasicKey(char glyph, Color color, String name, String appearance, int depth, int id) {
		super(glyph, color, name, appearance);
		this.setID(id);
		this.setKeyDepth(depth);
	}

}
