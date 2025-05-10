package RogueLike.Main.Damage;

import java.awt.Color;

import RogueLike.Main.ExtendedAsciiPanel;

public enum DamageType {
    ACID("Acid", ExtendedAsciiPanel.lime),
    CHAOS("Chaos", ExtendedAsciiPanel.brightMagenta),
    FIRE("Fire", ExtendedAsciiPanel.orange),
    FROST("Frost", ExtendedAsciiPanel.water),
    MAGIC("Magic", ExtendedAsciiPanel.lilac),
    PHYSICAL("Physical", ExtendedAsciiPanel.red),
    POISON("Poison", ExtendedAsciiPanel.magenta),
    SHOCK("Shock", ExtendedAsciiPanel.paralyzed),
    TRUE("True", ExtendedAsciiPanel.white),
    ;
    private final String name;
    private final Color color;
    
    DamageType(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return name;
    }

    public Color getColor() {
		return color;
	}

	// TRUE is not resistable, and shouldn't show up in lists of damage modifiers
    public static final DamageType[] RESISTABLE_TYPES = {PHYSICAL, FIRE, FROST, SHOCK, POISON, ACID, MAGIC, CHAOS};

    
}
