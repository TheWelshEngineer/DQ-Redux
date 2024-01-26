package RogueLike.Main.AI.SlimeAI;

import RogueLike.Main.Creature;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Factories.ObjectFactory;

public class PinkSlimelingAI extends CreatureAI{
	private Creature player;

	
	public PinkSlimelingAI(Creature creature, Creature player, ObjectFactory factory, World world) {
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