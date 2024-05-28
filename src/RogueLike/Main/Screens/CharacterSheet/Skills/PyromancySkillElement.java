package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;

public class PyromancySkillElement extends SkillElement {

    public PyromancySkillElement(Creature player) {
        super(player);
    }

    @Override
    protected int skillIndex() {
        return 9;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format("Pyromancy I: Add your proficiency bonus (%+d) to attack rolls made with Pyromancy spells. Add your proficiency bonus to the duration of Pyromancy spell effects, making them last %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Pyromancy II: Add your proficiency bonus (%+d) to damage rolls made with Pyromancy spells. Add your proficiency bonus to the duration of Pyromancy spell effects again, making them last a total of %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus() * 2);
    }

    @Override
    protected String descriptionLevel3() {
        return String.format("Pyromancy III: Critical hits with Pyromancy spells apply Ignited to the target for a number of turns equal to twice your proficiency bonus (%d turns).", 2 * player.proficiencyBonus());
    }
}
