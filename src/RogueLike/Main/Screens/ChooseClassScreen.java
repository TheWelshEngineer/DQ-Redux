package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.Enums.PlayerClass;
import RogueLike.Main.ExtendedAsciiPanel;

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
		}else if(check == 1) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = '>';
			rogueRight = '<';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = ' ';
			rangerRight = ' ';
		}else if(check == 2) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = '>';
			mageRight = '<';
			rangerLeft = ' ';
			rangerRight = ' ';
		}else if(check == 3) {
			warriorLeft = ' ';
			warriorRight = ' ';
			rogueLeft = ' ';
			rogueRight = ' ';
			mageLeft = ' ';
			mageRight = ' ';
			rangerLeft = '>';
			rangerRight = '<';
		}
	}

	public void displayOutput(ExtendedAsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Select your Class ==", 1);	
		int y = 5;
		
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
		//terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Warrior %c    %c", borderVertical, warriorLeft, warriorRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Rogue   %c    %c", borderVertical, rogueLeft, rogueRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Mage    %c    %c", borderVertical, mageLeft, mageRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Ranger  %c    %c", borderVertical, rangerLeft, rangerRight, borderVertical), 5, y++);
		//terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		
		y = 3;
		
		if(check == 0) {
			terminal.writeCenter("+||+ Warrior +||+", y+=4);
			y++;
			terminal.writeCenter("Clad in steel plate and armed with mighty blades,", y+=1);
			terminal.writeCenter("warriors represent the archetypal adventurer for many.", y+=1);
			terminal.writeCenter("Their extensive training ensures that wherever", y+=1);
			terminal.writeCenter("a warrior goes, victory is sure to follow.", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Skills +||+", y+=1);
			y++;
			terminal.writeCenter("+ Martial Weapons (I)", y+=1);
			terminal.writeCenter("+ Armor Training (I)", y+=1);
			terminal.writeCenter("+ 2x Level I skills of your choice", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Longsword", y+=1);
			terminal.writeCenter("+ Chainmail Tunic", y+=1);
			terminal.writeCenter("+ Kite Shield", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
		}else if(check == 1) {
			terminal.writeCenter("+||+ Rogue +||+", y+=4);
			y++;
			terminal.writeCenter("Cutthroats and scoundrels, rogues rarely fight", y+=1);
			terminal.writeCenter("on an even footing, prefering poisons, traps,", y+=1);
			terminal.writeCenter("and a knife in the dark. Cunning beyond measure,", y+=1);
			terminal.writeCenter("these heroes are always hiding a secret or two.", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Skills +||+", y+=1);
			y++;
			terminal.writeCenter("+ Finesse Weapons (I)", y+=1);
			terminal.writeCenter("+ Stealth (I)", y+=1);
			terminal.writeCenter("+ 2x Level I skills of your choice", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Dagger", y+=1);
			terminal.writeCenter("+ Padded Armor", y+=1);
			terminal.writeCenter("+ Potion of Invisibility", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
		}else if(check == 2) {
			terminal.writeCenter("+||+ Mage +||+", y+=4);
			y++;
			terminal.writeCenter("Gifted arcane scholars, magess possess a firm", y+=1);
			terminal.writeCenter("grasp of the fundamentals of magic, and the sheer", y+=1);
			terminal.writeCenter("power they can unleash ensures that many will", y+=1);
			terminal.writeCenter("never even need to swing their weapons at all.", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Skills +||+", y+=1);
			y++;
			terminal.writeCenter("+ Evocation (I)", y+=1);
			terminal.writeCenter("+ Simple Weapons (I)", y+=1);
			terminal.writeCenter("+ 2x Level I skills of your choice", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Club", y+=1);
			terminal.writeCenter("+ Padded Armor", y+=1);
			terminal.writeCenter("+ Wand of Magic Missile", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
		}else if(check == 3) {
			terminal.writeCenter("+||+ Ranger +||+", y+=4);
			y++;
			terminal.writeCenter("Wandering the outskirts of civilisation, rangers", y+=1);
			terminal.writeCenter("are masters of survival, hunting, and bushcraft.", y+=1);
			terminal.writeCenter("Possessed of keen eyesight and keener aim,", y+=1);
			terminal.writeCenter("many threats have been ended by rangers without", y+=1);
			terminal.writeCenter("the knowledge of the villages they protect.", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Skills +||+", y+=1);
			y++;
			terminal.writeCenter("+ Ranged Weapons (I)", y+=1);
			terminal.writeCenter("+ Perception (I)", y+=1);
			terminal.writeCenter("+ 2x Level I skills of your choice", y+=1);
			y++;
			terminal.writeCenter("+||+ Starting Equipment +||+", y+=1);
			y++;
			terminal.writeCenter("+ Club", y+=1);
			terminal.writeCenter("+ Padded Armor", y+=1);
			terminal.writeCenter("+ Shortbow", y+=1);
			terminal.writeCenter("+ 20x Arrows", y+=1);
			terminal.writeCenter("+ Ration of Food", y+=1);
		}

		terminal.writeCenter("-- [UP / DOWN]: Move Selection | [ENTER]: Confirm and Continue --", 36);
		terminal.writeCenter("-- [ESCAPE]: Return to Main Menu --", 38);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = 3;
			}else if(check == 1) {
				check = 0;
			}else if(check == 2) {
				check = 1;
			}else if(check == 3) {
				check = 2;
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
				check = 0;
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
			}
		case KeyEvent.VK_ESCAPE: return new ChooseAncestryScreen();
		}
		
		return this;
		
		
	}
	

}
