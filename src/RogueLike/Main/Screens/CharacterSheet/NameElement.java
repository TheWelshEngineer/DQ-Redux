package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Player;

public class NameElement extends CharacterSheetElement {
    private final Player player;

    public NameElement(Player player) {
        this.player = player;
    }

    @Override
    public String header() {
        return player.playerName();
    }

    @Override
    public String details() {
        return "That's you!";
    }
}
