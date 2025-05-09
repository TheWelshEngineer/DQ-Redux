package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.Enums.PlayerClass;
import RogueLike.Main.Factories.FactoryManager;

public class Player extends Creature {
	private static final long serialVersionUID = 1L;
	private PlayerClass playerClass;
	private PlayerAncestry playerAncestry;
	private String playerName;

	public Player(String name, char glyph, Color color, int maxHP, int maxMana, int armorclass, int strength, int dexterity, int intelligence, int visionRadius, int inventorySize) {
		super(name, glyph, color, maxHP, maxMana, armorclass, strength, dexterity, intelligence, visionRadius, inventorySize);
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
	
	@Override
	public void damage(Damage damage, String causeOfDeath) {
		super.damage(damage, causeOfDeath);
		if(playerAncestry == PlayerAncestry.LYCAN && this.hp() <= (this.maxHP()/2)) {
			this.addEffect(FactoryManager.getEffectFactory().beastForm(2*this.proficiencyBonus()));
		}
	}
}
