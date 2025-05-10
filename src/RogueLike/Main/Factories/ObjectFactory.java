package RogueLike.Main.Factories;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RogueLike.Main.Description;
import RogueLike.Main.Dice;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Enchantments.Enchantment;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Entities.Traps.AcidTrap;
import RogueLike.Main.Entities.Traps.BlinkTrap;
import RogueLike.Main.Entities.Traps.FireTrap;
import RogueLike.Main.Entities.Traps.FrostbiteTrap;
import RogueLike.Main.Entities.Traps.LightningTrap;
import RogueLike.Main.Entities.Traps.SmokeTrap;
import RogueLike.Main.Entities.Traps.SummoningTrap;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Utils.PointShapes.Point;

public class ObjectFactory implements Serializable {
	
	private static final long serialVersionUID = 2107505917946865299L;
	
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
	
	private World world;

	public ObjectFactory() {
		this.world = World.getInstance();
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
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfPoison(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfGiantStrength(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfMana(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfInvisibility(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfParalysis(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfCausticGas(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfHealing(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfRestoration(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfMindVision(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfOvergrowth(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfCombustion(0, false));
		potionIndex.add(FactoryManager.getItemFactory().newPotionOfLevitation(0, false));
		
		Collections.shuffle(potionIndex);
	}
	
	private void setUpWandAppearances() {
		wandColors = new HashMap<String, Description>();
		wandColors.put("Oak Wand", new Description("oak wood", ExtendedAsciiPanel.paper)); 
		wandColors.put("Ash Wand", new Description("ash wood", ExtendedAsciiPanel.brightGreen));
		wandColors.put("Thorn Wand", new Description("thornwood", ExtendedAsciiPanel.brightMagenta));
		wandColors.put("Beech Wand", new Description("beech wood", ExtendedAsciiPanel.brown));
		wandColors.put("Cherry Wand", new Description("cherry wood", ExtendedAsciiPanel.pink));
		wandColors.put("Willow Wand", new Description("willow", ExtendedAsciiPanel.paleRed));
		wandColors.put("Maple Wand", new Description("maple wood", ExtendedAsciiPanel.orange));
		wandColors.put("Birch Wand", new Description("birch wood", ExtendedAsciiPanel.white));
		wandColors.put("Rowan Wand", new Description("rowan", ExtendedAsciiPanel.apple));
		wandColors.put("Cedar Wand", new Description("cedar wood", ExtendedAsciiPanel.magenta));
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
		wandColors.put("Pear Wand", new Description("pearwood", ExtendedAsciiPanel.paralyzed));
		wandColors.put("Lavender Wand", new Description("braided lavender", ExtendedAsciiPanel.lilac)); //21
		wandColors.put("Heartwood Wand", new Description("heartwood", ExtendedAsciiPanel.kathryn)); //22
		wandColors.put("Copper Wand", new Description("copper", ExtendedAsciiPanel.copper)); //23
		wandColors.put("Driftwood Wand", new Description("driftwood", ExtendedAsciiPanel.seafoam)); //24
		wandColors.put("Driftwood Wand", new Description("driftwood", ExtendedAsciiPanel.seafoam)); //24
		wandColors.put("Wheatgrass Wand", new Description("braided wheatgrass", ExtendedAsciiPanel.wheatgrass)); //24
		wandAppearances = new ArrayList<String>(wandColors.keySet());
		Collections.shuffle(wandAppearances);
	}
	
	public void setUpWandIndex() {
		Creature player = this.world.player();
		wandIndex = new ArrayList<Item>();
		wandIndex.add(FactoryManager.getItemFactory().newMagicMissileWand(0, player, false));
		wandIndex.add(FactoryManager.getItemFactory().newForceBlastWand(0, player, false));
		
		wandIndex.add(FactoryManager.getItemFactory().newFireboltWand(0, player, false));
		
		wandIndex.add(FactoryManager.getItemFactory().newFlashFreezeWand(0, player, false));
		wandIndex.add(FactoryManager.getItemFactory().newIceWallWand(0, player, false));
		wandIndex.add(FactoryManager.getItemFactory().newIceKnifeWand(0, player, false));
		
		wandIndex.add(FactoryManager.getItemFactory().newChainLightningWand(0, player, false));
		
		wandIndex.add(FactoryManager.getItemFactory().newAcidBlastWand(0, player, false));
		
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
	
	public void setUpRingIndex() {
		ringIndex = new ArrayList<Item>();
		ringIndex.add(FactoryManager.getItemFactory().newStrengthRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newDexterityRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newIntelligenceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newArmorRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newFireResistanceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newFrostResistanceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newShockResistanceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newPoisonResistanceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newAcidResistanceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newMagicResistanceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newChaosResistanceRing(0, false, false));
		ringIndex.add(FactoryManager.getItemFactory().newVisionRing(0, false, false));
		
		
		
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
	
	public void setUpScrollIndex() {

		Creature player = this.world.player();
		scrollIndex = new ArrayList<Item>();
		scrollIndex.add(FactoryManager.getItemFactory().newScrollOfIdentify(0, player, false));
		scrollIndex.add(FactoryManager.getItemFactory().newScrollOfMagicMapping(0, player, false));
		scrollIndex.add(FactoryManager.getItemFactory().newScrollOfSummonMonsters(0, player, false));
		scrollIndex.add(FactoryManager.getItemFactory().newScrollOfUpgrade(0, player, false));
		scrollIndex.add(FactoryManager.getItemFactory().newScrollOfRemoveCurse(0, player, false));
		scrollIndex.add(FactoryManager.getItemFactory().newScrollOfEnchantment(0, player, false));
		scrollIndex.add(FactoryManager.getItemFactory().newScrollOfConfusion(0, player, false));
		
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
		item.setEnchantment(FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
	}
	
	public void randomEnchantArmor(Item item) {
		item.setEnchantment(FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
	}
	
	public void curseItem(Item item) {
		item.setCurse(FactoryManager.getEnchantmentFactory().randomCurse(item));
	}

	//generators
	public Item randomWeapon(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return randomSimpleWeapon(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return randomMartialWeapon(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return randomFinesseWeapon(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 4: return randomRangedWeapon(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return randomSimpleWeapon(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public Item randomSimpleWeapon(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 8)) {
		case 1: return FactoryManager.getItemFactory().newClub(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return FactoryManager.getItemFactory().newDart(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return FactoryManager.getItemFactory().newQuarterstaff(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 4: return FactoryManager.getItemFactory().newSpear(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 5: return FactoryManager.getItemFactory().newGreatclub(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 6: return FactoryManager.getItemFactory().newThrowingAxe(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 7: return FactoryManager.getItemFactory().newMattock(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 8: return FactoryManager.getItemFactory().newMaul(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return FactoryManager.getItemFactory().newClub(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public Item randomMartialWeapon(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 9)) {
		case 1: return FactoryManager.getItemFactory().newShortsword(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return FactoryManager.getItemFactory().newKnuckleduster(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return FactoryManager.getItemFactory().newMace(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 4: return FactoryManager.getItemFactory().newThrowingHammer(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 5: return FactoryManager.getItemFactory().newLongsword(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 6: return FactoryManager.getItemFactory().newHalberd(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 7: return FactoryManager.getItemFactory().newWarhammer(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 8: return FactoryManager.getItemFactory().newJavelin(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 9: return FactoryManager.getItemFactory().newGreatsword(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return FactoryManager.getItemFactory().newShortsword(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public Item randomFinesseWeapon(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return FactoryManager.getItemFactory().newDagger(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return FactoryManager.getItemFactory().newRapier(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return FactoryManager.getItemFactory().newFalchion(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 4: return FactoryManager.getItemFactory().newBoomerang(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 5: return FactoryManager.getItemFactory().newGlaive(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 6: return FactoryManager.getItemFactory().newFlamberge(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 7: return FactoryManager.getItemFactory().newPairedBlades(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return FactoryManager.getItemFactory().newDagger(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public Item randomRangedWeapon(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return FactoryManager.getItemFactory().newShortbow(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return FactoryManager.getItemFactory().newLongbow(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return FactoryManager.getItemFactory().newLightCrossbow(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 4: return FactoryManager.getItemFactory().newHeavyCrossbow(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 5: return FactoryManager.getItemFactory().newHandCrossbow(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 6: return FactoryManager.getItemFactory().newPistol(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 7: return FactoryManager.getItemFactory().newCaliver(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return FactoryManager.getItemFactory().newShortbow(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public Item randomArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return FactoryManager.getItemFactory().newPaddedClothArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return FactoryManager.getItemFactory().newLeatherArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return FactoryManager.getItemFactory().newStuddedLeatherArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 4: return FactoryManager.getItemFactory().newHideArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 5: return FactoryManager.getItemFactory().newChainmailArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 6: return FactoryManager.getItemFactory().newScaleArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 7: return FactoryManager.getItemFactory().newBreastplate(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 8: return FactoryManager.getItemFactory().newHalfPlate(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 9: return FactoryManager.getItemFactory().newPlateArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 10: return FactoryManager.getItemFactory().newRingMail(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 11: return FactoryManager.getItemFactory().newChainArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 12: return FactoryManager.getItemFactory().newSplintArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return FactoryManager.getItemFactory().newPaddedClothArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public Item randomShield(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return FactoryManager.getItemFactory().newRoundShield(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return FactoryManager.getItemFactory().newTowerShield(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return FactoryManager.getItemFactory().newKiteShield(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return FactoryManager.getItemFactory().newRoundShield(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public Item randomMimicDrop(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return randomWeapon(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 2: return randomArmor(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		case 3: return randomShield(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		default: return randomWeapon(depth, addToWorld, canUpgrade, canEnchant, canCurse);
		}
	}
	
	public void createRandomTrap(Point where) {
		Trap trap;
		switch(ExtraMaths.diceRoll(1, 7)) {
			case 1: trap = new LightningTrap(where.x, where.y, where.z); break;
			case 2: trap = new FrostbiteTrap(where.x, where.y, where.z); break;
			case 3: trap = new SummoningTrap(where.x, where.y, where.z); break;
			case 4: trap = new BlinkTrap(where.x, where.y, where.z); break;
			case 5: trap = new FireTrap(where.x, where.y, where.z); break;
			case 6: trap = new AcidTrap(where.x, where.y, where.z); break;
			case 7: trap = new SmokeTrap(where.x, where.y, where.z); break;
			default: trap = new FireTrap(where.x, where.y, where.z); break;
		}
		World.getInstance().add(trap);
	}
	
	public Item randomAmmunition(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return FactoryManager.getItemFactory().newArrows(depth, addToWorld);
		case 2: return FactoryManager.getItemFactory().newBolts(depth, addToWorld);
		case 3: return FactoryManager.getItemFactory().newPowder(depth, addToWorld);
		default: return FactoryManager.getItemFactory().newArrows(depth, addToWorld);
		}
	}
	
	public Item randomFood(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return FactoryManager.getItemFactory().newRations(depth, addToWorld);
		case 2: return FactoryManager.getItemFactory().newRations(depth, addToWorld);
		case 3: return FactoryManager.getItemFactory().newRations(depth, addToWorld);
		case 4: return FactoryManager.getItemFactory().newRations(depth, addToWorld);
		case 5: return FactoryManager.getItemFactory().newPasty(depth, addToWorld);
		default: return FactoryManager.getItemFactory().newRations(depth, addToWorld);
		}
	}
	
	public Item randomMagicItem(int depth, Creature player, boolean addToWorld, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return randomPotion(depth, addToWorld);
		case 2: return randomScroll(depth, player, addToWorld);
		case 3: return randomWand(depth, player, addToWorld);
		case 4: return randomRing(depth, addToWorld, canCurse);
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
		case 1: return FactoryManager.getItemFactory().newMagicMissileWand(depth, player, addToWorld);
		case 2: return FactoryManager.getItemFactory().newForceBlastWand(depth, player, addToWorld);
		case 3: return FactoryManager.getItemFactory().newFindTrapsWand(depth, player, addToWorld);
		case 4: return FactoryManager.getItemFactory().newArchmagesAegisWand(depth, player, addToWorld);
		
		default: return FactoryManager.getItemFactory().newMagicMissileWand(depth, player, addToWorld);
		}
	}
	
	public Item randomPyromancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return FactoryManager.getItemFactory().newFireboltWand(depth, player, addToWorld);
		case 2: return FactoryManager.getItemFactory().newFlashfireWand(depth, player, addToWorld);
		case 3: return FactoryManager.getItemFactory().newBrazierBarrierWand(depth, player, addToWorld);
		case 4: return FactoryManager.getItemFactory().newPyrotechnicsWand(depth, player, addToWorld);
		
		default: return FactoryManager.getItemFactory().newFireboltWand(depth, player, addToWorld);
		}
	}
	
	public Item randomCryomancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return FactoryManager.getItemFactory().newFlashFreezeWand(depth, player, addToWorld);
		case 2: return FactoryManager.getItemFactory().newIceKnifeWand(depth, player, addToWorld);
		case 3: return FactoryManager.getItemFactory().newIceWallWand(depth, player, addToWorld);
		case 4: return FactoryManager.getItemFactory().newGlaciateWand(depth, player, addToWorld);
		
		default: return FactoryManager.getItemFactory().newFlashFreezeWand(depth, player, addToWorld);
		}
	}
	
	public Item randomElectromancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return FactoryManager.getItemFactory().newChainLightningWand(depth, player, addToWorld);
		case 2: return FactoryManager.getItemFactory().newLightningLanceWand(depth, player, addToWorld);
		case 3: return FactoryManager.getItemFactory().newHasteWand(depth, player, addToWorld);
		case 4: return FactoryManager.getItemFactory().newStaticSurgeWand(depth, player, addToWorld);
		
		default: return FactoryManager.getItemFactory().newChainLightningWand(depth, player, addToWorld);
		}
	}
	
	public Item randomAlchemancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return FactoryManager.getItemFactory().newAcidBlastWand(depth, player, addToWorld);
		case 2: return FactoryManager.getItemFactory().newToxicTransfusionWand(depth, player, addToWorld);
		case 3: return FactoryManager.getItemFactory().newRefluxBarrierWand(depth, player, addToWorld);
		case 4: return FactoryManager.getItemFactory().newLifetapWand(depth, player, addToWorld);
		
		default: return FactoryManager.getItemFactory().newAcidBlastWand(depth, player, addToWorld);
		}
	}
	
	public Item randomFerromancyWand(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return FactoryManager.getItemFactory().newArmorStormWand(depth, player, addToWorld);
		case 2: return FactoryManager.getItemFactory().newWeaponBoltWand(depth, player, addToWorld);
		case 3: return FactoryManager.getItemFactory().newBladsWardWand(depth, player, addToWorld);
		case 4: return FactoryManager.getItemFactory().newInfuseUpgradeWand(depth, player, addToWorld);
		
		default: return FactoryManager.getItemFactory().newArmorStormWand(depth, player, addToWorld);
		}
	}
	
	public Item randomRing(int depth, boolean addToWorld, boolean canCurse) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return FactoryManager.getItemFactory().newStrengthRing(depth, addToWorld, canCurse);
		case 2: return FactoryManager.getItemFactory().newDexterityRing(depth, addToWorld, canCurse);
		case 3: return FactoryManager.getItemFactory().newIntelligenceRing(depth, addToWorld, canCurse);
		case 4: return FactoryManager.getItemFactory().newArmorRing(depth, addToWorld, canCurse);
		case 5: return FactoryManager.getItemFactory().newFireResistanceRing(depth, addToWorld, canCurse);
		case 6: return FactoryManager.getItemFactory().newFrostResistanceRing(depth, addToWorld, canCurse);
		case 7: return FactoryManager.getItemFactory().newShockResistanceRing(depth, addToWorld, canCurse);
		case 8: return FactoryManager.getItemFactory().newPoisonResistanceRing(depth, addToWorld, canCurse);
		case 9: return FactoryManager.getItemFactory().newAcidResistanceRing(depth, addToWorld, canCurse);
		case 10: return FactoryManager.getItemFactory().newMagicResistanceRing(depth, addToWorld, canCurse);
		case 11: return FactoryManager.getItemFactory().newChaosResistanceRing(depth, addToWorld, canCurse);
		case 12: return FactoryManager.getItemFactory().newVisionRing(depth, addToWorld, canCurse);
		default: return FactoryManager.getItemFactory().newStrengthRing(depth, addToWorld, canCurse);
		}
	}
	
	public Item randomScroll(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return FactoryManager.getItemFactory().newScrollOfIdentify(depth, player, addToWorld);
		case 2: return FactoryManager.getItemFactory().newScrollOfMagicMapping(depth, player, addToWorld);
		case 3: return FactoryManager.getItemFactory().newScrollOfSummonMonsters(depth, player, addToWorld);
		case 4: return FactoryManager.getItemFactory().newScrollOfUpgrade(depth, player, addToWorld);
		case 5: return FactoryManager.getItemFactory().newScrollOfRemoveCurse(depth, player, addToWorld);
		case 6: return FactoryManager.getItemFactory().newScrollOfEnchantment(depth, player, addToWorld);
		case 7: return FactoryManager.getItemFactory().newScrollOfConfusion(depth, player, addToWorld);
		default: return FactoryManager.getItemFactory().newScrollOfIdentify(depth, player, addToWorld);
		}
	}
	
	public Item randomPotion(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return FactoryManager.getItemFactory().newPotionOfPoison(depth, addToWorld);
		case 2: return FactoryManager.getItemFactory().newPotionOfGiantStrength(depth, addToWorld);
		case 3: return FactoryManager.getItemFactory().newPotionOfMana(depth, addToWorld);
		case 4: return FactoryManager.getItemFactory().newPotionOfInvisibility(depth, addToWorld);
		case 5: return FactoryManager.getItemFactory().newPotionOfParalysis(depth, addToWorld);
		case 6: return FactoryManager.getItemFactory().newPotionOfCausticGas(depth, addToWorld);
		case 7: return FactoryManager.getItemFactory().newPotionOfHealing(depth, addToWorld);
		case 8: return FactoryManager.getItemFactory().newPotionOfRestoration(depth, addToWorld);
		case 9: return FactoryManager.getItemFactory().newPotionOfMindVision(depth, addToWorld);
		case 10: return FactoryManager.getItemFactory().newPotionOfOvergrowth(depth, addToWorld);
		case 11: return FactoryManager.getItemFactory().newPotionOfCombustion(depth, addToWorld);
		case 12: return FactoryManager.getItemFactory().newPotionOfLevitation(depth, addToWorld);
		default: return FactoryManager.getItemFactory().newPotionOfHealing(depth, addToWorld);
		}
	}
	
	public Item randomPositivePotion(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return FactoryManager.getItemFactory().newPotionOfGiantStrength(depth, addToWorld);
		case 2: return FactoryManager.getItemFactory().newPotionOfMana(depth, addToWorld);
		case 3: return FactoryManager.getItemFactory().newPotionOfInvisibility(depth, addToWorld);
		case 4: return FactoryManager.getItemFactory().newPotionOfHealing(depth, addToWorld);
		case 5: return FactoryManager.getItemFactory().newPotionOfRestoration(depth, addToWorld);
		case 6: return FactoryManager.getItemFactory().newPotionOfMindVision(depth, addToWorld);
		case 7: return FactoryManager.getItemFactory().newPotionOfLevitation(depth, addToWorld);
		default: return FactoryManager.getItemFactory().newPotionOfHealing(depth, addToWorld);
		}
	}
	
	public Item randomNegativePotion(int depth, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return FactoryManager.getItemFactory().newPotionOfPoison(depth, addToWorld);
		case 2: return FactoryManager.getItemFactory().newPotionOfParalysis(depth, addToWorld);
		case 3: return FactoryManager.getItemFactory().newPotionOfCausticGas(depth, addToWorld);
		case 4: return FactoryManager.getItemFactory().newPotionOfOvergrowth(depth, addToWorld);
		case 5: return FactoryManager.getItemFactory().newPotionOfCombustion(depth, addToWorld);
		default: return FactoryManager.getItemFactory().newPotionOfPoison(depth, addToWorld);
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
		case 6: creature = FactoryManager.getCreatureFactory().newBat(depth, addToWorld); break;
		case 7: creature = FactoryManager.getCreatureFactory().newBat(depth, addToWorld); break;
		case 8: creature = FactoryManager.getCreatureFactory().newBat(depth, addToWorld); break;
		case 9: creature = randomSlime(depth, player, addToWorld); break;
		case 10: creature = randomSlime(depth, player, addToWorld); break;
		default: creature = FactoryManager.getCreatureFactory().newFungus(depth, addToWorld); break;
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = FactoryManager.getCreatureFactory().modifyCreatureDamageDealt(creature, FactoryManager.getModifierFactory().randomCreatureModifier());
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = FactoryManager.getCreatureFactory().modifyCreatureResistsDamage(creature, FactoryManager.getModifierFactory().randomCreatureModifier());
		}
		return creature;
	}
	
	public Creature randomFungus(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return FactoryManager.getCreatureFactory().newBloodFungus(depth, player, addToWorld);
		default: return FactoryManager.getCreatureFactory().newFungus(depth, addToWorld);
		}
	}
	
	public Creature randomSlime(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 16)) {
		case 1: return FactoryManager.getCreatureFactory().newMagmaSlime(depth, player, addToWorld);
		case 2: return FactoryManager.getCreatureFactory().newMetalSlime(depth, player, addToWorld);
		case 3: return FactoryManager.getCreatureFactory().newThundercloudSlime(depth, player, addToWorld);
		default: return FactoryManager.getCreatureFactory().newPinkSlime(depth, player, addToWorld);
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
		case 9: creature = FactoryManager.getCreatureFactory().newGremlinSkirmisher(depth, player, addToWorld); break;
		case 10: creature = FactoryManager.getCreatureFactory().newGremlinAlchemist(depth, player, addToWorld); break;
		default: creature = randomSkeleton(depth, player, addToWorld); break;
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = FactoryManager.getCreatureFactory().modifyCreatureDamageDealt(creature, FactoryManager.getModifierFactory().randomCreatureModifier());
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = FactoryManager.getCreatureFactory().modifyCreatureResistsDamage(creature, FactoryManager.getModifierFactory().randomCreatureModifier());
		}
		return creature;
	}
	
	public Creature randomConstruct(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 2)) {
		case 1: return FactoryManager.getCreatureFactory().newAnimatedArmor(depth, player, addToWorld);
		case 2: return FactoryManager.getCreatureFactory().newAnimatedWeapon(depth, player, addToWorld);
		default: return FactoryManager.getCreatureFactory().newAnimatedWeapon(depth, player, addToWorld);
		}
	}
	
	public Creature randomSkeleton(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 15)) {
		case 1: return FactoryManager.getCreatureFactory().newSkeletonCryomancer(depth, player, addToWorld);
		case 2: return FactoryManager.getCreatureFactory().newSkeletonPyromancer(depth, player, addToWorld);
		case 3: return FactoryManager.getCreatureFactory().newSkeletonElectromancer(depth, player, addToWorld);
		default: return FactoryManager.getCreatureFactory().newSkeleton(depth, player, addToWorld);
		}
	}
	
	public Creature randomGremlin(int depth, Creature player, boolean addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return FactoryManager.getCreatureFactory().newGremlinSkirmisher(depth, player, addToWorld);
		case 2: return FactoryManager.getCreatureFactory().newGremlinSkirmisher(depth, player, addToWorld);
		case 3: return FactoryManager.getCreatureFactory().newGremlinAlchemist(depth, player, addToWorld);
		default: return FactoryManager.getCreatureFactory().newGremlin(depth, player, addToWorld);
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
		case 8: creature = FactoryManager.getCreatureFactory().newMimic(depth, player, addToWorld); break;
		case 9: creature = FactoryManager.getCreatureFactory().newCloaker(depth, player, addToWorld); break;
		case 10: creature = FactoryManager.getCreatureFactory().newOgre(depth, player, addToWorld); break;
		default: creature = randomSkeleton(depth, player, addToWorld); break;
		}
		
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = FactoryManager.getCreatureFactory().modifyCreatureDamageDealt(creature, FactoryManager.getModifierFactory().randomCreatureModifier());
		}
		if(ExtraMaths.diceRoll(1, 50) == 1) {
			creature = FactoryManager.getCreatureFactory().modifyCreatureResistsDamage(creature, FactoryManager.getModifierFactory().randomCreatureModifier());
		}
		return creature;
	}
	
	public Creature randomChest(int depth, Creature player, boolean addToWorld) {
		switch(Dice.d20.roll()) {
		case 1: return FactoryManager.getCreatureFactory().newMimic(depth, player, addToWorld);
		default: return FactoryManager.getCreatureFactory().newChest(depth, player, addToWorld);
		}
	}

}
