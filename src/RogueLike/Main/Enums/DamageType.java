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
    // TODO: I'm not sure about keeping HEALING, MANA_LOSS, and MANA_GAIN as DamageTypes; it seems like it'll need
    //  a lot of special-casing when we could just split modifyHP into heal() and damage()!
    HEALING("Healing"),
    MANA_LOSS("Mana loss"),
    MANA_GAIN("Mana gain"),
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
