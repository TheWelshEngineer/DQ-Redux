package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Skill;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Utils.PlayerBuildDetails;

public class Zone1Screen implements Screen{
	
	public final PlayerBuildDetails playerDetails;
	 
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	

	public Zone1Screen(PlayerBuildDetails playerDetails){
		this.playerDetails = playerDetails;
	}

	public void displayOutput(ExtendedAsciiPanel terminal) {
		terminal.clear();
		int y = 9;
		terminal.writeCenter("+||+ The Abandoned Depths +||+", y++);
		y++;
		terminal.writeCenter("Below the Dwarvern City lies a network of abandoned tunnels", y++);
		terminal.writeCenter("that were originally intended to serve as the start of a new", y++);
		terminal.writeCenter("mithril mine, but they were sealed off after the lower levels were", y++);
		terminal.writeCenter("discovered to be full of monsters. Eitak, the Dwarvern King predicts that", y++);
		terminal.writeCenter("the ruffians who stole the Ancient Axe fled down into these tunnels..", y++);
		y++;
		terminal.writeCenter("Give chase, and recover the Axe!", y++);
		terminal.writeCenter("-- [ENTER]: Continue --", 40);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
			case KeybindManager.navigateMenuConfirm: 
				return new GameplayScreen(this.playerDetails);
			default:
				return this;
		}
	}
}
