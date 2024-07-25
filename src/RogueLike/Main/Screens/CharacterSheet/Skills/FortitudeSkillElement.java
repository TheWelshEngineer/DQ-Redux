package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;
import RogueLike.Main.SkillInstance;

public class FortitudeSkillElement extends SkillElement {

    public FortitudeSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected SkillInstance skill() {
        return player.skills().fortitude;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format(
                "Fortitude I: When starving, you take damage every %s turns instead of every turn.",
                player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format(
                "Fortitude II: You add your proficiency bonus (+%s) to checks made to avoid"
                        + " negative effects from eating corpses.",
                player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel3() {
        return "Fortitude III: The duration of negative effects applied to you is halves.";
    }
}
