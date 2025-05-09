package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.LevelUpController;
import RogueLike.Main.Creatures.Creature;

public class LevelUpScreen implements Screen{
	private LevelUpController controller;
	private Creature player;
	private int picks;
	
	public LevelUpScreen(Creature player, int picks) {
		this.controller = new LevelUpController();
		this.player = player;
		this.picks = picks;
	}
	
	@Override
	public void displayOutput() {
		List<String> options = controller.getLevelUpOptions();
		
		int y = 24;
		ExtendedAsciiPanel.clear(' ', 1, y, 30, options.size()+2);
		ExtendedAsciiPanel.write("Choose a levelup bonus:", 1, y++);
		ExtendedAsciiPanel.write("------------------------------", 1, y++);
		
		for(int i = 0; i < options.size(); i++) {
			ExtendedAsciiPanel.write(String.format("[%d] %s", i+1, options.get(i)), 1, y++);
		}
		y++;
		String points = "point";
		if(player.attributePoints() > 1) {
			points = "points";
		}
		ExtendedAsciiPanel.write(String.format("%d skill %s remaining", player.attributePoints(), points), 1, y++);
	}
	
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		List<String> options = controller.getLevelUpOptions();
		String chars = "";
		
		for(int i = 0; i < options.size(); i++) {
			chars = chars + Integer.toString(i+1);
		}
		
		int i = chars.indexOf(key.getKeyChar());
		
		if(i < 0) {
			return this;
		}
		
		controller.getLevelUpOption(options.get(i)).invoke(player);
		player.modifyAttributePoints(-1);
		
		if(--picks < 1) {
			return null;
		}else {
			return this;
		}
	}

}
