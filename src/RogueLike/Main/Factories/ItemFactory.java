package RogueLike.Main.Factories;

import RogueLike.Main.Dice;
import RogueLike.Main.ExtraColors;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.ArrowsRangedWeapon;
import RogueLike.Main.Items.BasicFinesseWeapon;
import RogueLike.Main.Items.BasicMeleeWeapon;
import RogueLike.Main.Items.BasicShield;
import RogueLike.Main.Items.BasicThrownWeapon;
import RogueLike.Main.Items.BoltsRangedWeapon;
import RogueLike.Main.Items.HeavyArmor;
import RogueLike.Main.Items.IronKey;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Items.LightArmor;
import RogueLike.Main.Items.MediumArmor;
import RogueLike.Main.Items.Potion;
import RogueLike.Main.Items.PowderRangedWeapon;
import RogueLike.Main.Items.Ring;
import RogueLike.Main.Items.Scroll;
import RogueLike.Main.Items.ThrownFinesseWeapon;
import RogueLike.Main.Items.ThrownVersatileWeapon;
import RogueLike.Main.Items.TowerShield;
import RogueLike.Main.Items.TwoHandedFinesseWeapon;
import RogueLike.Main.Items.TwoHandedMeleeWeapon;
import RogueLike.Main.Items.VersatileFinesseWeapon;
import RogueLike.Main.Items.VersatileMeleeWeapon;
import RogueLike.Main.Items.Wand;
import asciiPanel.AsciiPanel;

public class ItemFactory {
	
	public ObjectFactory objectFactory;
	
	public ItemFactory(ObjectFactory factory) {
		this.objectFactory = factory;
	}
	
	//Gold
	public Item newGold(int depth, boolean addToWorld) {
		Item item = new Item((char)155, ExtraColors.brightYellow, "Gold", "Gold");
		item.setIsGold(true);
		item.setBaseGoldValue(ExtraMaths.diceRoll(ExtraMaths.d12(), ExtraMaths.d100()+12)*(depth+1));
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(88);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Rock
	public Item newRock(int depth, int addToWorld) {
		Item rock = new Item(',', AsciiPanel.yellow, "Rock", "Rock");
		rock.setIsStackable(true);
		rock.setID(0);
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(rock, depth);
		}
		return rock;
	}
	
	//Food
	public Item newRations(int depth, int addToWorld) {
		Item item = new Item((char)233, ExtraColors.paper, "Ration of Food", "Ration of Food");
		item.modifyFoodValue(300);
		item.setIsStackable(true);
		item.setBaseGoldValue(5);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(1);
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPasty(int depth, int addToWorld) {
		Item item = new Item((char)233, ExtraColors.paper, "Dwarven Pasty", "Dwarven Pasty");
		item.modifyFoodValue(600);
		item.setIsStackable(true);
		item.setBaseGoldValue(15);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(2);
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Keys
	public Item newIronKey(int depth, boolean addToWorld) {
		Item key = new IronKey((char)157, AsciiPanel.white, "Iron Key", null, depth, 100);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(key, depth);
		}
		return key;
	}
	
	//Victory Item
	public Item newVictoryItem(int depth, int addToWorld) {
		Item item = new Item('*', AsciiPanel.brightWhite, "Eitak's Ancient Axe", "Eitak's Ancient Axe");
		item.setID(200);
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Ammunition
	public Item newArrows(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtraColors.white, "Arrow", "Arrow");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsArrowAmmunition(true);
		item.setIsEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d12());
		item.setID(300);
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newBolts(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtraColors.white, "Crossbow Bolt", "Crossbow Bolt");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsBoltAmmunition(true);
		item.setIsEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d8());
		item.setID(301);
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPowder(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtraColors.white, "Flintlock Bullet", "Flintlock Bullet");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsPowderAmmunition(true);
		item.setIsEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d6());
		item.setID(302);
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Simple Weapons
	public Item newClub(int depth, boolean addToWorld) {
		Item item = new BasicMeleeWeapon(')', AsciiPanel.brightWhite, "Club", null, Dice.d4, 10, 400);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newDart(int depth, boolean addToWorld) {
		Item item = new BasicThrownWeapon(')', AsciiPanel.brightWhite, "Dart", null, Dice.d1, Dice.d4, 10, 401);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newQuarterstaff(int depth, boolean addToWorld) {
		Item item = new VersatileMeleeWeapon(')', AsciiPanel.brightWhite, "Quarterstaff", null, Dice.d6, Dice.d8, 20, 402);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newSpear(int depth, boolean addToWorld) {
		Item item = new ThrownVersatileWeapon(')', AsciiPanel.brightWhite, "Spear", null, Dice.d4, Dice.d6, Dice.d8, 10, 403);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newGreatclub(int depth, boolean addToWorld) {
		Item item = new TwoHandedMeleeWeapon(')', AsciiPanel.brightWhite, "Greatclub", null, Dice.d10, 20, 404);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newThrowingAxe(int depth, boolean addToWorld) {
		Item item = new BasicThrownWeapon(')', AsciiPanel.brightWhite, "Throwing Axe", null, Dice.d6, Dice.d8, 10, 405);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newMattock(int depth, boolean addToWorld) {
		Item item = new VersatileMeleeWeapon(')', AsciiPanel.brightWhite, "Mattock", null, Dice.d8, Dice.d10, 150, 406);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newMaul(int depth, boolean addToWorld) {
		Item item = new TwoHandedMeleeWeapon(')', AsciiPanel.brightWhite, "Maul", null, Dice.d12, 20, 407);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Martial Weapons
	public Item newShortsword(int depth, boolean addToWorld) {
		Item item = new BasicMeleeWeapon(')', AsciiPanel.brightWhite, "Shortsword", null, Dice.d6, 10, 500);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newKnuckleduster(int depth, boolean addToWorld) {
		Item item = new BasicMeleeWeapon(')', AsciiPanel.brightWhite, "Knuckleduster", null, Dice.d4, 10, 501);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newMace(int depth, boolean addToWorld) {
		Item item = new VersatileMeleeWeapon(')', AsciiPanel.brightWhite, "Mace", null, Dice.d6, Dice.d8, 20, 502);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newThrowingHammer(int depth, boolean addToWorld) {
		Item item = new BasicThrownWeapon(')', AsciiPanel.brightWhite, "Throwing Hammer", null, Dice.d4, Dice.d6, 10, 503);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLongsword(int depth, boolean addToWorld) {
		Item item = new BasicMeleeWeapon(')', AsciiPanel.brightWhite, "Longsword", null, Dice.d8, 10, 504);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHalberd(int depth, boolean addToWorld) {
		Item item = new TwoHandedMeleeWeapon(')', AsciiPanel.brightWhite, "Halberd", null, Dice.d10, 20, 505);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newWarhammer(int depth, boolean addToWorld) {
		Item item = new VersatileMeleeWeapon(')', AsciiPanel.brightWhite, "Warhammer", null, Dice.d8, Dice.d10, 20, 506);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newJavelin(int depth, boolean addToWorld) {
		Item item = new BasicThrownWeapon(')', AsciiPanel.brightWhite, "Javelin", null, Dice.d6, Dice.d10, 10, 507);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newGreatsword(int depth, boolean addToWorld) {
		Item item = new TwoHandedMeleeWeapon(')', AsciiPanel.brightWhite, "Greatsword", null, Dice.d12, 20, 508);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Finesse Weapons
	public Item newDagger(int depth, boolean addToWorld) {
		Item item = new ThrownFinesseWeapon(')', AsciiPanel.brightWhite, "Dagger", null, Dice.d4, Dice.d4, 20, 600);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newRapier(int depth, boolean addToWorld) {
		Item item = new BasicFinesseWeapon(')', AsciiPanel.brightWhite, "Rapier", null, Dice.d6, 20, 601);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFalchion(int depth, boolean addToWorld) {
		Item item = new VersatileFinesseWeapon(')', AsciiPanel.brightWhite, "Falchion", null, Dice.d6, Dice.d8, 20, 602);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newBoomerang(int depth, boolean addToWorld) {
		Item item = new ThrownFinesseWeapon(')', AsciiPanel.brightWhite, "Boomerang", null, Dice.d4, Dice.d8, 20, 603);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newGlaive(int depth, boolean addToWorld) {
		Item item = new TwoHandedFinesseWeapon(')', AsciiPanel.brightWhite, "Glaive", null, Dice.d10, 20, 604);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFlamberge(int depth, boolean addToWorld) {
		Item item = new VersatileFinesseWeapon(')', AsciiPanel.brightWhite, "Flamberge", null, Dice.d8, Dice.d10, 20, 605);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPairedBlades(int depth, boolean addToWorld) {
		Item item = new TwoHandedFinesseWeapon(')', AsciiPanel.brightWhite, "Paired Blades", null, Dice.d6x2, 20, 606);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Ranged Weapons
	public Item newShortbow(int depth, boolean addToWorld) {
		Item item = new ArrowsRangedWeapon('}', AsciiPanel.brightWhite, "Shortbow", null, Dice.d6, 250, 700);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLongbow(int depth, boolean addToWorld) {
		Item item = new ArrowsRangedWeapon('}', AsciiPanel.brightWhite, "Longbow", null, Dice.d8, 500, 701);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLightCrossbow(int depth, boolean addToWorld) {
		Item item = new BoltsRangedWeapon('}', AsciiPanel.brightWhite, "Light Crossbow", null, Dice.d8, 250, 702);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHeavyCrossbow(int depth, boolean addToWorld) {
		Item item = new BoltsRangedWeapon('}', AsciiPanel.brightWhite, "Heavy Crossbow", null, Dice.d10, 500, 703);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHandCrossbow(int depth, boolean addToWorld) {
		Item item = new BoltsRangedWeapon('}', AsciiPanel.brightWhite, "Hand Crossbow", null, Dice.d4x2, 500, 704);
		item.setIsTwoHanded(false);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPistol(int depth, boolean addToWorld) {
		Item item = new PowderRangedWeapon('}', AsciiPanel.brightWhite, "Flintlock Pistol", null, Dice.d10, 750, 705);
		item.setIsTwoHanded(false);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newCaliver(int depth, boolean addToWorld) {
		Item item = new PowderRangedWeapon('}', AsciiPanel.brightWhite, "Flintlock Caliver", null, Dice.d12, 1000, 706);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Armor
	public Item newPaddedClothArmor(int depth, boolean addToWorld) {
		Item item = new LightArmor((char)203, AsciiPanel.brightWhite, "Padded Cloth Armor", null, 11, 50, 800);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLeatherArmor(int depth, boolean addToWorld) {
		Item item = new LightArmor((char)203, AsciiPanel.brightWhite, "Leather Armor", null, 11, 100, 801);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newStuddedLeatherArmor(int depth, boolean addToWorld) {
		Item item = new LightArmor((char)203, AsciiPanel.brightWhite, "Studded Leather Armor", null, 12, 450, 802);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHideArmor(int depth, boolean addToWorld) {
		Item item = new MediumArmor((char)203, AsciiPanel.brightWhite, "Hide Armor", null, 12, 100, 803);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newChainmailArmor(int depth, boolean addToWorld) {
		Item item = new MediumArmor((char)203, AsciiPanel.brightWhite, "Chainmail Tunic", null, 13, 500, 804);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newScaleArmor(int depth, boolean addToWorld) {
		Item item = new MediumArmor((char)203, AsciiPanel.brightWhite, "Scale Mail", null, 14, 500, 805);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newBreastplate(int depth, boolean addToWorld) {
		Item item = new MediumArmor((char)203, AsciiPanel.brightWhite, "Breastplate", null, 14, 800, 806);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHalfPlate(int depth, boolean addToWorld) {
		Item item = new MediumArmor((char)203, AsciiPanel.brightWhite, "Half-Plate Armor", null, 15, 1500, 807);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPlateArmor(int depth, boolean addToWorld) {
		Item item = new HeavyArmor((char)203, AsciiPanel.brightWhite, "Plate Armor", null, 18, 3000, 808);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newRingMail(int depth, boolean addToWorld) {
		Item item = new HeavyArmor((char)203, AsciiPanel.brightWhite, "Ringmail Armor", null, 14, 300, 809);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newChainArmor(int depth, boolean addToWorld) {
		Item item = new HeavyArmor((char)203, AsciiPanel.brightWhite, "Chainmail Armor", null, 16, 750, 810);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newSplintArmor(int depth, boolean addToWorld) {
		Item item = new HeavyArmor((char)203, AsciiPanel.brightWhite, "Splint Armor", null, 17, 1200, 811);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Shields
	public Item newRoundShield(int depth, boolean addToWorld) {
		Item item = new BasicShield((char)232, AsciiPanel.brightWhite, "Round Shield", null, 2, 100, 900);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newKiteShield(int depth, boolean addToWorld) {
		Item item = new BasicShield((char)232, AsciiPanel.brightWhite, "Kite Shield", null, 3, 200, 901);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newTowerShield(int depth, boolean addToWorld) {
		Item item = new TowerShield((char)232, AsciiPanel.brightWhite, "Tower Shield", null, 4, 400, 902);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Rings
	public Item newStrengthRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Strength", 0, 250, 1000);
		item.modifyStrength(2);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newDexterityRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Dexterity", 1, 250, 1001);
		item.modifyDexterity(2);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newIntelligenceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Intelligence", 2, 250, 1002);
		item.modifyIntelligence(2);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newArmorRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Shielding", 3, 250, 1003);
		item.modifyArmorClass(2);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFireResistanceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Fire Resistance", 4, 250, 1004);
		item.setResistsFireDamage(true);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFrostResistanceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Frost Resistance", 5, 250, 1005);
		item.setResistsFrostDamage(true);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newShockResistanceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Shock Resistance", 6, 250, 1006);
		item.setResistsShockDamage(true);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPoisonResistanceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Poison Resistance", 7, 250, 1007);
		item.setResistsPoisonDamage(true);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newAcidResistanceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Acid Resistance", 8, 250, 1008);
		item.setResistsAcidDamage(true);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newMagicResistanceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Magic Resistance", 9, 250, 1009);
		item.setResistsMagicDamage(true);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newChaosResistanceRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Chaos Resistance", 10, 250, 1010);
		item.setResistsChaosDamage(true);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newVisionRing(int depth, boolean addToWorld) {
		Item item = new Ring(this.objectFactory, (char)9, "Ring of Sight", 11, 250, 1011);
		item.modifyVisionRadius(4);
		if(addToWorld) { 
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Potions
	public Item newPotionOfHealing(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Healing", 0, "Healing", objectFactory.effectFactory.maxHealth(), false, 100, 1100);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfMana(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Mana", 1, "Mana Restoration", objectFactory.effectFactory.maxMana(), false, 100, 1102);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfPoison(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Poison", 2, "Poison", objectFactory.effectFactory.poisoned(3+depth+Dice.d6.roll()), true, 100, 1103);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfGiantStrength(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Giant Strength", 3, "Giant Strength", objectFactory.effectFactory.giantStrength(10+depth+Dice.d4.roll()), false, 100, 1104);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfInvisibility(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Invisibility", 4, "Invisibility", objectFactory.effectFactory.invisible(10+depth+Dice.d4.roll()), false, 100, 1105);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfParalysis(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Paralysis", 5, "Paralysis", objectFactory.effectFactory.paralysed(3+depth+Dice.d6.roll()), true, 100, 1106);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfCausticGas(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Caustic Gas", 6, "Caustic Cloud", objectFactory.effectFactory.causticVapor(3+depth+Dice.d6.roll()), true, 100, 1107);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfRestoration(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Restoration", 7, "Restoration", objectFactory.effectFactory.restoration(), false, 100, 1108);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfMindVision(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Mind Vision", 8, "Mind Vision", objectFactory.effectFactory.mindVision(10+depth+Dice.d4.roll()), false, 100, 1109);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfOvergrowth(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Overgrowth", 9, "Overgrowth", objectFactory.effectFactory.overgrow(3+depth+Dice.d6.roll()), true, 100, 1110);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfCombustion(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Combustion", 10, "Combustion Cloud", objectFactory.effectFactory.fireball(3+depth+Dice.d6.roll()), true, 100, 1111);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfLevitation(int depth, boolean addToWorld) {
		Item item = new Potion(this.objectFactory, (char)13, "Potion of Levitation", 11, "Levitation", objectFactory.effectFactory.levitating(10+depth+Dice.d4.roll()), false, 100, 1112);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Scrolls
	public Item newScrollOfMagicMapping(int depth, Creature player, boolean addToWorld) {
		Item item = new Scroll(this.objectFactory, (char)247, "Scroll of Magic Mapping", 0, objectFactory.spellFactory.magicMappingScroll(player), 150, 1200);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfIdentify(int depth, Creature player, boolean addToWorld) {
		Item item = new Scroll(this.objectFactory, (char)247, "Scroll of Identify", 1, objectFactory.spellFactory.identifyScroll(player), 150, 1201);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfSummonMonsters(int depth, Creature player, boolean addToWorld) {
		Item item = new Scroll(this.objectFactory, (char)247, "Scroll of Summon Monsters", 2, objectFactory.spellFactory.summonMonstersScroll(player), 150, 1202);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfUpgrade(int depth, Creature player, boolean addToWorld) {
		Item item = new Scroll(this.objectFactory, (char)247, "Scroll of Upgrade", 3, objectFactory.spellFactory.upgradeScroll(player), 150, 1203);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfRemoveCurse(int depth, Creature player, boolean addToWorld) {
		Item item = new Scroll(this.objectFactory, (char)247, "Scroll of Remove Curse", 4, objectFactory.spellFactory.removeCurseScroll(player), 150, 1204);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfEnchantment(int depth, Creature player, boolean addToWorld) {
		Item item = new Scroll(this.objectFactory, (char)247, "Scroll of Enchantment", 5, objectFactory.spellFactory.magicMappingScroll(player), 150, 1205);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfConfusion(int depth, Creature player, boolean addToWorld) {
		Item item = new Scroll(this.objectFactory, (char)247, "Scroll of Confusion", 0, objectFactory.spellFactory.confuseScroll(player), 150, 1206);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Wands
	//Evocation Wands
	public Item newMagicMissileWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Magic Missile", 1, objectFactory.spellFactory.magicMissile(player), 250, 1300);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newForceBlastWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Force Blast", 2, objectFactory.spellFactory.forceBlast(player), 250, 1301);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newArchmagesAegisWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Archmage's Aegis", 3, objectFactory.spellFactory.archmagesAegis(player), 250, 1302);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFindTrapsWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Find Traps", 4, objectFactory.spellFactory.findTraps(player), 250, 1303);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Pyromancy Wands
	public Item newFireboltWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Firebolt", 5, objectFactory.spellFactory.firebolt(player), 250, 1400);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newBrazierBarrierWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Brazier Barrier", 6, objectFactory.spellFactory.brazierBarrier(player), 250, 1401);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPyrotechnicsWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Pyrotechnics", 7, objectFactory.spellFactory.pyrotechnics(player), 250, 1402);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newFlashfireWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Flashfire", 8, objectFactory.spellFactory.flashfire(player), 250, 1403);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Cryomancy Wands
	public Item newFlashFreezeWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Flash Freeze", 9, objectFactory.spellFactory.flashFreeze(player), 250, 1500);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newIceWallWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Ice Wall", 10, objectFactory.spellFactory.iceWall(player), 250, 1501);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newIceKnifeWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Ice Knife", 11, objectFactory.spellFactory.iceKnife(player), 250, 1502);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newGlaciateWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Glaciate", 12, objectFactory.spellFactory.glaciate(player), 250, 1503);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Electromancy Wands
	public Item newChainLightningWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Chain Lightning", 13, objectFactory.spellFactory.chainLightning(player), 250, 1600);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLightningLanceWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Lightning Lance", 14, objectFactory.spellFactory.lightningLance(player), 250, 1601);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newStaticSurgeWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Static Surge", 15, objectFactory.spellFactory.staticSurge(player), 250, 1602);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHasteWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Haste", 16, objectFactory.spellFactory.haste(player), 250, 1603);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Alchemancy Wands
	public Item newAcidBlastWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Acid Blast", 17, objectFactory.spellFactory.acidBlast(player), 250, 1700);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newToxicTransfusionWand(int depth, Creature player, boolean addToWorld) {
		Item item = new Wand(this.objectFactory, (char)33, "Wand of Toxic Transfusion", 18, objectFactory.spellFactory.toxicTransfusion(player), 250, 1701);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	
	
	
	//traps
	public Item newFireTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.orange, "Fire Trap", "Fire Trap");
		item.setIsTrap(true);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(objectFactory.effectFactory.fireball(3+depth+Dice.d6.roll()));
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newFrostTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.water, "Frostbite Trap", "Frostbite Trap");
		item.setIsTrap(true);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(objectFactory.effectFactory.frozen(3+depth+Dice.d6.roll()));
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newShockTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.brightCyan, "Lightning Trap", "Lightning Trap");
		item.setIsTrap(true);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(objectFactory.effectFactory.electrified(3+depth+Dice.d6.roll()));
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newBlinkTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.pink, "Blink Trap", "Blink Trap");
		item.setIsTrap(true);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(objectFactory.effectFactory.blink());
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newSummonTrap(int depth, int addToWorld, Creature player) {
		Item item = new Item('#', ExtraColors.brightWhite, "Summoning Trap", "Summoning Trap");
		item.setIsTrap(true);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(objectFactory.effectFactory.summonMonstersScroll(player));
		if(addToWorld > 0) {
			objectFactory.world.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
