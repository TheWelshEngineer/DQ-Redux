package RogueLike.Main.Entities;

import java.awt.Color;

import RogueLike.Main.Effect;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Screens.TerminalText;

public abstract class Trap extends Entity {
	protected boolean isRevealed = false;

	public Trap(int x, int y, int z) {
		super(x, y, z);
	}

	@Override
	public char glyph() {
		if (isRevealed) {
			return '#';
		}
		else {
			return World.tile(x, y, z).glyph();
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
			return World.tile(x, y, z).color().brighter();
		}
	}

	public abstract Effect effect();
	public abstract Effect exploitEffect();
	protected abstract Color trueColor();

	@Override
	public void onSteppedOnBy(Creature other) {
		if(other.perceptionLevel() >= 3) {
			other.addEffect(exploitEffect());
			other.notify("You exploit the trap's mechanism to your benefit!");
		}else {
			other.doAction(new TerminalText("trigger a trap!"));
			other.addEffect(effect());
		}
		World.remove(this);
	}
}
