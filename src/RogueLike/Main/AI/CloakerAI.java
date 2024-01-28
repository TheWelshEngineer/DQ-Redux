package RogueLike.Main.AI;

import java.util.ArrayList;

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
			System.out.println(this.toString() + " uses [Lose Invisible]");
		}else if(creature.hp() < (creature.maxHP() / 2)) {
			//Become Invisible
			actionQueue.add(2);
			actionQueue.add(500);
			System.out.println(this.toString() + " uses [Become Invisible]");
		}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
			//Hunt
			actionQueue.add(3);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Hunt Player]");
		}else {
			//Wander
			actionQueue.add(4);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Wander]");
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.loseInvisible(); break;
			case 2: this.becomeInvisible(); break;
			case 3: this.hunt(player); break;
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
	
	public void loseInvisible() {
		creature.setIsInvisible(false);
		creature.changeColor(creature.defaultColor());
		creature.doAction("become visible");
	}
	
	public void becomeInvisible() {
		creature.setIsInvisible(true);
		creature.changeColor(ExtraColors.invisible);
		creature.doAction("become transparent");
	}

}
