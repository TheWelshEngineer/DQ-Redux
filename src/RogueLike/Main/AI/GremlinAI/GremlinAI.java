package RogueLike.Main.AI.GremlinAI;

import java.util.ArrayList;

import RogueLike.Main.Creature;
import RogueLike.Main.Dice;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Factories.ObjectFactory;

public class GremlinAI extends CreatureAI{
	private Creature player;
	private int arrowsLeft;
	
	public GremlinAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		this.arrowsLeft = Dice.d10.roll();

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(canRangedWeaponAttack(player) && player.isInvisible() == false && arrowsLeft > 0) {
			//Shoot Arrows
			actionQueue.add(1);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Shoot Arrows]");
		}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
			//Hunt
			actionQueue.add(2);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Hunt Player]");
		}else {
			//Wander
			actionQueue.add(3);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Wander]");
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.shootArrows(); break;
			case 2: this.hunt(player); break;
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
	
	public void shootArrows() {
		creature.rangedWeaponAttack(player);
		arrowsLeft -= 1;
	}

}
