package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class NameElement extends CharacterSheetElement {
    private final Creature player;
    public NameElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return player.playerName();
    }

    @Override
    public String details1() {
        return "That's you!";
    }
}
