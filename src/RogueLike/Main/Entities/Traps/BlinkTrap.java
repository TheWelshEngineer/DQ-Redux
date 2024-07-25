package RogueLike.Main.Entities.Traps;

import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.EffectFactory;
import RogueLike.Main.World;

import java.awt.*;

public class BlinkTrap extends Trap {

    public BlinkTrap(int x, int y, int z, World world) {
        super(x, y, z, world);
    }

    @Override
    public Effect effect(EffectFactory factory) {
        return factory.blink();
    }

    @Override
    public Effect exploitEffect(EffectFactory factory) {
        return factory.bounce();
    }

    @Override
    public String name() {
        return "Blink Trap";
    }

    @Override
    protected Color trueColor() {
        return ExtendedAsciiPanel.pink;
    }
}
