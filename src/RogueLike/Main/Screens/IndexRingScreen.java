package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Factories.ObjectFactory;

public class IndexRingScreen implements Screen{
	
	protected Creature player;
	protected ObjectFactory factory;
	
	public IndexRingScreen(Creature player) {
		this.player = player;
	}
	
	@Override
	public void displayOutput() {
		ExtendedAsciiPanel.clear();
		ExtendedAsciiPanel.writeCenter("== Index: Rings ==", 1);	
		int y = 3;
		for(int i = 0; i < 10; i++) {
			String ringColor = FactoryManager.getObjectFactory().ringIndex.get(i).getAppearance();
			String ringName = "???";
			if(player.nameOf(FactoryManager.getObjectFactory().ringIndex.get(i)) == FactoryManager.getObjectFactory().ringIndex.get(i).name()) {
				ringName = FactoryManager.getObjectFactory().ringIndex.get(i).name();
			}
			ExtendedAsciiPanel.write(String.format("%s : %s", ringColor, ringName), 44, y++);
			
		}
		
        y++;
        y++;
        y++;

    
        ExtendedAsciiPanel.writeCenter("-- [LEFT]: Wands | [RIGHT]: Potions | [ESCAPE]: Exit --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_RIGHT: return new IndexPotionScreen(player); 
        case KeyEvent.VK_LEFT: return new IndexWandScreen(player);
        case KeyEvent.VK_ESCAPE: return null;
		}
		return this;
	}

}
