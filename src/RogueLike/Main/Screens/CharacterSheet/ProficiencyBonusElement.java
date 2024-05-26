package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class ProficiencyBonusElement extends CharacterSheetElement {
    private final Creature player;

    public ProficiencyBonusElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Proficiency Bonus: %+d", player.proficiencyBonus());
    }

    @Override
    public String details1() {
        return String.format("As a level %d adventurer, you have a +%d proficiency bonus.", player.level(), player.proficiencyBonus());
    }
}
