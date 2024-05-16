package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

public class CharacterSheetScreen implements Screen{
	
	protected Creature player;
	protected List<Effect> effects;
	
	public CharacterSheetScreen(Creature player) {
		this.player = player;
		this.effects = player.effects();
	}
	
	private String details = "";
	private String details2 = "";
	private String details3 = "";
	
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
				//
				details = "That's you!";
				details2 = "";
				details3 = "";
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
				//
				details = String.format("You are a level %d %s %s.", player.level(), player.playerAncestry(), player.playerClass());
				if(player.playerClass().equals("Rogue")) {
					details2 = "As a Rogue, you regenerate health and mana at a roughly even rate.";
				}else if(player.playerClass().equals("Ranger")) {
					details2 = "As a Ranger, you regenerate health and mana at a roughly even rate.";
				}else if(player.playerClass().equals("Warrior")) {
					details2 = "As a Warrior, your health regenerates quickly, at the expense of your mana.";
				}else if(player.playerClass().equals("Mage")) {
					details2 = "As a Mage, your mana regenerates quickly, at the expense of your health.";
				}
				if(player.playerAncestry().equals("Human")) {
					details3 = "As a Human, you began your quest with an addtional skill point.";
				}else if(player.playerAncestry().equals("Elf")) {
					details3 = "As an Elf, you gain 25% more maximum mana upon levelling up.";
				}else if(player.playerAncestry().equals("Dwarf")) {
					details3 = "As a Dwarf, you are resistant to Poison damage, and your base armor class is increased by 1.";
				}else if(player.playerAncestry().equals("Orc")) {
					details3 = "As an Orc, you regenerate health whenever you eat, based on the quality of the food item you ate.";
				}else if(player.playerAncestry().equals("Dragonborn")) {
					details3 = "As a Dragonborn, you are resistant to Fire damage, and began your quest with a Wand of Firebolt.";
				}
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
				//
				details = String.format("You require %d more experience points to level up.", player.xpToNextLevel(), player.playerClass());
				details2 = String.format("You have %d available attribute points.", player.attributePoints());
				details3 = String.format("You have %d available skill points.", player.skillPoints());
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
				details = String.format("You are carrying %d gold pieces in your purse.", player.gold());
				details2 = String.format("You are wielding equipment worth %d gold pieces.", player.equipmentValue());
				details3 = "";
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
				details = String.format("You have %d health points remaining, out of a maximum of %d.", player.hp(), player.maxHP());
				details2 = "";
				details3 = "";
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
				details = String.format("You have %d mana points remaining, out of a maximum of %d.", player.mana(), player.maxMana());
				details2 = "";
				details3 = "";
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
				details = String.format("You are %s. You'll probably next need to eat after %d turns of exploration.", player.hungerAsString(), player.food()/2);
				details2 = "";
				details3 = "";
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
				details = String.format("You have %d total points of Strength, granting a %s modifier to Strength rolls.", player.strength(), ExtraMaths.modifierToString(player.strengthModifier()));
				details2 = String.format("You have %d natural points of Strength, out of a maximum of 30.", player.baseStrength());
				details3 = "";
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
				details = String.format("You have %d total points of Dexterity, granting a %s modifier to Dexterity rolls.", player.dexterity(), ExtraMaths.modifierToString(player.dexterityModifier()));
				details2 = String.format("You have %d natural points of Dexterity, out of a maximum of 30.", player.baseDexterity());
				details3 = "";
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
				details = String.format("You have %d total points of Intelligence, granting a %s modifier to Intelligence rolls.", player.intelligence(), ExtraMaths.modifierToString(player.intelligenceModifier()));
				details2 = String.format("You have %d natural points of Intelligence, out of a maximum of 30.", player.baseIntelligence());
				details3 = "";
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
				details = String.format("You have a total Armor Class of %d.", player.armorClass());
				details2 = String.format("You have a natural Armor Class of %d.", player.baseArmorClass());
				details3 = "";
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
				details = String.format("As a level %d adventurer, you have a +%d proficiency bonus.", player.level(), player.proficiencyBonus());
				details2 = "";
				details3 = "";
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
				details = String.format("You have a vision radius of %d tiles.", player.visionRadius());
				details2 = "";
				details3 = "";
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
					//
					if(physicalStatus().equals("Immune")) {
						details = String.format("You are immune to Physical damage, taking no damage of this type.");
					}else if(physicalStatus().equals("Resistant")) {
						details = String.format("You are resistant to Physical damage, taking half damage of this type.");
					}else if(physicalStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Physical damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Physical damage.");
					}
					details2 = "";
					details3 = "";
					//
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
					//
					if(fireStatus().equals("Immune")) {
						details = String.format("You are immune to Fire damage, taking no damage of this type.");
					}else if(fireStatus().equals("Resistant")) {
						details = String.format("You are resistant to Fire damage, taking half damage of this type.");
					}else if(fireStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Fire damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Fire damage.");
					}
					details2 = "";
					details3 = "";
					//
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
					//
					if(frostStatus().equals("Immune")) {
						details = String.format("You are immune to Frost damage, taking no damage of this type.");
					}else if(frostStatus().equals("Resistant")) {
						details = String.format("You are resistant to Frost damage, taking half damage of this type.");
					}else if(frostStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Frost damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Frost damage.");
					}
					details2 = "";
					details3 = "";
					//
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
					//
					if(shockStatus().equals("Immune")) {
						details = String.format("You are immune to Shock damage, taking no damage of this type.");
					}else if(shockStatus().equals("Resistant")) {
						details = String.format("You are resistant to Shock damage, taking half damage of this type.");
					}else if(shockStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Shock damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Shock damage.");
					}
					details2 = "";
					details3 = "";
					//
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
					//
					if(poisonStatus().equals("Immune")) {
						details = String.format("You are immune to Poison damage, taking no damage of this type.");
					}else if(poisonStatus().equals("Resistant")) {
						details = String.format("You are resistant to Poison damage, taking half damage of this type.");
					}else if(poisonStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Poison damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Poison damage.");
					}
					details2 = "";
					details3 = "";
					//
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
					//
					if(acidStatus().equals("Immune")) {
						details = String.format("You are immune to Acid damage, taking no damage of this type.");
					}else if(acidStatus().equals("Resistant")) {
						details = String.format("You are resistant to Acid damage, taking half damage of this type.");
					}else if(acidStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Acid damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Acid damage.");
					}
					details2 = "";
					details3 = "";
					//
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
					//
					if(magicStatus().equals("Immune")) {
						details = String.format("You are immune to Magic damage, taking no damage of this type.");
					}else if(magicStatus().equals("Resistant")) {
						details = String.format("You are resistant to Magic damage, taking half damage of this type.");
					}else if(magicStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Magic damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Magic damage.");
					}
					details2 = "";
					details3 = "";
					//
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
					//
					if(chaosStatus().equals("Immune")) {
						details = String.format("You are immune to Chaos damage, taking no damage of this type.");
					}else if(chaosStatus().equals("Resistant")) {
						details = String.format("You are resistant to Chaos damage, taking half damage of this type.");
					}else if(chaosStatus().equals("Weakness")) {
						details = String.format("You are vulnerable to Chaos damage, taking double damage of this type.");
					}else {
						details = String.format("You are damaged normally by Chaos damage.");
					}
					details2 = "";
					details3 = "";
					//
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
        terminal.write(String.format("%sStrength: %d (%s)", strengthCheck, player.strength(), ExtraMaths.modifierToString(player.strengthModifier())), x, y++);
        terminal.write(String.format("%sDexterity: %d (%s)", dexterityCheck, player.dexterity(), ExtraMaths.modifierToString(player.dexterityModifier())), x, y++);
        terminal.write(String.format("%sIntelligence: %d (%s)", intelligenceCheck, player.intelligence(), ExtraMaths.modifierToString(player.intelligenceModifier())), x, y++);
        y++;
        terminal.write(String.format("%sArmor Class: %d", armorCheck, player.armorClass()), x, y++);
        terminal.write(String.format("%sProficiency Bonus: +%s", proficiencyCheck, player.proficiencyBonus()), x, y++);
        terminal.write(String.format("%sVision Radius: %d tiles", visionCheck, player.visionRadius()), x, y++);
        y++;
        terminal.write("== Skills ==", x2, y2++);
        for(int i = 0; i < 14; i++) {
        	if(cX == 1 && cY == i) {
        		terminal.write(String.format(">> %s", player.skills()[i].toStringCharacterSheet()), x2, y2++);
        		switch(i) {
        		case 0:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Simple Weapons I: You add your proficiency bonus (+%s) to attack rolls made with Simple Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Simple Weapons II: You add your proficiency bonus (+%s) to damage rolls made with Simple Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Simple Weapons III: Critical hits with Simple Weapons Paralyse the target for %s turns.", player.proficiencyBonus());
        			}
        			break;
        		case 1:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Martial Weapons I: You add your proficiency bonus (+%s) to attack rolls made with Martial Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Martial Weapons II: You add your proficiency bonus (+%s) to damage rolls made with Martial Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Martial Weapons III: Critical hits with Martial Weapons deal 3x damage (up from 2x).");
        			}
        			break;
        		case 2:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Armor Training I: You can equip Medium Armor and Shields.");
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Armor Training II: You can equip Heavy Armor and Tower Shields.");
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Armor Training III: You gain a bonus to your total Armor Class equal to your proficiency bonus (+%s).", player.proficiencyBonus());
        			}
        			break;
        		case 3:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Fortitude I: When starving, you take damage every %s turns instead of every turn.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Fortitude II: You add your proficiency bonus (+%s) to checks made to avoid negative effects from eating corpses.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Fortitude III: The duration of negative effects applied to you is halves.");
        			}
        			break;
        		case 4:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Finesse Weapons I: You add your proficiency bonus (+%s) to attack rolls made with Finesse Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Finesse Weapons II: You add your proficiency bonus (+%s) to damage rolls made with Finesse Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Finesse Weapons III: Critical hits with Finesse Weapons apply Bleeding to the target for %s turns.", player.proficiencyBonus());
        			}
        			break;
        		case 5:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Ranged Weapons I: You add your proficiency bonus (+%s) to attack rolls made with Ranged Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Ranged Weapons II: You add your proficiency bonus (+%s) to damage rolls made with Ranged Weapons.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Ranged Weapons III: Attacks with Ranged Weapons refund the spent ammunition upon the target's death.");
        			}
        			break;
        		case 6:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Stealth I: You add your proficiency bonus (+%s) to checks made to avoid waking sleeping monsters.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Stealth II: Whenever you Search, you expend %s Mana to become Invisible for %d turns.", player.proficiencyBonus(), player.proficiencyBonus()*2);
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Stealth III: Attacks you make whilst Invisible are always considered to be critical hits.", player.proficiencyBonus());
        			}
        			break;
        		case 7:
        			if(player.skills()[i].level() >= 1) {
        				details = String.format("Perception I: You add your proficiency bonus (+%s) to checks made to detect traps.", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 2) {
        				details2 = String.format("Perception II: You gain a bonus to your Vision Radius equal to your proficiency bonus (+%s).", player.proficiencyBonus());
        			}
        			if(player.skills()[i].level() >= 3) {
        				details3 = String.format("Perception III: When you trigger a revealed trap, you instead gain a positive effect based on the trap's type.", player.proficiencyBonus());
        			}
        			break;
        		default: details = ""; details2 = ""; details3 = ""; break;
        		}
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
        
        terminal.write(details, x, 31);
        terminal.write(details2, x, 32);
        terminal.write(details3, x, 33);
    
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
