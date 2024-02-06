package RogueLike.Main;

public class Skill {
	
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
	
	public String toStringCharacterSheet() {
		return String.format("%s: %s", this.name(), ExtraMaths.toRomanNumerals(this.level()));
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
	
	public Skill(int id, String name, int level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

}
