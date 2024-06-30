package RogueLike.Main.Screens;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TerminalText implements Iterable<TerminalChar>{
    private final List<TerminalChar> terminalChars = new ArrayList<>();

    public List<TerminalChar> terminalChars() {
        return terminalChars;
    }

    public TerminalText() {}

    public TerminalText(String text, Color foreground, Color background) {
        this.append(text, foreground, background);
    }

    public TerminalText append(String text, Color foreground, Color background) {
        text.chars().forEach(c -> terminalChars.add(new TerminalChar((char)c, foreground, background)));
        return this; // for method chaining
    }

    public TerminalText append(char glyph, Color foreground, Color background) {
        terminalChars.add(new TerminalChar(glyph, foreground, background));
        return this;
    }

    @Override
    public Iterator<TerminalChar> iterator() {
        return terminalChars.iterator();
    }
}
