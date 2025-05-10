package RogueLike.Main;

import java.io.Serializable;

public class SkillInstance implements Serializable {
	
	private static final long serialVersionUID = -4881377885496171643L;
		
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
	public void modifyLevel(int amount) {
			level += amount;

	}

	public SkillInstance(Skill type, int level) {
		this.type = type;
		this.level = level;
	}
}
