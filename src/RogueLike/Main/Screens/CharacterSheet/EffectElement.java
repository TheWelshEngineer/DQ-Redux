package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Effect;

public class EffectElement extends CharacterSheetElement {
    private final Effect effect;
    public EffectElement(Effect effect) {
        this.effect = effect;
    }

    @Override
    public String header() {
        char effectIcon = effect.isNegative() ? (char)31 : (char)30;
        String turns = effect.duration() == 1 ? "turn" : "turns";
        return String.format("%c %s: %d %s", effectIcon, effect.name(), effect.duration(), turns);
    }

    @Override
    public String details() {
        return ""; // TODO add effect descriptions?
    }
}
