package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.SkillInstance;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;

public class MartialWeaponsSkillElement extends SkillElement {

    public MartialWeaponsSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected SkillInstance skill() {
        return player.skills().martialWeapons;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format("Martial Weapons I: You add your proficiency bonus (+%s) to attack rolls made with Martial Weapons.", player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Martial Weapons II: You add your proficiency bonus (+%s) to damage rolls made with Martial Weapons.", player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel3() {
        return "Martial Weapons III: Critical hits with Martial Weapons deal 3x damage (up from 2x).";
    }
}
