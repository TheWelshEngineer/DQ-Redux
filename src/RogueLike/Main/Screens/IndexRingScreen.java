package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import asciiPanel.AsciiPanel;

public class IndexRingScreen implements Screen{
	
	protected Creature player;
	protected ObjectFactory factory;
	
	public IndexRingScreen(Creature player, ObjectFactory factory) {
		this.player = player;
		this.factory = factory;
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Index: Rings ==", 1);	
		int y = 3;
		for(int i = 0; i < 10; i++) {
			String ringColor = factory.ringIndex.get(i).getAppearance();
			String ringName = "???";
			if(player.nameOf(factory.ringIndex.get(i)) == factory.ringIndex.get(i).name()) {
				ringName = factory.ringIndex.get(i).name();
			}
			terminal.write(String.format("%s : %s", ringColor, ringName), 44, y++);
			
		}
		
        y++;
        y++;
        y++;

    
        terminal.writeCenter("-- [LEFT]: Wands | [RIGHT]: Potions | [ESCAPE]: Exit --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_RIGHT: return new IndexPotionScreen(player, factory); 
        case KeyEvent.VK_LEFT: return new IndexWandScreen(player, factory);
        case KeyEvent.VK_ESCAPE: return null;
		}
		return this;
	}

}
