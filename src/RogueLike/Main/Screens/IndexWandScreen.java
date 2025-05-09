package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;

public class IndexWandScreen implements Screen{
	
	protected Creature player;
	
	public IndexWandScreen(Creature player) {
		this.player = player;
	}
	
	@Override
	public void displayOutput() {
		ExtendedAsciiPanel.clear();
		ExtendedAsciiPanel.writeCenter("== Index: Wands ==", 1);	
		int y = 3;
		for(int i = 0; i < 6; i++) {
			String wandColor = FactoryManager.getObjectFactory().wandIndex.get(i).getAppearance();
			String wandName = "???";
			if(player.nameOf(FactoryManager.getObjectFactory().wandIndex.get(i)) == FactoryManager.getObjectFactory().wandIndex.get(i).name()) {
				wandName = FactoryManager.getObjectFactory().wandIndex.get(i).name();
			}
			ExtendedAsciiPanel.write(String.format("%s : %s", wandColor, wandName), 44, y++);
			
		}
		
        y++;
        y++;
        y++;

    
        ExtendedAsciiPanel.writeCenter("-- [LEFT]: Scrolls | [RIGHT]: Rings | [ESCAPE]: Exit --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_RIGHT: return new IndexRingScreen(player); 
        case KeyEvent.VK_LEFT: return new IndexScrollScreen(player);
        case KeyEvent.VK_ESCAPE: return null;
		}
		return this;
	}

}
