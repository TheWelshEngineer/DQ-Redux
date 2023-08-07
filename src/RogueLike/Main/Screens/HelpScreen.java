package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class HelpScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}

	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Help Menu ==", 1);
		
		int y = 3;
		int x = 42;
		
        terminal.write("[1]: Controls - Movement", x, y++);
        terminal.write("[2]: Controls - Interactions", x, y++);
        terminal.write("[3]: Systems - Status Effects", x, y++);
        terminal.write("[ESCAPE]: Exit help menu", x, y++);

	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_1: return new HelpControlsScreen(fromMenu);
        case KeyEvent.VK_2: return new HelpInteractionsScreen(fromMenu);
        case KeyEvent.VK_3: return new HelpEffectsScreen(fromMenu);
        case KeyEvent.VK_ESCAPE: 
        	if(this.fromMenu == true) {
        		return new MainMenuScreen();
        	}else {
        		return null;
        	}
		}
		return this;
	}
	
}
