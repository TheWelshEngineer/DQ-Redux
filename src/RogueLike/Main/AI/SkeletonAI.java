package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class SkeletonAI extends CreatureAI{
	private Creature player;
	
	public SkeletonAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
	}
	
	public void onUpdate() {
		if(Math.random() < 0.2) {
			return;
		}
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
			if(creature.canSee(player.x, player.y, player.z) && player.invisible() == false) {
				hunt(player);
			}else {
				wander();
			}
		}
	}
	
}
