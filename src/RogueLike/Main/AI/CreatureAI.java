package RogueLike.Main.AI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RogueLike.Main.Creature;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Item;
import RogueLike.Main.LevelUpController;
import RogueLike.Main.Line;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.Path;
import RogueLike.Main.Point;
import RogueLike.Main.Tile;
import RogueLike.Main.World;

public class CreatureAI {
	
	protected Creature creature;
	private Map<String, String> itemNames;
	public ObjectFactory factory;
	public World world;
	
	public CreatureAI(Creature creature, ObjectFactory factory, World world){
		this.creature = creature;
		this.creature.setCreatureAI(this);
		this.itemNames = new HashMap<String, String>();
		this.factory = factory;
		this.world = world;
		
	}
	
	public String getName(Item item) {
		String name = itemNames.get(item.name());
		return name == null ? item.appearance() : name;
	}
	
	public void setName(Item item, String name) {
		itemNames.put(item.name(), name);
	}
	
	public void onEnter(int x, int y, int z, Tile tile) {
		//fixed
		if((tile.isOpen() && !tile.isBars()) || (tile.isStairs() && !tile.isBars())) {
			if(tile == Tile.PIT && (!creature.levitating() && creature.flying() == 0)) {
				
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
		return creature.weapon() != null && creature.weapon().rangedDamageDice() > 0 && creature.canSee(other.x, other.y, other.z);
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
			if(toThrow == null || item.thrownDamageDice() > toThrow.damageDice()) {
				toThrow = item;
			}
			
		}
		return toThrow;
	}
	
	protected boolean canPickup() {
		return creature.item(creature.x, creature.y, creature.z) != null && !creature.inventory().isFull(); 
	}
	
	protected boolean canUseBetterEquipment() {
		int currentWeaponRating = creature.weapon() == null ? 0 : creature.weapon().damageDice() + creature.weapon().rangedDamageDice();
		int currentArmorRating = creature.armor() == null ? 0 : creature.armor().armorClass();
		
		for(Item item : creature.inventory().getItems()) {
			if(item == null) {
				continue;
			}
			
			boolean isArmor = item.isArmor() == 1;
			
			if(item.damageDice() + item.rangedDamageDice() > currentWeaponRating || isArmor && item.armorClass() > currentArmorRating) {
				return true;
			}
		}
		return false;
	}
	
	protected void useBetterEquipment() {
		int currentWeaponRating = creature.weapon() == null ? 0 : creature.weapon().damageDice() + creature.weapon().rangedDamageDice();
		double currentArmorRating = creature.armor() == null ? 0 : creature.armor().armorClass();
		
		for(Item item : creature.inventory().getItems()) {
			if(item == null) {
				continue;
			}
			
			boolean isArmor = item.isArmor() == 1;
			
			if(item.damageDice() + item.rangedDamageDice() > currentWeaponRating || isArmor && item.armorClass() > currentArmorRating) {
				creature.equip(item);
			}
		}
		
	}

	/*public void rage() {
		if(creature.isRaging == 0) {
			creature.doAction("burst into a berserker rage!");
			creature.gainAttackValueOrc();
			creature.modifyIsRaging(1);
		}else {
			creature.notify("You can't rage right now.");
		}
	}*/
	
	
	

}
