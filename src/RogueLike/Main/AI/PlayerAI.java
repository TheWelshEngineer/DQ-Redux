package RogueLike.Main.AI;

import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.FieldOfView;
import RogueLike.Main.Tile;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class PlayerAI extends CreatureAI{
	
	private List<String> messages;
	private FieldOfView fov;
	
	public PlayerAI(Creature creature, List<String> messages, FieldOfView fov, ObjectFactory factory, World world) {
		super(creature, factory, world);
		actionQueue = new ArrayList<Integer>();
		this.messages = messages;
		this.fov = fov;
		
	}
	

	
	public void selectAction() {
		
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.creature.moveBy(-1, 0, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 2: this.creature.moveBy(1, 0, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 3: this.creature.moveBy(0,-1, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 4: this.creature.moveBy(0, 1, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 5: this.creature.moveBy(-1,-1, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 6: this.creature.moveBy(1,-1, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 7: this.creature.moveBy(-1, 1, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 8: this.creature.moveBy(1, 1, 0, false); actionQueue = new ArrayList<Integer>(); break;
			case 9: this.creature.idle(); break;
			//
			//TODO nonmovement player actions
			case 10: if(this.itemToProcess() != null){this.creature.drop(itemToProcess); this.setItemToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			case 11: if(this.itemToProcess() != null){this.creature.eat(itemToProcess); this.setItemToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			case 12: if(this.itemToProcess() != null){this.creature.equip(itemToProcess); this.setItemToProcess(null);} actionQueue = new ArrayList<Integer>(); break;
			case 13: this.creature.pickup(); actionQueue = new ArrayList<Integer>(); break;
			
			
			//
			default: this.creature.idle(); break;
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
				creature.getRidOf(correctKey);
				creature.notify("The key snaps off in the lock...");
			}else if(hasCorrectKey == false){
				creature.notify("The door is locked.");
			}
		}
		if((tile.isOpen() && !tile.isBars()) || (tile.isStairs() && !tile.isBars())) {
			creature.x = x;
			creature.y = y;
			creature.z = z;
		}
		else if(tile.isDiggable()) {
			creature.dig(x, y, z);
			
		}
		
	}
	
	public void onNotify(String message) {
		messages.add(message);
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

}
