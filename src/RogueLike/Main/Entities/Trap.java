package RogueLike.Main.Entities;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Effect;
import RogueLike.Main.Factories.EffectFactory;
import RogueLike.Main.Screens.TerminalText;
import RogueLike.Main.World;

import java.awt.*;

public abstract class Trap extends Entity {
	protected boolean isRevealed = false;

	public Trap(int x, int y, int z, World world) {
		super(world, x, y, z);
	}

	@Override
	public char glyph() {
		if (isRevealed) {
			return '#';
		}
		else {
			return world.tile(x, y, z).glyph();
		}
	}

	public boolean isRevealed() {return isRevealed;}
	public void reveal() {
		isRevealed = true;
	}

	@Override
	public Color color() {
		if (isRevealed) {
			return trueColor();
		} else {
			return world.tile(x, y, z).color().brighter();
		}
	}

	public abstract Effect effect(EffectFactory factory);
	public abstract Effect exploitEffect(EffectFactory factory);
	protected abstract Color trueColor();

	@Override
	public void onSteppedOnBy(Creature other) {
		if(other.perceptionLevel() >= 3) {
			other.addEffect(exploitEffect(other.factory().effectFactory));
			other.notify("You exploit the trap's mechanism to your benefit!");
		}else {
			other.doAction(new TerminalText("trigger a trap!"));
			other.addEffect(effect(other.factory().effectFactory));
		}
		world.remove(this);
	}
}
