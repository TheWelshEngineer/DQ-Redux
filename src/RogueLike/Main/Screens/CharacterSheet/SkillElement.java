package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.ExtraMaths;

public abstract class SkillElement extends CharacterSheetElement {
    protected final Creature player;

    protected SkillElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        var skill = player.skills()[skillIndex()];
        return String.format("%s %s", skill.name(), ExtraMaths.toRomanNumerals(skill.level()));
    }

    protected abstract int skillIndex();

    protected abstract String descriptionLevel1();

    protected abstract String descriptionLevel2();

    protected abstract String descriptionLevel3();

    @Override
    public String details1() {
        return player.skills()[skillIndex()].level() >= 1 ? descriptionLevel1() : "";
    }

    @Override
    public String details2() {
        return player.skills()[skillIndex()].level() >= 2 ? descriptionLevel2() : "";
    }

    @Override
    public String details3() {
        return player.skills()[skillIndex()].level() >= 3 ? descriptionLevel3() : "";
    }
}
