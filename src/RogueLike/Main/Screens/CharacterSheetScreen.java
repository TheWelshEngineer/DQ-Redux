package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

public class CharacterSheetScreen implements Screen{
	
	protected Creature player;
	protected List<Effect> effects;
	
	public CharacterSheetScreen(Creature player) {
		this.player = player;
		this.effects = player.effects();
	}
	
	private String nameCheck = ">> ";
	private String levelCheck = ">> ";
	private String xpCheck = ">> ";
	private String goldCheck = ">> ";
	
	private String healthCheck = ">> ";
	private String manaCheck = ">> ";
	private String hungerCheck = ">> ";
	
	private String strengthCheck = ">> ";
	private String dexterityCheck = ">> ";
	private String intelligenceCheck = ">> ";
	
	private String armorCheck = ">> ";
	private String proficiencyCheck = ">> ";
	private String visionCheck = ">> ";
	
	private String physicalCheck = ">> ";
	private String fireCheck = ">> ";
	private String frostCheck = ">> ";
	private String shockCheck = ">> ";
	private String poisonCheck = ">> ";
	private String acidCheck = ">> ";
	private String magicCheck = ">> ";
	private String chaosCheck = ">> ";
	
	int cX = 0;
	int cY = 0;
	
	private String updateSelected(int cX, int cY) {
		if(cX != 3) {
			physicalCheck = "";
			fireCheck = "";
			frostCheck = "";
			shockCheck = "";
			poisonCheck = "";
			acidCheck = "";
			magicCheck = "";
			chaosCheck = "";
		}
		if(cX == 0) {
			switch(cY) {
			case 0:
				nameCheck = ">> ";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 1:
				nameCheck = "";
				levelCheck = ">> ";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 2:
				nameCheck = "";
				levelCheck = "";
				xpCheck = ">> ";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 3:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = ">> ";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 4:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = ">> ";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 5:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = ">> ";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 6:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = ">> ";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 7:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = ">> ";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 8:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = ">> ";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 9:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = ">> ";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 10:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = ">> ";
				proficiencyCheck = "";
				visionCheck = "";
				break;
			case 11:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = ">> ";
				visionCheck = "";
				break;
			case 12:
				nameCheck = "";
				levelCheck = "";
				xpCheck = "";
				goldCheck = "";
				healthCheck = "";
				manaCheck = "";
				hungerCheck = "";
				strengthCheck = "";
				dexterityCheck = "";
				intelligenceCheck = "";
				armorCheck = "";
				proficiencyCheck = "";
				visionCheck = ">> ";
				break;
			default: break;
			}
		}else {
			nameCheck = "";
			levelCheck = "";
			xpCheck = "";
			goldCheck = "";
			healthCheck = "";
			manaCheck = "";
			hungerCheck = "";
			strengthCheck = "";
			dexterityCheck = "";
			intelligenceCheck = "";
			armorCheck = "";
			proficiencyCheck = "";
			visionCheck = "";
			if(cX == 3) {
				switch(cY) {
				case 0:
					physicalCheck = ">> ";
					fireCheck = "";
					frostCheck = "";
					shockCheck = "";
					poisonCheck = "";
					acidCheck = "";
					magicCheck = "";
					chaosCheck = "";
					break;
				case 1:
					physicalCheck = "";
					fireCheck = ">> ";
					frostCheck = "";
					shockCheck = "";
					poisonCheck = "";
					acidCheck = "";
					magicCheck = "";
					chaosCheck = "";
					break;
				case 2:
					physicalCheck = "";
					fireCheck = "";
					frostCheck = ">> ";
					shockCheck = "";
					poisonCheck = "";
					acidCheck = "";
					magicCheck = "";
					chaosCheck = "";
					break;
				case 3:
					physicalCheck = "";
					fireCheck = "";
					frostCheck = "";
					shockCheck = ">> ";
					poisonCheck = "";
					acidCheck = "";
					magicCheck = "";
					chaosCheck = "";
					break;
				case 4:
					physicalCheck = "";
					fireCheck = "";
					frostCheck = "";
					shockCheck = "";
					poisonCheck = ">> ";
					acidCheck = "";
					magicCheck = "";
					chaosCheck = "";
					break;
				case 5:
					physicalCheck = "";
					fireCheck = "";
					frostCheck = "";
					shockCheck = "";
					poisonCheck = "";
					acidCheck = ">> ";
					magicCheck = "";
					chaosCheck = "";
					break;
				case 6:
					physicalCheck = "";
					fireCheck = "";
					frostCheck = "";
					shockCheck = "";
					poisonCheck = "";
					acidCheck = "";
					magicCheck = ">> ";
					chaosCheck = "";
					break;
				case 7:
					physicalCheck = "";
					fireCheck = "";
					frostCheck = "";
					shockCheck = "";
					poisonCheck = "";
					acidCheck = "";
					magicCheck = "";
					chaosCheck = ">> ";
					break;
				default: break;
				}
			}
		}
		return null;
	}
	
	@Override
	public void displayOutput(ExtendedAsciiPanel terminal) {
		terminal.clear();
		
		Screen.generateBorders(terminal);
		
		this.updateSelected(cX, cY);
		
		terminal.writeCenter("== Character Sheet ==", 1);	
		int y = 3;
		int y2 = 3;
		int y3 = 3;
		int y4 = 3;
		
		int x = 5;
		int x2 = 32;
		int x3 = 59;
		int x4 = 89;
		
		
		
		terminal.write("== Stats ==", x, y++);	
		terminal.write(String.format("%s%s", nameCheck, player.playerName()), x, y++);
		terminal.write(String.format("%sLevel %d %s %s", levelCheck, player.level(), player.playerAncestry(), player.playerClass()), x, y++);
		terminal.write(String.format("%sXP: %d/%d", xpCheck, player.xp(), player.xpToNextLevel()), x, y++);
		terminal.write(String.format("%sGold: %d gold", goldCheck, player.gold()), x, y++);
		y++;
        terminal.write(String.format("%sHealth: %d/%d", healthCheck, player.hp(), player.maxHP()), x, y++);
        terminal.write(String.format("%sMana: %d/%d", manaCheck, player.mana(), player.maxMana()), x, y++);
        terminal.write(String.format("%sHunger: %s", hungerCheck, player.hungerAsString()), x, y++);
        y++;
        terminal.write(String.format("%sStrength: %d (%d)", strengthCheck, player.strength(), player.strengthModifier()), x, y++);
        terminal.write(String.format("%sDexterity: %d (%d)", dexterityCheck, player.dexterity(), player.dexterityModifier()), x, y++);
        terminal.write(String.format("%sIntelligence: %d (%d)", intelligenceCheck, player.intelligence(), player.intelligenceModifier()), x, y++);
        y++;
        terminal.write(String.format("%sArmor Class: %d", armorCheck, player.armorClass()), x, y++);
        terminal.write(String.format("%sProficiency Bonus: +%s", proficiencyCheck, player.proficiencyBonus()), x, y++);
        terminal.write(String.format("%sVision Radius: %d tiles", visionCheck, player.visionRadius()), x, y++);
        y++;
        terminal.write("== Skills ==", x2, y2++);
        for(int i = 0; i < 14; i++) {
        	if(cX == 1 && cY == i) {
        		terminal.write(String.format(">> %s", player.skills()[i].toStringCharacterSheet()), x2, y2++);
        	}else {
        		terminal.write(String.format("%s", player.skills()[i].toStringCharacterSheet()), x2, y2++);
        	}
        }
        y++;
        y++;
        terminal.write("== Status Effects ==", x3, y3++);
        y++;
        for(Effect effect : effects) {
        	if(effect.name() != null) {
        		String turns = "turn";
        		if(effect.duration() > 1) {
        			turns = "turns";
        		}
        		char effectIcon = (char)30;
        		if(effect.isNegative()) {
        			effectIcon = (char)31;
        		}
        		if(effect.showInMenu()) {
        			if(cX == 2 && cY == effects.indexOf(effect)) {
        				terminal.write(String.format(">> %c %s: %d %s", effectIcon, effect.name(), effect.duration(), turns), x3, y3++);
        			}else {
        				terminal.write(String.format("%c %s: %d %s", effectIcon, effect.name(), effect.duration(), turns), x3, y3++);
        			}
        			
        		}
        		
        	}
		}
        
        
        
        terminal.write("== Damage Resistances ==", x4, y4++);
        terminal.write(String.format("%sPhysical: %s", physicalCheck, physicalStatus()), x4, y4++);
        terminal.write(String.format("%sFire: %s", fireCheck, fireStatus()), x4, y4++);
        terminal.write(String.format("%sFrost: %s", frostCheck, frostStatus()), x4, y4++);
        terminal.write(String.format("%sShock: %s", shockCheck, shockStatus()), x4, y4++);
        terminal.write(String.format("%sPoison: %s", poisonCheck, poisonStatus()), x4, y4++);
        terminal.write(String.format("%sAcid: %s", acidCheck, acidStatus()), x4, y4++);
        terminal.write(String.format("%sMagic: %s", magicCheck, magicStatus()), x4, y4++);
        terminal.write(String.format("%sChaos: %s", chaosCheck, chaosStatus()), x4, y4++);
    
        terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
		
	}
	
	public String physicalStatus() {
		if(player.immunePhysicalDamage()) {
			return "Immune";
		}else if(player.resistsPhysicalDamage()) {
			return "Resistant";
		}else if(player.weakToPhysicalDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String fireStatus() {
		if(player.immuneFireDamage()) {
			return "Immune";
		}else if(player.resistsFireDamage()) {
			return "Resistant";
		}else if(player.weakToFireDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String frostStatus() {
		if(player.immuneFrostDamage()) {
			return "Immune";
		}else if(player.resistsFrostDamage()) {
			return "Resistant";
		}else if(player.weakToFrostDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String shockStatus() {
		if(player.immuneShockDamage()) {
			return "Immune";
		}else if(player.resistsShockDamage()) {
			return "Resistant";
		}else if(player.weakToShockDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String poisonStatus() {
		if(player.immunePoisonDamage()) {
			return "Immune";
		}else if(player.resistsPoisonDamage()) {
			return "Resistant";
		}else if(player.weakToPoisonDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String acidStatus() {
		if(player.immuneAcidDamage()) {
			return "Immune";
		}else if(player.resistsAcidDamage()) {
			return "Resistant";
		}else if(player.weakToAcidDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String magicStatus() {
		if(player.immuneMagicDamage()) {
			return "Immune";
		}else if(player.resistsMagicDamage()) {
			return "Resistant";
		}else if(player.weakToMagicDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}
	public String chaosStatus() {
		if(player.immuneChaosDamage()) {
			return "Immune";
		}else if(player.resistsChaosDamage()) {
			return "Resistant";
		}else if(player.weakToChaosDamage()) {
			return "Weakness";
		}else {
			return "No modifier";
		}
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuLeft:
			if(cX == 0) {
				cX = 3;
			}else {
				if(effects.size() == 0 && cX == 3) {
					cX = 1;
				}else {
					cX--;
				}
			}
			cY = 0;
			return this;
		case KeybindManager.navigateMenuRight:
			if(cX == 3) {
				cX = 0;
			}else {
				if(effects.size() == 0 && cX == 1) {
					cX = 3;
				}else {
					cX++;
				}
			}
			cY = 0;
			return this;
		case KeybindManager.navigateMenuUp:
			if(cX == 0) {
				if(cY == 0) {
					cY = 12;
				}else {
					cY--;
				}
			}else if(cX == 1) {
				if(cY == 0) {
					cY = 13;
				}else {
					cY--;
				}
			}else if(cX == 2) {
				if(cY == 0) {
					cY = effects.size()-1;
				}else {
					cY--;
				}
			}else if(cX == 3) {
				if(cY == 0) {
					cY = 7;
				}else {
					cY--;
				}
			}
			return this;
		case KeybindManager.navigateMenuDown:
			if(cX == 0) {
				if(cY == 12) {
					cY = 0;
				}else {
					cY++;
				}
			}else if(cX == 1) {
				if(cY == 13) {
					cY = 0;
				}else {
					cY++;
				}
			}
			else if(cX == 2) {
				if(cY == effects.size()-1) {
					cY = 0;
				}else {
					cY++;
				}
			}else if(cX == 3) {
				if(cY == 7) {
					cY = 0;
				}else {
					cY++;
				}
			}
			//TODO other columns
			return this;
		case KeybindManager.navigateMenuBack:
			return null;
		default: return this;
		}
	}

}
