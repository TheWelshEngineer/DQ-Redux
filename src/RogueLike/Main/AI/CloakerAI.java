package RogueLike.Main.AI;

import java.util.ArrayList;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtraColors;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class CloakerAI extends CreatureAI{
	private Creature player;
	
	public CloakerAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(creature.hp() < (creature.maxHP() / 2)) {
			//Lose Invisible
			actionQueue.add(1);
			actionQueue.add(0);
		}else if(creature.hp() <= (creature.maxHP() / 2)) {
			//Become Invisible
			actionQueue.add(2);
			actionQueue.add(500);
		}else if(creature.canSee(player.x, player.y, player.z) && !player.affectedBy(Effect.invisible)) {
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
			case 1: this.loseInvisible(); System.out.println(this.toString() + " uses [Lose Cloak]"); break;
			case 2: this.becomeInvisible(); System.out.println(this.toString() + " uses [Become Invisible]"); break;
			case 3: this.hunt(player); System.out.println(this.toString() + " uses [Hunt Player]"); break;
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
	
	public void loseInvisible() {
		creature.cureEffectOfType(Effect.invisible);
	}
	
	public void becomeInvisible() {
		Effect invisible = (Effect) this.factory.effectFactory.invisible(100000).clone();
		creature.addEffect(invisible);
	}

}
