package RogueLike.Main.Screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.AI.PlayerAI;
import RogueLike.Main.Creatures.Player;
import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Entity;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.ItemFactory;
import RogueLike.Main.FieldOfView;
import RogueLike.Main.Tile;
import RogueLike.Main.Utils.NotificationHistory;
import RogueLike.Main.Utils.PlayerBuildDetails;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.HelpScreens.HelpScreen;
import RogueLike.Main.Worldgen.WorldGenerationException;

public class GameplayScreen implements Screen{
	
	private boolean inputAccepted = false;
	public void setInputAccepted(boolean value) {
		inputAccepted = value;
	}
	
	private boolean toggleStatusToQuickslots = false;
	public void toggleStatusToQuickslots() {
		toggleStatusToQuickslots = !toggleStatusToQuickslots;
	}

	public void displayOutput(ExtendedAsciiPanel terminal) {
		
		int left = getScrollX();
		int top = getScrollY();
		
		displayTiles(terminal, left, top);
		playerNotifications.clearOldMessages(world.turnNumber());
		displayMessages(terminal, playerNotifications.getNotificationsOnTurn(world.turnNumber()));
		
		//health bar
		terminal.writeCenter("====================================================================================================================", 21);
		terminal.writeCenter("====================================================================================================================", 47);
		//
		String stats = "+||+                                                                                                          +||+";
		terminal.writeCenter(stats, 22);
		//
		String depth = String.format("Depth: %d (%d,%d)", player.z()+1, player.x()+1, player.y()+1);
		terminal.write(depth, 9, 22);
		String level = String.format(" Level: %d%s ", player.level(), canLevelUp());
		if(player.attributePoints() > 0 || player.skillPoints() > 0) {
			terminal.write(level, 29, 22, ExtendedAsciiPanel.white, ExtendedAsciiPanel.green);
		}else {
			terminal.write(level, 29, 22);
		}
		String xp = String.format("XP: %d/%d", player.xp(), player.xpToNextLevel());
		terminal.write(xp, 45, 22);
		String health = String.format(" Health: %d/%d ", player.hp(), player.maxHP());
		if(player.hp() <= player.maxHP()*0.2) {
			terminal.write(health, 59, 22, ExtendedAsciiPanel.white, ExtendedAsciiPanel.red);
		}else {
			terminal.write(health, 59, 22);
		}
		String mana = String.format(" Mana: %d/%d ", player.mana(), player.maxMana());
		if(player.mana() <= player.maxMana()*0.2) {
			terminal.write(mana, 78, 22, ExtendedAsciiPanel.white, ExtendedAsciiPanel.red);
		}else {
			terminal.write(mana, 78, 22);
		}
		String hunger = String.format(" Hunger: %s ", player.hungerAsString());
		if(player.food() <= player.maxFood()*0.1) {
			terminal.write(hunger, 95, 22, ExtendedAsciiPanel.white, ExtendedAsciiPanel.red);
		}else {
			terminal.write(hunger, 95, 22);
		}
		//
		int y = 24;
		if(!toggleStatusToQuickslots) {
			terminal.write("== Status Effects ==", 95, y++);
			y++;
			for(Effect effect : effects) {
	        	if(effect.name() != null) {
	        		int y2 = y;
	        		if(y2 > 46) {
	        			y2 = 46;
	        		}
	        		char effectTypeIcon = (char)30;
	        		Color effectTypeColor = ExtendedAsciiPanel.brightGreen;
	        		if(effect.isNegative()) {
	        			effectTypeIcon = (char)31;
	        			effectTypeColor = ExtendedAsciiPanel.brightRed;
	        		}
	        		if(effect.showInMenu()) {
	        			terminal.write(String.format("    %s (%d)", effect.name(), effect.duration()), 95, y2);
	        			terminal.write(String.format("%c ", effect.glyph()), 95, y2, effect.color());
	        			terminal.write(String.format(" %c", effectTypeIcon), 96, y2, effectTypeColor);
	        		}
	        		y++;
	        	}
			}
		}else {
			terminal.write("== Quickslots ==", 95, y++);
			y++;
			terminal.write(String.format("QS1: %s", player.quickslot_1() == null ? "Empty" : player.nameOf(player.quickslot_1())), 88, y++);
			terminal.write(String.format("QS2: %s", player.quickslot_2() == null ? "Empty" : player.nameOf(player.quickslot_2())), 88, y++);
			terminal.write(String.format("QS3: %s", player.quickslot_3() == null ? "Empty" : player.nameOf(player.quickslot_3())), 88, y++);
			terminal.write(String.format("QS4: %s", player.quickslot_4() == null ? "Empty" : player.nameOf(player.quickslot_4())), 88, y++);
			terminal.write(String.format("QS5: %s", player.quickslot_5() == null ? "Empty" : player.nameOf(player.quickslot_5())), 88, y++);
			terminal.write(String.format("QS6: %s", player.quickslot_6() == null ? "Empty" : player.nameOf(player.quickslot_6())), 88, y++);
		}
		
		//
		//mana here
		
		//floor
		//String currentFloor = String.format(" Floor %3d", player.z+1);
		//terminal.write(currentFloor, 1, 23);
		
		//menus
		if(subscreen != null) {
			subscreen.displayOutput(terminal);
		}
		
	}
	
	private String canLevelUp() {
		if(player.attributePoints() > 0 || player.skillPoints() > 0) {
			return "(!)";
		}else {
			return "";
		}
	}
	
	//temp
	int level = 1;
	//
	
	//
	public Screen respondToUserInput(KeyEvent key) {
		//int level = player.level();
		if(subscreen != null) {
			subscreen = subscreen.respondToUserInput(key);
		}else {
			//
			
			//
			switch(key.getKeyCode()) {
			//
			// Movement Controls
	        case KeybindManager.movementWest: player.ai().playerAIMoveWest(); break;
	        case KeybindManager.movementEast: player.ai().playerAIMoveEast(); break;
	        case KeybindManager.movementNorth: player.ai().playerAIMoveNorth(); break;
	        case KeybindManager.movementSouth: player.ai().playerAIMoveSouth(); break;
	        case KeybindManager.movementNorthWest: player.ai().playerAIMoveNorthWest(); break;
	        case KeybindManager.movementNorthEast: player.ai().playerAIMoveNorthEast(); break;
	        case KeybindManager.movementSouthWest: player.ai().playerAIMoveSouthWest(); break;
	        case KeybindManager.movementSouthEast: player.ai().playerAIMoveSouthEast(); break;
	        case KeybindManager.movementWait: player.ai().playerAIMoveIdle(); break;
	        case KeybindManager.movementUpStairs: 
	        	if(userIsTryingToExit()) {
	        		return userExits();
	        	}else {
	        		//player.moveBy( 0, 0, -1, false); inputAccepted = true; //break;
	        		player.ai().playerAIMoveUpStairs();
	        	}
	        	break;
	        case KeybindManager.movementDownStairs: 
	        	//player.moveBy( 0, 0, 1, false); inputAccepted = true;
	        	player.ai().playerAIMoveDownStairs();
//	        	if(player.z()+1 == 6 && player.hasVisitedZone2() == false) {
//	        		subscreen = new Zone2Screen();
//	        	}
//	        	if(player.z()+1 == 11 && player.hasVisitedZone3() == false) {
//	        		
//	        	}
	        	break;
	        //
	        // Interaction Controls
	        case KeybindManager.interactionDropItem: subscreen = new DropScreen(player); break;
	        case KeybindManager.interactionEatFood: subscreen = new EatScreen(player); break;
	        case KeybindManager.interactionEquipItem: subscreen = new EquipScreen(player); break;
	        case KeybindManager.interactionPickUpItem: player.ai().playerAIGetItem(); break;
	        case KeybindManager.interactionExamineItem: subscreen = new ExamineScreen(player); break;
	        case KeybindManager.interactionLook: subscreen = new LookScreen(player, String.format("Use the movement controls to look around, or press [%s] to stop looking.", KeybindManager.keybindText(KeybindManager.navigateMenuBack))); break;
	        case KeybindManager.interactionThrowItem: subscreen = new ThrowScreen(player, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionDrinkPotion: subscreen = new QuaffScreen(player); break;
	        case KeybindManager.interactionReadSpell: subscreen = new ReadScreen(player, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionQuickslot_1: subscreen = player.useItemFromQuickslot(1, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionQuickslot_2: subscreen = player.useItemFromQuickslot(2, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionQuickslot_3: subscreen = player.useItemFromQuickslot(3, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionQuickslot_4: subscreen = player.useItemFromQuickslot(4, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionQuickslot_5: subscreen = player.useItemFromQuickslot(5, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionQuickslot_6: subscreen = player.useItemFromQuickslot(6, player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeybindManager.interactionSearch: player.ai().playerAISearchArea(); break;
	        case KeybindManager.interactionFireRangedWeapon: 
	        	if(player.weapon() == null || player.weapon().rangedDamageDice() == null) {
	        		player.notify("You don't have a ranged weapon equipped."); 
	        	}else if(player.ammunition() == null) {
	        		player.notify("You don't have any ammunition ready."); 
	        	}else if(player.ammunition().isArrowAmmunition() && !player.weapon().usesArrowAmmunition()) {
	        		player.notify("You don't have the right ammunition ready."); 
	        	}else if(player.ammunition().isBoltAmmunition() && !player.weapon().usesBoltAmmunition()) {
	        		player.notify("You don't have the right ammunition ready."); 
	        	}else if(player.ammunition().isPowderAmmunition() && !player.weapon().usesPowderAmmunition()) {
	        		player.notify("You don't have the right ammunition ready."); 
	        	}else{
	        		subscreen = new RangedWeaponTargetingScreen(player); inputAccepted = true; break;
	        	}
	        case KeybindManager.interactionLevelUp: 
	        	if(player.attributePoints() == 0 && player.skillPoints() == 0) {
	        		player.notify("You don't have any skill points to spend."); break;
	        	}else {
	        		//subscreen = new LevelUpScreen(player, player.attributePoints()); break;
	        		subscreen = new PlayerLevelUpStatsScreen(player);
	        	}
	        	break;
	        //
	        // Menu Controls
	        case KeybindManager.menuHelp: subscreen = new HelpScreen(false); break;
	        case KeybindManager.menuCharacterSheet: subscreen = new CharacterSheetScreen(player); break;
	        case KeybindManager.menuIndex: subscreen = new IndexPotionScreen(player, player.factory()); break;
	        case KeybindManager.menuInventory: subscreen = new InventoryScreen(this, player, player.x - getScrollX(), player.y - getScrollY()); break;
			case KeybindManager.menuActionLog: subscreen = new ActionLogScreen(playerNotifications, world.turnNumber()); break;
			case KeybindManager.menuPause: subscreen = new PauseScreen(); break;
			case KeybindManager.interactionToggleStatusToQuickslots: this.toggleStatusToQuickslots(); inputAccepted = true; break;
			//
	        //case KeyEvent.VK_A: subscreen = new SpellbookScreen(player, player.x - getScrollX(), player.y - getScrollY(), true); inputAccepted = true; break;
	        //
	        //case KeyEvent.VK_M: subscreen = new FeatbookScreen(player, player.x - getScrollX(), player.y - getScrollY(), true); inputAccepted = 1; break;
			}
			
		}
		
		//if (player.level() > level) {
			//
			//int oldLevel = level;
			//level = player.level();
			//
			//subscreen = new LevelUpScreen(player, player.level() - oldLevel);
			
		//}
		
		if (player.isReadingIdentify()) {
			subscreen = new IdentifyScreen(player);
			player.setIsReadingIdentify(false);
			inputAccepted = true;
		}
		
		if (player.isReadingUpgrade()) {
			subscreen = new UpgradeScreen(player, player.factory());
			player.setIsReadingUpgrade(false);
			inputAccepted = true;
		}
		
		if (player.isReadingRemoveCurse()) {
			subscreen = new RemoveCurseScreen(player);
			player.setIsReadingRemoveCurse(false);
			inputAccepted = true;
		}
		
		if (player.isReadingEnchantment()) {
			subscreen = new EnchantScreen(player, player.factory());
			player.setIsReadingEnchantment(false);
			inputAccepted = true;
		}
		
		if(subscreen == null && (inputAccepted || player.ai().actionQueue().isEmpty() == false)) {
			//world.update();
			world.generateActionsOnCurrentFloor(player);
			world.updateOnCurrentFloor(player);
			//temp
			//player.stackEffects();
			for(Creature creature : world.creatures) {
				creature.stackEffects();
			}
			inputAccepted = false;
		}
		if(player.hp() < 1) {
			return new LoseScreen(player);	
		}
		return this;
	}
	
	//variables
	private World world;
	private int screenWidth;
	private int screenHeight;
	public Player player;
	public List<Effect> effects;
	private NotificationHistory playerNotifications;
	private final FieldOfView fov;
	public Screen subscreen;
	
	// #########
	public GameplayScreen(PlayerBuildDetails playerDetails) {
		this.screenWidth = 120; //80
		this.screenHeight = 21; //21
		// TODO: make the max notification history length configurable
		this.playerNotifications = new NotificationHistory(100);
		this.world = createWorld(this.playerNotifications, playerDetails);

		for(Creature c : world.creatures) {
			c.setGameplayScreen(this);
		}
		this.player = world.player();
		this.fov = ((PlayerAI) player.ai()).fov();
		this.effects = player.effects();
	}
	
	private World createWorld(NotificationHistory playerNotifications, PlayerBuildDetails playerDetails) {
		// This is a bit of a hacky way of handling the "world fails to generate sometimes" issue - just keep trying
		// until it generates successfully!
		while (true) {
			System.out.println("Generating world...");
			try {
				//IMPORTANT: World Width // World Height // World Depth
				return new WorldBuilder(120, 60, 22)
						.generateWorld()
						.build(playerNotifications, playerDetails);
			}
			catch (WorldGenerationException e) {
				System.out.printf("!!! Error - Aborting generation due to %s%n", e);
			}
		}
	}
	
	public int getScrollX() {
		return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
	}
	
	public int getScrollY() {
		return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
	}
	
	private boolean userIsTryingToExit() {
		return player.z == 0 && world.tile(player.x, player.y, player.z) == Tile.STAIRS_EXIT;
	}
	
	private Screen userExits() {
		for(Item item : player.inventory().getItems()) {
			if(item != null && item.id() == ItemFactory.VICTORY_ITEM_ID) {
				return new WinScreen(player);
			}
		}
		player.notify("You can't leave yet! You don't have the Axe!");
		return this;
	}
	
	private void displayMessages(ExtendedAsciiPanel terminal, List<TerminalText> messages) {
		//screenHeight - messages.size();
		int top = 24;
		int messagesSize = messages.size();
		if(messagesSize > 47) {
			messagesSize = 47;
		}
		for(int i = 0; i < messagesSize; i++) {
			int y = top+i;
			if(y > 47) {
				y = 47;
			}
			y = terminal.write(messages.get(i), 3, y);

		}
	}
	
	//player.x
	private void displayTiles(ExtendedAsciiPanel terminal, int left, int top) {
		if(player.isReadingMagicMapping()) {
			fov.updateMagicMapping(player.x, player.y, player.z, 1000);
		}else if(player.affectedBy(Effect.levitating)) {
			fov.updateLevitating(player.x, player.y, player.z, player.visionRadius());
		}else{
			fov.update(player.x, player.y, player.z, player.visionRadius());
		}
		
		if(player.isReadingMagicMapping()) {
			for(int x = 0; x < world.width(); x++) {
				for(int y = 0; y < world.height(); y++) {
					Entity entity = world.entity(x, y, player.z);
					if (entity instanceof Trap) {
						((Trap) entity).reveal();
					}
				}
			}
		}
		
		for (int x = 0; x < screenWidth; x++){
			for (int y = 0; y < screenHeight; y++){
				int wx = x + left;
				int wy = y + top;
				
				//
				//if(player.magicMapping() > 0 && player.item(wx, wy, player.z) != null && player.item(wx, wy, player.z).isTrap() > 0 && player.item(wx, wy, player.z).isFound() == 0) {
					//player.item(wx, wy, player.z).changeColor(player.item(wx, wy, player.z).defaultColor());
					//player.item(wx, wy, player.z).changeGlyph(player.item(wx, wy, player.z).defaultGlyph());
					//player.item(wx, wy, player.z).modifyIsFound(1);
				//}
				//
				if (player.canSee(wx, wy, player.z))
					terminal.write(world.glyph(wx, wy, player.z), x, y, world.color(wx, wy, player.z));
				else
					terminal.write(fov.tile(wx, wy, player.z).glyph(), x, y, Color.darkGray);
			}
		}
	}
		

}
