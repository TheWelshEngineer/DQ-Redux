package RogueLike.Main.AI;

import java.util.ArrayList;

import RogueLike.Main.Creature;
import RogueLike.Main.World;
import RogueLike.Main.Factories.ObjectFactory;

public class AlchemistAI extends CreatureAI{
	private Creature player;
	private int brewPotionCooldown;
	
	public AlchemistAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		this.factory = factory;

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(!creature.inventory().isFull() && (int)(Math.round(Math.random()*10)) < 2 && brewPotionCooldown <= 0) {
			//Brew Potion
			actionQueue.add(1);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Brew Potion]");
		}else if(canThrowAt(player) && player.isInvisible() == false) {
			//Brew Potion
			actionQueue.add(2);
			actionQueue.add(500);
			System.out.println(this.toString() + " uses [Throw Potion]");
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
			case 1: this.brewPotion(); break;
			case 2: this.throwPotion(); break;
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
			brewPotionCooldown--;
			decodeAction(actionQueue.get(0));
		}
	}
	
	public void brewPotion() {
		creature.inventory().add(factory.randomNegativePotion(0, false));
		creature.doAction("brew a potion");
		brewPotionCooldown = 3;
	}
	
	public void throwPotion() {
		creature.throwItem(getWeaponToThrow(), player.x, player.y, player.z);
	}

}