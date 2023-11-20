package RogueLike.Main;

public class Skill {
	
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
	
	private static Skill[] defaultSkillArray = {Skill.simpleWeaponsDefault, Skill.martialWeaponsDefault, Skill.armorTrainingDefault, Skill.fortitudeDefault, Skill.finesseWeaponsDefault, Skill.rangedWeaponsDefault, Skill.stealthDefault, Skill.perceptionDefault, Skill.evocationDefault, Skill.pyromancyDefault, Skill.cryomancyDefault, Skill.electromancyDefault, Skill.alchemancyDefault};
	
	private int id;
	public int id() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
	private String name;
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return String.format("%s (%d)", this.name, this.level);
	}
	
	private int level = 1;
	public int level() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void modifyLevel(int amount, boolean decrease) {
		if(decrease) {
			level -= amount;
			if(level < 1) {
				level = 1;
			}
		}else {
			level += amount;
		}
	}
	
	public static Skill[] defaultSkillArray() {
		return defaultSkillArray.clone();
	}
	
	
	public Skill(int id, String name, int level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

}
