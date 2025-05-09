package RogueLike.Main.Utils;

import java.util.List;

import RogueLike.Main.Skillset;
import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.Enums.PlayerClass;

public class PlayerBuildDetails {
	public final PlayerClass playerClass;
	public final List<Integer> startingStats;
	public final Skillset startingSkills;
	public final String name;
	public final PlayerAncestry ancestry;

	public PlayerBuildDetails(PlayerClass playerClass, List<Integer> startingStats, Skillset startingSkills, String name, PlayerAncestry ancestry) {
		this.playerClass = playerClass;
		this.startingStats = startingStats;
		this.startingSkills = startingSkills;
		this.name = name;
		this.ancestry = ancestry;
	}
}
