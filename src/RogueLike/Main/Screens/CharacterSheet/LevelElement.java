package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Creature;

public class LevelElement extends CharacterSheetElement {
    private final Creature player;

    public LevelElement(Creature player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Level %d", player.level());
    }

    @Override
    public String details1() {
        return String.format("You are a level %d %s %s.", player.level(), player.playerAncestry(), player.playerClass());
    }

    @Override
    public String details2() {
        if (player.playerClass().equals("Rogue")) {
            return "As a Rogue, you regenerate health and mana at a roughly even rate.";
        } else if (player.playerClass().equals("Ranger")) {
            return "As a Ranger, you regenerate health and mana at a roughly even rate.";
        } else if (player.playerClass().equals("Warrior")) {
            return "As a Warrior, your health regenerates quickly, at the expense of your mana.";
        } else if (player.playerClass().equals("Mage")) {
            return "As a Mage, your mana regenerates quickly, at the expense of your health.";
        } else {
            throw new IllegalStateException(player.playerClass());
        }
    }

    @Override
    public String details3() {
        if (player.playerAncestry().equals("Human")) {
            return "As a Human, you began your quest with an addtional skill point.";
        } else if (player.playerAncestry().equals("Elf")) {
            return "As an Elf, you gain 25% more maximum mana upon levelling up.";
        } else if (player.playerAncestry().equals("Dwarf")) {
            return "As a Dwarf, you are resistant to Poison damage, and your base armor class is increased by 1.";
        } else if (player.playerAncestry().equals("Orc")) {
            return "As an Orc, you regenerate health whenever you eat, based on the quality of the food item you ate.";
        } else if (player.playerAncestry().equals("Dragonborn")) {
            return "As a Dragonborn, you are resistant to Fire damage, and began your quest with a Wand of Firebolt.";
        } else {
            throw new IllegalStateException(player.playerAncestry());
        }
    }
}
