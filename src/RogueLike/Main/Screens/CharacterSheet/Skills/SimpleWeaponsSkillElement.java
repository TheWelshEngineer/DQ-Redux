package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;
import RogueLike.Main.SkillInstance;

public class SimpleWeaponsSkillElement extends SkillElement {

    public SimpleWeaponsSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected SkillInstance skill() {
        return player.skills().simpleWeapons;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format(
                "Simple Weapons I: You add your proficiency bonus (+%s) to attack rolls made with"
                        + " Simple Weapons.",
                player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format(
                "Simple Weapons II: You add your proficiency bonus (+%s) to damage rolls made with"
                        + " Simple Weapons.",
                player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel3() {
        return String.format(
                "Simple Weapons III: Critical hits with Simple Weapons Paralyse the target for %s"
                        + " turns.",
                player.proficiencyBonus());
    }
}
