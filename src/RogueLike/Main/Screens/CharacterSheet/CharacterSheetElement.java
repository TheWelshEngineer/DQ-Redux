package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Screens.TerminalText;

public abstract class CharacterSheetElement {
    protected abstract String header();

    public TerminalText formattedHeader(ExtendedAsciiPanel terminal) {
        return new TerminalText(
                header(),
                terminal.getDefaultForegroundColor(),
                terminal.getDefaultBackgroundColor());
    }
    ;

    public abstract String details();

    public boolean isSelectable() {
        return true;
    }
}
