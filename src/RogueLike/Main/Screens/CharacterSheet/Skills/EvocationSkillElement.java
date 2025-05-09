package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.SkillInstance;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;

public class EvocationSkillElement extends SkillElement {

    public EvocationSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected SkillInstance skill() {
        return player.skills().evocation;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format("Evocation I: Add your proficiency bonus (%+d) to attack rolls made with Evocation spells. Add your proficiency bonus to the duration of Evocation spell effects, making them last %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Evocation II: Add your proficiency bonus (%+d) to damage rolls made with Evocation spells. Add your proficiency bonus to the duration of Evocation spell effects again, making them last a total of %d extra turns.", player.proficiencyBonus(), player.proficiencyBonus() * 2);
    }

    @Override
    protected String descriptionLevel3() {
        return String.format("Evocation III: Critical hits with Evocation spells restore 50%% of your maximum Mana (%d MP).", player.maxMana() / 2);
    }
}
