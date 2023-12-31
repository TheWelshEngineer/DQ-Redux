package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class CreditsScreen implements Screen{

	public void displayOutput(AsciiPanel terminal) {
		int y = 1;
		terminal.writeCenter("+|=|+ Credits +|=|+", y++);
		y+=2;
		terminal.writeCenter("Design & Development:", y++);
		terminal.writeCenter("----------------", y++);
		terminal.writeCenter("Kathryn U.", y++);
		terminal.writeCenter("H. Roland R.", y++);
		y++;
		terminal.writeCenter("GitHub: TheWelshEngineer", y++);
		terminal.writeCenter("GitHub: RolandReff", y++);
		y+=3;
		terminal.writeCenter("Helper Method Library:", y++);
		terminal.writeCenter("----------------", y++);
		terminal.writeCenter("Ben T.", y++);
		y++;
		terminal.writeCenter("GitHub: BenTaylor25", y++);
		y+=3;
		terminal.writeCenter("AsciiPanel Framework & Initial Inspiration:", y++);
		terminal.writeCenter("----------------", y++);
		terminal.writeCenter("Trystan S.", y++);
		y++;
		terminal.writeCenter("GitHub: trystan", y++);
		y+=3;
		terminal.writeCenter("Code Review and GitHub Documentation:", y++);
		terminal.writeCenter("----------------", y++);
		terminal.writeCenter("H. Roland R.", y++);
		y++;
		terminal.writeCenter("GitHub: RolandReff", y++);
		y++;
		terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);

	}

	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeybindManager.navigateMenuBack? new MainMenuScreen() : this;
	}
	

}
