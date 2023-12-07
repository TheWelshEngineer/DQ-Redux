package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Managers.KeybindManager;
import asciiPanel.AsciiPanel;

public class HelpInteractionControlsScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpInteractionControlsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput(AsciiPanel terminal) {
		terminal.clear();
		terminal.writeCenter("== Help: Interaction Controls ==", 1);
		
		int y = 3;
		int x = 42;
        terminal.write(String.format("[%s]: Pick up an item from your feet", KeybindManager.keybindText(KeybindManager.interactionPickUpItem)), x, y++);
        terminal.write(String.format("[%s]: Drop an item at your feet", KeybindManager.keybindText(KeybindManager.interactionDropItem)), x, y++);
        terminal.write(String.format("[%s]: Examine an item in your inventory", KeybindManager.keybindText(KeybindManager.interactionExamineItem)), x, y++);
        terminal.write(String.format("[%s]: Equip or unequip an item", KeybindManager.keybindText(KeybindManager.interactionEquipItem)), x, y++);
        terminal.write(String.format("[%s]: Eat a piece of food", KeybindManager.keybindText(KeybindManager.interactionEatFood)), x, y++);
        terminal.write(String.format("[%s]: Quaff a potion", KeybindManager.keybindText(KeybindManager.interactionDrinkPotion)), x, y++);
        terminal.write(String.format("[%s]: Recite a spell from a magic item", KeybindManager.keybindText(KeybindManager.interactionReadSpell)), x, y++);
        terminal.write(String.format("[%s]: Throw an item", KeybindManager.keybindText(KeybindManager.interactionThrowItem)), x, y++);
        terminal.write(String.format("[%s]: Look at something further away", KeybindManager.keybindText(KeybindManager.interactionLook)), x, y++);
        terminal.write(String.format("[%s]: Search for hidden things around you", KeybindManager.keybindText(KeybindManager.interactionSearch)), x, y++);
        terminal.write(String.format("[%s]: Open the character sheet", KeybindManager.keybindText(KeybindManager.menuCharacterSheet)), x, y++);
        terminal.write(String.format("[%s]: Open the character sheet", KeybindManager.keybindText(KeybindManager.interactionLevelUp)), x, y++);
        terminal.write(String.format("[%s]: Open the character sheet", KeybindManager.keybindText(KeybindManager.menuHelp)), x, y++);
        terminal.write(String.format("[%s]: Use the item in your first quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_1)), x, y++);
        terminal.write(String.format("[%s]: Use the item in your second quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_2)), x, y++);
        terminal.write(String.format("[%s]: Use the item in your third quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_3)), x, y++);
        terminal.write(String.format("[%s]: Use the item in your fourth quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_4)), x, y++);
        terminal.write(String.format("[%s]: Use the item in your fifth quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_5)), x, y++);
        terminal.write(String.format("[%s]: Use the item in your sixth quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_6)), x, y++);
        y++;
        terminal.writeCenter("Move into an enemy to attack it, or into an object to interact with it!", y++);
        

    
        terminal.writeCenter("-- [ESCAPE]: Back --", 38);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
