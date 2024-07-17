package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;
import RogueLike.Main.SkillInstance;

public class CryomancySkillElement extends SkillElement {

    public CryomancySkillElement(Creature player) {
        super(player);
    }

    @Override
    protected SkillInstance skill() {
        return player.skills().cryomancy;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format("Cryomancy I: Add your proficiency bonus (%+d) to attack rolls made with Cryomancy spells. Add your proficiency bonus to the duration of Cryomancy spell effects, making them last %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Cryomancy II: Add your proficiency bonus (%+d) to damage rolls made with Cryomancy spells. Add your proficiency bonus to the duration of Cryomancy spell effects again, making them last a total of %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus()*2);
    }

    @Override
    protected String descriptionLevel3() {
        return String.format("Cryomancy III: Critical hits with Cryomancy spells apply Frozen to the target and all adjacent creatures for a number of turns equal to your proficiency bonus (%d turns).", player.proficiencyBonus());
    }
}
