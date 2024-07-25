package RogueLike.Main.AI;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.World;

public class ChestAI extends CreatureAI {

    public ChestAI(Creature creature, ObjectFactory factory, World world) {
        super(creature, factory, world);
    }

    public void onUpdate() {}
}
