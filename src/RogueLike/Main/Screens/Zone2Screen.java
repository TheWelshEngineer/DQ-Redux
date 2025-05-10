package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.List;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;

public class Zone2Screen implements Screen, Serializable{
	
	public String playerClass;
	public String playerName;
	public List<Integer> playerAbilities;
	 
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	

	public Zone2Screen(){
		
	}

	public void displayOutput() {
		ExtendedAsciiPanel.clear();
		int y = 9;
		ExtendedAsciiPanel.writeCenter("+||+ The Caves of Chaos +||+", y++);
		y++;
		ExtendedAsciiPanel.writeCenter("The monsters besieging the Abandoned Depths must have", y++);
		ExtendedAsciiPanel.writeCenter("originated here - the Caves of Chaos. Things are never quite", y++);
		ExtendedAsciiPanel.writeCenter("what they seem here, and danger lurks around every corner.", y++);
		ExtendedAsciiPanel.writeCenter("Are you dwarf enough to fight your way through the darkness, and", y++);
		ExtendedAsciiPanel.writeCenter("recover the Kingdom's most precious treasure?", y++);
		y++;
		ExtendedAsciiPanel.writeCenter("Good luck, adventurer..", y++);
		ExtendedAsciiPanel.writeCenter("-- [ENTER]: Continue --", 40);
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
