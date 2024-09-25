package RogueLike.Main.Screens.HelpScreens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.*;

public class HelpScreen implements Screen{
	
	private boolean fromMenu;
	
	private char moveLeft = '>';
	private char moveRight = '<';
	private char interactionLeft = '>';
	private char interactionRight = '<';
	private char menusLeft = '>';
	private char menusRight = '<';
	
	private char itemsLeft = '>';
	private char itemsRight = '<';
	private char inventoryLeft = '>';
	private char inventoryRight = '<';
	private char combatLeft = '>';
	private char combatRight = '<';
	private char explorationLeft = '>';
	private char explorationRight = '<';
	private char statusLeft = '>';
	private char statusRight = '<';
	
	private int check = 0;
	
	public void changeMarkers(int check) {
		if(check == 0) {
			moveLeft = '>';
			moveRight = '<';
			interactionLeft = ' ';
			interactionRight = ' ';
			menusLeft = ' ';
			menusRight = ' ';

			itemsLeft = ' ';
			itemsRight = ' ';
			inventoryLeft = ' ';
			inventoryRight = ' ';
			combatLeft = ' ';
			combatRight = ' ';
			explorationLeft = ' ';
			explorationRight = ' ';
			statusLeft = ' ';
			statusRight = ' ';
		}else if(check == 1) {
			moveLeft = ' ';
			moveRight = ' ';
			interactionLeft = '>';
			interactionRight = '<';
			menusLeft = ' ';
			menusRight = ' ';

			itemsLeft = ' ';
			itemsRight = ' ';
			inventoryLeft = ' ';
			inventoryRight = ' ';
			combatLeft = ' ';
			combatRight = ' ';
			explorationLeft = ' ';
			explorationRight = ' ';
			statusLeft = ' ';
			statusRight = ' ';
		}else if(check == 2) {
			moveLeft = ' ';
			moveRight = ' ';
			interactionLeft = ' ';
			interactionRight = ' ';
			menusLeft = '>';
			menusRight = '<';

			itemsLeft = ' ';
			itemsRight = ' ';
			inventoryLeft = ' ';
			inventoryRight = ' ';
			combatLeft = ' ';
			combatRight = ' ';
			explorationLeft = ' ';
			explorationRight = ' ';
			statusLeft = ' ';
			statusRight = ' ';
		}else if(check == 3) {
			moveLeft = ' ';
			moveRight = ' ';
			interactionLeft = ' ';
			interactionRight = ' ';
			menusLeft = ' ';
			menusRight = ' ';

			itemsLeft = '>';
			itemsRight = '<';
			inventoryLeft = ' ';
			inventoryRight = ' ';
			combatLeft = ' ';
			combatRight = ' ';
			explorationLeft = ' ';
			explorationRight = ' ';
			statusLeft = ' ';
			statusRight = ' ';
		}else if(check == 4) {
			moveLeft = ' ';
			moveRight = ' ';
			interactionLeft = ' ';
			interactionRight = ' ';
			menusLeft = ' ';
			menusRight = ' ';

			itemsLeft = ' ';
			itemsRight = ' ';
			inventoryLeft = '>';
			inventoryRight = '<';
			combatLeft = ' ';
			combatRight = ' ';
			explorationLeft = ' ';
			explorationRight = ' ';
			statusLeft = ' ';
			statusRight = ' ';
		}else if(check == 5) {
			moveLeft = ' ';
			moveRight = ' ';
			interactionLeft = ' ';
			interactionRight = ' ';
			menusLeft = ' ';
			menusRight = ' ';

			itemsLeft = ' ';
			itemsRight = ' ';
			inventoryLeft = ' ';
			inventoryRight = ' ';
			combatLeft = '>';
			combatRight = '<';
			explorationLeft = ' ';
			explorationRight = ' ';
			statusLeft = ' ';
			statusRight = ' ';
		}else if(check == 6) {
			moveLeft = ' ';
			moveRight = ' ';
			interactionLeft = ' ';
			interactionRight = ' ';
			menusLeft = ' ';
			menusRight = ' ';

			itemsLeft = ' ';
			itemsRight = ' ';
			inventoryLeft = ' ';
			inventoryRight = ' ';
			combatLeft = ' ';
			combatRight = ' ';
			explorationLeft = '>';
			explorationRight = '<';
			statusLeft = ' ';
			statusRight = ' ';
		}
		else if(check == 7) {
			moveLeft = ' ';
			moveRight = ' ';
			interactionLeft = ' ';
			interactionRight = ' ';
			menusLeft = ' ';
			menusRight = ' ';

			itemsLeft = ' ';
			itemsRight = ' ';
			inventoryLeft = ' ';
			inventoryRight = ' ';
			combatLeft = ' ';
			combatRight = ' ';
			explorationLeft = ' ';
			explorationRight = ' ';
			statusLeft = '>';
			statusRight = '<';
		}
	}
	
	public HelpScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}

	public void displayOutput(ExtendedAsciiPanel terminal) {
		changeMarkers(check);
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Help Menu ==", 1);
		
		int y = 3;
		int x = 42;
		
        terminal.write(String.format("%c Controls - Movement %c", moveLeft, moveRight), x, y++);
        y++;
        terminal.write(String.format("%c Controls - Interactions %c", interactionLeft, interactionRight), x, y++);
        y++;
        terminal.write(String.format("%c Controls - Menus %c", menusLeft, menusRight), x, y++);
        y++;
        terminal.write(String.format("%c Systems - Items %c", itemsLeft, itemsRight), x, y++);
        y++;
        terminal.write(String.format("%c Systems - Inventory and Equipment %c", inventoryLeft, inventoryRight), x, y++);
        y++;
        terminal.write(String.format("%c Systems - Combat %c", combatLeft, combatRight), x, y++);
        y++;
        terminal.write(String.format("%c Systems - Exploration %c", explorationLeft, explorationRight), x, y++);
        y++;
        terminal.write(String.format("%c Systems - Status Effects %c", statusLeft, statusRight), x, y++);
        
        
        terminal.writeCenter(String.format("-- [%s / %s]: Move Selection | [%s]: Confirm --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown), KeybindManager.keybindText(KeybindManager.navigateMenuConfirm)), 40);
		terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 42);

	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuUp:
			if(check == 0) {
				check = 7;
			}else{
				check--;
			}
			return this;
			
		case KeybindManager.navigateMenuDown:
			if(check == 7) {
				check = 0;
			}else{
				check++;
			}
			return this;
			
		case KeybindManager.navigateMenuConfirm:
			if(check == 0) {
				return new HelpMovementControlsScreen(fromMenu);
			}else if(check == 1) {
				return new HelpInteractionControlsScreen(fromMenu);
			}else if(check == 2) {
				return new HelpMenuControlsScreen(fromMenu);
			}else if(check == 3) {
				return this; //TODO Help: Items
			}else if(check == 4) {
				return this; //TODO Help: Inventory & Equipment
			}else if(check == 5) {
				return this; //TODO Help: Combat
			}else if(check == 6) {
				return this; //TODO Help: Exploration
			}else if(check == 7) {
				return new HelpEffectsScreen(fromMenu);
			}
        
        case KeybindManager.navigateMenuBack: 
        	if(this.fromMenu == true) {
        		return new MainMenuScreen();
        	}else {
        		return null;
        	}
		}
		return this;
	}
	
}
