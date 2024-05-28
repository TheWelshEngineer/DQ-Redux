package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class ExpElement extends CharacterSheetElement {
    private final Creature player;

    public ExpElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("XP: %d/%d", player.xp(), player.xpToNextLevel());
    }

    @Override
    public String details() {
        return String.format("You require %d more experience points to level up.\n", player.xpToNextLevel())
            + String.format("You have %d available attribute points.\n", player.attributePoints())
            + String.format("You have %d available skill points.", player.skillPoints());
    }
}
