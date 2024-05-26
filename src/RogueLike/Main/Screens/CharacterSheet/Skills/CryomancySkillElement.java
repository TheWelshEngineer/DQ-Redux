package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;

public class CryomancySkillElement extends SkillElement {

    public CryomancySkillElement(Creature player) {
        super(player);
    }

    @Override
    protected int skillIndex() {
        return 10;
    }

    @Override
    protected String descriptionLevel1() {
        return "";
    }

    @Override
    protected String descriptionLevel2() {
        return "";
    }

    @Override
    protected String descriptionLevel3() {
        return "";
    }
}
