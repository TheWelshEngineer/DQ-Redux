package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Factories.ObjectFactory;

public class IndexScrollScreen implements Screen, Serializable{
	
	protected Creature player;
	protected ObjectFactory factory;
	
	public IndexScrollScreen(Creature player) {
		this.player = player;
	}
	
	@Override
	public void displayOutput() {
		ExtendedAsciiPanel.clear();
		ExtendedAsciiPanel.writeCenter("== Index: Scrolls ==", 1);	
		int y = 3;
		for(int i = 0; i < 7; i++) {
			String scrollColor = FactoryManager.getObjectFactory().scrollIndex.get(i).getAppearance();
			String scrollName = "???";
			if(player.nameOf(FactoryManager.getObjectFactory().scrollIndex.get(i)) == FactoryManager.getObjectFactory().scrollIndex.get(i).name()) {
				scrollName = FactoryManager.getObjectFactory().scrollIndex.get(i).name();
			}
			ExtendedAsciiPanel.write(String.format("%s : %s", scrollColor, scrollName), 42, y++);
			
		}
		
        y++;
        y++;
        y++;

    
        ExtendedAsciiPanel.writeCenter("-- [LEFT]: Potions | [RIGHT]: Wands | [ESCAPE]: Exit --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_RIGHT: return new IndexWandScreen(player); 
        case KeyEvent.VK_LEFT: return new IndexPotionScreen(player);
        case KeyEvent.VK_ESCAPE: return null;
		}
		return this;
	}

}

