package RogueLike.Main.AI;

import java.util.ArrayList;

import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class OgreAI extends CreatureAI{
	private Creature player;
	
	public OgreAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(canUseBetterEquipment()) {
			//Burst
			actionQueue.add(1);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Change Equipment]");
		}else if(canThrowAt(player) && player.isInvisible() == false) {
			//Throw Attack
			actionQueue.add(2);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Throw Attack]");
		}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
			//Hunt
			actionQueue.add(3);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Hunt Player]");
		}else if(canPickup()) {
			//Pick Up
			actionQueue.add(4);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Pick Up]");
		}else{
			//Wander
			actionQueue.add(5);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Wander]");
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.useBetterEquipment(); break;
			case 2: creature.throwItem(getWeaponToThrow(), player.x, player.y, player.z); break;
			case 3: this.hunt(player); break;
			case 4: creature.pickup(); break;
			default: this.wander(); break;
		}
	}
	
	public void onUpdate() {
		if((creature.isParalyzed() == true)) {
			if((int)(Math.random()*10) < 8) {
				creature.doAction("struggle to move!");
				return;
			}else {
				creature.doAction("move with difficulty");
			}
		}
		
		if((creature.isFrozen() == true)) {
			creature.doAction("struggle to move!");
			return;

		}else {
			decodeAction(actionQueue.get(0));
		}
	}

}
