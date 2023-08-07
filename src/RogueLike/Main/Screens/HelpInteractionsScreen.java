package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class HelpInteractionsScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpInteractionsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Help: Interaction Controls ==", 1);
		
		int y = 3;
		int x = 42;
        terminal.write("[G]: Pick up an item from your feet", x, y++);
        terminal.write("[D]: Drop an item at your feet", x, y++);
        terminal.write("[X]: Examine an item in your inventory", x, y++);
        terminal.write("[W]: Equip or unequip an item", x, y++);
        terminal.write("[E]: Eat an item", x, y++);
        terminal.write("[Q]: Quaff a potion", x, y++);
        terminal.write("[R]: Read from a magic item", x, y++);
        terminal.write("[T]: Throw an item", x, y++);
        terminal.write("[L]: Look at something further away", x, y++);
        terminal.write("[S]: Search for hidden things around you", x, y++);
        terminal.write("[C]: Open the character screen", x, y++);
        terminal.write("[I]: Open the index", x, y++);
        terminal.write("[U]: Open the level-up screen", x, y++);
        terminal.write("[A]: Open the spellcasting screen", x, y++);
        terminal.write("[H]: Open the help menu", x, y++);
        y++;
        terminal.writeCenter("Move into an enemy to attack it, or into an object to interact with it!", y++);
        

    
        terminal.writeCenter("-- [ESCAPE]: Back --", 38);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeyEvent.VK_ESCAPE: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
