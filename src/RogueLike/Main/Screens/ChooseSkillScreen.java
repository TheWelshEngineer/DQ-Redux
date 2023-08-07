/*package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;

public class ChooseSkillScreen implements Screen{
	
	public String playerClass;
	public String playerName;
	public String playerSpecies;
	public List<Integer> playerAbilities;
	public List<Integer> playerSkills = new ArrayList<Integer>();
	
	public int skillPoints = 2;
	public void modifyPoints(int amount) {
		skillPoints += amount;
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
	public int playerFinesseWeapons = 0;
	public void modifyFinesseWeapons(int amount) {
		playerFinesseWeapons += amount;
	}
	public int playerRangedWeapons = 0;
	public void modifyRangedWeapons(int amount) {
		playerRangedWeapons += amount;
	}
	public int playerFortitude = 0;
	public void modifyFortitude(int amount) {
		playerFortitude += amount;
	}
	public int playerPerception = 0;
	public void modifyPerception(int amount) {
		playerPerception += amount;
	}
	public int playerStealth = 0;
	public void modifyStealth(int amount) {
		playerStealth += amount;
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
	
	public int playerSimpleWeaponsDefault = 0;
	public int playerMartialWeaponsDefault = 0;
	public int playerFinesseWeaponsDefault = 0;
	public int playerRangedWeaponsDefault = 0;
	public int playerFortitudeDefault = 0;
	public int playerPerceptionDefault = 0;
	public int playerStealthDefault = 0;
	public int playerEvocationDefault = 0;
	public int playerPyromancyDefault = 0;
	public int playerCryomancyDefault = 0;
	public int playerElectromancyDefault = 0;
	public int playerAlchemancyDefault = 0;
	
	public ChooseSkillScreen(String playerClass, List<Integer> playerAbilities, String playerName, String playerSpecies) {
		this.playerClass = playerClass;
		this.playerAbilities = playerAbilities;
		this.playerName = playerName;
		this.playerSpecies = playerSpecies;
		
		if(playerClass == "Warrior") {
			playerMartialWeaponsDefault = 1;
			playerFortitudeDefault = 1;
		}else if(playerClass == "Rogue") {
			playerFinesseWeaponsDefault = 1;
			playerStealthDefault = 1;
		}else if(playerClass == "Wizard") {
			playerEvocationDefault = 1;
			playerPerceptionDefault = 1;
		}else if(playerClass == "Barbarian") {
			playerSimpleWeaponsDefault = 1;
			playerMartialWeaponsDefault = 1;
		}else if(playerClass == "Evoker") {
			playerEvocationDefault = 1;
			playerMartialWeaponsDefault = 1;
		}else if(playerClass == "Ranger") {
			playerRangedWeaponsDefault = 1;
			playerPerceptionDefault = 1;
		}else if(playerClass == "Alchemist") {
			playerAlchemancyDefault = 1;
			playerPerceptionDefault = 1;
		}else if(playerClass == "Witch") {
			playerCryomancyDefault = 1;
			playerElectromancyDefault = 1;
		}else if(playerClass == "Sorcerer") {
			playerSimpleWeaponsDefault = 1;
			playerPyromancyDefault = 1;
		}
		
		if(playerSpecies == "Human") {
			modifyPoints(2);
		}
		
		playerSimpleWeapons = playerSimpleWeaponsDefault;
		playerMartialWeapons = playerMartialWeaponsDefault;
		playerFinesseWeapons = playerFinesseWeaponsDefault;
		playerRangedWeapons = playerRangedWeaponsDefault;
		playerFortitude = playerFortitudeDefault;
		playerPerception = playerPerceptionDefault;
		playerStealth = playerStealthDefault;
		playerEvocation = playerEvocationDefault;
		playerPyromancy = playerPyromancyDefault;
		playerCryomancy = playerCryomancyDefault;
		playerElectromancy = playerElectromancyDefault;
		playerAlchemancy = playerAlchemancyDefault;
		
		playerSkills.add(playerSimpleWeapons);
		playerSkills.add(playerMartialWeapons);
		playerSkills.add(playerFinesseWeapons);
		playerSkills.add(playerRangedWeapons);
		playerSkills.add(playerFortitude);
		playerSkills.add(playerPerception);
		playerSkills.add(playerStealth);
		playerSkills.add(playerEvocation);
		playerSkills.add(playerPyromancy);
		playerSkills.add(playerCryomancy);
		playerSkills.add(playerElectromancy);
		playerSkills.add(playerAlchemancy);

		
	}
	
	public char simpleLeft = '>';
	public char simpleRight = '<';
	public char martialLeft = '>';
	public char martialRight = '<';
	public char finesseLeft = '>';
	public char finesseRight = '<';
	public char rangedLeft = '>';
	public char rangedRight = '<';
	public char fortitudeLeft = '>';
	public char fortitudeRight = '<';
	public char percepLeft = '>';
	public char percepRight = '<';
	public char stealthLeft = '>';
	public char stealthRight = '<';
	public char evoLeft = '>';
	public char evoRight = '<';
	public char pyroLeft = '>';
	public char pyroRight = '<';
	public char cryoLeft = '>';
	public char cryoRight = '<';
	public char electroLeft = '>';
	public char electroRight = '<';
	public char alchLeft = '>';
	public char alchRight = '<';
	
	public char simpleUp = '+';
	public char simpleDown = '-';
	public char martialUp = '+';
	public char martialDown = '-';
	public char finesseUp = '+';
	public char finesseDown = '-';
	public char rangedUp = '+';
	public char rangedDown = '-';
	public char fortitudeUp = '+';
	public char fortitudeDown = '-';
	public char percepUp = '+';
	public char percepDown = '-';
	public char stealthUp = '+';
	public char stealthDown = '-';
	public char evoUp = '+';
	public char evoDown = '-';
	public char pyroUp = '+';
	public char pyroDown = '-';
	public char cryoUp = '+';
	public char cryoDown = '-';
	public char electroUp = '+';
	public char electroDown = '-';
	public char alchUp = '+';
	public char alchDown = '-';
	
	public void changeMarkers(int check) {
		if(check == 0) {
			simpleLeft = '>';
			simpleRight = '<';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 1) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = '>';
			martialRight = '<';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 2) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = '>';
			finesseRight = '<';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 3) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = '>';
			rangedRight = '<';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 4) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = '>';
			fortitudeRight = '<';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 5) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = '>';
			percepRight = '<';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 6) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = '>';
			stealthRight = '<';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 7) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = '>';
			evoRight = '<';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 8) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = '>';
			pyroRight = '<';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 9) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = '>';
			cryoRight = '<';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 10) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = '>';
			electroRight = '<';
			alchLeft = ' ';
			alchRight = ' ';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}else if(check == 11) {
			simpleLeft = ' ';
			simpleRight = ' ';
			martialLeft = ' ';
			martialRight = ' ';
			finesseLeft = ' ';
			finesseRight = ' ';
			rangedLeft = ' ';
			rangedRight = ' ';
			fortitudeLeft = ' ';
			fortitudeRight = ' ';
			percepLeft = ' ';
			percepRight = ' ';
			stealthLeft = ' ';
			stealthRight = ' ';
			evoLeft = ' ';
			evoRight = ' ';
			pyroLeft = ' ';
			pyroRight = ' ';
			cryoLeft = ' ';
			cryoRight = ' ';
			electroLeft = ' ';
			electroRight = ' ';
			alchLeft = '>';
			alchRight = '<';
			if(playerSimpleWeapons == playerSimpleWeaponsDefault && skillPoints > 0) {
				simpleUp = '+';
				simpleDown = ' ';
			}else if(skillPoints == 0 && playerSimpleWeapons > playerSimpleWeaponsDefault) {
				simpleUp = ' ';
				simpleDown = '-';
			}else if(skillPoints == 0) {
				simpleUp = ' ';
				simpleDown = ' ';
			}else {
				simpleUp = '+';
				simpleDown = '-';
			}
			if(playerMartialWeapons == playerMartialWeaponsDefault && skillPoints > 0) {
				martialUp = '+';
				martialDown = ' ';
			}else if(skillPoints == 0 && playerMartialWeapons > playerMartialWeaponsDefault) {
				martialUp = ' ';
				martialDown = '-';
			}else if(skillPoints == 0) {
				martialUp = ' ';
				martialDown = ' ';
			}else {
				martialUp = '+';
				martialDown = '-';
			}
			if(playerFinesseWeapons == playerFinesseWeaponsDefault && skillPoints > 0) {
				finesseUp = '+';
				finesseDown = ' ';
			}else if(skillPoints == 0 && playerFinesseWeapons > playerFinesseWeaponsDefault) {
				finesseUp = ' ';
				finesseDown = '-';
			}else if(skillPoints == 0) {
				finesseUp = ' ';
				finesseDown = ' ';
			}else {
				finesseUp = '+';
				finesseDown = '-';
			}
			if(playerRangedWeapons == playerRangedWeaponsDefault && skillPoints > 0) {
				rangedUp = '+';
				rangedDown = ' ';
			}else if(skillPoints == 0 && playerRangedWeapons > playerRangedWeaponsDefault) {
				rangedUp = ' ';
				rangedDown = '-';
			}else if(skillPoints == 0) {
				rangedUp = ' ';
				rangedDown = ' ';
			}else {
				rangedUp = '+';
				rangedDown = '-';
			}
			if(playerFortitude == playerFortitudeDefault && skillPoints > 0) {
				fortitudeUp = '+';
				fortitudeDown = ' ';
			}else if(skillPoints == 0 && playerFortitude > playerFortitudeDefault) {
				fortitudeUp = ' ';
				fortitudeDown = '-';
			}else if(skillPoints == 0) {
				fortitudeUp = ' ';
				fortitudeDown = ' ';
			}else {
				fortitudeUp = '+';
				fortitudeDown = '-';
			}
			if(playerPerception == playerPerceptionDefault && skillPoints > 0) {
				percepUp = '+';
				percepDown = ' ';
			}else if(skillPoints == 0 && playerPerception > playerPerceptionDefault) {
				percepUp = ' ';
				percepDown = '-';
			}else if(skillPoints == 0) {
				percepUp = ' ';
				percepDown = ' ';
			}else {
				percepUp = '+';
				percepDown = '-';
			}
			if(playerStealth == playerStealthDefault && skillPoints > 0) {
				stealthUp = '+';
				stealthDown = ' ';
			}else if(skillPoints == 0 && playerStealth > playerStealthDefault) {
				stealthUp = ' ';
				stealthDown = '-';
			}else if(skillPoints == 0) {
				stealthUp = ' ';
				stealthDown = ' ';
			}else {
				stealthUp = '+';
				stealthDown = '-';
			}
			if(playerEvocation == playerEvocationDefault && skillPoints > 0) {
				evoUp = '+';
				evoDown = ' ';
			}else if(skillPoints == 0 && playerEvocation > playerEvocationDefault) {
				evoUp = ' ';
				evoDown = '-';
			}else if(skillPoints == 0) {
				evoUp = ' ';
				evoDown = ' ';
			}else {
				evoUp = '+';
				evoDown = '-';
			}
			if(playerPyromancy == playerPyromancyDefault && skillPoints > 0) {
				pyroUp = '+';
				pyroDown = ' ';
			}else if(skillPoints == 0 && playerPyromancy > playerPyromancyDefault) {
				pyroUp = ' ';
				pyroDown = '-';
			}else if(skillPoints == 0) {
				pyroUp = ' ';
				pyroDown = ' ';
			}else {
				pyroUp = '+';
				pyroDown = '-';
			}
			if(playerCryomancy == playerCryomancyDefault && skillPoints > 0) {
				cryoUp = '+';
				cryoDown = ' ';
			}else if(skillPoints == 0 && playerCryomancy > playerCryomancyDefault) {
				cryoUp = ' ';
				cryoDown = '-';
			}else if(skillPoints == 0) {
				cryoUp = ' ';
				cryoDown = ' ';
			}else {
				cryoUp = '+';
				cryoDown = '-';
			}
			if(playerElectromancy == playerElectromancyDefault && skillPoints > 0) {
				electroUp = '+';
				electroDown = ' ';
			}else if(skillPoints == 0 && playerElectromancy > playerElectromancyDefault) {
				electroUp = ' ';
				electroDown = '-';
			}else if(skillPoints == 0) {
				electroUp = ' ';
				electroDown = ' ';
			}else {
				electroUp = '+';
				electroDown = '-';
			}
			if(playerAlchemancy == playerAlchemancyDefault && skillPoints > 0) {
				alchUp = '+';
				alchDown = ' ';
			}else if(skillPoints == 0 && playerAlchemancy > playerAlchemancyDefault) {
				alchUp = ' ';
				alchDown = '-';
			}else if(skillPoints == 0) {
				alchUp = ' ';
				alchDown = ' ';
			}else {
				alchUp = '+';
				alchDown = '-';
			}
		}
	}

	

	public void displayOutput(AsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		terminal.writeCenter("== Select your starting Skills ==", 1);	
		int y = 3;
		
		terminal.writeCenter(String.format("-- Points remaining: %d --", skillPoints), y);
		
		terminal.writeCenter(String.format("%c %c Simple Weapons ( %d ) %c %c", simpleDown, simpleLeft, playerSimpleWeapons, simpleRight, simpleUp), y+=3);
		terminal.writeCenter(String.format("%c %c Martial Weapons ( %d ) %c %c", martialDown, martialLeft, playerMartialWeapons, martialRight, martialUp), y+=2);
		terminal.writeCenter(String.format("%c %c Finesse Weapons ( %d ) %c %c", finesseDown, finesseLeft, playerFinesseWeapons, finesseRight, finesseUp), y+=2);
		terminal.writeCenter(String.format("%c %c Ranged Weapons ( %d ) %c %c", rangedDown, rangedLeft, playerRangedWeapons, rangedRight, rangedUp), y+=2);
		terminal.writeCenter(String.format("%c %c Fortitude ( %d ) %c %c", fortitudeDown, fortitudeLeft, playerFortitude, fortitudeRight, fortitudeUp), y+=2);
		terminal.writeCenter(String.format("%c %c Perception ( %d ) %c %c", percepDown, percepLeft, playerPerception, percepRight, percepUp), y+=2);
		terminal.writeCenter(String.format("%c %c Stealth ( %d ) %c %c", stealthDown, stealthLeft, playerStealth, stealthRight, stealthUp), y+=2);
		terminal.writeCenter(String.format("%c %c Evocation ( %d ) %c %c", evoDown, evoLeft, playerEvocation, evoRight, evoUp), y+=2);
		terminal.writeCenter(String.format("%c %c Pyromancy ( %d ) %c %c", pyroDown, pyroLeft, playerPyromancy, pyroRight, pyroUp), y+=2);
		terminal.writeCenter(String.format("%c %c Cryomancy ( %d ) %c %c", cryoDown, cryoLeft, playerCryomancy, cryoRight, cryoUp), y+=2);
		terminal.writeCenter(String.format("%c %c Electromancy ( %d ) %c %c", electroDown, electroLeft, playerElectromancy, electroRight, electroUp), y+=2);
		terminal.writeCenter(String.format("%c %c Alchemancy ( %d ) %c %c", alchDown, alchLeft, playerAlchemancy, alchRight, alchUp), y+=2);
		
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
			terminal.writeCenter("Finesse Weapons represents your skill with finesse weapons", y+=5);
			terminal.writeCenter("such as daggers and rapiers. A higher Finesse Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful finesse weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 3) {
			terminal.writeCenter("Ranged Weapons represents your skill with ranged weapons", y+=5);
			terminal.writeCenter("such as bows and crossbows. A higher Ranged Weapons skill", y+=1);
			terminal.writeCenter("allows you to use more powerful ranged weapons,", y+=1);
			terminal.writeCenter("and improves your accuracy with weapons of this type.", y+=1);
		}else if(check == 4) {
			terminal.writeCenter("Fortitude is a measure of your stamina and endurance.", y+=5);
			terminal.writeCenter("A higher Fortitude skill allows you to wear heavier armor,", y+=1);
			terminal.writeCenter("and increases the amount of time you can go without eating.", y+=1);
		}else if(check == 5) {
			terminal.writeCenter("Perception is a measure of your awareness and eye for detail.", y+=5);
			terminal.writeCenter("A higher Perception skill allows you to spot traps and other", y+=1);
			terminal.writeCenter("hidden things with greater ease, as well as improving", y+=1);
			terminal.writeCenter("your chances of identifying unknown items.", y+=1);
		}else if(check == 6) {
			terminal.writeCenter("Stealth represents your ability to move unnoticed.", y+=5);
			terminal.writeCenter("A higher Stealth skill allows you to sneak past sleeping", y+=1);
			terminal.writeCenter("and unalerted foes more reliably, giving you the upper", y+=1);
			terminal.writeCenter("hand in combat and making it easier to avoid a fight.", y+=1);
		}else if(check == 7) {
			terminal.writeCenter("Evocation is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of raw magic. A higher Evocation skill", y+=1);
			terminal.writeCenter("allows you to use more powerful evocation wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your evocation wands.", y+=1);
		}else if(check == 8) {
			terminal.writeCenter("Pyromancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of fire and heat. A higher Pyromancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful pyromancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your pyromancy wands.", y+=1);
		}else if(check == 9) {
			terminal.writeCenter("Cryomancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of water and ice. A higher Cryomancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful cryomancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your cryomancy wands.", y+=1);
		}else if(check == 10) {
			terminal.writeCenter("Electromancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of electrical energy. A higher Electromancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful electromancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your electromancy wands.", y+=1);
		}else if(check == 11) {
			terminal.writeCenter("Alchemancy is a school of magic focused mainly on the", y+=5);
			terminal.writeCenter("manipulation of chemical processes. A higher Alchemancy skill", y+=1);
			terminal.writeCenter("allows you to use more powerful alchemancy wands,", y+=1);
			terminal.writeCenter("and improves the effectiveness of your alchemancy wands.", y+=1);
		}
		
		
		if(skillPoints < 1) {
			terminal.writeCenter("-- [ENTER]: Confirm and Continue --", 38);
		}
		terminal.writeCenter("-- [UP / DOWN]: Move Selection | [LEFT / RIGHT]: Increase/Decrease Skill --", 40);
		terminal.writeCenter("-- [ESCAPE]: Return to Ability Score Selection --", 42);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = 11;
			}else if(check == 1) {
				check = 0;
			}else if(check == 2) {
				check = 1;
			}else if(check == 3) {
				check = 2;
			}else if(check == 4) {
				check = 3;
			}else if(check == 5) {
				check = 4;
			}else if(check == 6) {
				check = 5;
			}else if(check == 7) {
				check = 6;
			}else if(check == 8) {
				check = 7;
			}else if(check == 9) {
				check = 8;
			}else if(check == 10) {
				check = 9;
			}else if(check == 11) {
				check = 10;
			}
			return this;
			
		case KeyEvent.VK_DOWN:
			if(check == 0) {
				check = 1;
			}else if(check == 1) {
				check = 2;
			}else if(check == 2) {
				check = 3;
			}else if(check == 3) {
				check = 4;
			}else if(check == 4) {
				check = 5;
			}else if(check == 5) {
				check = 6;
			}else if(check == 6) {
				check = 7;
			}else if(check == 7) {
				check = 8;
			}else if(check == 8) {
				check = 9;
			}else if(check == 9) {
				check = 10;
			}else if(check == 10) {
				check = 11;
			}else if(check == 11) {
				check = 0;
			}
			return this;
		
		case KeyEvent.VK_RIGHT:
			if(check == 0) {
				if(skillPoints > 0) {
					modifySimpleWeapons(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(0, playerSimpleWeapons); 
			}else if(check == 1) {
				if(skillPoints > 0) {
					modifyMartialWeapons(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(1, playerMartialWeapons); 
			}else if(check == 2) {
				if(skillPoints > 0) {
					modifyFinesseWeapons(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(2, playerFinesseWeapons); 
			}else if(check == 3) {
				if(skillPoints > 0) {
					modifyRangedWeapons(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(3, playerRangedWeapons); 
			}else if(check == 4) {
				if(skillPoints > 0) {
					modifyFortitude(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(4, playerFortitude); 
			}else if(check == 5) {
				if(skillPoints > 0) {
					modifyPerception(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(5, playerPerception); 
			}else if(check == 6) {
				if(skillPoints > 0) {
					modifyStealth(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(6, playerStealth); 
			}else if(check == 7) {
				if(skillPoints > 0) {
					modifyEvocation(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(7, playerEvocation); 
			}else if(check == 8) {
				if(skillPoints > 0) {
					modifyPyromancy(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(8, playerPyromancy); 
			}else if(check == 9) {
				if(skillPoints > 0) {
					modifyCryomancy(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(9, playerCryomancy); 
			}else if(check == 10) {
				if(skillPoints > 0) {
					modifyElectromancy(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(10, playerElectromancy); 
			}else if(check == 11) {
				if(skillPoints > 0) {
					modifyAlchemancy(1); 
					modifyPoints(-1); 
				}
				playerSkills.set(11, playerAlchemancy); 
			}	
			return this;
			
		case KeyEvent.VK_LEFT:
			if(check == 0) {
				if(playerSimpleWeapons > playerSimpleWeaponsDefault) {
					modifySimpleWeapons(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(0, playerSimpleWeapons); 
			}else if(check == 1) {
				if(playerMartialWeapons > playerMartialWeaponsDefault) {
					modifyMartialWeapons(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(1, playerMartialWeapons); 
			}else if(check == 2) {
				if(playerFinesseWeapons > playerFinesseWeaponsDefault) {
					modifyFinesseWeapons(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(2, playerFinesseWeapons); 
			}else if(check == 3) {
				if(playerRangedWeapons > playerRangedWeaponsDefault) {
					modifyRangedWeapons(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(3, playerRangedWeapons); 
			}else if(check == 4) {
				if(playerFortitude > playerFortitudeDefault) {
					modifyFortitude(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(4, playerFortitude); 
			}else if(check == 5) {
				if(playerPerception > playerPerceptionDefault) {
					modifyPerception(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(5, playerPerception); 
			}else if(check == 6) {
				if(playerStealth > playerStealthDefault) {
					modifyStealth(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(6, playerEvocation); 
			}else if(check == 7) {
				if(playerEvocation > playerEvocationDefault) {
					modifyEvocation(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(7, playerEvocation); 
			}else if(check == 8) {
				if(playerPyromancy > playerPyromancyDefault) {
					modifyPyromancy(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(8, playerPyromancy); 
			}else if(check == 9) {
				if(playerCryomancy > playerCryomancyDefault) {
					modifyCryomancy(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(9, playerCryomancy); 
			}else if(check == 10) {
				if(playerElectromancy > playerElectromancyDefault) {
					modifyElectromancy(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(10, playerElectromancy); 
			}else if(check == 11) {
				if(playerAlchemancy > playerAlchemancyDefault) {
					modifyAlchemancy(-1); 
					modifyPoints(1); 
				}
				playerSkills.set(11, playerAlchemancy); 
			}
				
				
			return this;
		
			
		case KeyEvent.VK_ENTER: 
			if(skillPoints < 1) {
				return new PlayScreen(playerClass, playerAbilities, playerSkills, playerName, playerSpecies); 
			}else {
				return this;
			}
			
		case KeyEvent.VK_ESCAPE: return new ChooseAbilityScreen(playerClass, playerSpecies);
		
		}
		
		return this;
	}
	

}

*/