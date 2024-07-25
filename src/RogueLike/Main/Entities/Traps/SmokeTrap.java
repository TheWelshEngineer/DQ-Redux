package RogueLike.Main.Entities.Traps;

import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.EffectFactory;
import RogueLike.Main.World;

import java.awt.*;

public class SmokeTrap extends Trap {

    public SmokeTrap(int x, int y, int z, World world) {
        super(x, y, z, world);
    }

    @Override
    public Effect effect(EffectFactory factory) {
        return factory.smokeTrap();
    }

    @Override
    public Effect exploitEffect(EffectFactory factory) {
        return factory.mindVision(10);
    }

    @Override
    public String name() {
        return "Smoke Trap";
    }

    @Override
    protected Color trueColor() {
        return ExtendedAsciiPanel.smoke;
    }
}
