package RogueLike.Main.Screens.CharacterSheet;

import RogueLike.Main.Creatures.Player;

public class LevelElement extends CharacterSheetElement {
    private final Player player;

    public LevelElement(Player player) {
        this.player = player;
    }

    @Override
    public String header() {
        return String.format("Level %d", player.level());
    }

    @Override
    public String details() {
        return String.format(
                        "You are a level %d %s %s.\n",
                        player.level(), player.playerAncestry(), player.playerClass())
                + details2()
                + "\n"
                + details3();
    }

    private String details2() {
        switch (player.playerClass()) {
            case ROGUE:
                return "As a Rogue, you regenerate health and mana at a roughly even rate.";
            case RANGER:
                return "As a Ranger, you regenerate health and mana at a roughly even rate.";
            case WARRIOR:
                return "As a Warrior, your health regenerates quickly, at the expense of your"
                        + " mana.";
            case PALADIN:
                return "As a Paladin, your health regenerates quickly, at the expense of your"
                        + " mana.";
            case MAGE:
                return "As a Mage, your mana regenerates quickly, at the expense of your health.";
            case WITCH:
                return "As a Witch, your mana regenerates quickly, at the expense of your health.";
            default:
                throw new IllegalStateException(player.playerClass().toString());
        }
    }

    private String details3() {
        switch (player.playerAncestry()) {
            case HUMAN:
                return "As a Human, you began your quest with an addtional skill point.";
            case ELF:
                return "As an Elf, you gain 25% more maximum mana upon levelling up.";
            case DWARF:
                return "As a Dwarf, you are resistant to Poison damage, and your base armor class"
                        + " is increased by 1.";
            case ORC:
                return "As an Orc, you regenerate health whenever you eat, based on the quality of"
                        + " the food item you ate.";
            case DRAGONBORN:
                return "As a Dragonborn, you are resistant to Fire damage, and began your quest"
                        + " with a Wand of Firebolt.";
            default:
                throw new IllegalStateException(player.playerAncestry().toString());
        }
    }
}
