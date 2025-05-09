package RogueLike.Main.Screens.HelpScreens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.Screen;

public class HelpItemsScreen implements Screen{
	
	private boolean fromMenu;
	
	private int check = 0;
	
	public char borderVertical = 186;
	public char borderHorizontal = 205;
	public char borderCorner = 206;
	public char borderCornerNW = 201;
	public char borderCornerNE = 187;
	public char borderCornerSW = 200;
	public char borderCornerSE = 188;
	
	public char foodLeft = '>';
	public char foodRight = '<';
	public char potionLeft = '>';
	public char potionRight = '<';
	public char weaponLeft = '>';
	public char weaponRight = '<';
	public char armorLeft = '>';
	public char armorRight = '<';
	public char shieldLeft = '>';
	public char shieldRight = '<';
	public char ringLeft = '>';
	public char ringRight = '<';
	public char ammoLeft = '>';
	public char ammoRight = '<';
	public char wandLeft = '>';
	public char wandRight = '<';
	public char scrollLeft = '>';
	public char scrollRight = '<';
	

	
	public void changeMarkers(int check) {
		if(check == 0) {
			foodLeft = '>';
			foodRight = '<';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 1) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = '>';
			potionRight = '<';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 2) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = '>';
			weaponRight = '<';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 3) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = '>';
			armorRight = '<';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 4) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = '>';
			shieldRight = '<';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 5) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = '>';
			ringRight = '<';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 6) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = '>';
			ammoRight = '<';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 7) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = '>';
			wandRight = '<';
			scrollLeft = ' ';
			scrollRight = ' ';
		}else if(check == 8) {
			foodLeft = ' ';
			foodRight = ' ';
			potionLeft = ' ';
			potionRight = ' ';
			weaponLeft = ' ';
			weaponRight = ' ';
			armorLeft = ' ';
			armorRight = ' ';
			shieldLeft = ' ';
			shieldRight = ' ';
			ringLeft = ' ';
			ringRight = ' ';
			ammoLeft = ' ';
			ammoRight = ' ';
			wandLeft = ' ';
			wandRight = ' ';
			scrollLeft = '>';
			scrollRight = '<';
		}
	}
	
	
	public HelpItemsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput() {
		changeMarkers(check);
		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		ExtendedAsciiPanel.writeCenter("== Help: Items ==", 1);
		
		int y = 3;
		
		ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Food %c           %c", borderVertical, foodLeft, foodRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Potions %c        %c", borderVertical, potionLeft, potionRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Weapons %c        %c", borderVertical, weaponLeft, weaponRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Armor %c          %c", borderVertical, armorLeft, armorRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Shields %c        %c", borderVertical, shieldLeft, shieldRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Rings %c          %c", borderVertical, ringLeft, ringRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Ammunition %c     %c", borderVertical, ammoLeft, ammoRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Wands %c          %c", borderVertical, wandLeft, wandRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c %c Scrolls %c        %c", borderVertical, scrollLeft, scrollRight, borderVertical), 5, y++);
		ExtendedAsciiPanel.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		
		y = 0;
		y+=4;
		if(check == 0) {
			ExtendedAsciiPanel.write("+||+ Food +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Food keeps you alive! Every action you take in the Caves of Chaos", 34, y++);
			ExtendedAsciiPanel.write("will make you hungrier, so keep an eye out for rations and pasties.", 34, y++);
			ExtendedAsciiPanel.write("If you're feeling brave, of course, certain other items are edible..", 34, y++);
		}else if(check == 1) {
			ExtendedAsciiPanel.write("+||+ Potions +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Potions can have a variety of effects. Drink positive potions for", 34, y++);
			ExtendedAsciiPanel.write("an advantage during your quest, or throw negative potions at your foes", 34, y++);
			ExtendedAsciiPanel.write("to even the odds during a fight! The only problem is, most potions are", 34, y++);
			ExtendedAsciiPanel.write("unidentified.. A Scroll of Identify will help you, but if you want to", 34, y++);
			ExtendedAsciiPanel.write("take a risk, you could always just drink one and find out?", 34, y++);
		}else if(check == 2) {
			ExtendedAsciiPanel.write("+||+ Weapons +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("You'll need a weapon to help you survive the Caves of Chaos! There are", 34, y++);
			ExtendedAsciiPanel.write("three types of melee weapon (Simple, Martial, and Finesse), as well as", 34, y++);
			ExtendedAsciiPanel.write("Ranged weapons, so there's plenty of variety. Weapons are explained in", 34, y++);
			ExtendedAsciiPanel.write("greater detail in the Equipment section of the Help menu.", 34, y++);
		}else if(check == 3) {
			ExtendedAsciiPanel.write("+||+ Armor +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Armor is your best bet at surviving the multitude of monsters you'll", 34, y++);
			ExtendedAsciiPanel.write("encounter on your quest. It comes in Light, Medium, and Heavy varieties,", 34, y++);
			ExtendedAsciiPanel.write("each suited for different styles of play. Armor is explained in greater", 34, y++);
			ExtendedAsciiPanel.write("detail in the Equipment section of the Help menu.", 34, y++);
		}else if(check == 4) {
			ExtendedAsciiPanel.write("+||+ Shields +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Just like armor, shields will give you a fighting chance at surviving", 34, y++);
			ExtendedAsciiPanel.write("the Caves of Chaos. They come in Round, Kite, and Tower varieties, each", 34, y++);
			ExtendedAsciiPanel.write("increasing your defence by different amounts. Shields are explained in", 34, y++);
			ExtendedAsciiPanel.write("greater detail in the Equipment section of the Help menu.", 34, y++);
		}else if(check == 5) {
			ExtendedAsciiPanel.write("+||+ Rings +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Rings are magic items which provide a variety of benefits, from increased", 34, y++);
			ExtendedAsciiPanel.write("vision radius to resistance to acid. At first, however, you won't know what", 34, y++);
			ExtendedAsciiPanel.write("your ring does - you'll need to spend some time with it equipped or use a", 34, y++);
			ExtendedAsciiPanel.write("Scroll of Identify. Rings are explained in greater detail in the Equipment", 34, y++);
			ExtendedAsciiPanel.write("section of the Help menu.", 34, y++);
		}else if(check == 6) {
			ExtendedAsciiPanel.write("+||+ Ammunition +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Ranged weapons can strike your enemies from a distance, but they need", 34, y++);
			ExtendedAsciiPanel.write("ammunition to do it. From simple arrows and crossbow bolts to black powder", 34, y++);
			ExtendedAsciiPanel.write("charges, you'll need to keep an eye out for resupplies whilst you're on your", 34, y++);
			ExtendedAsciiPanel.write("quest. Ammunition is explained in greater detail in the Equipment section of", 34, y++);
			ExtendedAsciiPanel.write("the Help menu.", 34, y++);
		}else if(check == 7) {
			ExtendedAsciiPanel.write("+||+ Wands +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Wands are magical items that allow you to cast various spells using your Mana,", 34, y++);
			ExtendedAsciiPanel.write("There are six schools of magic each with a variety of spells, allowing you to bring", 34, y++);
			ExtendedAsciiPanel.write("an entirely new approach to combat. Wands are explained in greater detail in the", 34, y++);
			ExtendedAsciiPanel.write("Equipment section of the Help menu.", 34, y++);
		}else if(check == 7) {
			ExtendedAsciiPanel.write("+||+ Scrolls +||+", 29, y++);
			y++;
			ExtendedAsciiPanel.write("Scrolls are magical items that allow you to cast various powerful spells, ranging", 34, y++);
			ExtendedAsciiPanel.write("from upgrading or enchanting your equipment to instantly learning the layout of an", 34, y++);
			ExtendedAsciiPanel.write("entire level of the Caves. The magic contained within a scroll is unstable, however,", 34, y++);
			ExtendedAsciiPanel.write("so the scroll is destroyed after it is used. Scrolls will provide incredible benefits", 34, y++);
			ExtendedAsciiPanel.write("throughout your quest, so it's wise to seek them out!", 34, y++);
		}
			
		

        ExtendedAsciiPanel.writeCenter(String.format("-- [%s / %s]: Move Selection --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown)), 34);
        ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
        
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 8;
			}else {
				check--;
			}
			break;
			
		case KeybindManager.navigateMenuDown:
			if(check == 8) {
				check = 0;
			}else {
				check++;
			}
			break;
		
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}

