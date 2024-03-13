package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import asciiPanel.AsciiPanel;

public class IndexScrollScreen implements Screen{
	
	protected Creature player;
	protected ObjectFactory factory;
	
	public IndexScrollScreen(Creature player, ObjectFactory factory) {
		this.player = player;
		this.factory = factory;
	}
	
	@Override
	public void displayOutput(ExtendedAsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Index: Scrolls ==", 1);	
		int y = 3;
		for(int i = 0; i < 7; i++) {
			String scrollColor = factory.scrollIndex.get(i).getAppearance();
			String scrollName = "???";
			if(player.nameOf(factory.scrollIndex.get(i)) == factory.scrollIndex.get(i).name()) {
				scrollName = factory.scrollIndex.get(i).name();
			}
			terminal.write(String.format("%s : %s", scrollColor, scrollName), 42, y++);
			
		}
		
        y++;
        y++;
        y++;

    
        terminal.writeCenter("-- [LEFT]: Potions | [RIGHT]: Wands | [ESCAPE]: Exit --", 38);
		
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_RIGHT: return new IndexWandScreen(player, factory); 
        case KeyEvent.VK_LEFT: return new IndexPotionScreen(player, factory);
        case KeyEvent.VK_ESCAPE: return null;
		}
		return this;
	}

}

