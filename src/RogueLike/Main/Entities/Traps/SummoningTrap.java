package RogueLike.Main.Entities.Traps;

import RogueLike.Main.Effect;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.EffectFactory;
import RogueLike.Main.World;

import java.awt.*;

public class SummoningTrap extends Trap {

    public SummoningTrap(int x, int y, int z, World world) {
        super(x, y, z, world);
    }

    @Override
    public Effect effect(EffectFactory factory) {
        return factory.summonMonstersScroll(world.player());
    }

    @Override
    public Effect exploitEffect(EffectFactory factory) {
        return factory.invisible(10);
    }

    @Override
    public String name() {
        return "Summoning Trap";
    }

    @Override
    protected Color trueColor() {
        return ExtendedAsciiPanel.brightWhite;
    }
}
