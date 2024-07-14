package RogueLike.Main.Utils;

import RogueLike.Main.Skill;

import java.util.List;

public class PlayerBuildDetails {
	public final String playerClass;
	public final List<Integer> startingStats;
	public final Skill[] startingSkills;
	public final String name;
	public final String ancestry;

	public PlayerBuildDetails(String playerClass, List<Integer> startingStats, Skill[] startingSkills, String name, String ancestry) {
		this.playerClass = playerClass;
		this.startingStats = startingStats;
		this.startingSkills = startingSkills;
		this.name = name;
		this.ancestry = ancestry;
	}
}
