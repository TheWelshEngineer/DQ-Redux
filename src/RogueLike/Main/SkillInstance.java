package RogueLike.Main;

public class SkillInstance {
	
	public final Skill type;

	public String name() {
		return type.toString();
	}
	public String toString() {
		return String.format("%s (%d)", this.name(), this.level);
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
	
	public SkillInstance(Skill type, int level) {
		this.type = type;
		this.level = level;
	}
}
