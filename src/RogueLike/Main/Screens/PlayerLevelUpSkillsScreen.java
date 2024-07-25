package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Managers.KeybindManager;

import java.awt.event.KeyEvent;

public class PlayerLevelUpSkillsScreen implements Screen {

    protected Creature player;

    public int points;

    public void modifyPoints(int amount) {
        points += amount;
    }

    public int check = 0;

    public void setCheck(int value) {
        check = value;
    }

    public int playerSimpleWeapons = 0;

    public void modifySimpleWeapons(int amount) {
        playerSimpleWeapons += amount;
    }

    public int playerMartialWeapons = 0;

    public void modifyMartialWeapons(int amount) {
        playerMartialWeapons += amount;
    }

    public int playerArmorTraining = 0;

    public void modifyArmorTraining(int amount) {
        playerArmorTraining += amount;
    }

    public int playerFortitude = 0;

    public void modifyFortitude(int amount) {
        playerFortitude += amount;
    }

    public int playerFinesseWeapons = 0;

    public void modifyFinesseWeapons(int amount) {
        playerFinesseWeapons += amount;
    }

    public int playerRangedWeapons = 0;

    public void modifyRangedWeapons(int amount) {
        playerRangedWeapons += amount;
    }

    public int playerStealth = 0;

    public void modifyStealth(int amount) {
        playerStealth += amount;
    }

    public int playerPerception = 0;

    public void modifyPerception(int amount) {
        playerPerception += amount;
    }

    public int playerEvocation = 0;

    public void modifyEvocation(int amount) {
        playerEvocation += amount;
    }

    public int playerPyromancy = 0;

    public void modifyPyromancy(int amount) {
        playerPyromancy += amount;
    }

    public int playerCryomancy = 0;

    public void modifyCryomancy(int amount) {
        playerCryomancy += amount;
    }

    public int playerElectromancy = 0;

    public void modifyElectromancy(int amount) {
        playerElectromancy += amount;
    }

    public int playerAlchemancy = 0;

    public void modifyAlchemancy(int amount) {
        playerAlchemancy += amount;
    }

    public int playerFerromancy = 0;

    public void modifyFerromancy(int amount) {
        playerFerromancy += amount;
    }

    public char simpleLeft = '>';
    public char simpleRight = '<';
    public char martialLeft = '>';
    public char martialRight = '<';
    public char armorLeft = '>';
    public char armorRight = '<';
    public char fortitudeLeft = '>';
    public char fortitudeRight = '<';
    public char finesseLeft = '>';
    public char finesseRight = '<';
    public char rangedLeft = '>';
    public char rangedRight = '<';
    public char stealthLeft = '>';
    public char stealthRight = '<';
    public char perceptionLeft = '>';
    public char perceptionRight = '<';
    public char evocationLeft = '>';
    public char evocationRight = '<';
    public char pyromancyLeft = '>';
    public char pyromancyRight = '<';
    public char cryomancyLeft = '>';
    public char cryomancyRight = '<';
    public char electromancyLeft = '>';
    public char electromancyRight = '<';
    public char alchemancyLeft = '>';
    public char alchemancyRight = '<';
    public char ferromancyLeft = '>';
    public char ferromancyRight = '<';

    public void updateSimpleMarker(int check) {
        switch (check) {
            case 0:
                simpleLeft = '>';
                simpleRight = '<';
                break;
            default:
                simpleLeft = ' ';
                simpleRight = ' ';
                break;
        }
    }

    public void updateMartialMarker(int check) {
        switch (check) {
            case 1:
                martialLeft = '>';
                martialRight = '<';
                break;
            default:
                martialLeft = ' ';
                martialRight = ' ';
                break;
        }
    }

    public void updateArmorMarker(int check) {
        switch (check) {
            case 2:
                armorLeft = '>';
                armorRight = '<';
                break;
            default:
                armorLeft = ' ';
                armorRight = ' ';
                break;
        }
    }

    public void updateFortitudeMarker(int check) {
        switch (check) {
            case 3:
                fortitudeLeft = '>';
                fortitudeRight = '<';
                break;
            default:
                fortitudeLeft = ' ';
                fortitudeRight = ' ';
                break;
        }
    }

    public void updateFinesseMarker(int check) {
        switch (check) {
            case 4:
                finesseLeft = '>';
                finesseRight = '<';
                break;
            default:
                finesseLeft = ' ';
                finesseRight = ' ';
                break;
        }
    }

    public void updateRangedMarker(int check) {
        switch (check) {
            case 5:
                rangedLeft = '>';
                rangedRight = '<';
                break;
            default:
                rangedLeft = ' ';
                rangedRight = ' ';
                break;
        }
    }

    public void updateStealthMarker(int check) {
        switch (check) {
            case 6:
                stealthLeft = '>';
                stealthRight = '<';
                break;
            default:
                stealthLeft = ' ';
                stealthRight = ' ';
                break;
        }
    }

    public void updatePerceptionMarker(int check) {
        switch (check) {
            case 7:
                perceptionLeft = '>';
                perceptionRight = '<';
                break;
            default:
                perceptionLeft = ' ';
                perceptionRight = ' ';
                break;
        }
    }

    public void updateEvocationMarker(int check) {
        switch (check) {
            case 8:
                evocationLeft = '>';
                evocationRight = '<';
                break;
            default:
                evocationLeft = ' ';
                evocationRight = ' ';
                break;
        }
    }

    public void updatePyromancyMarker(int check) {
        switch (check) {
            case 9:
                pyromancyLeft = '>';
                pyromancyRight = '<';
                break;
            default:
                pyromancyLeft = ' ';
                pyromancyRight = ' ';
                break;
        }
    }

    public void updateCryomancyMarker(int check) {
        switch (check) {
            case 10:
                cryomancyLeft = '>';
                cryomancyRight = '<';
                break;
            default:
                cryomancyLeft = ' ';
                cryomancyRight = ' ';
                break;
        }
    }

    public void updateElectromancyMarker(int check) {
        switch (check) {
            case 11:
                electromancyLeft = '>';
                electromancyRight = '<';
                break;
            default:
                electromancyLeft = ' ';
                electromancyRight = ' ';
                break;
        }
    }

    public void updateAlchemancyMarker(int check) {
        switch (check) {
            case 12:
                alchemancyLeft = '>';
                alchemancyRight = '<';
                break;
            default:
                alchemancyLeft = ' ';
                alchemancyRight = ' ';
                break;
        }
    }

    public void updateFerromancyMarker(int check) {
        switch (check) {
            case 13:
                ferromancyLeft = '>';
                ferromancyRight = '<';
                break;
            default:
                ferromancyLeft = ' ';
                ferromancyRight = ' ';
                break;
        }
    }

    public void changeMarkers(int check) {
        updateSimpleMarker(check);
        updateMartialMarker(check);
        updateArmorMarker(check);
        updateFortitudeMarker(check);
        updateFinesseMarker(check);
        updateRangedMarker(check);
        updateStealthMarker(check);
        updatePerceptionMarker(check);
        updateEvocationMarker(check);
        updatePyromancyMarker(check);
        updateCryomancyMarker(check);
        updateElectromancyMarker(check);
        updateAlchemancyMarker(check);
        updateFerromancyMarker(check);
    }

    public PlayerLevelUpSkillsScreen(Creature player) {
        this.player = player;
        this.points = player.skillPoints();
    }

    @Override
    public void displayOutput(ExtendedAsciiPanel terminal) {
        changeMarkers(check);
        terminal.clear();
        Screen.generateBorders(terminal);
        terminal.writeCenter("== Increase your Skills ==", 1);
        int y = 3;

        terminal.writeCenter(String.format("-- Points remaining: %d --", points), y);
        y += 3;
        terminal.writeCenter(
                String.format(
                        "%c Simple Weapons: %s %c",
                        simpleLeft,
                        ExtraMaths.toRomanNumerals(
                                player.simpleWeaponsLevel() + playerSimpleWeapons),
                        simpleRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Martial Weapons: %s %c",
                        martialLeft,
                        ExtraMaths.toRomanNumerals(
                                player.martialWeaponsLevel() + playerMartialWeapons),
                        martialRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Armor Training: %s %c",
                        armorLeft,
                        ExtraMaths.toRomanNumerals(
                                player.armorTrainingLevel() + playerArmorTraining),
                        armorRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Fortitude: %s %c",
                        fortitudeLeft,
                        ExtraMaths.toRomanNumerals(player.fortitudeLevel() + playerFortitude),
                        fortitudeRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Finesse Weapons: %s %c",
                        finesseLeft,
                        ExtraMaths.toRomanNumerals(
                                player.finesseWeaponsLevel() + playerFinesseWeapons),
                        finesseRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Ranged Weapons: %s %c",
                        rangedLeft,
                        ExtraMaths.toRomanNumerals(
                                player.rangedWeaponsLevel() + playerRangedWeapons),
                        rangedRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Stealth: %s %c",
                        stealthLeft,
                        ExtraMaths.toRomanNumerals(player.stealthLevel() + playerStealth),
                        stealthRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Perception: %s %c",
                        perceptionLeft,
                        ExtraMaths.toRomanNumerals(player.perceptionLevel() + playerPerception),
                        perceptionRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Evocation: %s %c",
                        evocationLeft,
                        ExtraMaths.toRomanNumerals(player.evocationLevel() + playerEvocation),
                        evocationRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Pyromancy: %s %c",
                        pyromancyLeft,
                        ExtraMaths.toRomanNumerals(player.pyromancyLevel() + playerPyromancy),
                        pyromancyRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Cryomancy: %s %c",
                        cryomancyLeft,
                        ExtraMaths.toRomanNumerals(player.cryomancyLevel() + playerCryomancy),
                        cryomancyRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Electromancy: %s %c",
                        electromancyLeft,
                        ExtraMaths.toRomanNumerals(player.electromancyLevel() + playerElectromancy),
                        electromancyRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Alchemancy: %s %c",
                        alchemancyLeft,
                        ExtraMaths.toRomanNumerals(player.alchemancyLevel() + playerAlchemancy),
                        alchemancyRight),
                y++);
        terminal.writeCenter(
                String.format(
                        "%c Ferromancy: %s %c",
                        ferromancyLeft,
                        ExtraMaths.toRomanNumerals(player.ferromancyLevel() + playerFerromancy),
                        ferromancyRight),
                y++);

        if (check == 0) {
            terminal.writeCenter(
                    "Simple Weapons represents your skill with simple weapons", y += 5);
            terminal.writeCenter(
                    "such as clubs and handaxes. A higher Simple Weapons skill", y += 1);
            terminal.writeCenter("allows you to use more powerful simple weapons,", y += 1);
            terminal.writeCenter("and improves your accuracy with weapons of this type.", y += 1);
        } else if (check == 1) {
            terminal.writeCenter(
                    "Martial Weapons represents your skill with martial weapons", y += 5);
            terminal.writeCenter(
                    "such as longswords and battleaxes. A higher Martial Weapons skill", y += 1);
            terminal.writeCenter("allows you to use more powerful martial weapons,", y += 1);
            terminal.writeCenter("and improves your accuracy with weapons of this type.", y += 1);
        } else if (check == 2) {
            terminal.writeCenter(
                    "Armor Training represents your ability to move and fight", y += 5);
            terminal.writeCenter("whilst wearing armor. A higher Armor Training skill", y += 1);
            terminal.writeCenter(
                    "allows you to use heavier armor and shields, and provides", y += 1);
            terminal.writeCenter("further bonuses to your Armor Class at higher levels.", y += 1);
        } else if (check == 3) {
            terminal.writeCenter(
                    "Fortitude is a measure of your stamina and constitution.", y += 5);
            terminal.writeCenter(
                    "A higher Fortitude skill increases the length of time you", y += 1);
            terminal.writeCenter("can go without eating, and lessens the negative effects", y += 1);
            terminal.writeCenter("incurred by eating certain food items.", y += 1);
        } else if (check == 4) {
            terminal.writeCenter(
                    "Finesse Weapons represents your skill with finesse weapons", y += 5);
            terminal.writeCenter(
                    "such as daggers and rapiers. A higher Finesse Weapons skill", y += 1);
            terminal.writeCenter("allows you to use more powerful finesse weapons,", y += 1);
            terminal.writeCenter("and improves your accuracy with weapons of this type.", y += 1);
        } else if (check == 5) {
            terminal.writeCenter(
                    "Ranged Weapons represents your skill with ranged weapons", y += 5);
            terminal.writeCenter(
                    "such as bows and crossbows. A higher Ranged Weapons skill", y += 1);
            terminal.writeCenter("allows you to use more powerful ranged weapons,", y += 1);
            terminal.writeCenter("and improves your accuracy with weapons of this type.", y += 1);
        } else if (check == 6) {
            terminal.writeCenter("Stealth represents your ability to move unnoticed.", y += 5);
            terminal.writeCenter(
                    "A higher Stealth skill allows you to sneak past sleeping", y += 1);
            terminal.writeCenter("and unalerted foes more reliably, giving you the upper", y += 1);
            terminal.writeCenter("hand in combat and making it easier to avoid a fight.", y += 1);
        } else if (check == 7) {
            terminal.writeCenter(
                    "Perception is a measure of your awareness and eye for detail.", y += 5);
            terminal.writeCenter(
                    "A higher Perception skill allows you to spot traps and other", y += 1);
            terminal.writeCenter("hidden things with greater ease, as well as improving", y += 1);
            terminal.writeCenter("your chances of identifying unknown items.", y += 1);
        } else if (check == 8) {
            terminal.writeCenter("Evocation is a school of magic focused mainly on the", y += 5);
            terminal.writeCenter("manipulation of raw magic. A higher Evocation skill", y += 1);
            terminal.writeCenter("allows you to use more powerful evocation wands,", y += 1);
            terminal.writeCenter("and improves the effectiveness of your evocation wands.", y += 1);
        } else if (check == 9) {
            terminal.writeCenter("Pyromancy is a school of magic focused mainly on the", y += 5);
            terminal.writeCenter("manipulation of fire and heat. A higher Pyromancy skill", y += 1);
            terminal.writeCenter("allows you to use more powerful pyromancy wands,", y += 1);
            terminal.writeCenter("and improves the effectiveness of your pyromancy wands.", y += 1);
        } else if (check == 10) {
            terminal.writeCenter("Cryomancy is a school of magic focused mainly on the", y += 5);
            terminal.writeCenter("manipulation of water and ice. A higher Cryomancy skill", y += 1);
            terminal.writeCenter("allows you to use more powerful cryomancy wands,", y += 1);
            terminal.writeCenter("and improves the effectiveness of your cryomancy wands.", y += 1);
        } else if (check == 11) {
            terminal.writeCenter("Electromancy is a school of magic focused mainly on the", y += 5);
            terminal.writeCenter(
                    "manipulation of electrical energy. A higher Electromancy skill", y += 1);
            terminal.writeCenter("allows you to use more powerful electromancy wands,", y += 1);
            terminal.writeCenter(
                    "and improves the effectiveness of your electromancy wands.", y += 1);
        } else if (check == 12) {
            terminal.writeCenter("Alchemancy is a school of magic focused mainly on the", y += 5);
            terminal.writeCenter(
                    "manipulation of chemical processes. A higher Alchemancy skill", y += 1);
            terminal.writeCenter("allows you to use more powerful alchemancy wands,", y += 1);
            terminal.writeCenter(
                    "and improves the effectiveness of your alchemancy wands.", y += 1);
        } else if (check == 13) {
            terminal.writeCenter("Ferromancy is a school of magic focused mainly on the", y += 5);
            terminal.writeCenter(
                    "manipulation of stone and metals. A higher Ferromancy skill", y += 1);
            terminal.writeCenter("allows you to use more powerful ferromancy wands,", y += 1);
            terminal.writeCenter(
                    "and improves the effectiveness of your ferromancy wands.", y += 1);
        }

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
                if (check == 0) {
                    if (points > 0) {
                        modifySimpleWeapons(1);
                        modifyPoints(-1);
                    }
                } else if (check == 1) {
                    if (points > 0) {
                        modifyMartialWeapons(1);
                        modifyPoints(-1);
                    }
                } else if (check == 2) {
                    if (points > 0) {
                        modifyArmorTraining(1);
                        modifyPoints(-1);
                    }
                } else if (check == 3) {
                    if (points > 0) {
                        modifyFortitude(1);
                        modifyPoints(-1);
                    }
                } else if (check == 4) {
                    if (points > 0) {
                        modifyFinesseWeapons(1);
                        modifyPoints(-1);
                    }
                } else if (check == 5) {
                    if (points > 0) {
                        modifyRangedWeapons(1);
                        modifyPoints(-1);
                    }
                } else if (check == 6) {
                    if (points > 0) {
                        modifyStealth(1);
                        modifyPoints(-1);
                    }
                } else if (check == 7) {
                    if (points > 0) {
                        modifyPerception(1);
                        modifyPoints(-1);
                    }
                } else if (check == 8) {
                    if (points > 0) {
                        modifyEvocation(1);
                        modifyPoints(-1);
                    }
                } else if (check == 9) {
                    if (points > 0) {
                        modifyPyromancy(1);
                        modifyPoints(-1);
                    }
                } else if (check == 10) {
                    if (points > 0) {
                        modifyCryomancy(1);
                        modifyPoints(-1);
                    }
                } else if (check == 11) {
                    if (points > 0) {
                        modifyElectromancy(1);
                        modifyPoints(-1);
                    }
                } else if (check == 12) {
                    if (points > 0) {
                        modifyAlchemancy(1);
                        modifyPoints(-1);
                    }
                } else if (check == 13) {
                    if (points > 0) {
                        modifyFerromancy(1);
                        modifyPoints(-1);
                    }
                }
                return this;

            case KeybindManager.navigateMenuLeft:
                if (check == 0) {
                    if (playerSimpleWeapons > 0) {
                        modifySimpleWeapons(-1);
                        modifyPoints(1);
                    }
                } else if (check == 1) {
                    if (playerMartialWeapons > 0) {
                        modifyMartialWeapons(-1);
                        modifyPoints(1);
                    }
                } else if (check == 2) {
                    if (playerArmorTraining > 0) {
                        modifyArmorTraining(-1);
                        modifyPoints(1);
                    }
                } else if (check == 3) {
                    if (playerFortitude > 0) {
                        modifyFortitude(-1);
                        modifyPoints(1);
                    }
                } else if (check == 4) {
                    if (playerFinesseWeapons > 0) {
                        modifyFinesseWeapons(-1);
                        modifyPoints(1);
                    }
                } else if (check == 5) {
                    if (playerRangedWeapons > 0) {
                        modifyRangedWeapons(-1);
                        modifyPoints(1);
                    }
                } else if (check == 6) {
                    if (playerStealth > 0) {
                        modifyStealth(-1);
                        modifyPoints(1);
                    }
                } else if (check == 7) {
                    if (playerPerception > 0) {
                        modifyPerception(-1);
                        modifyPoints(1);
                    }
                } else if (check == 8) {
                    if (playerEvocation > 0) {
                        modifyEvocation(-1);
                        modifyPoints(1);
                    }
                } else if (check == 9) {
                    if (playerPyromancy > 0) {
                        modifyPyromancy(-1);
                        modifyPoints(1);
                    }
                } else if (check == 10) {
                    if (playerCryomancy > 0) {
                        modifyCryomancy(-1);
                        modifyPoints(1);
                    }
                } else if (check == 11) {
                    if (playerElectromancy > 0) {
                        modifyElectromancy(-1);
                        modifyPoints(1);
                    }
                } else if (check == 12) {
                    if (playerAlchemancy > 0) {
                        modifyAlchemancy(-1);
                        modifyPoints(1);
                    }
                } else if (check == 13) {
                    if (playerFerromancy > 0) {
                        modifyFerromancy(-1);
                        modifyPoints(1);
                    }
                }

                return this;

            case KeybindManager.navigateMenuConfirm:
                if (points < 1) {
                    player.skills().simpleWeapons.setLevel(playerSimpleWeapons);
                    player.skills().martialWeapons.setLevel(playerMartialWeapons);
                    player.skills().armorTraining.setLevel(playerArmorTraining);
                    player.skills().fortitude.setLevel(playerFortitude);
                    player.skills().finesseWeapons.setLevel(playerFinesseWeapons);
                    player.skills().rangedWeapons.setLevel(playerRangedWeapons);
                    player.skills().stealth.setLevel(playerStealth);
                    player.skills().perception.setLevel(playerPerception);
                    player.skills().evocation.setLevel(playerEvocation);
                    player.skills().pyromancy.setLevel(playerPyromancy);
                    player.skills().cryomancy.setLevel(playerCryomancy);
                    player.skills().electromancy.setLevel(playerElectromancy);
                    player.skills().alchemancy.setLevel(playerAlchemancy);
                    player.setSkillPoints(0);
                    return null;
                } else {
                    return this;
                }
        }

        return this;
    }
}
