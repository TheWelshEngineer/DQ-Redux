package RogueLike.Main;

import RogueLike.Main.Creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class LevelUpController {

    private static LevelUpOption[] options =
            new LevelUpOption[] {
                new LevelUpOption("+1 Strength") {
                    public void invoke(Creature creature) {
                        creature.gainStrength();
                    }
                },
                new LevelUpOption("+1 Dexterity") {
                    public void invoke(Creature creature) {
                        creature.gainDexterity();
                    }
                },
                new LevelUpOption("+1 Intelligence") {
                    public void invoke(Creature creature) {
                        creature.gainIntelligence();
                    }
                }
            };

    public void autoLevelUp(Creature creature) {
        options[(int) (Math.random() * options.length)].invoke(creature);
    }

    public List<String> getLevelUpOptions() {
        List<String> names = new ArrayList<String>();
        for (LevelUpOption option : options) {
            names.add(option.name());
        }
        return names;
    }

    public LevelUpOption getLevelUpOption(String name) {
        for (LevelUpOption option : options) {
            if (option.name().equals(name)) return option;
        }
        return null;
    }
}
