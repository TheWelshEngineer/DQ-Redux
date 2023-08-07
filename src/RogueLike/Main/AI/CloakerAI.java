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
			if(creature.hp() < ((int)creature.maxHP() / 2)) {
				creature.modifyInvisible(false);
				creature.changeColor(creature.defaultColor());
				creature.doAction("become visible");
			}
		
			if(creature.invisible() == false && creature.hp() >= ((int)creature.maxHP() / 2)) {
				creature.modifyInvisible(true);
				creature.changeColor(ExtraColors.invisible);
				creature.doAction("become transparent");
			}else if(creature.canSee(player.x, player.y, player.z) && player.invisible() == false) {
				hunt(player);
			}else {
				wander();
			}
		}
	}

}
