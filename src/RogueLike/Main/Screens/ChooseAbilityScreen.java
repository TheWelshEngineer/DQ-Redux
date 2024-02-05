package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class ChooseAbilityScreen implements Screen{
	
	public String playerClass;
	public String playerAncestry;
	 
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public char strLeft = '>';
	public char strRight = '<';
	public char dexLeft = '>';
	public char dexRight = '<';
	public char intLeft = '>';
	public char intRight = '<';
	
	public char strUp = '+';
	public char strDown = '-';
	public char dexUp = '+';
	public char dexDown = '-';
	public char intUp = '+';
	public char intDown = '-';
	
	public void changeMarkers(int check) {
		if(check == 0) {
			strLeft = '>';
			strRight = '<';
			dexLeft = ' ';
			dexRight = ' ';
			intLeft = ' ';
			intRight = ' ';
			if(playerStrength == 8) {
				strUp = '+';
				strDown = ' ';
			}else if(playerStrength == 16) {
				strUp = ' ';
				strDown = '-';
			}else{
				strUp = '+';
				strDown = '-';
			}
			if(playerDexterity == 8) {
				dexUp = '+';
				dexDown = ' ';
			}else if(playerDexterity == 16) {
				dexUp = ' ';
				dexDown = '-';
			}else{
				dexUp = '+';
				dexDown = '-';
			}
			if(playerIntelligence == 8) {
				intUp = '+';
				intDown = ' ';
			}else if(playerIntelligence == 16) {
				intUp = ' ';
				intDown = '-';
			}else{
				intUp = '+';
				intDown = '-';
			}
		}else if(check == 1) {
			strLeft = ' ';
			strRight = ' ';
			dexLeft = '>';
			dexRight = '<';
			intLeft = ' ';
			intRight = ' ';
			if(playerStrength == 8) {
				strUp = '+';
				strDown = ' ';
			}else if(playerStrength == 16) {
				strUp = ' ';
				strDown = '-';
			}else{
				strUp = '+';
				strDown = '-';
			}
			if(playerDexterity == 8) {
				dexUp = '+';
				dexDown = ' ';
			}else if(playerDexterity == 16) {
				dexUp = ' ';
				dexDown = '-';
			}else{
				dexUp = '+';
				dexDown = '-';
			}
			if(playerIntelligence == 8) {
				intUp = '+';
				intDown = ' ';
			}else if(playerIntelligence == 16) {
				intUp = ' ';
				intDown = '-';
			}else{
				intUp = '+';
				intDown = '-';
			}
		}else if(check == 2) {
			strLeft = ' ';
			strRight = ' ';
			dexLeft = ' ';
			dexRight = ' ';
			intLeft = '>';
			intRight = '<';
			if(playerStrength == 8) {
				strUp = '+';
				strDown = ' ';
			}else if(playerStrength == 16) {
				strUp = ' ';
				strDown = '-';
			}else{
				strUp = '+';
				strDown = '-';
			}
			if(playerDexterity == 8) {
				dexUp = '+';
				dexDown = ' ';
			}else if(playerDexterity == 16) {
				dexUp = ' ';
				dexDown = '-';
			}else{
				dexUp = '+';
				dexDown = '-';
			}
			if(playerIntelligence == 8) {
				intUp = '+';
				intDown = ' ';
			}else if(playerIntelligence == 16) {
				intUp = ' ';
				intDown = '-';
			}else{
				intUp = '+';
				intDown = '-';
			}
		}
		if(abilityPoints == 0) {
			strUp = ' ';
			dexUp = ' ';
			intUp = ' ';
		}
	}
	
	
	public int abilityPoints = 14;
	public void modifyPoints(int amount) {
		abilityPoints += amount;
	}
	public int playerStrength = 8;
	public void modifyStrength(int amount) {
		playerStrength += amount;
	}
	public int playerDexterity = 8;
	public void modifyDexterity(int amount) {
		playerDexterity += amount;
	}
	public int playerIntelligence = 8;
	public void modifyIntelligence(int amount) {
		playerIntelligence += amount;
	}
	
	public int strengthModifier() {
		return ExtraMaths.roundModifier(playerStrength);
	}
	public int dexterityModifier() {
		return ExtraMaths.roundModifier(playerDexterity);
	}
	public int intelligenceModifier() {
		return ExtraMaths.roundModifier(playerIntelligence);
	}
	
	public List<Integer> playerAbilities = new ArrayList<Integer>();
	
	
	public String playerName;
	public String playerName() {
		if(ExtraMaths.diceRoll(1, 2) == 1) {
			String firstName = randomFirstName();
			String lastName = randomLastName();
			String returnName = String.format("%s %s", firstName, lastName);
			return returnName;
		}else {
			String firstName = randomFirstName();
			String lastName = randomFirstName();
			String suffix = randomSuffix();
			String returnName = String.format("%s %s%s", firstName, lastName, suffix);
			return returnName;
		}
	}
	
	public void setPlayerName() {
		playerName = playerName();
	}
	


	public ChooseAbilityScreen(String playerClass, String playerAncestry) {
		this.playerClass = playerClass;
		this.playerAncestry = playerAncestry;
		playerAbilities.add(playerStrength);
		playerAbilities.add(playerDexterity);
		playerAbilities.add(playerIntelligence);
	}

	public void displayOutput(AsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Select your starting Ability Scores ==", 1);	
		int y = 3;
		
		terminal.writeCenter(String.format("-- Points remaining: %d --", abilityPoints), y);
		
		terminal.writeCenter(String.format("%c %c Strength ( %d / +%d ) %c %c", strDown, strLeft, playerStrength, strengthModifier(), strRight, strUp), y+=3);
		terminal.writeCenter(String.format("%c %c Dexterity ( %d / +%d ) %c %c", dexDown, dexLeft, playerDexterity, dexterityModifier(), dexRight, dexUp), y+=2);
		terminal.writeCenter(String.format("%c %c Intelligence ( %d / +%d ) %c %c", intDown, intLeft, playerIntelligence, intelligenceModifier(), intRight, intUp), y+=2);
		if(check == 0) {
			terminal.writeCenter("Strength represents the physical power of your body.", y+=6);
			terminal.writeCenter("Strength increases the damage dealt by simple and martial weapons,", y+=1);
			terminal.writeCenter("as well as the amount of health you gain from levelling up.", y+=1);
		}else if(check == 1) {
			terminal.writeCenter("Dexterity is a measure of your agility and reflexes.", y+=6);
			terminal.writeCenter("Dexterity increases the damage dealt by ranged and finesse weapons,", y+=1);
			terminal.writeCenter("as well as providing a bonus to light and medium armor.", y+=1);
		}else if(check == 2) {
			terminal.writeCenter("Intelligence represents your willpower and magical talent.", y+=6);
			terminal.writeCenter("Intelligence increases the effectiveness of wands,", y+=1);
			terminal.writeCenter("as well as the amount of mana you gain from levelling up.", y+=1);
		}
		
		
		if(abilityPoints < 1) {
			terminal.writeCenter(String.format("-- [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 36);
		}
		terminal.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s / %s]: Increase/Decrease Attribute --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 38);
		terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 40);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		setPlayerName();
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 2;
			}else{
				check--;
			}
			return this;
			
		case KeybindManager.navigateMenuDown:
			if(check == 2) {
				check = 0;
			}else {
				check++;
			}
			return this;
			
		case KeybindManager.navigateMenuRight:
			if(check == 0) {
				if(playerStrength < 16 && abilityPoints > 0) {
					if(playerStrength == 15 && abilityPoints >= 2) {
						modifyStrength(1); 
						modifyPoints(-2); 
					}else if(playerStrength < 15) {
						modifyStrength(1); 
						modifyPoints(-1);
					}
				}
				playerAbilities.set(0, playerStrength); 
			}else if(check == 1) {
				if(playerDexterity < 16 && abilityPoints > 0) {
					if(playerDexterity == 15 && abilityPoints >= 2) {
						modifyDexterity(1); 
						modifyPoints(-2); 
					}else if(playerDexterity < 15) {
						modifyDexterity(1); 
						modifyPoints(-1);
					}
				}
				playerAbilities.set(1, playerDexterity);
			}else if(check == 2) {
				if(playerIntelligence < 16 && abilityPoints > 0) {
					if(playerIntelligence == 15 && abilityPoints >= 2) {
						modifyIntelligence(1); 
						modifyPoints(-2); 
					}else if(playerIntelligence < 15) {
						modifyIntelligence(1); 
						modifyPoints(-1);
					}
				}
				playerAbilities.set(2, playerIntelligence); 
			}
			return this;
			
		case KeybindManager.navigateMenuLeft:
			if(check == 0) {
				if(playerStrength > 8) {
					if(playerStrength == 16) {
						modifyStrength(-1); 
						modifyPoints(2);
					}else {
						modifyStrength(-1); 
						modifyPoints(1); 
					}

				}
				playerAbilities.set(0, playerStrength);
			}else if(check == 1) {
				if(playerDexterity > 8) {
					if(playerDexterity == 16) {
						modifyDexterity(-1); 
						modifyPoints(2);
					}else {
						modifyDexterity(-1); 
						modifyPoints(1); 
					}

				}
				playerAbilities.set(1, playerDexterity);
			}else if(check == 2) {
				if(playerIntelligence > 8) {
					if(playerIntelligence == 16) {
						modifyIntelligence(-1); 
						modifyPoints(2);
					}else {
						modifyIntelligence(-1); 
						modifyPoints(1); 
					}

				}
				playerAbilities.set(2, playerIntelligence); 
			}
			return this;
		
			
		case KeybindManager.navigateMenuConfirm: 
			if(abilityPoints < 1) {
				return new ChooseSkillScreen(playerClass, playerAbilities, playerName, playerAncestry); 
			}else {
				return this;
			}
		
		case KeybindManager.navigateMenuBack: return new ChooseClassScreen(playerAncestry);
		}
		return this;
	}
	
	public String randomFirstName() {
		switch(ExtraMaths.diceRoll(1, 61)) {
		case 1: return "Elwyn";
		case 2: return "Cranog";
		case 3: return "Rhain";
		case 4: return "Arwel";
		case 5: return "Maelon";
		case 6: return "Morwenna";
		case 7: return "Elin";
		case 8: return "Gwyneth";
		case 9: return "Maegan";
		case 10: return "Teleri";
		case 11: return "Tadhg";
		case 12: return "Bhaltair";
		case 13: return "Martainn";
		case 14: return "Tormod";
		case 15: return "Asgall";
		case 16: return "Iseabail";
		case 17: return "Baraball";
		case 18: return "Caitriona";
		case 19: return "Maili";
		case 20: return "Aoife";
		case 21: return "Zakarias";
		case 22: return "Viggo";
		case 23: return "Astrad";
		case 24: return "Gunnar";
		case 25: return "Axel";
		case 26: return "Alice";
		case 27: return "Aeva";
		case 28: return "Tuva";
		case 29: return "Siv";
		case 30: return "Ingrid";
		case 31: return "Hvo";
		case 32: return "Brukmi";
		case 33: return "Dast";
		case 34: return "Aldn";
		case 35: return "Thekerv";
		case 36: return "Hvuna";
		case 37: return "Galra";
		case 38: return "Miornera";
		case 39: return "Sian";
		case 40: return "Dasa";
		case 41: return "Magdan";
		case 42: return "Armrigg";
		case 43: return "Melthrun";
		case 44: return "Ragnar";
		case 45: return "Hjal";
		case 46: return "Naerbera";
		case 47: return "Nallyn";
		case 48: return "Kathdora";
		case 49: return "Maeve";
		case 50: return "Helja";
		case 51: return "Stoic";
		case 52: return "Valka";
		case 53: return "Astrid";
		case 54: return "Aurora";
		case 55: return "Kathryn";
		case 56: return "Athene";
		case 57: return "Karl";
		case 58: return "Arn";
		case 59: return "Maria";
		case 60: return "Maeve";
		case 61: return "Violet";
		default: return "Elwyn";
		}
	}
	
	public String randomLastName() {
		switch(ExtraMaths.diceRoll(1, 40)) {
		case 1: return "Gwallawg";
		case 2: return "Uchdryd";
		case 3: return "Maddock";
		case 4: return "Caswallawn";
		case 5: return "Caddell";
		case 6: return "Urchardan";
		case 7: return "Ceiteach";
		case 8: return "MacMhatha";
		case 9: return "Fhiontag";
		case 10: return "MacArtain";
		case 11: return "Vikstrom";
		case 12: return "Svensson";
		case 13: return "Holgersson";
		case 14: return "Airisdottir";
		case 15: return "Einesdottir";
		case 16: return "Rekk";
		case 17: return "Skog";
		case 18: return "Karak";
		case 19: return "Hnerna";
		case 20: return "Mhodrella";
		case 21: return "Battlehammer";
		case 22: return "Hammerhand";
		case 23: return "Moltenire";
		case 24: return "Deepdelver";
		case 25: return "Silverheart";
		case 26: return "Careck";
		case 27: return "Barra";
		case 28: return "Tormyl";
		case 29: return "Mekak";
		case 30: return "Gekar";
		case 31: return "the Slayer";
		case 32: return "the Beardless";
		case 33: return "the Mighty";
		case 34: return "the Wise";
		case 35: return "the Crafty";
		case 36: return "the Bold";
		case 37: return "the Aledrinker";
		case 38: return "the Always-Hungry";
		case 39: return "the Foolish";
		case 40: return "the Ironhearted";
		default: return "Gwallawg";
		}
	}
	
	public String randomSuffix() {
		switch(ExtraMaths.diceRoll(1, 2)) {
		case 1: return "sson";
		case 2: return "sdottir";
		default: return "sson";
		}
	}

	
	

}