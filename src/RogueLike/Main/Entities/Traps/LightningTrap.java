package RogueLike.Main.Entities.Traps;

import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.EffectFactory;
import RogueLike.Main.World;

import java.awt.*;

public class LightningTrap extends Trap {

    public LightningTrap(int x, int y, int z, World world) {
        super(x, y, z, world);
    }

    @Override
    public Effect effect(EffectFactory factory) {
        return factory.electrified(3 + z + Dice.d6.roll());
    }

    @Override
    public Effect exploitEffect(EffectFactory factory) {
        return factory.arcWard(10);
    }

    @Override
    public String name() {
        return "Lightning Trap";
    }

    @Override
    protected Color trueColor() {
        return ExtendedAsciiPanel.brightCyan;
    }
}
