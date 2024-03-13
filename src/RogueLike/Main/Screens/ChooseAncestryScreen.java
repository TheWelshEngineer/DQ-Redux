package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

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
		terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		
		y = 3;
		if(check == 0) {
			terminal.writeCenter("+||+ Human +||+", y+=4);
			y++;
			terminal.writeCenter("One or more of your parents was a human, and you", y+=1);
			terminal.writeCenter("likely grew up in a port or along a trade route.", y+=1);
			terminal.writeCenter("As such, you pick up new skills as quickly as gossip.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Ancestry Trait +||+", y+=1);
			y++;
			terminal.writeCenter("Jack of All Trades: You begin your adventure with an extra Skill Point.", y+=1);
			y++;
		}else if(check == 1) {
			terminal.writeCenter("+||+ Elf +||+", y+=4);
			y++;
			terminal.writeCenter("One or more of your parents was an elf, and as you grew", y+=1);
			terminal.writeCenter("up, you were tutored in magic as is the elven tradition.", y+=1);
			terminal.writeCenter("As such, you find it easier to tap into nearby leylines.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Ancestry Trait +||+", y+=1);
			y++;
			terminal.writeCenter("Arcane Academia: You gain a greater bonus to your maximum", y+=1);
			terminal.writeCenter("Mana Points from your Intelligence score.", y+=1);
			y++;
		}else if(check == 2) {
			terminal.writeCenter("+||+ Dwarf +||+", y+=4);
			y++;
			terminal.writeCenter("One or more of your parents was a dwarf, and you", y+=1);
			terminal.writeCenter("inherited their rocky constitution. Literally - your body", y+=1);
			terminal.writeCenter("is covered in iridescent bismuth outcroppings.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Ancestry Trait +||+", y+=1);
			y++;
			terminal.writeCenter("Bismuth Brawn: Your Armour Class increases by 1, and", y+=1);
			terminal.writeCenter("you are Resistant to Poison damage.", y+=1);
			y++;
		}else if(check == 3) {
			terminal.writeCenter("+||+ Orc +||+", y+=4);
			y++;
			terminal.writeCenter("One or more of your parents was an orc, and you", y+=1);
			terminal.writeCenter("inherited their hearty appetite and secondary stomach.", y+=1);
			terminal.writeCenter("There's nothing you won't dig your tusks into at least once!", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Ancestry Trait +||+", y+=1);
			y++;
			terminal.writeCenter("Iron Stomach: You recover a small portion of Health Points", y+=1);
			terminal.writeCenter("whenever you eat food. The recovered HP increases based on", y+=1);
			terminal.writeCenter("the quality of the consumed food item.", y+=1);
			y++;
		}else  if(check == 4) {
			terminal.writeCenter("+||+ Dragonborn +||+", y+=4);
			y++;
			terminal.writeCenter("One or more of your parents was a dragonborn, or was", y+=1);
			terminal.writeCenter("blessed by a dragon before your birth. You are covered in", y+=1);
			terminal.writeCenter("tough scales, and possess an affinity for fire magic.", y+=1);
			y++;
			y++;
			terminal.writeCenter("+||+ Ancestry Trait +||+", y+=1);
			y++;
			terminal.writeCenter("Draconic Descendant: You are resistant to Fire damage, and begin", y+=1);
			terminal.writeCenter("your quest with a Wand of Firebolt.", y+=1);
			y++;
		}

		terminal.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 40);
		terminal.writeCenter(String.format("-- [%s]: Return to Main Menu --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 42);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 4;
			}else{
				check--;
			}
			return this;
			
		case KeybindManager.navigateMenuDown:
			if(check == 4) {
				check = 0;
			}else{
				check++;
			}
			return this;
			
		case KeybindManager.navigateMenuConfirm:
			if(check == 0) {
				return new ChooseClassScreen("Human");
			}else if(check == 1) {
				return new ChooseClassScreen("Elf");
			}else if(check == 2) {
				return new ChooseClassScreen("Dwarf");
			}else if(check == 3) {
				return new ChooseClassScreen("Orc");
			}else if(check == 4) {
				return new ChooseClassScreen("Dragonborn");
			}
			
		case KeybindManager.navigateMenuBack: return new MainMenuScreen();
		}
		
		return this;
		
		
	}
	

}


