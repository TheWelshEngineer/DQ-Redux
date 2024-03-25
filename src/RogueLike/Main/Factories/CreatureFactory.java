package RogueLike.Main.Factories;

import java.util.List;

import RogueLike.Main.Dice;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.FieldOfView;
import RogueLike.Main.Skill;
import RogueLike.Main.AI.ChestAI;
import RogueLike.Main.AI.PlayerAI;
import RogueLike.Main.Creatures.Bat;
import RogueLike.Main.Creatures.Cloaker;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Creatures.Fungus;
import RogueLike.Main.Creatures.Mimic;
import RogueLike.Main.Creatures.Ogre;
import RogueLike.Main.Creatures.Constructs.AnimatedArmor;
import RogueLike.Main.Creatures.Constructs.AnimatedWeapon;
import RogueLike.Main.Creatures.Containers.BasicChest;
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
import RogueLike.Main.Items.Item;

public class CreatureFactory {
	
public ObjectFactory objectFactory;
	
	public CreatureFactory(ObjectFactory factory) {
		this.objectFactory = factory;
	}
	
	//Player
	public Creature newPlayer(List<String> messages, FieldOfView fov, String playerClass, List<Integer> startingStats, Skill[] startingSkills, String playerName, String playerAncestry) {
		//world, name, glyph, color, maxHP 20, maxMana, base armorclass, strength, dexterity, intelligence, visionRadius, inventorySize) {
		Creature player = new Creature(objectFactory.world, "player", '@', ExtendedAsciiPanel.brightWhite, 20, 20, 10, 10, 10, 10, 8, 20);
		player.setID(0);
		new PlayerAI(player, messages, fov, objectFactory, objectFactory.world);
		
		player.setPlayerName(playerName);
		player.setPlayerAncestry(playerAncestry);
		player.creatureTypes.add(playerAncestry);
		
		player.setStrength(startingStats.get(0));
		player.setDexterity(startingStats.get(1));
		player.setIntelligence(startingStats.get(2));
		
		player.setSkills(startingSkills);

		//Max HP
		player.setMaxHP((player.baseStrength()*2)-5);

		//Max Mana
		if(playerAncestry == "Elf") {
			int amount = (int) Math.ceil((((player.baseIntelligence()*2)-5)*1.25));
			player.setMaxMana(amount);
		}else {
			player.setMaxMana((player.baseIntelligence()*2)-5);
		}
		
		//Ancestry Traits
		if(playerAncestry == "Dwarf") {
			player.modifyBaseArmorClass(1);
			player.setResistsPoisonDamage(true);
		}
		if(playerAncestry == "Dragonborn") {
			player.setResistsFireDamage(true);
		}
		
		//Class Equipment
		if(playerClass == "Warrior") {
			player.setPlayerClass(playerClass);
			Item startWeaponWarrior = objectFactory.itemFactory.newShortsword(0, false);
			player.learnNameQuiet(startWeaponWarrior);
			player.inventory().add(startWeaponWarrior);
			player.equip(startWeaponWarrior);
			Item startArmorWarrior = objectFactory.itemFactory.newChainmailArmor(0, false);
			player.learnNameQuiet(startArmorWarrior);
			player.inventory().add(startArmorWarrior);
			player.equip(startArmorWarrior);
			Item startShieldWarrior = objectFactory.itemFactory.newRoundShield(0, false);
			player.learnNameQuiet(startShieldWarrior);
			player.inventory().add(startShieldWarrior);
			player.equip(startShieldWarrior);
			
			player.setHPScaleAmount(player.hpScaleHigh());
			player.setManaScaleAmount(player.manaScaleLow());
			
		}else if(playerClass == "Rogue") {
			player.setPlayerClass(playerClass);
			Item startWeaponRogue = objectFactory.itemFactory.newDagger(0, false);
			player.learnNameQuiet(startWeaponRogue);
			player.inventory().add(startWeaponRogue);
			player.equip(startWeaponRogue);
			player.equipToQuickslot(startWeaponRogue, 1);
			Item startArmorRogue = objectFactory.itemFactory.newPaddedClothArmor(0, false);
			player.learnNameQuiet(startArmorRogue);
			player.inventory().add(startArmorRogue);
			player.equip(startArmorRogue);
			Item startItemRogue = objectFactory.itemFactory.newPotionOfInvisibility(0, false);
			player.learnNameQuiet(startItemRogue);
			player.inventory().add(startItemRogue);
			
			player.setHPScaleAmount(player.hpScaleMedium());
			player.setManaScaleAmount(player.manaScaleMedium());
			
		}else if(playerClass == "Mage") {
			player.setPlayerClass(playerClass);
			Item startWeaponWizard = objectFactory.itemFactory.newClub(0, false);
			player.learnNameQuiet(startWeaponWizard);
			player.inventory().add(startWeaponWizard);
			player.equip(startWeaponWizard);
			Item startArmorWizard = objectFactory.itemFactory.newPaddedClothArmor(0, false);
			player.learnNameQuiet(startArmorWizard);
			player.inventory().add(startArmorWizard);
			player.equip(startArmorWizard);
			Item startWandMage = objectFactory.itemFactory.newMagicMissileWand(0, player, false);
			player.learnNameQuiet(startWandMage);
			player.inventory().add(startWandMage);
			player.equipToQuickslot(startWandMage, 1);

			player.setHPScaleAmount(player.hpScaleLow());
			player.setManaScaleAmount(player.manaScaleHigh());
			
		}else if(playerClass == "Ranger") {
			player.setPlayerClass(playerClass);
			Item startBowRanger = objectFactory.itemFactory.newShortbow(0, false);
			player.learnNameQuiet(startBowRanger);
			player.inventory().add(startBowRanger);
			player.equip(startBowRanger);
			Item startWeaponRanger = objectFactory.itemFactory.newClub(0, false);
			player.learnNameQuiet(startWeaponRanger);
			player.inventory().add(startWeaponRanger);
			Item startArmorRanger = objectFactory.itemFactory.newPaddedClothArmor(0, false);
			player.learnNameQuiet(startArmorRanger);
			player.inventory().add(startArmorRanger);
			player.equip(startArmorRanger);
			Item startItemRanger = objectFactory.itemFactory.newArrows(0, 0);
			startItemRanger.setStackAmount(20);
			player.learnNameQuiet(startItemRanger);
			player.inventory().add(startItemRanger);
			player.equip(startItemRanger);
			
			player.setHPScaleAmount(player.hpScaleMedium());
			player.setManaScaleAmount(player.manaScaleMedium());
			
		}else{
			
		}
	
		//Starting Food
		player.inventory().add(objectFactory.itemFactory.newRations(0, 0));
		
		//Ancestry Items
		if(playerAncestry == "Dragonborn") {
			Item startWandDragonborn = objectFactory.itemFactory.newFireboltWand(0, player, false);
			player.learnNameQuiet(startWandDragonborn);
			player.inventory().add(startWandDragonborn);
			if(playerClass == "Mage") {
				player.equipToQuickslot(startWandDragonborn, 2);
			}else {
				player.equipToQuickslot(startWandDragonborn, 1);
			}
		}

		//Test Items
		Item startWandTest = objectFactory.itemFactory.newAcidBlastWand(0, player, false);
		//player.learnNameQuiet(startWandTest);
		player.inventory().add(startWandTest);
		
		player.inventory().add(objectFactory.itemFactory.newScrollOfSummonMonsters(0, player, false));
		//player.inventory().add(objectFactory.itemFactory.newScrollOfMagicMapping(0, player, false));
		
		//
		player.stackItems();
		objectFactory.world.addAtSpawnLocation(player, 0);
		return player;
	}
	
	//Fungi
	public Creature newFungus(int depth, boolean addToWorld) {
		Creature fungus = new Fungus(objectFactory, "Fungus", 'f', ExtendedAsciiPanel.green, 1, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(fungus, depth);
		}
		return fungus;
	}
	
	//Bats
	public Creature newBat(int depth, boolean addToWorld) {
		Creature bat = new Bat(objectFactory, "Bat", 'b', ExtendedAsciiPanel.magenta, 2, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(bat, depth);
		}
		return bat;
		
	}
	
	//Skeletons
	public Creature newSkeleton(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new Skeleton(objectFactory, player, "Skeleton", 'k', ExtendedAsciiPanel.white, 3, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	public Creature newSkeletonCryomancer(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new SkeletonCryomancer(objectFactory, player, "Skeleton Cryomancer", 'k', ExtendedAsciiPanel.water, 19, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	public Creature newSkeletonPyromancer(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new SkeletonPyromancer(objectFactory, player, "Skeleton Pyromancer", 'k', ExtendedAsciiPanel.orange, 90, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	public Creature newSkeletonElectromancer(int depth, Creature player, boolean addToWorld) {
		Creature skeleton = new SkeletonElectromancer(objectFactory, player, "Skeleton Electromancer", 'k', ExtendedAsciiPanel.paralyzed, 91, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(skeleton, depth);
		}
		return skeleton;
	}
	
	//Slimes
	public Creature newPinkSlime(int depth, Creature player, boolean addToWorld) {
		Creature slime = new PinkSlime(objectFactory, player, "Pink Slime", 'S', ExtendedAsciiPanel.brightPink, 5, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(slime, depth);
		}
		return slime;		
	}
	
	public Creature newMetalSlime(int depth, Creature player, boolean addToWorld) {
		Creature slime = new MetalSlime(objectFactory, player, "Metal Slime", 'S', ExtendedAsciiPanel.white, 15, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(slime, depth);
		}
		return slime;		
	}
	
	public Creature newMagmaSlime(int depth, Creature player, boolean addToWorld) {
		Creature slime = new MagmaSlime(objectFactory, player, "Magma Slime", 'S', ExtendedAsciiPanel.orange, 17, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(slime, depth);
		}
		return slime;	
	}
	
	//Slimelings
	public Creature newPinkSlimeling(int depth, Creature player, boolean addToWorld) {
		Creature slimeling = new PinkSlimeling(objectFactory, player, "Pink Slimeling", 's', ExtendedAsciiPanel.brightPink, 6, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(slimeling, depth);
		}
		return slimeling;
	}
	
	public Creature newMetalSlimeling(int depth, Creature player, boolean addToWorld) {
		Creature slimeling = new MetalSlimeling(objectFactory, player, "Metal Slimeling", 's', ExtendedAsciiPanel.white, 16, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(slimeling, depth);
		}
		return slimeling;
	}
	
	public Creature newMagmaSlimeling(int depth, Creature player, boolean addToWorld) {
		Creature slimeling = new MagmaSlimeling(objectFactory, player, "Magma Slimeling", 's', ExtendedAsciiPanel.orange, 18, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(slimeling, depth);
		}
		return slimeling;
	}
	
	//Ogres
	public Creature newOgre(int depth, Creature player, boolean addToWorld) {
		Creature ogre = new Ogre(objectFactory, player, "Ogre", 'O', ExtendedAsciiPanel.brightGreen, 7, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(ogre, depth);
		}
		return ogre;
	}
	
	//Gremlins
	public Creature newGremlin(int depth, Creature player, boolean addToWorld) {
		Creature gremlin = new Gremlin(objectFactory, player, "Gremlin", 'g', ExtendedAsciiPanel.cobalt, 8, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(gremlin, depth);
		}
		return gremlin;
	}
	
	public Creature newGremlinSkirmisher(int depth, Creature player, boolean addToWorld) {
		Creature gremlin = new Gremlin(objectFactory, player, "Gremlin Skirmisher", 'g', ExtendedAsciiPanel.red, 9, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(gremlin, depth);
		}
		return gremlin;
	}
	
	public Creature newGremlinAlchemist(int depth, Creature player, boolean addToWorld) {
		Creature alchemist = new GremlinAlchemist(objectFactory, player, "Gremlin Alchemist", 'g', ExtendedAsciiPanel.orange, 4, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(alchemist, depth);
		}
		return alchemist;
	}
	
	//Cloaker
	public Creature newCloaker(int depth, Creature player, boolean addToWorld) {
		Creature cloaker = new Cloaker(objectFactory, player, "Cloaker", 'c', ExtendedAsciiPanel.brightBlack, 10, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(cloaker, depth);
		}
		return cloaker;	
	}
	
	//Constructs
	public Creature newAnimatedArmor(int depth, Creature player, boolean addToWorld) {
		Creature animatedArmor = new AnimatedArmor(objectFactory, player, "Animated Armor", (char)203, ExtendedAsciiPanel.lilac, 11, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(animatedArmor, depth);
		}
		return animatedArmor;
	}
	
	public Creature newAnimatedWeapon(int depth, Creature player, boolean addToWorld) {
		Creature animatedWeapon = new AnimatedWeapon(objectFactory, player, "Animated Weapon", ')', ExtendedAsciiPanel.lilac, 12, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(animatedWeapon, depth);
		}
		return animatedWeapon;
	}
	
	//Mimic
	public Creature newMimic(int depth, Creature player, boolean addToWorld) {
		Creature mimic = new Mimic(objectFactory, player, "Mimic", (char)127, ExtendedAsciiPanel.apple, 13, depth);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(mimic, depth);
		}
		return mimic;
	}
	
	//Chest
	public Creature newChest(int depth, Creature player, boolean addToWorld) {
		Creature chest = new BasicChest(objectFactory, player, "Chest", (char)127, ExtendedAsciiPanel.brown, depth, Dice.d12.roll());
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(chest, depth);
		}
		return chest;
	}
	
	//Spell Creatures
	public Creature newIceWall(int depth, Creature player, boolean addToWorld) {
		Creature icewall = new Creature(objectFactory.world, "Ice Wall", (char)177, ExtendedAsciiPanel.water, 15, 10, 16, 16, 1, 1, 1, 20);
		icewall.setID(14);
		new ChestAI(icewall, objectFactory, objectFactory.world);
		icewall.setImmuneFrostDamage(true);
		icewall.setHasNoCorpse(true);
		icewall.setHasNoXP(true);
		icewall.creatureTypes.add("Elemental");
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(icewall, depth);
		}
		return icewall;
	}
	
	//Technical Creatures
	public Creature newMarker(int depth, Creature player, boolean addToWorld) {
		Creature marker = new Creature(objectFactory.world, " ", 'M', ExtendedAsciiPanel.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(marker, objectFactory, objectFactory.world);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(marker, depth);
			marker.addEffect(objectFactory.randomStructure(player));
		}
		return marker;
	}
	
	public Creature newTileSpell(int depth, Creature player, boolean addToWorld) {
		Creature tileSpell = new Creature(objectFactory.world, " ", 'T', ExtendedAsciiPanel.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(tileSpell, objectFactory, objectFactory.world);
		tileSpell.setIsFlying(true);
		tileSpell.modifyIsTileSpell(true);
		tileSpell.setHasNoCorpse(true);
		tileSpell.setHasNoXP(true);
		if(addToWorld) {
			objectFactory.world.addAtEmptyLocation(tileSpell, depth);
		}
		return tileSpell;
	}
	
	
	

}
