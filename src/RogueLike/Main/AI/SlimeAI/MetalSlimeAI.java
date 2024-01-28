package RogueLike.Main.AI.SlimeAI;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Factories.ObjectFactory;

public class MetalSlimeAI extends CreatureAI{
	private Creature player;


	
	public MetalSlimeAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		//this.factory = factory;
		//this.world = world;
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(creature.hp() < (creature.maxHP() / 2)) {
			//Burst
			actionQueue.add(1);
			actionQueue.add(0);
			System.out.println(this.toString() + " uses [Burst]");
		}else if(creature.canSee(player.x, player.y, player.z) && player.isInvisible() == false) {
			//Hunt
			actionQueue.add(2);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Hunt Player]");
		}else {
			//Wander
			actionQueue.add(3);
			actionQueue.add(1000);
			System.out.println(this.toString() + " uses [Wander]");
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.burst(); break;
			case 2: this.hunt(player); break;
			default: this.wander(); break;
		}
	}
	
	private void burst() {
		if(creature.hp() < (creature.maxHP() / 2)) {
            for (int ox = -1; ox < 2; ox++){
                for (int oy = -1; oy < 2; oy++){
                    int nx = creature.x + ox;
                    int ny = creature.y + oy;
                    if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) != null) {
                        continue;
                    }

                    Creature slimeling = factory.newMetalSlimeling(0, player, 0);

                    if (!slimeling.canEnter(nx, ny, creature.z)){
                        world.remove(slimeling);
                        continue;
                    }
                    
                    if (creature.creature(nx, ny, creature.z) != null){
                        world.remove(slimeling);
                        continue;
                    }
                    
                    if (Dice.d10.roll() <= 4){
                        world.remove(slimeling);
                        continue;
                    }
                    
                    slimeling.x = nx;
                    slimeling.y = ny;
                    slimeling.z = creature.z;
                    
                    creature.summon(slimeling);
                    
                }
            }
            creature.doAction("split into slimelings!");
            Damage damage = new Damage(creature.hp(), false, true, Damage.physical, factory.effectFactory, false);
            creature.modifyHP(damage, "");
		}
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
			decodeAction(actionQueue.get(0)); 
		}
	}
	

}
