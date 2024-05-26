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

    @Override
    public String details1() {
        return String.format("You have a total Armor Class of %d.", player.armorClass());
    }

    @Override
    public String details2() {
        return String.format("You have a natural Armor Class of %d.", player.baseArmorClass());
    }

}
