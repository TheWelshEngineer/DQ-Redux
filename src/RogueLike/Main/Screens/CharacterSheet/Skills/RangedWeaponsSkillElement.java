package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;
import RogueLike.Main.Skill;

public class RangedWeaponsSkillElement extends SkillElement {

    public RangedWeaponsSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected Skill skill() {
        return player.skills().rangedWeapons;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format("Ranged Weapons I: You add your proficiency bonus (+%s) to attack rolls made with Ranged Weapons.", player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Ranged Weapons II: You add your proficiency bonus (+%s) to damage rolls made with Ranged Weapons.", player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel3() {
        return "Ranged Weapons III: Attacks with Ranged Weapons refund the spent ammunition upon the target's death.";
    }
}
