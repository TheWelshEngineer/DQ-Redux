package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class StrengthElement extends CharacterSheetElement {
    private final Creature player;

    public StrengthElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Strength: %d (%+d)", player.strength(), player.strengthModifier());
    }

    @Override
    public String details() {
        return String.format("You have %d total points of Strength, granting a %+d modifier to Strength rolls.\n", player.strength(), player.strengthModifier())
            + String.format("You have %d natural points of Strength, out of a maximum of 30.", player.baseStrength());
    }
}
