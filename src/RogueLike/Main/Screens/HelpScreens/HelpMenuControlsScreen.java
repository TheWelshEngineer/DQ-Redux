package RogueLike.Main.Screens.HelpScreens;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.Screen;

public class HelpMenuControlsScreen implements Screen, Serializable{
	
	private boolean fromMenu;
	
	public HelpMenuControlsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput() {
		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		ExtendedAsciiPanel.writeCenter("== Help: Menu Controls ==", 1);
		
		int y = 3;
		int x = 12;
		ExtendedAsciiPanel.write(String.format("[%s]: Confirm a choice in a menu", KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), x, y++);
		y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Exit the current menu, or return to a previous one", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Move the menu cursor up", KeybindManager.keybindText(KeybindManager.navigateMenuUp)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Move the menu cursor down", KeybindManager.keybindText(KeybindManager.navigateMenuDown)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Move the menu cursor left", KeybindManager.keybindText(KeybindManager.navigateMenuLeft)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Move the menu cursor right", KeybindManager.keybindText(KeybindManager.navigateMenuRight)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Sort the options in a menu", KeybindManager.keybindText(KeybindManager.navigateMenuSort)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Toggle between two menu options", KeybindManager.keybindText(KeybindManager.navigateMenuToggle)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select a special function in a menu", KeybindManager.keybindText(KeybindManager.navigateMenuFunction_1)), x, y++);
        y++;
        y = 3;
        x = 77;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 1 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_1)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 2 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_2)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 3 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_3)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 4 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_4)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 5 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_5)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 6 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_6)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 7 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_7)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 8 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_8)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Select option 9 from a menu", KeybindManager.keybindText(KeybindManager.navigateMenuOption_9)), x, y++);
        y++;
        ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
