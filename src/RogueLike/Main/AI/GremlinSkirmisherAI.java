package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class GremlinSkirmisherAI extends CreatureAI{
	private Creature player;
	
	public GremlinSkirmisherAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;

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
			if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
				hunt(player);
				hunt(player);
			}else {
				wander();
			}
		}
	}

}
