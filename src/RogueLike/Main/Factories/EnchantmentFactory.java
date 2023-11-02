package RogueLike.Main.Factories;

import RogueLike.Main.Dice;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Enchantments.Enchantment;

public class EnchantmentFactory {
	
	private EffectFactory effectFactory;
	public EffectFactory effectFactory() {
		return effectFactory;
	}
	public void setEffectFactory(EffectFactory factory) {
		effectFactory = factory;
	}
	
	public EnchantmentFactory(EffectFactory factory) {
		effectFactory = factory;
	}
	
	// Weapon enchantments
	public Enchantment fireWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Blazing", effectFactory.ignited(), Damage.fire);
		return enchantment;
	}
	
	public Enchantment frostWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Freezing", effectFactory.frozen(), Damage.frost);
		return enchantment;
	}
	
	public Enchantment shockWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Shocking", effectFactory.electrified(), Damage.shock);
		return enchantment;
	}
	
	public Enchantment stunWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Stunning", effectFactory.paralyzed());
		return enchantment;
	}
	
	public Enchantment blinkWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Warping", effectFactory.blink());
		return enchantment;
	}
	
	public Enchantment poisonWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Toxic", effectFactory.poisoned(), Damage.poison);
		return enchantment;
	}
	
	public Enchantment acidWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Caustic", effectFactory.corroded(), Damage.acid);
		return enchantment;
	}
	
	public Enchantment chaosWeaponEnchantment() {
		Enchantment enchantment  = new Enchantment("Corrupting", effectFactory.devoured(), Damage.chaos);
		return enchantment;
	}
	
	//Armor enchantments
	
	public Enchantment fireArmorEnchantment() {
		Enchantment enchantment  = new Enchantment("of Magma Ward", effectFactory.magmaWard());
		return enchantment;
	}
	
	public Enchantment frostArmorEnchantment() {
		Enchantment enchantment  = new Enchantment("of Chill Ward", effectFactory.chillWard());
		return enchantment;
	}
	
	public Enchantment shockArmorEnchantment() {
		Enchantment enchantment  = new Enchantment("of Arc Ward", effectFactory.arcWard());
		return enchantment;
	}
	
	public Enchantment forceArmorEnchantment() {
		Enchantment enchantment  = new Enchantment("of Repulsion", effectFactory.bounce());
		return enchantment;
	}
	
	public Enchantment stealthArmorEnchantment() {
		Enchantment enchantment  = new Enchantment("of Transparency", effectFactory.invisible());
		return enchantment;
	}
	
	//Curses
	public Enchantment devouringCurse() {
		Enchantment curse = new Enchantment("Insatiable", effectFactory.devoured());
		return curse;
	}
	
	public Enchantment blindingCurse() {
		Enchantment curse = new Enchantment("Shadowing", effectFactory.blinded());
		return curse;
	}
	
	public Enchantment confusingCurse() {
		Enchantment curse = new Enchantment("Bewildering", effectFactory.confused());
		return curse;
	}
	
	//Generators
	
	public Enchantment randomWeaponEnchantment() {
		switch(Dice.d8.roll()) {
		case 1: return fireWeaponEnchantment();
		case 2: return frostWeaponEnchantment();
		case 3: return shockWeaponEnchantment();
		case 4: return stunWeaponEnchantment();
		case 5: return blinkWeaponEnchantment();
		case 6: return poisonWeaponEnchantment();
		case 7: return acidWeaponEnchantment();
		case 8: return chaosWeaponEnchantment();
		default: return fireWeaponEnchantment();
		}
	}
	
	public Enchantment randomArmorEnchantment() {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return fireArmorEnchantment();
		case 2: return frostArmorEnchantment();
		case 3: return shockArmorEnchantment();
		case 4: return forceArmorEnchantment();
		case 5: return stealthArmorEnchantment();
		default: return fireArmorEnchantment();
		}
	}
	
	public Enchantment randomCurse() {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return devouringCurse();
		case 2: return confusingCurse();
		case 3: return blindingCurse();
		default: return devouringCurse();
		}
	}

}
