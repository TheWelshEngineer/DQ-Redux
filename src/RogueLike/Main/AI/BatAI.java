package RogueLike.Main.AI;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class BatAI extends CreatureAI{

	public BatAI(Creature creature, ObjectFactory factory, World world) {
		super(creature, factory, world);
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

		}
		decodeAction(actionQueue.get(0));
		
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(Dice.d100.roll() > 50) {
			//Double wander
			actionQueue.add(1);
			actionQueue.add(1000);
		}else {
			//Fast wander
			actionQueue.add(2);
			actionQueue.add(500);
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.wander(); this.wander(); System.out.println(this.toString() + " uses [Double Wander]"); break;
			case 2: this.wander(); System.out.println(this.toString() + " uses [Fast Wander]"); break;
			default: this.wander(); System.out.println(this.toString() + " uses [Wander]"); break;
		}
	}

}
