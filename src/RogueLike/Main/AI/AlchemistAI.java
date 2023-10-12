package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class AlchemistAI extends CreatureAI{
	private Creature player;
	private int brewcount;
	
	public AlchemistAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		this.factory = factory;

	}
	
	public void onUpdate() {
		if((creature.paralyzed() == true)) {
			if((int)(Math.random()*10) < 8) {
				creature.doAction("struggle to move!");
				return;
			}else {
				creature.doAction("move with difficulty");
			}
		}
		
		if((creature.frozen() == true)) {
			creature.doAction("struggle to move!");
			return;

		}else {
			if(!creature.inventory().isFull() && (int)(Math.round(Math.random()*10)) < 2 && brewcount < 3) {
				creature.inventory().add(factory.randomNegativePotion(0, false));
				creature.doAction("brew a potion");
				brewcount++;
			}else if(canThrowAt(player) && player.invisible() == false) {
				creature.throwItem(getWeaponToThrow(), player.x, player.y, player.z);
			}else if(creature.canSee(player.x, player.y, player.z) && player.invisible() == false) {
				hunt(player);
			}else {
				wander();
			}
		}
	}

}