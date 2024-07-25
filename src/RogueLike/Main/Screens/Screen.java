package RogueLike.Main.Screens;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.MenuDecorationManager;

import java.awt.event.KeyEvent;

public interface Screen {

    public void displayOutput(ExtendedAsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);

    public static void generateBorders(ExtendedAsciiPanel terminal) {
        for (int i = 0; i < terminal.getWidthInCharacters(); i++) {
            terminal.write(MenuDecorationManager.menuBorderHorizontal, i, 0);
            terminal.write(
                    MenuDecorationManager.menuBorderHorizontal,
                    i,
                    terminal.getHeightInCharacters() - 1);
        }
        for (int i = 0; i < terminal.getHeightInCharacters(); i++) {
            terminal.write(MenuDecorationManager.menuBorderVertical, 0, i);
            terminal.write(
                    MenuDecorationManager.menuBorderVertical,
                    terminal.getWidthInCharacters() - 1,
                    i);
        }
        terminal.write(MenuDecorationManager.menuBorderCornerNW, 0, 0);
        terminal.write(
                MenuDecorationManager.menuBorderCornerNE, terminal.getWidthInCharacters() - 1, 0);
        terminal.write(
                MenuDecorationManager.menuBorderCornerSW, 0, terminal.getHeightInCharacters() - 1);
        terminal.write(
                MenuDecorationManager.menuBorderCornerSE,
                terminal.getWidthInCharacters() - 1,
                terminal.getHeightInCharacters() - 1);
    }
}
