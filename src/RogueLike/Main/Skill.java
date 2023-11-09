package RogueLike.Main;

public class Skill {
	
	public static Skill simpleWeapons = new Skill(0, "Simple Weapons", 1);
	public static Skill martialWeapons = new Skill(1, "Martial Weapons", 1);
	public static Skill armorTraining = new Skill(2, "Armor Training", 1);
	public static Skill fortitude = new Skill(3, "Fortitude", 1);
	public static Skill finesseWeapons= new Skill(4, "Finesse Weapons", 1);
	public static Skill rangedWeapons = new Skill(5, "Ranged Weapons", 1);
	public static Skill stealth = new Skill(6, "Stealth", 1);
	public static Skill perception = new Skill(7, "Perception", 1);
	public static Skill evocation = new Skill(8, "Evocation", 1);
	public static Skill pyromancy = new Skill(9, "Pyromancy", 1);
	public static Skill cryomancy = new Skill(10, "Cryomancy", 1);
	public static Skill electromancy = new Skill(11, "Electromancy", 1);
	public static Skill alchemancy = new Skill(12, "Alchemancy", 1);
	
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
	
	private int level;
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
	
	
	public Skill(int id, String name, int level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

}
