package RogueLike.Main.Screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.FieldOfView;
import RogueLike.Main.Skill;
import RogueLike.Main.Tile;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Managers.KeybindManager;

public class GameplayScreen implements Screen{
	
	private boolean inputAccepted = false;
	public void setInputAccepted(boolean value) {
		inputAccepted = value;
	}

	public void displayOutput(ExtendedAsciiPanel terminal) {
		
		int left = getScrollX();
		int top = getScrollY();
		
		displayTiles(terminal, left, top);
		displayMessages(terminal, messages);
		
		//health bar
		terminal.writeCenter("====================================================================================================================", 21);
		terminal.writeCenter("====================================================================================================================", 47);
		String stats = String.format("+||+ Depth: %2d (%d | %d) // Level: %2d%s // XP: %d/%d // Health: %3d/%3d // Mana: %d/%d // Hunger: %s +||+", player.z()+1, player.x()+1, player.y()+1, player.level(), canLevelUp(), player.xp(), player.xpToNextLevel(), player.hp(), player.maxHP(), player.mana(), player.maxMana(), player.hungerAsString());
		terminal.writeCenter(stats, 22);
		//
		int y = 24;
		terminal.write("== Status Effects ==", 95, y++);
		y++;
		for(Effect effect : effects) {
        	if(effect.name() != null) {
        		int y2 = y;
        		if(y2 > 46) {
        			y2 = 46;
        		}
        		char effectIcon = (char)30;
        		if(effect.isNegative()) {
        			effectIcon = (char)31;
        		}
        		if(effect.showInMenu()) {
        			terminal.write(String.format("%c %s (%d)", effectIcon, effect.name(), effect.duration()), 95, y2);
        		}
        		y++;
        	}
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
	        	if(player.z()+1 == 6 && player.hasVisitedZone2() == false) {
	        		subscreen = new Zone2Screen();
	        	}
	        	if(player.z()+1 == 11 && player.hasVisitedZone3() == false) {
	        		
	        	}
	        	break;
	        //
	        // Interaction Controls
	        case KeybindManager.interactionDropItem: subscreen = new DropScreen(player); break;
	        case KeybindManager.interactionEatFood: subscreen = new EatScreen(player); break;
	        case KeybindManager.interactionEquipItem: subscreen = new EquipScreen(player); break;
	        case KeybindManager.interactionPickUpItem: player.ai().playerAIGetItem(); break;
	        case KeybindManager.interactionExamineItem: subscreen = new ExamineScreen(player); break;
	        case KeybindManager.interactionLook: subscreen = new LookScreen(player, String.format("Use the movement controls to look around, or press [%s] to stop looking.", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), player.x - getScrollX(), player.y - getScrollY()); break;
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
	        		subscreen = new RangedWeaponTargetingScreen(player, player.x - getScrollX(), player.y - getScrollY()); inputAccepted = true; break;
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
	public Creature player;
	public List<Effect> effects;
	private List<String> messages;
	private FieldOfView fov;
	private Screen subscreen;
	public String playerClass;
	public List<Integer> startingStats = new ArrayList<Integer>();
	public Skill[] startingSkills;
	public String playerName;
	public String playerAncestry;
	//temp
	
	//
	public ObjectFactory cloneFactory;
	public void setCloneFactory(ObjectFactory factory) {
		cloneFactory = factory;
	}
	public ObjectFactory getCloneFactory() {
		return cloneFactory;
	}
	
	
	// #########
	public GameplayScreen(String playerClass, List<Integer> playerAbilities, Skill[] playerSkills, String playerName, String playerAncestry) {
		this.playerClass = playerClass;
		this.startingStats = playerAbilities;
		this.startingSkills = playerSkills;
		this.playerName = playerName;
		this.playerAncestry = playerAncestry;
		screenWidth = 120; //80
		screenHeight = 21; //21
		messages = new ArrayList<String>();
		createWorld();
		fov = new FieldOfView(world);
		ObjectFactory factory = new ObjectFactory(world);
		setCloneFactory(factory);
		createCreatures(factory);
		createItems(factory);
		this.effects = player.effects();
		
	}

	
	
	public void createCreatures(ObjectFactory factory) {
		player = factory.creatureFactory.newPlayer(messages, fov, this.playerClass, this.startingStats, this.startingSkills, this.playerName, this.playerAncestry);
		
		factory.setUpPotionIndex();
		factory.setUpWandIndex(player);
		factory.setUpRingIndex(player);
		factory.setUpScrollIndex(player);
		for(int z = 0; z < world.depth(); z++) {
			//temp
			for(int i = 0; i < 20; i++) { // 20
				factory.creatureFactory.newMarker(z, player, true);
			}
			//
			for(int i = 0; i < 18; i++) {
				factory.randomChest(z, player, true);
				//factory.newGoldChest(z, player, true);
			}
			
			for(int i = 0; i < 70; i++) {
				factory.randomLesserMonster(z, player, true);
			}
			if(z > 1) {
				for(int i = 0; i < 35; i++) {
					factory.randomMediumMonster(z, player, true);
				}
			}
			if(z > 3) {
				for(int i = 0; i < 18; i++) {
					factory.randomGreaterMonster(z, player, true);
				}
			}



		}
		for(Creature c : world.creatures) {
			c.setGameplayScreen(this);
		}
	}
	
	private void createItems(ObjectFactory factory) {
		for(int z = 0; z < world.depth(); z++) {
			for(int i = 0; i < world.width() * world.height() / 25; i++) {
				factory.itemFactory.newRock(z, 1);
			}
			for(int i = 0; i < 35; i++) {
				factory.randomTrap(z, 1, player);
			}
			for(int i = 0; i < 6; i++) {//6
				factory.randomFood(z, 1);
			}
			for(int j = 0; j < 4; j++) {
				factory.randomPotion(z, true);
			}
			for(int k = 0; k < 1; k++) {
				factory.randomArmor(z, true);
			}
			for(int l = 0; l < 1; l++) {
				factory.randomShield(z, true);
			}
			for(int m = 0; m < 2; m++) {
				factory.randomWeapon(z, true);
			}
			for(int l = 0; l < 1; l++) {
				factory.randomRing(z, true);
			}
			for(int l = 0; l < 1; l++) {
				factory.randomScroll(z, player, true);
			}
		}
		factory.itemFactory.newVictoryItem(world.depth()-1, 1);
	}
	
	private void createWorld() {
		//IMPORTANT: World Width // World Height // World Depth
		world = new WorldBuilder(120, 60, 15)
				.makeCaves()
				.build();
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
			if(item != null && item.name().equals("ancient axe")) {
				return new WinScreen(player);
			}
		}
		player.notify("You can't leave yet! You don't have the Axe!");
		return this;
	}
	
	//
	private void displayMessages(ExtendedAsciiPanel terminal, List<String> messages) {
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
			terminal.write(messages.get(i), 3, y);
			
		}
		messages.clear();
		
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
					if(world.item(x, y, player.z) != null && world.item(x, y, player.z).isTrap()) {
						world.item(x, y, player.z).setColor(world.item(x, y, player.z).defaultColor());
						world.item(x, y, player.z).changeGlyph(world.item(x, y, player.z).defaultGlyph());
						world.item(x, y, player.z).setIsTrapFound(true);
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
