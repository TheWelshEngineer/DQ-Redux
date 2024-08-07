package RogueLike.Main;

import RogueLike.Main.Creatures.Creature;

public abstract class LevelUpOption {

    private String name;

    public String name() {
        return name;
    }

    public LevelUpOption(String name) {
        this.name = name;
    }

    public abstract void invoke(Creature creature);
}
