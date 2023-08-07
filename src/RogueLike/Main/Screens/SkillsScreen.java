/*package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Creature;
import RogueLike.Main.Effect;
import asciiPanel.AsciiPanel;

public class SkillsScreen implements Screen{
	
	protected Creature player;
	protected List<Effect> effects;
	
	public SkillsScreen(Creature player) {
		this.player = player;
		this.effects = player.effects();
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Skills ==", 1);	
		int y = 3;
		terminal.writeCenter(String.format("Simple Weapons: %d", player.skillSimpleWeapons()), y++);
		terminal.writeCenter(String.format("Martial Weapons: %d", player.skillMartialWeapons()), y++);
		terminal.writeCenter(String.format("Finesse Weapons: %d", player.skillFinesseWeapons()), y++);
		terminal.writeCenter(String.format("Ranged Weapons: %d", player.skillRangedWeapons()), y++);
		terminal.writeCenter(String.format("Fortitude: %d", player.skillFortitude()), y++);
		terminal.writeCenter(String.format("Perception: %d", player.skillPerception()), y++);
		terminal.writeCenter(String.format("Stealth: %d", player.skillStealth()), y++);
		terminal.writeCenter(String.format("Evocation: %d", player.skillEvocation()), y++);
		terminal.writeCenter(String.format("Pyromancy: %d", player.skillPyromancy()), y++);
		terminal.writeCenter(String.format("Cryomancy: %d", player.skillCryomancy()), y++);
		terminal.writeCenter(String.format("Electromancy: %d", player.skillElectromancy()), y++);
		terminal.writeCenter(String.format("Alchemancy: %d", player.skillAlchemancy()), y++);
        y++;
        y++;
        y++;

    
        terminal.writeCenter("-- [LEFT / RIGHT]: Stats | [ESCAPE]: Return to Game --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			return new StatsScreen(player);
		case KeyEvent.VK_RIGHT:
			return new StatsScreen(player);
		case KeyEvent.VK_ESCAPE:
			return null;
		default: return this;
		}
	}

}*/