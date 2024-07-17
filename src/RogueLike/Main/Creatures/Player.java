package RogueLike.Main.Creatures;

import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.Enums.PlayerClass;
import RogueLike.Main.World;

import java.awt.*;

public class Player extends Creature {
	private PlayerClass playerClass;
	private PlayerAncestry playerAncestry;
	private String playerName;

	public Player(World world, String name, char glyph, Color color, int maxHP, int maxMana, int armorclass, int strength, int dexterity, int intelligence, int visionRadius, int inventorySize) {
		super(world, name, glyph, color, maxHP, maxMana, armorclass, strength, dexterity, intelligence, visionRadius, inventorySize);
	}

	public PlayerClass playerClass() {
		return playerClass;
	}
	public void setPlayerClass(PlayerClass newClass) {
		playerClass = newClass;
	}

	public PlayerAncestry playerAncestry() {
		return playerAncestry;
	}
	public void setPlayerAncestry(PlayerAncestry ancestry) {
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
		if (playerAncestry == PlayerAncestry.ELF) {
			bonus += proficiencyBonus();
		}
		return bonus;
	}
}
