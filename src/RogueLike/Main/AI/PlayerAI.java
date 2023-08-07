package RogueLike.Main.AI;

import java.util.List;

import RogueLike.Main.Creature;
import RogueLike.Main.FieldOfView;
import RogueLike.Main.Item;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.Tile;
import RogueLike.Main.World;

public class PlayerAI extends CreatureAI{
	
	private List<String> messages;
	private FieldOfView fov;
	
	public PlayerAI(Creature creature, List<String> messages, FieldOfView fov, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.messages = messages;
		this.fov = fov;
		
	}
	
	public void onEnter(int x, int y, int z, Tile tile) {
		//System.out.println(Speech.passiveConversation());
		//fixed
		if(tile.isBarsDoor()) {
			boolean hasCorrectKey = false;
			Item correctKey = null;
			for(Item item : creature.inventory().getItems()) {
				try {
					if(item.isKey() > 0 && item.keyDepth() == z) {
						correctKey = item;
						hasCorrectKey = true;
						break;
					}else {
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
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
