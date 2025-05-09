package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;

public class CreditsScreen implements Screen{

	public void displayOutput() {
		Screen.generateBorders();;
		int y = 1;
		ExtendedAsciiPanel.writeCenter("+|=|+ Credits +|=|+", y++);
		y+=2;
		ExtendedAsciiPanel.writeCenter("Design & Development:", y++);
		ExtendedAsciiPanel.writeCenter("----------------", y++);
		ExtendedAsciiPanel.writeCenter("Kathryn U.", y++);
		ExtendedAsciiPanel.writeCenter("H. Roland R.", y++);
		ExtendedAsciiPanel.writeCenter("Julia I.", y++);
		ExtendedAsciiPanel.writeCenter("Diamond H.", y++);
		y++;
		ExtendedAsciiPanel.writeCenter("GitHub: TheWelshEngineer", y++);
		ExtendedAsciiPanel.writeCenter("GitHub: RolandReff", y++);
		ExtendedAsciiPanel.writeCenter("GitHub: JuliaScythe", y++);
		ExtendedAsciiPanel.writeCenter("GitHub: diamond-deluxe", y++);
		y+=3;
		ExtendedAsciiPanel.writeCenter("AsciiPanel Framework & Initial Inspiration:", y++);
		ExtendedAsciiPanel.writeCenter("----------------", y++);
		ExtendedAsciiPanel.writeCenter("Trystan S.", y++);
		y++;
		ExtendedAsciiPanel.writeCenter("GitHub: trystan", y++);
		y+=3;
		ExtendedAsciiPanel.writeCenter("Code Review and GitHub Documentation:", y++);
		ExtendedAsciiPanel.writeCenter("----------------", y++);
		ExtendedAsciiPanel.writeCenter("H. Roland R.", y++);
		y++;
		ExtendedAsciiPanel.writeCenter("GitHub: RolandReff", y++);
		y++;
		ExtendedAsciiPanel.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);

	}

	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeybindManager.navigateMenuBack? new MainMenuScreen() : this;
	}
	

}
