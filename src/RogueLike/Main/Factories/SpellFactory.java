package RogueLike.Main.Factories;

import RogueLike.Main.Effect;
import RogueLike.Main.Spell;
import RogueLike.Main.Creatures.Creature;

public class SpellFactory {
	
	public EffectFactory effectFactory;
	
	public SpellFactory(EffectFactory factory) {
		this.effectFactory = factory;
	}
	
	public Spell firebolt(Creature caster) {
		Spell firebolt = new Spell("Firebolt", 0, 4, caster, effectFactory.firebolt(caster), false, false);
		return firebolt;
	}
	
	public Spell forceBlast(Creature caster) {
		Spell repel = new Spell("Force Blast", 1, 4, caster, effectFactory.forceBlast(caster), false, false);
		return repel;
	}
	
	public Spell flashFreeze(Creature caster) {
		Spell freeze = new Spell("Flash Freeze", 2, 4, caster, effectFactory.flashFreeze(caster), false, false);
		return freeze;
	}
	
	public Spell chainLightning(Creature caster) {
		Spell lightning = new Spell("Chain Lightning", 3, 4, caster, effectFactory.chainLightning(caster), false, false);
		return lightning;
	}
	
	public Spell magicMissile(Creature caster) {
		Spell missile = new Spell("Magic Missile", 4, 4, caster, effectFactory.magicMissile(caster), false, false);
		return missile;
	}
	
	public Spell iceWall(Creature caster) {
		Spell wall = new Spell("Ice Wall", 5, 8, caster, effectFactory.iceWall(caster), false, true);
		return wall;
	}
	
	public Spell magicMappingScroll(Creature caster) {
		Spell map = new Spell("Magic Mapping", 6, 0, caster, effectFactory.magicMappingScroll(), true, false);
		return map;
	}
	
	public Spell identifyScroll(Creature caster) {
		Spell identify = new Spell("Identify", 7, 0, caster, effectFactory.identifyScroll(), true, false);
		return identify;
	}
	
	public Spell summonMonstersScroll(Creature caster) {
		Spell monster = new Spell("Summon Monsters", 8, 0, caster, effectFactory.summonMonstersScroll(caster), false, true);
		return monster;
	}
	
	public Spell upgradeScroll(Creature caster) {
		Spell upgrade = new Spell("Upgrade", 9, 0, caster, effectFactory.upgradeScroll(), true, false);
		return upgrade;
	}
	
	public Spell removeCurseScroll(Creature caster) {
		Spell curse = new Spell("Remove Curse", 10, 0, caster, effectFactory.removeCurseScroll(), true, false);
		return curse;
	}
	
	public Spell enchantScroll(Creature caster) {
		Spell enchant = new Spell("Enchant", 11, 0, caster, effectFactory.enchantScroll(), true, false);
		return enchant;
	}
	
	public Spell confuseScroll(Creature caster) {
		Spell confuse = new Spell("Confusion", 12, 0, caster, effectFactory.confused(5), false, false);
		return confuse;
	}
	
	public Spell iceKnife(Creature caster) {
		Spell iceKnife = new Spell("Ice Knife", 19, 8, caster, effectFactory.iceKnife(caster), false, false);
		return iceKnife;
	}
	
	public Spell acidBlast(Creature caster) {
		Spell acidBlast = new Spell("Acid Blast", 20, 4, caster, effectFactory.acidBlast(caster), false, false);
		return acidBlast;
	}
	
	public Spell brazierBarrier(Creature caster) {
		Spell brazierBarrier = new Spell("Brazier Barrier", 21, 4, caster, effectFactory.brazierBarrier(caster), true, false);
		return brazierBarrier;
	}
	
	public Spell pyrotechnics(Creature caster) {
		Spell pyrotechnics = new Spell("Pyrotechnics", 22, 4, caster, effectFactory.pyrotechnics(caster), false, false);
		return pyrotechnics;
	}
	
	public Spell flashfire(Creature caster) {
		Spell flashfire = new Spell("Flashfire", 23, 4, caster, effectFactory.flashfire(caster), false, false);
		return flashfire;
	}
	
	public Spell glaciate(Creature caster) {
		Spell glaciate = new Spell("Glaciate", 24, 4, caster, effectFactory.glaciate(caster), true, false);
		return glaciate;
	}
	
	public Spell lightningLance(Creature caster) {
		Spell lightningLance = new Spell("Lightning Lance", 25, 4, caster, effectFactory.lightningLance(caster), false, false);
		return lightningLance;
	}
	
	public Spell staticSurge(Creature caster) {
		Spell staticSurge = new Spell("Static Surge", 26, 4, caster, effectFactory.staticSurge(caster), true, false);
		return staticSurge;
	}
	
	public Spell haste(Creature caster) {
		Spell haste = new Spell("Haste", 27, 4, caster, effectFactory.hasteSpell(caster), true, false);
		return haste;
	}
	
	public Spell archmagesAegis(Creature caster) {
		Spell archmagesAegis = new Spell("Archmage's Aegis", 28, 4, caster, effectFactory.archmagesAegis(caster), true, false);
		return archmagesAegis;
	}
	
	public Spell findTraps(Creature caster) {
		Spell findTraps = new Spell("Find Traps", 29, 4, caster, effectFactory.findTraps(), true, false);
		return findTraps;
	}
	
	public Spell toxicTransfusion(Creature caster) {
		Spell toxicTransfusion = new Spell("Toxic Transfusion", 30, 4, caster, effectFactory.toxicTransfusion(caster), false, false);
		return toxicTransfusion;
	}
	
	public Spell refluxBarrier(Creature caster) {
		Spell refluxBarrier = new Spell("Reflux Barrier", 31, 4, caster, effectFactory.refluxBarrier(caster), true, false);
		return refluxBarrier;
	}
	
	public Spell lifetap(Creature caster) {
		Spell lifetap = new Spell("Lifetap", 32, 0, caster, effectFactory.lifetap(caster), true, false);
		return lifetap;
	}
	
	public Spell armorStorm(Creature caster) {
		Spell armorStorm = new Spell("Armor Storm", 33, 4, caster, effectFactory.armorStorm(caster), false, false);
		return armorStorm;
	}
	
	public Spell bladsWard(Creature caster) {
		Spell bladsWard = new Spell("Blad's Ward", 34, 4, caster, effectFactory.bladsWard(caster), true, false);
		return bladsWard;
	}
	
	public Spell weaponBolt(Creature caster) {
		Spell weaponBolt = new Spell("Weapon Bolt", 35, 4, caster, effectFactory.weaponBolt(caster), false, false);
		return weaponBolt;
	}
	
	public Spell infuseUpgrade(Creature caster) {
		Spell infuseUpgrate = new Spell("Weapon Bolt", 36, 0, caster, effectFactory.infuseUpgrade(caster), true, false);
		return infuseUpgrate;
	}


}
