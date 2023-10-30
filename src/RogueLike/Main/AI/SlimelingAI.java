package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class SlimelingAI extends CreatureAI{
	private Creature player;

	
	public SlimelingAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;

	}
	
	public void onUpdate() {
		
		if((creature.isFrozen() == true)) {
			creature.doAction("struggle to move!");
			return;

		}else {
			if((creature.isParalyzed() == true)) {
				creature.cureParalysis();
				creature.doAction("break free of paralysis!");
			}
			if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
				hunt(player);
			}else {
				wander();
			}
		}
	}
	
}