package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;
import RogueLike.Main.Skill;

public class FerromancySkillElement extends SkillElement {

    public FerromancySkillElement(Creature player) {
        super(player);
    }

    @Override
    protected Skill skill() {
        return player.skills().ferromancy;
    }


    @Override
    protected String descriptionLevel1() {
        return String.format("Ferromancy I: Add your proficiency bonus (%+d) to attack rolls made with Ferromancy spells. Add your proficiency bonus to the duration of Ferromancy spell effects, making them last %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Ferromancy II: Add your proficiency bonus (%+d) to damage rolls made with Ferromancy spells. Add your proficiency bonus to the duration of Ferromancy spell effects again, making them last a total of %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus() * 2);
    }

    @Override
    protected String descriptionLevel3() {
        return String.format("Ferromancy III: Critical hits with Ferromancy spells grant Giant Strength for a number of turns equal to your proficiency bonus (%d turns).", player.proficiencyBonus());
    }
}
