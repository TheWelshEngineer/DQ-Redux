package RogueLike.Main.Factories;

import java.io.Serializable;

import RogueLike.Main.Feat;

public class FeatFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	public FeatFactory(){}

	public Feat fireResistance() {
		Feat feat = new Feat("Fire Resistance", 0, false, 0);
		return feat;
	}
	
	public Feat frostResistance() {
		Feat feat = new Feat("Frost Resistance", 1, false, 0);
		return feat;
	}
	
	public Feat shockResistance() {
		Feat feat = new Feat("Shock Resistance", 2, false, 0);
		return feat;
	}
	
	public Feat poisonResistance() {
		Feat feat = new Feat("Poison Resistance", 3, false, 0);
		return feat;
	}
	
	public Feat acidResistance() {
		Feat feat = new Feat("Acid Resistance", 4, false, 0);
		return feat;
	}
	
	public Feat lightArmor() {
		Feat feat = new Feat("Light Armor Training", 5, false, 0);
		return feat;
	}
	
	public Feat mediumArmor() {
		Feat feat = new Feat("Medium Armor Training", 6, false, 0);
		feat.addPrerequisite(5);
		return feat;
	}
	
	public Feat heavyArmor() {
		Feat feat = new Feat("Heavy Armor Training", 7, false, 0);
		feat.addPrerequisite(6);
		return feat;
	}
	
	public Feat towerShield() {
		Feat feat = new Feat("Tower Shield Training", 8, false, 0);
		feat.addPrerequisite(7);
		return feat;
	}
	
	public Feat simpleWeapons() {
		Feat feat = new Feat("Simple Weapon Training", 9, false, 0);
		return feat;
	}
	
	public Feat martialWeapons() {
		Feat feat = new Feat("Martial Weapon Training", 10, false, 0);
		feat.addPrerequisite(9);
		return feat;
	}
	
	public Feat finesseWeapons() {
		Feat feat = new Feat("Finesse Weapon Training", 11, false, 0);
		feat.addPrerequisite(9);
		return feat;
	}
	
	public Feat rangedWeapons() {
		Feat feat = new Feat("Ranged Weapon Training", 12, false, 0);
		return feat;
	}
	
	public Feat flintlockWeapons() {
		Feat feat = new Feat("Flintlock Weapon Training", 13, false, 0);
		feat.addPrerequisite(12);
		return feat;
	}
	
	public Feat evocation() {
		Feat feat = new Feat("Evocation Training", 14, false, 0);
		return feat;
	}
	
	public Feat pyromancy() {
		Feat feat = new Feat("Pyromancy Training", 15, false, 0);
		return feat;
	}
	
	public Feat cryomancy() {
		Feat feat = new Feat("Cryomancy Training", 16, false, 0);
		return feat;
	}
	
	public Feat electromancy() {
		Feat feat = new Feat("Electromancy Training", 17, false, 0);
		return feat;
	}
	
	public Feat alchemancy() {
		Feat feat = new Feat("Alchemancy Training", 18, false, 0);
		return feat;
	}
	
	public Feat perception() {
		Feat feat = new Feat("Keen Eyesight", 19, false, 0);
		return feat;
	}
	
	public Feat stealth() {
		Feat feat = new Feat("Silent Step", 20, false, 0);
		return feat;
	}
	
	public Feat lightArmorMastery() {
		Feat feat = new Feat("Light Armor Mastery", 21, false, 0);
		feat.addPrerequisite(5);
		return feat;
	}
	
	public Feat mediumArmorMastery() {
		Feat feat = new Feat("Medium Armor Mastery", 22, false, 0);
		feat.addPrerequisite(6);
		return feat;
	}
	
	public Feat heavyArmorMastery() {
		Feat feat = new Feat("Heavy Armor Mastery", 23, false, 0);
		feat.addPrerequisite(7);
		return feat;
	}
	
	public Feat simpleWeaponMastery() {
		Feat feat = new Feat("Simple Weapon Mastery", 24, false, 0);
		feat.addPrerequisite(9);
		return feat;
	}
	
	public Feat martialWeaponMastery() {
		Feat feat = new Feat("Martial Weapon Mastery", 25, false, 0);
		feat.addPrerequisite(10);
		return feat;
	}
	
	public Feat finesseWeaponMastery() {
		Feat feat = new Feat("Finesse Weapon Mastery", 26, false, 0);
		feat.addPrerequisite(11);
		return feat;
	}
	
	public Feat rangedWeaponMastery() {
		Feat feat = new Feat("Ranged Weapon Mastery", 27, false, 0);
		feat.addPrerequisite(12);
		return feat;
	}
	
	public Feat flintlockWeaponMastery() {
		Feat feat = new Feat("Flintlock Weapon Mastery", 28, false, 0);
		feat.addPrerequisite(13);
		return feat;
	}
	
	public Feat evocationMastery() {
		Feat feat = new Feat("Evocation Mastery", 29, false, 0);
		feat.addPrerequisite(14);
		return feat;
	}
	
	public Feat pyromancyMastery() {
		Feat feat = new Feat("Pyromancy Mastery", 30, false, 0);
		feat.addPrerequisite(15);
		return feat;
	}
	
	public Feat cryomancyMastery() {
		Feat feat = new Feat("Cryomancy Mastery", 31, false, 0);
		feat.addPrerequisite(16);
		return feat;
	}
	
	public Feat electromancyMastery() {
		Feat feat = new Feat("Electromancy Mastery", 32, false, 0);
		feat.addPrerequisite(17);
		return feat;
	}
	
	public Feat alchemancyMastery() {
		Feat feat = new Feat("Alchemancy Mastery", 33, false, 0);
		feat.addPrerequisite(18);
		return feat;
	}
	
	public Feat magicResistance() {
		Feat feat = new Feat("Magic Resistance", 34, false, 0);
		return feat;
	}
	
	public Feat chaosResistance() {
		Feat feat = new Feat("Chaos Resistance", 35, false, 0);
		return feat;
	}
	
	public Feat simpleFire() {
		Feat feat = new Feat("Simple Weapon Combo: Fire", 36, false, 0);
		feat.addPrerequisite(9);
		feat.addPrerequisite(15);
		feat.addIncompatibiliy(37);
		feat.addIncompatibiliy(38);
		feat.addIncompatibiliy(39);
		feat.addIncompatibiliy(40);
		feat.addIncompatibiliy(41);
		return feat;
	}
	
	public Feat simpleFrost() {
		Feat feat = new Feat("Simple Weapon Combo: Frost", 37, false, 0);
		feat.addPrerequisite(9);
		feat.addPrerequisite(16);
		feat.addIncompatibiliy(36);
		feat.addIncompatibiliy(38);
		feat.addIncompatibiliy(39);
		feat.addIncompatibiliy(40);
		feat.addIncompatibiliy(41);
		return feat;
	}
	
	public Feat simpleShock() {
		Feat feat = new Feat("Simple Weapon Combo: Shock", 38, false, 0);
		feat.addPrerequisite(9);
		feat.addPrerequisite(17);
		feat.addIncompatibiliy(36);
		feat.addIncompatibiliy(37);
		feat.addIncompatibiliy(39);
		feat.addIncompatibiliy(40);
		feat.addIncompatibiliy(41);
		return feat;
	}
	
	public Feat simplePoison() {
		Feat feat = new Feat("Simple Weapon Combo: Poison", 39, false, 0);
		feat.addPrerequisite(9);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(36);
		feat.addIncompatibiliy(37);
		feat.addIncompatibiliy(38);
		feat.addIncompatibiliy(40);
		feat.addIncompatibiliy(41);
		return feat;
	}
	
	public Feat simpleAcid() {
		Feat feat = new Feat("Simple Weapon Combo: Acid", 40, false, 0);
		feat.addPrerequisite(9);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(36);
		feat.addIncompatibiliy(37);
		feat.addIncompatibiliy(38);
		feat.addIncompatibiliy(39);
		feat.addIncompatibiliy(41);
		return feat;
	}
	
	public Feat simpleStun() {
		Feat feat = new Feat("Simple Weapon Combo: Stun", 41, false, 0);
		feat.addPrerequisite(24);
		feat.addIncompatibiliy(36);
		feat.addIncompatibiliy(37);
		feat.addIncompatibiliy(38);
		feat.addIncompatibiliy(39);
		feat.addIncompatibiliy(40);
		return feat;
	}
	
	public Feat martialFire() {
		Feat feat = new Feat("Martial Weapon Combo: Fire", 42, false, 0);
		feat.addPrerequisite(10);
		feat.addPrerequisite(15);
		feat.addIncompatibiliy(43);
		feat.addIncompatibiliy(44);
		feat.addIncompatibiliy(45);
		feat.addIncompatibiliy(46);
		feat.addIncompatibiliy(47);
		return feat;
	}
	
	public Feat martialFrost() {
		Feat feat = new Feat("Martial Weapon Combo: Frost", 43, false, 0);
		feat.addPrerequisite(10);
		feat.addPrerequisite(16);
		feat.addIncompatibiliy(42);
		feat.addIncompatibiliy(44);
		feat.addIncompatibiliy(45);
		feat.addIncompatibiliy(46);
		feat.addIncompatibiliy(47);
		return feat;
	}
	
	public Feat martialShock() {
		Feat feat = new Feat("Martial Weapon Combo: Shock", 44, false, 0);
		feat.addPrerequisite(10);
		feat.addPrerequisite(17);
		feat.addIncompatibiliy(42);
		feat.addIncompatibiliy(43);
		feat.addIncompatibiliy(45);
		feat.addIncompatibiliy(46);
		feat.addIncompatibiliy(47);
		return feat;
	}
	
	public Feat martialPoison() {
		Feat feat = new Feat("Martial Weapon Combo: Poison", 45, false, 0);
		feat.addPrerequisite(10);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(42);
		feat.addIncompatibiliy(43);
		feat.addIncompatibiliy(44);
		feat.addIncompatibiliy(46);
		feat.addIncompatibiliy(47);
		return feat;
	}
	
	public Feat martialAcid() {
		Feat feat = new Feat("Martial Weapon Combo: Acid", 46, false, 0);
		feat.addPrerequisite(10);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(42);
		feat.addIncompatibiliy(43);
		feat.addIncompatibiliy(44);
		feat.addIncompatibiliy(45);
		feat.addIncompatibiliy(47);
		return feat;
	}
	
	public Feat martialKnockback() {
		Feat feat = new Feat("Martial Weapon Combo: Knockback", 47, false, 0);
		feat.addPrerequisite(25);
		feat.addIncompatibiliy(42);
		feat.addIncompatibiliy(43);
		feat.addIncompatibiliy(44);
		feat.addIncompatibiliy(46);
		feat.addIncompatibiliy(45);
		return feat;
	}
	
	public Feat finesseFire() {
		Feat feat = new Feat("Finesse Weapon Combo: Fire", 48, false, 0);
		feat.addPrerequisite(11);
		feat.addPrerequisite(15);
		feat.addIncompatibiliy(49);
		feat.addIncompatibiliy(50);
		feat.addIncompatibiliy(51);
		feat.addIncompatibiliy(52);
		feat.addIncompatibiliy(53);
		return feat;
	}
	
	public Feat finesseFrost() {
		Feat feat = new Feat("Finesse Weapon Combo: Frost", 49, false, 0);
		feat.addPrerequisite(11);
		feat.addPrerequisite(16);
		feat.addIncompatibiliy(48);
		feat.addIncompatibiliy(50);
		feat.addIncompatibiliy(51);
		feat.addIncompatibiliy(52);
		feat.addIncompatibiliy(53);
		return feat;
	}
	
	public Feat finesseShock() {
		Feat feat = new Feat("Finesse Weapon Combo: Shock", 50, false, 0);
		feat.addPrerequisite(11);
		feat.addPrerequisite(17);
		feat.addIncompatibiliy(48);
		feat.addIncompatibiliy(49);
		feat.addIncompatibiliy(51);
		feat.addIncompatibiliy(52);
		feat.addIncompatibiliy(53);
		return feat;
	}
	
	public Feat finessePoison() {
		Feat feat = new Feat("Finesse Weapon Combo: Poison", 51, false, 0);
		feat.addPrerequisite(11);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(48);
		feat.addIncompatibiliy(49);
		feat.addIncompatibiliy(50);
		feat.addIncompatibiliy(52);
		feat.addIncompatibiliy(53);
		return feat;
	}
	
	public Feat finesseAcid() {
		Feat feat = new Feat("Finesse Weapon Combo: Acid", 52, false, 0);
		feat.addPrerequisite(11);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(48);
		feat.addIncompatibiliy(49);
		feat.addIncompatibiliy(50);
		feat.addIncompatibiliy(51);
		feat.addIncompatibiliy(53);
		return feat;
	}
	
	public Feat finesseCritical() {
		Feat feat = new Feat("Finesse Weapon Combo: Critical", 53, false, 0);
		feat.addPrerequisite(26);
		feat.addIncompatibiliy(48);
		feat.addIncompatibiliy(49);
		feat.addIncompatibiliy(50);
		feat.addIncompatibiliy(51);
		feat.addIncompatibiliy(52);
		return feat;
	}
	
	public Feat rangedFire() {
		Feat feat = new Feat("Ranged Weapon Combo: Fire", 54, false, 0);
		feat.addPrerequisite(12);
		feat.addPrerequisite(15);
		feat.addIncompatibiliy(55);
		feat.addIncompatibiliy(56);
		feat.addIncompatibiliy(57);
		feat.addIncompatibiliy(58);
		feat.addIncompatibiliy(59);
		return feat;
	}
	
	public Feat rangedFrost() {
		Feat feat = new Feat("Ranged Weapon Combo: Frost", 55, false, 0);
		feat.addPrerequisite(12);
		feat.addPrerequisite(16);
		feat.addIncompatibiliy(54);
		feat.addIncompatibiliy(56);
		feat.addIncompatibiliy(57);
		feat.addIncompatibiliy(58);
		feat.addIncompatibiliy(59);
		return feat;
	}
	
	public Feat rangedShock() {
		Feat feat = new Feat("Ranged Weapon Combo: Shock", 56, false, 0);
		feat.addPrerequisite(12);
		feat.addPrerequisite(17);
		feat.addIncompatibiliy(54);
		feat.addIncompatibiliy(55);
		feat.addIncompatibiliy(57);
		feat.addIncompatibiliy(58);
		feat.addIncompatibiliy(59);
		return feat;
	}
	
	public Feat rangedPoison() {
		Feat feat = new Feat("Ranged Weapon Combo: Poison", 57, false, 0);
		feat.addPrerequisite(12);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(54);
		feat.addIncompatibiliy(55);
		feat.addIncompatibiliy(56);
		feat.addIncompatibiliy(58);
		feat.addIncompatibiliy(59);
		return feat;
	}
	
	public Feat rangedAcid() {
		Feat feat = new Feat("Ranged Weapon Combo: Acid", 58, false, 0);
		feat.addPrerequisite(12);
		feat.addPrerequisite(18);
		feat.addIncompatibiliy(54);
		feat.addIncompatibiliy(55);
		feat.addIncompatibiliy(56);
		feat.addIncompatibiliy(57);
		feat.addIncompatibiliy(59);
		return feat;
	}
	
	public Feat rangedMultishot() {
		Feat feat = new Feat("Ranged Weapon Combo: Multishot", 59, false, 0);
		feat.addPrerequisite(27);
		feat.addIncompatibiliy(54);
		feat.addIncompatibiliy(55);
		feat.addIncompatibiliy(56);
		feat.addIncompatibiliy(57);
		feat.addIncompatibiliy(58);
		return feat;
	}
	
	public Feat warriorTraining() {
		Feat feat = new Feat("Extra Attack", 60, false, 0);
		return feat;
	}
	
	public Feat barbarianTraining() {
		Feat feat = new Feat("Battle Rage", 61, false, 0);
		return feat;
	}
	
	public Feat evokerTraining() {
		Feat feat = new Feat("Arcane Armaments", 62, false, 0);
		return feat;
	}
	
	public Feat rogueTraining() {
		Feat feat = new Feat("Thief's Wit", 63, false, 0);
		return feat;
	}
	
	public Feat rangerTraining() {
		Feat feat = new Feat("Steady Aim", 64, false, 0);
		return feat;
	}

	public Feat alchemistTraining() {
		Feat feat = new Feat("Reactive Reagents", 65, false, 0);
		return feat;
	}
	
	public Feat wizardTraining() {
		Feat feat = new Feat("Master of Magic", 66, false, 0);
		return feat;
	}
	
	public Feat witchTraining() {
		Feat feat = new Feat("Inspire Terror", 67, false, 0);
		return feat;
	}
	
	public Feat sorcererTraining() {
		Feat feat = new Feat("Unstable Arcana", 68, false, 0);
		return feat;
	}
	
	public Feat towerShieldMastery() {
		Feat feat = new Feat("Tower Shield Mastery", 69, false, 0);
		feat.addPrerequisite(8);
		return feat;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
