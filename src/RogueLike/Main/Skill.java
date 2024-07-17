package RogueLike.Main;

public enum Skill {
	SIMPLE_WEAPONS("Simple Weapons"),
	MARTIAL_WEAPONS("Martial Weapons"),
	ARMOR_TRAINING("Armor Training"),
	FORTITUDE("Fortitude"),
	FINESSE_WEAPONS("Finesse Weapons"),
	RANGED_WEAPONS("Ranged Weapons"),
	STEALTH("Stealth"),
	PERCEPTION("Perception"),
	EVOCATION("Evocation"),
	PYROMANCY("Pyromancy"),
	CRYOMANCY("Cryomancy"),
	ELECTROMANCY("Electromancy"),
	ALCHEMANCY("Alchemancy"),
	FERROMANCY("Ferromancy"),
	;
	public final String name;

	Skill(String name) {
		this.name = name;
	}
}
