package RogueLike.Main.Factories;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Description;
import RogueLike.Main.Dice;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Entities.Traps.*;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.*;
import RogueLike.Main.Enchantments.*;
import RogueLike.Main.Items.*;

public class ObjectFactory {
	
	public World world;
	
	//public Map<String, Color> potionColors;
	public Map<String, Description> potionColors;
	public List<String> potionAppearances;
	public List<Item> potionIndex;
	
	public Map<String, Description> wandColors;
	public List<String> wandAppearances;
	public List<Item> wandIndex;
	
	public Map<String, Color> ringColors;
	public List<String> ringAppearances;
	public List<Item> ringIndex;
	
	public Map<String, Color> scrollColors;
	public List<String> scrollAppearances;
	public List<Item> scrollIndex;
	
	public EffectFactory effectFactory = new EffectFactory(this);
	public SpellFactory spellFactory = new SpellFactory(effectFactory);
	public FeatFactory featFactory = new FeatFactory();
	public EnchantmentFactory enchantmentFactory = new EnchantmentFactory(effectFactory);
	public ItemFactory itemFactory = new ItemFactory(this);
	public CreatureFactory creatureFactory = new CreatureFactory(this);
	public ParticleFactory particleFactory = new ParticleFactory();
	
	public ObjectFactory(World world) {
		this.world = world;
		setUpPotionAppearances();
		//setUpPotionIndex();
		setUpWandAppearances();
		
		setUpRingAppearances();
		
		setUpScrollAppearances();
		
	}
	
	private void setUpPotionAppearances() {
		potionColors = new HashMap<String, Description>();
		potionColors.put("Crimson Potion", new Description("crimson", ExtendedAsciiPanel.brightRed));
		potionColors.put("Golden Potion", new Description("golden", ExtendedAsciiPanel.gold));
		potionColors.put("Lime Potion", new Description("lime", ExtendedAsciiPanel.lime));
		potionColors.put("Cyan Potion", new Description("cyan", ExtendedAsciiPanel.cyan));
		potionColors.put("Cobalt Potion", new Description("cobalt", ExtendedAsciiPanel.cobalt));
		potionColors.put("Magenta Potion", new Description("magenta", ExtendedAsciiPanel.pink));
		potionColors.put("Charcoal Potion", new Description("charcoal", ExtendedAsciiPanel.brightBlack));
		potionColors.put("Silver Potion", new Description("silver", ExtendedAsciiPanel.white));
		potionColors.put("Cloudy Potion", new Description("cloudy", ExtendedAsciiPanel.brightWhite));
		potionColors.put("Pink Potion", new Description("pink", ExtendedAsciiPanel.brightPink));
		potionColors.put("Emerald Potion", new Description("emerald", ExtendedAsciiPanel.brightGreen));
		potionColors.put("Amber Potion", new Description("amber", ExtendedAsciiPanel.orange));
		
		potionAppearances = new ArrayList<String>(potionColors.keySet());
		Collections.shuffle(potionAppearances);
	}
	
	public void setUpPotionIndex() {
		potionIndex = new ArrayList<Item>();
		potionIndex.add(itemFactory.newPotionOfPoison(0, false));
		potionIndex.add(itemFactory.newPotionOfGiantStrength(0, false));
		potionIndex.add(itemFactory.newPotionOfMana(0, false));
		potionIndex.add(itemFactory.newPotionOfInvisibility(0, false));
		potionIndex.add(itemFactory.newPotionOfParalysis(0, false));
		potionIndex.add(itemFactory.newPotionOfCausticGas(0, false));
		potionIndex.add(itemFactory.newPotionOfHealing(0, false));
		potionIndex.add(itemFactory.newPotionOfRestoration(0, false));
		potionIndex.add(itemFactory.newPotionOfMindVision(0, false));
		potionIndex.add(itemFactory.newPotionOfOvergrowth(0, false));
		potionIndex.add(itemFactory.newPotionOfCombustion(0, false));
		potionIndex.add(itemFactory.newPotionOfLevitation(0, false));
		
		Collections.shuffle(potionIndex);
	}
	
	private void setUpWandAppearances() {
		wandColors = new HashMap<String, Description>();
		wandColors.put("Oak Wand", new Description("oak wood", ExtendedAsciiPanel.paper)); 
		wandColors.put("Ash Wand", new Description("ash wood", ExtendedAsciiPanel.brightGreen));
		wandColors.put("Thorn Wand", new Description("thorn wood", ExtendedAsciiPanel.brightMagenta));
		wandColors.put("Beech Wand", new Description("beechwood", ExtendedAsciiPanel.brown));
		wandColors.put("Cherry Wand", new Description("cherry wood", ExtendedAsciiPanel.pink));
		wandColors.put("Willow Wand", new Description("willow", ExtendedAsciiPanel.paleRed));
		wandColors.put("Maple Wand", new Description("maplewood", ExtendedAsciiPanel.orange));
		wandColors.put("Birch Wand", new Description("birchwood", ExtendedAsciiPanel.white));
		wandColors.put("Rowan Wand", new Description("rowan", ExtendedAsciiPanel.apple));
		wandColors.put("Cedar Wand", new Description("cedarwood", ExtendedAsciiPanel.magenta));
		wandColors.put("Bamboo Wand", new Description("bamboo", ExtendedAsciiPanel.green));
		wandColors.put("Pine Wand", new Description("pinewood", ExtendedAsciiPanel.smoke));
		wandColors.put("Stone Wand", new Description("dungeon-stone", ExtendedAsciiPanel.brightBlack));
		wandColors.put("Crystal Wand", new Description("crystalline", ExtendedAsciiPanel.water));
		wandColors.put("Applewood Wand", new Description("applewood", ExtendedAsciiPanel.red));
		wandColors.put("Yew Wand", new Description("yew", ExtendedAsciiPanel.brightYellow));
		wandColors.put("Alder Wand", new Description("alderwood", ExtendedAsciiPanel.brightPink));
		wandColors.put("Baobab Wand", new Description("baobab", ExtendedAsciiPanel.brightBlue));
		wandColors.put("Redwood Wand", new Description("redwood", ExtendedAsciiPanel.brightRed));
		wandColors.put("Sequoia Wand", new Description("sequoia", ExtendedAsciiPanel.yellow));
		wandColors.put("Pear Wand", new Description("pear-wood", ExtendedAsciiPanel.paralyzed));
		wandColors.put("Lavender Wand", new Description("braided lavender", ExtendedAsciiPanel.lilac)); //21
		wandColors.put("Heartwood Wand", new Description("heartwood", ExtendedAsciiPanel.kathryn)); //22
		wandColors.put("Copper Wand", new Description("copper", ExtendedAsciiPanel.copper)); //23
		wandColors.put("Driftwood Wand", new Description("driftwood", ExtendedAsciiPanel.seafoam)); //24
		
		wandAppearances = new ArrayList<String>(wandColors.keySet());
		Collections.shuffle(wandAppearances);
	}
	
	public void setUpWandIndex(Creature player) {
		wandIndex = new ArrayList<Item>();
		wandIndex.add(itemFactory.newMagicMissileWand(0, player, false));
		wandIndex.add(itemFactory.newForceBlastWand(0, player, false));
		
		wandIndex.add(itemFactory.newFireboltWand(0, player, false));
		
		wandIndex.add(itemFactory.newFlashFreezeWand(0, player, false));
		wandIndex.add(itemFactory.newIceWallWand(0, player, false));
		wandIndex.add(itemFactory.newIceKnifeWand(0, player, false));
		
		wandIndex.add(itemFactory.newChainLightningWand(0, player, false));
		
		wandIndex.add(itemFactory.newAcidBlastWand(0, player, false));
		
		Collections.shuffle(wandIndex);
	}
	
	private void setUpRingAppearances() {
		ringColors = new HashMap<String, Color>();
		ringColors.put("Diamond Ring", ExtendedAsciiPanel.brightWhite);
		ringColors.put("Ruby Ring", ExtendedAsciiPanel.brightRed);
		ringColors.put("Emerald Ring", ExtendedAsciiPanel.lime);
		ringColors.put("Quartz Ring", ExtendedAsciiPanel.brightPink);
		ringColors.put("Topaz Ring", ExtendedAsciiPanel.orange);
		ringColors.put("Sapphire Ring", ExtendedAsciiPanel.brightBlue);
		ringColors.put("Amethyst Ring", ExtendedAsciiPanel.lilac);
		ringColors.put("Silver Ring", ExtendedAsciiPanel.white);
		ringColors.put("Opal Ring", ExtendedAsciiPanel.brightBlack);
		ringColors.put("Rusted Ring", ExtendedAsciiPanel.brown);
		ringColors.put("Golden Ring", ExtendedAsciiPanel.brightYellow);
		ringColors.put("Garnet Ring", ExtendedAsciiPanel.red);
		ringColors.put("Amber Ring", ExtendedAsciiPanel.yellow);
		ringColors.put("Turquoise Ring", ExtendedAsciiPanel.brightCyan);
		ringColors.put("Tungsten Ring", ExtendedAsciiPanel.black);
		
		ringAppearances = new ArrayList<String>(ringColors.keySet());
		Collections.shuffle(ringAppearances);
	}
	
	public void setUpRingIndex(Creature player) {
		ringIndex = new ArrayList<Item>();
		ringIndex.add(itemFactory.newStrengthRing(0, false));
		ringIndex.add(itemFactory.newDexterityRing(0, false));
		ringIndex.add(itemFactory.newIntelligenceRing(0, false));
		ringIndex.add(itemFactory.newArmorRing(0, false));
		ringIndex.add(itemFactory.newFireResistanceRing(0, false));
		ringIndex.add(itemFactory.newFrostResistanceRing(0, false));
		ringIndex.add(itemFactory.newShockResistanceRing(0, false));
		ringIndex.add(itemFactory.newPoisonResistanceRing(0, false));
		ringIndex.add(itemFactory.newAcidResistanceRing(0, false));
		ringIndex.add(itemFactory.newMagicResistanceRing(0, false));
		ringIndex.add(itemFactory.newChaosResistanceRing(0, false));
		ringIndex.add(itemFactory.newVisionRing(0, false));
		
		
		
		Collections.shuffle(ringIndex);
	}
	
	private void setUpScrollAppearances() {
		scrollColors = new HashMap<String, Color>();
		scrollColors.put("Scroll of rune LATA", ExtendedAsciiPanel.brightRed);
		scrollColors.put("Scroll of rune MAELA", ExtendedAsciiPanel.gold);
		scrollColors.put("Scroll of rune GJALDA", ExtendedAsciiPanel.lime);
		scrollColors.put("Scroll of rune SKJOLDR", ExtendedAsciiPanel.cyan);
		scrollColors.put("Scroll of rune KYN", ExtendedAsciiPanel.cobalt);
		scrollColors.put("Scroll of rune MYSS", ExtendedAsciiPanel.pink);
		scrollColors.put("Scroll of rune LEYSA", ExtendedAsciiPanel.brightBlack);
		scrollColors.put("Scroll of rune DJUPR", ExtendedAsciiPanel.white);
		scrollColors.put("Scroll of rune MOEDR", ExtendedAsciiPanel.brightWhite);
		
		scrollAppearances = new ArrayList<String>(scrollColors.keySet());
		Collections.shuffle(scrollAppearances);
	}
	
	public void setUpScrollIndex(Creature player) {
		scrollIndex = new ArrayList<Item>();
		scrollIndex.add(itemFactory.newScrollOfIdentify(0, player, false));
		scrollIndex.add(itemFactory.newScrollOfMagicMapping(0, player, false));
		scrollIndex.add(itemFactory.newScrollOfSummonMonsters(0, player, false));
		scrollIndex.add(itemFactory.newScrollOfUpgrade(0, player, false));
		scrollIndex.add(itemFactory.newScrollOfRemoveCurse(0, player, false));
		scrollIndex.add(itemFactory.newScrollOfEnchantment(0, player, false));
		scrollIndex.add(itemFactory.newScrollOfConfusion(0, player, false));
		
		Collections.shuffle(scrollIndex);
	}
	
	public void upgradeItem(Item item, int amount) {
		item.modifyUpgradeLevel(amount);
		if(item.enchantment() != null) {
			item.enchantment().updateEffectDuration();
		}
		
	}
	
	public void enchantItem(Item item, Enchantment enchantment) {
		item.setEnchantment(enchantment);
	}
	
	public void randomEnchantWeapon(Item item) {
		item.setEnchantment(enchantmentFactory.randomWeaponEnchantment(item));
	}
	
	public void randomEnchantArmor(Item item) {
		item.setEnchantment(enchantmentFactory.randomArmorEnchantment(item));
	}
	
	public void curseItem(Item item) {
		item.setCurse(enchantmentFactory.randomCurse(item));
	}

	//generators
	public Item randomWeapon(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return randomSimpleWeapon(depth, addToWorld);
		case 2: return randomMartialWeapon(depth, addToWorld);
		case 3: return randomFinesseWeapon(depth, addToWorld);
		case 4: return randomRangedWeapon(depth, addToWorld);
		default: return randomSimpleWeapon(depth, addToWorld);
		}
	}
	
	public Item randomSimpleWeapon(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 8)) {
		case 1: return itemFactory.newClub(depth, addToWorld);
		case 2: return itemFactory.newDart(depth, addToWorld);
		case 3: return itemFactory.newQuarterstaff(depth, addToWorld);
		case 4: return itemFactory.newSpear(depth, addToWorld);
		case 5: return itemFactory.newGreatclub(depth, addToWorld);
		case 6: return itemFactory.newThrowingAxe(depth, addToWorld);
		case 7: return itemFactory.newMattock(depth, addToWorld);
		case 8: return itemFactory.newMaul(depth, addToWorld);
		default: return itemFactory.newClub(depth, addToWorld);
		}
	}
	
	public Item randomMartialWeapon(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 9)) {
		case 1: return itemFactory.newShortsword(depth, addToWorld);
		case 2: return itemFactory.newKnuckleduster(depth, addToWorld);
		case 3: return itemFactory.newMace(depth, addToWorld);
		case 4: return itemFactory.newThrowingHammer(depth, addToWorld);
		case 5: return itemFactory.newLongsword(depth, addToWorld);
		case 6: return itemFactory.newHalberd(depth, addToWorld);
		case 7: return itemFactory.newWarhammer(depth, addToWorld);
		case 8: return itemFactory.newJavelin(depth, addToWorld);
		case 9: return itemFactory.newGreatsword(depth, addToWorld);
		default: return itemFactory.newShortsword(depth, addToWorld);
		}
	}
	
	public Item randomFinesseWeapon(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return itemFactory.newDagger(depth, addToWorld);
		case 2: return itemFactory.newRapier(depth, addToWorld);
		case 3: return itemFactory.newFalchion(depth, addToWorld);
		case 4: return itemFactory.newBoomerang(depth, addToWorld);
		case 5: return itemFactory.newGlaive(depth, addToWorld);
		case 6: return itemFactory.newFlamberge(depth, addToWorld);
		case 7: return itemFactory.newPairedBlades(depth, addToWorld);
		default: return itemFactory.newDagger(depth, addToWorld);
		}
	}
	
	public Item randomRangedWeapon(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return itemFactory.newShortbow(depth, addToWorld);
		case 2: return itemFactory.newLongbow(depth, addToWorld);
		case 3: return itemFactory.newLightCrossbow(depth, addToWorld);
		case 4: return itemFactory.newHeavyCrossbow(depth, addToWorld);
		case 5: return itemFactory.newHandCrossbow(depth, addToWorld);
		case 6: return itemFactory.newPistol(depth, addToWorld);
		case 7: return itemFactory.newCaliver(depth, addToWorld);
		default: return itemFactory.newShortbow(depth, addToWorld);
		}
	}
	
	public Item randomArmor(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return itemFactory.newPaddedClothArmor(depth, addToWorld);
		case 2: return itemFactory.newLeatherArmor(depth, addToWorld);
		case 3: return itemFactory.newStuddedLeatherArmor(depth, addToWorld);
		case 4: return itemFactory.newHideArmor(depth, addToWorld);
		case 5: return itemFactory.newChainmailArmor(depth, addToWorld);
		case 6: return itemFactory.newScaleArmor(depth, addToWorld);
		case 7: return itemFactory.newBreastplate(depth, addToWorld);
		case 8: return itemFactory.newHalfPlate(depth, addToWorld);
		case 9: return itemFactory.newPlateArmor(depth, addToWorld);
		case 10: return itemFactory.newRingMail(depth, addToWorld);
		case 11: return itemFactory.newChainArmor(depth, addToWorld);
		case 12: return itemFactory.newSplintArmor(depth, addToWorld);
		default: return itemFactory.newPaddedClothArmor(depth, addToWorld);
		}
	}
	
	public Item randomShield(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return itemFactory.newRoundShield(depth, addToWorld);
		case 2: return itemFactory.newTowerShield(depth, addToWorld);
		case 3: return itemFactory.newKiteShield(depth, addToWorld);
		default: return itemFactory.newRoundShield(depth, addToWorld);
		}
	}
	
	public Item randomMimicDrop(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return randomWeapon(depth, addToWorld);
		case 2: return randomArmor(depth, addToWorld);
		case 3: return randomShield(depth, addToWorld);
		default: return randomWeapon(depth, addToWorld);
		}
	}
	
	public void createRandomTrap(Point where) {
		Trap trap;
		switch(ExtraMaths.diceRoll(1, 7)) {
			case 1: trap = new LightningTrap(where.x, where.y, where.z, world); break;
			case 2: trap = new FrostbiteTrap(where.x, where.y, where.z, world); break;
			case 3: trap = new SummoningTrap(where.x, where.y, where.z, world); break;
			case 4: trap = new BlinkTrap(where.x, where.y, where.z, world); break;
			case 5: trap = new FireTrap(where.x, where.y, where.z, world); break;
			case 6: trap = new AcidTrap(where.x, where.y, where.z, world); break;
			case 7: trap = new SmokeTrap(where.x, where.y, where.z, world); break;
			default: trap = new FireTrap(where.x, where.y, where.z, world); break;
		}
		world.add(trap);
	}
	
	public Item randomAmmunition(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return itemFactory.newArrows(depth, addToWorld);
		case 2: return itemFactory.newBolts(depth, addToWorld);
		case 3: return itemFactory.newPowder(depth, addToWorld);
		default: return itemFactory.newArrows(depth, addToWorld);
		}
	}
	
	public Item randomFood(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return itemFactory.newRations(depth, addToWorld);
		case 2: return itemFactory.newRations(depth, addToWorld);
		case 3: return itemFactory.newRations(depth, addToWorld);
		case 4: return itemFactory.newRations(depth, addToWorld);
		case 5: return itemFactory.newPasty(depth, addToWorld);
		default: return itemFactory.newRations(depth, addToWorld);
		}
	}
	
	public Item randomMagicItem(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return randomPotion(depth, addToWorld);
		case 2: return randomScroll(depth, player, addToWorld);
		case 3: return randomWand(depth, player, addToWorld);
		case 4: return randomRing(depth, addToWorld);
		default: return randomPotion(depth, addToWorld);
		}
	}
	
	public Item randomWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 6)) {
		case 1: return randomEvocationWand(depth, player, addToWorld);
		case 2: return randomPyromancyWand(depth, player, addToWorld);
		case 3: return randomCryomancyWand(depth, player, addToWorld);
		case 4: return randomElectromancyWand(depth, player, addToWorld);
		case 5: return randomAlchemancyWand(depth, player, addToWorld);
		case 6: return randomFerromancyWand(depth, player, addToWorld);
		
		default: return randomEvocationWand(depth, player, addToWorld);
		}
	}
	
	public Item randomEvocationWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return itemFactory.newMagicMissileWand(depth, player, addToWorld);
		case 2: return itemFactory.newForceBlastWand(depth, player, addToWorld);
		case 3: return itemFactory.newFindTrapsWand(depth, player, addToWorld);
		case 4: return itemFactory.newArchmagesAegisWand(depth, player, addToWorld);
		
		default: return itemFactory.newMagicMissileWand(depth, player, addToWorld);
		}
	}
	
	public Item randomPyromancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return itemFactory.newFireboltWand(depth, player, addToWorld);
		case 2: return itemFactory.newFlashfireWand(depth, player, addToWorld);
		case 3: return itemFactory.newBrazierBarrierWand(depth, player, addToWorld);
		case 4: return itemFactory.newPyrotechnicsWand(depth, player, addToWorld);
		
		default: return itemFactory.newFireboltWand(depth, player, addToWorld);
		}
	}
	
	public Item randomCryomancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return itemFactory.newFlashFreezeWand(depth, player, addToWorld);
		case 2: return itemFactory.newIceKnifeWand(depth, player, addToWorld);
		case 3: return itemFactory.newIceWallWand(depth, player, addToWorld);
		case 4: return itemFactory.newGlaciateWand(depth, player, addToWorld);
		
		default: return itemFactory.newFlashFreezeWand(depth, player, addToWorld);
		}
	}
	
	public Item randomElectromancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return itemFactory.newChainLightningWand(depth, player, addToWorld);
		case 2: return itemFactory.newLightningLanceWand(depth, player, addToWorld);
		case 3: return itemFactory.newHasteWand(depth, player, addToWorld);
		case 4: return itemFactory.newStaticSurgeWand(depth, player, addToWorld);
		
		default: return itemFactory.newChainLightningWand(depth, player, addToWorld);
		}
	}
	
	public Item randomAlchemancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return itemFactory.newAcidBlastWand(depth, player, addToWorld);
		case 2: return itemFactory.newToxicTransfusionWand(depth, player, addToWorld);
		case 3: return itemFactory.newRefluxBarrierWand(depth, player, addToWorld);
		case 4: return itemFactory.newLifetapWand(depth, player, addToWorld);
		
		default: return itemFactory.newAcidBlastWand(depth, player, addToWorld);
		}
	}
	
	public Item randomFerromancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return itemFactory.newArmorStormWand(depth, player, addToWorld);
		case 2: return itemFactory.newWeaponBoltWand(depth, player, addToWorld);
		case 3: return itemFactory.newBladsWardWand(depth, player, addToWorld);
		case 4: return itemFactory.newInfuseUpgradeWand(depth, player, addToWorld);
		
		default: return itemFactory.newArmorStormWand(depth, player, addToWorld);
		}
	}
	
	public Item randomRing(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return itemFactory.newStrengthRing(depth, addToWorld);
		case 2: return itemFactory.newDexterityRing(depth, addToWorld);
		case 3: return itemFactory.newIntelligenceRing(depth, addToWorld);
		case 4: return itemFactory.newArmorRing(depth, addToWorld);
		case 5: return itemFactory.newFireResistanceRing(depth, addToWorld);
		case 6: return itemFactory.newFrostResistanceRing(depth, addToWorld);
		case 7: return itemFactory.newShockResistanceRing(depth, addToWorld);
		case 8: return itemFactory.newPoisonResistanceRing(depth, addToWorld);
		case 9: return itemFactory.newAcidResistanceRing(depth, addToWorld);
		case 10: return itemFactory.newMagicResistanceRing(depth, addToWorld);
		case 11: return itemFactory.newChaosResistanceRing(depth, addToWorld);
		case 12: return itemFactory.newVisionRing(depth, addToWorld);
		default: return itemFactory.newStrengthRing(depth, addToWorld);
		}
	}
	
	public Item randomScroll(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return itemFactory.newScrollOfIdentify(depth, player, addToWorld);
		case 2: return itemFactory.newScrollOfMagicMapping(depth, player, addToWorld);
		case 3: return itemFactory.newScrollOfSummonMonsters(depth, player, addToWorld);
		case 4: return itemFactory.newScrollOfUpgrade(depth, player, addToWorld);
		case 5: return itemFactory.newScrollOfRemoveCurse(depth, player, addToWorld);
		case 6: return itemFactory.newScrollOfEnchantment(depth, player, addToWorld);
		case 7: return itemFactory.newScrollOfConfusion(depth, player, addToWorld);
		default: return itemFactory.newScrollOfIdentify(depth, player, addToWorld);
		}
	}
	
	public Item randomPotion(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return itemFactory.newPotionOfPoison(depth, addToWorld);
		case 2: return itemFactory.newPotionOfGiantStrength(depth, addToWorld);
		case 3: return itemFactory.newPotionOfMana(depth, addToWorld);
		case 4: return itemFactory.newPotionOfInvisibility(depth, addToWorld);
		case 5: return itemFactory.newPotionOfParalysis(depth, addToWorld);
		case 6: return itemFactory.newPotionOfCausticGas(depth, addToWorld);
		case 7: return itemFactory.newPotionOfHealing(depth, addToWorld);
		case 8: return itemFactory.newPotionOfRestoration(depth, addToWorld);
		case 9: return itemFactory.newPotionOfMindVision(depth, addToWorld);
		case 10: return itemFactory.newPotionOfOvergrowth(depth, addToWorld);
		case 11: return itemFactory.newPotionOfCombustion(depth, addToWorld);
		case 12: return itemFactory.newPotionOfLevitation(depth, addToWorld);
		default: return itemFactory.newPotionOfHealing(depth, addToWorld);
		}
	}
	
	public Item randomPositivePotion(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return itemFactory.newPotionOfGiantStrength(depth, addToWorld);
		case 2: return itemFactory.newPotionOfMana(depth, addToWorld);
		case 3: return itemFactory.newPotionOfInvisibility(depth, addToWorld);
		case 4: return itemFactory.newPotionOfHealing(depth, addToWorld);
		case 5: return itemFactory.newPotionOfRestoration(depth, addToWorld);
		case 6: return itemFactory.newPotionOfMindVision(depth, addToWorld);
		case 7: return itemFactory.newPotionOfLevitation(depth, addToWorld);
		default: return itemFactory.newPotionOfHealing(depth, addToWorld);
		}
	}
	
	public Item randomNegativePotion(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return itemFactory.newPotionOfPoison(depth, addToWorld);
		case 2: return itemFactory.newPotionOfParalysis(depth, addToWorld);
		case 3: return itemFactory.newPotionOfCausticGas(depth, addToWorld);
		case 4: return itemFactory.newPotionOfOvergrowth(depth, addToWorld);
		case 5: return itemFactory.newPotionOfCombustion(depth, addToWorld);
		default: return itemFactory.newPotionOfPoison(depth, addToWorld);
		}
	}
	
	public Creature randomLesserMonster(int depth, Creature player, boolean addToWorld) {
		Creature creature;
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: creature = randomFungus(depth, player, addToWorld); break;
		case 2: creature = randomFungus(depth, player, addToWorld); break;
		case 3: creature = randomFungus(depth, player, addToWorld); break;
		case 4: creature = randomFungus(depth, player, addToWorld); break;
		case 5: creature = randomSlime(depth, player, addToWorld); break;
		case 6: creature = creatureFactory.newBat(depth, addToWorld); break;
		case 7: creature = creatureFactory.newBat(depth, addToWorld); break;
		case 8: creature = creatureFactory.newBat(depth, addToWorld); break;
		case 9: creature = randomSlime(depth, player, addToWorld); break;
		case 10: creature = randomSlime(depth, player, addToWorld); break;
		default: creature = creatureFactory.newFungus(depth, addToWorld); break;
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = creatureFactory.modifyCreatureDamageDealt(creature, creatureFactory.modifierFactory.randomCreatureModifier());
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = creatureFactory.modifyCreatureResistsDamage(creature, creatureFactory.modifierFactory.randomCreatureModifier());
		}
		return creature;
	}
	
	public Creature randomFungus(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return creatureFactory.newBloodFungus(depth, player, addToWorld);
		default: return creatureFactory.newFungus(depth, addToWorld);
		}
	}
	
	public Creature randomSlime(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 16)) {
		case 1: return creatureFactory.newMagmaSlime(depth, player, addToWorld);
		case 2: return creatureFactory.newMetalSlime(depth, player, addToWorld);
		case 3: return creatureFactory.newThundercloudSlime(depth, player, addToWorld);
		default: return creatureFactory.newPinkSlime(depth, player, addToWorld);
		}
	}
	
	public Creature randomMediumMonster(int depth, Creature player, boolean addToWorld) {
		Creature creature;
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: creature = randomSkeleton(depth, player, addToWorld); break;
		case 2: creature = randomSkeleton(depth, player, addToWorld); break;
		case 3: creature = randomSlime(depth, player, addToWorld); break;
		case 4: creature = randomSlime(depth, player, addToWorld); break;
		case 5: creature = randomGremlin(depth, player, addToWorld); break;
		case 6: creature = randomGremlin(depth, player, addToWorld); break;
		case 7: creature = randomConstruct(depth, player, addToWorld); break;
		case 8: creature = randomConstruct(depth, player, addToWorld); break;
		case 9: creature = creatureFactory.newGremlinSkirmisher(depth, player, addToWorld); break;
		case 10: creature = creatureFactory.newGremlinAlchemist(depth, player, addToWorld); break;
		default: creature = randomSkeleton(depth, player, addToWorld); break;
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = creatureFactory.modifyCreatureDamageDealt(creature, creatureFactory.modifierFactory.randomCreatureModifier());
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = creatureFactory.modifyCreatureResistsDamage(creature, creatureFactory.modifierFactory.randomCreatureModifier());
		}
		return creature;
	}
	
	public Creature randomConstruct(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 2)) {
		case 1: return creatureFactory.newAnimatedArmor(depth, player, addToWorld);
		case 2: return creatureFactory.newAnimatedWeapon(depth, player, addToWorld);
		default: return creatureFactory.newAnimatedWeapon(depth, player, addToWorld);
		}
	}
	
	public Creature randomSkeleton(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 15)) {
		case 1: return creatureFactory.newSkeletonCryomancer(depth, player, addToWorld);
		case 2: return creatureFactory.newSkeletonPyromancer(depth, player, addToWorld);
		case 3: return creatureFactory.newSkeletonElectromancer(depth, player, addToWorld);
		default: return creatureFactory.newSkeleton(depth, player, addToWorld);
		}
	}
	
	public Creature randomGremlin(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return creatureFactory.newGremlinSkirmisher(depth, player, addToWorld);
		case 2: return creatureFactory.newGremlinSkirmisher(depth, player, addToWorld);
		case 3: return creatureFactory.newGremlinAlchemist(depth, player, addToWorld);
		default: return creatureFactory.newGremlin(depth, player, addToWorld);
		}
	}
	
	public Creature randomGreaterMonster(int depth, Creature player, boolean addToWorld) {
		Creature creature;
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: creature = randomSkeleton(depth, player, addToWorld); break;
		case 2: creature = randomSkeleton(depth, player, addToWorld); break;
		case 3: creature = randomGremlin(depth, player, addToWorld); break;
		case 4: creature = randomGremlin(depth, player, addToWorld); break;
		case 5: creature = randomGremlin(depth, player, addToWorld); break;
		case 6: creature = randomConstruct(depth, player, addToWorld); break;
		case 7: creature = randomConstruct(depth, player, addToWorld); break;
		case 8: creature = creatureFactory.newMimic(depth, player, addToWorld); break;
		case 9: creature = creatureFactory.newCloaker(depth, player, addToWorld); break;
		case 10: creature = creatureFactory.newOgre(depth, player, addToWorld); break;
		default: creature = randomSkeleton(depth, player, addToWorld); break;
		}
		
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = creatureFactory.modifyCreatureDamageDealt(creature, creatureFactory.modifierFactory.randomCreatureModifier());
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = creatureFactory.modifyCreatureResistsDamage(creature, creatureFactory.modifierFactory.randomCreatureModifier());
		}
		return creature;
	}
	
	public Creature randomChest(int depth, Creature player, boolean addToWorld) {
		switch(Dice.d20.roll()) {
		case 1: return creatureFactory.newMimic(depth, player, addToWorld);
		default: return creatureFactory.newChest(depth, player, addToWorld);
		}
	}

}
