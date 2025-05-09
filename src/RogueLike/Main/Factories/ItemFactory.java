package RogueLike.Main.Factories;

import java.io.Serializable;

import RogueLike.Main.Dice;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.DamageType;
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
public class ItemFactory implements Serializable {
	
	private static final long serialVersionUID = 8562110641811695913L;

	public static final int VICTORY_ITEM_ID = 200;
	
	public ItemFactory() {
	}


	//Rock
	public Item newRock(int depth, int addToWorld) {
		Item rock = new Item(',', ExtendedAsciiPanel.yellow, "Rock", "Rock");
		rock.setIsStackable(true);
		rock.setID(0);
		if(addToWorld > 0) {
			World.addAtEmptyLocation(rock, depth);
		}
		return rock;
	}
	
	//Food
	public Item newRations(int depth, int addToWorld) {
		Item item = new Item((char)233, ExtendedAsciiPanel.paper, "Ration of Food", "Ration of Food");
		item.modifyFoodValue(300);
		item.setIsStackable(true);
		item.setBaseGoldValue(5);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(1);
		if(addToWorld > 0) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPasty(int depth, int addToWorld) {
		Item item = new Item((char)233, ExtendedAsciiPanel.smoke, "Dwarven Pasty", "Dwarven Pasty");
		item.modifyFoodValue(600);
		item.setIsStackable(true);
		item.setBaseGoldValue(15);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(2);
		if(addToWorld > 0) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Gold
	public Item newGold(int depth, boolean addToWorld) {
		Item item = new Item((char)155, ExtendedAsciiPanel.brightYellow, "Gold", "Gold");
		item.setIsGold(true);
		item.setBaseGoldValue(ExtraMaths.diceRoll(ExtraMaths.d12(), ExtraMaths.d100()+12)*(depth+1));
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(3);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Keys
	public Item newIronKey(int depth, boolean addToWorld) {
		Item key = new IronKey((char)157, ExtendedAsciiPanel.white, "Iron Key", null, depth, 100);
		if(addToWorld) {
			World.addAtEmptyLocation(key, depth);
		}
		return key;
	}
	
	//Victory Item
	public Item newVictoryItem(int depth, int addToWorld) {
		Item item = new Item('*', ExtendedAsciiPanel.brightWhite, "Eitak's Ancient Axe", "Eitak's Ancient Axe");
		item.setID(VICTORY_ITEM_ID);
		if(addToWorld > 0) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Ammunition
	public Item newArrows(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtendedAsciiPanel.white, "Arrow", "Arrow");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsArrowAmmunition(true);
		item.setIsEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d12());
		item.setBaseGoldValue(1);
		item.setID(300);
		if(addToWorld > 0) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newBolts(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtendedAsciiPanel.white, "Crossbow Bolt", "Crossbow Bolt");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsBoltAmmunition(true);
		item.setIsEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d8());
		item.setBaseGoldValue(3);
		item.setID(301);
		if(addToWorld > 0) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPowder(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtendedAsciiPanel.white, "Flintlock Bullet", "Flintlock Bullet");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsPowderAmmunition(true);
		item.setIsEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d6());
		item.setBaseGoldValue(5);
		item.setID(302);
		if(addToWorld > 0) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Simple Weapons
	public Item newClub(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Club", null, Dice.d4, 25, 400);
		item.setIsSimple(true);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newDart(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicThrownWeapon(')', ExtendedAsciiPanel.brightWhite, "Dart", null, Dice.d1, Dice.d4, 25, 401);
		item.setIsSimple(true);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newQuarterstaff(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new VersatileMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Quarterstaff", null, Dice.d6, Dice.d8, 75, 402);
		item.setIsSimple(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(12);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newSpear(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new ThrownVersatileWeapon(')', ExtendedAsciiPanel.brightWhite, "Spear", null, Dice.d4, Dice.d6, Dice.d8, 75, 403);
		item.setIsSimple(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(12);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newGreatclub(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new TwoHandedMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Greatclub", null, Dice.d10, 125, 404);
		item.setIsSimple(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newThrowingAxe(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicThrownWeapon(')', ExtendedAsciiPanel.brightWhite, "Throwing Axe", null, Dice.d6, Dice.d8, 125, 405);
		item.setIsSimple(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newMattock(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new VersatileMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Mattock", null, Dice.d8, Dice.d10, 250, 406);
		item.setIsSimple(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(16);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newMaul(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new TwoHandedMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Maul", null, Dice.d12, 500, 407);
		item.setIsSimple(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(18);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Martial Weapons
	public Item newShortsword(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Shortsword", null, Dice.d6, 25, 500);
		item.setIsMartial(true);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newKnuckleduster(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Knuckleduster", null, Dice.d4, 25, 501);
		item.setIsMartial(true);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newMace(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new VersatileMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Mace", null, Dice.d6, Dice.d8, 75, 502);
		item.setIsMartial(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(12);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newThrowingHammer(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicThrownWeapon(')', ExtendedAsciiPanel.brightWhite, "Throwing Hammer", null, Dice.d4, Dice.d6, 75, 503);
		item.setIsMartial(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(12);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLongsword(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Longsword", null, Dice.d8, 125, 504);
		item.setIsMartial(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHalberd(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new TwoHandedMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Halberd", null, Dice.d10, 125, 505);
		item.setIsMartial(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newWarhammer(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new VersatileMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Warhammer", null, Dice.d8, Dice.d10, 125, 506);
		item.setIsMartial(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newJavelin(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicThrownWeapon(')', ExtendedAsciiPanel.brightWhite, "Javelin", null, Dice.d6, Dice.d10, 250, 507);
		item.setIsMartial(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(16);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newGreatsword(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new TwoHandedMeleeWeapon(')', ExtendedAsciiPanel.brightWhite, "Greatsword", null, Dice.d12, 500, 508);
		item.setIsMartial(true);
		item.setUsesStrength(true);
		item.setStrengthRequirement(18);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Finesse Weapons
	public Item newDagger(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new ThrownFinesseWeapon(')', ExtendedAsciiPanel.brightWhite, "Dagger", null, Dice.d4, Dice.d4, 25, 600);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newRapier(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicFinesseWeapon(')', ExtendedAsciiPanel.brightWhite, "Rapier", null, Dice.d6, 25, 601);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFalchion(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new VersatileFinesseWeapon(')', ExtendedAsciiPanel.brightWhite, "Falchion", null, Dice.d6, Dice.d8, 75, 602);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(12);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newBoomerang(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new ThrownFinesseWeapon(')', ExtendedAsciiPanel.brightWhite, "Boomerang", null, Dice.d4, Dice.d8, 125, 603);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newGlaive(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new TwoHandedFinesseWeapon(')', ExtendedAsciiPanel.brightWhite, "Glaive", null, Dice.d10, 125, 604);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFlamberge(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new VersatileFinesseWeapon(')', ExtendedAsciiPanel.brightWhite, "Flamberge", null, Dice.d8, Dice.d10, 250, 605);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(16);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPairedBlades(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new TwoHandedFinesseWeapon(')', ExtendedAsciiPanel.brightWhite, "Paired Blades", null, Dice.d6x2, 500, 606);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(18);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Ranged Weapons
	public Item newShortbow(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new ArrowsRangedWeapon('}', ExtendedAsciiPanel.brightWhite, "Shortbow", null, Dice.d6, 25, 700);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLongbow(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new ArrowsRangedWeapon('}', ExtendedAsciiPanel.brightWhite, "Longbow", null, Dice.d8, 75, 701);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(12);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLightCrossbow(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BoltsRangedWeapon('}', ExtendedAsciiPanel.brightWhite, "Light Crossbow", null, Dice.d8, 75, 702);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(12);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHeavyCrossbow(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BoltsRangedWeapon('}', ExtendedAsciiPanel.brightWhite, "Heavy Crossbow", null, Dice.d10, 125, 703);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(14);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHandCrossbow(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BoltsRangedWeapon('}', ExtendedAsciiPanel.brightWhite, "Hand Crossbow", null, Dice.d4x2, 250, 704);
		item.setIsTwoHanded(false);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(16);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPistol(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new PowderRangedWeapon('}', ExtendedAsciiPanel.brightWhite, "Flintlock Pistol", null, Dice.d10, 250, 705);
		item.setIsTwoHanded(false);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(16);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newCaliver(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new PowderRangedWeapon('}', ExtendedAsciiPanel.brightWhite, "Flintlock Caliver", null, Dice.d12, 500, 706);
		item.setUsesDexterity(true);
		item.setDexterityRequirement(18);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomWeaponEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Armor
	public Item newPaddedClothArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new LightArmor((char)203, ExtendedAsciiPanel.brightWhite, "Padded Cloth Armor", null, 11, 25, 800);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLeatherArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new LightArmor((char)203, ExtendedAsciiPanel.brightWhite, "Leather Armor", null, 11, 25, 801);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newStuddedLeatherArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new LightArmor((char)203, ExtendedAsciiPanel.brightWhite, "Studded Leather Armor", null, 12, 75, 802);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHideArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new MediumArmor((char)203, ExtendedAsciiPanel.brightWhite, "Hide Armor", null, 12, 75, 803);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newChainmailArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new MediumArmor((char)203, ExtendedAsciiPanel.brightWhite, "Chainmail Tunic", null, 13, 125, 804);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newScaleArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new MediumArmor((char)203, ExtendedAsciiPanel.brightWhite, "Scale Mail", null, 14, 125, 805);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newBreastplate(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new MediumArmor((char)203, ExtendedAsciiPanel.brightWhite, "Breastplate", null, 14, 125, 806);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHalfPlate(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new MediumArmor((char)203, ExtendedAsciiPanel.brightWhite, "Half-Plate Armor", null, 15, 250, 807);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPlateArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new HeavyArmor((char)203, ExtendedAsciiPanel.brightWhite, "Plate Armor", null, 18, 750, 808);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newRingMail(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new HeavyArmor((char)203, ExtendedAsciiPanel.brightWhite, "Ringmail Armor", null, 14, 125, 809);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newChainArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new HeavyArmor((char)203, ExtendedAsciiPanel.brightWhite, "Chainmail Armor", null, 16, 500, 810);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newSplintArmor(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new HeavyArmor((char)203, ExtendedAsciiPanel.brightWhite, "Splint Armor", null, 17, 750, 811);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Shields
	public Item newRoundShield(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicShield((char)232, ExtendedAsciiPanel.brightWhite, "Round Shield", null, 2, 75, 900);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newKiteShield(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new BasicShield((char)232, ExtendedAsciiPanel.brightWhite, "Kite Shield", null, 3, 250, 901);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newTowerShield(int depth, boolean addToWorld, boolean canUpgrade, boolean canEnchant, boolean canCurse) {
		Item item = new TowerShield((char)232, ExtendedAsciiPanel.brightWhite, "Tower Shield", null, 4, 500, 902);
		//
		if(canUpgrade) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().upgradeItem(item, Dice.d4.roll());
			}
		}
		if(canEnchant) {
			if(Dice.d100.roll() >= 75) {
				FactoryManager.getObjectFactory().enchantItem(item, FactoryManager.getEnchantmentFactory().randomArmorEnchantment(item));
			}
		}
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Rings
	public Item newStrengthRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Strength", 0, 250, 1000);
		item.modifyStrength(2);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newDexterityRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Dexterity", 1, 250, 1001);
		item.modifyDexterity(2);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newIntelligenceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Intelligence", 2, 250, 1002);
		item.modifyIntelligence(2);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newArmorRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Shielding", 3, 250, 1003);
		item.modifyArmorClass(2);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFireResistanceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Fire Resistance", 4, 250, 1004);
		item.addResistance(DamageType.FIRE);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFrostResistanceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Frost Resistance", 5, 250, 1005);
		item.addResistance(DamageType.FROST);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newShockResistanceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Shock Resistance", 6, 250, 1006);
		item.addResistance(DamageType.SHOCK);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newPoisonResistanceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Poison Resistance", 7, 250, 1007);
		item.addResistance(DamageType.POISON);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newAcidResistanceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Acid Resistance", 8, 250, 1008);
		item.addResistance(DamageType.ACID);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newMagicResistanceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Magic Resistance", 9, 250, 1009);
		item.addResistance(DamageType.MAGIC);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newChaosResistanceRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Chaos Resistance", 10, 250, 1010);
		item.addResistance(DamageType.CHAOS);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newVisionRing(int depth, boolean addToWorld, boolean canCurse) {
		Item item = new Ring( (char)9, "Ring of Sight", 11, 250, 1011);
		item.modifyVisionRadius(4);
		//
		if(canCurse) {
			if(Dice.d100.roll() >= 60) {
				FactoryManager.getObjectFactory().curseItem(item);
			}
		}
		//
		if(addToWorld) { 
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Potions
	public Item newPotionOfHealing(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Healing", 0, "Healing", FactoryManager.getEffectFactory().maxHealth(), false, 100, 1100);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfMana(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Mana", 1, "Mana Restoration", FactoryManager.getEffectFactory().maxMana(), false, 100, 1102);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfPoison(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Poison", 2, "Poison", FactoryManager.getEffectFactory().poisoned(3+depth+Dice.d6.roll()), true, 100, 1103);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfGiantStrength(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Giant Strength", 3, "Giant Strength", FactoryManager.getEffectFactory().giantStrength(10+depth+Dice.d4.roll()), false, 100, 1104);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfInvisibility(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Invisibility", 4, "Invisibility", FactoryManager.getEffectFactory().invisible(10+depth+Dice.d4.roll()), false, 100, 1105);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfParalysis(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Paralysis", 5, "Paralysis", FactoryManager.getEffectFactory().paralysed(3+depth+Dice.d6.roll()), true, 100, 1106);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfCausticGas(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Caustic Gas", 6, "Caustic Cloud", FactoryManager.getEffectFactory().causticVapor(3+depth+Dice.d6.roll()), true, 100, 1107);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfRestoration(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Restoration", 7, "Restoration", FactoryManager.getEffectFactory().restoration(), false, 100, 1108);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfMindVision(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Mind Vision", 8, "Mind Vision", FactoryManager.getEffectFactory().mindVision(10+depth+Dice.d4.roll()), false, 100, 1109);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfOvergrowth(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Overgrowth", 9, "Overgrowth", FactoryManager.getEffectFactory().overgrow(3+depth+Dice.d6.roll()), true, 100, 1110);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfCombustion(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Combustion", 10, "Combustion Cloud", FactoryManager.getEffectFactory().fireball(3+depth+Dice.d6.roll()), true, 100, 1111);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPotionOfLevitation(int depth, boolean addToWorld) {
		Item item = new Potion( (char)13, "Potion of Levitation", 11, "Levitation", FactoryManager.getEffectFactory().levitating(10+depth+Dice.d4.roll()), false, 100, 1112);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Scrolls
	public Item newScrollOfMagicMapping(int depth, Creature reference, boolean addToWorld) {
		Item item = new Scroll( (char)247, "Scroll of Magic Mapping", 0, FactoryManager.getSpellFactory().magicMappingScroll(reference), 150, 1200);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfIdentify(int depth, Creature reference, boolean addToWorld) {
		Item item = new Scroll( (char)247, "Scroll of Identify", 1, FactoryManager.getSpellFactory().identifyScroll(reference), 150, 1201);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfSummonMonsters(int depth, Creature reference, boolean addToWorld) {
		Item item = new Scroll( (char)247, "Scroll of Summon Monsters", 2, FactoryManager.getSpellFactory().summonMonstersScroll(reference), 150, 1202);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfUpgrade(int depth, Creature reference, boolean addToWorld) {
		Item item = new Scroll( (char)247, "Scroll of Upgrade", 3, FactoryManager.getSpellFactory().upgradeScroll(reference), 150, 1203);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfRemoveCurse(int depth, Creature reference, boolean addToWorld) {
		Item item = new Scroll( (char)247, "Scroll of Remove Curse", 4, FactoryManager.getSpellFactory().removeCurseScroll(reference), 150, 1204);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfEnchantment(int depth, Creature reference, boolean addToWorld) {
		Item item = new Scroll( (char)247, "Scroll of Enchantment", 5, FactoryManager.getSpellFactory().magicMappingScroll(reference), 150, 1205);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newScrollOfConfusion(int depth, Creature reference, boolean addToWorld) {
		Item item = new Scroll( (char)247, "Scroll of Confusion", 6, FactoryManager.getSpellFactory().confuseScroll(reference), 150, 1206);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Wands
	//Evocation Wands
	public Item newMagicMissileWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Magic Missile", 1, FactoryManager.getSpellFactory().magicMissile(reference), 500, 1300);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newForceBlastWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Force Blast", 2, FactoryManager.getSpellFactory().forceBlast(reference), 500, 1301);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newArchmagesAegisWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Archmage's Aegis", 3, FactoryManager.getSpellFactory().archmagesAegis(reference), 500, 1302);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newFindTrapsWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Find Traps", 4, FactoryManager.getSpellFactory().findTraps(reference), 500, 1303);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Pyromancy Wands
	public Item newFireboltWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Firebolt", 5, FactoryManager.getSpellFactory().firebolt(reference), 500, 1400);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newBrazierBarrierWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Brazier Barrier", 6, FactoryManager.getSpellFactory().brazierBarrier(reference), 500, 1401);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newPyrotechnicsWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Pyrotechnics", 7, FactoryManager.getSpellFactory().pyrotechnics(reference), 500, 1402);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	public Item newFlashfireWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Flashfire", 8, FactoryManager.getSpellFactory().flashfire(reference), 500, 1403);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}

	public Item newDragonsBreathWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Dragon's Breath", 9, FactoryManager.getSpellFactory().dragonsBreath(reference), 500, 1404);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Cryomancy Wands
	public Item newFlashFreezeWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Flash Freeze", 10, FactoryManager.getSpellFactory().flashFreeze(reference), 500, 1500);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newIceWallWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Ice Wall", 11, FactoryManager.getSpellFactory().iceWall(reference), 500, 1501);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newIceKnifeWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Ice Knife", 12, FactoryManager.getSpellFactory().iceKnife(reference), 500, 1502);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newGlaciateWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Glaciate", 13, FactoryManager.getSpellFactory().glaciate(reference), 500, 1503);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Electromancy Wands
	public Item newChainLightningWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Chain Lightning", 14, FactoryManager.getSpellFactory().chainLightning(reference), 500, 1600);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLightningLanceWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Lightning Lance", 15, FactoryManager.getSpellFactory().lightningLance(reference), 500, 1601);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newStaticSurgeWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Static Surge", 16, FactoryManager.getSpellFactory().staticSurge(reference), 500, 1602);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newHasteWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Haste", 17, FactoryManager.getSpellFactory().haste(reference), 500, 1603);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Alchemancy Wands
	public Item newAcidBlastWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Acid Blast", 18, FactoryManager.getSpellFactory().acidBlast(reference), 500, 1700);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newToxicTransfusionWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Toxic Transfusion", 19, FactoryManager.getSpellFactory().toxicTransfusion(reference), 500, 1701);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newRefluxBarrierWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Reflux Barrier", 20, FactoryManager.getSpellFactory().refluxBarrier(reference), 500, 1702);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newLifetapWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Lifetap", 21, FactoryManager.getSpellFactory().lifetap(reference), 500, 1703);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	//Ferromancy Wands
	public Item newArmorStormWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Armor Storm", 22, FactoryManager.getSpellFactory().armorStorm(reference), 500, 1800);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newBladsWardWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Blad's Ward", 23, FactoryManager.getSpellFactory().bladsWard(reference), 500, 1801);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newWeaponBoltWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Weapon Bolt", 24, FactoryManager.getSpellFactory().weaponBolt(reference), 500, 1802);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	public Item newInfuseUpgradeWand(int depth, Creature reference, boolean addToWorld) {
		Item item = new Wand( (char)33, "Wand of Infuse Upgrade", 25, FactoryManager.getSpellFactory().infuseUpgrade(reference), 500, 1803);
		if(addToWorld) {
			World.addAtEmptyLocation(item, depth);
		}
		return item;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
