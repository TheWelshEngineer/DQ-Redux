package RogueLike.Main.AI.GremlinAI;

import java.util.ArrayList;

import RogueLike.Main.Effect;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class GremlinAlchemistAI extends CreatureAI{
	private Creature player;
	private int brewPotionCooldown;
	private int turnsWithoutPlayer = 0;
	
	public GremlinAlchemistAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		this.factory = factory;

	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(turnsWithoutPlayer >= 20) {
			//Fall Asleep
			actionQueue.add(5);
			actionQueue.add(1000);
		}else if(!creature.inventory().isFull() && (int)(Math.round(Math.random()*10)) < 2 && brewPotionCooldown <= 0) {
			//Brew Potion
			actionQueue.add(1);
			actionQueue.add(1000);
		}else if(canThrowAt(player) && !player.affectedBy(Effect.invisible)) {
			//Throw Potion
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
			case 1: this.brewPotion(); System.out.println(this.toString() + " uses [Brew Potion]"); break;
			case 2: this.throwPotion(); System.out.println(this.toString() + " uses [Throw Potion]"); break;
			case 3: this.hunt(player); System.out.println(this.toString() + " uses [Hunt Player]"); break;
			case 5: this.creature.sleep();; System.out.println(this.toString() + " uses [Fall Asleep]"); break;
			default: this.wander(); System.out.println(this.toString() + " uses [Wander]"); break;
		}
	}
	
	public void onUpdate() {
		if(creature.isAsleep()) {
			if(this.creature.canSee(player.x(), player.y(), this.creature.z())) {
				int playerStealthRoll = player.dexterityRoll();
				int bonus = 0;
				if(player.stealthLevel() >= 1) {
					bonus = player.proficiencyBonus();
				}
				if(playerStealthRoll+bonus < this.creature.dexterityRoll()) {
					this.creature.wakeup();
					creature.doAction("wake up!");
					
				}
			}else {
				creature.doAction("snore gently");
				return;
			}
		}
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
			brewPotionCooldown--;
			if(!creature.canSee(player.x, player.y, player.z) || player.affectedBy(Effect.invisible)) {
				turnsWithoutPlayer++;
			}
			decodeAction(actionQueue.get(0));
		}
	}
	
	public void brewPotion() {
		creature.inventory().add(factory.randomNegativePotion(0, false));
		creature.doAction("brew a foul potion.");
		brewPotionCooldown = 3;
	}
	
	public void throwPotion() {
		creature.throwItem(getWeaponToThrow(), player.x, player.y, player.z);
	}

}