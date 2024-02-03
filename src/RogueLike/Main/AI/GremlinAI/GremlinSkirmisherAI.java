package RogueLike.Main.AI.GremlinAI;

import java.util.ArrayList;

import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class GremlinSkirmisherAI extends CreatureAI{
	private Creature player;
	private int comboAttackCooldown = 0;
	
	public GremlinSkirmisherAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false && !super.isAdjacentTo(player)) {
			//Fast Hunt Player
			actionQueue.add(1);
			actionQueue.add(500);
		}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false && super.isAdjacentTo(player) && comboAttackCooldown == 0) {
			//Hunt
			actionQueue.add(2);
			actionQueue.add(1000);
		}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
			//Hunt
			actionQueue.add(3);
			actionQueue.add(1000);
		}else {
			//Wander
			actionQueue.add(4);
			actionQueue.add(1000);
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.hunt(player); System.out.println(this.toString() + " uses [Fast Hunt Player]"); break;
			case 2: this.comboAttack(); System.out.println(this.toString() + " uses [Combo Attack]"); break;
			case 3: this.hunt(player); System.out.println(this.toString() + " uses [Hunt Player]"); break;
			default: this.wander(); System.out.println(this.toString() + " uses [Wander]"); break;
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
			comboAttackCooldown--;
			decodeAction(actionQueue.get(0)); 
		}
	}
	
	public void comboAttack() {
		hunt(player);
		hunt(player);
		hunt(player);
		comboAttackCooldown = 5;
	}

}
