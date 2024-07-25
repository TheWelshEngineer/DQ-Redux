package RogueLike.Main.Screens;

import java.awt.*;

public class TerminalChar {
    public final char glyph;
    public final Color foreground;
    public final Color background;

    public TerminalChar(char glyph, Color foreground, Color background) {
        this.glyph = glyph;
        this.foreground = foreground;
        this.background = background;
    }
}
