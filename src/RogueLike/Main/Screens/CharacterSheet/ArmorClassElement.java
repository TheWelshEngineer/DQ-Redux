package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class ArmorClassElement extends CharacterSheetElement {
    private final Creature player;

    public ArmorClassElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Armor Class: %d", player.armorClass());
    }

    public String details() {
        return String.format("You have a total Armor Class of %d.\n", player.armorClass())
                + String.format("You have a natural Armor Class of %d.", player.baseArmorClass());
    }
}
