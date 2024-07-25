package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;
import RogueLike.Main.SkillInstance;

public class FinesseWeaponsSkillElement extends SkillElement {

    public FinesseWeaponsSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected SkillInstance skill() {
        return player.skills().finesseWeapons;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format(
                "Finesse Weapons I: You add your proficiency bonus (+%s) to attack rolls made with"
                        + " Finesse Weapons.",
                player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format(
                "Finesse Weapons II: You add your proficiency bonus (+%s) to damage rolls made with"
                        + " Finesse Weapons.",
                player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel3() {
        return String.format(
                "Finesse Weapons III: Critical hits with Finesse Weapons apply Bleeding to the"
                        + " target for %s turns.",
                player.proficiencyBonus());
    }
}
