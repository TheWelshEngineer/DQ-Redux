package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;

public class HelpMovementControlsScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpMovementControlsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput(ExtendedAsciiPanel terminal) {
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Help: Movement Controls ==", 1);
		
		int y = 3;
		int x = 42;
        terminal.write(String.format("[%s]: Move northwest", KeybindManager.keybindText(KeybindManager.movementNorthWest)), x, y++);
        terminal.write(String.format("[%s]: Move north", KeybindManager.keybindText(KeybindManager.movementNorth)), x, y++);
        terminal.write(String.format("[%s]: Move northeast", KeybindManager.keybindText(KeybindManager.movementNorthEast)), x, y++);
        terminal.write(String.format("[%s]: Move west", KeybindManager.keybindText(KeybindManager.movementWest)), x, y++);
        terminal.write(String.format("[%s]: Wait for a turn", KeybindManager.keybindText(KeybindManager.movementWait)), x, y++);
        terminal.write(String.format("[%s]: Move east", KeybindManager.keybindText(KeybindManager.movementEast)), x, y++);
        terminal.write(String.format("[%s]: Move southwest", KeybindManager.keybindText(KeybindManager.movementSouthWest)), x, y++);
        terminal.write(String.format("[%s]: Move south", KeybindManager.keybindText(KeybindManager.movementSouth)), x, y++);
        terminal.write(String.format("[%s]: Move southeast", KeybindManager.keybindText(KeybindManager.movementSouthEast)), x, y++);
        terminal.write(String.format("[%s]: Climb an upward staircase", KeybindManager.keybindText(KeybindManager.movementUpStairs)), x, y++);
        terminal.write(String.format("[%s]: Climb a downward staircase", KeybindManager.keybindText(KeybindManager.movementDownStairs)), x, y++);
        y++;
        terminal.writeCenter("Note: By default, Dwarf Quest must be played with [NUM LOCK] turned on!", y++);

    
        terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
