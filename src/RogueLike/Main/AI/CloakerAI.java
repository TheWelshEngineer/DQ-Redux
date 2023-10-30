package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ExtraColors;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class CloakerAI extends CreatureAI{
	private Creature player;
	
	public CloakerAI(Creature creature, Creature player, ObjectFactory factory, World world) {
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
			if(creature.hp() < ((int)creature.maxHP() / 2)) {
				creature.setIsInvisible(false);
				creature.changeColor(creature.defaultColor());
				creature.doAction("become visible");
			}
		
			if(creature.isInvisible() == false && creature.hp() >= ((int)creature.maxHP() / 2)) {
				creature.setIsInvisible(true);
				creature.changeColor(ExtraColors.invisible);
				creature.doAction("become transparent");
			}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
				hunt(player);
			}else {
				wander();
			}
		}
	}

}
