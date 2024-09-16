package RogueLike.Main;

public enum Skill {
	SIMPLE_WEAPONS(
            "Simple Weapons",
            "Simple Weapons represents your skill with simple weapons "
                    + "such as clubs and handaxes. A higher Simple Weapons skill "
                    + "allows you to use more powerful simple weapons, "
                    + "and improves your accuracy with weapons of this type.",
            "You add your proficiency bonus to attack rolls made with Simple Weapons.",
            "You add your proficiency bonus to damage rolls made with Simple Weapons.",
            "Critical hits with Simple Weapons Paralyse the target for a number of turns equal to your proficiency bonus."
			),
	MARTIAL_WEAPONS(
            "Martial Weapons",
            "Martial Weapons represents your skill with martial weapons "
                    + "such as longswords and battleaxes. A higher Martial Weapons skill "
                    + "allows you to use more powerful martial weapons, "
                    + "and improves your accuracy with weapons of this type.",
            "You add your proficiency bonus to attack rolls made with Martial Weapons.",
            "You add your proficiency bonus to damage rolls made with Martial Weapons.",
            "Critical hits with Martial Weapons deal 3x damage (up from 2x)."
			),
	ARMOR_TRAINING(
            "Armor Training",
            "Armor Training represents your ability to move and fight "
                    + "whilst wearing armor. A higher Armor Training skill "
                    + "allows you to use heavier armor and shields, and provides "
                    + "further bonuses to your Armor Class at higher levels.",
            "You can equip Medium Armor and Shields.",
            "You can equip Heavy Armor and Tower Shields.",
            "You gain a bonus to your total Armor Class equal to your proficiency bonus."
			),
	FORTITUDE(
            "Fortitude",
            "Fortitude is a measure of your stamina and constitution. "
                    + "A higher Fortitude skill increases the length of time you "
                    + "can go without eating, and lessens the negative effects "
                    + "incurred by eating certain food items.",
            "When starving, you take damage periodically instead of every turn.",
            "You add your proficiency bonus to checks made to avoid negative effects from eating corpses.",
            "The duration of negative effects applied to you is halved."
			),
	FINESSE_WEAPONS(
            "Finesse Weapons",
            "Finesse Weapons represents your skill with finesse weapons "
                    + "such as daggers and rapiers. A higher Finesse Weapons skill "
                    + "allows you to use more powerful finesse weapons, "
                    + "and improves your accuracy with weapons of this type.",
            "You add your proficiency bonus to attack rolls made with Finesse Weapons.",
            "You add your proficiency bonus to damage rolls made with Finesse Weapons.",
            "Critical hits with Finesse Weapons apply Bleeding to the target for a number of turns equal to your proficiency bonus."
			),
	RANGED_WEAPONS(
            "Ranged Weapons",
            "Ranged Weapons represents your skill with ranged weapons "
                    + "such as bows and crossbows. A higher Ranged Weapons skill "
                    + "allows you to use more powerful ranged weapons, "
                    + "and improves your accuracy with weapons of this type.",
            "You add your proficiency bonus to attack rolls made with Ranged Weapons.",
            "You add your proficiency bonus (+%s) to damage rolls made with Ranged Weapons.",
            "Attacks with Ranged Weapons refund the spent ammunition upon the target's death."
			),
	STEALTH(
            "Stealth",
            "Stealth represents your ability to move unnoticed. "
                    + "A higher Stealth skill allows you to sneak past sleeping "
                    + "and unalerted foes more reliably, giving you the upper "
                    + "hand in combat and making it easier to avoid a fight.",
            "You add your proficiency bonus to checks made to avoid waking sleeping monsters.",
            "Whenever you Search, you can expend Mana to become briefly Invisible.",
            "Attacks you make whilst Invisible are always considered to be critical hits."
			),
	PERCEPTION(
            "Perception",
            "Perception is a measure of your awareness and eye for detail. "
                    + "A higher Perception skill allows you to spot traps and other "
                    + "hidden things with greater ease, as well as improving "
                    + "your chances of identifying unknown items.",
            "You add your proficiency bonus to checks made to detect traps.",
            "You gain a bonus to your Vision Radius equal to your proficiency bonus.",
            "When you trigger a revealed trap, you instead gain a positive effect based on the trap's type."
			),
	EVOCATION(
            "Evocation",
            "Evocation is a school of magic focused mainly on the "
                    + "manipulation of raw magic. A higher Evocation skill "
                    + "allows you to use more powerful evocation wands, "
                    + "and improves the effectiveness of your evocation wands.",
            "Add your proficiency bonus to attack rolls made with Evocation spells. "
                    + "Add your proficiency bonus to the duration of Evocation spell effects.",
            "Add your proficiency bonus to damage rolls made with Evocation spells. " 
                    +"Add your proficiency bonus to the duration of Evocation spell effects again.",
            "Critical hits with Evocation spells restore 50%% of your maximum Mana."
			),
	PYROMANCY(
            "Pyromancy",
            "Pyromancy is a school of magic focused mainly on the "
                    + "manipulation of fire and heat. A higher Pyromancy skill "
                    + "allows you to use more powerful pyromancy wands, "
                    + "and improves the effectiveness of your pyromancy wands.",
            "Add your proficiency bonus to attack rolls made with Pyromancy spells. " 
                    +"Add your proficiency bonus to the duration of Pyromancy spell effects.",
            "Add your proficiency bonus to damage rolls made with Pyromancy spells. " 
                    +"Add your proficiency bonus to the duration of Pyromancy spell effects again.",
            "Critical hits with Pyromancy spells apply Ignited to the target for a number of turns equal to twice your proficiency bonus."
			),
	CRYOMANCY(
            "Cryomancy",
            "Cryomancy is a school of magic focused mainly on the "
                    + "manipulation of water and ice. A higher Cryomancy skill "
                    + "allows you to use more powerful cryomancy wands, "
                    + "and improves the effectiveness of your cryomancy wands.",
            "Add your proficiency bonus to attack rolls made with Cryomancy spells. " 
                    +"Add your proficiency bonus to the duration of Cryomancy spell effects.",
            "Add your proficiency bonus to damage rolls made with Cryomancy spells. " 
                    +"Add your proficiency bonus to the duration of Cryomancy spell effects again.",
            "Critical hits with Cryomancy spells apply Frozen to the target and all adjacent creatures for a number of turns equal to your proficiency bonus."
			),
	ELECTROMANCY(
            "Electromancy",
            "Electromancy is a school of magic focused mainly on the "
                    + "manipulation of electrical energy. A higher Electromancy skill "
                    + "allows you to use more powerful electromancy wands, "
                    + "and improves the effectiveness of your electromancy wands.",
            "Add your proficiency bonus to attack rolls made with Electromancy spells. " 
                    +"Add your proficiency bonus to the duration of Electromancy spell effects.",
            "Add your proficiency bonus to damage rolls made with Electromancy spells. " 
                    +"Add your proficiency bonus to the duration of Electromancy spell effects again.",
            "Critical hits with Electromancy spells grant Electrocharged for a number of turns equal to your proficiency bonus." 
			),
	ALCHEMANCY(
            "Alchemancy",
            "Alchemancy is a school of magic focused mainly on the "
                    + "manipulation of chemical processes. A higher Alchemancy skill "
                    + "allows you to use more powerful alchemancy wands, "
                    + "and improves the effectiveness of your alchemancy wands.",
            "Add your proficiency bonus to attack rolls made with Alchemancy spells. " 
                    +"Add your proficiency bonus to the duration of Alchemancy spell effects.",
            "Add your proficiency bonus to damage rolls made with Alchemancy spells. " 
                    +"Add your proficiency bonus to the duration of Alchemancy spell effects again.",
            "Critical hits with Alchemancy spells grant Restoration and heal you for an amount equal to five times your proficiency bonus"
			),
	FERROMANCY(
            "Ferromancy",
            "Ferromancy is a school of magic focused mainly on the "
                    + "manipulation of stone and metals. A higher Ferromancy skill "
                    + "allows you to use more powerful ferromancy wands, "
                    + "and improves the effectiveness of your ferromancy wands.",
            "Add your proficiency bonus to attack rolls made with Ferromancy spells. " 
                    +"Add your proficiency bonus to the duration of Ferromancy spell effects.",
            "Add your proficiency bonus to damage rolls made with Ferromancy spells. " 
                    +"Add your proficiency bonus to the duration of Ferromancy spell effects again.",
            "Critical hits with Ferromancy spells grant Giant Strength for a number of turns equal to your proficiency bonus."
			),
	;
	private final String name;
    public final String description;
    public final String details_1;
    public final String details_2;
    public final String details_3;

	Skill(String name, String description, String details_1, String details_2, String details_3) {
		this.name = name;
		this.description = description;
		this.details_1 = details_1;
		this.details_2 = details_2;
		this.details_3 = details_3;
    }

	@Override
	public String toString() {
		return name;
	}
}
