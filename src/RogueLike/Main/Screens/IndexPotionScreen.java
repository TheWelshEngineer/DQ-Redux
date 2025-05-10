package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Factories.ObjectFactory;

public class IndexPotionScreen implements Screen, Serializable{
	
	protected Creature player;
	protected ObjectFactory factory;
	
	public IndexPotionScreen(Creature player) {
		this.player = player;
	}
	
	@Override
	public void displayOutput() {
		ExtendedAsciiPanel.clear();
		ExtendedAsciiPanel.writeCenter("== Index: Potions ==", 1);	
		int y = 3;
		for(int i = 0; i < 12; i++) {
			String potionColor = FactoryManager.getObjectFactory().potionIndex.get(i).getAppearance();
			String potionName = "???";
			if(player.nameOf(FactoryManager.getObjectFactory().potionIndex.get(i)) == FactoryManager.getObjectFactory().potionIndex.get(i).name()) {
				potionName = FactoryManager.getObjectFactory().potionIndex.get(i).name();
			}
			ExtendedAsciiPanel.write(String.format("%s : %s", potionColor, potionName), 44, y++);
			
		}
		
        y++;
        y++;
        y++;

    
        ExtendedAsciiPanel.writeCenter("-- [LEFT]: Rings | [RIGHT]: Scrolls | [ESCAPE]: Exit --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_RIGHT: return new IndexScrollScreen(player); 
        case KeyEvent.VK_LEFT: return new IndexRingScreen(player);
        case KeyEvent.VK_ESCAPE: return null;
		}
		return this;
		
	}

}
