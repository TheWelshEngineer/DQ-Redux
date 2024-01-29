package RogueLike.Main.AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RogueLike.Main.ExtraMaths;
import RogueLike.Main.LevelUpController;
import RogueLike.Main.Line;
import RogueLike.Main.Path;
import RogueLike.Main.Point;
import RogueLike.Main.Tile;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class CreatureAI {
	
	protected Creature creature;
	private Map<String, String> itemNames;
	public ObjectFactory factory;
	public World world;
	
	protected ArrayList<Integer> actionQueue = new ArrayList<Integer>();
	
	protected Item itemToProcess = null;
	public Item itemToProcess() {
		return itemToProcess;
	}
	public void setItemToProcess(Item item) {
		itemToProcess = item;
	}
	
	public void selectAction() {
		
	}
	
	public void decodeAction(int action) {

	}
	
	public int getActionSpeed() {
		return actionQueue.get(1);
	}
	
	public CreatureAI(Creature creature, ObjectFactory factory, World world){
		this.creature = creature;
		this.creature.setCreatureAI(this);
		this.itemNames = new HashMap<String, String>();
		this.factory = factory;
		this.world = world;
		actionQueue.add(1);
		actionQueue.add(1000);
		
	}
	
	public String getName(Item item) {
		String name = itemNames.get(item.name());
		return name == null ? item.getAppearance() : name;
	}
	
	public void setName(Item item, String name) {
		itemNames.put(item.name(), name);
	}
	
	public void onEnter(int x, int y, int z, Tile tile) {
		//fixed
		if((tile.isOpen() && !tile.isBars()) || (tile.isStairs() && !tile.isBars())) {
			if(tile == Tile.PIT && (!creature.isLevitating() && !creature.isFlying())) {
				
			}else {
				creature.x = x;
				creature.y = y;
				creature.z = z;
			}
			
		}
		else {
			creature.doAction("bump into a wall");
		}
	}
	
	public void wander() {
		int mx = (int)(Math.random()*3) - 1;
		int my = (int)(Math.random()*3) - 1;
		
		Creature other = creature.creature(creature.x + mx, creature.y + my, creature.z);
		
		if((other != null && other.glyph() == creature.glyph()) || (other != null && other.isContainer() == true)) {
			if(other != null && other.glyph() == creature.glyph() && creature.social()) {
				creature.doAction("say '%s' to the %s", creature.talkToOther(), other.name());
			}else {
				return;
			}
			return;
		}
		else {
			switch(ExtraMaths.diceRoll(1, 10)) {
				case 1: 
					if(creature.social()) {
						creature.doAction("say '%s' out loud", creature.talkToSelf());
					}else {
						creature.moveBy(mx, my, 0, false);
					}
					break;
				default: creature.moveBy(mx, my, 0, false);
			}
		}
		
		
	}
	
	public void onUpdate() {
		decodeAction(actionQueue.get(0));
	}
	
	public void onNotify(String message) {
		
	}
	
	public boolean canSee(int wx, int wy, int wz) {
		if (creature.z != wz)
			return false;
		
		if ((creature.x-wx)*(creature.x-wx) + (creature.y-wy)*(creature.y-wy) > creature.visionRadius()*creature.visionRadius())
			return false;
		
		for (Point p : new Line(creature.x, creature.y, wx, wy)){
			//fixed
			if ((creature.realTile(p.x, p.y, wz).isGround() && !creature.realTile(p.x, p.y, wz).isObscuring()) || creature.realTile(p.x, p.y, wz).isStairs() || p.x == wx && p.y == wy)
				continue;
			
			return false;
		}
		
		return true;
	}
	
	public boolean isAdjacentTo(Creature target) {
		if(creature.z != target.z) {
			return false;
		}
		if(((target.x >= 0 && target.x <= creature.x+1) || (target.x <= 0 && target.x >= creature.x-1)) && ((target.y >= 0 && target.y <= creature.y+1) || (target.y <= 0 && target.y >= creature.y-1))) {
			return true;
		}
		return false;
	}

	public void onGainLevel() {
		new LevelUpController().autoLevelUp(creature);
		creature.gainMaxHP();
		creature.gainMaxMana();
		
	}
	
	public Tile rememberedTile(int wx, int wy, int wz) {
		return Tile.UNKNOWN;
	}

	public void hunt(Creature target) {
		List<Point> points = new Path(creature, target.x, target.y).points();
		
		int mx;
		if(creature != null || points.size() == 0) {
			try {
				mx = points.get(0).x - creature.x;
			} catch (Exception e) {
				//e.printStackTrace();
				mx = 0;
			}
		}else {
			mx = 0;
		}
		
		
		int my;
		if(creature != null || points.size() == 0) {
			try {
				my = points.get(0).y - creature.y;
			} catch (Exception e) {
				//e.printStackTrace();
				my = 0;
			}
		}else {
			my = 0;
		}
			
		creature.moveBy(mx, my, 0, false);
	}
	
	protected boolean canRangedWeaponAttack(Creature other) {
		return creature.weapon() != null && creature.weapon().rangedDamageDice() != null && creature.canSee(other.x, other.y, other.z);
	}
	
	protected boolean canThrowAt(Creature other) {
		return creature.canSee(other.x, other.y, other.z) && getWeaponToThrow() != null;
	}
	
	protected Item getWeaponToThrow() {
		Item toThrow = null;
		for(Item item : creature.inventory().getItems()){
			if(item == null || creature.weapon() == item || creature.armor() == item || creature.shield() == item || creature.ring() == item) {
				continue;
			}
			if(toThrow == null || item.thrownDamageDice().toInt() > toThrow.damageDice().toInt()) {
				toThrow = item;
			}
			
		}
		return toThrow;
	}
	
	protected boolean canPickup() {
		return creature.item(creature.x, creature.y, creature.z) != null && !creature.inventory().isFull(); 
	}
	
	protected boolean canUseBetterEquipment() {
		int currentWeaponRating = creature.weapon() == null ? 0 : creature.weapon().damageDice().toInt() + creature.weapon().rangedDamageDice().toInt();
		int currentArmorRating = creature.armor() == null ? 0 : creature.armor().armorClass();
		
		for(Item item : creature.inventory().getItems()) {
			if(item == null) {
				continue;
			}
			
			boolean isArmor = item.isArmor();
			
			if(item.damageDice().toInt() + item.rangedDamageDice().toInt() > currentWeaponRating || isArmor && item.armorClass() > currentArmorRating) {
				return true;
			}
		}
		return false;
	}
	
	protected void useBetterEquipment() {
		int currentWeaponRating = creature.weapon() == null ? 0 : creature.weapon().damageDice().toInt() + creature.weapon().rangedDamageDice().toInt();
		double currentArmorRating = creature.armor() == null ? 0 : creature.armor().armorClass();
		
		for(Item item : creature.inventory().getItems()) {
			if(item == null) {
				continue;
			}
			
			boolean isArmor = item.isArmor();
			
			if(item.damageDice().toInt() + item.rangedDamageDice().toInt() > currentWeaponRating || isArmor && item.armorClass() > currentArmorRating) {
				creature.equip(item);
			}
		}
		
	}

	public void playerAIMoveWest() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(1);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack West]");
	}
	
	public void playerAIMoveEast() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(2);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack East]");
	}
	
	public void playerAIMoveNorth() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(3);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack North]");
	}
	
	public void playerAIMoveSouth() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(4);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack South]");
	}
	
	public void playerAIMoveNorthWest() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(5);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack Northwest]");
	}
	
	public void playerAIMoveNorthEast() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(6);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack Northeast]");
	}
	
	public void playerAIMoveSouthWest() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(7);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack Southwest]");
	}
	
	public void playerAIMoveSouthEast() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(8);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Move/Attack Southeast]");
	}
	
	public void playerAIMoveIdle() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(9);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Idle]");
	}
	
	public void playerAIDropItem(Item toDrop) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(10);
		actionQueue.add(1000);
		this.setItemToProcess(toDrop);
		System.out.println(this.toString() + " uses [Drop "+toDrop.toString()+"]");
	}
	
	public void playerAIEatItem(Item toEat) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(11);
		actionQueue.add(1000);
		this.setItemToProcess(toEat);
		System.out.println(this.toString() + " uses [Eat "+toEat.toString()+"]");
	}
	
	public void playerAIEquipItem(Item toEquip) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(12);
		actionQueue.add(1000);
		this.setItemToProcess(toEquip);
		System.out.println(this.toString() + " uses [Equip "+toEquip.toString()+"]");
	}
	
	public void playerAIGetItem() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(13);
		actionQueue.add(1000);
		System.out.println(this.toString() + " uses [Get Item]");
	}
	
	
	

}
