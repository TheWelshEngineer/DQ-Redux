package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class HungerElement extends CharacterSheetElement {
    private final Creature player;

    public HungerElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Hunger: %s", player.hungerAsString());
    }

    @Override
    public String details() {
        return String.format("You are %s. You'll probably next need to eat after %d turns of exploration.", player.hungerAsString(), player.food()/2);
    }
}
