package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.SkillInstance;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;

public class ArmorTrainingSkillElement extends SkillElement {

    public ArmorTrainingSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected SkillInstance skill() {
        return player.skills().armorTraining;
    }

    @Override
    protected String descriptionLevel1() {
        return "Armor Training I: You can equip Medium Armor and Shields.";
    }

    @Override
    protected String descriptionLevel2() {
        return "Armor Training II: You can equip Heavy Armor and Tower Shields.";
    }

    @Override
    protected String descriptionLevel3() {
        return String.format("Armor Training III: You gain a bonus to your total Armor Class equal to your proficiency bonus (+%s).", player.proficiencyBonus());
    }
}
