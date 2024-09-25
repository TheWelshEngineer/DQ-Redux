package RogueLike.Main.Enums;

public enum PlayerAncestry {
	HUMAN("Human"),
	ELF("Elf"),
	DWARF("Dwarf"),
	ORC("Orc"),
	DRAGONBORN("Dragonborn"),
	LYCAN("Lycan"),
	;

	private final String name;

	PlayerAncestry(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
