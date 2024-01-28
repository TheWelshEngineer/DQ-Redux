package RogueLike.Main.AI.SlimeAI;

import java.util.ArrayList;

import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class SlimelingAI extends CreatureAI{
	private Creature player;

	
	public SlimelingAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
			//Hunt
			actionQueue.add(1);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Hunt Player]");
		}else {
			//Wander
			actionQueue.add(2);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Wander]");
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.hunt(player); break;
			default: this.wander(); break;
		}
	}
	
	public void onUpdate() {
		
		if((creature.isFrozen() == true)) {
			creature.doAction("struggle to move!");
			return;

		}else {
			if((creature.isParalyzed() == true)) {
				creature.cureParalysis();
				creature.doAction("break free of paralysis!");
			}
			decodeAction(actionQueue.get(0)); 
		}
	}
	
}