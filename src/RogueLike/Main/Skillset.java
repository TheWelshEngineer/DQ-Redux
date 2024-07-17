package RogueLike.Main;

public class Skillset {
	public final SkillInstance simpleWeapons = new SkillInstance(Skill.SIMPLE_WEAPONS, 0);
	public final SkillInstance martialWeapons = new SkillInstance(Skill.MARTIAL_WEAPONS, 0);
	public final SkillInstance armorTraining = new SkillInstance(Skill.ARMOR_TRAINING, 0);
	public final SkillInstance fortitude = new SkillInstance(Skill.FORTITUDE, 0);
	public final SkillInstance finesseWeapons = new SkillInstance(Skill.FINESSE_WEAPONS, 0) ;
	public final SkillInstance rangedWeapons = new SkillInstance(Skill.RANGED_WEAPONS, 0);
	public final SkillInstance stealth = new SkillInstance(Skill.STEALTH, 0);
	public final SkillInstance perception = new SkillInstance(Skill.PERCEPTION, 0);
	public final SkillInstance evocation = new SkillInstance(Skill.EVOCATION, 0);
	public final SkillInstance pyromancy = new SkillInstance(Skill.PYROMANCY, 0);
	public final SkillInstance cryomancy = new SkillInstance(Skill.CRYOMANCY, 0);
	public final SkillInstance electromancy = new SkillInstance(Skill.ELECTROMANCY, 0);
	public final SkillInstance alchemancy = new SkillInstance(Skill.ALCHEMANCY, 0);
	public final SkillInstance ferromancy = new SkillInstance(Skill.FERROMANCY, 0);

	public SkillInstance get(Skill type) {
		switch (type) {
			case SIMPLE_WEAPONS: return simpleWeapons;
			case MARTIAL_WEAPONS: return martialWeapons;
			case ARMOR_TRAINING: return armorTraining;
			case FORTITUDE: return fortitude;
			case FINESSE_WEAPONS: return finesseWeapons;
			case RANGED_WEAPONS: return rangedWeapons;
			case STEALTH: return stealth;
			case PERCEPTION: return perception;
			case EVOCATION: return evocation;
			case PYROMANCY: return pyromancy;
			case CRYOMANCY: return cryomancy;
			case ELECTROMANCY: return electromancy;
			case ALCHEMANCY: return alchemancy;
			case FERROMANCY: return ferromancy;
			default: throw new IllegalArgumentException(type.toString());
		}
	}
}
