package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;

public class HelpScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}

	public void displayOutput(ExtendedAsciiPanel terminal) {
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Help Menu ==", 1);
		
		int y = 3;
		int x = 42;
		
        terminal.write(String.format("[%s]: Controls - Movement", KeybindManager.keybindText(KeybindManager.navigateMenuOption_1)), x, y++);
        terminal.write(String.format("[%s]: Controls - Interactions", KeybindManager.keybindText(KeybindManager.navigateMenuOption_2)), x, y++);
        terminal.write(String.format("[%s]: Systems - Status Effects", KeybindManager.keybindText(KeybindManager.navigateMenuOption_3)), x, y++);
        terminal.write(String.format("[%s]: Exit", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), x, y++);

	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeybindManager.navigateMenuOption_1: return new HelpMovementControlsScreen(fromMenu);
        case KeybindManager.navigateMenuOption_2: return new HelpInteractionControlsScreen(fromMenu);
        case KeybindManager.navigateMenuOption_3: return new HelpEffectsScreen(fromMenu);
        case KeybindManager.navigateMenuBack: 
        	if(this.fromMenu == true) {
        		return new MainMenuScreen();
        	}else {
        		return null;
        	}
		}
		return this;
	}
	
}
