package RogueLike.Main.AI.FungusAI;

import java.util.ArrayList;

import RogueLike.Main.Effect;
import RogueLike.Main.World;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Screens.TerminalText;

public class BloodFungusAI extends CreatureAI{

	private static final long serialVersionUID = -1384207131598507928L;
	private int spreadcount;
	private Creature player;

	public BloodFungusAI(Creature creature, Creature player) {
		super(creature);
		this.player = player;
		
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(creature.canSee(player.x, player.y, player.z) && !player.affectedBy(Effect.invisible)) {
			//Hunt
			actionQueue.add(3);
			actionQueue.add(1000);
		}else if(spreadcount < 2 && Math.random() < 0.0025) {
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
			case 3: this.hunt(player); System.out.println(this.toString() + " uses [Hunt Player]"); break;
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
		if (World.getInstance().entity(x, y, creature.z) instanceof Trap) {
			return;
		}
		creature.doAction(new TerminalText("spawn a child"));		
		Creature child = FactoryManager.getCreatureFactory().newBloodFungus(creature.z, player, true);
		child.x = x;
		child.y = y;
		spreadcount++;		
	}
	
	
	
	
	
	

}
