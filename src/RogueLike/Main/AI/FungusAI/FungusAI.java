package RogueLike.Main.AI.FungusAI;

import java.util.ArrayList;

import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Screens.TerminalText;

public class FungusAI extends CreatureAI{
	private int spreadcount;

	public FungusAI(Creature creature, ObjectFactory factory, World world) {
		super(creature, factory, world);
		this.factory = factory;
		
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(spreadcount < 3 && Math.random() < 0.0025) {
			//Spread
			actionQueue.add(1);
			actionQueue.add(0);
		}else {
			//Idle
			actionQueue.add(2);
			actionQueue.add(1000);
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.spread(); System.out.println(this.toString() + " uses [Spread Mycelium]"); break;
			case 2: System.out.println(this.toString() + " uses [Idle]"); break;
			default: System.out.println(this.toString() + " uses [Idle]"); break;
		}
	}
	
	public void onUpdate() {
		if(!creature.affectedBy(Effect.frozen)) {
			decodeAction(actionQueue.get(0)); 
		}
	}
	
	private void spread() {
		int x = creature.x + (int)(Math.random()*11) - 6;
		int y = creature.y + (int)(Math.random()*11) - 6;
		if(!creature.canEnter(x,y,creature.z) || creature.tile(x, y, creature.z).isBars()) {
			return;
			
		}
		// Don't spread onto traps
		if (world.entity(x, y, creature.z) instanceof Trap) {
			return;
		}
		creature.doAction(new TerminalText("spawn a child"));		
		Creature child = factory.creatureFactory.newFungus(creature.z, true);
		child.x = x;
		child.y = y;
		spreadcount++;		
	}
	
	
	
	
	
	

}
