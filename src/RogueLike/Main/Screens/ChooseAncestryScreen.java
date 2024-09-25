package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;

public class ChooseAncestryScreen implements Screen{
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public char humanLeft = '>';
	public char humanRight = '<';
	public char elfLeft = '>';
	public char elfRight = '<';
	public char dwarfLeft = '>';
	public char dwarfRight = '<';
	public char orcLeft = '>';
	public char orcRight = '<';
	public char dragonbornLeft = '>';
	public char dragonbornRight = '<';
	public char lycanLeft = '>';
	public char lycanRight = '<';
	
	public char borderVertical = 186;
	public char borderHorizontal = 205;
	public char borderCorner = 206;
	public char borderCornerNW = 201;
	public char borderCornerNE = 187;
	public char borderCornerSW = 200;
	public char borderCornerSE = 188;
	
	public void changeMarkers(int check) {
		if(check == 0) {
			humanLeft = '>';
			humanRight = '<';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
		}else if(check == 1) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = '>';
			elfRight = '<';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
		}else if(check == 2) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = '>';
			dwarfRight = '<';
			orcLeft = ' ';
			orcRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
		}else if(check == 3) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = '>';
			orcRight = '<';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			lycanLeft = ' ';
			lycanRight = ' ';
		}else if(check == 4) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			dragonbornLeft = '>';
			dragonbornRight = '<';
			lycanLeft = ' ';
			lycanRight = ' ';
		}else if(check == 5) {
			humanLeft = ' ';
			humanRight = ' ';
			elfLeft = ' ';
			elfRight = ' ';
			dwarfLeft = ' ';
			dwarfRight = ' ';
			orcLeft = ' ';
			orcRight = ' ';
			dragonbornLeft = ' ';
			dragonbornRight = ' ';
			lycanLeft = '>';
			lycanRight = '<';
		}
	}

	public void displayOutput(ExtendedAsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Choose your Ancestry ==", 1);	
		int y = 5;
		
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
		terminal.write(String.format("%c %c Human %c      %c", borderVertical, humanLeft, humanRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Elf %c        %c", borderVertical, elfLeft, elfRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Dwarf %c      %c", borderVertical, dwarfLeft, dwarfRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Orc %c        %c", borderVertical, orcLeft, orcRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Dragonborn %c %c", borderVertical, dragonbornLeft, dragonbornRight, borderVertical), 5, y++);
		terminal.write(String.format("%c                %c", borderVertical, borderVertical), 5, y++);
		terminal.write(String.format("%c %c Lycan %c      %c", borderVertical, lycanLeft, lycanRight, borderVertical), 5, y++);
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		
		y = 2;
		if(check == 0) {
			terminal.write("+||+ Human +||+", 26, y+=4);
			y++;
			terminal.write("One or more of your parents was a human, and you", 31, y+=1);
			terminal.write("likely grew up in a port or along a trade route.", 31, y+=1);
			terminal.write("As such, you pick up new skills as quickly as gossip.", 31, y+=1);
			y++;
			y++;
			terminal.write("+||+ Ancestry Trait: Jack of All Trades +||+", 26, y+=1);
			y++;
			terminal.write("You begin your adventure with an extra Skill Point.", 31, y+=1);
			y++;
		}else if(check == 1) {
			terminal.write("+||+ Elf +||+", 26, y+=4);
			y++;
			terminal.write("One or more of your parents was an elf, and as you grew", 31, y+=1);
			terminal.write("up, you were tutored in magic as is the elven tradition.", 31, y+=1);
			terminal.write("As such, you find it easier to tap into nearby leylines.", 31, y+=1);
			y++;
			y++;
			terminal.write("+||+ Ancestry Trait: Arcane Academia +||+", 26, y+=1);
			y++;
			terminal.write("You gain a greater bonus to your maximum", 31, y+=1);
			terminal.write("Mana Points from your Intelligence score.", 31, y+=1);
			y++;
		}else if(check == 2) {
			terminal.write("+||+ Dwarf +||+", 26, y+=4);
			y++;
			terminal.write("One or more of your parents was a dwarf, and you", 31, y+=1);
			terminal.write("inherited their rocky constitution. Literally - your body", 31, y+=1);
			terminal.write("is covered in iridescent bismuth outcroppings.", 31, y+=1);
			y++;
			y++;
			terminal.write("+||+ Ancestry Trait: Bismuth Brawn +||+", 26, y+=1);
			y++;
			terminal.write("Your natural Armour Class increases by 1, and", 31, y+=1);
			terminal.write("you are Resistant to Poison damage.", 31, y+=1);
			y++;
		}else if(check == 3) {
			terminal.write("+||+ Orc +||+", 26, y+=4);
			y++;
			terminal.write("One or more of your parents was an orc, and you", 31, y+=1);
			terminal.write("inherited their hearty appetite and secondary stomach.", 31, y+=1);
			terminal.write("There's nothing you won't dig your tusks into at least once!", 31, y+=1);
			y++;
			y++;
			terminal.write("+||+ Ancestry Trait: Iron Stomach +||+", 26, y+=1);
			y++;
			terminal.write("You recover a small portion of Health Points whenever", 31, y+=1);
			terminal.write("you eat food. The recovered HP increases based on", 31, y+=1);
			terminal.write("the quality of the consumed food item.", 31, y+=1);
			y++;
		}else if(check == 4) {
			terminal.write("+||+ Dragonborn +||+", 26, y+=4);
			y++;
			terminal.write("One or more of your parents was a dragonborn, or was", 31, y+=1);
			terminal.write("blessed by a dragon before your birth. You are covered in", 31, y+=1);
			terminal.write("tough scales, and possess an affinity for fire magic.", 31, y+=1);
			y++;
			y++;
			terminal.write("+||+ Ancestry Trait: Draconic Descendant +||+", 26, y+=1);
			y++;
			terminal.write("You are resistant to Fire damage, and begin", 31, y+=1);
			terminal.write("your adventure with a Wand of Dragon's Breath.", 31, y+=1);
			y++;
		}else if(check == 5) {
			terminal.write("+||+ Lycan +||+", 26, y+=4);
			y++;
			terminal.write("One or more of your parents was a lycan, or you were", 31, y+=1);
			terminal.write("born on the night of the harvest moon. You are able to", 31, y+=1);
			terminal.write("shift between your lycan and werewolf forms at will.", 31, y+=1);
			y++;
			y++;
			terminal.write("+||+ Ancestry Trait: Wild Fury +||+", 26, y+=1);
			y++;
			terminal.write("Whenever you drop below 50% of your maximum HP,", 31, y+=1);
			terminal.write("you gain the Beast Form effect for a short duration.", 31, y+=1);
			y++;
		}

		terminal.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 40);
		terminal.writeCenter(String.format("-- [%s]: Return to Main Menu --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 42);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 5;
			}else{
				check--;
			}
			return this;
			
		case KeybindManager.navigateMenuDown:
			if(check == 5) {
				check = 0;
			}else{
				check++;
			}
			return this;
			
		case KeybindManager.navigateMenuConfirm:
			if(check == 0) {
				return new ChooseClassScreen(PlayerAncestry.HUMAN);
			}else if(check == 1) {
				return new ChooseClassScreen(PlayerAncestry.ELF);
			}else if(check == 2) {
				return new ChooseClassScreen(PlayerAncestry.DWARF);
			}else if(check == 3) {
				return new ChooseClassScreen(PlayerAncestry.ORC);
			}else if(check == 4) {
				return new ChooseClassScreen(PlayerAncestry.DRAGONBORN);
			}else if(check == 5) {
				return new ChooseClassScreen(PlayerAncestry.LYCAN);
			}
			
		case KeybindManager.navigateMenuBack: return new MainMenuScreen();
		}
		
		return this;
		
		
	}
}


