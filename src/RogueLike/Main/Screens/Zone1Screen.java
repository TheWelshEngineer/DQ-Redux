package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Skill;
import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class Zone1Screen implements Screen{
	
	public String playerClass;
	public String playerName;
	public List<Integer> playerAbilities;
	public Skill[] playerSkills;
	 
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	

	public Zone1Screen(String playerClass, List<Integer> playerAbilities, Skill[] playerSkills, String playerName){
		this.playerClass = playerClass;
		this.playerName = playerName;
		this.playerAbilities = playerAbilities;
		this.playerSkills = playerSkills;
		
	}

	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		int y = 9;
		terminal.writeCenter("+||+ The Abandoned Depths +||+", y++);
		y++;
		terminal.writeCenter("Below the Dwarvern City lies a network of abandoned tunnels", y++);
		terminal.writeCenter("that were originally intended to serve as the start of a new", y++);
		terminal.writeCenter("mithril mine, but they were sealed off after the lower levels were", y++);
		terminal.writeCenter("discovered to be full of monsters. The Dwarvern King predicts that", y++);
		terminal.writeCenter("the ruffians who stole the Ancient Axe fled down into these tunnels..", y++);
		y++;
		terminal.writeCenter("Give chase, and recover the Axe!", y++);
		terminal.writeCenter("-- [ENTER]: Continue --", 40);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
			case KeybindManager.navigateMenuConfirm: 
				return new PlayScreen(playerClass, playerAbilities, playerSkills, playerName);
			default:
				return this;
		}
	}
}
