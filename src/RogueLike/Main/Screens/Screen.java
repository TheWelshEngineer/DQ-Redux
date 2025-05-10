package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.MenuDecorationManager;

public interface Screen {
	
	public void displayOutput();
	
	public Screen respondToUserInput(KeyEvent key);
	
	public static void generateBorders() {
		for(int i = 0; i < ExtendedAsciiPanel.getWidthInCharacters(); i++) {
			ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderHorizontal, i, 0);
			ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderHorizontal, i, ExtendedAsciiPanel.getHeightInCharacters()-1);
		}
		for(int i = 0; i < ExtendedAsciiPanel.getHeightInCharacters(); i++) {
			ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderVertical, 0, i);
			ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderVertical, ExtendedAsciiPanel.getWidthInCharacters()-1, i);
		}
		ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderCornerNW, 0, 0);
		ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderCornerNE, ExtendedAsciiPanel.getWidthInCharacters()-1, 0);
		ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderCornerSW, 0, ExtendedAsciiPanel.getHeightInCharacters()-1);
		ExtendedAsciiPanel.write(MenuDecorationManager.menuBorderCornerSE, ExtendedAsciiPanel.getWidthInCharacters()-1, ExtendedAsciiPanel.getHeightInCharacters()-1);
	}

}
