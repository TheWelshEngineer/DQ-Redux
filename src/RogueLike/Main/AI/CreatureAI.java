package RogueLike.Main.AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Entity;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.LevelUpController;
import RogueLike.Main.Path;
import RogueLike.Main.Spell;
import RogueLike.Main.Tile;
import RogueLike.Main.World;
import RogueLike.Main.AoE.Line;
import RogueLike.Main.AoE.Point;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class CreatureAI {
	
	protected Creature creature;
	private Map<String, String> itemNames;
	public ObjectFactory factory;
	public World world;
	
	protected ArrayList<Integer> actionQueue = new ArrayList<Integer>();
	public ArrayList<Integer> actionQueue(){
		return actionQueue;
	}
	
	protected Item itemToProcess = null;
	public Item itemToProcess() {
		return itemToProcess;
	}
	public void setItemToProcess(Item item) {
		itemToProcess = item;
	}
	
	protected Spell spellToProcess = null;
	public Spell spellToProcess() {
		return spellToProcess;
	}
	public void setSpellToProcess(Spell spell) {
		spellToProcess = spell;
	}
	
	protected Creature creatureToProcess = null;
	public Creature creatureToProcess() {
		return creatureToProcess;
	}
	public void setCreatureToProcess(Creature creature) {
		creatureToProcess = creature;
	}
	
	protected Integer processX = null;
	public Integer processX() {
		return processX;
	}
	public void setProcessX(Integer value) {
		processX = value;
	}
	
	protected Integer processY = null;
	public Integer processY() {
		return processY;
	}
	public void setProcessY(Integer value) {
		processY = value;
	}
	
	protected Integer processZ = null;
	public Integer processZ() {
		return processZ;
	}
	public void setProcessZ(Integer value) {
		processZ = value;
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
			if(tile == Tile.PIT && (!creature.affectedBy(Effect.levitating) && !creature.isFlying())) {
				
			}else {
				creature.x = x;
				creature.y = y;
				creature.z = z;

				Entity entity = world.entity(x, y, z);
				if (entity != null) {
					entity.onSteppedOnBy(creature);
				}
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
			if(item == null || item.thrownDamageDice() != null || creature.weapon() == item || creature.armor() == item || creature.shield() == item || creature.ring() == item) {
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
		int currentWeaponRating = 0;
		if(creature.weapon() != null) {
			currentWeaponRating += creature.weapon().damageDice().toInt();
			if(creature.weapon().rangedDamageDice() != null) {
				currentWeaponRating += creature.weapon().rangedDamageDice().toInt();
			}
		}
		int currentArmorRating = creature.armor() == null ? 0 : creature.armor().armorClass();
		
		for(Item item : creature.inventory().getItems()) {
			if(item == null) {
				continue;
			}
			
			int itemWeaponRating = 0;
			if(item.damageDice() != null) {
				itemWeaponRating += item.damageDice().toInt();
				if(item.rangedDamageDice() != null) {
					itemWeaponRating += item.rangedDamageDice().toInt();
				}
			}
			
			boolean isArmor = item.isArmor();
			
			if(!isArmor && itemWeaponRating > currentWeaponRating || isArmor && item.armorClass() > currentArmorRating) {
				return true;
			}
		}
		return false;
	}
	
	protected void useBetterEquipment() {
		int currentWeaponRating = 0;
		if(creature.weapon() != null) {
			currentWeaponRating += creature.weapon().damageDice().toInt();
			if(creature.weapon().rangedDamageDice() != null) {
				currentWeaponRating += creature.weapon().rangedDamageDice().toInt();
			}
		}
		int currentArmorRating = creature.armor() == null ? 0 : creature.armor().armorClass();
		
		for(Item item : creature.inventory().getItems()) {
			if(item == null) {
				continue;
			}
			int itemWeaponRating = 0;
			if(item.damageDice() != null) {
				itemWeaponRating += item.damageDice().toInt();
				if(item.rangedDamageDice() != null) {
					itemWeaponRating += item.rangedDamageDice().toInt();
				}
			}
			
			boolean isArmor = item.isArmor();
			
			if(!isArmor && itemWeaponRating > currentWeaponRating || isArmor && item.armorClass() > currentArmorRating) {
				creature.equip(item);
			}
		}
		
	}

	public void playerAIMoveWest() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(1);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveEast() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(2);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveNorth() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(3);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveSouth() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(4);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveNorthWest() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(5);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveNorthEast() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(6);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveSouthWest() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(7);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveSouthEast() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(8);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveIdle() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(9);
		actionQueue.add(1000);
	}
	
	public void playerAIDropItem(Item toDrop) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(10);
		actionQueue.add(1000);
		this.setItemToProcess(toDrop);
	}
	
	public void playerAIEatItem(Item toEat) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(11);
		actionQueue.add(1000);
		this.setItemToProcess(toEat);
	}
	
	public void playerAIEquipItem(Item toEquip) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(12);
		actionQueue.add(1000);
		this.setItemToProcess(toEquip);
	}
	
	public void playerAIGetItem() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(13);
		actionQueue.add(1000);
	}
	
	public void playerAIThrowItem(Item toThrow, int x, int y, int z) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(14);
		actionQueue.add(1000);
		this.setItemToProcess(toThrow);
		this.setProcessX(x);
		this.setProcessY(y);
		this.setProcessZ(z);
	}
	
	public void playerAIQuaffItem(Item toQuaff) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(15);
		actionQueue.add(1000);
		this.setItemToProcess(toQuaff);
	}
	
	public void playerAICastSpell(Item toRead, Spell toCast, int x, int y) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(16);
		actionQueue.add(1000);
		this.setItemToProcess(toRead);
		this.setSpellToProcess((Spell) toCast.clone());
		this.setProcessX(x);
		this.setProcessY(y);
	}
	
	public void playerAISearchArea() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(17);
		actionQueue.add(3000);
	}
	
	public void playerAIRangedAttack(Creature toAttack) {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(18);
		actionQueue.add(1000);
		this.setCreatureToProcess(toAttack);
	}
	
	public void playerAIMoveUpStairs() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(19);
		actionQueue.add(1000);
	}
	
	public void playerAIMoveDownStairs() {
		actionQueue = new ArrayList<Integer>();
		actionQueue.add(20);
		actionQueue.add(1000);
	}
}
