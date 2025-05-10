package RogueLike.Main.AI.SkeletonAI;

import java.util.ArrayList;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Screens.TerminalText;

public class SkeletonPyromancerAI extends CreatureAI{
	private static final long serialVersionUID = -6766879922016966350L;

	private Creature player;
	
	private int spellCooldown = 0;
	
	public SkeletonPyromancerAI(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}
	
	public void selectAction() {
		actionQueue = new ArrayList<Integer>();
		if(Dice.d20.roll() == 1) {
			//Blunder
			actionQueue.add(1);
			actionQueue.add(1000);
		}else if(creature.canSee(player.x, player.y, player.z) && !player.affectedBy(Effect.invisible) && this.spellCooldown == 0) {
			//Cast Firebolt
			actionQueue.add(2);
			actionQueue.add(1000);
		}else if(creature.canSee(player.x, player.y, player.z) && !player.affectedBy(Effect.invisible)) {
			//Hunt
			actionQueue.add(3);
			actionQueue.add(1000);
		}else {
			//Wander
			actionQueue.add(4);
			actionQueue.add(1000);
		}
	}
	
	public void decodeAction(int action) {
		switch(action) {
			case 1: this.wander(); System.out.println(this.toString() + " uses [Blunder]"); break;
			case 2: this.castSpell(); System.out.println(this.toString() + " uses [Cast Firebolt]"); this.spellCooldown = 6; break;
			case 3: this.hunt(player); System.out.println(this.toString() + " uses [Hunt Player]"); break;
			default: this.wander(); System.out.println(this.toString() + " uses [Wander]"); break;
		}
	}
	
	public void onUpdate() {
		if((creature.affectedBy(Effect.paralysed))) {
			if((int)(Math.random()*10) < 8) {
				creature.doAction(new TerminalText("struggle to move!"));
				return;
			}else {
				creature.doAction(new TerminalText("move with difficulty"));
			}
		}
		
		if((creature.affectedBy(Effect.frozen))) {
			creature.doAction(new TerminalText("struggle to move!"));
			return;

		}else {
			if(spellCooldown > 0) {
				spellCooldown--;
			}
			decodeAction(actionQueue.get(0));
		}
	}
	
	public void castSpell() {
		player.addEffect((Effect)FactoryManager.getEffectFactory().firebolt(creature).clone());
	}
	
}
