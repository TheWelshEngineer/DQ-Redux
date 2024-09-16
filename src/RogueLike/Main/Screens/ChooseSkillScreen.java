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
import RogueLike.Main.TextUtils;
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
			case MAGE: classSkills = EnumSet.of(Skill.EVOCATION, Skill.PYROMANCY); break;
			case RANGER: classSkills = EnumSet.of(Skill.RANGED_WEAPONS, Skill.PERCEPTION); break;
			case WITCH: classSkills = EnumSet.of(Skill.FINESSE_WEAPONS, Skill.ALCHEMANCY); break;
			case PALADIN: classSkills = EnumSet.of(Skill.ARMOR_TRAINING, Skill.FERROMANCY); break;
			case MONK: classSkills = EnumSet.of(Skill.SIMPLE_WEAPONS, Skill.FORTITUDE); break;
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
		return ' ';
	}

    public void displayOutput(ExtendedAsciiPanel terminal) {
        terminal.clear();
        Screen.generateBorders(terminal);
        terminal.writeCenter("== Select your starting Skills ==", 1);
        int y = 3;

        
        terminal.write(String.format("%c%s%c", borderCornerNW, String.valueOf(borderHorizontal).repeat(29), borderCornerNE), 4, y++);
        terminal.write(String.format("%c -- Choices remaining: %2d -- %c", borderVertical, skillPoints, borderVertical), 4, y++);
        terminal.write(String.format("%c                             %c", borderVertical, borderVertical), 4, y++);
        
        for (Skill skill : Skill.values()) {
            char leftMarker = (skill == selectedSkill()) ? '>' : ' ';
            char rightMarker = (skill == selectedSkill()) ? '<' : ' ';
            terminal.write(String.format( "%c %c %-17s ( %c ) %c %c", borderVertical, leftMarker, skill, booleanToChar( skillsChosen.contains(skill) || classSkills.contains(skill)), rightMarker, borderVertical), 4, y++);
        }
        terminal.write(String.format("%c%s%c", borderCornerSW, String.valueOf(borderHorizontal).repeat(29), borderCornerSE), 4, y++);

        y = 4;
        terminal.write(String.format("+||+ %s +||+", selectedSkill().toString()), 37, y++);
        y++;
        terminal.writeMultiline(TextUtils.wordWrap(selectedSkill().description, 66, 0), 41, y++);
        y+=4;
        terminal.write(String.format("+||+ %s I +||+", selectedSkill().toString()), 37, y++);
        y++;
        terminal.writeMultiline(TextUtils.wordWrap(selectedSkill().details_1, 66, 0), 41, y++);
        y+=3;
        terminal.write(String.format("+||+ %s II +||+", selectedSkill().toString()), 37, y++);
        y++;
        terminal.writeMultiline(TextUtils.wordWrap(selectedSkill().details_2, 66, 0), 41, y++);
        y+=3;
        terminal.write(String.format("+||+ %s III +||+", selectedSkill().toString()), 37, y++);
        y++;
        terminal.writeMultiline(TextUtils.wordWrap(selectedSkill().details_3, 66, 0), 41, y++);

        if (skillPoints < 1) {
            terminal.writeCenter(String.format("-- [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 38);
        } else {
            terminal.writeCenter("-- Assign all skill points to continue --", 38);
        }
        terminal.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s / %s]: Select/Deselect Skill --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 40);
        terminal.writeCenter(String.format("-- [%s]: Return to Ability Score selection --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 42);
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
                    return new Zone1Screen(new PlayerBuildDetails(playerClass, playerAbilities, skillset, playerName, playerAncestry));
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

