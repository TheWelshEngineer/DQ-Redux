package RogueLike.Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RogueLike.Main.AI.AlchemistAI;
import RogueLike.Main.AI.BatAI;
import RogueLike.Main.AI.ChestAI;
import RogueLike.Main.AI.CloakerAI;
import RogueLike.Main.AI.FungusAI;
import RogueLike.Main.AI.GremlinAI;
import RogueLike.Main.AI.GremlinSkirmisherAI;
import RogueLike.Main.AI.MimicAI;
import RogueLike.Main.AI.OgreAI;
import RogueLike.Main.AI.PlayerAI;
import RogueLike.Main.AI.SkeletonAI;
import RogueLike.Main.AI.SlimeAI;
import RogueLike.Main.AI.SlimelingAI;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Items.Potion;
import asciiPanel.AsciiPanel;

public class ObjectFactory {
	
	public World world;
	
	//public Map<String, Color> potionColors;
	public Map<String, Description> potionColors;
	public List<String> potionAppearances;
	public List<Item> potionIndex;
	
	public Map<String, Color> wandColors;
	public List<String> wandAppearances;
	public List<Item> wandIndex;
	
	public Map<String, Color> ringColors;
	public List<String> ringAppearances;
	public List<Item> ringIndex;
	
	public Map<String, Color> scrollColors;
	public List<String> scrollAppearances;
	public List<Item> scrollIndex;
	
	public Map<String, Color> bookColors;
	public List<String> bookAppearances;
	public List<Item> bookIndex;
	
	public EffectFactory effectFactory = new EffectFactory(this);
	public SpellFactory spellFactory = new SpellFactory(effectFactory);
	public FeatFactory featFactory = new FeatFactory();
	
	public ObjectFactory(World world) {
		this.world = world;
		setUpPotionAppearances();
		//setUpPotionIndex();
		setUpWandAppearances();
		
		setUpRingAppearances();
		
		setUpScrollAppearances();
		
		setUpBookAppearances();
		
	}
	
	/*private void setUpPotionAppearances() {
		potionColors = new HashMap<String, Color>();
		potionColors.put("Crimson Potion", AsciiPanel.brightRed);
		potionColors.put("Golden Potion", ExtraColors.gold);
		potionColors.put("Lime Potion", ExtraColors.lime);
		potionColors.put("Cyan Potion", AsciiPanel.cyan);
		potionColors.put("Cobalt Potion", ExtraColors.cobalt);
		potionColors.put("Magenta Potion", ExtraColors.pink);
		potionColors.put("Charcoal Potion", AsciiPanel.brightBlack);
		potionColors.put("Silver Potion", AsciiPanel.white);
		potionColors.put("Cloudy Potion", AsciiPanel.brightWhite);
		potionColors.put("Pink Potion", ExtraColors.brightPink);
		potionColors.put("Emerald Potion", AsciiPanel.brightGreen);
		potionColors.put("Amber Potion", ExtraColors.orange);
		
		potionAppearances = new ArrayList<String>(potionColors.keySet());
		Collections.shuffle(potionAppearances);
	}*/
	
	private void setUpPotionAppearances() {
		potionColors = new HashMap<String, Description>();
		potionColors.put("Crimson Potion", new Description("crimson", AsciiPanel.brightRed));
		potionColors.put("Golden Potion", new Description("golden", ExtraColors.gold));
		potionColors.put("Lime Potion", new Description("lime", ExtraColors.lime));
		potionColors.put("Cyan Potion", new Description("cyan", ExtraColors.cyan));
		potionColors.put("Cobalt Potion", new Description("cobalt", ExtraColors.cobalt));
		potionColors.put("Magenta Potion", new Description("magenta", ExtraColors.pink));
		potionColors.put("Charcoal Potion", new Description("charcoal", ExtraColors.brightBlack));
		potionColors.put("Silver Potion", new Description("silver", ExtraColors.white));
		potionColors.put("Cloudy Potion", new Description("cloudy", ExtraColors.brightWhite));
		potionColors.put("Pink Potion", new Description("pink", ExtraColors.brightPink));
		potionColors.put("Emerald Potion", new Description("emerald", ExtraColors.brightGreen));
		potionColors.put("Amber Potion", new Description("amber", ExtraColors.orange));
		
		potionAppearances = new ArrayList<String>(potionColors.keySet());
		Collections.shuffle(potionAppearances);
	}
	
	public void setUpPotionIndex() {
		potionIndex = new ArrayList<Item>();
		potionIndex.add(newPotionOfPoison(0, 0));
		potionIndex.add(newPotionOfGiantStrength(0, 0));
		potionIndex.add(newPotionOfMana(0, 0));
		potionIndex.add(newPotionOfInvisibility(0, 0));
		potionIndex.add(newPotionOfParalysis(0, 0));
		potionIndex.add(newPotionOfCausticGas(0, 0));
		potionIndex.add(newPotionOfHealing(0, 0));
		potionIndex.add(newPotionOfRestoration(0, 0));
		potionIndex.add(newPotionOfMindVision(0, 0));
		potionIndex.add(newPotionOfOvergrowth(0, 0));
		potionIndex.add(newPotionOfLiquidFlame(0, 0));
		potionIndex.add(newPotionOfLevitation(0, 0));
		
		Collections.shuffle(potionIndex);
	}
	
	private void setUpWandAppearances() {
		wandColors = new HashMap<String, Color>();
		wandColors.put("Oak Wand", ExtraColors.paper);
		wandColors.put("Ash Wand", ExtraColors.brightGreen);
		wandColors.put("Thorn Wand", ExtraColors.red);
		wandColors.put("Beech Wand", ExtraColors.brown);
		wandColors.put("Cherry Wand", ExtraColors.pink);
		wandColors.put("Willow Wand", ExtraColors.pink);
		wandColors.put("Maple Wand", ExtraColors.orange);
		wandColors.put("Birch Wand", AsciiPanel.white);
		wandColors.put("Rowan Wand", ExtraColors.apple);
		
		wandAppearances = new ArrayList<String>(wandColors.keySet());
		Collections.shuffle(wandAppearances);
	}
	
	public void setUpWandIndex(Creature player) {
		wandIndex = new ArrayList<Item>();
		wandIndex.add(newForceWand(0, player, 0));
		wandIndex.add(newFreezingWand(0, player, 0));
		wandIndex.add(newLightningWand(0, player, 0));
		wandIndex.add(newFireboltWand(0, player, 0));
		wandIndex.add(newMissileWand(0, player, 0));
		wandIndex.add(newIceWallWand(0, player, 0));
		
		Collections.shuffle(wandIndex);
	}
	
	private void setUpRingAppearances() {
		ringColors = new HashMap<String, Color>();
		ringColors.put("Diamond Ring", AsciiPanel.brightWhite);
		ringColors.put("Ruby Ring", ExtraColors.brightRed);
		ringColors.put("Emerald Ring", ExtraColors.lime);
		ringColors.put("Quartz Ring", ExtraColors.brightPink);
		ringColors.put("Topaz Ring", ExtraColors.orange);
		ringColors.put("Sapphire Ring", ExtraColors.cyan);
		ringColors.put("Amethyst Ring", ExtraColors.lilac);
		ringColors.put("Silver Ring", AsciiPanel.white);
		ringColors.put("Opal Ring", AsciiPanel.brightBlack);
		ringColors.put("Rusted Ring", ExtraColors.brown);
		
		ringAppearances = new ArrayList<String>(ringColors.keySet());
		Collections.shuffle(ringAppearances);
	}
	
	public void setUpRingIndex(Creature player) {
		ringIndex = new ArrayList<Item>();
		ringIndex.add(newStrengthRing(0, 0));
		ringIndex.add(newFireDefenseRing(0, 0));
		ringIndex.add(newIceDefenseRing(0, 0));
		ringIndex.add(newShockDefenseRing(0, 0));
		ringIndex.add(newElementDefenseRing(0, 0));
		ringIndex.add(newVisionRing(0, 0));
		ringIndex.add(newDefenseRing(0, 0));
		ringIndex.add(newPoisonDefenseRing(0, 0));
		ringIndex.add(newDexterityRing(0, 0));
		ringIndex.add(newIntelligenceRing(0, 0));
		
		Collections.shuffle(ringIndex);
	}
	
	private void setUpScrollAppearances() {
		scrollColors = new HashMap<String, Color>();
		scrollColors.put("Scroll of rune LATA", AsciiPanel.brightRed);
		scrollColors.put("Scroll of rune MAELA", ExtraColors.gold);
		scrollColors.put("Scroll of rune GJALDA", ExtraColors.lime);
		scrollColors.put("Scroll of rune SKJOLDR", AsciiPanel.cyan);
		scrollColors.put("Scroll of rune KYN", ExtraColors.cobalt);
		scrollColors.put("Scroll of rune MYSS", ExtraColors.pink);
		scrollColors.put("Scroll of rune LEYSA", AsciiPanel.brightBlack);
		scrollColors.put("Scroll of rune DJUPR", AsciiPanel.white);
		scrollColors.put("Scroll of rune MOEDR", AsciiPanel.brightWhite);
		
		scrollAppearances = new ArrayList<String>(scrollColors.keySet());
		Collections.shuffle(scrollAppearances);
	}
	
	public void setUpScrollIndex(Creature player) {
		scrollIndex = new ArrayList<Item>();
		scrollIndex.add(newScrollOfIdentify(0, player, 0));
		scrollIndex.add(newScrollOfMagicMapping(0, player, 0));
		scrollIndex.add(newScrollOfSummonMonsters(0, player, 0));
		scrollIndex.add(newScrollOfUpgrade(0, player, 0));
		scrollIndex.add(newScrollOfRemoveCurse(0, player, 0));
		scrollIndex.add(newScrollOfEnchantment(0, player, 0));
		scrollIndex.add(newScrollOfConfusion(0, player, 0));
		
		Collections.shuffle(scrollIndex);
	}
	
	private void setUpBookAppearances() {
		bookColors = new HashMap<String, Color>();
		bookColors.put("Ragged Spellbook", AsciiPanel.brightRed);
		bookColors.put("Torn Spellbook", ExtraColors.gold);
		bookColors.put("Singed Spellbook", ExtraColors.lime);
		bookColors.put("Stained Spellbook", AsciiPanel.cyan);
		bookColors.put("Worn Spellbook", ExtraColors.cobalt);
		bookColors.put("Ancient Spellbook", ExtraColors.pink);
		bookColors.put("Defaced Spellbook", AsciiPanel.brightBlack);
		bookColors.put("Intact Spellbook", AsciiPanel.white);
		bookColors.put("Immaculate Spellbook", AsciiPanel.brightWhite);
		
		bookAppearances = new ArrayList<String>(bookColors.keySet());
		Collections.shuffle(bookAppearances);
	}
	
	public void setUpBookIndex(Creature player) {
		bookIndex = new ArrayList<Item>();
		bookIndex.add(newFireboltBook(0, player, 0));

		
		Collections.shuffle(bookIndex);
	}
	
	public void upgradeItem(Item item, int amount) {
		item.modifyUpgradeLevel(amount);
		item.modifyCurrentGoldValue((int)Math.ceil(item.baseGoldValue()*0.5));
		
	}
	
	public void enchantItem(Item item, Effect enchantment) {
		item.setIsEnchanted(true);
		item.setEnchantmentEffect(enchantment);
		item.setEnchantedName(item.enchantmentEffect().enchantmentName());
		if(enchantment.enchantmentName() == "Blazing ") {
			item.setDealsFireDamage(true);
		}else if(enchantment.enchantmentName() == "Freezing ") {
			item.setDealsFrostDamage(true);
		}else if(enchantment.enchantmentName() == "Shocking ") {
			item.setDealsShockDamage(true);
		}else {
			
		}
		item.modifyCurrentGoldValue(item.baseGoldValue());
	}
	
	public void randomEnchantWeapon(Item item) {
		item.setIsEnchanted(true);
		Effect enchantment = randomMeleeEnchantment();
		item.setEnchantmentEffect(enchantment);
		item.setEnchantedName(item.enchantmentEffect().enchantmentName());
		if(enchantment.enchantmentName() == "Blazing ") {
			item.setDealsFireDamage(true);
		}else if(enchantment.enchantmentName() == "Freezing ") {
			item.setDealsFrostDamage(true);
		}else if(enchantment.enchantmentName() == "Shocking ") {
			item.setDealsShockDamage(true);
		}else {
			
		}
		item.modifyCurrentGoldValue(item.baseGoldValue());
	}
	
	public void randomEnchantArmor(Item item) {
		item.setIsEnchanted(true);
		Effect enchantment = randomArmorEnchantment();
		item.setEnchantmentEffect(enchantment);
		item.setEnchantedName(item.enchantmentEffect().enchantmentName());
		if(enchantment.enchantmentName() == " of Magma Ward") {
			item.setResistsFireDamage(true);
		}else if(enchantment.enchantmentName() == " of Chill Ward") {
			item.setResistsFrostDamage(true);
		}else if(enchantment.enchantmentName() == " of Arc Ward") {
			item.setResistsShockDamage(true);
		}else {
			
		}
		item.modifyCurrentGoldValue(item.baseGoldValue());
	}
	
	public void curseItem(Item item) {
		item.setIsCursed(true);
		item.setCurseEffect(randomCurse());
	}
	
	public Creature newPlayer(List<String> messages, FieldOfView fov, String playerClass, List<Integer> startingStats, /*List<Integer> startingSkills,*/ String playerName/*, String playerSpecies*/) {
		//world, name, glyph, color, maxHP 20, maxMana, base armorclass, strength, dexterity, intelligence, visionRadius, inventorySize) {
		Creature player = new Creature(world, "player", '@', ExtraColors.brightWhite, 20, 20, 10, 10, 10, 10, 8, 20);
		player.setID(0);
		new PlayerAI(player, messages, fov, this, this.world);
		player.setPlayerName(playerName);
		//player.modifyPlayerSpecies(playerSpecies);
		player.setStrength(startingStats.get(0));
		player.setDexterity(startingStats.get(1));
		player.setIntelligence(startingStats.get(2));
		player.creatureTypes.add("Humanoid");
		
		//if(playerSpecies == "Orc") {
			//int amount = (int)(((player.baseStrength()*2)-5)*1.25);
			//player.setMaxHP(amount);
		//}else {
			player.setMaxHP((player.baseStrength()*2)-5);
		//}
		
		//if(playerSpecies == "Elf") {
			//int amount = (int)(((player.baseIntelligence()*2)-5)*1.25);
			//player.setMaxMana(amount);
		//}else if(playerSpecies == "Halfling") {
			//int amount = (int)(((player.baseIntelligence()*2)-5)*1.15);
			//player.setMaxMana(amount);
		//}else {
			player.setMaxMana((player.baseIntelligence()*2)-5);
		//}
		
		/*if(playerSpecies == "Lycan") {
			player.modifyVisionRadius(2);
		}
		if(playerSpecies == "Tiefling") {
			player.setResistsFire(1);
			player.setSaveBonusFire(2);
		}
		if(playerSpecies == "Dwarf") {
			player.modifyBaseArmorClass(2);
			player.setResistsAcid(1);
			player.setSaveBonusAcid(2);
		}
		if(playerSpecies == "Dragonborn") {
			player.setResistsFrost(1);
			player.setSaveBonusFrost(2);
		}
		if(playerSpecies == "Gorgon") {
			player.setResistsPoison(1);
			player.setSaveBonusPoison(2);
		}
		if(playerSpecies == "Zendarii") {
			player.setResistsChaos(1);
			player.setSaveBonusChaos(2);
		}*/
		
		/*player.setSkillSimpleWeapons(startingSkills.get(0));
		player.setSkillMartialWeapons(startingSkills.get(1));
		player.setSkillFinesseWeapons(startingSkills.get(2));
		player.setSkillRangedWeapons(startingSkills.get(3));
		player.setSkillFortitude(startingSkills.get(4));
		player.setSkillPerception(startingSkills.get(5));
		player.setSkillStealth(startingSkills.get(6));
		player.setSkillEvocation(startingSkills.get(7));
		player.setSkillPyromancy(startingSkills.get(8));
		player.setSkillCryomancy(startingSkills.get(9));
		player.setSkillElectromancy(startingSkills.get(10));
		player.setSkillAlchemancy(startingSkills.get(11));
			
		if(playerSpecies == "Elf") {
			player.spellbook().add(spellFactory.magicMissile(player));
		}else if(playerSpecies == "Orc") {
			player.spellbook().add(spellFactory.overgrowth(player));
		}else if(playerSpecies == "Tiefling") {
			player.spellbook().add(spellFactory.firebolt(player));
		}else if(playerSpecies == "Dragonborn") {
			player.spellbook().add(spellFactory.frostWard(player));
		}else if(playerSpecies == "Halfling") {
			player.spellbook().add(spellFactory.levitate(player));
		}else if(playerSpecies == "Gorgon") {
			player.spellbook().add(spellFactory.paralysis(player));
		}else if(playerSpecies == "Lycan") {
			player.spellbook().add(spellFactory.beastForm(player));
		}else if(playerSpecies == "Zendarii") {
			player.spellbook().add(spellFactory.confusion(player));
		}*/
		
		if(playerClass == "Warrior") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.warriorTraining());
			player.featbook().add(featFactory.simpleWeapons());
			player.featbook().add(featFactory.martialWeapons());
			player.featbook().add(featFactory.lightArmor());
			player.featbook().add(featFactory.mediumArmor());*/
			
			Item startWeaponWarrior = newLongsword(0, 0);
			player.learnNameQuiet(startWeaponWarrior);
			player.inventory().add(startWeaponWarrior);
			player.equip(startWeaponWarrior);
			Item startArmorWarrior = newChainmailArmor(0, 0);
			player.learnNameQuiet(startArmorWarrior);
			player.inventory().add(startArmorWarrior);
			player.equip(startArmorWarrior);
			Item startShieldWarrior = newKiteShield(0, 0);
			player.learnNameQuiet(startShieldWarrior);
			player.inventory().add(startShieldWarrior);
			player.equip(startShieldWarrior);
			
			player.setHPScaleAmount(player.hpScaleHigh());
			player.setManaScaleAmount(player.manaScaleLow());
			
		}else if(playerClass == "Rogue") {
			player.setPlayerClass(playerClass);
			/*player.featbook().add(featFactory.rogueTraining());
			player.featbook().add(featFactory.finesseWeapons());
			player.featbook().add(featFactory.rangedWeapons());
			player.featbook().add(featFactory.lightArmor());
			player.featbook().add(featFactory.stealth());*/
			
			Item startWeaponRogue = newDagger(0, 0);
			player.learnNameQuiet(startWeaponRogue);
			player.inventory().add(startWeaponRogue);
			player.equip(startWeaponRogue);
			Item startArmorRogue = newPaddedArmor(0, 0);
			player.learnNameQuiet(startArmorRogue);
			player.inventory().add(startArmorRogue);
			player.equip(startArmorRogue);
			Item startItemRogue = newPotionOfInvisibility(0, 0);
			player.learnNameQuiet(startItemRogue);
			player.inventory().add(startItemRogue);
			
			
			player.setHPScaleAmount(player.hpScaleMedium());
			player.setManaScaleAmount(player.manaScaleLow());
			
		}else if(playerClass == "Wizard") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.wizardTraining());
			player.featbook().add(featFactory.evocation());
			player.featbook().add(featFactory.pyromancy());
			player.featbook().add(featFactory.cryomancy());*/
			
			Item startWeaponWizard = newClub(0, 0);
			player.learnNameQuiet(startWeaponWizard);
			player.inventory().add(startWeaponWizard);
			player.equip(startWeaponWizard);
			Item startArmorWizard = newPaddedArmor(0, 0);
			player.learnNameQuiet(startArmorWizard);
			player.inventory().add(startArmorWizard);
			player.equip(startArmorWizard);

			player.setHPScaleAmount(player.hpScaleLow());
			player.setManaScaleAmount(player.manaScaleHigh());
			
		}else if(playerClass == "Alchemist") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.alchemistTraining());
			player.featbook().add(featFactory.simpleWeapons());
			player.featbook().add(featFactory.finesseWeapons());
			player.featbook().add(featFactory.alchemancy());*/
			
			Item startWeaponAlchemist = newDagger(0, 0);
			player.learnNameQuiet(startWeaponAlchemist);
			player.inventory().add(startWeaponAlchemist);
			player.equip(startWeaponAlchemist);
			Item startArmorAlchemist = newLeatherArmor(0, 0);
			player.learnNameQuiet(startArmorAlchemist);
			player.inventory().add(startArmorAlchemist);
			player.equip(startArmorAlchemist);
			Item startItemAlchemist = newPotionOfPoison(0, 0);
			player.learnNameQuiet(startItemAlchemist);
			player.inventory().add(startItemAlchemist);

			player.setHPScaleAmount(player.hpScaleMedium());
			player.setManaScaleAmount(player.manaScaleMedium());
			
		}else if(playerClass == "Barbarian") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.barbarianTraining());
			player.featbook().add(featFactory.simpleWeapons());
			player.featbook().add(featFactory.martialWeapons());
			player.featbook().add(featFactory.lightArmor());
			player.featbook().add(featFactory.mediumArmor());*/
			
			Item startWeaponBarbarian = newBattleaxe(0, 0);
			player.learnNameQuiet(startWeaponBarbarian);
			player.inventory().add(startWeaponBarbarian);
			player.equip(startWeaponBarbarian);
			Item startArmorBarbarian = newLeatherArmor(0, 0);
			player.learnNameQuiet(startArmorBarbarian);
			player.inventory().add(startArmorBarbarian);
			player.equip(startArmorBarbarian);
			Item startShieldBarbarian = newRoundShield(0, 0);
			player.learnNameQuiet(startShieldBarbarian);
			player.inventory().add(startShieldBarbarian);
			player.equip(startShieldBarbarian);

			player.setHPScaleAmount(player.hpScaleHigh());
			player.setManaScaleAmount(player.manaScaleLow());
			
		}else if(playerClass == "Witch") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.witchTraining());
			player.featbook().add(featFactory.cryomancy());
			player.featbook().add(featFactory.electromancy());
			player.featbook().add(featFactory.perception());*/
			
			Item startWeaponWitch = newDagger(0, 0);
			player.learnNameQuiet(startWeaponWitch);
			player.inventory().add(startWeaponWitch);
			player.equip(startWeaponWitch);
			Item startArmorWitch = newPaddedArmor(0, 0);
			player.learnNameQuiet(startArmorWitch);
			player.inventory().add(startArmorWitch);
			player.equip(startArmorWitch);

			player.setHPScaleAmount(player.hpScaleLow());
			player.setManaScaleAmount(player.manaScaleHigh());
			
		}else if(playerClass == "Evoker") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.evokerTraining());
			player.featbook().add(featFactory.simpleWeapons());
			player.featbook().add(featFactory.martialWeapons());
			player.featbook().add(featFactory.lightArmor());
			player.featbook().add(featFactory.evocation());*/
			
			Item startWeaponEvoker = newWarhammer(0, 0);
			player.learnNameQuiet(startWeaponEvoker);
			player.inventory().add(startWeaponEvoker);
			player.equip(startWeaponEvoker);
			Item startArmorEvoker = newStuddedLeatherArmor(0, 0);
			player.learnNameQuiet(startArmorEvoker);
			player.inventory().add(startArmorEvoker);
			player.equip(startArmorEvoker);
			Item startShieldEvoker = newRoundShield(0, 0);
			player.learnNameQuiet(startShieldEvoker);
			player.inventory().add(startShieldEvoker);
			player.equip(startShieldEvoker);

			player.setHPScaleAmount(player.hpScaleMedium());
			player.setManaScaleAmount(player.manaScaleMedium());
			
		}else if(playerClass == "Sorcerer") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.sorcererTraining());
			player.featbook().add(featFactory.simpleWeapons());
			player.featbook().add(featFactory.pyromancy());
			player.featbook().add(featFactory.simpleFire());*/
			
			Item startWeaponSorcerer = newHandaxe(0, 0);
			player.learnNameQuiet(startWeaponSorcerer);
			player.inventory().add(startWeaponSorcerer);
			player.equip(startWeaponSorcerer);
			Item startArmorSorcerer = newPaddedArmor(0, 0);
			player.learnNameQuiet(startArmorSorcerer);
			player.inventory().add(startArmorSorcerer);
			player.equip(startArmorSorcerer);

			player.setHPScaleAmount(player.hpScaleMedium());
			player.setManaScaleAmount(player.manaScaleHigh());
			
		}else if(playerClass == "Ranger") {
			player.setPlayerClass(playerClass);
			
			/*player.featbook().add(featFactory.rangerTraining());
			player.featbook().add(featFactory.simpleWeapons());
			player.featbook().add(featFactory.rangedWeapons());
			player.featbook().add(featFactory.lightArmor());
			player.featbook().add(featFactory.perception());*/
			
			Item startWeaponRanger = newLongbow(0, 0);
			player.learnNameQuiet(startWeaponRanger);
			player.inventory().add(startWeaponRanger);
			player.equip(startWeaponRanger);
			Item startArmorRanger = newLeatherArmor(0, 0);
			player.learnNameQuiet(startArmorRanger);
			player.inventory().add(startArmorRanger);
			player.equip(startArmorRanger);
			Item startAmmoRanger = newArrows(0, 0);
			player.learnNameQuiet(startAmmoRanger);
			startAmmoRanger.setStackAmount(20);
			player.inventory().add(startAmmoRanger);
			player.equip(startAmmoRanger);

			player.setHPScaleAmount(player.hpScaleMedium());
			player.setManaScaleAmount(player.manaScaleLow());
			
		}else {
			
		}
	
		
		player.inventory().add(newRations(0, 0));
		//temp
		//
		player.inventory().add(newPotionOfHealing(0, 0));
		player.inventory().add(newPotionOfHealing(0, 0));
		player.inventory().add(newPotionOfHealing(0, 0));
		//
		//player.spellbook().add(spellFactory.test(player));

		/*player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newForceWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newLightningWand(0, player, 0).writtenSpells().get(0));
		player.spellbook().add(newFireboltWand(0, player, 0).writtenSpells().get(0));*/
		//





		
		
		player.stackItems();
		world.addAtSpawnLocation(player, 0);
		return player;
		
	}
	
	public Creature newFungus(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature fungus = new Creature(world, "Fungus", 'f', AsciiPanel.green, 10, 10, 9, 3, 8, 10, 3, 20);
		fungus.setID(1);
		new FungusAI(fungus, this, this.world);
		fungus.creatureTypes.add("Plant");
		fungus.scaleHPWithDepth(depth);
		fungus.scaleManaWithDepth(depth);	
		fungus.scaleStrengthWithDepth(depth);	
		fungus.scaleDexterityWithDepth(depth);	
		fungus.scaleIntelligenceWithDepth(depth);	
		if(addToWorld > 0) {
			world.addAtEmptyLocation(fungus, depth);
		}else {
			
		}
		return fungus;
		
		
	}
	
	public Creature newBat(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature bat = new Creature(world, "Bat", 'b', AsciiPanel.magenta, 8, 1, 12, 2, 15, 2, 6, 20);
		bat.setID(2);
		new BatAI(bat, this, this.world);
		bat.modifyFlying(1);
		bat.setSocial(true);
		bat.creatureTypes.add("Beast");
		bat.scaleHPWithDepth(depth);
		bat.scaleManaWithDepth(depth);	
		bat.scaleStrengthWithDepth(depth);	
		bat.scaleDexterityWithDepth(depth);	
		bat.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(bat, depth);
		}else {
			
		}
		return bat;
		
	}	
	
	public Creature newSkeleton(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature skeleton = new Creature(world, "Skeleton", 's', AsciiPanel.white, 13, 10, 13, 10, 14, 6, 6, 20);
		skeleton.setID(3);
		new SkeletonAI(skeleton, player, this, this.world);
		//
		Item startWeapon = newSword(0, 0);
		skeleton.inventory().add(startWeapon);
		skeleton.equip(startWeapon);
		//
		skeleton.creatureTypes.add("Undead");
		skeleton.scaleHPWithDepth(depth);
		skeleton.scaleManaWithDepth(depth);	
		skeleton.scaleStrengthWithDepth(depth);	
		skeleton.scaleDexterityWithDepth(depth);	
		skeleton.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(skeleton, depth);
		}else {
			
		}
		return skeleton;
		
	}
	
	public Creature newCrazyAlchemist(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature alchemist = new Creature(world, "Crazy Alchemist", 'c', ExtraColors.paper, 15, 15, 12, 11, 12, 10, 8, 20);
		alchemist.setID(4);
		new AlchemistAI(alchemist, player, this, this.world);
		//
		Item startWeapon = newDagger(0, 0);
		Item startArmor = newLeatherArmor(0, 0);
		alchemist.inventory().add(startWeapon);
		alchemist.inventory().add(startArmor);
		alchemist.equip(startWeapon);
		alchemist.equip(startArmor);
		//
		alchemist.creatureTypes.add("Humanoid");
		alchemist.scaleHPWithDepth(depth);
		alchemist.scaleManaWithDepth(depth);	
		alchemist.scaleStrengthWithDepth(depth);	
		alchemist.scaleDexterityWithDepth(depth);	
		alchemist.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(alchemist, depth);
		}else {
			
		}
		return alchemist;
		
	}
	
	public Creature newPinkSlime(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature slime = new Creature(world, "Pink Slime", 'P', ExtraColors.brightPink, 22, 10, 8, 12, 6, 1, 6, 20);
		slime.setID(5);
		new SlimeAI(slime, player, this, this.world);
		slime.creatureTypes.add("Ooze");
		slime.scaleHPWithDepth(depth);
		slime.scaleManaWithDepth(depth);	
		slime.scaleStrengthWithDepth(depth);	
		slime.scaleDexterityWithDepth(depth);	
		slime.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(slime, depth);
		}else {
			
		}
		return slime;
		
	}
	
	public Creature newPinkSlimeling(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature slimeling = new Creature(world, "Pink Slimeling", 'p', ExtraColors.brightPink, 6, 10, 8, 12, 6, 1, 6, 20);
		slimeling.setID(6);
		new SlimelingAI(slimeling, player, this, this.world);
		slimeling.creatureTypes.add("Ooze");
		slimeling.scaleHPWithDepth(depth);
		slimeling.scaleManaWithDepth(depth);	
		slimeling.scaleStrengthWithDepth(depth);	
		slimeling.scaleDexterityWithDepth(depth);	
		slimeling.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(slimeling, depth);
		}else {
			
		}
		return slimeling;
		
	}
	
	public Creature newOgre(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature ogre = new Creature(world, "Ogre", 'O', AsciiPanel.brightGreen, 40, 20, 10, 16, 12, 7, 8, 20);
		ogre.setID(7);
		new OgreAI(ogre, player, this, this.world);
		Item startWeapon = newGreatsword(0, 0);
		Item startArmor = newHideArmor(0, 0);
		ogre.inventory().add(startWeapon);
		ogre.inventory().add(startArmor);
		ogre.equip(startWeapon);
		ogre.equip(startArmor);
		ogre.creatureTypes.add("Humanoid");
		ogre.creatureTypes.add("Beast");
		ogre.scaleHPWithDepth(depth);
		ogre.scaleManaWithDepth(depth);	
		ogre.scaleStrengthWithDepth(depth);	
		ogre.scaleDexterityWithDepth(depth);	
		ogre.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(ogre, depth);
		}else {
			
		}
		return ogre;
		
	}
	
	public Creature newGremlin(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature gremlin = new Creature(world, "Gremlin", 'g', ExtraColors.cobalt, 17, 10, 10, 8, 14, 10, 8, 20);
		gremlin.setID(8);
		new GremlinAI(gremlin, player, this, this.world);
		Item startWeapon = newBow(0, 0);
		gremlin.inventory().add(startWeapon);
		gremlin.equip(startWeapon);
		Item startAmmo = newArrows(0, 0);
		gremlin.inventory().add(startAmmo);
		gremlin.equip(startAmmo);
		gremlin.creatureTypes.add("Gremlin");
		gremlin.scaleHPWithDepth(depth);
		gremlin.scaleManaWithDepth(depth);	
		gremlin.scaleStrengthWithDepth(depth);	
		gremlin.scaleDexterityWithDepth(depth);	
		gremlin.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(gremlin, depth);
		}else {
			
		}
		return gremlin;
		
	}
	
	public Creature newGremlinSkirmisher(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature gremlin = new Creature(world, "Gremlin Skirmisher", 'g', ExtraColors.cobalt, 17, 10, 10, 8, 14, 10, 8, 20);
		gremlin.setID(9);
		new GremlinSkirmisherAI(gremlin, player, this, this.world);
		Item startWeapon = newSword(0, 0);
		gremlin.inventory().add(startWeapon);
		gremlin.equip(startWeapon);
		Item startArmor = newLeatherArmor(0, 0);
		gremlin.inventory().add(startArmor);
		gremlin.equip(startArmor);
		gremlin.creatureTypes.add("Gremlin");
		gremlin.scaleHPWithDepth(depth);
		gremlin.scaleManaWithDepth(depth);	
		gremlin.scaleStrengthWithDepth(depth);	
		gremlin.scaleDexterityWithDepth(depth);	
		gremlin.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(gremlin, depth);
		}else {
			
		}
		return gremlin;
		
	}
	
	public Creature newCloaker(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature cloaker = new Creature(world, "Cloaker", 'c', AsciiPanel.brightBlack, 22, 10, 11, 16, 12, 2, 8, 20);
		cloaker.setID(10);
		new CloakerAI(cloaker, player, this, this.world);
		cloaker.modifyInvisible(true);
		cloaker.changeColor(ExtraColors.invisible);
		cloaker.creatureTypes.add("Beast");
		cloaker.creatureTypes.add("Monstrosity");
		cloaker.scaleHPWithDepth(depth);
		cloaker.scaleManaWithDepth(depth);	
		cloaker.scaleStrengthWithDepth(depth);	
		cloaker.scaleDexterityWithDepth(depth);	
		cloaker.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(cloaker, depth);
		}else {
			
		}
		return cloaker;
		
	}
	
	public Creature newAnimatedArmor(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature animatedArmor = new Creature(world, "Animated Armor", (char)203, ExtraColors.lilac, 35, 10, 18, 14, 11, 1, 8, 20);
		animatedArmor.setID(11);
		new SkeletonAI(animatedArmor, player, this, this.world);
		Item startArmor = randomArmor(0, 0);
		animatedArmor.inventory().add(startArmor);
		animatedArmor.equip(startArmor);
		animatedArmor.modifyNoCorpse(1);
		animatedArmor.creatureTypes.add("Construct");
		animatedArmor.scaleHPWithDepth(depth);
		animatedArmor.scaleManaWithDepth(depth);	
		animatedArmor.scaleStrengthWithDepth(depth);	
		animatedArmor.scaleDexterityWithDepth(depth);	
		animatedArmor.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(animatedArmor, depth);
		}else {
			
		}
		return animatedArmor;
		
	}
	
	public Creature newAnimatedWeapon(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature animatedWeapon = new Creature(world, "Animated Weapon", ')', ExtraColors.lilac, 35, 10, 18, 14, 11, 1, 8, 20);
		animatedWeapon.setID(12);
		new SkeletonAI(animatedWeapon, player, this, this.world);
		Item startWeapon = randomMeleeWeapon(0, 0);
		animatedWeapon.inventory().add(startWeapon);
		animatedWeapon.equip(startWeapon);
		animatedWeapon.modifyNoCorpse(1);
		animatedWeapon.creatureTypes.add("Construct");
		animatedWeapon.scaleHPWithDepth(depth);
		animatedWeapon.scaleManaWithDepth(depth);	
		animatedWeapon.scaleStrengthWithDepth(depth);	
		animatedWeapon.scaleDexterityWithDepth(depth);	
		animatedWeapon.scaleIntelligenceWithDepth(depth);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(animatedWeapon, depth);
		}else {
			
		}
		return animatedWeapon;
		
	}
	
	public Creature newPotionChest(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature potionChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(potionChest, this, this.world);
		Item startPotion = randomPotion(0, 0);
		potionChest.inventory().add(startPotion);
		potionChest.modifyIsContainer(true);
		potionChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(potionChest, depth);
		}else {
			
		}
		return potionChest;
		
	}
	
	public Creature newFoodChest(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature foodChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(foodChest, this, this.world);
		Item startFood = randomFood(0, 0);
		foodChest.inventory().add(startFood);
		foodChest.modifyIsContainer(true);
		foodChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(foodChest, depth);
		}else {
			
		}
		return foodChest;
		
	}
	
	public Creature newAmmoChest(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature ammoChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(ammoChest, this, this.world);
		Item startAmmo = randomAmmo(0, 0);
		ammoChest.inventory().add(startAmmo);
		ammoChest.modifyIsContainer(true);
		ammoChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(ammoChest, depth);
		}else {
			
		}
		return ammoChest;
		
	}
	
	public Creature newWeaponChest(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature weaponChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(weaponChest, this, this.world);
		Item startWeapon = randomWeapon(0, 0);
		weaponChest.inventory().add(startWeapon);
		weaponChest.modifyIsContainer(true);
		weaponChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(weaponChest, depth);
		}else {
			
		}
		return weaponChest;
		
	}
	
	public Creature newArmorChest(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature armorChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(armorChest, this, this.world);
		Item startArmor = randomArmor(0, 0);
		armorChest.inventory().add(startArmor);
		armorChest.modifyIsContainer(true);
		armorChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(armorChest, depth);
		}else {
			
		}
		return armorChest;
		
	}
	
	public Creature newShieldChest(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature shieldChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(shieldChest, this, this.world);
		Item startShield = randomShield(0, 0);
		shieldChest.inventory().add(startShield);
		shieldChest.modifyIsContainer(true);
		shieldChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(shieldChest, depth);
		}else {
			
		}
		return shieldChest;
		
	}
	
	public Creature newRingChest(int depth, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature ringChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(ringChest, this, this.world);
		Item startRing = randomRing(0, 0);
		ringChest.inventory().add(startRing);
		ringChest.modifyIsContainer(true);
		ringChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(ringChest, depth);
		}else {
			
		}
		return ringChest;
		
	}
	
	public Creature newScrollChest(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature scrollChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(scrollChest, this, this.world);
		Item startScroll = randomScroll(0, player, 0);
		scrollChest.inventory().add(startScroll);
		scrollChest.modifyIsContainer(true);
		scrollChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(scrollChest, depth);
		}else {
			
		}
		return scrollChest;
		
	}
	
	public Creature newWandChest(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature wandChest = new Creature(world, "Chest", (char)127, ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(wandChest, this, this.world);
		Item startWand = randomWand(0, player, 0);
		wandChest.inventory().add(startWand);
		wandChest.modifyIsContainer(true);
		wandChest.modifyNoCorpse(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(wandChest, depth);
		}else {
			
		}
		return wandChest;
		
	}
	
	public Creature newMimic(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature mimic = new Creature(world, "Mimic", (char)127, ExtraColors.apple, 60, 10, 12, 17, 12, 5, 8, 20);
		mimic.setID(13);
		new MimicAI(mimic, player, this, this.world);
		Item startItem = randomMimicDrop(0, 0);
		if(ExtraMaths.d10() < 4) {
			upgradeItem(startItem, ExtraMaths.d4());
		}
		if(ExtraMaths.d10() == 1) { // == 1
			curseItem(startItem);
		}
		mimic.inventory().add(startItem);
		mimic.creatureTypes.add("Monstrosity");
		mimic.scaleHPWithDepth(depth);
		mimic.scaleManaWithDepth(depth);	
		mimic.scaleStrengthWithDepth(depth);	
		mimic.scaleDexterityWithDepth(depth);	
		mimic.scaleIntelligenceWithDepth(depth);
		mimic.hide();
		if(addToWorld > 0) {
			world.addAtEmptyLocation(mimic, depth);
		}else {
			
		}
		return mimic;
		
	}
	
	public Creature newIceWall(int depth, Creature player, int addToWorld) {
		//world, name, glyph, color, max health, max mana, base armor class, strength, dexterity, intelligence, vision range, inventory size (max 20)
		Creature icewall = new Creature(world, "Ice Wall", (char)177, ExtraColors.water, 15, 10, 16, 16, 1, 1, 1, 20);
		icewall.setID(14);
		new ChestAI(icewall, this, this.world);
		icewall.setImmuneFrostDamage(true);
		icewall.modifyNoCorpse(1);
		icewall.modifyNoXP(1);
		icewall.creatureTypes.add("Elemental");
		if(addToWorld > 0) {
			world.addAtEmptyLocation(icewall, depth);
		}else {
			
		}
		return icewall;
		
	}
	
	// marker
	
	public Creature newMarker(int depth, Creature player, int addToWorld) {
		// world, glyph, color, max health, base attack, base defense, vision range, name, inventory size (max 20), max mana, fire defense, ice defense, shock defense
		Creature marker = new Creature(world, " ", 'M', ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(marker, this, this.world);
		
		if(addToWorld > 0) {
			world.addAtEmptyLocation(marker, depth);
			marker.addEffect(randomStructure(player));
			//marker.addEffect(generatePit(player));
		}else {
			
		}
		return marker;
		
	}
	
	public Creature newTileSpell(int depth, Creature player, int addToWorld) {
		// world, glyph, color, max health, base attack, base defense, vision range, name, inventory size (max 20), max mana, fire defense, ice defense, shock defense
		Creature marker = new Creature(world, " ", 'T', ExtraColors.brown, 1, 1, 10, 1, 1, 1, 1, 1);
		new ChestAI(marker, this, this.world);
		marker.modifyFlying(1);
		marker.modifyIsTileSpell(true);
		marker.modifyNoCorpse(1);
		marker.modifyNoXP(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(marker, depth);
			//marker.addEffect(randomStructure(player));
			//marker.addEffect(generatePit(player));
		}else {
			
		}
		return marker;
		
	}
	
	//items
	// glyph, color, name, appearance
	
	//items current ID max: 65
	public int idCount = 0;
	public void iterateIDCount() {
		idCount++;
	}
	
	public Item newRock(int depth, int addToWorld) {
		Item rock = new Item(',', AsciiPanel.yellow, "Rock", "Rock");
		rock.setIsStackable(true);
		rock.setID(0);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(rock, depth);
		}else {
			
		}
		return rock;
		
	}
	
	public Item newRations(int depth, int addToWorld) {
		Item item = new Item((char)233, ExtraColors.paper, "Ration of Food", "Ration of Food");
		item.modifyFoodValue(300);
		item.setIsStackable(true);
		item.setBaseGoldValue(5);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(1);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
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
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newIronKey(int depth, int addToWorld) {
		Item key = new Item((char)157, AsciiPanel.white, "Iron Key", null);
		key.setIsIronKey(true);
		key.setKeyDepth(depth);
		//key.modifyIsStackable(1);
		key.setID(3);
		key.changeName(String.format("Iron Key (from Depth %d)", depth+1));
		if(addToWorld > 0) {
			world.addAtEmptyLocation(key, depth);
		}else {
			
		}
		return key;
		
	}
	
	public Item newVictoryItem(int depth, int addToWorld) {
		Item item = new Item('*', AsciiPanel.brightWhite, "Ancient Axe of the Dwarflords", "Ancient Axe of the Dwarflords");
		//item.modifyIsStackable(1);
		item.setID(4);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	//ammunition
	public Item newArrows(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtraColors.white, "Arrow", "Arrow");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsArrowAmmunition(true);
		item.setEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d12());
		item.setID(5);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newBolts(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtraColors.white, "Crossbow Bolt", "Crossbow Bolt");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsBoltAmmunition(true);
		item.setEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d8());
		item.setID(6);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPowder(int depth, int addToWorld) {
		Item item = new Item((char)24, ExtraColors.white, "Flintlock Bullet", "Flintlock Bullet");
		item.setIsAmmunition(true);
		item.modifyAmmunitionAmount(10);
		item.setIsPowderAmmunition(true);
		item.setEquippable(true);
		item.setIsStackable(true);
		item.modifyStackAmount(ExtraMaths.d6());
		item.setID(7);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	//traps
	public Item newFireTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.orange, "Fire Trap", "Fire Trap");
		item.setIsTrap(true);
		item.modifyThrownDamageDice(-1);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(effectFactory.fireball());
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
		
	}
	
	public Item newFrostTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.water, "Frostbite Trap", "Frostbite Trap");
		item.setIsTrap(true);
		item.modifyThrownDamageDice(-1);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(effectFactory.frozen());
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
		
	}
	
	public Item newShockTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.brightCyan, "Lightning Trap", "Lightning Trap");
		item.setIsTrap(true);
		item.modifyThrownDamageDice(-1);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(effectFactory.electrified());
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
		
	}
	
	public Item newBlinkTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.pink, "Blink Trap", "Blink Trap");
		item.setIsTrap(true);
		item.modifyThrownDamageDice(-1);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(effectFactory.blink());
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
		
	}
	
	public Item newSummonTrap(int depth, int addToWorld) {
		Item item = new Item('#', ExtraColors.brightWhite, "Summoning Trap", "Summoning Trap");
		item.setIsTrap(true);
		item.modifyThrownDamageDice(-1);
		item.setColor(ExtraColors.trap);
		item.changeGlyph((char)250);
		item.setQuaffEffect(new Effect(1, null, 0, null) {
			public void start(Creature creature){
                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) != null) {
                            continue;
                        }
                        Creature bat = newBat(0, 0);
                        if (!bat.canEnter(nx, ny, creature.z)){
                            world.remove(bat);
                            continue;
                        }
                        
                        if (creature.creature(nx, ny, creature.z) != null){
                            world.remove(bat);
                            continue;
                        }
                        
                        if ((int)(Math.random()*10) < 5){
                            world.remove(bat);
                            continue;
                        }
                        
                        bat.x = nx;
                        bat.y = ny;
                        bat.z = creature.z;
                        
                        creature.summon(bat);
                    }
                }
            }
		});
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
		
	}
	
	//weapons
	public Item newDagger(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Dagger", null);
		item.modifyDamageDice(4);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsThrownWeapon(true);
		item.modifyThrownDamageDice(4);
		item.setIsFinesse(true);
		item.setBaseGoldValue(20);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(8);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newClub(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Club", null);
		item.modifyDamageDice(4);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setBaseGoldValue(10);
		//item.modifyIsSimple(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(9);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newGreatclub(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Greatclub", null);
		item.modifyDamageDice(8);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(20);
		//item.modifyIsSimple(1);
		//item.modifySkillRestriction(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(10);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newHandaxe(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Handaxe", null);
		item.modifyDamageDice(6);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsThrownWeapon(true);
		item.modifyThrownDamageDice(6);
		item.setBaseGoldValue(50);
		//item.modifyIsSimple(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(11);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newLightHammer(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Light Hammer", null);
		item.modifyDamageDice(4);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsThrownWeapon(true);
		item.modifyThrownDamageDice(4);
		item.setBaseGoldValue(20);
		//item.modifyIsSimple(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(12);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newMace(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Mace", null);
		item.modifyDamageDice(6);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setBaseGoldValue(50);
		//item.modifyIsMartial(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(13);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newSickle(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Sickle", null);
		item.modifyDamageDice(4);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setBaseGoldValue(10);
		//item.modifyIsSimple(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(14);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newStaff(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Quarterstaff", null);
		item.modifyDamageDice(6);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsVersatile(true);
		item.modifyVersatileDamageDice(8);
		item.setBaseGoldValue(20);
		//item.modifyIsSimple(1);
		//item.modifySkillRestriction(2);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(15);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newSpear(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Spear", null);
		item.modifyDamageDice(6);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsVersatile(true);
		item.modifyVersatileDamageDice(8);
		item.setIsThrownWeapon(true);
		item.modifyThrownDamageDice(6);
		item.setBaseGoldValue(10);
		//item.modifyIsSimple(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(16);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	//martial weapons
	public Item newSword(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Shortsword", null);
		item.modifyDamageDice(6);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		//item.modifyIsMartial(1);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(17);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newRapier(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Rapier", null);
		item.modifyDamageDice(8);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsFinesse(true);
		item.setBaseGoldValue(250);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(18);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newLongsword(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Longsword", null);
		item.modifyDamageDice(8);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsVersatile(true);
		item.modifyVersatileDamageDice(10);
		item.setBaseGoldValue(150);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(19);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newBattleaxe(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Battleaxe", null);
		item.modifyDamageDice(8);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsVersatile(true);
		item.modifyVersatileDamageDice(10);
		item.setBaseGoldValue(100);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(4);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(20);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newWarhammer(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Warhammer", null);
		item.modifyDamageDice(8);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsVersatile(true);
		item.modifyVersatileDamageDice(10);
		item.setBaseGoldValue(150);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(4);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(21);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newGreatsword(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Greatsword", null);
		item.modifyDamageDice(12);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(500);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(5);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(22);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newMaul(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Maul", null);
		item.modifyDamageDice(12);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(100);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(4);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(23);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newHalberd(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Halberd", null);
		item.modifyDamageDice(10);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(200);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(4);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(24);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newMorningstar(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Morningstar", null);
		item.modifyDamageDice(8);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setBaseGoldValue(150);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(25);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newGlaive(int depth, int addToWorld) {
		Item item = new Item(')', AsciiPanel.brightWhite, "Glaive", null);
		item.modifyDamageDice(10);
		item.setIsWeapon(true);
		item.setIsMeleeWeapon(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(200);
		//item.modifyIsMartial(1);
		//item.modifySkillRestriction(4);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(26);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	
	
	//ranged weapons
	public Item newPistol(int depth, int addToWorld) {
		Item item = new Item('}', AsciiPanel.brightWhite, "Flintlock Pistol", null);
		item.modifyDamageDice(1);
		item.modifyRangedDamageDice(10);
		item.setIsWeapon(true);
		item.setIsRangedWeapon(true);
		item.setUsesPowderAmmunition(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(750);
		//item.modifySkillRestriction(5);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(27);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newCannon(int depth, int addToWorld) {
		Item item = new Item('}', AsciiPanel.brightWhite, "Flintlock Caliver", null);
		item.modifyDamageDice(1);
		item.modifyRangedDamageDice(12);
		item.setIsWeapon(true);
		item.setIsRangedWeapon(true);
		item.setUsesPowderAmmunition(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(1000);
		//item.modifySkillRestriction(6);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(28);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newBow(int depth, int addToWorld) {
		Item item = new Item('}', AsciiPanel.brightWhite, "Shortbow", null);
		item.modifyDamageDice(1);
		item.modifyRangedDamageDice(6);
		item.setIsWeapon(true);
		item.setIsRangedWeapon(true);
		item.setUsesArrowAmmunition(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(29);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newLongbow(int depth, int addToWorld) {
		Item item = new Item('}', AsciiPanel.brightWhite, "Longbow", null);
		item.modifyDamageDice(1);
		item.modifyRangedDamageDice(8);
		item.setIsWeapon(true);
		item.setIsRangedWeapon(true);
		item.setUsesArrowAmmunition(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(500);
		//item.modifySkillRestriction(2);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(30);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newCrossbow(int depth, int addToWorld) {
		Item item = new Item('}', AsciiPanel.brightWhite, "Light Crossbow", null);
		item.modifyDamageDice(1);
		item.modifyRangedDamageDice(8);
		item.setIsWeapon(true);
		item.setIsRangedWeapon(true);
		item.setUsesBoltAmmunition(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(250);
		//item.modifySkillRestriction(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(31);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newHeavyCrossbow(int depth, int addToWorld) {
		Item item = new Item('}', AsciiPanel.brightWhite, "Heavy Crossbow", null);
		item.modifyDamageDice(1);
		item.modifyRangedDamageDice(10);
		item.setIsWeapon(true);
		item.setIsRangedWeapon(true);
		item.setUsesBoltAmmunition(true);
		item.setEquippable(true);
		item.setIsTwoHanded(true);
		item.setBaseGoldValue(500);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(32);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	//armor
	public Item newPaddedArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Padded Armor", null);
		//item.modifyDefenseValue(0.1);
		item.setIsArmor(true);
		item.modifyArmorClass(11);
		item.setIsLightArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(50);
		//item.modifyIsFortitude(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(33);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newLeatherArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Leather Armor", null);
		//item.modifyDefenseValue(0.2);
		item.setIsArmor(true);
		item.modifyArmorClass(11);
		item.setIsLightArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(100);
		//item.modifyIsFortitude(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(34);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newStuddedLeatherArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Studded Leather Armor", null);
		//item.modifyDefenseValue(0.2);
		item.setIsArmor(true);
		item.modifyArmorClass(12);
		item.setIsLightArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(450);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(2);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(35);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newHideArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Hide Armor", null);
		//item.modifyDefenseValue(0.2);
		item.setIsArmor(true);
		item.modifyArmorClass(12);
		item.setIsMediumArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(100);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(36);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newChainmailArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Chainmail Tunic", null);
		//item.modifyDefenseValue(0.3);
		item.setIsArmor(true);
		item.modifyArmorClass(13);
		item.setIsMediumArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(500);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(37);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newScaleArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Scale Mail", null);
		//item.modifyDefenseValue(0.4);
		item.setIsArmor(true);
		item.modifyArmorClass(14);
		item.setIsMediumArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(500);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(4);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(38);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newBreastplate(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Breastplate", null);
		//item.modifyDefenseValue(0.4);
		item.setIsArmor(true);
		item.modifyArmorClass(14);
		item.setIsMediumArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(800);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(4);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(39);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newHalfPlate(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Half-plate Armor", null);
		//item.modifyDefenseValue(0.4);
		item.setIsArmor(true);
		item.modifyArmorClass(15);
		item.setIsMediumArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(1500);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(5);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(40);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPlateArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Plate Armor", null);
		//item.modifyDefenseValue(0.5);
		item.setIsArmor(true);
		item.modifyArmorClass(18);
		item.setIsHeavyArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(3000);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(8);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(41);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	//adjust elemental damage on traps/wands, merchants
	public Item newRingMail(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Ringmail Armor", null);
		//item.modifyDefenseValue(0.5);
		item.setIsArmor(true);
		item.modifyArmorClass(14);
		item.setIsHeavyArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(300);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(42);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newChainArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Chainmail Armor", null);
		//item.modifyDefenseValue(0.5);
		item.setIsArmor(true);
		item.modifyArmorClass(16);
		item.setIsHeavyArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(750);
		item.setCurrentGoldValue(item.baseGoldValue());
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(5);
		item.setID(43);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newSplintArmor(int depth, int addToWorld) {
		Item item = new Item((char)203, AsciiPanel.brightWhite, "Splint Armor", null);
		//item.modifyDefenseValue(0.5);
		item.setIsArmor(true);
		item.modifyArmorClass(17);
		item.setIsHeavyArmor(true);
		item.setEquippable(true);
		item.setBaseGoldValue(1200);
		item.setCurrentGoldValue(item.baseGoldValue());
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(7);
		item.setID(44);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	//shields
	public Item newRoundShield(int depth, int addToWorld) {
		Item item = new Item((char)232, AsciiPanel.brightWhite, "Round Shield", null);
		//item.modifyDefenseValue(0.15);
		item.setIsShield(true);
		item.setIsLightArmor(true);
		item.modifyArmorClass(2);
		item.setEquippable(true);
		item.setBaseGoldValue(100);
		//item.modifyIsFortitude(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(45);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newKiteShield(int depth, int addToWorld) {
		Item item = new Item((char)232, AsciiPanel.brightWhite, "Kite Shield", null);
		//item.modifyDefenseValue(0.3);
		item.setIsShield(true);
		item.setIsMediumArmor(true);
		item.modifyArmorClass(3);
		item.setEquippable(true);
		item.setBaseGoldValue(200);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(46);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newTowerShield(int depth, int addToWorld) {
		Item item = new Item((char)232, AsciiPanel.brightWhite, "Tower Shield", null);
		//item.modifyDefenseValue(0.45);
		item.setIsShield(true);
		item.setIsHeavyArmor(true);
		item.setIsTowerShield(true);
		item.modifyArmorClass(4);
		item.setEquippable(true);
		item.setBaseGoldValue(400);
		//item.modifyIsFortitude(1);
		//item.modifySkillRestriction(6);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(47);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	//rings
	public Item newStrengthRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(0);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Strength", appearance);
		item.modifyStrength(2);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(48);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newDefenseRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(1);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Shielding", appearance);
		item.modifyArmorClass(2);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(49);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newFireDefenseRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(2);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Fire Resistance", appearance);
		item.modifySaveBonusFire(5);
		item.setResistsFireDamage(true);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(50);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newIceDefenseRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(3);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Frost Resistance", appearance);
		item.modifySaveBonusFrost(5);
		item.setResistsFrostDamage(true);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(51);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newShockDefenseRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(4);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Shock Resistance", appearance);
		item.modifySaveBonusShock(5);
		item.setResistsShockDamage(true);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(52);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPoisonDefenseRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(5);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Poison Resistance", appearance);
		item.modifySaveBonusPoison(5);
		item.setResistsPoisonDamage(true);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(53);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newElementDefenseRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(6);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Elemental Resistance", appearance);
		item.modifySaveBonusFire(2);
		item.modifySaveBonusFrost(2);
		item.modifySaveBonusShock(2);
		item.setResistsFireDamage(true);
		item.setResistsFrostDamage(true);
		item.setResistsShockDamage(true);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(54);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newVisionRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(7);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Awareness", appearance);
		item.modifyVisionRadius(4);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(55);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newDexterityRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(8);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Dexterity", appearance);
		item.modifyDexterity(2);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(56);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
        return item;
	}
	
	public Item newIntelligenceRing(int depth, int addToWorld) {
		String appearance = ringAppearances.get(9);
		Item item = new Item((char)9, ringColors.get(appearance), "Ring of Intelligence", appearance);
		item.modifyIntelligence(2);
		item.setIsRing(true);
		item.setEquippable(true);
		item.setBaseGoldValue(250);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(57);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
        return item;
	}
	
	//potions
	
	public Item newPotionOfHealing(int depth, int addToWorld) {
		Item item = new Potion(this, (char)13, "Potion of Healing", 0, "Healing", effectFactory.maxHealth(), 100, 58);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	/*public Item newPotionOfHealing(int depth, int addToWorld) {
		String appearance = potionAppearances.get(0);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("healing");
		Item item = new Item((char)13, description.getColor(), "Potion of Healing", appearance);
		item.setQuaffEffect(effectFactory.maxHealth());
		item.setPotionName("Healing");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(58);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}*/
	
	public Item newPotionOfMana(int depth, int addToWorld) {
		String appearance = potionAppearances.get(1);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("mana");
		Item item = new Item((char)13, description.getColor(), "Potion of Mana", appearance);
		item.setQuaffEffect(effectFactory.maxMana());
		item.setPotionName("Mana Restoration");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(59);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfPoison(int depth, int addToWorld) {
		String appearance = potionAppearances.get(2);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("poison");
		Item item = new Item((char)13, description.getColor(), "Potion of Poison", appearance);
		item.setQuaffEffect(effectFactory.poisoned());
		item.setPotionName("Poison");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(60);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfGiantStrength(int depth, int addToWorld) {
		String appearance = potionAppearances.get(3);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("giant strength");
		Item item = new Item((char)13, description.getColor(), "Potion of Giant Strength", appearance);
		item.setQuaffEffect(effectFactory.giantStrength());
		item.setPotionName("Giant Strength");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(61);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfInvisibility(int depth, int addToWorld) {
		String appearance = potionAppearances.get(4);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("invisibility");
		Item item = new Item((char)13, description.getColor(), "Potion of Invisibility", appearance);
		item.setQuaffEffect(effectFactory.invisible());
		item.setPotionName("Invisibility");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(62);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfParalysis(int depth, int addToWorld) {
		String appearance = potionAppearances.get(5);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("paralysis");
		Item item = new Item((char)13, description.getColor(), "Potion of Paralysis", appearance);
		item.setQuaffEffect(effectFactory.paralyzed());
		item.setPotionName("Paralysis");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(63);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfCausticGas(int depth, int addToWorld) {
		String appearance = potionAppearances.get(6);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("caustic gas");
		Item item = new Item((char)13, description.getColor(), "Potion of Caustic Gas", appearance);
		item.setQuaffEffect(effectFactory.causticVapour());
		item.setPotionName("Caustic Cloud");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(64);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfRestoration(int depth, int addToWorld) {
		String appearance = potionAppearances.get(7);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("restoration");
		Item item = new Item((char)13, description.getColor(), "Potion of Restoration", appearance);
		item.setQuaffEffect(effectFactory.restoration());
		item.setPotionName("Restoration");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(65);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfMindVision(int depth, int addToWorld) {
		String appearance = potionAppearances.get(8);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("mind vision");
		Item item = new Item((char)13, description.getColor(), "Potion of Mind Vision", appearance);
		item.setQuaffEffect(effectFactory.mindVision());
		item.setPotionName("Mind Vision");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(66);
		item.quaffEffect().setDuration(20);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfOvergrowth(int depth, int addToWorld) {
		String appearance = potionAppearances.get(9);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("overgrowth");
		Item item = new Item((char)13, description.getColor(), "Potion of Overgrowth", appearance);
		item.setQuaffEffect(effectFactory.overgrow());
		item.setPotionName("Overgrowth");
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setIsStackable(true);
		item.setID(67);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfLiquidFlame(int depth, int addToWorld) {
		String appearance = potionAppearances.get(10);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("combustion");
		Item item = new Item((char)13, description.getColor(), "Potion of Combustion", appearance);
		item.setQuaffEffect(effectFactory.fireball());
		item.setPotionName("Combustion Cloud");
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setIsStackable(true);
		item.setID(68);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newPotionOfLevitation(int depth, int addToWorld) {
		String appearance = potionAppearances.get(11);
		Description description = potionColors.get(appearance);
		description.setPotionEffectName("levitation");
		Item item = new Item((char)13, description.getColor(), "Potion of Levitation", appearance);
		item.setQuaffEffect(effectFactory.levitating());
		item.setPotionName("Levitation");
		item.setIsStackable(true);
		item.setBaseGoldValue(100);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setDescription(description);
		item.setID(69);
		item.quaffEffect().setDuration(12);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	//scrolls

	public Item newScrollOfMagicMapping(int depth, Creature player, int addToWorld) {
		String appearance = scrollAppearances.get(0);
		Item item = new Item((char)247, ExtraColors.paper, "Scroll of Magic Mapping", appearance);
		item.setIsScroll(true);								//2
		item.addWrittenSpell(spellFactory.magicMappingScroll(player));
		item.setIsStackable(true);
		item.setBaseGoldValue(150);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(70);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newScrollOfIdentify(int depth, Creature player, int addToWorld) {
		String appearance = scrollAppearances.get(1);
		Item item = new Item((char)247, ExtraColors.paper, "Scroll of Identify", appearance);
		item.setIsScroll(true);
		item.addWrittenSpell(spellFactory.identifyScroll(player));
		item.setIsStackable(true);
		item.setBaseGoldValue(150);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(71);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newScrollOfSummonMonsters(int depth, Creature player, int addToWorld) {
		String appearance = scrollAppearances.get(2);
		Item item = new Item((char)247, ExtraColors.paper, "Scroll of Summon Monsters", appearance);
		item.setIsScroll(true);
		item.addWrittenSpell(spellFactory.summonMonstersScroll(player));
		item.setIsStackable(true);
		item.setBaseGoldValue(150);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(72);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
		
	}
	
	public Item newScrollOfUpgrade(int depth, Creature player, int addToWorld) {
		String appearance = scrollAppearances.get(3);
		Item item = new Item((char)247, ExtraColors.paper, "Scroll of Upgrade", appearance);
		item.setIsScroll(true);
		item.addWrittenSpell(spellFactory.upgradeScroll(player));
		item.setIsStackable(true);
		item.setBaseGoldValue(150);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(73);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newScrollOfRemoveCurse(int depth, Creature player, int addToWorld) {
		String appearance = scrollAppearances.get(4);
		Item item = new Item((char)247, ExtraColors.paper, "Scroll of Remove Curse", appearance);
		item.setIsScroll(true);
		item.addWrittenSpell(spellFactory.removeCurseScroll(player));
		item.setIsStackable(true);
		item.setBaseGoldValue(150);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(74);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newScrollOfEnchantment(int depth, Creature player, int addToWorld) {
		String appearance = scrollAppearances.get(5);
		Item item = new Item((char)247, ExtraColors.paper, "Scroll of Enchantment", appearance);
		item.setIsScroll(true);
		item.addWrittenSpell(spellFactory.enchantScroll(player));
		item.setIsStackable(true);
		item.setBaseGoldValue(150);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(75);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	
	public Item newScrollOfConfusion(int depth, Creature player, int addToWorld) {
		String appearance = scrollAppearances.get(6);
		Item item = new Item((char)247, ExtraColors.paper, "Scroll of Confusion", appearance);
		item.setIsScroll(true);
		item.addWrittenSpell(spellFactory.confuseScroll(player));
		item.setIsStackable(true);
		item.setBaseGoldValue(150);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(76);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
		return item;
	}
	

	
	//wands
	
	public Item newFireboltWand(int depth, Creature player, int addToWorld) {
		String appearance = wandAppearances.get(0);
		Item item = new Item((char)33, wandColors.get(appearance), "Wand of Firebolt", appearance);
		//item.modifyAttackValue(5);
		item.setIsWand(true);
		item.addWrittenSpell(spellFactory.firebolt(player));
		item.setBaseGoldValue(250);
		//item.modifyIsPyromancy(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		//item.setIsStackable(1);
		item.setID(78);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
        return item;
	}
	
	public Item newForceWand(int depth, Creature player, int addToWorld) {
		String appearance = wandAppearances.get(1);
		Item item = new Item((char)33, wandColors.get(appearance), "Wand of Force", appearance);
		//item.modifyAttackValue(5);
		item.setIsWand(true);
		item.addWrittenSpell(spellFactory.repel(player));
		//item.setIsStackable(1);
		item.setBaseGoldValue(250);
		//item.modifyIsEvocation(1);
		//item.modifySkillRestriction(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(79);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
        return item;
	}
	
	public Item newFreezingWand(int depth, Creature player, int addToWorld) {
		String appearance = wandAppearances.get(2);
		Item item = new Item((char)33, wandColors.get(appearance), "Wand of Freezing", appearance);
		//item.modifyAttackValue(5);
		item.setIsWand(true);
		item.addWrittenSpell(spellFactory.flashFreeze(player));
		//item.setIsStackable(1);
		item.setBaseGoldValue(250);
		//item.modifyIsCryomancy(1);
		//item.modifySkillRestriction(2);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(80);
		item.writtenSpells().get(0).makeEffectNegative();
		item.writtenSpells().get(0).makeEffectFrozen();
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
        return item;
	}
	
	public Item newLightningWand(int depth, Creature player, int addToWorld) {
		String appearance = wandAppearances.get(3);
		Item item = new Item((char)33, wandColors.get(appearance), "Wand of Lightning", appearance);
		//item.modifyAttackValue(5);
		item.setIsWand(true);
		item.addWrittenSpell(spellFactory.chainLightning(player));
		//item.setIsStackable(1);
		item.setBaseGoldValue(250);
		//item.modifyIsElectromancy(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(81);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
        return item;
	}
	
	public Item newMissileWand(int depth, Creature player, int addToWorld) {
		String appearance = wandAppearances.get(4);
		Item item = new Item((char)33, wandColors.get(appearance), "Wand of Magic Missile", appearance);
		//item.modifyAttackValue(5);
		item.setIsWand(true);
		item.addWrittenSpell(spellFactory.magicMissile(player));
		//item.setIsStackable(1);
		item.setBaseGoldValue(250);
		//item.modifyIsEvocation(1);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(82);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {
			
		}
        return item;
	}
	
	public Item newIceWallWand(int depth, Creature player, int addToWorld) {
		String appearance = wandAppearances.get(5);
		Item item = new Item((char)33, wandColors.get(appearance), "Wand of Ice Wall", appearance);
		//item.modifyAttackValue(5);
		item.setIsWand(true);
		item.addWrittenSpell(spellFactory.iceWall(player));
		//item.setIsStackable(1);
		item.setBaseGoldValue(250);
		//item.modifyIsCryomancy(1);
		//item.modifySkillRestriction(3);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(83);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {

		}
		return item;
	}
	
	public Item newFireboltBook(int depth, Creature player, int addToWorld) {
		String appearance = bookAppearances.get(5);
		Item item = new Item((char)8, bookColors.get(appearance), "Book of Firebolt", appearance);
		item.addWrittenSpell(spellFactory.firebolt(player));
		//item.setIsStackable(1);
		item.setBaseGoldValue(250);
		//item.modifyIsPyromancy(1);
		//item.modifySkillRestriction(1);
		item.setIsSpellbook(true);
		item.setCurrentGoldValue(item.baseGoldValue());
		item.setID(84);
		if(addToWorld > 0) {
			world.addAtEmptyLocation(item, depth);
		}else {

		}
		return item;
	}
	
	//enchantments
	public Effect newFireWeaponEnchantment() {
		Effect enchantment = effectFactory.ignited();
		enchantment.setDuration(8);
		enchantment.setEnchantmentName("Blazing ");
		return enchantment;
	}
	
	public Effect newFrostWeaponEnchantment() {
		Effect enchantment = effectFactory.frozen();
		enchantment.setEnchantmentName("Freezing ");
		return enchantment;
        
	}
	
	public Effect newShockWeaponEnchantment() {
		Effect enchantment = effectFactory.electrified();
		enchantment.setEnchantmentName("Shocking ");
		return enchantment;
	}
	
	public Effect newStunWeaponEnchantment() {
		Effect enchantment = effectFactory.paralyzed();
		enchantment.setEnchantmentName("Stunning ");
		return enchantment;
	}
	
	public Effect newBlinkWeaponEnchantment() {
		Effect enchantment = effectFactory.blink();
		enchantment.setEnchantmentName("Shifting ");
		return enchantment;
	}
	
	public Effect newShockArmorEnchantment() {
		Effect enchantment = effectFactory.arcWard();
		enchantment.setEnchantmentName(" of Arc Ward");
		return enchantment;
	}
	
	public Effect newStealthArmorEnchantment() {
		Effect enchantment = effectFactory.invisible();
		enchantment.setEnchantmentName(" of Transparency");
		return enchantment;
	}
	
	public Effect newFireArmorEnchantment() {
		Effect enchantment = effectFactory.magmaWard();
		enchantment.setEnchantmentName(" of Magma Ward");
		return enchantment;
	}
	
	public Effect newFrostArmorEnchantment() {
		Effect enchantment = effectFactory.frostWard();
		enchantment.setEnchantmentName(" of Chill Ward");
		return enchantment;
	}
	
	public Effect newBounceArmorEnchantment() {
		Effect enchantment = effectFactory.bounce();
		enchantment.setEnchantmentName(" of Force");
		return enchantment;
	}
	
	//curses
	public Effect manaCurse() {
		Effect curse = effectFactory.devoured();
		return curse;
	}
	
	public Effect confuseCurse() {
		Effect curse = effectFactory.confused();
		return curse;
	}
	
	public Effect blindCurse() {
		Effect curse = effectFactory.blinded();
		return curse;
	}
	
	//structures
	
	public Effect generateSmallCell(Creature player) {
		Effect generate = new Effect(7, "Arc Ward", 0, null){
			public void start(Creature creature){
				if(creature.y() == 60 || creature.y() == 59) {
					creature.moveBy(0, -2, 0, false);
				}
				if(creature.y() == 0) {
					creature.moveBy(0, 1, 0, false);
				}

                for (int ox = -1; ox < 2; ox++){
                    for (int oy = -1; oy < 2; oy++){
                        int nx = creature.x + ox;
                        int ny = creature.y + oy;
                        if (ox == 0 && oy == 0 || creature.creature(nx, ny, creature.z) != null) {
                            continue;
                        }
                    }
                }
                int check = 0;
            	
            	 for (int ox = -1; ox < 2; ox++){
                     for (int oy = -1; oy < 2; oy++){
                         int nx = creature.x + ox;
                         int ny = creature.y + oy;
                         if (ox == 0 && oy == 0) {
                        	 
                             
                         }
                         if(ox == -1 && oy == -1) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_NW);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x()-1, creature.y()-1, creature.z(), Tile.BARS_NW);
                         }
                         if(ox == 1 && oy == -1) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_NE);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x()+1, creature.y()-1, creature.z(), Tile.BARS_NE);
                         }
                         if(ox == -1 && oy == 1) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_SW);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x()-1, creature.y()+1, creature.z(), Tile.BARS_SW);
                         }
                         if(ox == 1 && oy == 1) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_SE);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x()+1, creature.y()+1, creature.z(), Tile.BARS_SE);
                         }
                         if(ox == -1 && oy == 0) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_VERTICAL);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x()-1, creature.y(), creature.z(), Tile.BARS_VERTICAL);
                         }
                         if(ox == 1 && oy == 0) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_VERTICAL);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x()+1, creature.y(), creature.z(), Tile.BARS_VERTICAL);
                         }
                         if(ox == 0 && oy == -1) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_HORIZONTAL);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x(), creature.y()-1, creature.z(), Tile.BARS_HORIZONTAL);
                         }
                         if(ox == 0 && oy == 1) {
                        	 if(creature.item(nx, ny, creature.z()) != null) {
                        		 creature.world().remove(nx, ny, creature.z());
                        	 }
                        	 if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
                        		 //creature.world().changeTile(nx, ny, creature.z(), Tile.BARS_DOOR);
                        		 check += 1;
                        	 }
                        	 //creature.world().changeTile(creature.x(), creature.y()+1, creature.z(), Tile.BARS_DOOR);
                         }
                     }
                    
                 }
            	 if(check >= 8) {
            		 creature.world().changeTile(creature.x()-1, creature.y()-1, creature.z(), Tile.BARS_NW);
            		 creature.world().changeTile(creature.x()+1, creature.y()-1, creature.z(), Tile.BARS_NE);
            		 creature.world().changeTile(creature.x()-1, creature.y()+1, creature.z(), Tile.BARS_SW);
            		 creature.world().changeTile(creature.x()+1, creature.y()+1, creature.z(), Tile.BARS_SE);
            		 creature.world().changeTile(creature.x()-1, creature.y(), creature.z(), Tile.BARS_VERTICAL);
            		 creature.world().changeTile(creature.x()+1, creature.y(), creature.z(), Tile.BARS_VERTICAL);
            		 creature.world().changeTile(creature.x(), creature.y()-1, creature.z(), Tile.BARS_HORIZONTAL);
            		 creature.world().changeTile(creature.x(), creature.y()+1, creature.z(), Tile.BARS_DOOR);
            		 newIronKey(creature.z(), 1);
            		 if(creature.item(creature.x(), creature.y(), creature.z()) != null) {
                		 creature.world().remove(creature.x(), creature.y(), creature.z());
                	 }
            		 creature.world().addAtEmptySpace(randomMagicItem(creature.z(), player, 0), creature.x(), creature.y(), creature.z());
                 }
                world.remove(creature); 
            }
        };
		return generate;
	}
	
	public Effect generatePotionRoom(Creature player) {
		Effect generate = new Effect(7, "Arc Ward", 0, null){
			public void start(Creature creature){
				if(creature.y() == 60 || creature.y() == 59) {
					creature.moveBy(0, -2, 0, false);
				}
				if(creature.y() == 0) {
					creature.moveBy(0, 1, 0, false);
				}


				int check = 0;
				
				for (int ox = -2; ox < 3; ox++){
					for (int oy = -2; oy < 2; oy++){
						int nx = creature.x + ox;
						int ny = creature.y + oy;
						//if(creature.item(nx, ny, creature.z()) != null) {
						//creature.world().remove(nx, ny, creature.z());
						//}

						if(ox == -2 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()-2, creature.y()-2, creature.z(), Tile.BARS_NW);
						}
						if(ox == 2 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()+2, creature.y()-2, creature.z(), Tile.BARS_NE);
						}
						if(ox == -2 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()-2, creature.y()+1, creature.z(), Tile.BARS_SW);
						}
						if(ox == 2 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()+2, creature.y()+1, creature.z(), Tile.BARS_SE);
						}
						if(ox == -2 && oy == 0) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()-2, creature.y(), creature.z(), Tile.BARS_VERTICAL);
						}
						if(ox == 2 && oy == 0) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()+2, creature.y(), creature.z(), Tile.BARS_VERTICAL);
						}
						if(ox == -2 && oy == -1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()-2, creature.y()-1, creature.z(), Tile.BARS_VERTICAL);
						}
						if(ox == 2 && oy == -1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()+2, creature.y()-1, creature.z(), Tile.BARS_VERTICAL);
						}
						if(ox == 0 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x(), creature.y()-2, creature.z(), Tile.BARS_HORIZONTAL);
						}
						if(ox == 1 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()+1, creature.y()-2, creature.z(), Tile.BARS_HORIZONTAL);
						}
						if(ox == -1 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()-1, creature.y()-2, creature.z(), Tile.BARS_HORIZONTAL);
						}
						if(ox == 1 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()+1, creature.y()+1, creature.z(), Tile.BARS_HORIZONTAL);
						}
						if(ox == -1 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x()-1, creature.y()+1, creature.z(), Tile.BARS_HORIZONTAL);
						}
						if(ox == 0 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								check += 1;
							}
							//creature.world().changeTile(creature.x(), creature.y()+1, creature.z(), Tile.BARS_DOOR);
						}
						if(ox == 0 && oy == 0) {

						}

					}
				}

				world.remove(creature);
				if(check >= 14) {
					creature.world().changeTile(creature.x()-2, creature.y()-2, creature.z(), Tile.BARS_NW);
					creature.world().changeTile(creature.x()+2, creature.y()-2, creature.z(), Tile.BARS_NE);
					creature.world().changeTile(creature.x()-2, creature.y()+1, creature.z(), Tile.BARS_SW);
					creature.world().changeTile(creature.x()+2, creature.y()+1, creature.z(), Tile.BARS_SE);
					creature.world().changeTile(creature.x()-2, creature.y(), creature.z(), Tile.BARS_VERTICAL);
					creature.world().changeTile(creature.x()+2, creature.y(), creature.z(), Tile.BARS_VERTICAL);
					creature.world().changeTile(creature.x()-2, creature.y()-1, creature.z(), Tile.BARS_VERTICAL);
					creature.world().changeTile(creature.x()+2, creature.y()-1, creature.z(), Tile.BARS_VERTICAL);
					creature.world().changeTile(creature.x(), creature.y()-2, creature.z(), Tile.BARS_HORIZONTAL);
					creature.world().changeTile(creature.x()+1, creature.y()-2, creature.z(), Tile.BARS_HORIZONTAL);
					creature.world().changeTile(creature.x()-1, creature.y()-2, creature.z(), Tile.BARS_HORIZONTAL);
					creature.world().changeTile(creature.x()+1, creature.y()+1, creature.z(), Tile.BARS_HORIZONTAL);
					creature.world().changeTile(creature.x()-1, creature.y()+1, creature.z(), Tile.BARS_HORIZONTAL);
					creature.world().changeTile(creature.x(), creature.y()+1, creature.z(), Tile.BARS_DOOR);
					if(creature.item(creature.x(), creature.y()-1, creature.z()) != null) {
						creature.world().remove(creature.x(), creature.y()-1, creature.z());
					}
					if(creature.item(creature.x()-1, creature.y()-1, creature.z()) != null) {
						creature.world().remove(creature.x()-1, creature.y()-1, creature.z());
					}
					if(creature.item(creature.x()+1, creature.y()-1, creature.z()) != null) {
						creature.world().remove(creature.x()+1, creature.y()-1, creature.z());
					}
					if(creature.item(creature.x(), creature.y(), creature.z()) != null) {
						creature.world().remove(creature.x(), creature.y(), creature.z());
					}
					if(creature.item(creature.x()-1, creature.y(), creature.z()) != null) {
						creature.world().remove(creature.x()-1, creature.y(), creature.z());
					}
					if(creature.item(creature.x()+1, creature.y(), creature.z()) != null) {
						creature.world().remove(creature.x()+1, creature.y(), creature.z());
					}
					creature.world().addAtEmptySpace(randomPotion(creature.z(), 0), creature.x()+1, creature.y()-1, creature.z());
					creature.world().addAtEmptySpace(randomPositivePotion(creature.z(), 0), creature.x(), creature.y()-1, creature.z());
					creature.world().addAtEmptySpace(randomPotion(creature.z(), 0), creature.x()-1, creature.y()-1, creature.z());
					newIronKey(creature.z(), 1);
				}

			}
		};
		return generate;
	}
	
	public Effect generateGrassPatch(Creature player) {
		Effect generate = new Effect(1, "Arc Ward", 0, null){
			public void start(Creature creature){
				for (int ox = -2; ox < 3; ox++){
					for (int oy = -2; oy < 3; oy++){
						int nx = creature.x + ox;
						int ny = creature.y + oy;
						if(ox == 0 && oy == 0) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 0 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 0 && oy == 2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 0 && oy == -1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 0 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 1 && oy == 0) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 1 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 1 && oy == 2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 1 && oy == -1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 1 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 2 && oy == 0) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 2 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 2 && oy == 2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 2 && oy == -1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == 2 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -1 && oy == 0) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -1 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -1 && oy == 2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -1 && oy == -1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -1 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -2 && oy == 0) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -2 && oy == 1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -2 && oy == 2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -2 && oy == -1) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						
						if(ox == -2 && oy == -2) {
							if(creature.item(nx, ny, creature.z()) != null) {
								creature.world().remove(nx, ny, creature.z());
							}
							if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
								if(ExtraMaths.d10() > 5) {
									creature.world().changeTile(nx, ny, creature.z(), Tile.GRASS_TALL);
								}
							}
						}
						

						
						
						
						
						
					}
				}
				world.remove(creature);


			}
		};
		return generate;
	}
	
	public Effect generatePit(Creature player) {
		Effect generate = new Effect(1, "Arc Ward", 0, null){
			public void start(Creature creature){
				if(creature.z() == world.depth()-1) {
					world.remove(creature);
				}else {
					for (int ox = -2; ox < 3; ox++){
						for (int oy = -2; oy < 3; oy++){
							int nx = creature.x + ox;
							int ny = creature.y + oy;
							if(ox == 0 && oy == 0) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 0 && oy == 1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 0 && oy == 2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 0 && oy == -1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 0 && oy == -2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 1 && oy == 0) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 1 && oy == 1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 1 && oy == 2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 1 && oy == -1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 1 && oy == -2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 2 && oy == 0) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 2 && oy == 1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 2 && oy == 2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 2 && oy == -1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == 2 && oy == -2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -1 && oy == 0) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -1 && oy == 1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -1 && oy == 2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -1 && oy == -1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -1 && oy == -2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -2 && oy == 0) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -2 && oy == 1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -2 && oy == 2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -2 && oy == -1) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}

							if(ox == -2 && oy == -2) {
								if(creature.item(nx, ny, creature.z()) != null) {
									creature.world().remove(nx, ny, creature.z());
								}
								if(creature.world().tile(nx, ny, creature.z()) == Tile.FLOOR) {
									if(ExtraMaths.d10() > 5) {
										creature.world().changeTile(nx, ny, creature.z(), Tile.PIT);
									}
								}
							}







						}
					}
					world.remove(creature);


				}
			}
		};
		return generate;
	}
	
	//generators
	public Effect randomStructure(Creature player) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return generateGrassPatch(player);
		case 2: return generateGrassPatch(player);
		case 3: return generateSmallCell(player);
		case 4: return generatePotionRoom(player);
		case 5: return generatePit(player);
		default: return generateGrassPatch(player);
		}
	}
	
	public Item randomWeapon(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 24)) {
		case 1: return newDagger(depth, addToWorld);
		case 2: return newClub(depth, addToWorld);
		case 3: return newGreatclub(depth, addToWorld);
		case 4: return newHandaxe(depth, addToWorld);
		case 5: return newLightHammer(depth, addToWorld);
		case 6: return newMace(depth, addToWorld);
		case 7: return newSickle(depth, addToWorld);
		case 8: return newStaff(depth, addToWorld);
		case 9: return newSpear(depth, addToWorld);
		case 10: return newSword(depth, addToWorld);
		case 11: return newRapier(depth, addToWorld);
		case 12: return newLongsword(depth, addToWorld);
		case 13: return newBattleaxe(depth, addToWorld);
		case 14: return newWarhammer(depth, addToWorld);
		case 15: return newGreatsword(depth, addToWorld);
		case 16: return newMaul(depth, addToWorld);
		case 17: return newHalberd(depth, addToWorld);
		case 18: return newMorningstar(depth, addToWorld);
		case 19: return newGlaive(depth, addToWorld);
		case 20: return newPistol(depth, addToWorld);
		case 21: return newBow(depth, addToWorld);
		case 22: return newLongbow(depth, addToWorld);
		case 23: return newCrossbow(depth, addToWorld);
		case 24: return newHeavyCrossbow(depth, addToWorld);
		default: return newDagger(depth, addToWorld);
		}
	}
	
	public Item randomMeleeWeapon(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 19)) {
		case 1: return newDagger(depth, addToWorld);
		case 2: return newClub(depth, addToWorld);
		case 3: return newGreatclub(depth, addToWorld);
		case 4: return newHandaxe(depth, addToWorld);
		case 5: return newLightHammer(depth, addToWorld);
		case 6: return newMace(depth, addToWorld);
		case 7: return newSickle(depth, addToWorld);
		case 8: return newStaff(depth, addToWorld);
		case 9: return newSpear(depth, addToWorld);
		case 10: return newSword(depth, addToWorld);
		case 11: return newRapier(depth, addToWorld);
		case 12: return newLongsword(depth, addToWorld);
		case 13: return newBattleaxe(depth, addToWorld);
		case 14: return newWarhammer(depth, addToWorld);
		case 15: return newGreatsword(depth, addToWorld);
		case 16: return newMaul(depth, addToWorld);
		case 17: return newHalberd(depth, addToWorld);
		case 18: return newMorningstar(depth, addToWorld);
		case 19: return newGlaive(depth, addToWorld);
		default: return newDagger(depth, addToWorld);
		}
	}
	
	public Item randomArmor(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return newPaddedArmor(depth, addToWorld);
		case 2: return newLeatherArmor(depth, addToWorld);
		case 3: return newStuddedLeatherArmor(depth, addToWorld);
		case 4: return newHideArmor(depth, addToWorld);
		case 5: return newChainmailArmor(depth, addToWorld);
		case 6: return newScaleArmor(depth, addToWorld);
		case 7: return newBreastplate(depth, addToWorld);
		case 8: return newHalfPlate(depth, addToWorld);
		case 9: return newPlateArmor(depth, addToWorld);
		case 10: return newRingMail(depth, addToWorld);
		case 11: return newChainArmor(depth, addToWorld);
		case 12: return newSplintArmor(depth, addToWorld);
		default: return newPaddedArmor(depth, addToWorld);
		}
	}
	
	public Item randomShield(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return newRoundShield(depth, addToWorld);
		case 2: return newTowerShield(depth, addToWorld);
		case 3: return newKiteShield(depth, addToWorld);
		default: return newRoundShield(depth, addToWorld);
		}
	}
	
	public Item randomMimicDrop(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return randomWeapon(depth, addToWorld);
		case 2: return randomArmor(depth, addToWorld);
		case 3: return randomShield(depth, addToWorld);
		default: return randomWeapon(depth, addToWorld);
		}
	}
	
	public Effect randomMeleeEnchantment() {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return newFireWeaponEnchantment();
		case 2: return newBlinkWeaponEnchantment();
		case 3: return newFrostWeaponEnchantment();
		case 4: return newShockWeaponEnchantment();
		case 5: return newStunWeaponEnchantment();
		default: return newFireWeaponEnchantment();
		}
	}
	
	public Effect randomArmorEnchantment() {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return newShockArmorEnchantment();
		case 2: return newFireArmorEnchantment();
		case 3: return newFrostArmorEnchantment();
		case 4: return newBounceArmorEnchantment();
		case 5: return newStealthArmorEnchantment();
		default: return newShockArmorEnchantment();
		}
	}
	
	public Effect randomCurse() {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return manaCurse();
		case 2: return confuseCurse();
		case 3: return blindCurse();
		default: return manaCurse();
		}
	}
	
	public Item randomTrap(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return newShockTrap(depth, addToWorld);
		case 2: return newFrostTrap(depth, addToWorld);
		case 3: return newSummonTrap(depth, addToWorld);
		case 4: return newBlinkTrap(depth, addToWorld);
		case 5: return newFireTrap(depth, addToWorld);
		default: return newFireTrap(depth, addToWorld);
		}
	}
	
	public Item randomAmmo(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 3)) {
		case 1: return newArrows(depth, addToWorld);
		case 2: return newBolts(depth, addToWorld);
		case 3: return newPowder(depth, addToWorld);
		default: return newArrows(depth, addToWorld);
		}
	}
	
	public Item randomFood(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return newRations(depth, addToWorld);
		case 2: return newRations(depth, addToWorld);
		case 3: return newRations(depth, addToWorld);
		case 4: return newRations(depth, addToWorld);
		case 5: return newPasty(depth, addToWorld);
		default: return newRations(depth, addToWorld);
		}
	}
	
	public Item randomMagicItem(int depth, Creature player, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 4)) {
		case 1: return randomPositivePotion(depth, addToWorld);
		case 2: return randomScroll(depth, player, addToWorld);
		case 3: return randomWand(depth, player, addToWorld);
		case 4: return randomRing(depth, addToWorld);
		default: return randomRing(depth, addToWorld);
		}
	}
	
	public Item randomWand(int depth, Creature player, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 6)) {
		case 1: return newForceWand(depth, player, addToWorld);
		case 2: return newFreezingWand(depth, player, addToWorld);
		case 3: return newLightningWand(depth, player, addToWorld);
		case 4: return newFireboltWand(depth, player, addToWorld);
		case 5: return newMissileWand(depth, player, addToWorld);
		case 6: return newIceWallWand(depth, player, addToWorld);
		default: return newFireboltWand(depth, player, addToWorld);
		}
	}
	
	public Item selectWand(int depth, Creature player, int addToWorld, int wandIndex) {
		switch(wandIndex) {
		case 1: return newForceWand(depth, player, addToWorld);
		case 2: return newFreezingWand(depth, player, addToWorld);
		case 3: return newLightningWand(depth, player, addToWorld);
		case 4: return newFireboltWand(depth, player, addToWorld);
		case 5: return newMissileWand(depth, player, addToWorld);
		case 6: return newIceWallWand(depth, player, addToWorld);
		default: return newFireboltWand(depth, player, addToWorld);
		}
	}
	
	public Item randomRing(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return newStrengthRing(depth, addToWorld);
		case 2: return newFireDefenseRing(depth, addToWorld);
		case 3: return newIceDefenseRing(depth, addToWorld);
		case 4: return newShockDefenseRing(depth, addToWorld);
		case 5: return newElementDefenseRing(depth, addToWorld);
		case 6: return newVisionRing(depth, addToWorld);
		case 7: return newDefenseRing(depth, addToWorld);
		case 8: return newPoisonDefenseRing(depth, addToWorld);
		case 9: return newDexterityRing(depth, addToWorld);
		case 10: return newIntelligenceRing(depth, addToWorld);
		default: return newDefenseRing(depth, addToWorld);
		}
	}
	
	public Item selectRing(int depth, int addToWorld, int ringIndex) {
		switch(ringIndex) {
		case 1: return newStrengthRing(depth, addToWorld);
		case 2: return newFireDefenseRing(depth, addToWorld);
		case 3: return newIceDefenseRing(depth, addToWorld);
		case 4: return newShockDefenseRing(depth, addToWorld);
		case 5: return newElementDefenseRing(depth, addToWorld);
		case 6: return newVisionRing(depth, addToWorld);
		case 7: return newDefenseRing(depth, addToWorld);
		case 8: return newPoisonDefenseRing(depth, addToWorld);
		case 9: return newDexterityRing(depth, addToWorld);
		case 10: return newIntelligenceRing(depth, addToWorld);
		default: return newDefenseRing(depth, addToWorld);
		}
	}
	
	public Item randomScroll(int depth, Creature player, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return newScrollOfIdentify(depth, player, addToWorld);
		case 2: return newScrollOfMagicMapping(depth, player, addToWorld);
		case 3: return newScrollOfSummonMonsters(depth, player, addToWorld);
		case 4: return newScrollOfUpgrade(depth, player, addToWorld);
		case 5: return newScrollOfRemoveCurse(depth, player, addToWorld);
		case 6: return newScrollOfEnchantment(depth, player, addToWorld);
		case 7: return newScrollOfConfusion(depth, player, addToWorld);
		default: return newScrollOfIdentify(depth, player, addToWorld);
		}
	}
	
	public Item selectScroll(int depth, Creature player, int addToWorld, int scrollIndex) {
		switch(scrollIndex) {
		case 1: return newScrollOfIdentify(depth, player, addToWorld);
		case 2: return newScrollOfMagicMapping(depth, player, addToWorld);
		case 3: return newScrollOfSummonMonsters(depth, player, addToWorld);
		case 4: return newScrollOfUpgrade(depth, player, addToWorld);
		case 5: return newScrollOfRemoveCurse(depth, player, addToWorld);
		case 6: return newScrollOfEnchantment(depth, player, addToWorld);
		case 7: return newScrollOfConfusion(depth, player, addToWorld);
		default: return newScrollOfIdentify(depth, player, addToWorld);
		}
	}
	
	public Item randomPotion(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 12)) {
		case 1: return newPotionOfPoison(depth, addToWorld);
		case 2: return newPotionOfGiantStrength(depth, addToWorld);
		case 3: return newPotionOfMana(depth, addToWorld);
		case 4: return newPotionOfInvisibility(depth, addToWorld);
		case 5: return newPotionOfParalysis(depth, addToWorld);
		case 6: return newPotionOfCausticGas(depth, addToWorld);
		case 7: return newPotionOfHealing(depth, addToWorld);
		case 8: return newPotionOfRestoration(depth, addToWorld);
		case 9: return newPotionOfMindVision(depth, addToWorld);
		case 10: return newPotionOfOvergrowth(depth, addToWorld);
		case 11: return newPotionOfLiquidFlame(depth, addToWorld);
		case 12: return newPotionOfLevitation(depth, addToWorld);
		default: return newPotionOfHealing(depth, addToWorld);
		}
	}
	
	public Item randomPositivePotion(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 7)) {
		case 1: return newPotionOfGiantStrength(depth, addToWorld);
		case 2: return newPotionOfMana(depth, addToWorld);
		case 3: return newPotionOfInvisibility(depth, addToWorld);
		case 4: return newPotionOfHealing(depth, addToWorld);
		case 5: return newPotionOfRestoration(depth, addToWorld);
		case 6: return newPotionOfMindVision(depth, addToWorld);
		case 7: return newPotionOfLevitation(depth, addToWorld);
		default: return newPotionOfHealing(depth, addToWorld);
		}
	}
	
	public Item randomNegativePotion(int depth, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 5)) {
		case 1: return newPotionOfPoison(depth, addToWorld);
		case 2: return newPotionOfParalysis(depth, addToWorld);
		case 3: return newPotionOfCausticGas(depth, addToWorld);
		case 4: return newPotionOfOvergrowth(depth, addToWorld);
		case 5: return newPotionOfLiquidFlame(depth, addToWorld);
		default: return newPotionOfPoison(depth, addToWorld);
		}
	}
	
	public Item selectPotion(int depth, int addToWorld, int potionIndex) {
		switch(potionIndex) {
		case 1: return newPotionOfPoison(depth, addToWorld);
		case 2: return newPotionOfGiantStrength(depth, addToWorld);
		case 3: return newPotionOfMana(depth, addToWorld);
		case 4: return newPotionOfInvisibility(depth, addToWorld);
		case 5: return newPotionOfParalysis(depth, addToWorld);
		case 6: return newPotionOfCausticGas(depth, addToWorld);
		case 7: return newPotionOfHealing(depth, addToWorld);
		case 8: return newPotionOfRestoration(depth, addToWorld);
		case 9: return newPotionOfMindVision(depth, addToWorld);
		case 10: return newPotionOfOvergrowth(depth, addToWorld);
		case 11: return newPotionOfLiquidFlame(depth, addToWorld);
		case 12: return newPotionOfLevitation(depth, addToWorld);
		default: return newPotionOfHealing(depth, addToWorld);
		}
	}
	
	public Creature randomLesserMonster(int depth, Creature player, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return newFungus(depth, addToWorld);
		case 2: return newFungus(depth, addToWorld);
		case 3: return newFungus(depth, addToWorld);
		case 4: return newFungus(depth, addToWorld);
		case 5: return newFungus(depth, addToWorld);
		case 6: return newBat(depth, addToWorld);
		case 7: return newBat(depth, addToWorld);
		case 8: return newBat(depth, addToWorld);
		case 9: return newPinkSlime(depth, player, addToWorld);
		case 10: return newPinkSlime(depth, player, addToWorld);
		default: return newFungus(depth, addToWorld);
		}
	}
	
	public Creature randomMediumMonster(int depth, Creature player, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return newSkeleton(depth, player, addToWorld);
		case 2: return newSkeleton(depth, player, addToWorld);
		case 3: return newPinkSlime(depth, player, addToWorld);
		case 4: return newGremlin(depth, player, addToWorld);
		case 5: return newGremlin(depth, player, addToWorld);
		case 6: return newAnimatedWeapon(depth, player, addToWorld);
		case 7: return newAnimatedArmor(depth, player, addToWorld);
		case 8: return newGremlinSkirmisher(depth, player, addToWorld);
		case 9: return newPinkSlime(depth, player, addToWorld);
		case 10: return newCrazyAlchemist(depth, player, addToWorld);
		default: return newSkeleton(depth, player, addToWorld);
		}
	}
	
	public Creature randomGreaterMonster(int depth, Creature player, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 10)) {
		case 1: return newSkeleton(depth, player, addToWorld);
		case 2: return newSkeleton(depth, player, addToWorld);
		case 3: return newGremlin(depth, player, addToWorld);
		case 4: return newCrazyAlchemist(depth, player, addToWorld);
		case 5: return newCrazyAlchemist(depth, player, addToWorld);
		case 6: return newAnimatedWeapon(depth, player, addToWorld);
		case 7: return newAnimatedArmor(depth, player, addToWorld);
		case 8: return newMimic(depth, player, addToWorld);
		case 9: return newCloaker(depth, player, addToWorld);
		case 10: return newOgre(depth, player, addToWorld);
		default: return newSkeleton(depth, player, addToWorld);
		}
	}
	
	public Creature randomChest(int depth, Creature player, int addToWorld) {
		switch(ExtraMaths.diceRoll(1, 20)) {
		case 1: return newFoodChest(depth, addToWorld);
		case 2: return newFoodChest(depth, addToWorld);
		case 3: return newFoodChest(depth, addToWorld);
		case 4: return newAmmoChest(depth, addToWorld);
		case 5: return newAmmoChest(depth, addToWorld);
		case 6: return newAmmoChest(depth, addToWorld);
		case 7: return newPotionChest(depth, addToWorld);
		case 8: return newPotionChest(depth, addToWorld);
		case 9: return newScrollChest(depth, player, addToWorld);
		case 10: return newWandChest(depth, player, addToWorld);
		case 11: return newRingChest(depth, addToWorld);
		case 12: return newWeaponChest(depth, addToWorld);
		case 13: return newWeaponChest(depth, addToWorld);
		case 14: return newShieldChest(depth, addToWorld);
		case 15: return newShieldChest(depth, addToWorld);
		case 16: return newArmorChest(depth, addToWorld);
		case 17: return newArmorChest(depth,addToWorld);
		case 18: return newWeaponChest(depth, addToWorld);
		case 19: return newArmorChest(depth, addToWorld);
		case 20: return newMimic(depth, player, addToWorld);
		default: return newFoodChest(depth, addToWorld);
		}
	}

}
