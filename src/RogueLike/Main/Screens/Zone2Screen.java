package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class Zone2Screen implements Screen{
	
	public String playerClass;
	public String playerName;
	public List<Integer> playerAbilities;
	 
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	

	public Zone2Screen(){
		
	}

	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		int y = 9;
		terminal.writeCenter("+||+ The Caves of Chaos +||+", y++);
		y++;
		terminal.writeCenter("The monsters besieging the Abandoned Depths must have", y++);
		terminal.writeCenter("originated here - the Caves of Chaos. Things are never quite", y++);
		terminal.writeCenter("what they seem here, and danger lurks around every corner.", y++);
		terminal.writeCenter("Are you dwarf enough to fight your way through the darkness, and", y++);
		terminal.writeCenter("recover the Kingdom's most precious treasure?", y++);
		y++;
		terminal.writeCenter("Good luck, adventurer..", y++);
		terminal.writeCenter("-- [ENTER]: Continue --", 40);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
			case KeybindManager.navigateMenuConfirm: 
				return null;
			default:
				return this;
		}
	}
}
