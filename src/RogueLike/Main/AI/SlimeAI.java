package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.World;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Factories.ObjectFactory;

public class SlimeAI extends CreatureAI{
	private Creature player;


	
	public SlimeAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		//this.factory = factory;
		//this.world = world;
	}
	
	public void onUpdate() {
		if(Math.random() < 0.2) {
			wander();
		}
		
		if((creature.isFrozen() == true)) {
			creature.doAction("struggle to move!");
			return;

		}else {
			if((creature.isParalyzed() == true)) {
				creature.cureParalysis();
				creature.doAction("break free of paralysis!");
			}
			if(creature.hp() < (creature.maxHP() / 2)) {
                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) != null) {
                            continue;
                        }

                        Creature bat = factory.newPinkSlimeling(0, player, 0);

                        if (!bat.canEnter(nx, ny, creature.z)){
                            world.remove(bat);
                            continue;
                        }
                        
                        if (creature.creature(nx, ny, creature.z) != null){
                            world.remove(bat);
                            continue;
                        }
                        
                        if ((int)(Math.random()*10) < 4){
                            world.remove(bat);
                            continue;
                        }
                        
                        bat.x = nx;
                        bat.y = ny;
                        bat.z = creature.z;
                        
                        creature.summon(bat);
                        
                    }
                }
                creature.doAction("split into slimelings!");
                Damage damage = new Damage(creature.hp(), false, true, Damage.physical, factory.effectFactory, false);
                creature.modifyHP(damage, "");
	            
			}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
				hunt(player);
			}else {
				wander();
			}
		}
	}
	

}
