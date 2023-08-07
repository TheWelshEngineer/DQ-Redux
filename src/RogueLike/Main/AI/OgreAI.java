package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class OgreAI extends CreatureAI{
	private Creature player;
	
	public OgreAI(Creature creature, Creature player, ObjectFactory factory, World world) {
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
			/*if(creature.isRaging == 0 && creature.hp() < ((int)creature.maxHP() / 4)) {
				rage();
			}*/
			if(canUseBetterEquipment()) {
				useBetterEquipment();
			}else if(canRangedWeaponAttack(player) && player.invisible() == false) {
				creature.rangedWeaponAttack(player);
			}else if(canThrowAt(player) && player.invisible() == false) {
				creature.throwItem(getWeaponToThrow(), player.x, player.y, player.z);
			}else if(creature.canSee(player.x, player.y, player.z) && player.invisible() == false) {
				hunt(player);
			}else if(canPickup()){
				creature.pickup();
			}else {
				wander();
			}
		}
	}

}
