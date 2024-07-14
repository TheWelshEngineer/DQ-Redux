package RogueLike.Main.Creatures;

import RogueLike.Main.World;

import java.awt.*;

public class Player extends Creature {
	private String playerClass;
	private String playerAncestry;
	private String playerName;

	public Player(World world, String name, char glyph, Color color, int maxHP, int maxMana, int armorclass, int strength, int dexterity, int intelligence, int visionRadius, int inventorySize) {
		super(world, name, glyph, color, maxHP, maxMana, armorclass, strength, dexterity, intelligence, visionRadius, inventorySize);
	}

	public String playerClass() {
		return playerClass;
	}
	public void setPlayerClass(String newClass) {
		playerClass = newClass;
	}

	public String playerAncestry() {
		return playerAncestry;
	}
	public void setPlayerAncestry(String ancestry) {
		playerAncestry = ancestry;
	}

	public String playerName() {
		return playerName;
	}
	public void setPlayerName(String newName) {
		playerName = newName;
	}

	@Override
	public int manaGainedOnLevelUp() {
		int bonus = super.manaGainedOnLevelUp();
		if (playerAncestry == "Elf") {
			bonus += proficiencyBonus();
		}
		return bonus;
	}
}
