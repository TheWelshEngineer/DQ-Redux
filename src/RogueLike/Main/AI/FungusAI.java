package RogueLike.Main.AI;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.World;

public class FungusAI extends CreatureAI{
	private int spreadcount;

	public FungusAI(Creature creature, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.factory = factory;
		
	}
	
	public void onUpdate() {
		if(creature.paralyzed() == false && creature.frozen() == false) {
			if(spreadcount < 3 && Math.random() < 0.0025) {
				spread();
			}
		}
	}
	
	private void spread() {
		int x = creature.x + (int)(Math.random()*11) - 6;
		int y = creature.y + (int)(Math.random()*11) - 6;
		
		if(!creature.canEnter(x,y,creature.z) || creature.tile(x, y, creature.z).isBars()) {
			return;
			
		}
		
		if(creature.item(x,y,creature.z) != null && creature.item(x,y,creature.z).isTrap()> 0) {
			return;
			
		}
		
		creature.doAction("spawn a child");
		
		Creature child = factory.newFungus(creature.z, 1);
		child.x = x;
		child.y = y;
		spreadcount++;
			
		
		
		
		
	}
	
	
	
	
	
	

}
