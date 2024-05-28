package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class ManaElement extends CharacterSheetElement {
    private final Creature player;

    public ManaElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Mana: %d/%d", player.mana(), player.maxMana());
    }

    @Override
    public String details() {
        return String.format("You have %d mana points remaining, out of a maximum of %d.", player.hp(), player.maxHP());
    }
}
