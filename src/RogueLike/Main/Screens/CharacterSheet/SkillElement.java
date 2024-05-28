package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Skill;

public abstract class SkillElement extends CharacterSheetElement {
    protected final Creature player;
    protected final Skill skill;

    protected SkillElement(Creature player) {
        this.player = player;
        this.skill = player.skills()[skillIndex()];
    }

    @Override
    public String header() {
        return String.format("%s %s", skill.name(), ExtraMaths.toRomanNumerals(skill.level()));
    }

    protected abstract int skillIndex();

    protected abstract String descriptionLevel1();

    protected abstract String descriptionLevel2();

    protected abstract String descriptionLevel3();

    @Override
    public String details() {
        return String.join(
            "\n\n",
            skill.level() >= 1 ? descriptionLevel1() : "",
            skill.level() >= 2 ? descriptionLevel2() : "",
            skill.level() >= 3 ? descriptionLevel3() : ""
        );
    }
}
