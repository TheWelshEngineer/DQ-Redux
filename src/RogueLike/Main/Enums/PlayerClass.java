package RogueLike.Main.Enums;

public enum PlayerClass {
	WARRIOR("Warrior"),
	ROGUE("Rogue"),
	MAGE("Mage"),
	RANGER("Ranger"),
	WITCH("Witch"),
	PALADIN("Paladin"),
	;

	private final String name;

	PlayerClass(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
