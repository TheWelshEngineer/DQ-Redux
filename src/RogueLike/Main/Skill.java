package RogueLike.Main;

public enum Skill {
    SIMPLE_WEAPONS(
            "Simple Weapons",
            "Simple Weapons represents your skill with simple weapons "
                    + "such as clubs and handaxes. A higher Simple Weapons skill "
                    + "allows you to use more powerful simple weapons, "
                    + "and improves your accuracy with weapons of this type."),
    MARTIAL_WEAPONS(
            "Martial Weapons",
            "Martial Weapons represents your skill with martial weapons "
                    + "such as longswords and battleaxes. A higher Martial Weapons skill "
                    + "allows you to use more powerful martial weapons, "
                    + "and improves your accuracy with weapons of this type."),
    ARMOR_TRAINING(
            "Armor Training",
            "Armor Training represents your ability to move and fight "
                    + "whilst wearing armor. A higher Armor Training skill "
                    + "allows you to use heavier armor and shields, and provides "
                    + "further bonuses to your Armor Class at higher levels."),
    FORTITUDE(
            "Fortitude",
            "Fortitude is a measure of your stamina and constitution. "
                    + "A higher Fortitude skill increases the length of time you "
                    + "can go without eating, and lessens the negative effects "
                    + "incurred by eating certain food items."),
    FINESSE_WEAPONS(
            "Finesse Weapons",
            "Finesse Weapons represents your skill with finesse weapons "
                    + "such as daggers and rapiers. A higher Finesse Weapons skill "
                    + "allows you to use more powerful finesse weapons, "
                    + "and improves your accuracy with weapons of this type."),
    RANGED_WEAPONS(
            "Ranged Weapons",
            "Ranged Weapons represents your skill with ranged weapons "
                    + "such as bows and crossbows. A higher Ranged Weapons skill "
                    + "allows you to use more powerful ranged weapons, "
                    + "and improves your accuracy with weapons of this type."),
    STEALTH(
            "Stealth",
            "Stealth represents your ability to move unnoticed. "
                    + "A higher Stealth skill allows you to sneak past sleeping "
                    + "and unalerted foes more reliably, giving you the upper "
                    + "hand in combat and making it easier to avoid a fight."),
    PERCEPTION(
            "Perception",
            "Perception is a measure of your awareness and eye for detail. "
                    + "A higher Perception skill allows you to spot traps and other "
                    + "hidden things with greater ease, as well as improving "
                    + "your chances of identifying unknown items."),
    EVOCATION(
            "Evocation",
            "Evocation is a school of magic focused mainly on the "
                    + "manipulation of raw magic. A higher Evocation skill "
                    + "allows you to use more powerful evocation wands, "
                    + "and improves the effectiveness of your evocation wands."),
    PYROMANCY(
            "Pyromancy",
            "Pyromancy is a school of magic focused mainly on the "
                    + "manipulation of fire and heat. A higher Pyromancy skill "
                    + "allows you to use more powerful pyromancy wands, "
                    + "and improves the effectiveness of your pyromancy wands."),
    CRYOMANCY(
            "Cryomancy",
            "Cryomancy is a school of magic focused mainly on the "
                    + "manipulation of water and ice. A higher Cryomancy skill "
                    + "allows you to use more powerful cryomancy wands, "
                    + "and improves the effectiveness of your cryomancy wands."),
    ELECTROMANCY(
            "Electromancy",
            "Electromancy is a school of magic focused mainly on the "
                    + "manipulation of electrical energy. A higher Electromancy skill "
                    + "allows you to use more powerful electromancy wands, "
                    + "and improves the effectiveness of your electromancy wands."),
    ALCHEMANCY(
            "Alchemancy",
            "Alchemancy is a school of magic focused mainly on the "
                    + "manipulation of chemical processes. A higher Alchemancy skill "
                    + "allows you to use more powerful alchemancy wands, "
                    + "and improves the effectiveness of your alchemancy wands."),
    FERROMANCY(
            "Ferromancy",
            "Ferromancy is a school of magic focused mainly on the "
                    + "manipulation of stone and metals. A higher Ferromancy skill "
                    + "allows you to use more powerful ferromancy wands, "
                    + "and improves the effectiveness of your ferromancy wands."),
    ;
    private final String name;
    public final String description;

    Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
