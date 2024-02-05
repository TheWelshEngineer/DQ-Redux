package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Skill;
import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class ChooseSkillScreen implements Screen{
	
	public String playerClass;
	public String playerName;
	public String playerAncestry;
	public List<Integer> playerAbilities;
	public Skill[] playerSkills = Skill.defaultSkillArray();
	
	public int skillPoints = 2;
	public void modifyPoints(int amount) {
		skillPoints += amount;
	}
	 
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public boolean playerSimpleWeapons = false;
	public boolean playerMartialWeapons = false;
	public boolean playerArmorTraining = false;
	public boolean playerFortitude = false;
	public boolean playerFinesseWeapons = false;
	public boolean playerRangedWeapons = false;
	public boolean playerStealth = false;
	public boolean playerPerception = false;
	public boolean playerEvocation = false;
	public boolean playerPyromancy = false;
	public boolean playerCryomancy = false;
	public boolean playerElectromancy = false;
	public boolean playerAlchemancy = false;
	
	public ChooseSkillScreen(String playerClass, List<Integer> playerAbilities, String playerName, String playerAncestry) {
		this.playerClass = playerClass;
		this.playerAbilities = playerAbilities;
		this.playerName = playerName;
		this.playerAncestry = playerAncestry;
		if(this.playerAncestry == "Human") {
			this.skillPoints = 3;
		}
		
		if(playerClass == "Warrior") {
			playerMartialWeapons = true;
			playerSkills[1].modifyLevel(1, false);
			playerArmorTraining = true;
			playerSkills[2].modifyLevel(1, false);
			martialUp = ' ';
			armorUp = ' ';
		}else if(playerClass == "Rogue") {
			playerFinesseWeapons = true;
			playerSkills[4].modifyLevel(1, false);
			playerStealth = true;
			playerSkills[6].modifyLevel(1, false);
			finesseUp = ' ';
			stealthUp = ' ';
		}else if(playerClass == "Mage") {
			playerEvocation = true;
			playerSkills[8].modifyLevel(1, false);
			playerPerception = true;
			playerSkills[7].modifyLevel(1, false);
			evocationUp = ' ';
			perceptionUp = ' ';
		}
		updateMarkers(check);
		

		
	}
	
	public char borderVertical = 186;
	public char borderHorizontal = 205;
	public char borderCorner = 206;
	public char borderCornerNW = 201;
	public char borderCornerNE = 187;
	public char borderCornerSW = 200;
	public char borderCornerSE = 188;
	
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
	
	public void updateSimpleMarker(int check) {
		switch(check) {
		case 0: simpleLeft = '>'; simpleRight = '<'; break;
		default: simpleLeft = ' '; simpleRight = ' '; break;
		}
	}
	public void updateMartialMarker(int check) {
		switch(check) {
		case 1: martialLeft = '>'; martialRight = '<'; break;
		default: martialLeft = ' '; martialRight = ' '; break;
		}
	}
	public void updateArmorMarker(int check) {
		switch(check) {
		case 2: armorLeft = '>'; armorRight = '<'; break;
		default: armorLeft = ' '; armorRight = ' '; break;
		}
	}
	public void updateFortitudeMarker(int check) {
		switch(check) {
		case 3: fortitudeLeft = '>'; fortitudeRight = '<'; break;
		default: fortitudeLeft = ' '; fortitudeRight = ' '; break;
		}
	}
	public void updateFinesseMarker(int check) {
		switch(check) {
		case 4: finesseLeft = '>'; finesseRight = '<'; break;
		default: finesseLeft = ' '; finesseRight = ' '; break;
		}
	}
	public void updateRangedMarker(int check) {
		switch(check) {
		case 5: rangedLeft = '>'; rangedRight = '<'; break;
		default: rangedLeft = ' '; rangedRight = ' '; break;
		}
	}
	public void updateStealthMarker(int check) {
		switch(check) {
		case 6: stealthLeft = '>'; stealthRight = '<'; break;
		default: stealthLeft = ' '; stealthRight = ' '; break;
		}
	}
	public void updatePerceptionMarker(int check) {
		switch(check) {
		case 7: perceptionLeft = '>'; perceptionRight = '<'; break;
		default: perceptionLeft = ' '; perceptionRight = ' '; break;
		}
	}
	public void updateEvocationMarker(int check) {
		switch(check) {
		case 8: evocationLeft = '>'; evocationRight = '<'; break;
		default: evocationLeft = ' '; evocationRight = ' '; break;
		}
	}
	public void updatePyromancyMarker(int check) {
		switch(check) {
		case 9: pyromancyLeft = '>'; pyromancyRight = '<'; break;
		default: pyromancyLeft = ' '; pyromancyRight = ' '; break;
		}
	}
	public void updateCryomancyMarker(int check) {
		switch(check) {
		case 10: cryomancyLeft = '>'; cryomancyRight = '<'; break;
		default: cryomancyLeft = ' '; cryomancyRight = ' '; break;
		}
	}
	public void updateElectromancyMarker(int check) {
		switch(check) {
		case 11: electromancyLeft = '>'; electromancyRight = '<'; break;
		default: electromancyLeft = ' '; electromancyRight = ' '; break;
		}
	}
	public void updateAlchemancyMarker(int check) {
		switch(check) {
		case 12: alchemancyLeft = '>'; alchemancyRight = '<'; break;
		default: alchemancyLeft = ' '; alchemancyRight = ' '; break;
		}
	}




	
	public char simpleUp = '+';
	public char simpleDown = ' ';
	public char martialUp = '+';
	public char martialDown = ' ';
	public char armorUp = '+';
	public char armorDown = ' ';
	public char fortitudeUp = '+';
	public char fortitudeDown = ' ';
	public char finesseUp = '+';
	public char finesseDown = ' ';
	public char rangedUp = '+';
	public char rangedDown = ' ';
	public char stealthUp = '+';
	public char stealthDown = ' ';
	public char perceptionUp = '+';
	public char perceptionDown = ' ';
	public char evocationUp = '+';
	public char evocationDown = ' ';
	public char pyromancyUp = '+';
	public char pyromancyDown = ' ';
	public char cryomancyUp = '+';
	public char cryomancyDown = ' ';
	public char electromancyUp = '+';
	public char electromancyDown = ' ';
	public char alchemancyUp = '+';
	public char alchemancyDown = ' ';
	
	public void updateMarkers(int check) {
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
		switch(check) {
		case 0: if(playerSimpleWeapons == false && skillPoints > 0) {
					simpleUp = '+';
					simpleDown = ' ';
				}else if(playerSimpleWeapons == true) {
					simpleUp = ' ';
					simpleDown = '-';
				}else if(skillPoints == 0) {
					simpleUp = ' ';
					simpleDown = ' ';
				}else {
					simpleUp = '+';
					simpleDown = '-';
				}break;
		case 1: if(playerMartialWeapons == false && skillPoints > 0 && playerClass != "Warrior") {
					martialUp = '+';
					martialDown = ' ';
				}else if(playerMartialWeapons == true && playerClass != "Warrior") {
					martialUp = ' ';
					martialDown = '-';
				}else if(skillPoints == 0 || playerClass == "Warrior") {
					martialUp = ' ';
					martialDown = ' ';
				}else {
					martialUp = '+';
					martialDown = '-';
				}break;
		case 2: if(playerArmorTraining == false && skillPoints > 0 && playerClass != "Warrior") {
					armorUp = '+';
					armorDown = ' ';
				}else if(playerArmorTraining == true && playerClass != "Warrior") {
					armorUp = ' ';
					armorDown = '-';
				}else if(skillPoints == 0 || playerClass == "Warrior") {
					armorUp = ' ';
					armorDown = ' ';
				}else {
					armorUp = '+';
					armorDown = '-';
				}break;
		case 3: if(playerFortitude == false && skillPoints > 0) {
					fortitudeUp = '+';
					fortitudeDown = ' ';
				}else if(playerFortitude == true) {
					fortitudeUp = ' ';
					fortitudeDown = '-';
				}else if(skillPoints == 0) {
					fortitudeUp = ' ';
					fortitudeDown = ' ';
				}else {
					fortitudeUp = '+';
					fortitudeDown = '-';
				}break;
		case 4: if(playerFinesseWeapons == false && skillPoints > 0 && playerClass != "Rogue") {
					finesseUp = '+';
					finesseDown = ' ';
				}else if(playerFinesseWeapons == true && playerClass != "Rogue") {
					finesseUp = ' ';
					finesseDown = '-';
				}else if(skillPoints == 0 || playerClass == "Rogue") {
					finesseUp = ' ';
					finesseDown = ' ';
				}else {
					finesseUp = '+';
					finesseDown = '-';
				}break;
		case 5: if(playerRangedWeapons == false && skillPoints > 0) {
					rangedUp = '+';
					rangedDown = ' ';
				}else if(playerRangedWeapons == true) {
					rangedUp = ' ';
					rangedDown = '-';
				}else if(skillPoints == 0) {
					rangedUp = ' ';
					rangedDown = ' ';
				}else {
					rangedUp = '+';
					rangedDown = '-';
				}break;
		case 6: if(playerStealth == false && skillPoints > 0 && playerClass != "Rogue") {
					stealthUp = '+';
					stealthDown = ' ';
				}else if(playerStealth == true && playerClass != "Rogue") {
					stealthUp = ' ';
					stealthDown = '-';
				}else if(skillPoints == 0 || playerClass == "Rogue") {
					stealthUp = ' ';
					stealthDown = ' ';
				}else {
					stealthUp = '+';
					stealthDown = '-';
				}break;
		case 7: if(playerPerception == false && skillPoints > 0 && playerClass != "Mage") {
					perceptionUp = '+';
					perceptionDown = ' ';
				}else if(playerPerception == true && playerClass != "Mage") {
					perceptionUp = ' ';
					perceptionDown = '-';
				}else if(skillPoints == 0 || playerClass == "Mage") {
					perceptionUp = ' ';
					perceptionDown = ' ';
				}else {
					perceptionUp = '+';
					perceptionDown = '-';
				}break;
		case 8: if(playerEvocation == false && skillPoints > 0 && playerClass != "Mage") {
					evocationUp = '+';
					evocationDown = ' ';
				}else if(playerEvocation == true && playerClass != "Mage") {
					evocationUp = ' ';
					evocationDown = '-';
				}else if(skillPoints == 0 || playerClass == "Mage") {
					evocationUp = ' ';
					evocationDown = ' ';
				}else {
					evocationUp = '+';
					evocationDown = '-';
				}break;
		case 9: if(playerPyromancy == false && skillPoints > 0) {
					pyromancyUp = '+';
					pyromancyDown = ' ';
				}else if(playerPyromancy == true) {
					pyromancyUp = ' ';
					pyromancyDown = '-';
				}else if(skillPoints == 0) {
					pyromancyUp = ' ';
					pyromancyDown = ' ';
				}else {
					pyromancyUp = '+';
					pyromancyDown = '-';
				}break;
		case 10: if(playerCryomancy == false && skillPoints > 0) {
					cryomancyUp = '+';
					cryomancyDown = ' ';
				}else if(playerCryomancy == true) {
					cryomancyUp = ' ';
					cryomancyDown = '-';
				}else if(skillPoints == 0) {
					cryomancyUp = ' ';
					cryomancyDown = ' ';
				}else {
					cryomancyUp = '+';
					cryomancyDown = '-';
				}break;
		case 11: if(playerElectromancy == false && skillPoints > 0) {
					electromancyUp = '+';
					electromancyDown = ' ';
				}else if(playerElectromancy == true) {
					electromancyUp = ' ';
					electromancyDown = '-';
				}else if(skillPoints == 0) {
					electromancyUp = ' ';
					electromancyDown = ' ';
				}else {
					electromancyUp = '+';
					electromancyDown = '-';
				}break;
		case 12: if(playerAlchemancy == false && skillPoints > 0) {
					alchemancyUp = '+';
					alchemancyDown = ' ';
				}else if(playerAlchemancy == true) {
					alchemancyUp = ' ';
					alchemancyDown = '-';
				}else if(skillPoints == 0) {
					alchemancyUp = ' ';
					alchemancyDown = ' ';
				}else {
					alchemancyUp = '+';
					alchemancyDown = '-';
				}break;
		}
	}
	
	private char booleanToChar(boolean value) {
		if(value) {
			return (char)251;
		}
		return 'X';
	}

	

	public void displayOutput(AsciiPanel terminal) {
		updateMarkers(check);
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Select your starting Skills ==", 1);	
		int y = 3;
		
		terminal.writeCenter(String.format("-- Choices remaining: %d --", skillPoints), y++);
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 4, y++);
		terminal.write(String.format("%c %c Simple Weapons I  ( %c ) %c %c", borderVertical, simpleLeft, booleanToChar(playerSimpleWeapons), simpleRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Martial Weapons I ( %c ) %c %c", borderVertical, martialLeft, booleanToChar(playerMartialWeapons), martialRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Armor Training I  ( %c ) %c %c", borderVertical, armorLeft, booleanToChar(playerArmorTraining), armorRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Fortitude I       ( %c ) %c %c", borderVertical, fortitudeLeft, booleanToChar(playerFortitude), fortitudeRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Finesse Weapons I ( %c ) %c %c", borderVertical, finesseLeft, booleanToChar(playerFinesseWeapons), finesseRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Ranged Weapons I  ( %c ) %c %c", borderVertical, rangedLeft, booleanToChar(playerRangedWeapons), rangedRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Stealth I         ( %c ) %c %c", borderVertical, stealthLeft, booleanToChar(playerStealth), stealthRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Perception I      ( %c ) %c %c", borderVertical, perceptionLeft, booleanToChar(playerPerception), perceptionRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Evocation I       ( %c ) %c %c", borderVertical, evocationLeft, booleanToChar(playerEvocation), evocationRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Pyromancy I       ( %c ) %c %c", borderVertical, pyromancyLeft, booleanToChar(playerPyromancy), pyromancyRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Cryomancy I       ( %c ) %c %c", borderVertical, cryomancyLeft, booleanToChar(playerCryomancy), cryomancyRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Electromancy I    ( %c ) %c %c", borderVertical, electromancyLeft, booleanToChar(playerElectromancy), electromancyRight, borderVertical), 4, y++);
		terminal.write(String.format("%c %c Alchemancy I      ( %c ) %c %c", borderVertical, alchemancyLeft, booleanToChar(playerAlchemancy), alchemancyRight, borderVertical), 4, y++);
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 4, y++);
		
		if(check == 0) {
			terminal.writeCenter("Simple Weapons represents your skill with simple weapons", y+=5);
			terminal.writeCenter("such as clubs and handaxes. A higher Simple Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful simple weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 1) {
			terminal.writeCenter("Martial Weapons represents your skill with martial weapons", y+=5);
			terminal.writeCenter("such as longswords and battleaxes. A higher Martial Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful martial weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 2) {
			terminal.writeCenter("Armor Training represents your ability to move and fight", y+=5);
			terminal.writeCenter("whilst wearing armor. A higher Armor Training skill", y+=1);
			terminal.writeCenter("allows you to use heavier armor and shields, and provides", y+=1);
			terminal.writeCenter("further bonuses to your Armor Class at higher levels.", y+=1);
		}else if(check == 3) {
			terminal.writeCenter("Fortitude is a measure of your stamina and constitution.", y+=5);
			terminal.writeCenter("A higher Fortitude skill increases the length of time you", y+=1);
			terminal.writeCenter("can go without eating, and lessens the negative effects", y+=1);
			terminal.writeCenter("incurred by eating certain food items.", y+=1);
		}else if(check == 4) {
			terminal.writeCenter("Finesse Weapons represents your skill with finesse weapons", y+=5);
			terminal.writeCenter("such as daggers and rapiers. A higher Finesse Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful finesse weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 5) {
			terminal.writeCenter("Ranged Weapons represents your skill with ranged weapons", y+=5);
			terminal.writeCenter("such as bows and crossbows. A higher Ranged Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful ranged weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 6) {
			terminal.writeCenter("Stealth represents your ability to move unnoticed.", y+=5);
			terminal.writeCenter("A higher Stealth skill allows you to sneak past sleeping", y+=1);
			terminal.writeCenter("and unalerted foes more reliably, giving you the upper", y+=1);
			terminal.writeCenter("hand in combat and making it easier to avoid a fight.", y+=1);
		}else if(check == 7) {
			terminal.writeCenter("Perception is a measure of your awareness and eye for detail.", y+=5);
			terminal.writeCenter("A higher Perception skill allows you to spot traps and other", y+=1);
			terminal.writeCenter("hidden things with greater ease, as well as improving", y+=1);
			terminal.writeCenter("your chances of identifying unknown items.", y+=1);
		}else if(check == 8) {
			terminal.writeCenter("Evocation is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of raw magic. A higher Evocation skill", y+=1);
			terminal.writeCenter("allows you to use more powerful evocation wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your evocation wands.", y+=1);
		}else if(check == 9) {
			terminal.writeCenter("Pyromancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of fire and heat. A higher Pyromancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful pyromancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your pyromancy wands.", y+=1);
		}else if(check == 10) {
			terminal.writeCenter("Cryomancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of water and ice. A higher Cryomancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful cryomancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your cryomancy wands.", y+=1);
		}else if(check == 11) {
			terminal.writeCenter("Electromancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of electrical energy. A higher Electromancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful electromancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your electromancy wands.", y+=1);
		}else if(check == 12) {
			terminal.writeCenter("Alchemancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of chemical processes. A higher Alchemancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful alchemancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your alchemancy wands.", y+=1);
		}
		
		
		if(skillPoints < 1) {
			terminal.writeCenter(String.format("-- [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 38);
		}
		terminal.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s / %s]: Select/Deselect Skill --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 40);
		terminal.writeCenter(String.format("-- [%s]: Return to Ability Score selection --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 42);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 12;
			}else{
				check--;
			}
			updateMarkers(check);
			return this;
			
		case KeybindManager.navigateMenuDown:
			if(check == 12) {
				check = 0;
			}else{
				check++;
			}
			updateMarkers(check);
			return this;
		
		case KeybindManager.navigateMenuRight:
			if(check == 0) {
				if(skillPoints > 0 && !playerSimpleWeapons) {
					playerSkills[0].modifyLevel(1, false);
					playerSimpleWeapons = true;
					modifyPoints(-1); 
				}
			}else if(check == 1) {
				if(skillPoints > 0 && playerClass != "Warrior" && !playerMartialWeapons) {
					playerSkills[1].modifyLevel(1, false);
					playerMartialWeapons = true;
					modifyPoints(-1); 
				}
			}else if(check == 2) {
				if(skillPoints > 0 && playerClass != "Warrior" && !playerArmorTraining) {
					playerSkills[2].modifyLevel(1, false);
					playerArmorTraining = true;
					modifyPoints(-1); //TODO
				}
			}else if(check == 3) {
				if(skillPoints > 0 && !playerFortitude) {
					playerSkills[3].modifyLevel(1, false);
					playerFortitude = true;
					modifyPoints(-1);
				}
			}else if(check == 4) {
				if(skillPoints > 0 && playerClass != "Rogue" && !playerFinesseWeapons) {
					playerSkills[4].modifyLevel(1, false);
					playerFinesseWeapons = true;
					modifyPoints(-1);
				}
			}else if(check == 5) {
				if(skillPoints > 0 && !playerRangedWeapons) {
					playerSkills[5].modifyLevel(1, false);
					playerRangedWeapons = true;
					modifyPoints(-1);
				} 
			}else if(check == 6) {
				if(skillPoints > 0 && playerClass != "Rogue" && !playerStealth) {
					playerSkills[6].modifyLevel(1, false);
					playerStealth = true;
					modifyPoints(-1);
				}
			}else if(check == 7) {
				if(skillPoints > 0 && playerClass != "Mage" && !playerPerception) {
					playerSkills[7].modifyLevel(1, false);
					playerPerception = true;
					modifyPoints(-1);
				} 
			}else if(check == 8) {
				if(skillPoints > 0 && playerClass != "Mage" && !playerEvocation) {
					playerSkills[8].modifyLevel(1, false);
					playerEvocation = true;
					modifyPoints(-1);
				}
			}else if(check == 9) {
				if(skillPoints > 0 && !playerPyromancy) {
					playerSkills[9].modifyLevel(1, false);
					playerPyromancy = true;
					modifyPoints(-1);
				}
			}else if(check == 10) {
				if(skillPoints > 0 && !playerCryomancy) {
					playerSkills[10].modifyLevel(1, false);
					playerCryomancy = true;
					modifyPoints(-1);
				}
			}else if(check == 11) {
				if(skillPoints > 0 && !playerElectromancy) {
					playerSkills[11].modifyLevel(1, false);
					playerElectromancy = true;
					modifyPoints(-1);
				}
			}else if(check == 12) {
				if(skillPoints > 0 && !playerAlchemancy) {
					playerSkills[12].modifyLevel(1, false);
					playerAlchemancy = true;
					modifyPoints(-1);
				}
			}
			updateMarkers(check);
			return this;
			
		case KeybindManager.navigateMenuLeft:
			if(check == 0) {
				if(playerSimpleWeapons) {
					playerSimpleWeapons = false;
					playerSkills[0].modifyLevel(1, true);
					modifyPoints(1); 
				}
			}else if(check == 1) {
				if(playerMartialWeapons && playerClass != "Warrior") {
					playerMartialWeapons = false;
					playerSkills[1].modifyLevel(1, true);
					modifyPoints(1); //TODO
				}
			}else if(check == 2) {
				if(playerArmorTraining && playerClass != "Warrior") {
					playerArmorTraining = false;
					playerSkills[2].modifyLevel(1, true);
					modifyPoints(1); //TODO
				}
			}else if(check == 3) {
				if(playerFortitude) {
					playerFortitude = false;
					playerSkills[3].modifyLevel(1, true);
					modifyPoints(1); 
				}
			}else if(check == 4) {
				if(playerFinesseWeapons && playerClass != "Rogue") {
					playerFinesseWeapons = false;
					playerSkills[4].modifyLevel(1, true);
					modifyPoints(1);
				} 
			}else if(check == 5) {
				if(playerRangedWeapons) {
					playerRangedWeapons = false;
					playerSkills[5].modifyLevel(1, true);
					modifyPoints(1); 
				} 
			}else if(check == 6) {
				if(playerStealth && playerClass != "Rogue") {
					playerStealth = false;
					playerSkills[6].modifyLevel(1, true);
					modifyPoints(1);
				} 
			}else if(check == 7) {
				if(playerPerception && playerClass != "Mage") {
					playerPerception = false;
					playerSkills[7].modifyLevel(1, true);
					modifyPoints(1); 
				}  
			}else if(check == 8) {
				if(playerEvocation && playerClass != "Mage") {
					playerEvocation = false;
					playerSkills[8].modifyLevel(1, true);
					modifyPoints(1); 
				}
			}else if(check == 9) {
				if(playerPyromancy) {
					playerPyromancy = false;
					playerSkills[9].modifyLevel(1, true);
					modifyPoints(1); 
				}
			}else if(check == 10) {
				if(playerCryomancy) {
					playerCryomancy = false;
					playerSkills[10].modifyLevel(1, true);
					modifyPoints(1); 
				}
			}else if(check == 11) {
				if(playerElectromancy) {
					playerElectromancy = false;
					playerSkills[11].modifyLevel(1, true);
					modifyPoints(1); 
				}
			}else if(check == 12) {
				if(playerAlchemancy) {
					playerAlchemancy = false;
					playerSkills[12].modifyLevel(1, true);
					modifyPoints(1); 
				}
			}
			updateMarkers(check);
			return this;
		
			
		case KeybindManager.navigateMenuConfirm: 
			if(skillPoints < 1) {
				return new Zone1Screen(playerClass, playerAbilities, playerSkills, playerName, playerAncestry); 
			}else {
				return this;
			}
			
		case KeybindManager.navigateMenuBack: return new ChooseAbilityScreen(playerClass, playerAncestry);
		
		}
		
		return this;
	}
	

}

