package RogueLike.Main.AI.SkeletonAI;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class SkeletonAI extends CreatureAI{
	private Creature player;
	
	public SkeletonAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(Dice.d20.roll() == 1) {
			//Blunder
			actionQueue.add(1);
			actionQueue.add(1000);
		}else if(creature.canSee(player.x, player.y, player.z) && !player.affectedBy(Effect.invisible)) {
			//Hunt
			actionQueue.add(2);
			actionQueue.add(1000);
		}else {
			//Wander
			actionQueue.add(3);
			actionQueue.add(1000);
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.wander(); System.out.println(this.toString() + " uses [Blunder]"); break;
			case 2: this.hunt(player); System.out.println(this.toString() + " uses [Hunt Player]"); break;
			default: this.wander(); System.out.println(this.toString() + " uses [Wander]"); break;
		}
	}
	
	public void onUpdate() {
		if((creature.affectedBy(Effect.paralysed))) {
			if((int)(Math.random()*10) < 8) {
				creature.doAction("struggle to move!");
				return;
			}else {
				creature.doAction("move with difficulty");
			}
		}
		
		if((creature.affectedBy(Effect.frozen))) {
			creature.doAction("struggle to move!");
			return;

		}else {
			decodeAction(actionQueue.get(0));
		}
	}
	
}
