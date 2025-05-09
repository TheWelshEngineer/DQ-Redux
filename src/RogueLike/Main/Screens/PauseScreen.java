package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.HelpScreens.HelpScreen;

public class PauseScreen implements Screen{
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public char saveLeft = '>';
	public char saveRight = '<';
	public char helpLeft = '>';
	public char helpRight = '<';
	public char closeLeft = '>';
	public char closeRight = '<';
	
	public void changeMarkers(int check) {
		if(check == 0) {
			saveLeft = '>';
			saveRight = '<';
			helpLeft = ' ';
			helpRight = ' ';
			closeLeft = ' ';
			closeRight = ' ';
		}else if(check == 1) {
			saveLeft = ' ';
			saveRight = ' ';
			helpLeft = '>';
			helpRight = '<';
			closeLeft = ' ';
			closeRight = ' ';
		}else if(check == 2) {
			saveLeft = ' ';
			saveRight = ' ';
			helpLeft = ' ';
			helpRight = ' ';
			closeLeft = '>';
			closeRight = '<';
		}
	}

	public void displayOutput() {
		changeMarkers(check);
		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		int y = 1;
		ExtendedAsciiPanel.writeCenter(" _____                      __  ____                  _   ", y++);
		ExtendedAsciiPanel.writeCenter("|  __ \\                    / _|/ __ \\                | |  ", y++);
		ExtendedAsciiPanel.writeCenter("| |  | |_      ____ _ _ __| |_| |  | |_   _  ___  ___| |_ ", y++);
		ExtendedAsciiPanel.writeCenter("| |  | \\ \\ /\\ / / _` | '__|  _| |  | | | | |/ _ \\/ __| __|", y++);
		ExtendedAsciiPanel.writeCenter("| |__| |\\ V  V / (_| | |  | | | |__| | |_| |  __/\\__ \\ |_ ", y++);
		ExtendedAsciiPanel.writeCenter("|_____/  \\_/\\_/ \\__,_|_|  |_|  \\___\\_\\\\__,_|\\___||___/\\__|", y++);
		y++;
		ExtendedAsciiPanel.writeCenter("+|=|+ Magic and Madness in the Caves of Chaos! +|=|+", y++);
		ExtendedAsciiPanel.writeCenter("== Pause Menu ==", y+=2);
		ExtendedAsciiPanel.write(String.format("%c  Save Game         %c", saveLeft, saveRight), 52, y+=2);
		ExtendedAsciiPanel.write(String.format("%c  Help              %c", helpLeft, helpRight), 52, y+=2);
		ExtendedAsciiPanel.write(String.format("%c  Quit to Desktop   %c", closeLeft, closeRight), 52, y+=2);

		ExtendedAsciiPanel.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 36);
		ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Return to Game --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 2;
			}else if(check == 1) {
				check = 0;
			}else if(check == 2) {
				check = 1;
			}
			return this;
			
		case KeybindManager.navigateMenuDown:
			if(check == 0) {
				check = 1;
			}else if(check == 1) {
				check = 2;
			}else if(check == 2) {
				check = 0;
			}
			return this;
			
		case KeybindManager.navigateMenuConfirm:
			if(check == 0) {
				System.out.println("Not yet implemented"); //TODO Save file generation and loading
				return this;
			}else if(check == 1) {
				return new HelpScreen(false);
			}else if(check == 2) {
				System.exit(0);
			}
		case KeybindManager.navigateMenuBack: return null;
		}
		
		return this;
		
		
	}
	

}
