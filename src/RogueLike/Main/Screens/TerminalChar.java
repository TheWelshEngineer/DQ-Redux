package RogueLike.Main.Screens;

import java.awt.Color;
import java.io.Serializable;

public class TerminalChar implements Serializable {
	private static final long serialVersionUID = 7544679336954921330L;
	public final char glyph;
	public final Color foreground;
	public final Color background;

	public TerminalChar(char glyph, Color foreground, Color background) {
		this.glyph = glyph;
		this.foreground = foreground;
		this.background = background;
	}
}
