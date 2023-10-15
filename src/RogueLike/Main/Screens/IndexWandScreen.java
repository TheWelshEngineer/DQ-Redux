package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import asciiPanel.AsciiPanel;

public class IndexWandScreen implements Screen{
	
	protected Creature player;
	protected ObjectFactory factory;
	
	public IndexWandScreen(Creature player, ObjectFactory factory) {
		this.player = player;
		this.factory = factory;
	}
	
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Index: Wands ==", 1);	
		int y = 3;
		for(int i = 0; i < 6; i++) {
			String wandColor = factory.wandIndex.get(i).getAppearance();
			String wandName = "???";
			if(player.nameOf(factory.wandIndex.get(i)) == factory.wandIndex.get(i).name()) {
				wandName = factory.wandIndex.get(i).name();
			}
			terminal.write(String.format("%s : %s", wandColor, wandName), 44, y++);
			
		}
		
        y++;
        y++;
        y++;

    
        terminal.writeCenter("-- [LEFT]: Scrolls | [RIGHT]: Rings | [ESCAPE]: Exit --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_RIGHT: return new IndexRingScreen(player, factory); 
        case KeyEvent.VK_LEFT: return new IndexScrollScreen(player, factory);
        case KeyEvent.VK_ESCAPE: return null;
		}
		return this;
	}

}
