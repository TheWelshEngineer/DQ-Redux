package RogueLike.Main.Creatures;

import RogueLike.Main.Damage.DamageType;

public class CreatureModifier {
	
	private String prefix;
	public String prefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	private String suffix;
	public String suffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	private DamageType damageType;
	public DamageType damageType() {
		return damageType;
	}
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	
	public CreatureModifier(String prefix, String suffix, DamageType damageType) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.damageType = damageType;
	}

}
