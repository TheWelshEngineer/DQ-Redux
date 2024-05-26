package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class VisionRadiusElement extends CharacterSheetElement {
    private final Creature player;

    public VisionRadiusElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Vision Radius: %d tiles", player.visionRadius());
    }

    @Override
    public String details() {
        return String.format("You have a vision radius of %d tiles.", player.visionRadius());
    }
}
