package RogueLike.Main.Screens.HelpScreens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.Screen;

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
        
        terminal.write(String.format("[%s]: Move/attack north", KeybindManager.keybindText(KeybindManager.movementNorth)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Move/attack east", KeybindManager.keybindText(KeybindManager.movementEast)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Move/attack south", KeybindManager.keybindText(KeybindManager.movementSouth)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Move/attack west", KeybindManager.keybindText(KeybindManager.movementWest)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Move/attack northeast", KeybindManager.keybindText(KeybindManager.movementNorthEast)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Move/attack northwest", KeybindManager.keybindText(KeybindManager.movementNorthWest)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Move/attack southeast", KeybindManager.keybindText(KeybindManager.movementSouthEast)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Move/attack southwest", KeybindManager.keybindText(KeybindManager.movementSouthWest)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Wait for a turn", KeybindManager.keybindText(KeybindManager.movementWait)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Climb an upward staircase", KeybindManager.keybindText(KeybindManager.movementUpStairs)), x, y++);
        y++;
        terminal.write(String.format("[%s]: Climb a downward staircase", KeybindManager.keybindText(KeybindManager.movementDownStairs)), x, y++);
        y++;
        

        terminal.writeCenter("Note: By default, Dwarf Quest must be played with [NUM LOCK] turned on!", 36);
        terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
