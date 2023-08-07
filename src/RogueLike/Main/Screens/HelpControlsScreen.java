package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class HelpControlsScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpControlsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Help: Movement Controls ==", 1);
		
		int y = 3;
		int x = 42;
        terminal.write("[NUM 7]: Move northwest", x, y++);
        terminal.write("[NUM 8]: Move north", x, y++);
        terminal.write("[NUM 9]: Move northeast", x, y++);
        terminal.write("[NUM 4]: Move west", x, y++);
        terminal.write("[NUM 5]: Wait", x, y++);
        terminal.write("[NUM 6]: Move east", x, y++);
        terminal.write("[NUM 1]: Move southwest", x, y++);
        terminal.write("[NUM 2]: Move south", x, y++);
        terminal.write("[NUM 3]: Move southeast", x, y++);
        terminal.write("[UP]: Climb an upward staircase", x, y++);
        terminal.write("[DOWN]: Climb a downward staircase", x, y++);
        y++;
        terminal.writeCenter("Note: Dwarf Quest must be played with [NUM LOCK] turned on!", y++);

    
        terminal.writeCenter("-- [ESCAPE]: Back --", 38);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_ESCAPE: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
