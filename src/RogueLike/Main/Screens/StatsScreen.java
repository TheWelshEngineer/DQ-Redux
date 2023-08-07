package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Creature;
import RogueLike.Main.Effect;
import asciiPanel.AsciiPanel;

public class StatsScreen implements Screen{
	
	protected Creature player;
	protected List<Effect> effects;
	
	public StatsScreen(Creature player) {
		this.player = player;
		this.effects = player.effects();
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Stats ==", 1);	
		int y = 3;
		terminal.writeCenter(String.format("%s", player.playerName()), y++);
		y++;
		terminal.writeCenter(String.format("Level %d %s", player.level(), player.playerClass()), y++);
        terminal.writeCenter(String.format("Health: %3d/%d", player.hp(), player.maxHP()), y++);
        terminal.writeCenter(String.format("Mana: %3d/%d", player.mana(), player.maxMana()), y++);
        terminal.writeCenter(String.format("Hunger: %3d/%d", player.food(), player.maxFood()), y++);
        terminal.writeCenter(String.format("Armor Class: %2d", player.armorClass()), y++);
        terminal.writeCenter(String.format("Strength: %2d%2s%d%s", player.strength(), "(", player.strengthModifier(), ")"), y++);
        terminal.writeCenter(String.format("Dexterity: %2d%2s%d%s", player.dexterity(), "(", player.dexterityModifier(), ")"), y++);
        terminal.writeCenter(String.format("Intelligence: %2d%2s%d%s", player.intelligence(), "(", player.intelligenceModifier(), ")"), y++);
        terminal.writeCenter(String.format("Vision Radius: %3d %s", player.visionRadius(), "tiles"), y++);
        y++;
        y++;
        y++;
        terminal.writeCenter("== Status Effects ==", y++);
        y++;
        for(Effect effect : effects) {
        	if(effect.name() != null) {
        		String turns = "turn";
        		if(effect.duration() > 1) {
        			turns = "turns";
        		}
        		char effectIcon = (char)30;
        		if(effect.isNegative() > 0) {
        			effectIcon = (char)31;
        		}
        		terminal.writeCenter(String.format("%c %s: %3d %s remaining", effectIcon, effect.name(), effect.duration(), turns), y++);
        	}
		}
    
        terminal.writeCenter("-- [ESCAPE]: Return to Game --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		/*case KeyEvent.VK_LEFT:
			return new SkillsScreen(player);
		case KeyEvent.VK_RIGHT:
			return new SkillsScreen(player);*/
		case KeyEvent.VK_ESCAPE:
			return null;
		default: return this;
		}
	}

}
