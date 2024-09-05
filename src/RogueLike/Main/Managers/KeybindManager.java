package RogueLike.Main.Managers;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeybindManager {


	public static int applyKeymap(int inputKeycode) {
		// TODO distinguish between menus and gameplay
        return keymap.getOrDefault(inputKeycode, inputKeycode);
	}

	public static final int movementWest = KeyEvent.VK_NUMPAD4;
	public static final int movementEast = KeyEvent.VK_NUMPAD6;
	public static final int movementNorth = KeyEvent.VK_NUMPAD8;
	public static final int movementSouth = KeyEvent.VK_NUMPAD2;

	public static final int movementNorthWest = KeyEvent.VK_NUMPAD7;
	public static final int movementNorthEast = KeyEvent.VK_NUMPAD9;
	public static final int movementSouthWest = KeyEvent.VK_NUMPAD1;
	public static final int movementSouthEast = KeyEvent.VK_NUMPAD3;

	public static final int movementWait = KeyEvent.VK_NUMPAD5;
	public static final int movementUpStairs = KeyEvent.VK_UP;
	public static final int movementDownStairs = KeyEvent.VK_DOWN;

	public static final int interactionDropItem = KeyEvent.VK_D;
	public static final int interactionDrinkPotion = KeyEvent.VK_Q;
	public static final int interactionEatFood = KeyEvent.VK_E;
	public static final int interactionEquipItem = KeyEvent.VK_W;
	public static final int interactionExamineItem = KeyEvent.VK_X;
	public static final int interactionThrowItem = KeyEvent.VK_T;
	public static final int interactionReadSpell = KeyEvent.VK_R;
	public static final int interactionPickUpItem = KeyEvent.VK_G;
	public static final int interactionSearch = KeyEvent.VK_S;
	public static final int interactionLevelUp = KeyEvent.VK_U;
	public static final int interactionQuickslot_1 = KeyEvent.VK_1;
	public static final int interactionQuickslot_2 = KeyEvent.VK_2;
	public static final int interactionQuickslot_3 = KeyEvent.VK_3;
	public static final int interactionQuickslot_4 = KeyEvent.VK_4;
	public static final int interactionQuickslot_5 = KeyEvent.VK_5;
	public static final int interactionQuickslot_6 = KeyEvent.VK_6;
	public static final int interactionFireRangedWeapon = KeyEvent.VK_F;
	public static final int interactionLook = KeyEvent.VK_L;

	public static final int menuHelp = KeyEvent.VK_H;
	public static final int menuInventory = KeyEvent.VK_B;
	public static final int menuIndex = KeyEvent.VK_I;
	public static final int menuCharacterSheet = KeyEvent.VK_C;
	public static final int menuActionLog = KeyEvent.VK_A;
	public static final int menuPause = KeyEvent.VK_ESCAPE;
	
	public static final int navigateMenuConfirm = KeyEvent.VK_ENTER;
	public static final int navigateMenuBack = KeyEvent.VK_ESCAPE;
	public static final int navigateMenuUp = KeyEvent.VK_UP;
	public static final int navigateMenuDown = KeyEvent.VK_DOWN;
	public static final int navigateMenuLeft = KeyEvent.VK_LEFT;
	public static final int navigateMenuRight = KeyEvent.VK_RIGHT;
	public static final int navigateMenuOption_1 = KeyEvent.VK_1;
	public static final int navigateMenuOption_2 = KeyEvent.VK_2;
	public static final int navigateMenuOption_3 = KeyEvent.VK_3;
	public static final int navigateMenuOption_4 = KeyEvent.VK_4;
	public static final int navigateMenuOption_5 = KeyEvent.VK_5;
	public static final int navigateMenuOption_6 = KeyEvent.VK_6;
	public static final int navigateMenuOption_7 = KeyEvent.VK_7;
	public static final int navigateMenuOption_8 = KeyEvent.VK_8;
	public static final int navigateMenuOption_9 = KeyEvent.VK_9;
	public static final int navigateMenuFunction_1 = KeyEvent.VK_F1;
	public static final int navigateMenuSort = KeyEvent.VK_S;

	public static String keybindText(int keyCode) {
		String representation = KeyEvent.getKeyText(keyCode);
		return representation.toUpperCase();
	}
	public static Map<Integer, Integer> keymap = new HashMap<>();

	public static boolean addKeybind(int source, int destination) {
		keymap.put(source, destination);

		// If there isn't already a reverse keybind, send it to /dev/null
		if (!keymap.containsKey(destination)) {
			keymap.put(destination, KeyEvent.VK_F24);
		}

		return true;
	}

	public static void clearKeybinds() {
		keymap = new HashMap<>();
	}

}
