package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class DexterityElement extends CharacterSheetElement {
    private final Creature player;

    public DexterityElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Dexterity: %d (%+d)", player.dexterity(), player.dexterityModifier());
    }

    @Override
    public String details1() {
        return String.format("You have %d total points of Dexterity, granting a %+d modifier to Dexterity rolls.", player.dexterity(), player.dexterityModifier());
    }

    @Override
    public String details2() {
        return String.format("You have %d natural points of Dexterity, out of a maximum of 30.", player.baseDexterity());
    }

}
