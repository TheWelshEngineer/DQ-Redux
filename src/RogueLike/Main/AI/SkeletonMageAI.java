package RogueLike.Main.AI;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class SkeletonMageAI extends CreatureAI{
	private Creature player;
	
	private int spellCooldown = 0;
	
	public SkeletonMageAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(Dice.d20.roll() == 1) {
			//Blunder
			actionQueue.add(1);
			actionQueue.add(1000);
		}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false && this.spellCooldown == 0) {
			//Cast Ice Knife
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
			case 1: this.wander(); System.out.println(this.toString() + " uses [Blunder]"); break;
			case 2: this.castSpell(); System.out.println(this.toString() + " uses [Cast Ice Knife]"); this.spellCooldown = 4; break;
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
			if(spellCooldown > 0) {
				spellCooldown--;
			}
			decodeAction(actionQueue.get(0));
		}
	}
	
	public void castSpell() {
		player.addEffect((Effect)factory.effectFactory.iceKnife(creature).clone());
	}
	
}
