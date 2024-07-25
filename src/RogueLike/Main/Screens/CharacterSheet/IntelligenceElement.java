package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class IntelligenceElement extends CharacterSheetElement {
    private final Creature player;

    public IntelligenceElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format(
                "Intelligence: %d (%+d)", player.intelligence(), player.intelligenceModifier());
    }

    @Override
    public String details() {
        return String.format(
                        "You have %d total points of Intelligence, granting a %+d modifier to"
                                + " Intelligence rolls.\n",
                        player.intelligence(), player.intelligenceModifier())
                + String.format(
                        "You have %d natural points of Intelligence, out of a maximum of 30.",
                        player.baseIntelligence());
    }
}
