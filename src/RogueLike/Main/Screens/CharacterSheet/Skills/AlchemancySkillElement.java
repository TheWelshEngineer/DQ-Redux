package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;

public class AlchemancySkillElement extends SkillElement {

    public AlchemancySkillElement(Creature player) {
        super(player);
    }

    @Override
    protected int skillIndex() {
        return 12;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format("Alchemancy I: Add your proficiency bonus (%+d) to attack rolls made with Alchemancy spells. Add your proficiency bonus to the duration of Alchemancy spell effects, making them last %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Alchemancy II: Add your proficiency bonus (%+d) to damage rolls made with Alchemancy spells. Add your proficiency bonus to the duration of Alchemancy spell effects again, making them last a total of %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus()*2);
    }

    @Override
    protected String descriptionLevel3() {
        return String.format("Alchemancy III: Critical hits with Alchemancy spells grant Restoration and heal you for an amount equal to five times your proficiency bonus (%d HP).", 5 * player.proficiencyBonus());
    }
}
