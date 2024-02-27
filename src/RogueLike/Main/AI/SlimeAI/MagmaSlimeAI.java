package RogueLike.Main.AI.SlimeAI;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Factories.ObjectFactory;

public class MagmaSlimeAI extends CreatureAI{
	private Creature player;


	
	public MagmaSlimeAI(Creature creature, Creature player, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.player = player;
		//this.factory = factory;
		//this.world = world;
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(creature.hp() < (creature.maxHP() / 2)) {
			//Explosive Burst
			actionQueue.add(1);
			actionQueue.add(2000);
		}else if(creature.canSee(player.x, player.y, player.z) && !player.affectedBy(Effect.invisible)) {
			//Hunt
			actionQueue.add(2);
			actionQueue.add(1000);
		}else {
			//Wander
			actionQueue.add(3);
			actionQueue.add(1000);
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.burst(); System.out.println(this.toString() + " uses [Explosive Burst]"); break;
			case 2: this.hunt(player); System.out.println(this.toString() + " uses [Hunt Player]"); break;
			default: this.wander(); System.out.println(this.toString() + " uses [Wander]"); break;
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

                    Creature slimeling = factory.creatureFactory.newMagmaSlimeling(0, player, false);

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
            creature.addEffect((Effect)factory.effectFactory.fireball(5).clone());
            creature.doAction("explode into slimelings!");
            Damage damage = new Damage(creature.hp(), false, true, Damage.physical, factory.effectFactory, false);
            creature.modifyHP(damage, "");
		}
	}
	
	public void onUpdate() {
		
		if((creature.affectedBy(Effect.frozen))) {
			creature.doAction("struggle to move!");
			return;

		}else {
			if((creature.affectedBy(Effect.paralysed))) {
				creature.cureEffectOfType(Effect.paralysed);
				creature.doAction("break free of paralysis!");
			}
			decodeAction(actionQueue.get(0)); 
		}
	}
	

}

