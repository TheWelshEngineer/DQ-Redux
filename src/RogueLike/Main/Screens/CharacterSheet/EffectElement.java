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
    public TerminalText formattedHeader() {
        String turns = effect.duration() == 1 ? "turn" : "turns";
        return new TerminalText()
            .append(effect.glyph(), effect.color(), ExtendedAsciiPanel.getDefaultBackgroundColor())
            .append(
                String.format(" %s: %d %s", effect.name(), effect.duration(), turns),
                ExtendedAsciiPanel.getDefaultForegroundColor(),
                ExtendedAsciiPanel.getDefaultBackgroundColor()
            );
    }

    @Override
    public String details() {
        return effect.description();
    }
}
