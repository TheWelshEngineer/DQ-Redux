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
    // TODO: I'm not sure about keeping HEALING as a DamageType; it seems like it'll need
    //  unnecessary special-casing when we could just split modifyHP into heal() and damage() and be clearer!
    HEALING("Healing"),
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
