package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.EnumSet;
import java.util.List;

import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.Enums.PlayerClass;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Skill;
import RogueLike.Main.Skillset;
import RogueLike.Main.Utils.PlayerBuildDetails;


public class ChooseSkillScreen implements Screen{
	private final PlayerClass playerClass;
	private final String playerName;
	private final PlayerAncestry playerAncestry;
	private final List<Integer> playerAbilities;

	private int skillPoints = 2;

	/** The index currently selected by the user. */
	private int check = 0;
	/** Skills chosen by the player. */
	private final EnumSet<Skill> skillsChosen = EnumSet.noneOf(Skill.class);
	/** Skills that are forced to be selected based on the class chosen. */
	private final EnumSet<Skill> classSkills;
	
	public ChooseSkillScreen(PlayerClass playerClass, List<Integer> playerAbilities, String playerName, PlayerAncestry playerAncestry) {
		this.playerClass = playerClass;
		this.playerAbilities = playerAbilities;
		this.playerName = playerName;
		this.playerAncestry = playerAncestry;
		if(this.playerAncestry == PlayerAncestry.HUMAN) {
			this.skillPoints = 3;
		}

		switch (playerClass) {
			case WARRIOR: classSkills = EnumSet.of(Skill.MARTIAL_WEAPONS, Skill.ARMOR_TRAINING); break;
			case ROGUE: classSkills = EnumSet.of(Skill.FINESSE_WEAPONS, Skill.STEALTH); break;
			case MAGE: classSkills = EnumSet.of(Skill.EVOCATION, Skill.SIMPLE_WEAPONS); break;
			case RANGER: classSkills = EnumSet.of(Skill.RANGED_WEAPONS, Skill.PERCEPTION); break;
			case WITCH: classSkills = EnumSet.of(Skill.ELECTROMANCY, Skill.ALCHEMANCY); break;
			case PALADIN: classSkills = EnumSet.of(Skill.ARMOR_TRAINING, Skill.FERROMANCY); break;
			default: throw new IllegalArgumentException(playerClass.toString());
		}
	}
	
	public static final char borderVertical = 186;
	public static final char borderHorizontal = 205;
//	public static final char borderCorner = 206;
	public static final char borderCornerNW = 201;
	public static final char borderCornerNE = 187;
	public static final char borderCornerSW = 200;
	public static final char borderCornerSE = 188;

	private Skill selectedSkill() {
		return Skill.values()[check];
	}
	private boolean isSkillSelected(Skill skill) {
		return skillsChosen.contains(skill) || classSkills.contains(skill);
	}

	private char booleanToChar(boolean value) {
		if(value) {
			return (char)251;
		}
		return 'X';
	}

    public void displayOutput(ExtendedAsciiPanel terminal) {
        terminal.clear();
        Screen.generateBorders(terminal);
        terminal.writeCenter("== Select your starting Skills ==", 1);
        int y = 3;

        terminal.writeCenter(String.format("-- Choices remaining: %d --", skillPoints), y++);
        terminal.write(
                String.format(
                        "%c%s%c",
                        borderCornerNW,
                        String.valueOf(borderHorizontal).repeat(29),
                        borderCornerNE),
                4,
                y++);

        for (Skill skill : Skill.values()) {
            char leftMarker = (skill == selectedSkill()) ? '>' : ' ';
            char rightMarker = (skill == selectedSkill()) ? '<' : ' ';
            terminal.write(
                    String.format(
                            "%c %c %-17s ( %c ) %c %c",
                            borderVertical,
                            leftMarker,
                            skill,
                            booleanToChar(
                                    skillsChosen.contains(skill) || classSkills.contains(skill)),
                            rightMarker,
                            borderVertical),
                    4,
                    y++);
        }
        terminal.write(
                String.format(
                        "%c%s%c",
                        borderCornerSW,
                        String.valueOf(borderHorizontal).repeat(29),
                        borderCornerSE),
                4,
                y++);

        switch (selectedSkill()) {
            case SIMPLE_WEAPONS:
                terminal.writeCenter(
                        "Simple Weapons represents your skill with simple weapons", y += 5);
                terminal.writeCenter(
                        "such as clubs and handaxes. A higher Simple Weapons skill", y += 1);
                terminal.writeCenter("allows you to use more powerful simple weapons,", y += 1);
                terminal.writeCenter(
                        "and improves your accuracy with weapons of this type.", y += 1);
                break;
            case MARTIAL_WEAPONS:
                terminal.writeCenter(
                        "Martial Weapons represents your skill with martial weapons", y += 5);
                terminal.writeCenter(
                        "such as longswords and battleaxes. A higher Martial Weapons skill",
                        y += 1);
                terminal.writeCenter("allows you to use more powerful martial weapons,", y += 1);
                terminal.writeCenter(
                        "and improves your accuracy with weapons of this type.", y += 1);
                break;
            case ARMOR_TRAINING:
                terminal.writeCenter(
                        "Armor Training represents your ability to move and fight", y += 5);
                terminal.writeCenter("whilst wearing armor. A higher Armor Training skill", y += 1);
                terminal.writeCenter(
                        "allows you to use heavier armor and shields, and provides", y += 1);
                terminal.writeCenter(
                        "further bonuses to your Armor Class at higher levels.", y += 1);
                break;
            case FORTITUDE:
                terminal.writeCenter(
                        "Fortitude is a measure of your stamina and constitution.", y += 5);
                terminal.writeCenter(
                        "A higher Fortitude skill increases the length of time you", y += 1);
                terminal.writeCenter(
                        "can go without eating, and lessens the negative effects", y += 1);
                terminal.writeCenter("incurred by eating certain food items.", y += 1);
                break;
            case FINESSE_WEAPONS:
                terminal.writeCenter(
                        "Finesse Weapons represents your skill with finesse weapons", y += 5);
                terminal.writeCenter(
                        "such as daggers and rapiers. A higher Finesse Weapons skill", y += 1);
                terminal.writeCenter("allows you to use more powerful finesse weapons,", y += 1);
                terminal.writeCenter(
                        "and improves your accuracy with weapons of this type.", y += 1);
                break;
            case RANGED_WEAPONS:
                terminal.writeCenter(
                        "Ranged Weapons represents your skill with ranged weapons", y += 5);
                terminal.writeCenter(
                        "such as bows and crossbows. A higher Ranged Weapons skill", y += 1);
                terminal.writeCenter("allows you to use more powerful ranged weapons,", y += 1);
                terminal.writeCenter(
                        "and improves your accuracy with weapons of this type.", y += 1);
                break;
            case STEALTH:
                terminal.writeCenter("Stealth represents your ability to move unnoticed.", y += 5);
                terminal.writeCenter(
                        "A higher Stealth skill allows you to sneak past sleeping", y += 1);
                terminal.writeCenter(
                        "and unalerted foes more reliably, giving you the upper", y += 1);
                terminal.writeCenter(
                        "hand in combat and making it easier to avoid a fight.", y += 1);
                break;
            case PERCEPTION:
                terminal.writeCenter(
                        "Perception is a measure of your awareness and eye for detail.", y += 5);
                terminal.writeCenter(
                        "A higher Perception skill allows you to spot traps and other", y += 1);
                terminal.writeCenter(
                        "hidden things with greater ease, as well as improving", y += 1);
                terminal.writeCenter("your chances of identifying unknown items.", y += 1);
                break;
            case EVOCATION:
                terminal.writeCenter(
                        "Evocation is a school of magic focused mainly on the", y += 5);
                terminal.writeCenter("manipulation of raw magic. A higher Evocation skill", y += 1);
                terminal.writeCenter("allows you to use more powerful evocation wands,", y += 1);
                terminal.writeCenter(
                        "and improves the effectiveness of your evocation wands.", y += 1);
                break;
            case PYROMANCY:
                terminal.writeCenter(
                        "Pyromancy is a school of magic focused mainly on the", y += 5);
                terminal.writeCenter(
                        "manipulation of fire and heat. A higher Pyromancy skill", y += 1);
                terminal.writeCenter("allows you to use more powerful pyromancy wands,", y += 1);
                terminal.writeCenter(
                        "and improves the effectiveness of your pyromancy wands.", y += 1);
                break;
            case CRYOMANCY:
                terminal.writeCenter(
                        "Cryomancy is a school of magic focused mainly on the", y += 5);
                terminal.writeCenter(
                        "manipulation of water and ice. A higher Cryomancy skill", y += 1);
                terminal.writeCenter("allows you to use more powerful cryomancy wands,", y += 1);
                terminal.writeCenter(
                        "and improves the effectiveness of your cryomancy wands.", y += 1);
                break;
            case ELECTROMANCY:
                terminal.writeCenter(
                        "Electromancy is a school of magic focused mainly on the", y += 5);
                terminal.writeCenter(
                        "manipulation of electrical energy. A higher Electromancy skill", y += 1);
                terminal.writeCenter("allows you to use more powerful electromancy wands,", y += 1);
                terminal.writeCenter(
                        "and improves the effectiveness of your electromancy wands.", y += 1);
                break;
            case ALCHEMANCY:
                terminal.writeCenter(
                        "Alchemancy is a school of magic focused mainly on the", y += 5);
                terminal.writeCenter(
                        "manipulation of chemical processes. A higher Alchemancy skill", y += 1);
                terminal.writeCenter("allows you to use more powerful alchemancy wands,", y += 1);
                terminal.writeCenter(
                        "and improves the effectiveness of your alchemancy wands.", y += 1);
                break;
            case FERROMANCY:
                terminal.writeCenter(
                        "Ferromancy is a school of magic focused mainly on the", y += 5);
                terminal.writeCenter(
                        "manipulation of stone and metals. A higher Ferromancy skill", y += 1);
                terminal.writeCenter("allows you to use more powerful ferromancy wands,", y += 1);
                terminal.writeCenter(
                        "and improves the effectiveness of your ferromancy wands.", y += 1);
                break;
            default:
                throw new IllegalArgumentException(selectedSkill().toString());
        }

        if (skillPoints < 1) {
            terminal.writeCenter(
                    String.format(
                            "-- [%s]: Confirm and Continue --",
                            KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)),
                    38);
        } else {
            terminal.writeCenter("-- Assign all skill points to continue --", 38);
        }
        terminal.writeCenter(
                String.format(
                        "-- [%s / %s]: Move Selection | [%s / %s]: Select/Deselect Skill --",
                        KeybindManager.keybindText(KeybindManager.navigateMenuUp),
                        KeybindManager.keybindText(KeybindManager.navigateMenuDown),
                        KeybindManager.keybindText(KeybindManager.navigateMenuLeft),
                        KeybindManager.keybindText(KeybindManager.navigateMenuRight)),
                40);
        terminal.writeCenter(
                String.format(
                        "-- [%s]: Return to Ability Score selection --",
                        KeybindManager.keybindText(KeybindManager.navigateMenuBack)),
                42);
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeybindManager.navigateMenuUp:
                if (check == 0) {
                    check = Skill.values().length - 1;
                } else {
                    check--;
                }
                return this;

            case KeybindManager.navigateMenuDown:
                if (check == Skill.values().length - 1) {
                    check = 0;
                } else {
                    check++;
                }
                return this;

            case KeybindManager.navigateMenuRight:
                if (skillPoints > 0 && !isSkillSelected(selectedSkill())) {
                    skillPoints--;
                    skillsChosen.add(selectedSkill());
                }
                return this;

            case KeybindManager.navigateMenuLeft:
                if (skillsChosen.contains(selectedSkill())) {
                    // note that we can only unselect manually selected skills, and not class skills
                    skillPoints++;
                    skillsChosen.remove(selectedSkill());
                }
                return this;

            case KeybindManager.navigateMenuConfirm:
                if (skillPoints < 1) {
                    Skillset skillset = new Skillset();
                    for (Skill skill : Skill.values()) {
                        skillset.get(skill).setLevel(isSkillSelected(skill) ? 1 : 0);
                    }
                    return new Zone1Screen(
                            new PlayerBuildDetails(
                                    playerClass,
                                    playerAbilities,
                                    skillset,
                                    playerName,
                                    playerAncestry));
                } else {
                    return this;
                }

            case KeybindManager.navigateMenuBack:
                return new ChooseAbilityScreen(playerClass, playerAncestry);

            default:
                return this;
        }
    }
}

