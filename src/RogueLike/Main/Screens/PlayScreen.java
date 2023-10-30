package RogueLike.Main.Screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.Creature;
import RogueLike.Main.Effect;
import RogueLike.Main.FieldOfView;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.Tile;
import RogueLike.Main.World;
import RogueLike.Main.WorldBuilder;
import RogueLike.Main.Items.Item;
import asciiPanel.AsciiPanel;

public class PlayScreen implements Screen{
	
	private boolean inputAccepted = false;
	public void setInputAccepted(boolean value) {
		inputAccepted = value;
	}

	public void displayOutput(AsciiPanel terminal) {
		//terminal.write("fun", 1, 1);
		//terminal.writeCenter("escape to lose enter to win", 22);
		
		int left = getScrollX();
		int top = getScrollY();
		
		displayTiles(terminal, left, top);
		displayMessages(terminal, messages);
		
		//terminal.write(player.glyph(), player.x - left, player.y - top, player.color());
		
		//health bar
		terminal.writeCenter("====================================================================================================================", 21);
		terminal.writeCenter("====================================================================================================================", 47);
		String stats = String.format("+||+ Depth: %2d (%d | %d) // Level: %2d%s // XP: %d/%d // Health: %3d/%3d // Mana: %d/%d // Hunger: %s +||+", player.z()+1, player.x()+1, player.y()+1, player.level(), canLevelUp(), player.xp(), player.xpToNextLevel(), player.hp(), player.maxHP(), player.mana(), player.maxMana(), hunger());
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
        		if(effect.isNegative() > 0) {
        			effectIcon = (char)31;
        		}
        		terminal.write(String.format("%c %s (%d)", effectIcon, effect.name(), effect.duration()), 95, y2);
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
	
	private String hunger() {
		if(player.food() < player.maxFood() * 0.1) {
			return "Starving";
		}else if(player.food() < player.maxFood() * 0.2) {
			return "Hungry";
		}else if(player.food() < player.maxFood() * 0.5) {
			return "Satisfied";
		}else if(player.food() < player.maxFood() * 0.8) {
			return "Stuffed";
		}else if(player.food() < player.maxFood() * 0.9) {
			return "Full";
		}else if(player.food() <= player.maxFood()) {
			return "Full";
		}else{
			return "";
		}
	}
	//temp
	int level = 1;
	//
	public Screen respondToUserInput(KeyEvent key) {
		//int level = player.level();
		if(subscreen != null) {
			subscreen = subscreen.respondToUserInput(key);
		}else {
			//
			
			//
			switch(key.getKeyCode()) {
			// Movement Controls
	        case KeyEvent.VK_NUMPAD4: player.moveBy(-1, 0, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD6: player.moveBy( 1, 0, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD8: player.moveBy( 0,-1, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD2: player.moveBy( 0, 1, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD7: player.moveBy(-1,-1, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD9: player.moveBy( 1,-1, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD1: player.moveBy(-1, 1, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD3: player.moveBy( 1, 1, 0, false); inputAccepted = true; break;
	        case KeyEvent.VK_NUMPAD5: player.idle(); inputAccepted = true; break;
	        //
	        case KeyEvent.VK_D: subscreen = new DropScreen(player); inputAccepted = true; break;
	        case KeyEvent.VK_E: subscreen = new EatScreen(player); inputAccepted = true; break;
	        case KeyEvent.VK_W: subscreen = new EquipScreen(player); inputAccepted = true; break;
	        case KeyEvent.VK_H: subscreen = new HelpScreen(false); break;
	        case KeyEvent.VK_C: subscreen = new StatsScreen(player); break;
	        case KeyEvent.VK_I: subscreen = new IndexPotionScreen(player, player.factory()); break;
	        case KeyEvent.VK_X: subscreen = new ExamineScreen(player); break;
	        case KeyEvent.VK_L: subscreen = new LookScreen(player, "Looking", player.x - getScrollX(), player.y - getScrollY()); break;
	        case KeyEvent.VK_T: subscreen = new ThrowScreen(player, player.x - getScrollX(), player.y - getScrollY()); inputAccepted = true; break;
	        case KeyEvent.VK_Q: subscreen = new QuaffScreen(player); inputAccepted = true; break;
	        case KeyEvent.VK_R: subscreen = new ReadScreen(player, player.x - getScrollX(), player.y - getScrollY()); inputAccepted = true; break;
	        //
	        case KeyEvent.VK_A: subscreen = new SpellbookScreen(player, player.x - getScrollX(), player.y - getScrollY(), true); inputAccepted = true; break;
	        //
	        //case KeyEvent.VK_M: subscreen = new FeatbookScreen(player, player.x - getScrollX(), player.y - getScrollY(), true); inputAccepted = 1; break;
	        //
	        case KeyEvent.VK_B: subscreen = new InventoryScreen(this, player, player.x - getScrollX(), player.y - getScrollY()); /*inputAccepted = 1;*/ break;
	        //
	        //
	        case KeyEvent.VK_1: player.modifyXP(1000000000);
	        //
	        case KeyEvent.VK_G: player.pickup(); inputAccepted = true; break;
	        case KeyEvent.VK_S: player.search(12, false); inputAccepted = true; break;
	        case KeyEvent.VK_UP: 
	        	if(userIsTryingToExit()) {
	        		return userExits();
	        	}else {
	        		player.moveBy( 0, 0, -1, false); inputAccepted = true; break;
	        	}
	        case KeyEvent.VK_DOWN: 
	        	player.moveBy( 0, 0, 1, false); inputAccepted = true; 
	        	if(player.z()+1 == 6 && player.hasVisitedZone2() == false) {
	        		subscreen = new Zone2Screen();
	        	}
	        	if(player.z()+1 == 11 && player.hasVisitedZone3() == false) {
	        		
	        	}
	        	break;
	        
	        case KeyEvent.VK_U: 
	        	if(player.attributePoints() == 0 && player.skillPoints() == 0) {
	        		player.notify("You don't have any skill points to spend."); break;
	        	}else {
	        		//subscreen = new LevelUpScreen(player, player.attributePoints()); break;
	        		subscreen = new PlayerLevelUpStatsScreen(player);
	        	}
	        	
	        	
	        
	        case KeyEvent.VK_F: 
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
	        		subscreen = new FireWeaponScreen(player, player.x - getScrollX(), player.y - getScrollY()); inputAccepted = true; break;
	        	}
	        
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
		
		if(subscreen == null && inputAccepted) {
			//world.update();
			world.updateOnCurrentFloor(player);
			//temp
			//player.stackEffects();
			for(Creature creature : world.creatures) {
				creature.stackEffects();
			}
			inputAccepted = false;
			//
			/*System.out.print("\n");
			System.out.print(player.x());
			System.out.print(" ");
			System.out.print(player.y());
			System.out.print(" ");
			System.out.print(player.z());*/
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
	public List<Integer> startingSkills = new ArrayList<Integer>();
	public String playerName;
	public String playerSpecies;
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
	public PlayScreen(String playerClass, List<Integer> playerAbilities, /*List<Integer> playerSkills,*/ String playerName/*, String playerSpecies*/) {
		this.playerClass = playerClass;
		this.startingStats = playerAbilities;
		//this.startingSkills = playerSkills;
		this.playerName = playerName;
		//this.playerSpecies = playerSpecies;
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
		player = factory.newPlayer(messages, fov, this.playerClass, this.startingStats, this.playerName);
		factory.setUpPotionIndex();
		factory.setUpWandIndex(player);
		factory.setUpRingIndex(player);
		factory.setUpScrollIndex(player);
		for(int z = 0; z < world.depth(); z++) {
			//temp
			for(int i = 0; i < 20; i++) { // 20
				factory.newMarker(z, player, 1);
			}
			//
			for(int i = 0; i < 18; i++) {
				factory.randomChest(z, player, 1);
			}
			
			for(int i = 0; i < 70; i++) {
				factory.randomLesserMonster(z, player, 1);
			}
			if(z > 1) {
				for(int i = 0; i < 35; i++) {
					factory.randomMediumMonster(z, player, 1);
				}
			}
			if(z > 3) {
				for(int i = 0; i < 18; i++) {
					factory.randomGreaterMonster(z, player, 1);
				}
			}



		}
	}
	
	private void createItems(ObjectFactory factory) {
		for(int z = 0; z < world.depth(); z++) {
			for(int i = 0; i < world.width() * world.height() / 25; i++) {
				factory.newRock(z, 1);
			}
			for(int i = 0; i < 35; i++) {
				factory.randomTrap(z, 1);
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
		factory.newVictoryItem(world.depth()-1, 1);
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
	private void displayMessages(AsciiPanel terminal, List<String> messages) {
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
	private void displayTiles(AsciiPanel terminal, int left, int top) {
		if(player.isReadingMagicMapping()) {
			fov.updateMagicMapping(player.x, player.y, player.z, 1000);
		}else if(player.isLevitating() == true) {
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
