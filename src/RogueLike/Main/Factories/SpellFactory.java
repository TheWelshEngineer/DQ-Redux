package RogueLike.Main.Factories;

import RogueLike.Main.Effect;
import RogueLike.Main.Spell;
import RogueLike.Main.Creatures.Creature;

public class SpellFactory {
	
	public EffectFactory effectFactory;
	
	public SpellFactory(EffectFactory factory) {
		this.effectFactory = factory;
	}
	
	public Spell firebolt(Creature player) {
		Spell firebolt = new Spell("Firebolt", 0, 4, player, effectFactory.firebolt(player), false, false);
		return firebolt;
	}
	
	public Spell forceBlast(Creature player) {
		Spell repel = new Spell("Force Blast", 1, 4, player, effectFactory.repelWand(player), false, false);
		return repel;
	}
	
	public Spell flashFreeze(Creature player) {
		Spell freeze = new Spell("Flash Freeze", 2, 4, player, effectFactory.flashFreeze(player), false, false);
		return freeze;
	}
	
	public Spell chainLightning(Creature player) {
		Spell lightning = new Spell("Chain Lightning", 3, 4, player, effectFactory.chainLightning(player), false, false);
		return lightning;
	}
	
	public Spell magicMissile(Creature player) {
		Spell missile = new Spell("Magic Missile", 4, 4, player, effectFactory.magicMissile(player), false, false);
		return missile;
	}
	
	public Spell iceWall(Creature player) {
		Spell wall = new Spell("Ice Wall", 5, 8, player, effectFactory.iceWall(player), false, true);
		return wall;
	}
	
	public Spell magicMappingScroll(Creature player) {
		Spell map = new Spell("Magic Mapping", 6, 0, player, effectFactory.magicMappingScroll(), true, false);
		return map;
	}
	
	public Spell identifyScroll(Creature player) {
		Spell identify = new Spell("Identify", 7, 0, player, effectFactory.identifyScroll(), true, false);
		return identify;
	}
	
	public Spell summonMonstersScroll(Creature player) {
		Spell monster = new Spell("Summon Monsters", 8, 0, player, effectFactory.summonMonstersScroll(player), false, true);
		return monster;
	}
	
	public Spell upgradeScroll(Creature player) {
		Spell upgrade = new Spell("Upgrade", 9, 0, player, effectFactory.upgradeScroll(), true, false);
		return upgrade;
	}
	
	public Spell removeCurseScroll(Creature player) {
		Spell curse = new Spell("Remove Curse", 10, 0, player, effectFactory.removeCurseScroll(), true, false);
		return curse;
	}
	
	public Spell enchantScroll(Creature player) {
		Spell enchant = new Spell("Enchant", 11, 0, player, effectFactory.enchantScroll(), true, false);
		return enchant;
	}
	
	public Spell confuseScroll(Creature player) {
		Spell confuse = new Spell("Confusion", 12, 0, player, effectFactory.confused(5), false, false);
		return confuse;
	}
	
	public Spell iceKnife(Creature player) {
		Spell iceKnife = new Spell("Ice Knife", 19, 8, player, effectFactory.iceKnife(player), false, false);
		return iceKnife;
	}
	
	public Spell acidBlast(Creature player) {
		Spell acidBlast = new Spell("Acid Blast", 20, 4, player, effectFactory.acidBlast(player), false, false);
		return acidBlast;
	}
	
	public Spell brazierBarrier(Creature player) {
		Spell brazierBarrier = new Spell("Brazier Barrier", 21, 4, player, effectFactory.brazierBarrier(player), true, false);
		return brazierBarrier;
	}
	
	public Spell pyrotechnics(Creature player) {
		Spell pyrotechnics = new Spell("Pyrotechnics", 22, 4, player, effectFactory.pyrotechnics(player), false, false);
		return pyrotechnics;
	}
	
	public Spell flashfire(Creature player) {
		Spell flashfire = new Spell("Flashfire", 23, 4, player, effectFactory.flashfire(player), false, false);
		return flashfire;
	}
	
	public Spell glaciate(Creature player) {
		Spell glaciate = new Spell("Glaciate", 24, 4, player, effectFactory.glaciate(player), true, false);
		return glaciate;
	}
	
	public Spell lightningLance(Creature player) {
		Spell lightningLance = new Spell("Lightning Lance", 25, 4, player, effectFactory.lightningLance(player), false, false);
		return lightningLance;
	}
	
	public Spell staticSurge(Creature player) {
		Spell staticSurge = new Spell("Static Surge", 26, 4, player, effectFactory.staticSurge(player), true, false);
		return staticSurge;
	}
	
	public Spell haste(Creature player) {
		Spell haste = new Spell("Haste", 27, 4, player, effectFactory.hasteSpell(player), true, false);
		return haste;
	}
	
	public Spell archmagesAegis(Creature player) {
		Spell archmagesAegis = new Spell("Archmage's Aegis", 28, 4, player, effectFactory.archmagesAegis(player), true, false);
		return archmagesAegis;
	}
	
	public Spell findTraps(Creature player) {
		Spell findTraps = new Spell("Find Traps", 29, 4, player, effectFactory.findTraps(), true, false);
		return findTraps;
	}
	
	public Spell toxicTransfusion(Creature player) {
		Spell toxicTransfusion = new Spell("Toxic Transfusion", 30, 4, player, effectFactory.toxicTransfusion(player), false, false);
		return toxicTransfusion;
	}
	


}
