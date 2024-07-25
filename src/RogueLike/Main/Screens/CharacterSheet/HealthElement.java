package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class HealthElement extends CharacterSheetElement {
    private final Creature player;

    public HealthElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Health: %d/%d", player.hp(), player.maxHP());
    }

    @Override
    public String details() {
        return String.format(
                "You have %d health points remaining, out of a maximum of %d.",
                player.hp(), player.maxHP());
    }
}
