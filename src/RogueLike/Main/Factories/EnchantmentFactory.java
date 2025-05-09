package RogueLike.Main.Factories;

import java.io.Serializable;

import RogueLike.Main.Dice;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Enchantments.Enchantment;
import RogueLike.Main.Items.Item;

public class EnchantmentFactory implements Serializable {
	
	private static final long serialVersionUID = -3659119664404692952L;
	
	public EnchantmentFactory() {}
	
	// Weapon enchantments
	public Enchantment fireWeaponEnchantment(Item host) {
		Enchantment enchantment = new Enchantment("Blazing", FactoryManager.getEffectFactory().ignited(5 + host.upgradeLevel()), 5, host, DamageType.FIRE);
		return enchantment;
	}
	
	public Enchantment frostWeaponEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("Freezing", FactoryManager.getEffectFactory().frozen(3 + host.upgradeLevel()), 3, host, DamageType.FROST);
		return enchantment;
	}
	
	public Enchantment shockWeaponEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("Shocking", FactoryManager.getEffectFactory().electrified(5 + host.upgradeLevel()), 5, host, DamageType.SHOCK);
		return enchantment;
	}
	
	public Enchantment stunWeaponEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("Stunning", FactoryManager.getEffectFactory().paralysed(3 + host.upgradeLevel()), 3, host);
		return enchantment;
	}
	
	public Enchantment magicWeaponEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("Arcane", FactoryManager.getEffectFactory().blink(), 0, host);
		return enchantment;
	}
	
	public Enchantment poisonWeaponEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("Toxic", FactoryManager.getEffectFactory().poisoned(5 + host.upgradeLevel()), 5, host, DamageType.POISON);
		return enchantment;
	}
	
	public Enchantment acidWeaponEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("Caustic", FactoryManager.getEffectFactory().corroded(5 + host.upgradeLevel()), 5, host, DamageType.ACID);
		return enchantment;
	}
	
	public Enchantment chaosWeaponEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("Corrupting", FactoryManager.getEffectFactory().devoured(5 + host.upgradeLevel()), 5, host, DamageType.CHAOS);
		return enchantment;
	}
	
	//Armor enchantments
	
	public Enchantment fireArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Magma Ward", FactoryManager.getEffectFactory().magmaWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment frostArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Chill Ward", FactoryManager.getEffectFactory().chillWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment shockArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Arc Ward", FactoryManager.getEffectFactory().arcWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment magicArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Arcane Ward", FactoryManager.getEffectFactory().arcaneWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment poisonArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Venomous Ward", FactoryManager.getEffectFactory().venomousWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment acidArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Caustic Ward", FactoryManager.getEffectFactory().causticWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment chaosArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Eldritch Ward", FactoryManager.getEffectFactory().eldritchWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment physicalArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Blade Ward", FactoryManager.getEffectFactory().bladeWard(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	public Enchantment stealthArmorEnchantment(Item host) {
		Enchantment enchantment  = new Enchantment("of Transparency", FactoryManager.getEffectFactory().invisible(10 + host.upgradeLevel()), 10, host);
		return enchantment;
	}
	
	//Curses
	public Enchantment devouringCurse(Item host) {
		Enchantment curse = new Enchantment("Insatiable", FactoryManager.getEffectFactory().devoured(Math.max(1, 7-host.upgradeLevel())), Math.max(1, 7-host.upgradeLevel()), host);
		return curse;
	}
	
	public Enchantment blindingCurse(Item host) {
		Enchantment curse = new Enchantment("Shadowing", FactoryManager.getEffectFactory().blinded(Math.max(1, 7-host.upgradeLevel())), Math.max(1, 7-host.upgradeLevel()), host);
		return curse;
	}
	
	public Enchantment confusingCurse(Item host) {
		Enchantment curse = new Enchantment("Bewildering", FactoryManager.getEffectFactory().confused(Math.max(1, 7-host.upgradeLevel())), Math.max(1, 7-host.upgradeLevel()), host);
		return curse;
	}
	
	//Generators
	
	public Enchantment randomWeaponEnchantment(Item host) {
		switch(Dice.d8.roll()) {
		case 1: return fireWeaponEnchantment(host);
		case 2: return frostWeaponEnchantment(host);
		case 3: return shockWeaponEnchantment(host);
		case 4: return stunWeaponEnchantment(host);
		case 5: return magicWeaponEnchantment(host);
		case 6: return poisonWeaponEnchantment(host);
		case 7: return acidWeaponEnchantment(host);
		case 8: return chaosWeaponEnchantment(host);
		default: return fireWeaponEnchantment(host);
		}
	}
	
	public Enchantment randomArmorEnchantment(Item host) {
		switch(ExtraMaths.diceRoll(1, 9)) {
		case 1: return physicalArmorEnchantment(host);
		case 2: return fireArmorEnchantment(host);
		case 3: return frostArmorEnchantment(host);
		case 4: return shockArmorEnchantment(host);
		case 5: return poisonArmorEnchantment(host);
		case 6: return acidArmorEnchantment(host);
		case 7: return magicArmorEnchantment(host);
		case 8: return chaosArmorEnchantment(host);
		case 9: return stealthArmorEnchantment(host);
		default: return fireArmorEnchantment(host);
		}
	}
	
	public Enchantment randomCurse(Item host) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return devouringCurse(host);
		case 2: return confusingCurse(host);
		case 3: return blindingCurse(host);
		default: return devouringCurse(host);
		}
	}

}
