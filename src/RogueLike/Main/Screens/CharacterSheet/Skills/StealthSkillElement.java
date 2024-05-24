package RogueLike.Main.Screens.CharacterSheet.Skills;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.CharacterSheet.SkillElement;

public class StealthSkillElement extends SkillElement {

    public StealthSkillElement(Creature player) {
        super(player);
    }

    @Override
    protected int skillIndex() {
        return 6;
    }

    @Override
    protected String descriptionLevel1() {
        return String.format("Stealth I: You add your proficiency bonus (+%s) to checks made to avoid waking sleeping monsters.", player.proficiencyBonus());
    }

    @Override
    protected String descriptionLevel2() {
        return String.format("Stealth II: Whenever you Search, you expend %s Mana to become Invisible for %d turns.", player.proficiencyBonus(), player.proficiencyBonus()*2);
    }

    @Override
    protected String descriptionLevel3() {
        return "Stealth III: Attacks you make whilst Invisible are always considered to be critical hits.";
    }
}
