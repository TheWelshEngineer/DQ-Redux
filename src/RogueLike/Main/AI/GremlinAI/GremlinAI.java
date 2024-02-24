package RogueLike.Main.AI.GremlinAI;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class GremlinAI extends CreatureAI{
	private Creature player;
	private int arrowsLeft;
	
	public GremlinAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		this.arrowsLeft = Dice.d10.roll()+3;

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(canRangedWeaponAttack(player) && !player.affectedBy(Effect.invisible) && arrowsLeft > 0) {
			//Shoot Arrows
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
			case 1: this.shootArrows(); System.out.println(this.toString() + " uses [Shoot Arrows]"); break;
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
	
	public void shootArrows() {
		creature.rangedWeaponAttack(player);
		arrowsLeft -= 1;
	}

}
