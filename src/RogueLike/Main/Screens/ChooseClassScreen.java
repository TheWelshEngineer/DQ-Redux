package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.Enums.PlayerClass;

public class ChooseClassScreen implements Screen{
	
	public int check = 0;

	public void setCheck(int value) {
		check = value;
	}
	
	public ChooseClassScreen(PlayerAncestry playerAncestry) {
		this.playerAncestry = playerAncestry;
	}
	
	private final PlayerAncestry playerAncestry;

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
	public char mageLeft = '>';
	public char mageRight = '<';
	public char rangerLeft = '>';
	public char rangerRight = '<';
	public char witchLeft = '>';
	public char witchRight = '<';
	public char paladinLeft = '>';
	public char paladinRight = '<';
	public char monkLeft = '>';
	public char monkRight = '<';
	
	public void changeMarkers(int check) {
		if(check == 0) {
			warriorLeft = '>';
			warriorRight = '<';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			paladinLeft = ' ';
			paladinRight = ' ';
			monkLeft = ' ';
			monkRight = ' ';
		}else if(check == 1) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = '>';
			rogueRight = '<';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			paladinLeft = ' ';
			paladinRight = ' ';
			monkLeft = ' ';
			monkRight = ' ';
		}else if(check == 2) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = '>';
			mageRight = '<';
			rangerLeft = ' ';
			rangerRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			paladinLeft = ' ';
			paladinRight = ' ';
			monkLeft = ' ';
			monkRight = ' ';
		}else if(check == 3) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = '>';
			rangerRight = '<';
			witchLeft = ' ';
			witchRight = ' ';
			paladinLeft = ' ';
			paladinRight = ' ';
			monkLeft = ' ';
			monkRight = ' ';
		}else if(check == 4) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			witchLeft = '>';
			witchRight = '<';
			paladinLeft = ' ';
			paladinRight = ' ';
			monkLeft = ' ';
			monkRight = ' ';
		}else if(check == 5) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			paladinLeft = '>';
			paladinRight = '<';
			monkLeft = ' ';
			monkRight = ' ';
		}else if(check == 6) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
			witchLeft = ' ';
			witchRight = ' ';
			paladinLeft = ' ';
			paladinRight = ' ';
			monkLeft = '>';
			monkRight = '<';
		}
	}

	public void displayOutput() {
		changeMarkers(check);
		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		ExtendedAsciiPanel.writeCenter("== Select your Class ==", 1);	
		int y = 5;
		
		ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
		//ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Warrior %c    %c", borderVertical, warriorLeft, warriorRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Rogue   %c    %c", borderVertical, rogueLeft, rogueRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Mage    %c    %c", borderVertical, mageLeft, mageRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Ranger  %c    %c", borderVertical, rangerLeft, rangerRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Witch  %c     %c", borderVertical, witchLeft, witchRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Paladin  %c   %c", borderVertical, paladinLeft, paladinRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Monk  %c      %c", borderVertical, monkLeft, monkRight, borderVertical), 5, y++);
		//ExtendedAsciiPanel.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		
		y = 2;
		
		if(check == 0) {
			ExtendedAsciiPanel.write("+||+ Warrior +||+", 26, y+=4);
			y++;
			ExtendedAsciiPanel.write("Clad in steel plate and armed with mighty blades,", 31, y+=1);
			ExtendedAsciiPanel.write("warriors represent the archetypal adventurer for many.", 31, y+=1);
			ExtendedAsciiPanel.write("Their extensive training ensures that wherever", 31, y+=1);
			ExtendedAsciiPanel.write("a warrior goes, victory is sure to follow.", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Skills +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Martial Weapons (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Martial Weapons.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Armor Training (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You can equip Medium Armor and Shields.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ 2x Level I skills of your choice", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Equipment +||+",26,  y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Shortsword", 31, y+=1);
			ExtendedAsciiPanel.write("+ Chainmail Tunic", 31, y+=1);
			ExtendedAsciiPanel.write("+ Round Shield", 31, y+=1);
			ExtendedAsciiPanel.write("+ Ration of Food", 31, y+=1);
		}else if(check == 1) {
			ExtendedAsciiPanel.write("+||+ Rogue +||+", 26, y+=4);
			y++;
			ExtendedAsciiPanel.write("Cutthroats and scoundrels, rogues rarely fight", 31, y+=1);
			ExtendedAsciiPanel.write("on an even footing, prefering poisons, traps,", 31, y+=1);
			ExtendedAsciiPanel.write("and a knife in the dark. Cunning beyond measure,", 31, y+=1);
			ExtendedAsciiPanel.write("these heroes are always hiding a secret or two.", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Skills +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Finesse Weapons (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Finesse Weapons.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Stealth (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to checks made to avoid waking sleeping monsters.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ 2x Level I skills of your choice", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Equipment +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Dagger", 31, y+=1);
			ExtendedAsciiPanel.write("+ Padded Armor", 31, y+=1);
			ExtendedAsciiPanel.write("+ Potion of Invisibility", 31, y+=1);
			ExtendedAsciiPanel.write("+ Ration of Food", 31, y+=1);
		}else if(check == 2) {
			ExtendedAsciiPanel.write("+||+ Mage +||+", 26, y+=4);
			y++;
			ExtendedAsciiPanel.write("Gifted arcane scholars, mages possess a firm", 31, y+=1);
			ExtendedAsciiPanel.write("grasp of the fundamentals of magic, and the sheer", 31, y+=1);
			ExtendedAsciiPanel.write("power they can unleash ensures that many will", 31, y+=1);
			ExtendedAsciiPanel.write("never even need to swing their weapons at all.", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Skills +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Evocation (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Evocation spells.", 33, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to the duration of Evocation spell effects.", 33, y+=1);
			ExtendedAsciiPanel.write("+ Pyromancy (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Pyromancy spells.", 33, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to the duration of Pyromancy spell effects.", 33, y+=1);
			ExtendedAsciiPanel.write("+ 2x Level I skills of your choice", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Equipment +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Padded Armor", 31, y+=1);
			ExtendedAsciiPanel.write("+ Wand of Magic Missile", 31, y+=1);
			ExtendedAsciiPanel.write("+ Wand of Firebolt", 31, y+=1);
			ExtendedAsciiPanel.write("+ Ration of Food", 31, y+=1);
		}else if(check == 3) {
			ExtendedAsciiPanel.write("+||+ Ranger +||+", 26, y+=4);
			y++;
			ExtendedAsciiPanel.write("Wandering the outskirts of civilisation, rangers are masters", 31, y+=1);
			ExtendedAsciiPanel.write("of survival, hunting, and bushcraft. Posessed of keen eyesight", 31, y+=1);
			ExtendedAsciiPanel.write("and keener aim, many threats have been ended by rangers", 31, y+=1);
			ExtendedAsciiPanel.write("without the knowledge of the villages they protect.", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Skills +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Ranged Weapons (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Ranged Weapons.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Perception (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to checks made to detect traps.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ 2x Level I skills of your choice", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Equipment +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Club", 31, y+=1);
			ExtendedAsciiPanel.write("+ Padded Armor", 31, y+=1);
			ExtendedAsciiPanel.write("+ Shortbow", 31, y+=1);
			ExtendedAsciiPanel.write("+ 20x Arrows", 31, y+=1);
			ExtendedAsciiPanel.write("+ Ration of Food", 31, y+=1);
		}else if(check == 4) {
			ExtendedAsciiPanel.write("+||+ Witch +||+", 26, y+=4);
			y++;
			ExtendedAsciiPanel.write("Mysterious magic-wielders often working in the", 31, y+=1);
			ExtendedAsciiPanel.write("shadows, witches trade the arcane studies undertaken", 31, y+=1);
			ExtendedAsciiPanel.write("by traditional mages for a more hands-on approach.", 31, y+=1);
			ExtendedAsciiPanel.write("Still, power must always come at a price..", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Skills +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Finesse Weapons (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Finesse Weapons.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Alchemancy (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Alchemancy spells.", 33, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to the duration of Alchemancy spell effects.", 33, y+=1);
			ExtendedAsciiPanel.write("+ 2x Level I skills of your choice", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Equipment +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Dagger", 31, y+=1);
			ExtendedAsciiPanel.write("+ Padded Armor", 31, y+=1);
			ExtendedAsciiPanel.write("+ Wand of Acid Blast", 31, y+=1);
			ExtendedAsciiPanel.write("+ Ration of Food", 31, y+=1);
		}else if(check == 5) {
			ExtendedAsciiPanel.write("+||+ Paladin +||+", 26, y+=4);
			y++;
			ExtendedAsciiPanel.write("Devout warriors with deep connections to", 31, y+=1);
			ExtendedAsciiPanel.write("their weapons, paladins swear mighty oaths", 31, y+=1);
			ExtendedAsciiPanel.write("before each quest. Every victory deepens their", 31, y+=1);
			ExtendedAsciiPanel.write("bond, their conviction, and their bravery.", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Skills +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Armor Training (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You can equip Medium Armor and Shields.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Ferromancy (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Ferromancy spells.", 33, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to the duration of Ferromancy spell effects.", 33, y+=1);
			ExtendedAsciiPanel.write("+ 2x Level I skills of your choice", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Equipment +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Shortsword", 31, y+=1);
			ExtendedAsciiPanel.write("+ Chainmail Tunic", 31, y+=1);
			ExtendedAsciiPanel.write("+ Wand of Blad's Ward", 31, y+=1);
			ExtendedAsciiPanel.write("+ Ration of Food", 31, y+=1);
		}else if(check == 6) {
			ExtendedAsciiPanel.write("+||+ Monk +||+", 26, y+=4);
			y++;
			ExtendedAsciiPanel.write("Warrior monks posessed of an incredible fighting", 31, y+=1);
			ExtendedAsciiPanel.write("spirit, these adventurers thrive in the toughest", 31, y+=1);
			ExtendedAsciiPanel.write("of conditions, seeing each new trial as a way", 31, y+=1);
			ExtendedAsciiPanel.write("to further strengthen their faith and resolve.", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Skills +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Simple Weapons (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- You add your proficiency bonus to attack rolls made with Simple Weapons.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Fortitude (I)", 31, y+=1);
			ExtendedAsciiPanel.write("- When starving, you take damage at a reduced rate.", 33, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ 2x Level I skills of your choice", 31, y+=1);
			y++;
			ExtendedAsciiPanel.write("+||+ Starting Equipment +||+", 26, y+=1);
			y++;
			ExtendedAsciiPanel.write("+ Club", 31, y+=1);
			ExtendedAsciiPanel.write("+ Padded Armor", 31, y+=1);
			ExtendedAsciiPanel.write("+ Potion of Mind Vision", 31, y+=1);
			ExtendedAsciiPanel.write("+ Ration of Food", 31, y+=1);
		}

		ExtendedAsciiPanel.writeCenter("-- [UP / DOWN]: Move Selection | [ENTER]: Confirm and Continue --", 36);
		ExtendedAsciiPanel.writeCenter("-- [ESCAPE]: Return to Main Menu --", 38);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = 6;
			}else {
				check--;
			}
			return this;
			
		case KeyEvent.VK_DOWN:
			if(check == 6) {
				check = 0;
			}else {
				check++;
			}
			return this;
			
		case KeyEvent.VK_ENTER:
			if(check == 0) {
				return new ChooseAbilityScreen(PlayerClass.WARRIOR, playerAncestry);
			}else if(check == 1) {
				return new ChooseAbilityScreen(PlayerClass.ROGUE, playerAncestry);
			}else if(check == 2) {
				return new ChooseAbilityScreen(PlayerClass.MAGE, playerAncestry);
			}else if(check == 3) {
				return new ChooseAbilityScreen(PlayerClass.RANGER, playerAncestry);
			}else if(check == 4) {
				return new ChooseAbilityScreen(PlayerClass.WITCH, playerAncestry);
			}else if(check == 5) {
				return new ChooseAbilityScreen(PlayerClass.PALADIN, playerAncestry);
			}else if(check == 6) {
				return new ChooseAbilityScreen(PlayerClass.MONK, playerAncestry);
			}
		case KeyEvent.VK_ESCAPE: return new ChooseAncestryScreen();
		}
		
		return this;
		
		
	}
}
