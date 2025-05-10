package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Screens.TerminalText;

public abstract class CharacterSheetElement {
    protected abstract String header();
    public TerminalText formattedHeader() {
        return new TerminalText(header(), ExtendedAsciiPanel.getDefaultForegroundColor(), ExtendedAsciiPanel.getDefaultBackgroundColor());
    };

    public abstract String details();

    public boolean isSelectable() {
        return true;
    }
}
