package RogueLike.Main.Items;

import java.awt.Color;

public class CrystalKey extends BasicKey{

	private static final long serialVersionUID = 6808427085270187414L;

	public CrystalKey(char glyph, Color color, String name, String appearance, int depth, int id) {
		super(glyph, color, name, appearance, depth, id);
		this.setIsCrystalKey(true);
		this.changeName(String.format("%s (from Depth %d)", name, depth+1));
	}

}
