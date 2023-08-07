package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class GremlinAI extends CreatureAI{
	private Creature player;
	private int arrowsLeft;
	
	public GremlinAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		this.arrowsLeft = 7;

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
			if(canRangedWeaponAttack(player) && player.invisible() == false && (int)(Math.random()*10) < 6 && arrowsLeft > 0) {
				creature.rangedWeaponAttack(player);
				arrowsLeft -= 1;
			}else if(creature.canSee(player.x, player.y, player.z) && player.invisible() == false) {
				hunt(player);
			}else {
				wander();
			}
		}
	}

}
