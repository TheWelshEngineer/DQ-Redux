package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.HelpScreens.HelpScreen;

public class MainMenuScreen implements Screen{
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public char startLeft = '>';
	public char startRight = '<';
	public char helpLeft = '>';
	public char helpRight = '<';
	public char creditsLeft = '>';
	public char creditsRight = '<';
	
	public void changeMarkers(int check) {
		if(check == 0) {
			startLeft = '>';
			startRight = '<';
			helpLeft = ' ';
			helpRight = ' ';
			creditsLeft = ' ';
			creditsRight = ' ';
		}else if(check == 1) {
			startLeft = ' ';
			startRight = ' ';
			helpLeft = '>';
			helpRight = '<';
			creditsLeft = ' ';
			creditsRight = ' ';
		}else if(check == 2) {
			startLeft = ' ';
			startRight = ' ';
			helpLeft = ' ';
			helpRight = ' ';
			creditsLeft = '>';
			creditsRight = '<';
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
		ExtendedAsciiPanel.writeCenter("== Main Menu ==", y+=2);
		ExtendedAsciiPanel.write(String.format("%c  New Game  %c", startLeft, startRight), 52, y+=2);
		ExtendedAsciiPanel.write(String.format("%c  Help      %c", helpLeft, helpRight), 52, y+=2);
		ExtendedAsciiPanel.write(String.format("%c  Credits   %c", creditsLeft, creditsRight), 52, y+=2);

		ExtendedAsciiPanel.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s]: Confirm and Continue --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 36);
		ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Return to Title Screen --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
	}

	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if (check == 0) {
				check = 2;
			} else {
				check = (check - 1) % 3;
			}
			return this;
			
		case KeybindManager.navigateMenuDown:
			check = (check + 1) % 3;
			return this;
			
		case KeybindManager.navigateMenuConfirm:
			if(check == 0) {
				return new ChooseAncestryScreen();
			}else if(check == 1) {
				return new HelpScreen(true);
			}else if(check == 2) {
				return new CreditsScreen();
			}
		case KeybindManager.navigateMenuBack: return new TitleScreen();
		}
		
		return this;
		
		
	}
	

}
