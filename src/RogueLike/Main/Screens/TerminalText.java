package RogueLike.Main.Screens;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import RogueLike.Main.ExtendedAsciiPanel;

public class TerminalText implements Iterable<TerminalChar>{
    private final List<TerminalChar> terminalChars = new ArrayList<>();

    public List<TerminalChar> terminalChars() {
        return terminalChars;
    }

    public TerminalText() {}

    public TerminalText(String text, Color foreground, Color background) {
        this.append(text, foreground, background);
    }
    
    public TerminalText(String text, Color foreground) {
        this.append(text, foreground, ExtendedAsciiPanel.getDefaultBackgroundColor());
    }
    public TerminalText(String text) {
        this.append(text, ExtendedAsciiPanel.getDefaultForegroundColor(), ExtendedAsciiPanel.getDefaultBackgroundColor());
    }

    public TerminalText append(String text, Color foreground, Color background) {
        text.chars().forEach(c -> terminalChars.add(new TerminalChar((char)c, foreground, background)));
        return this; // for method chaining
    }
    
    public TerminalText append(String text, Color foreground) {
        text.chars().forEach(c -> terminalChars.add(new TerminalChar((char)c, foreground, ExtendedAsciiPanel.getDefaultBackgroundColor())));
        return this; // for method chaining
    }
    
    public TerminalText append(String text) {
        text.chars().forEach(c -> terminalChars.add(new TerminalChar((char)c, ExtendedAsciiPanel.getDefaultForegroundColor(), ExtendedAsciiPanel.getDefaultBackgroundColor())));
        return this; // for method chaining
    }

    public TerminalText append(char glyph, Color foreground, Color background) {
        terminalChars.add(new TerminalChar(glyph, foreground, background));
        return this;
    }
    
	public TerminalText append(TerminalText text) {
		 terminalChars.addAll(text.terminalChars());
		 return this; // for method chaining
		
	}

    @Override
    public Iterator<TerminalChar> iterator() {
        return terminalChars.iterator();
    }

}
