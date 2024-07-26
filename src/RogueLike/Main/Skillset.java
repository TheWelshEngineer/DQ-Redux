package RogueLike.Main;

public class Skillset {
    public final SkillInstance simpleWeapons;
    public final SkillInstance martialWeapons;
    public final SkillInstance armorTraining;
    public final SkillInstance fortitude;
    public final SkillInstance finesseWeapons;
    public final SkillInstance rangedWeapons;
    public final SkillInstance stealth;
    public final SkillInstance perception;
    public final SkillInstance evocation;
    public final SkillInstance pyromancy;
    public final SkillInstance cryomancy;
    public final SkillInstance electromancy;
    public final SkillInstance alchemancy;
    public final SkillInstance ferromancy;

    public SkillInstance get(Skill type) {
        switch (type) {
            case SIMPLE_WEAPONS:
                return simpleWeapons;
            case MARTIAL_WEAPONS:
                return martialWeapons;
            case ARMOR_TRAINING:
                return armorTraining;
            case FORTITUDE:
                return fortitude;
            case FINESSE_WEAPONS:
                return finesseWeapons;
            case RANGED_WEAPONS:
                return rangedWeapons;
            case STEALTH:
                return stealth;
            case PERCEPTION:
                return perception;
            case EVOCATION:
                return evocation;
            case PYROMANCY:
                return pyromancy;
            case CRYOMANCY:
                return cryomancy;
            case ELECTROMANCY:
                return electromancy;
            case ALCHEMANCY:
                return alchemancy;
            case FERROMANCY:
                return ferromancy;
            default:
                throw new IllegalArgumentException(type.toString());
        }
    }

    public Skillset() {
        simpleWeapons = new SkillInstance(Skill.SIMPLE_WEAPONS, 0);
        martialWeapons = new SkillInstance(Skill.MARTIAL_WEAPONS, 0);
        armorTraining = new SkillInstance(Skill.ARMOR_TRAINING, 0);
        fortitude = new SkillInstance(Skill.FORTITUDE, 0);
        finesseWeapons = new SkillInstance(Skill.FINESSE_WEAPONS, 0);
        rangedWeapons = new SkillInstance(Skill.RANGED_WEAPONS, 0);
        stealth = new SkillInstance(Skill.STEALTH, 0);
        perception = new SkillInstance(Skill.PERCEPTION, 0);
        evocation = new SkillInstance(Skill.EVOCATION, 0);
        pyromancy = new SkillInstance(Skill.PYROMANCY, 0);
        cryomancy = new SkillInstance(Skill.CRYOMANCY, 0);
        electromancy = new SkillInstance(Skill.ELECTROMANCY, 0);
        alchemancy = new SkillInstance(Skill.ALCHEMANCY, 0);
        ferromancy = new SkillInstance(Skill.FERROMANCY, 0);
    }

    public Skillset(Skillset other) {
        simpleWeapons = new SkillInstance(Skill.SIMPLE_WEAPONS, other.simpleWeapons.level());
        martialWeapons = new SkillInstance(Skill.MARTIAL_WEAPONS, other.martialWeapons.level());
        armorTraining = new SkillInstance(Skill.ARMOR_TRAINING, other.armorTraining.level());
        fortitude = new SkillInstance(Skill.FORTITUDE, other.fortitude.level());
        finesseWeapons = new SkillInstance(Skill.FINESSE_WEAPONS, other.finesseWeapons.level());
        rangedWeapons = new SkillInstance(Skill.RANGED_WEAPONS, other.rangedWeapons.level());
        stealth = new SkillInstance(Skill.STEALTH, other.stealth.level());
        perception = new SkillInstance(Skill.PERCEPTION, other.perception.level());
        evocation = new SkillInstance(Skill.EVOCATION, other.evocation.level());
        pyromancy = new SkillInstance(Skill.PYROMANCY, other.pyromancy.level());
        cryomancy = new SkillInstance(Skill.CRYOMANCY, other.cryomancy.level());
        electromancy = new SkillInstance(Skill.ELECTROMANCY, other.electromancy.level());
        alchemancy = new SkillInstance(Skill.ALCHEMANCY, other.alchemancy.level());
        ferromancy = new SkillInstance(Skill.FERROMANCY, other.ferromancy.level());
    }
}
