package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class ChooseClassScreen implements Screen{
	
	public int check = 0;

	public void setCheck(int value) {
		check = value;
	}
	
	public ChooseClassScreen(/*String playerSpecies*/) {
		//setSpecies(playerSpecies);
	}
	
	/*public String playerSpecies;
	public void setSpecies(String species) {
		playerSpecies = species;
	}*/
	
	public char borderVertical = 186;
	public char borderHorizontal = 205;
	public char borderCorner = 206;
	public char borderCornerNW = 201;
	public char borderCornerNE = 187;
	public char borderCornerSW = 200;
	public char borderCornerSE = 188;
	
	public char warriorLeft = '>';
	public char warriorRight = '<';
	public char rogueLeft = '>';
	public char rogueRight = '<';
	public char wizardLeft = '>';
	public char wizardRight = '<';
	public char barbarianLeft = '>';
	public char barbarianRight = '<';
	public char alchemistLeft = '>';
	public char alchemistRight = '<';
	public char sorcererLeft = '>';
	public char sorcererRight = '<';
	public char rangerLeft = '>';
	public char rangerRight = '<';
	public char witchLeft = '>';
	public char witchRight = '<';
	public char evokerLeft = '>';
	public char evokerRight = '<';
	
	public void changeMarkers(int check) {
		if(check == 5) {
			alchemistLeft = '>';
			alchemistRight = '<';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 1) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = '>';
			barbarianRight = '<';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 7) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = '>';
			witchRight = '<';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 2) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = '>';
			evokerRight = '<';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 4) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = '>';
			rangerRight = '<';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 3) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = '>';
			rogueRight = '<';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 8) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = '>';
			sorcererRight = '<';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 0) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = '>';
			warriorRight = '<';
			wizardLeft = ' ';
			wizardRight = ' ';
		}else if(check == 6) {
			alchemistLeft = ' ';
			alchemistRight = ' ';
			barbarianLeft = ' ';
			barbarianRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			evokerLeft = ' ';
			evokerRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			sorcererLeft = ' ';
			sorcererRight = ' ';
			warriorLeft = ' ';
			warriorRight = ' ';
			wizardLeft = '>';
			wizardRight = '<';
		}
	}

	public void displayOutput(AsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		terminal.writeCenter("== Select your Class ==", 1);	
		int y = 5;
		
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
		terminal.write(String.format("%c %c Warrior %c    %c", borderVertical, warriorLeft, warriorRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Barbarian %c  %c", borderVertical, barbarianLeft, barbarianRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Evoker %c     %c", borderVertical, evokerLeft, evokerRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Rogue %c      %c", borderVertical, rogueLeft, rogueRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Ranger %c     %c", borderVertical, rangerLeft, rangerRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Alchemist %c  %c", borderVertical, alchemistLeft, alchemistRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Wizard %c     %c", borderVertical, wizardLeft, wizardRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Witch %c      %c", borderVertical, witchLeft, witchRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Sorcerer %c   %c", borderVertical, sorcererLeft, sorcererRight, borderVertical), 5, y++);
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		
		y = 3;
		
		if(check == 5) {
			terminal.writeCenter("+||+ Alchemist +||+", y+=4);
			y++;
			terminal.writeCenter("Alchemists are arcane scientists, masters of", y+=1);
			terminal.writeCenter("potions and elixirs. Their bodies warped by years", y+=1);
			terminal.writeCenter("of self-experimentation, alchemists often boast an", y+=1);
			terminal.writeCenter("uncanny resistance to the chemicals they create.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Alchemancy", y+=1);
			terminal.writeCenter("+1 Perception", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Dagger", y+=1);
			terminal.writeCenter("+ Leather Armor", y+=1);
			terminal.writeCenter("+ Potion of Poison", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Reactive Reagents", y+=1);
			terminal.writeCenter("Simple Weapon Training", y+=1);
			terminal.writeCenter("Finesse Weapon Training", y+=1);
			terminal.writeCenter("Alchemancy Training", y+=1);
		}else if(check == 1) {
			terminal.writeCenter("+||+ Barbarian +||+", y+=4);
			y++;
			terminal.writeCenter("Brutal, primal warriors, barbarians might lack the", y+=1);
			terminal.writeCenter("formal training of warriors and rangers, but their", y+=1);
			terminal.writeCenter("sheer strength and unquenchable fury ensure that", y+=1);
			terminal.writeCenter("these heroes are almost always the last ones standing.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Simple Weapons", y+=1);
			terminal.writeCenter("+1 Martial Weapons", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Battleaxe", y+=1);
			terminal.writeCenter("+ Leather Armor", y+=1);
			terminal.writeCenter("+ Round Shield", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Battle Rage", y+=1);
			terminal.writeCenter("Simple Weapon Training", y+=1);
			terminal.writeCenter("Martial Weapon Training", y+=1);
			terminal.writeCenter("Light Armor Training", y+=1);
			terminal.writeCenter("Medium Armor Training", y+=1);
		}else if(check == 7) {
			terminal.writeCenter("+||+ Witch +||+", y+=4);
			y++;
			terminal.writeCenter("Graceful yet eerie, witches draw their power from", y+=1);
			terminal.writeCenter("the whispers of the Dead Gods, rewriting the very", y+=1);
			terminal.writeCenter("laws of reality to confuse and harrow their foes.", y+=1);
			terminal.writeCenter("'When a witch smiles, start praying.' - Unknown", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Cryomancy", y+=1);
			terminal.writeCenter("+1 Electromancy", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Dagger", y+=1);
			terminal.writeCenter("+ Padded Armor", y+=1);
			//terminal.writeCenter("+ Wand of Magic Missile", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Inspire Terror", y+=1);
			terminal.writeCenter("Cryomancy Training", y+=1);
			terminal.writeCenter("Electromancy Training", y+=1);
			terminal.writeCenter("Keen Eyesight", y+=1);
		}else if(check == 2) {
			terminal.writeCenter("+||+ Evoker +||+", y+=4);
			y++;
			terminal.writeCenter("Sometimes known as 'battlemages', evokers draw", y+=1);
			terminal.writeCenter("wild magic into their bodies, becoming nigh", y+=1);
			terminal.writeCenter("unstoppable forces of nature in battle and", y+=1);
			terminal.writeCenter("inspiring their allies to even greater heights.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Evocation", y+=1);
			terminal.writeCenter("+1 Martial Weapons", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Warhammer", y+=1);
			terminal.writeCenter("+ Studded Leather Armor", y+=1);
			terminal.writeCenter("+ Round Shield", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Arcane Armaments", y+=1);
			terminal.writeCenter("Simple Weapon Training", y+=1);
			terminal.writeCenter("Martial Weapon Training", y+=1);
			terminal.writeCenter("Light Armor Training", y+=1);
			terminal.writeCenter("Evocation Training", y+=1);
		}else if(check == 4) {
			terminal.writeCenter("+||+ Ranger +||+", y+=4);
			y++;
			terminal.writeCenter("Found at the very edges of civilisation, rangers", y+=1);
			terminal.writeCenter("prowl the wilds to ensure the safety of their homes.", y+=1);
			terminal.writeCenter("Each and every one a gifted archer, these heroes", y+=1);
			terminal.writeCenter("fight a silent war against the Nightmare Court.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Ranged Weapons", y+=1);
			terminal.writeCenter("+1 Perception", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Longbow", y+=1);
			terminal.writeCenter("+ Leather Armor", y+=1);
			terminal.writeCenter("+ 20 Arrows", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Steady Aim", y+=1);
			terminal.writeCenter("Simple Weapon Training", y+=1);
			terminal.writeCenter("Ranged Weapon Training", y+=1);
			terminal.writeCenter("Light Armor Training", y+=1);
			terminal.writeCenter("Keen Eyesight", y+=1);
		}else if(check == 3) {
			terminal.writeCenter("+||+ Rogue +||+", y+=4);
			y++;
			terminal.writeCenter("Cutthroats and scoundrels, rogues rarely fight", y+=1);
			terminal.writeCenter("on an even footing, prefering poisons, traps,", y+=1);
			terminal.writeCenter("and a knife in the dark. Cunning beyond measure,", y+=1);
			terminal.writeCenter("these heroes are always hiding a secret or two.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Finesse Weapons", y+=1);
			terminal.writeCenter("+1 Stealth", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Dagger", y+=1);
			terminal.writeCenter("+ Padded Armor", y+=1);
			terminal.writeCenter("+ Potion of Invisibility", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Thief's Wit", y+=1);
			terminal.writeCenter("Finesse Weapon Training", y+=1);
			terminal.writeCenter("Ranged Weapon Training", y+=1);
			terminal.writeCenter("Light Armor Training", y+=1);
			terminal.writeCenter("Silent Step", y+=1);
		}else if(check == 8) {
			terminal.writeCenter("+||+ Sorcerer +||+", y+=4);
			y++;
			terminal.writeCenter("Inheritors of the same dark magic as the", y+=1);
			terminal.writeCenter("Nightmare Court, sorcerers use the power of chaos as", y+=1);
			terminal.writeCenter("a force for good. Unstable, explosive, and unpredictable,", y+=1);
			terminal.writeCenter("their magic makes for quite the spectacle.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Pyromancy", y+=1);
			terminal.writeCenter("+1 Simple Weapons", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Handaxe", y+=1);
			terminal.writeCenter("+ Padded Armor", y+=1);
			//terminal.writeCenter("+ Wand of Magic Missile", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Unstable Arcana", y+=1);
			terminal.writeCenter("Simple Weapon Training", y+=1);
			terminal.writeCenter("Pyromancy Training", y+=1);
			terminal.writeCenter("Simple Weapon Combo: Fire", y+=1);
		}else if(check == 0) {
			terminal.writeCenter("+||+ Warrior +||+", y+=4);
			y++;
			terminal.writeCenter("Clad in steel plate and armed with mighty blades,", y+=1);
			terminal.writeCenter("warriors represent the archetypal adventurer for many.", y+=1);
			terminal.writeCenter("Their extensive training ensures that wherever", y+=1);
			terminal.writeCenter("a warrior goes, victory is sure to follow.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Martial Weapons", y+=1);
			terminal.writeCenter("+1 Fortitude", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Longsword", y+=1);
			terminal.writeCenter("+ Chainmail Tunic", y+=1);
			terminal.writeCenter("+ Kite Shield", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Extra Attack", y+=1);
			terminal.writeCenter("Simple Weapon Training", y+=1);
			terminal.writeCenter("Martial Weapon Training", y+=1);
			terminal.writeCenter("Light Armor Training", y+=1);
			terminal.writeCenter("Medium Armor Training", y+=1);
		}else if(check == 6) {
			terminal.writeCenter("+||+ Wizard +||+", y+=4);
			y++;
			terminal.writeCenter("Gifted arcane scholars, wizards possess a firm", y+=1);
			terminal.writeCenter("grasp of the fundamentals of magic, and the sheer", y+=1);
			terminal.writeCenter("power they can unleash ensures that many will", y+=1);
			terminal.writeCenter("never even need to swing their weapons at all.", y+=1);
			y++;
			terminal.writeCenter("+||+ Skill Bonuses +||+", y+=1);
			y++;
			terminal.writeCenter("+1 Evocation", y+=1);
			terminal.writeCenter("+1 Perception", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Club", y+=1);
			terminal.writeCenter("+ Padded Armor", y+=1);
			//terminal.writeCenter("+ Wand of Magic Missile", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
			y++;
			terminal.writeCenter("+||+ Feats +||+", y+=1);
			y++;
			terminal.writeCenter("Master of Magic", y+=1);
			terminal.writeCenter("Evocation Training", y+=1);
			terminal.writeCenter("Pyromancy Training", y+=1);
			terminal.writeCenter("Cryomancy Training", y+=1);
		}

		terminal.writeCenter("-- [UP / DOWN]: Move Selection | [ENTER]: Confirm and Continue --", 36);
		terminal.writeCenter("-- [ESCAPE]: Return to Main Menu --", 38);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = 8;
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
				check = 0;
			}
			return this;
			
		case KeyEvent.VK_ENTER:
			if(check == 0) {
				return new ChooseAbilityScreen("Warrior");
			}else if(check == 1) {
				return new ChooseAbilityScreen("Barbarian");
			}else if(check == 2) {
				return new ChooseAbilityScreen("Evoker");
			}else if(check == 3) {
				return new ChooseAbilityScreen("Rogue");
			}else if(check == 4) {
				return new ChooseAbilityScreen("Ranger");
			}else if(check == 5) {
				return new ChooseAbilityScreen("Alchemist");
			}else if(check == 6) {
				return new ChooseAbilityScreen("Wizard");
			}else if(check == 7) {
				return new ChooseAbilityScreen("Witch");
			}else if(check == 8) {
				return new ChooseAbilityScreen("Sorcerer");
			}
		case KeyEvent.VK_ESCAPE: return new MainMenuScreen();
		}
		
		return this;
		
		
	}
	

}
