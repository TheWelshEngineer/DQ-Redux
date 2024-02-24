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
		Spell firebolt = new Spell("firebolt", 0, 4, player, effectFactory.firebolt(player), false, false);
		return firebolt;
	}
	
	public Spell forceBlast(Creature player) {
		Spell repel = new Spell("repel", 1, 4, player, effectFactory.repelWand(player), false, false);
		return repel;
	}
	
	public Spell flashFreeze(Creature player) {
		Spell freeze = new Spell("flash freeze", 2, 4, player, effectFactory.flashFreeze(player), false, false);
		return freeze;
	}
	
	public Spell chainLightning(Creature player) {
		Spell lightning = new Spell("chain lightning", 3, 4, player, effectFactory.chainLightning(player), false, false);
		return lightning;
	}
	
	public Spell magicMissile(Creature player) {
		Spell missile = new Spell("magic missile", 4, 4, player, effectFactory.magicMissile(player), false, false);
		return missile;
	}
	
	public Spell iceWall(Creature player) {
		Spell wall = new Spell("ice wall", 5, 8, player, effectFactory.iceWall(player), false, true);
		return wall;
	}
	
	public Spell magicMappingScroll(Creature player) {
		Spell map = new Spell("magic mapping", 6, 0, player, effectFactory.magicMappingScroll(), true, false);
		return map;
	}
	
	public Spell identifyScroll(Creature player) {
		Spell identify = new Spell("identify", 7, 0, player, effectFactory.identifyScroll(), true, false);
		return identify;
	}
	
	public Spell summonMonstersScroll(Creature player) {
		Spell monster = new Spell("summon monsters", 8, 0, player, effectFactory.summonMonstersScroll(player), false, true);
		return monster;
	}
	
	public Spell upgradeScroll(Creature player) {
		Spell upgrade = new Spell("upgrade", 9, 0, player, effectFactory.upgradeScroll(), true, false);
		return upgrade;
	}
	
	public Spell removeCurseScroll(Creature player) {
		Spell curse = new Spell("remove curse", 10, 0, player, effectFactory.removeCurseScroll(), true, false);
		return curse;
	}
	
	public Spell enchantScroll(Creature player) {
		Spell enchant = new Spell("enchant", 11, 0, player, effectFactory.enchantScroll(), true, false);
		return enchant;
	}
	
	public Spell confuseScroll(Creature player) {
		Spell confuse = new Spell("confusion", 12, 0, player, effectFactory.confused(5), false, false);
		return confuse;
	}
	
	public Spell iceKnife(Creature player) {
		Spell iceKnife = new Spell("ice knife", 19, 8, player, effectFactory.iceKnife(player), false, false);
		return iceKnife;
	}
	
	public Spell acidBlast(Creature player) {
		Spell acidBlast = new Spell("acid blast", 20, 4, player, effectFactory.acidBlast(player), false, false);
		return acidBlast;
	}
	
	public Spell brazierBarrier(Creature player) {
		Spell brazierBarrier = new Spell("brazier barrier", 21, 4, player, effectFactory.brazierBarrier(player), true, false);
		return brazierBarrier;
	}
	
	public Spell pyrotechnics(Creature player) {
		Spell pyrotechnics = new Spell("pyrotechnics", 22, 4, player, effectFactory.pyrotechnics(player), false, false);
		return pyrotechnics;
	}
	
	public Spell flashfire(Creature player) {
		Spell flashfire = new Spell("flashfire", 23, 4, player, effectFactory.flashfire(player), false, false);
		return flashfire;
	}
	


}
