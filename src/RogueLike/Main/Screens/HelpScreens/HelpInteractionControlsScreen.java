package RogueLike.Main.Screens.HelpScreens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.Screen;

public class HelpInteractionControlsScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpInteractionControlsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput() {
		ExtendedAsciiPanel.clear();
		Screen.generateBorders();;
		ExtendedAsciiPanel.writeCenter("== Help: Interaction Controls ==", 1);
		
		int y = 3;
		int x = 12;
		ExtendedAsciiPanel.write(String.format("[%s]: Open your inventory", KeybindManager.keybindText(KeybindManager.menuInventory)), x, y++);
		y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Pick up an item from your feet", KeybindManager.keybindText(KeybindManager.interactionPickUpItem)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Drop an item at your feet", KeybindManager.keybindText(KeybindManager.interactionDropItem)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Examine an item in your inventory", KeybindManager.keybindText(KeybindManager.interactionExamineItem)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Equip or unequip an item", KeybindManager.keybindText(KeybindManager.interactionEquipItem)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Eat a piece of food", KeybindManager.keybindText(KeybindManager.interactionEatFood)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Quaff a potion", KeybindManager.keybindText(KeybindManager.interactionDrinkPotion)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Recite a spell from a magic item", KeybindManager.keybindText(KeybindManager.interactionReadSpell)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Throw an item", KeybindManager.keybindText(KeybindManager.interactionThrowItem)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Look at something further away", KeybindManager.keybindText(KeybindManager.interactionLook)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Search for hidden things around you", KeybindManager.keybindText(KeybindManager.interactionSearch)), x, y++);
        y++;
        y = 3;
        x = 67;
        ExtendedAsciiPanel.write(String.format("[%s]: Open the character sheet", KeybindManager.keybindText(KeybindManager.menuCharacterSheet)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: View the action log", KeybindManager.keybindText(KeybindManager.menuActionLog)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Spend ability and skill points", KeybindManager.keybindText(KeybindManager.interactionLevelUp)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Open the help menu", KeybindManager.keybindText(KeybindManager.menuHelp)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Use the item in your first quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_1)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Use the item in your second quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_2)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Use the item in your third quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_3)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Use the item in your fourth quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_4)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Use the item in your fifth quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_5)), x, y++);
        y++;
        ExtendedAsciiPanel.write(String.format("[%s]: Use the item in your sixth quickslot", KeybindManager.keybindText(KeybindManager.interactionQuickslot_6)), x, y++);
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
