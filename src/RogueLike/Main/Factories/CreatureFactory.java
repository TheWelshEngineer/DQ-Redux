package RogueLike.Main.Factories;

import java.io.Serializable;

import RogueLike.Main.Dice;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.FieldOfView;
import RogueLike.Main.World;
import RogueLike.Main.AI.ChestAI;
import RogueLike.Main.AI.PlayerAI;
import RogueLike.Main.Creatures.Bat;
import RogueLike.Main.Creatures.Cloaker;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Creatures.CreatureModifier;
import RogueLike.Main.Creatures.Merchant;
import RogueLike.Main.Creatures.Mimic;
import RogueLike.Main.Creatures.Ogre;
import RogueLike.Main.Creatures.Player;
import RogueLike.Main.Creatures.Constructs.AnimatedArmor;
import RogueLike.Main.Creatures.Constructs.AnimatedWeapon;
import RogueLike.Main.Creatures.Containers.BasicChest;
import RogueLike.Main.Creatures.Fungi.BloodFungus;
import RogueLike.Main.Creatures.Fungi.Fungus;
import RogueLike.Main.Creatures.Gremlins.Gremlin;
import RogueLike.Main.Creatures.Gremlins.GremlinAlchemist;
import RogueLike.Main.Creatures.Skeletons.Skeleton;
import RogueLike.Main.Creatures.Skeletons.SkeletonCryomancer;
import RogueLike.Main.Creatures.Skeletons.SkeletonElectromancer;
import RogueLike.Main.Creatures.Skeletons.SkeletonPyromancer;
import RogueLike.Main.Creatures.Slimes.MagmaSlime;
import RogueLike.Main.Creatures.Slimes.MagmaSlimeling;
import RogueLike.Main.Creatures.Slimes.MetalSlime;
import RogueLike.Main.Creatures.Slimes.MetalSlimeling;
import RogueLike.Main.Creatures.Slimes.PinkSlime;
import RogueLike.Main.Creatures.Slimes.PinkSlimeling;
import RogueLike.Main.Creatures.Slimes.ThundercloudSlime;
import RogueLike.Main.Creatures.Slimes.ThundercloudSlimeling;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Enums.PlayerAncestry;
import RogueLike.Main.Enums.PlayerClass;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Utils.NotificationHistory;
import RogueLike.Main.Utils.PlayerBuildDetails;

public class CreatureFactory implements Serializable {
	private static final long serialVersionUID = 987053286733457992L;
	
	public CreatureFactory() {
	}

	public Creature modifyCreatureDamageDealt(Creature creature, CreatureModifier modifier) {
		creature.setName(String.format("%s %s", modifier.prefix(), creature.name()));
		creature.setUnarmedDamageType(modifier.damageType());
		return creature;
	}
	
	public Creature modifyCreatureResistsDamage(Creature creature, CreatureModifier modifier) {
		creature.setName(String.format("%s %s", creature.name(), modifier.suffix()));

		creature.addResistanceTo(modifier.damageType());

		return creature;
	}
	
	//Player
	public Player newPlayer(FieldOfView fov, NotificationHistory notificationsHandle, PlayerBuildDetails details) {
		//World, name, glyph, color, maxHP 20, maxMana, base armorclass, strength, dexterity, intelligence, visionRadius, inventorySize) {
		Player player = new Player("Player", '@', ExtendedAsciiPanel.brightWhite, 20, 20, 10, 10, 10, 10, 8, 20);
		player.setID(0);
		new PlayerAI(player, notificationsHandle, fov);
		
		player.setPlayerName(details.name);
		player.setPlayerAncestry(details.ancestry);
		player.creatureTypes.add(details.ancestry.toString());
		
		player.setStrength(details.startingStats.get(0));
		player.setDexterity(details.startingStats.get(1));
		player.setIntelligence(details.startingStats.get(2));
		
		player.setSkills(details.startingSkills);

		//Max HP
		player.setMaxHP((player.baseStrength()*2)-5);

		//Max Mana
		if(details.ancestry == PlayerAncestry.ELF) {
			int amount = (int) Math.ceil((((player.baseIntelligence()*2)-5)*1.25));
			player.setMaxMana(amount);
		}else {
			player.setMaxMana((player.baseIntelligence()*2)-5);
		}
		
		//Ancestry Traits
		if(details.ancestry == PlayerAncestry.DWARF) {
			player.modifyBaseArmorClass(1);
			player.addResistanceTo(DamageType.POISON);
		}
		if(details.ancestry == PlayerAncestry.DRAGONBORN) {
			player.addResistanceTo(DamageType.FIRE);
		}
		
		//Class Equipment
		player.setPlayerClass(details.playerClass);
		switch (details.playerClass) {
			case WARRIOR:
				Item startWeaponWarrior = FactoryManager.getItemFactory().newShortsword(0, false, false, false, false);
				player.learnNameQuiet(startWeaponWarrior);
				player.inventory().add(startWeaponWarrior);
				player.equip(startWeaponWarrior, true);
				Item startArmorWarrior = FactoryManager.getItemFactory().newChainmailArmor(0, false, false, false, false);
				player.learnNameQuiet(startArmorWarrior);
				player.inventory().add(startArmorWarrior);
				player.equip(startArmorWarrior, true);
				Item startShieldWarrior = FactoryManager.getItemFactory().newRoundShield(0, false, false, false, false);
				player.learnNameQuiet(startShieldWarrior);
				player.inventory().add(startShieldWarrior);
				player.equip(startShieldWarrior, true);

				player.setHPScaleAmount(player.hpScaleHigh());
				player.setManaScaleAmount(player.manaScaleLow());
				break;

			case ROGUE:
				Item startWeaponRogue = FactoryManager.getItemFactory().newDagger(0, false, false, false, false);
				player.learnNameQuiet(startWeaponRogue);
				player.inventory().add(startWeaponRogue);
				player.equip(startWeaponRogue, true);
				player.equipToQuickslot(startWeaponRogue, 1);
				Item startArmorRogue = FactoryManager.getItemFactory().newPaddedClothArmor(0, false, false, false, false);
				player.learnNameQuiet(startArmorRogue);
				player.inventory().add(startArmorRogue);
				player.equip(startArmorRogue, true);
				Item startItemRogue = FactoryManager.getItemFactory().newPotionOfInvisibility(0, false);
				player.learnNameQuiet(startItemRogue);
				player.inventory().add(startItemRogue);

				player.setHPScaleAmount(player.hpScaleMedium());
				player.setManaScaleAmount(player.manaScaleMedium());
				break;

			case MAGE:
				Item startArmorWizard = FactoryManager.getItemFactory().newPaddedClothArmor(0, false, false, false, false);
				player.learnNameQuiet(startArmorWizard);
				player.inventory().add(startArmorWizard);
				player.equip(startArmorWizard, true);
				Item startWandMage = FactoryManager.getItemFactory().newMagicMissileWand(0, player, false);
				player.learnNameQuiet(startWandMage);
				player.inventory().add(startWandMage);
				player.equipToQuickslot(startWandMage, 1);
				startWandMage = FactoryManager.getItemFactory().newFireboltWand(0, player, false);
				player.learnNameQuiet(startWandMage);
				player.inventory().add(startWandMage);
				player.equipToQuickslot(startWandMage, 1);

				player.setHPScaleAmount(player.hpScaleLow());
				player.setManaScaleAmount(player.manaScaleHigh());
				break;

			case RANGER:
				Item startBowRanger = FactoryManager.getItemFactory().newShortbow(0, false, false, false, false);
				player.learnNameQuiet(startBowRanger);
				player.inventory().add(startBowRanger);
				player.equip(startBowRanger, true);
				Item startWeaponRanger = FactoryManager.getItemFactory().newClub(0, false, false, false, false);
				player.learnNameQuiet(startWeaponRanger);
				player.inventory().add(startWeaponRanger);
				Item startArmorRanger = FactoryManager.getItemFactory().newPaddedClothArmor(0, false, false, false, false);
				player.learnNameQuiet(startArmorRanger);
				player.inventory().add(startArmorRanger);
				player.equip(startArmorRanger, true);
				Item startItemRanger = FactoryManager.getItemFactory().newArrows(0, 0);
				startItemRanger.setStackAmount(20);
				player.learnNameQuiet(startItemRanger);
				player.inventory().add(startItemRanger);
				player.equip(startItemRanger, true);

				player.setHPScaleAmount(player.hpScaleMedium());
				player.setManaScaleAmount(player.manaScaleMedium());
				break;
				
			case WITCH:
				Item startWeaponWitch = FactoryManager.getItemFactory().newDagger(0, false, false, false, false);
				player.learnNameQuiet(startWeaponWitch);
				player.inventory().add(startWeaponWitch);
				player.equip(startWeaponWitch, true);
				player.equipToQuickslot(startWeaponWitch, 1);
				Item startArmorWitch = FactoryManager.getItemFactory().newPaddedClothArmor(0, false, false, false, false);
				player.learnNameQuiet(startArmorWitch);
				player.inventory().add(startArmorWitch);
				player.equip(startArmorWitch, true);
				Item startWandWitch_2 = FactoryManager.getItemFactory().newAcidBlastWand(0, player, false);
				player.learnNameQuiet(startWandWitch_2);
				player.inventory().add(startWandWitch_2);
				player.equipToQuickslot(startWandWitch_2, 2);

				player.setHPScaleAmount(player.hpScaleLow());
				player.setManaScaleAmount(player.manaScaleHigh());
				break;
				
			case PALADIN:
				Item startWeaponPaladin = FactoryManager.getItemFactory().newShortsword(0, false, false, false, false);
				player.learnNameQuiet(startWeaponPaladin);
				player.inventory().add(startWeaponPaladin);
				player.equip(startWeaponPaladin, true);
				Item startArmorPaladin = FactoryManager.getItemFactory().newChainmailArmor(0, false, false, false, false);
				player.learnNameQuiet(startArmorPaladin);
				player.inventory().add(startArmorPaladin);
				player.equip(startArmorPaladin, true);
				Item startWandPaladin = FactoryManager.getItemFactory().newBladsWardWand(0, player, false);
				player.learnNameQuiet(startWandPaladin);
				player.inventory().add(startWandPaladin);
				player.equipToQuickslot(startWandPaladin, 1);

				player.setHPScaleAmount(player.hpScaleHigh());
				player.setManaScaleAmount(player.manaScaleLow());
				break;
				
			case MONK:
				Item startWeaponMonk = FactoryManager.getItemFactory().newClub(0, false, false, false, false);
				player.learnNameQuiet(startWeaponMonk);
				player.inventory().add(startWeaponMonk);
				player.equip(startWeaponMonk, true);
				Item startArmorMonk = FactoryManager.getItemFactory().newPaddedClothArmor(0, false, false, false, false);
				player.learnNameQuiet(startArmorMonk);
				player.inventory().add(startArmorMonk);
				player.equip(startArmorMonk, true);
				Item startItemMonk = FactoryManager.getItemFactory().newPotionOfMindVision(0, false);
				player.learnNameQuiet(startItemMonk);
				player.inventory().add(startItemMonk);

				player.setHPScaleAmount(player.hpScaleMedium());
				player.setManaScaleAmount(player.manaScaleMedium());
				break;

			default:
				throw new IllegalArgumentException(details.playerClass.toString());
		}
	
		//Starting Food
		player.inventory().add(FactoryManager.getItemFactory().newRations(0, 0));

		//Ancestry Items
		if(details.ancestry == PlayerAncestry.DRAGONBORN) {
			Item startWandDragonborn = FactoryManager.getItemFactory().newDragonsBreathWand(0, player, false);
			player.learnNameQuiet(startWandDragonborn);
			player.inventory().add(startWandDragonborn);
			if(details.playerClass == PlayerClass.MAGE || details.playerClass == PlayerClass.PALADIN) {
				player.equipToQuickslot(startWandDragonborn, 2);
			}else if(details.playerClass == PlayerClass.WITCH) {
				player.equipToQuickslot(startWandDragonborn, 3);
			}else{
				player.equipToQuickslot(startWandDragonborn, 1);
			}
		}

		//Test Items
		//Item startWandTest = FactoryManager.getItemFactory().newScrollOfMagicMapping(0, player, false);
		//player.learnNameQuiet(startWandTest);
		//player.inventory().add(startWandTest);
		player.inventory().add(FactoryManager.getItemFactory().newDragonsBreathWand(0, player, false));
		player.inventory().add(FactoryManager.getItemFactory().newIceKnifeWand(0, player, false));
		//player.modifyGold(10000);
		
		//
		player.stackItems();
		return player;
	}
	
	//Fungi
	public Creature newFungus(int depth, boolean addToWorld) {
		Creature fungus = new Fungus( "Fungus", 'f', ExtendedAsciiPanel.green, 100, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(fungus, depth);
		}
		return fungus;
	}
	public Creature newBloodFungus(int depth, Creature player, boolean addToWorld) {
		Creature fungus = new BloodFungus( player, "Blood Fungus", 'f', ExtendedAsciiPanel.red, 101, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(fungus, depth);
		}
		return fungus;
	}
	
	//Bats
	public Creature newBat(int depth, boolean addToWorld) {
		Creature bat = new Bat( "Bat", 'b', ExtendedAsciiPanel.magenta, 200, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(bat, depth);
		}
		return bat;
		
	}
	
	//Skeletons
	public Creature newSkeleton(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new Skeleton( player, "Skeleton", 'k', ExtendedAsciiPanel.white, 300, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	public Creature newSkeletonCryomancer(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new SkeletonCryomancer( player, "Skeleton Cryomancer", 'k', ExtendedAsciiPanel.water, 301, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	public Creature newSkeletonPyromancer(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new SkeletonPyromancer( player, "Skeleton Pyromancer", 'k', ExtendedAsciiPanel.orange, 302, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	public Creature newSkeletonElectromancer(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new SkeletonElectromancer( player, "Skeleton Electromancer", 'k', ExtendedAsciiPanel.paralyzed, 303, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	//Slimes
	public Creature newPinkSlime(int depth, Creature player, boolean addToWorld) {
		Creature slime = new PinkSlime( player, "Pink Slime", 'S', ExtendedAsciiPanel.brightPink, 400, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slime, depth);
		}
		return slime;		
	}
	
	public Creature newMetalSlime(int depth, Creature player, boolean addToWorld) {
		Creature slime = new MetalSlime( player, "Metal Slime", 'S', ExtendedAsciiPanel.white, 401, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slime, depth);
		}
		return slime;		
	}
	
	public Creature newMagmaSlime(int depth, Creature player, boolean addToWorld) {
		Creature slime = new MagmaSlime( player, "Magma Slime", 'S', ExtendedAsciiPanel.orange, 402, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slime, depth);
		}
		return slime;	
	}
	
	public Creature newThundercloudSlime(int depth, Creature player, boolean addToWorld) {
		Creature slime = new ThundercloudSlime( player, "Thundercloud Slime", 'S', ExtendedAsciiPanel.paralyzed, 403, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slime, depth);
		}
		return slime;	
	}
	
	//Slimelings
	public Creature newPinkSlimeling(int depth, Creature player, boolean addToWorld) {
		Creature slimeling = new PinkSlimeling( player, "Pink Slimeling", 's', ExtendedAsciiPanel.brightPink, 500, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slimeling, depth);
		}
		return slimeling;
	}
	
	public Creature newMetalSlimeling(int depth, Creature player, boolean addToWorld) {
		Creature slimeling = new MetalSlimeling( player, "Metal Slimeling", 's', ExtendedAsciiPanel.white, 501, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slimeling, depth);
		}
		return slimeling;
	}
	
	public Creature newMagmaSlimeling(int depth, Creature player, boolean addToWorld) {
		Creature slimeling = new MagmaSlimeling( player, "Magma Slimeling", 's', ExtendedAsciiPanel.orange, 502, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slimeling, depth);
		}
		return slimeling;
	}
	
	public Creature newThundercloudSlimeling(int depth, Creature player, boolean addToWorld) {
		Creature slimeling = new ThundercloudSlimeling( player, "Thundercloud Slimeling", 's', ExtendedAsciiPanel.paralyzed, 503, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(slimeling, depth);
		}
		return slimeling;
	}
	
	//Ogres
	public Creature newOgre(int depth, Creature player, boolean addToWorld) {
		Creature ogre = new Ogre( player, "Ogre", 'O', ExtendedAsciiPanel.brightGreen, 600, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(ogre, depth);
		}
		return ogre;
	}
	
	//Gremlins
	public Creature newGremlin(int depth, Creature player, boolean addToWorld) {
		Creature gremlin = new Gremlin( player, "Gremlin", 'g', ExtendedAsciiPanel.cobalt, 700, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(gremlin, depth);
		}
		return gremlin;
	}
	
	public Creature newGremlinSkirmisher(int depth, Creature player, boolean addToWorld) {
		Creature gremlin = new Gremlin( player, "Gremlin Skirmisher", 'g', ExtendedAsciiPanel.red, 701, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(gremlin, depth);
		}
		return gremlin;
	}
	
	public Creature newGremlinAlchemist(int depth, Creature player, boolean addToWorld) {
		Creature alchemist = new GremlinAlchemist( player, "Gremlin Alchemist", 'g', ExtendedAsciiPanel.orange, 702, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(alchemist, depth);
		}
		return alchemist;
	}
	
	//Cloaker
	public Creature newCloaker(int depth, Creature player, boolean addToWorld) {
		Creature cloaker = new Cloaker( player, "Cloaker", 'c', ExtendedAsciiPanel.brightBlack, 800, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(cloaker, depth);
		}
		return cloaker;	
	}
	
	//Constructs
	public Creature newAnimatedArmor(int depth, Creature player, boolean addToWorld) {
		Creature animatedArmor = new AnimatedArmor( player, "Animated Armor", (char)203, ExtendedAsciiPanel.lilac, 900, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(animatedArmor, depth);
		}
		return animatedArmor;
	}
	
	public Creature newAnimatedWeapon(int depth, Creature player, boolean addToWorld) {
		Creature animatedWeapon = new AnimatedWeapon( player, "Animated Weapon", ')', ExtendedAsciiPanel.lilac, 901, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(animatedWeapon, depth);
		}
		return animatedWeapon;
	}
	
	//Mimic
	public Creature newMimic(int depth, Creature player, boolean addToWorld) {
		Creature mimic = new Mimic( player, "Mimic", (char)127, ExtendedAsciiPanel.apple, 1000, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(mimic, depth);
		}
		return mimic;
	}
	
	//Merchant
	public Creature newMerchant(int depth, Creature player, boolean addToWorld) {
		Creature merchant = new Merchant( player, "Merchant", (char)234, ExtendedAsciiPanel.lilac, 1100, depth);
		if(addToWorld) {
			World.addAtEmptyLocation(merchant, depth);
		}
		return merchant;
	}
	
	//Chest
	public Creature newChest(int depth, Creature player, boolean addToWorld) {
		Creature chest = new BasicChest( player, "Chest", (char)127, ExtendedAsciiPanel.brown, depth, Dice.d12.roll());
		if(addToWorld) {
			World.addAtEmptyLocation(chest, depth);
		}
		return chest;
	}
	
	//Spell Creatures
	public Creature newIceWall(int depth, Creature player, boolean addToWorld) {
		Creature icewall = new Creature("Ice Wall", (char)177, ExtendedAsciiPanel.water, 15, 10, 16, 16, 1, 1, 1, 20);
		icewall.setID(1100);
		new ChestAI(icewall);
		icewall.addImmunityTo(DamageType.FROST);
		icewall.setHasNoCorpse(true);
		icewall.setHasNoXP(true);
		icewall.creatureTypes.add("Elemental");
		if(addToWorld) {
			World.addAtEmptyLocation(icewall, depth);
		}
		return icewall;
	}
	
	//Technical Creatures
	public Creature newTileSpell(int depth, Creature player, boolean addToWorld) {
		Creature tileSpell = new Creature(" ", 'T', ExtendedAsciiPanel.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(tileSpell);
		tileSpell.setIsFlying(true);
		tileSpell.modifyIsTileSpell(true);
		tileSpell.setHasNoCorpse(true);
		tileSpell.setHasNoXP(true);
		if(addToWorld) {
			World.addAtEmptyLocation(tileSpell, depth);
		}
		return tileSpell;
	}
	
	
	

}
