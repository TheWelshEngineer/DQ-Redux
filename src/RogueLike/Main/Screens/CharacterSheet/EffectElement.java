package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Screens.TerminalText;

public class EffectElement extends CharacterSheetElement {
    private final Effect effect;
    public EffectElement(Effect effect) {
        this.effect = effect;
    }

    @Override
    protected String header() {
        throw new IllegalStateException("This method should not be called.");
    }

    @Override
    public TerminalText formattedHeader(ExtendedAsciiPanel terminal) {
        String turns = effect.duration() == 1 ? "turn" : "turns";
        return new TerminalText()
            .append(effect.glyph(), effect.color(), terminal.getDefaultBackgroundColor())
            .append(
                String.format(" %s: %d %s", effect.name(), effect.duration(), turns),
                terminal.getDefaultForegroundColor(),
                terminal.getDefaultBackgroundColor()
            );
    }

    @Override
    public String details() {
        return ""; // TODO add effect descriptions?
    }
}
