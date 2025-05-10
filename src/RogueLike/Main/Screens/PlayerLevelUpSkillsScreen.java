package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Skill;
import RogueLike.Main.Skillset;
import RogueLike.Main.TextUtils;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

public class PlayerLevelUpSkillsScreen implements Screen, Serializable{

	protected Creature player;

	private int points ;
	private final Skillset newSkills;
	private int check =  0;
	private Skill selectedSkill() {
		return Skill.values()[check];
	}

	public PlayerLevelUpSkillsScreen(Creature player) {
		this.player = player;
		this.points = player.skillPoints();
		this.newSkills = new Skillset(player.skills());
    }

	@Override
	public void displayOutput() {

		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		ExtendedAsciiPanel.writeCenter("== Increase your Skills ==", 1);
		int y = 3;

		ExtendedAsciiPanel.writeCenter(String.format("-- Points remaining: %d --", points), y);
		y+=3;
		for (Skill skill : Skill.values()) {
            char leftMarker = (skill == selectedSkill()) ? '>' : ' ';
            char rightMarker = (skill == selectedSkill()) ? '<' : ' ';
		ExtendedAsciiPanel.writeCenter(String.format("%c %s: %s %c", leftMarker, skill, ExtraMaths.toRomanNumerals(newSkills.get(skill).level()),
			rightMarker),
			 y++);
		}
			 y+=5;
			ExtendedAsciiPanel.writeCenterMultiline(TextUtils.wordWrap(selectedSkill().description, 66, 0), y);


		if(points < 1) {
			ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 40);
		}
		ExtendedAsciiPanel.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s / %s]: Increase/Decrease Skill Point Allocation --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 38);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 13;
			}else{
				check--;
			}
			return this;

		case KeybindManager.navigateMenuDown:
			if(check == 13) {
				check = 0;
			}else{
				check++;
			}
			return this;

		case KeybindManager.navigateMenuRight:
			if(points > 0 && newSkills.get(selectedSkill()).level() < 3) {
				newSkills.get(selectedSkill()).modifyLevel(1);
					points--;
			}
			return this;

		case KeybindManager.navigateMenuLeft:
			if(newSkills.get(selectedSkill()).level()
				> player.skills().get(selectedSkill()).level()) {
					newSkills.get(selectedSkill()).modifyLevel(-1);
				points++;
			}


			return this;


		case KeybindManager.navigateMenuConfirm:
            if (points < 1) {
                player.setSkills(newSkills);
                player.setSkillPoints(0);
                return null;
            } else {
                return this;
            }

		}

		return this;
	}
}

