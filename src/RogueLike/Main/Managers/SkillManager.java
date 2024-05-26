package RogueLike.Main.Managers;

import RogueLike.Main.Skill;

public class SkillManager {
	
	public static Skill simpleWeaponsDefault = new Skill(0, "Simple Weapons", 0);
	public static Skill martialWeaponsDefault = new Skill(1, "Martial Weapons", 0);
	public static Skill armorTrainingDefault = new Skill(2, "Armor Training", 0);
	public static Skill fortitudeDefault = new Skill(3, "Fortitude", 0);
	public static Skill finesseWeaponsDefault = new Skill(4, "Finesse Weapons", 0);
	public static Skill rangedWeaponsDefault = new Skill(5, "Ranged Weapons", 0);
	public static Skill stealthDefault = new Skill(6, "Stealth", 0);
	public static Skill perceptionDefault = new Skill(7, "Perception", 0);
	public static Skill evocationDefault = new Skill(8, "Evocation", 0);
	public static Skill pyromancyDefault = new Skill(9, "Pyromancy", 0);
	public static Skill cryomancyDefault = new Skill(10, "Cryomancy", 0);
	public static Skill electromancyDefault = new Skill(11, "Electromancy", 0);
	public static Skill alchemancyDefault = new Skill(12, "Alchemancy", 0);
	public static Skill ferromancyDefault = new Skill(13, "Ferromancy", 0);
	
	public static Skill[] getDefaultSkillArray() {
		return new Skill[]{
			new Skill(0, "Simple Weapons", 0),
			new Skill(1, "Martial Weapons", 0),
			new Skill(2, "Armor Training", 0),
			new Skill(3, "Fortitude", 0),
			new Skill(4, "Finesse Weapons", 0),
			new Skill(5, "Ranged Weapons", 0),
			new Skill(6, "Stealth", 0),
			new Skill(7, "Perception", 0),
			new Skill(8, "Evocation", 0),
			new Skill(9, "Pyromancy", 0),
			new Skill(10, "Cryomancy", 0),
			new Skill(11, "Electromancy", 0),
			new Skill(12, "Alchemancy", 0),
			new Skill(13, "Ferromancy", 0)
		};
	}

}
