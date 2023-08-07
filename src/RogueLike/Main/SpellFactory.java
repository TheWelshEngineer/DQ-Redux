package RogueLike.Main;

public class SpellFactory {
	
	public EffectFactory effectFactory;
	
	public SpellFactory(EffectFactory factory) {
		this.effectFactory = factory;
	}
	
	public Spell firebolt(Creature player) {
		Spell firebolt = new Spell("firebolt", 0, 4, player, effectFactory.firebolt(player), false, false);
		return firebolt;
	}
	
	public Spell repel(Creature player) {
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
		Spell confuse = new Spell("confusion", 12, 0, player, effectFactory.confused(), false, false);
		return confuse;
	}
	
	public Spell overgrowth(Creature player) {
		Spell wildgrowth = new Spell("overgrowth", 13, 6, player, effectFactory.overgrow(), false, false);
		return wildgrowth;
	}
	
	public Spell frostWard(Creature player) {
		Effect temp = effectFactory.frostWard();
		temp.setDuration(12);
		Spell frostWard = new Spell("frost ward", 14, 8, player, temp, true, false);
		return frostWard;
	}
	
	public Spell levitate(Creature player) {
		Effect temp = effectFactory.levitating();
		temp.setDuration(16);
		Spell levitate = new Spell("levitate", 15, 8, player, temp, true, false);
		return levitate;
	}
	
	public Spell paralysis(Creature player) {
		Effect temp = effectFactory.paralyzed();
		temp.setDuration(8);
		Spell paralysis = new Spell("paralysis", 16, 10, player, temp, false, false);
		return paralysis;
	}
	
	public Spell beastForm(Creature player) {
		Effect temp = effectFactory.beastForm();
		temp.setDuration(10);
		Spell beastForm = new Spell("beast form", 17, 8, player, temp, true, false);
		return beastForm;
	}
	
	public Spell confusion(Creature player) {
		Effect temp = effectFactory.confused();
		temp.setDuration(10);
		Spell confuse = new Spell("confusion", 18, 8, player, temp, false, false);
		return confuse;
	}
	


}
