package RogueLike.Main.AI;

import java.util.*;

import RogueLike.Main.Creatures.Player;
import RogueLike.Main.Entities.Entity;
import RogueLike.Main.FieldOfView;
import RogueLike.Main.Tile;
import RogueLike.Main.Utils.NotificationHistory;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class PlayerAI extends CreatureAI{
	
	private final FieldOfView fov;
	private final NotificationHistory notificationsHandle;
	
	public PlayerAI(Player creature, NotificationHistory notificationsHandle, FieldOfView fov, ObjectFactory factory, World world) {
		super(creature, factory, world);
		actionQueue = new ArrayList<Integer>();
		this.fov = fov;
		this.notificationsHandle = notificationsHandle;
	}
	

	
	public void selectAction() {
		
	}
	
	public void decodeAction(int action) {
		if(this.actionQueue.get(0) == null) {
			action = 0;
		}
		switch(action) {
			case 1: this.creature.moveBy(-1, 0, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack West]"); break;
			case 2: this.creature.moveBy(1, 0, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack East]"); break;
			case 3: this.creature.moveBy(0,-1, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack North]"); break;
			case 4: this.creature.moveBy(0, 1, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack South]"); break;
			case 5: this.creature.moveBy(-1,-1, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack Northwest]"); break;
			case 6: this.creature.moveBy(1,-1, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack Northeast]"); break;
			case 7: this.creature.moveBy(-1, 1, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack Southwest]"); break;
			case 8: this.creature.moveBy(1, 1, 0, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move/Attack Southeast]"); break;
			case 9: this.creature.idle(); System.out.println(this.toString() + " uses [Idle]"); break;
			//
			case 10: if(this.itemToProcess() != null){this.creature.drop(itemToProcess, false); System.out.println(this.toString() + " uses [Drop "+itemToProcess.toString()+"]"); this.setItemToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			case 11: if(this.itemToProcess() != null){this.creature.eat(itemToProcess); System.out.println(this.toString() + " uses [Eat "+itemToProcess.toString()+"]"); this.setItemToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			case 12: if(this.itemToProcess() != null){this.creature.equip(itemToProcess, false); System.out.println(this.toString() + " uses [Equip "+itemToProcess.toString()+"]"); this.setItemToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			case 13: this.creature.pickup(); System.out.println(this.toString() + " uses [Get Item]"); actionQueue = new ArrayList<Integer>(); break;
			case 14: if(this.itemToProcess() != null){this.creature.throwItem(itemToProcess, processX, processY, processZ); System.out.println(this.toString() + " uses [Throw "+itemToProcess.toString()+"] at: ("+processX+","+processY+","+processZ+")"); this.setItemToProcess(null); this.setProcessX(null); this.setProcessY(null); this.setProcessZ(null);} actionQueue = new ArrayList<Integer>(); break;
			case 15: if(this.itemToProcess() != null){this.creature.quaff(itemToProcess); System.out.println(this.toString() + " uses [Quaff "+itemToProcess.toString()+"]"); this.setItemToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			case 16: if(this.itemToProcess() != null){this.creature.castSpell(spellToProcess, processX, processY, itemToProcess); System.out.println(this.toString() + " uses [Cast "+itemToProcess.toString()+"] at: ("+processX+","+processY+","+processZ+")"); this.setItemToProcess(null); this.setSpellToProcess(null); this.setProcessX(null); this.setProcessY(null);} actionQueue = new ArrayList<Integer>(); break;
			case 17: this.creature.search(12, false); System.out.println(this.toString() + " uses [Search Area]"); actionQueue = new ArrayList<Integer>(); break;
			case 18: if(this.creatureToProcess() != null){this.creature.rangedWeaponAttack(creatureToProcess); System.out.println(this.toString() + " uses [Ranged Attack: "+creatureToProcess.toString()+"]"); this.setCreatureToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			//
			case 19: this.creature.moveBy(0, 0, -1, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move Upstairs]"); break;
			case 20: this.creature.moveBy(0, 0, 1, false); actionQueue = new ArrayList<Integer>(); System.out.println(this.toString() + " uses [Move Downstairs]"); break;
			default: this.creature.idle(); System.out.println(this.toString() + " uses [Idle]"); break;
		}
	}
	
	public void onUpdate() {
		decodeAction(actionQueue.get(0));

	}
	
	public void onEnter(int x, int y, int z, Tile tile) {
		//System.out.println(Speech.passiveConversation());
		//fixed
		if(tile.isBarsDoor()) {
			boolean hasCorrectKey = false;
			Item correctKey = null;
			for(Item item : creature.inventory().getItems()) {
				try {
					if(item.isIronKey() && item.keyDepth() == z) {
						correctKey = item;
						hasCorrectKey = true;
						break;
					}else {
						
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
			if(hasCorrectKey == true) {
				creature.world().changeTile(x, y, z, Tile.FLOOR);
				creature.notify("The door opens!");
				creature.getRidOf(correctKey, false);
				creature.notify("The key snaps off in the lock...");
			}else if(hasCorrectKey == false){
				creature.notify("The door is locked.");
			}
		}
		if((tile.isOpen() && !tile.isBars()) || (tile.isStairs() && !tile.isBars())) {
			creature.x = x;
			creature.y = y;
			creature.z = z;

			Entity entity = world.entity(x, y, z);
			if (entity != null) {
				entity.onSteppedOnBy(creature);
			}
		}
		else if(tile.isDiggable()) {
			creature.dig(x, y, z);
			
		}
		
	}
	
	public void onNotify(String message) {
		notificationsHandle.addNotification(message, world.turnNumber());
	}
	
	public boolean canSee(int wx, int wy, int wz) {
		//if(creature.magicMapping() > 0) {
			//return true;
		//}else {
			return fov.isVisible(wx, wy, wz);
		//}
		
	}
	
	public Tile rememberedTile(int wx, int wy, int wz) {
		return fov.tile(wx, wy, wz);
	}
	
	public void onGainLevel() {
		if(creature.level()%4 == 0) {
			creature.modifyAttributePoints(2);
		}
		creature.modifySkillPoints(1);
		creature.gainMaxHP();
		creature.gainMaxMana();
	}

	public FieldOfView fov() {
		return fov;
	}
}
