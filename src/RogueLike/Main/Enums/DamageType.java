package RogueLike.Main.Enums;

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
}
