package RogueLike.Main.Screens;

import RogueLike.Main.*;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

import java.awt.event.KeyEvent;

public class PlayerLevelUpSkillsScreen implements Screen {

    protected Creature player;

    private int points;
    private final Skillset newSkills;
    private int check = 0;

    private Skill selectedSkill() {
        return Skill.values()[check];
    }

    public PlayerLevelUpSkillsScreen(Creature player) {
        this.player = player;
        this.points = player.skillPoints();
        this.newSkills = new Skillset(player.skills());
    }

    @Override
    public void displayOutput(ExtendedAsciiPanel terminal) {
        terminal.clear();
        Screen.generateBorders(terminal);
        terminal.writeCenter("== Increase your Skills ==", 1);
        int y = 3;

        terminal.writeCenter(String.format("-- Points remaining: %d --", points), y);
        y += 3;
        for (Skill skill : Skill.values()) {
            char leftMarker = (skill == selectedSkill()) ? '>' : ' ';
            char rightMarker = (skill == selectedSkill()) ? '<' : ' ';
            terminal.writeCenter(
                    String.format(
                            "%c %s: %s %c",
                            leftMarker,
                            skill,
                            ExtraMaths.toRomanNumerals(newSkills.get(skill).level()),
                            rightMarker),
                    y++);
        }

        y += 5;
        terminal.writeCenterMultiline(TextUtils.wordWrap(selectedSkill().description, 66, 0), y);

        if (points < 1) {
            terminal.writeCenter(
                    String.format(
                            "-- [%s]: Confirm and Continue --",
                            KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)),
                    40);
        }
        terminal.writeCenter(
                String.format(
                        "-- [%s / %s]: Move Selection | [%s / %s]: Increase/Decrease Skill Point"
                                + " Allocation --",
                        KeybindManager.keybindText(KeybindManager.navigateMenuUp),
                        KeybindManager.keybindText(KeybindManager.navigateMenuDown),
                        KeybindManager.keybindText(KeybindManager.navigateMenuLeft),
                        KeybindManager.keybindText(KeybindManager.navigateMenuRight)),
                38);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeybindManager.navigateMenuUp:
                if (check == 0) {
                    check = 13;
                } else {
                    check--;
                }
                return this;

            case KeybindManager.navigateMenuDown:
                if (check == 13) {
                    check = 0;
                } else {
                    check++;
                }
                return this;

            case KeybindManager.navigateMenuRight:
                if (points > 0 && newSkills.get(selectedSkill()).level() < 3) {
                    newSkills.get(selectedSkill()).modifyLevel(1);
                    points--;
                }
                return this;

            case KeybindManager.navigateMenuLeft:
                if (newSkills.get(selectedSkill()).level()
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
