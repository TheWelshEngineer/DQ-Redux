package RogueLike.Main.Enums;

import java.util.List;

public enum DamageType {
    ACID("Acid"),
    CHAOS("Chaos"),
    FIRE("Fire"),
    FROST("Frost"),
    MAGIC("Magic"),
    PHYSICAL("Physical"),
    POISON("Poison"),
    SHOCK("Shock"),
    TRUE("True"),
    ;
    private final String name;
    DamageType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    // TRUE is not resistable, and shouldn't show up in lists of damage modifiers
    public static final DamageType[] RESISTABLE_TYPES = {PHYSICAL, FIRE, FROST, SHOCK, POISON, ACID, MAGIC, CHAOS};
}
