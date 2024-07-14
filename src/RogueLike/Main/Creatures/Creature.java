package RogueLike.Main.Creatures;

import java.awt.Color;
import java.util.*;

import RogueLike.Main.*;
import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.AI.PlayerAI;
import RogueLike.Main.AoE.Line;
import RogueLike.Main.AoE.Point;
import RogueLike.Main.Damage.*;
import RogueLike.Main.Entities.Entity;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Factories.EffectFactory;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Managers.SkillManager;
import RogueLike.Main.Screens.SpellTargetingScreen;
import RogueLike.Main.Screens.SpellSelectScreen;
import RogueLike.Main.Screens.GameplayScreen;
import RogueLike.Main.Screens.MerchantScreen;
import RogueLike.Main.Screens.Screen;
import RogueLike.Main.Screens.ThrowAtScreen;

public class Creature implements Cloneable{

	private World world;
	public World world() {
		return world;
	}
	
	private GameplayScreen gameplayScreen;
	public GameplayScreen gameplayScreen() {
		return gameplayScreen;
	}
	public void setGameplayScreen(GameplayScreen screen) {
		gameplayScreen = screen;
	}
	
	private CreatureAI ai;
	public CreatureAI ai() {
		return ai;
	}
	
	public int getActionSpeed() {
		int speed = ai.getActionSpeed();
		if(this.affectedBy(Effect.haste)) {
			speed = (int) Math.floor(speed/2);
		}
		return speed;
	}

	public ObjectFactory factory() {
		return ai.factory;

	}

	private int id;
	public int id() {
		return id;
	}

	public void setID(int amount) {
		id = amount;
	}

	public int x;
	public int x() {
		return x;
	}
	
	public void overrideX(int amount) {
		x = amount;
	}

	public int y;
	public int y() {
		return y;
	}
	
	public void overrideY(int amount) {
		y = amount;
	}

	public int z;
	public int z() {
		return z;
	}
	
	public void overrideZ(int amount) {
		z = amount;
	}
	
	public int depth() {
		return z+1;
	}

	private int maxDepth;
	public int maxDepth() {
		return maxDepth;
	}

	public void updateMaxDepth() {
		maxDepth = Math.max(z, maxDepth);
	}

	private char glyph;
	public char glyph() {
		return glyph;
	}

	private char defaultGlyph;
	public char defaultGlyph() {
		return defaultGlyph;
	}

	public void changeGlyph(char newGlyph) {
		glyph = newGlyph;
	}

	private Color color;
	public Color color() {
		return color;
	}

	private Color defaultColor;
	public Color defaultColor() {
		return defaultColor;
	}

	public void changeColor(Color newColor) {
		color = newColor;
	}
	
	public ArrayList<String> creatureTypes = new ArrayList<String>();



	public void setIsDead(boolean value) {
		isDead = value;
	}

	private boolean isDead;
	public boolean isDead() {
		return isDead;
	}

	private int maxHP;
	public int maxHP() {
		return maxHP;
	}

	public void modifyMaxStartingHP(int amount) {
		maxHP += amount;
		hp = maxHP;
	}

	public void setMaxHP(int amount) {
		maxHP = amount;
		hp = maxHP;
	}

	private int hp;
	public int hp() {
		return hp;
	}

	public void damage(Damage damage, String causeOfDeath) {
		int amount = damage.amount();
		int amountTemp = damage.amount();
		boolean applyStatus = true;

		if (amount < 0) amount = 0;

		if(this.isResistantTo(damage.type)){
			amount = (int)Math.floor(amountTemp*0.5);
		}

		if(this.isWeakTo(damage.type)){
			amount = (int)Math.floor(amountTemp*2);
		}

		if(this.isImmuneTo(damage.type)){
			amount = 0;
			applyStatus = false;
		}

		hp -= amount;
		if(amount > 0) {
			setHPCooldown(5);
		}
		if(!damage.isSilent && amount > 0) {
			if(this.isPlayer()) {
				this.notify("You take %d %s damage.", amount, damage.type.toString().toLowerCase());
			}else {
				this.doAction("take %d %s damage", amount, damage.type.toString().toLowerCase());
			}
		}else if (!damage.isSilent && amount == 0) {
			if(this.isPlayer()) {
				this.notify("You ignore the %s damage.", damage.type.toString().toLowerCase());
			}else {
				this.doAction("seem to ignore the %s damage", damage.type.toString().toLowerCase());
			}
		}

		if(applyStatus && damage.applyStatus && Dice.d20.roll() == 1 && damage.statusEffect() != null) {
			this.addEffect((Effect) damage.statusEffect().clone());
		}


		this.causeOfDeath = causeOfDeath;

		if(hp <= 0 && !isDead) {
			setIsDead(true);
			doActionWhenDead("die");
			if(lastHit != null) {
				lastHit.gainXP(this);
			}
			leaveCorpse();
			world.remove(this);
		}
	}

	public void heal(int amount) {
		heal(amount, false);
	}
	public void heal(int amount, boolean isSilent) {
		if (amount < 0) amount = 0;

		hp += amount;
		if(!isSilent) {
			if(this.isPlayer()) {
				this.notify("You recover %d health.", amount);
			}else {
				this.doAction("recover %d health", amount);
			}
		}

		if(hp > maxHP) {
			hp = maxHP;
		}
	}

	private int maxMana;
	public int maxMana() {
		return maxMana;
	}

	public void modifyMaxStartingMana(int amount) {
		maxMana += amount;
		mana = maxMana;
	}

	public void setMaxMana(int amount) {
		maxMana = amount;
		mana = maxMana;
	}

	private int mana;
	public int mana() {
		return mana;
	}

	public void gainMana(int amount, boolean isSilent) {
		if (amount <= 0) return; // gain must be positive

		mana += amount;
		if (mana > maxMana) {
			mana = maxMana;
		}

		if (this.isPlayer() && !isSilent) {
			this.notify("You recover %d mana.", amount);
		}
	}

	public void loseMana(int amount, boolean isSilent) {
		if (amount >= 0) return; // gain must be positive

		mana -= amount;
		if (mana > maxMana) {
			mana = maxMana;
		}

		if (this.isPlayer() && !isSilent) {
			this.notify("You lose %d mana.", amount);
		}
	}

	private int inventorySize;
	public int inventorySize() {
		return inventorySize;
	}

	public Item cloneFromInventory(int i) {
		return (Item) inventory.get(i).clone();
	}

	private int spellbookSize;
	public int spellbookSize() {
		return spellbookSize;
	}

	public Spell cloneFromSpellbook(int i) {
		return (Spell) spellbook.get(i).clone();
	}

	//private int armorClass;
	public int armorClass() {
		int returnArmorClass = 0;

		int armorClassBonuses = (weapon == null ? 0 : weapon.armorClass()) + (ring == null ? 0 : ring.armorClass()) + (shield == null ? 0 : shield.armorClass());

		int bonusACFromDex = dexterityModifier();
		if(armor != null && armor.isMediumArmor()) {
			if(bonusACFromDex > 2) {
				bonusACFromDex = 2;
			}
		}
		if(armor != null && armor.isHeavyArmor()) {
			bonusACFromDex = 0;
		}

		if(armor != null) {
			returnArmorClass = armor.armorClass() + armorClassBonuses+bonusACFromDex;
		}else {
			returnArmorClass = baseArmorClass + armorClassBonuses+bonusACFromDex;
		}
		
		if(armor !=null) {
			returnArmorClass += armor.upgradeLevel();
		}
		
		if(shield !=null) {
			returnArmorClass += shield.upgradeLevel();
		}

		if(affectedBy(Effect.giantStrength) == true) {
			returnArmorClass += 4;
		}
		if(affectedBy(Effect.corroded) == true) {
			returnArmorClass -= 2;
		}
		if(affectedBy(Effect.sundered) == true) {
			returnArmorClass = 10;
		}
		if(this.armorTrainingLevel() >= 3) {
			returnArmorClass += this.proficiencyBonus();
		}
		if(returnArmorClass < 0) {
			returnArmorClass = 0;
		}
		return returnArmorClass; 
	}


	private int baseArmorClass;
	public int baseArmorClass() {
		return baseArmorClass;
	}

	public void modifyBaseArmorClass(int amount) {
		baseArmorClass += amount;
	}
	public void setBaseArmorClass(int value) {
		baseArmorClass = value;
	}

	private int visionRadius;
	public int visionRadius() {
		int returnVisionRadius = visionRadius + (weapon == null ? 0 : weapon.visionRadius()) + (armor == null ? 0 : armor.visionRadius()) + (ring == null ? 0 : ring.visionRadius()) + (shield == null ? 0 : shield.visionRadius());
		if(affectedBy(Effect.blinded) == true) {
			returnVisionRadius = 2;
		}
		if(affectedBy(Effect.beastForm) == true) {
			returnVisionRadius += 4;
		}
		if(affectedBy(Effect.illuminated) == true) {
			returnVisionRadius += 10;
		}
		if(this.perceptionLevel() >= 2) {
			returnVisionRadius += this.proficiencyBonus();
		}
		return returnVisionRadius; 
	}

	public void modifyVisionRadius(int amount) {
		visionRadius += amount;
	}

	private int strength;
	public int strength() {
		int returnStrength = strength + (weapon == null ? 0 : weapon.strength()) + (armor == null ? 0 : armor.strength()) + (ring == null ? 0 : ring.strength()) + (shield == null ? 0 : shield.strength());
		if(affectedBy(Effect.giantStrength) == true) {
			returnStrength += 4;
		}
		//strength = returnStrength;
		return returnStrength; 
	}

	public int baseStrength() {
		return strength;
	}

	public void modifyStrength(int amount) {
		strength += amount;
	}

	public void setStrength(int amount) {
		strength = amount;
	}

	public int strengthModifier() {
		return ExtraMaths.roundModifier(strength());
	}

	public int strengthRoll() {
		int roll = (ExtraMaths.d20())+strengthModifier();
		return roll;
	}

	private int dexterity;
	public int dexterity() {
		int returnDexterity = dexterity + (weapon == null ? 0 : weapon.dexterity()) + (armor == null ? 0 : armor.dexterity()) + (ring == null ? 0 : ring.dexterity()) + (shield == null ? 0 : shield.dexterity());
		//dexterity = returnDexterity;
		if(affectedBy(Effect.beastForm)) {
			returnDexterity += 4;
		}
		return returnDexterity; 
	}

	public int baseDexterity() {
		return dexterity;
	}

	public void modifyDexterity(int amount) {
		dexterity += amount;
	}

	public void setDexterity(int amount) {
		dexterity = amount;
	}

	public int dexterityModifier() {
		return ExtraMaths.roundModifier(dexterity());
	}

	public int dexterityRoll() {
		int roll = (ExtraMaths.d20())+dexterityModifier();
		return roll;
	}

	private int intelligence;
	public int intelligence() {
		int returnIntelligence = intelligence + (weapon == null ? 0 : weapon.intelligence()) + (armor == null ? 0 : armor.intelligence()) + (ring == null ? 0 : ring.intelligence()) + (shield == null ? 0 : shield.intelligence());
		//intelligence = returnIntelligence;
		return returnIntelligence; 
	}

	public int baseIntelligence() {
		return intelligence;
	}

	public void modifyIntelligence(int amount) {
		intelligence += amount;
	}

	public void setIntelligence(int amount) {
		intelligence = amount;
	}

	public int intelligenceModifier() {
		return ExtraMaths.roundModifier(intelligence());
	}

	public int intelligenceRoll() {
		int roll = (ExtraMaths.d20())+intelligenceModifier();
		return roll;
	}

	private final EnumMap<DamageType, Integer> saveBonuses;
	public void setSaveBonus(DamageType damageType, int bonus) {
		saveBonuses.put(damageType, bonus);
	}
	public int saveBonus(DamageType damageType) {
		int baseSaveBonus = saveBonuses.getOrDefault(damageType, 0);

		int equipmentBonus = getequipmentArrayList().stream()
			.filter(Objects::nonNull)
			.map(eq -> eq.saveBonus(damageType))
			.reduce(0, Integer::sum);

		int wardBonus;
		switch (damageType) {
			case ACID: wardBonus = affectedBy(Effect.causticWard) ? 5 : 0; break;
			case CHAOS: wardBonus = affectedBy(Effect.eldritchWard) ? 5 : 0; break;
			case FIRE: wardBonus = affectedBy(Effect.magmaWard) ? 5 : 0; break;
			case FROST: wardBonus = affectedBy(Effect.chillWard) ? 5 : 0; break;
			case MAGIC: wardBonus = affectedBy(Effect.arcaneWard) ? 5 : 0; break;
			case PHYSICAL: wardBonus = affectedBy(Effect.bladeWard) ? 5 : 0; break;
			case POISON: wardBonus = affectedBy(Effect.venomousWard) ? 5 : 0; break;
			case SHOCK: wardBonus = affectedBy(Effect.arcWard) ? 5 : 0; break;
			case TRUE: wardBonus = 0; break;
			default: throw new IllegalArgumentException(damageType.toString());
		}

		int resistanceBonus = isResistantTo(damageType) ? 5 : 0;
		if(isImmuneTo(damageType)) {
			resistanceBonus += 100;
		}

		return baseSaveBonus + equipmentBonus + wardBonus + resistanceBonus;
	}
	public int rollSaveAgainst(DamageType damageType) {
		return Dice.d20.roll() + saveBonus(damageType);
	}

	public ArrayList<Item> getequipmentArrayList(){
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList.add(weapon);
		equipmentArrayList.add(armor);
		equipmentArrayList.add(ring);
		equipmentArrayList.add(shield);

		return equipmentArrayList;
	}

	private EnumSet<DamageType> resistances;
	private EnumSet<DamageType> weaknesses;
	private EnumSet<DamageType> immunities;

	public void addResistanceTo(DamageType damageType) {
		resistances.add(damageType);
	}
	public void addWeaknessTo(DamageType damageType) {
		weaknesses.add(damageType);
	}
	public void addImmunityTo(DamageType damageType) {
		immunities.add(damageType);
	}
	public boolean isImmuneTo(DamageType damageType) {
		for (Item equipment: getequipmentArrayList()) {
			if (equipment!=null && equipment.grantsImmunityTo(damageType)) return true;
		}
		return immunities.contains(damageType);
	}
	public boolean isWeakTo(DamageType damageType) {
		for (Item equipment: getequipmentArrayList()) {
			if (equipment!=null && equipment.grantsWeaknessTo(damageType)) return true;
		}
		return weaknesses.contains(damageType);
	}
	public boolean isResistantTo(DamageType damageType) {
		for (Item equipment: getequipmentArrayList()) {
			if (equipment!=null && equipment.grantsResistanceTo(damageType)) return true;
		}
		return resistances.contains(damageType);
	}

	private Skill[] skills = SkillManager.getDefaultSkillArray();
	public Skill[] skills(){
		//Collections.sort(skills, Comparator.comparing(Skill::id));
		return skills;
	}
	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}
	public void levelUpSkill(int id, int amount) {
		boolean decrease = false;
		if(amount < 0) {
			decrease = true;
		}
		skills[id].modifyLevel(amount, decrease);
	}
	
	public Skill simpleWeapons() {
		return skills[0];
	}
	public int simpleWeaponsLevel() {
		return skills[0].level();
	}
	
	public Skill martialWeapons() {
		return skills[1];
	}
	public int martialWeaponsLevel() {
		return skills[1].level();
	}
	
	public Skill armorTraining() {
		return skills[2];
	}
	public int armorTrainingLevel() {
		return skills[2].level();
	}
	
	public Skill fortitude() {
		return skills[3];
	}
	public int fortitudeLevel() {
		return skills[3].level();
	}

	public Skill finesseWeapons() {
		return skills[4];
	}
	public int finesseWeaponsLevel() {
		return skills[4].level();
	}
	
	public Skill rangedWeapons() {
		return skills[5];
	}
	public int rangedWeaponsLevel() {
		return skills[5].level();
	}
	
	public Skill stealth() {
		return skills[6];
	}
	public int stealthLevel() {
		return skills[6].level();
	}
	
	public Skill perception() {
		return skills[7];
	}
	public int perceptionLevel() {
		return skills[7].level();
	}
	
	public Skill evocation() {
		return skills[8];
	}
	public int evocationLevel() {
		return skills[8].level();
	}
	
	public Skill pyromancy() {
		return skills[9];
	}
	public int pyromancyLevel() {
		return skills[9].level();
	}
	
	public Skill cryomancy() {
		return skills[10];
	}
	public int cryomancyLevel() {
		return skills[10].level();
	}
	
	public Skill electromancy() {
		return skills[11];
	}
	public int electromancyLevel() {
		return skills[11].level();
	}
	
	public Skill alchemancy() {
		return skills[12];
	}
	public int alchemancyLevel() {
		return skills[12].level();
	}
	
	public Skill ferromancy() {
		return skills[13];
	}
	public int ferromancyLevel() {
		return skills[13].level();
	}



	private DamageType unarmedDamageType;
	public void setUnarmedDamageType(DamageType unarmedDamageType) {
		this.unarmedDamageType = unarmedDamageType;
	}
	public DamageType unarmedDamageType() {
		// TODO: I've removed the ability for equipment to override the base unarmed damage type here. We may want
		//  to add this back in later.
		return unarmedDamageType;
	}


	private String name;
	public String name() {
		return name;
	}

	private String defaultName;
	public String defaultName() {
		return defaultName;
	}

	public void setName(String newName) {
		name = newName;
	}

	private Inventory inventory;
	public Inventory inventory() {
		return inventory;
	}

	private Spellbook spellbook;
	public Spellbook spellbook() {
		return spellbook;
	}
	
	/*private Featbook featbook;
	public Featbook featbook() {
		return featbook;
	}*/

	private int maxFood;
	public int maxFood() {
		return maxFood;
	}

	public void modifyMaxFood(int amount) {
		maxFood += amount;
	}

	public void modifyMaxStartingFood(int amount) {
		maxFood += amount;
		food = maxFood / 3 * 2;
	}

	private int food;
	public int food() {
		return food;
	}

	private int xp;
	public int xp() {
		return xp;
	}

	public int xpToNextLevel() {
		return (int)((Math.pow(level, 2.0)*25));
	}

	public void modifyXP(int amount) {
		xp += amount;
		notify("You %s %d XP.", amount < 0 ? "lose" : "gain", amount);

		while(xp >= xpToNextLevel()) {
			level++;
			xp = 0;
			doAction("advance to level %d", level);
			ai.onGainLevel();
			heal(level * 2);
		}
	}

	public void gainStrength() {
		strength += 1;
		doAction("become stronger");
	}

	public void gainDexterity() {
		dexterity += 1;
		doAction("become more agile");
	}

	public void gainIntelligence() {
		intelligence += 1;
		doAction("become more aware");
	}

	private int hpScaleAmount;
	public int hpScaleAmount() {
		return hpScaleAmount;
	}

	public void setHPScaleAmount(int amount) {
		hpScaleAmount = amount;
	}

	private int hpScaleLow = 4;
	public int hpScaleLow() {
		return hpScaleLow;
	}
	private int hpScaleMedium = 8;
	public int hpScaleMedium() {
		return hpScaleMedium;
	}
	private int hpScaleHigh = 12;
	public int hpScaleHigh() {
		return hpScaleHigh;
	}

	private int manaScaleAmount;
	public int manaScaleAmount() {
		return manaScaleAmount;
	}

	public void setManaScaleAmount(int amount) {
		manaScaleAmount = amount;
	}

	private int manaScaleLow = 4;
	public int manaScaleLow() {
		return manaScaleLow;
	}
	private int manaScaleMedium = 8;
	public int manaScaleMedium() {
		return manaScaleMedium;
	}
	private int manaScaleHigh = 12;
	public int manaScaleHigh() {
		return manaScaleHigh;
	}

	public void gainMaxHP() {
		int bonus = strengthModifier();
		if(bonus < 0) {
			bonus = 0;
		}
		maxHP += hpScaleAmount+bonus;
		hp += hpScaleAmount+bonus;
		//doAction("look healthier");
	}

	public void gainMaxMana() {
		int bonus = Math.max(0, manaGainedOnLevelUp());
		maxMana += manaScaleAmount+bonus;
		mana += manaScaleAmount+bonus;
		//doAction("look healthier");
	}

	public int manaGainedOnLevelUp() {
		return intelligenceModifier();
	}

	private int regenHPCooldown = 1;
	public void setHPCooldown(int amount) {
		regenHPCooldown = amount;
	}

	private int regenManaCooldown = 1;
	public void setManaCooldown(int amount) {
		regenManaCooldown = amount;
	}
	
	private double healthRegenPercentage = 0.1;
	public double healthRegenPercentage() {
		return healthRegenPercentage;
	}
	public void setHealthRegenPercentage(double amount) {
		healthRegenPercentage = amount;
	}
	public void modifyHealthRegenPercentage(double amount) {
		healthRegenPercentage += amount;
	}
	
	private double manaRegenPercentage = 0.1;
	public double manaRegenPercentage() {
		return manaRegenPercentage;
	}
	public void setManaRegenPercentage(double amount) {
		manaRegenPercentage = amount;
	}
	public void modifyManaRegenPercentage(double amount) {
		manaRegenPercentage += amount;
	}



	/*public void gainAttackValue() {
		attackValue += 1;
		doAction("look stronger");
	}

	public void gainAttackValueOrc() {
		attackValue += attackValue;
	}

	public int isRaging;
	public int isRaging() {
		return isRaging;
	}
	public void modifyIsRaging(int amount) {
		isRaging += amount;
	}

	public void gainDefenseValue() {
		defenseValue += 1;
		doAction("look tougher");
	}*/

	public void gainVision() {
		visionRadius += 1;
		doAction("look more alert");
	}

	private int level;
	public int level() {
		return level;
	}

	private Item weapon;
	public Item weapon() {
		return weapon;
	}

	private Item armor;
	public Item armor() {
		return armor;
	}

	private Item shield;
	public Item shield() {
		return shield;
	}

	private Item ring;
	public Item ring() {
		return ring;
	}

	private Item ammunition;
	public Item ammunition() {
		return ammunition;
	}

	private String weaponName;
	public String weaponName() {
		return weaponName;
	}

	private String armorName;
	public String armorName() {
		return armorName;
	}

	private String shieldName;
	public String shieldName() {
		return shieldName;
	}

	private String ringName;
	public String ringName() {
		return ringName;
	}

	private String ammunitionName;
	public String ammunitionName() {
		return ammunitionName;
	}
	
	private Item quickslot_1;
	public Item quickslot_1() {
		return quickslot_1;
	}
	private Item quickslot_2;
	public Item quickslot_2() {
		return quickslot_2;
	}
	private Item quickslot_3;
	public Item quickslot_3() {
		return quickslot_3;
	}
	private Item quickslot_4;
	public Item quickslot_4() {
		return quickslot_4;
	}
	private Item quickslot_5;
	public Item quickslot_5() {
		return quickslot_5;
	}
	private Item quickslot_6;
	public Item quickslot_6() {
		return quickslot_6;
	}

	public String healthAsString() {
		if(hp >= (maxHP*0.75)) {
			return "Healthy";
		}else if(hp >= (maxHP*0.5)) {
			return "Injured";
		}else if(hp >= (maxHP*0.25)) {
			return "Wounded";
		}else {
			return "Dying";
		}
	}

	public String armorAsString() {
		if(armorClass() >= 18) {
			return "Ironclad";
		}else if(armorClass() >= 15) {
			return "Resilient";
		}else if(armorClass() >= 12) {
			return "Sturdy";
		}else {
			return "Feeble";
		}
	}

	public String details() {
		return String.format("   Level: %d   Health: %s   Armor: %s", level, healthAsString(), armorAsString());
	}

	private String causeOfDeath;
	public String causeOfDeath() {
		return causeOfDeath;
	}

	private List<Effect> effects;
	public List<Effect> effects(){
		return effects;
	}

	public boolean affectedBy(String type) {
		for(Effect e : this.effects()) {
			if(e.type() == type) {
				return true;
			}
		}
		return false;
	}

	

	private boolean stayVisible;
	public boolean stayVisible() {
		return stayVisible;
	}
	public void modifyStayVisible(boolean value) {
		stayVisible = value;
	}

	private boolean isFlying;
	public boolean isFlying() {
		return isFlying;
	}
	public void setIsFlying(boolean value) {
		isFlying = value;
	}

	

	private boolean isAsleep;
	public boolean isAsleep() {
		return isAsleep;
	}
	public void setIsAsleep(boolean value) {
		isAsleep = value;
	}

	

	private boolean isDisguised;
	public boolean isDisguised() {
		return isDisguised;
	}
	public void setIsDisguised(boolean value) {
		isDisguised = value;
	}

	private boolean isContainer;
	public boolean isContainer() {
		return isContainer;
	}
	public void modifyIsContainer(boolean value) {
		isContainer = value;
	}

	private boolean isMimic;
	public boolean isMimic() {
		return isMimic;
	}
	public void modifyIsMimic(boolean value) {
		isMimic = value;
	}

	private boolean isTileSpell;
	public boolean isTileSpell() {
		return isTileSpell;
	}
	public void modifyIsTileSpell(boolean value) {
		isTileSpell = value;
	}

	private int score;
	public int score() {
		return score + this.xp() + this.gold();
	}

	public void modifyScore(int amount) {
		score += amount;
	}

	private boolean isReadingMagicMapping;
	public boolean isReadingMagicMapping() {
		return isReadingMagicMapping;
	}

	public void setIsReadingMagicMapping(boolean value) {
		isReadingMagicMapping = value;
	}

	private boolean isReadingIdentify;
	public boolean isReadingIdentify() {
		return isReadingIdentify;
	}

	public void setIsReadingIdentify(boolean value) {
		isReadingIdentify = value;
	}

	private boolean isReadingUpgrade;
	public boolean isReadingUpgrade() {
		return isReadingUpgrade;
	}

	public void setIsReadingUpgrade(boolean value) {
		isReadingUpgrade = value;
	}

	private boolean isReadingRemoveCurse;
	public boolean isReadingRemoveCurse() {
		return isReadingRemoveCurse;
	}

	public void setIsReadingRemoveCurse(boolean value) {
		isReadingRemoveCurse = value;
	}

	private boolean isReadingEnchantment;
	public boolean isReadingEnchantment() {
		return isReadingEnchantment;
	}

	public void setIsReadingEnchantment(boolean value) {
		isReadingEnchantment = value;
	}

	private boolean hasNoCorpse;
	public boolean hasNoCorpse() {
		return hasNoCorpse;
	}

	public void setHasNoCorpse(boolean value) {
		hasNoCorpse = value;
	}

	private boolean hasNoXP;
	public boolean hasNoXP() {
		return hasNoXP;
	}

	public void setHasNoXP(boolean value) {
		hasNoXP = value;
	}

	private int attributePoints;
	public int attributePoints() {
		return attributePoints;
	}

	public void modifyAttributePoints(int amount) {
		attributePoints += amount;
	}

	public void setAttributePoints(int amount) {
		attributePoints = amount;
	}

	private int skillPoints;
	public int skillPoints() {
		return skillPoints;
	}

	public void modifySkillPoints(int amount) {
		skillPoints += amount;
	}

	public void setSkillPoints(int amount) {
		skillPoints = amount;
	}

	//private int proficiencyBonus;
	public int proficiencyBonus() {
		return (int)1+(int)Math.ceil(level*0.25);
	}

//	public void modifyProficiencyBonus(int amount) {
//		proficiencyBonus += amount;
//	}

	public int strengthSaveDC() {
		return 8+proficiencyBonus()+strengthModifier();
	}

	public int dexteritySaveDC() {
		return 8+proficiencyBonus()+dexterityModifier();
	}

	public int intelligenceSaveDC() {
		return 8+proficiencyBonus()+intelligenceModifier();
	}

	private Creature lastHit;
	public Creature lastHit() {
		return lastHit;
	}

	public void setLastHit(Creature hitLast) {
		lastHit = hitLast;
	}

	private Creature lastTarget;
	public Creature lastTarget() {
		return lastTarget;
	}

	public void setLastTarget(Creature targetLast) {
		lastTarget = targetLast;
	}

	private int gold;
	public int gold() {
		return gold;
	}

	public void modifyGold(int amount) {
		gold += amount;
	}
	
	public int equipmentValue() {
		int value = 0;
		if(weapon != null) {
			value += weapon.currentGoldValue();
		}
		if(armor != null) {
			value += armor.currentGoldValue();
		}
		if(shield != null) {
			value += shield.currentGoldValue();
		}
		if(ring != null) {
			value += ring.currentGoldValue();
		}
		if(ammunition != null) {
			value += ammunition.currentGoldValue();
		}
		return value;
	}

	public void become(Creature other) {
		this.world().replaceCreature(this, other);

	}

	private boolean social;
	public boolean social() {
		return social;
	}
	public void setSocial(boolean value) {
		social = value;
	}
	
	public String talkToSelf() {
		switch(ExtraMaths.diceRoll(1, 8)) {
			case 1: return "Ti sbrn.. Hsi oig.";
			case 2: return "Uh-gaddt gig ur uyaan.";
			case 3: return "Ir'gymn uh.";
			case 4: return "Icudg o ugsbe rudnw ol ofrafnu eraon o.";
			case 5: return "Iwne Fgzai aigse oih? Odri az smkn twtngt.";
			case 6: return "Hco, oo dialn og!";
			case 7: return "Enhro etshkicnogr btu.";
			case 8: return "U tijsanh? T agcd epiolhv, rua.";
			default: return "Ti sbrn.. Hsi oig.";
		}
	}
	
	public String talkToOther() {
		switch(ExtraMaths.diceRoll(1, 8)) {
			case 1: return "Mwi ae? Sakttt Fgzai ngagi?";
			case 2: return "Oge sw? Hothehhcl etl! Aow.";
			case 3: return "Idc ey? Sahlopu ledw.";
			case 4: return "Ym mhsee'aytt, aht..";
			case 5: return "Awepewo nsrsheo, guglidb gte.";
			case 6: return "Wa? Itta! Htw hsaatw?";
			case 7: return "Ihntge? Mdoisd ryaoeuh.";
			case 8: return "Hycotua wt atkser if.";
			default: return "Mwi ae? Sakttt Fgzai ngagi?";
		}
	}
	
	private boolean hasVisitedZone1 = false;
	public boolean hasVisitedZone1() {
		return hasVisitedZone1;
	}
	public void setHasVisitedZone1(boolean value) {
		hasVisitedZone1 = value;
	}
	
	private boolean hasVisitedZone2 = false;
	public boolean hasVisitedZone2() {
		return hasVisitedZone2;
	}
	public void setHasVisitedZone2(boolean value) {
		hasVisitedZone2 = value;
	}
	
	private boolean hasVisitedZone3 = false;
	public boolean hasVisitedZone3() {
		return hasVisitedZone3;
	}
	public void setHasVisitedZone3(boolean value) {
		hasVisitedZone3 = value;
	}


	//item id max

	public Creature(World world, String name, char glyph, Color color, int maxHP, int maxMana, int armorclass, int strength, int dexterity, int intelligence, int visionRadius, int inventorySize) {
		this.world = world;
		this.glyph = glyph;
		this.defaultGlyph = glyph;
		this.color = color;
		this.defaultColor = color;
		this.maxHP = maxHP;
		this.hp = maxHP;
		this.maxMana = maxMana;
		this.mana = maxMana;
		this.baseArmorClass = armorclass;
		this.visionRadius = visionRadius;
		this.name = name;
		this.defaultName = name;
		this.inventory = new Inventory(inventorySize);
		//
		this.spellbook = new Spellbook(60);
		//
		//
		//this.featbook = new Featbook(60);
		//
		this.maxFood = 1200;
		this.food = maxFood / 3 * 2;
		this.level = 1;
		this.effects = new ArrayList<Effect>();
		this.score = 0;
		this.maxDepth = z;
		this.isFlying = false;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		//this.proficiencyBonus = 2;
		this.hpScaleAmount = hpScaleMedium();
		this.manaScaleAmount = manaScaleMedium();
		this.unarmedDamageType = DamageType.PHYSICAL;
		this.weaknesses = EnumSet.noneOf(DamageType.class);
		this.resistances = EnumSet.noneOf(DamageType.class);
		this.immunities = EnumSet.noneOf(DamageType.class);
		saveBonuses = new EnumMap<>(DamageType.class);
	}



	public void setCreatureAI(CreatureAI ai) {
		this.ai = ai;

	}

	public String nameOf(Item item) {
		return ai.getName(item);
	}

	public void learnName(Item item) {
		notify("The "+item.getAppearance()+" is a "+item.name()+"!");
		item.setIsIdentified(true);
		ai.setName(item, item.name());
	}

	public void learnNameQuiet(Item item) {
		item.setIsIdentified(true);
		ai.setName(item, item.name());
	}

	public void dig(int wx, int wy, int wz) {
		modifyFood(-10);
		doAction("dig through the wall");
		world.dig(wx, wy, wz);

	}

	public void idle() {
		modifyFood(-1);
	}

	public void moveBy(int mx, int my, int mz, boolean falling) {
		//if(mx == 0 && my == 0 && mz == 0) {
		//return;
		//}
		if((affectedBy(Effect.paralysed)) && isPlayer()) {
			if((int)(Math.random()*10) < 8) {
				doAction("struggle to overcome paralysis!");
				return;
			}else {
				doAction("move with difficulty");
			}
		}

		if((affectedBy(Effect.frozen)) && isPlayer()) {
			doAction("struggle to break the frost!");
			return;

		}

		if(affectedBy(Effect.confused)) {
			mx = ExtraMaths.diceRoll(-1, 1);
			my = ExtraMaths.diceRoll(-1, 1);
		}

		Tile tile = world.tile(x+mx, y+my, z+mz);

		if(mz == -1) {
			if(tile == Tile.STAIRS_DOWN) {
				doAction("walk up the stairs to depth %d", z+mz+1);
			}
			else {
				doAction("can't find a way up here");
				return;
			}
		}
		else if(mz == 1) {
			if(tile == Tile.STAIRS_UP) {
				doAction("walk down the stairs to depth %d", z+mz+1);
			}else if(falling == true) {
				
			}else {
				doAction("can't find a way down here");
				return;
			}

		}


		Creature other = world.creature(x+mx, y+my, z+mz);
		modifyFood(-2);
		if(other == null) {
			ai.onEnter(x+mx, y+my, z+mz, tile);
		}
		else if(other.isContainer()){ 
			openContainer(other);
		}else if(other instanceof Merchant && this instanceof Player) {
			// TODO(dd): this looks kinda hacky to me
			talkToMerchant((Merchant) other, (Player) this);
		}else{
			attack(other);
		}		
	}
	
	public void talkToMerchant(Merchant merchant, Player player) {//TODO TRIGGER MERCHANT
		this.gameplayScreen().subscreen = new MerchantScreen(merchant, player);
	}

	public void openContainer(Creature container) {
		container.leaveCorpse();
		world.remove(container);
	}

	public void reveal() {
		setIsDisguised(false);
		setName(defaultName());
		changeColor(defaultColor());
		doAction("reveal itself!");
	}

	public void hide() {
		setIsDisguised(true);
		setName("Chest");
		changeColor(ExtendedAsciiPanel.mimic);
	}

	public void sleep() {
		setIsAsleep(true);
	}

	public void wakeup() {
		setIsAsleep(false);
	}
	
	private boolean isMerchant = false;
	public boolean isMerchant() {
		return isMerchant;
	}
	public void setIsMerchant(boolean merchant) {
		isMerchant = merchant;
	}

	public void attack(Creature other) {

		int amount = 0;
		int attackBonus = 0;
		if(weapon != null) {
			if((this.weapon.isSimple() && this.simpleWeaponsLevel() >= 2) || (this.weapon.isMartial() && this.martialWeaponsLevel() >= 2) || (this.weapon.isFinesse() && this.finesseWeaponsLevel() >= 2)) {
				amount += this.proficiencyBonus();
			}
			if(weapon.usesStrength()) {
				attackBonus += this.strengthModifier();
			}else if(weapon.usesDexterity()){
				attackBonus += this.dexterityModifier();
			}else if(weapon.usesIntelligence()){
				attackBonus += this.intelligenceModifier();
			}
			if(weapon.isVersatile() && shield == null) {
				amount += (int)(weapon.versatileDamageDice().roll())+attackBonus+weapon.upgradeLevel();
			}else {
				amount += (int)(weapon.damageDice().roll())+attackBonus+weapon.upgradeLevel();
			}
		}else {
			amount += 1+strengthModifier();
		}

		if(amount < 1) {
			amount = 1;
		}
		
		int electroAmount = this.proficiencyBonus();
		if(this.weapon() != null) {
			electroAmount += this.weapon().upgradeLevel();
		}
		Damage electroDamage = new Damage(electroAmount, false, DamageType.SHOCK, this.ai.factory.effectFactory, true);
		
		int attackRoll = 0;
		if(weapon != null) {
			if(weapon.usesDexterity()) {
				attackRoll += this.dexterityRoll()+weapon.upgradeLevel();
			}else if(weapon.usesStrength()) {
				attackRoll += this.strengthRoll()+weapon.upgradeLevel();
			}else if(weapon.usesIntelligence()) {
				attackRoll += this.intelligenceRoll()+weapon.upgradeLevel();
			}else {
				attackRoll += this.strengthRoll();
			}
		}else {
			attackRoll += this.strengthRoll();
		}
		
		if(this.weapon() != null && ((this.weapon.isSimple() && this.simpleWeaponsLevel() >= 1) || (this.weapon.isMartial() && this.martialWeaponsLevel() >= 1) || (this.weapon.isFinesse() && this.finesseWeaponsLevel() >= 1))) {
			attackRoll += this.proficiencyBonus();
		}

		if(other.affectedBy(Effect.invisible) == true) {
			attackRoll -= 5;
		}
		
		if(affectedBy(Effect.invisible) == true) {
			if(this.stealthLevel() >= 3) {
				attackRoll = 20;
			}else {
				attackRoll += 5;
			}
		}
		
		if(attackRoll >= 20) {
			if(this.weapon != null && this.weapon.isMartial() && this.martialWeaponsLevel() >= 3) {
				amount *= 3;
			}else {
				amount *= 2;
			}
		}
		
		DamageType damageType;
		if(weapon != null) {
			damageType = weapon.effectiveDamageType();
		}
		else {
			// no weapon, use unarmed damage type
			damageType = unarmedDamageType();
		}
		Damage damage = new Damage(amount, false, damageType, factory().effectFactory, true);





		modifyFood(-5);


		

		if(attackRoll >= other.armorClass() || attackRoll >= 20) {
			//doAction("attack the %s for %d damage", other.name, damage.amount());
			doAction("attack the %s", other.name);
			if(attackRoll >= 20) {
				notify("It's a critical hit!");
			}
			//
			
			
			
			other.setLastHit(this);
			if(weapon != null) {
				other.damage(damage, String.format("Killed by a %s using a %s", this.name, this.weaponName()));
				if(this.weapon.isSimple() && this.simpleWeaponsLevel() >= 3 && attackRoll >= 20) {
					other.addEffect((Effect)this.ai().factory.effectFactory.paralysed(this.proficiencyBonus()).clone());
				}else if(this.weapon.isFinesse() && this.finesseWeaponsLevel() >= 3 && attackRoll >= 20) {
					other.addEffect((Effect)this.ai().factory.effectFactory.paralysed(this.proficiencyBonus()).clone());
				}
			}else {
				other.damage(damage, String.format("Killed by a %s", this.name));
			}
			if(this.affectedBy(Effect.electrocharged)) {
				if(weapon != null) {
					other.damage(electroDamage, String.format("Killed by a %s using a %s", this.name, this.weaponName()));
				}else {
					other.damage(electroDamage, String.format("Killed by a %s", this.name));
				}
			}
			

			if(other.hp > 0){
				this.setLastTarget(other);
			}

			if(weapon != null && weapon.enchantment() != null && other.hp() >= 1) {
				if(weapon.enchantment().checkActivation(weapon.upgradeLevel()*this.intelligenceModifier())) {
					weapon.enchantment().applyToCreature(other);
				}
			}
			if(other.armor() != null && other.armor().enchantment() != null && other.hp() >= 1) {
				if(other.armor().enchantment().checkActivation(other.armor().upgradeLevel()*other.intelligenceModifier())) {
					other.armor().enchantment().applyToCreature(other);
				}
			}
			if(other.shield() != null && other.shield().enchantment() != null && other.hp() >= 1) {
				if(other.shield().enchantment().checkActivation(other.shield().upgradeLevel()*other.intelligenceModifier())) {
					other.shield().enchantment().applyToCreature(other);
				}
			}

			if(other.isAsleep() == true) {
				other.setIsAsleep(false);
				other.doAction("wake up");
			}
			if(other.isDisguised() == true) {
				other.reveal();
			}
		}else {
			doAction("fail to hit the %s", other.name);
		}


		if(affectedBy(Effect.invisible) == true) {
			this.cureEffectOfType(Effect.invisible);
		}

		//if(other.hp < 1) {
		//gainXP(other);
		//}
		//notify("You attack the '%s' for %d damage.", other.glyph, amount);
		//other.notify("The '%s' attacks you for %d damage.", glyph, amount);
	}

	public void throwItem(Item item, int wx, int wy, int wz) {
		Point end = new Point(x, y, 0);
		item.setOwner(this);
		for(Point p : new Line(x, y, wx, wy)) {
			if(realTile(p.x, p.y, z).blocksRangedAttacks()) {
				break;
			}
			end = p;
		}

		wx = end.x;
		wy = end.y;
		Creature c = creature(wx, wy, wz);
		if(item.curse() != null && (item == weapon || item == armor || item == shield || item == ring || item == ammunition || item == quickslot_1 || item == quickslot_2 || item == quickslot_3 || item == quickslot_4 || item == quickslot_5 || item == quickslot_6)) {
			notify("Your "+nameOf(item)+" is cursed! You can't let go of it!");
		}else {
			Item item2 = (Item) item.clone();
			item.modifyStackAmount(-1);
			if(item.stackAmount() <= 0 && (item == quickslot_1 || item == quickslot_2 || item == quickslot_3 || item == quickslot_4 || item == quickslot_5 || item == quickslot_6)) {
				if(item == quickslot_1) {
					quickslot_1 = null;
				}else if(item == quickslot_2) {
					quickslot_2 = null;
				}else if(item == quickslot_3) {
					quickslot_3 = null;
				}else if(item == quickslot_4) {
					quickslot_4 = null;
				}else if(item == quickslot_5) {
					quickslot_5 = null;
				}else if(item == quickslot_6) {
					quickslot_6 = null;
				}
			}
			item2.setStackAmount(1);
			if(c != null && c.isContainer() == false && c.isDisguised() == false) {
				throwAttack(item2, c);
			}else {
				doAction("throw a %s", nameOf(item2));
			}

			if(item2.quaffEffect() != null && c != null) {
				getRidOf(item2);
			}else {
				putAt(item2, wx, wy, wz);
			}
		}

	}

	private void throwAttack(Item item, Creature other) {
		modifyFood(-1);
		int amount = 0;
		int attackBonus = 0;
		if(item.isThrownWeapon()) {
			if((item.isSimple() && this.simpleWeaponsLevel() >= 2) || (item.isMartial() && this.martialWeaponsLevel() >= 2) || (item.isFinesse() && this.finesseWeaponsLevel() >= 2)) {
				amount += this.proficiencyBonus();
			}
			if(item.usesStrength()) {
				attackBonus += this.strengthModifier();
			}else if(item.usesDexterity()){
				attackBonus += this.dexterityModifier();
			}else if(item.usesIntelligence()){
				attackBonus += this.intelligenceModifier();
			}
			amount += (int)(item.thrownDamageDice().roll())+attackBonus+item.upgradeLevel();
		
		}else {
			amount += 1+strengthModifier();
		}

		if(amount < 1) {
			amount = 1;
		}

		Damage damage = new Damage(amount, false, item.effectiveDamageType(), factory().effectFactory, true);

		int attackRoll = 0;
		if(item.isThrownWeapon()) {
			if(item.usesDexterity()) {
				attackRoll += this.dexterityRoll()+item.upgradeLevel();
			}else if(item.usesStrength()) {
				attackRoll += this.strengthRoll()+item.upgradeLevel();
			}else if(item.usesIntelligence()) {
				attackRoll += this.intelligenceRoll()+item.upgradeLevel();
			}else {
				attackRoll += this.strengthRoll();
			}
		}else {
			attackRoll += this.strengthRoll();
		}
		
		if((item.isSimple() && this.simpleWeaponsLevel() >= 1) || (item.isMartial() && this.martialWeaponsLevel() >= 1) || (item.isFinesse() && this.finesseWeaponsLevel() >= 1)) {
			attackRoll += this.proficiencyBonus();
		}

		if(other.affectedBy(Effect.invisible) == true) {
			attackRoll -= 5;
		}
		
		if(affectedBy(Effect.invisible) == true) {
			if(this.stealthLevel() >= 3) {
				attackRoll = 20;
			}else {
				attackRoll += 5;
			}
		}
		
		if(attackRoll >= 20) {
			if(item.isMartial() && this.martialWeaponsLevel() >= 3) {
				amount *= 3;
			}else {
				amount *= 2;
			}
		}
		
		int electroAmount = this.proficiencyBonus();
		if(item.upgradeLevel() > 0) {
			electroAmount += item.upgradeLevel();
		}
		Damage electroDamage = new Damage(electroAmount, false, DamageType.SHOCK, this.ai.factory.effectFactory, true);

		if(attackRoll >= other.armorClass() || attackRoll >= 20) {
			//doAction("throw a %s at the %s for %d damage", nameOf(item), other.name, damage.amount());
			doAction("throw a %s at the %s", nameOf(item), other.name);
			if(attackRoll >= 20) {
				notify("It's a critical hit!");
			}

			other.setLastHit(this);
			other.addEffect(item.quaffEffect());
			other.damage(damage, String.format("Killed by a %s using a %s", this.name, item.name()));
			if(item.isSimple() && this.simpleWeaponsLevel() >= 3 && attackRoll >= 20) {
				other.addEffect((Effect)this.ai().factory.effectFactory.paralysed(this.proficiencyBonus()).clone());
			}else if(item.isFinesse() && this.finesseWeaponsLevel() >= 3 && attackRoll >= 20) {
				other.addEffect((Effect)this.ai().factory.effectFactory.paralysed(this.proficiencyBonus()).clone());
			}
			if(this.affectedBy(Effect.electrocharged)) {
				other.damage(electroDamage, String.format("Killed by a %s using a %s", this.name, item.name()));
			}
			if(other.hp > 0){
				this.setLastTarget(other);
			}

			if(item.isThrownWeapon() && item.enchantment() != null && other.hp() >= 1) {
				if(item.enchantment().checkActivation(item.upgradeLevel()*this.intelligenceModifier())) {
					item.enchantment().applyToCreature(other);
				}
			}
			if(other.armor() != null && other.armor().enchantment() != null && other.hp() >= 1) {
				if(other.armor().enchantment().checkActivation(other.armor().upgradeLevel()*other.intelligenceModifier())) {
					other.armor().enchantment().applyToCreature(other);
				}
			}
			if(other.shield() != null && other.shield().enchantment() != null && other.hp() >= 1) {
				if(other.shield().enchantment().checkActivation(other.shield().upgradeLevel()*other.intelligenceModifier())) {
					other.shield().enchantment().applyToCreature(other);
				}
			}
			if(other.isAsleep() == true) {
				other.setIsAsleep(false);
				other.doAction("wake up");
			}
			if(other.isDisguised() == true) {
				other.reveal();
			}
		}else {
			doAction("fail to hit the %s", other.name);
		}

		if(affectedBy(Effect.invisible) == true) {
			this.cureEffectOfType(Effect.invisible);
		}

		//if(other.hp < 1) {
		//gainXP(other);
		//}
	}

	public void rangedWeaponAttack(Creature other) {
		modifyFood(-1);

		int amount = 0;
		int attackBonus = 0;
		if(weapon != null) {
			if(this.rangedWeaponsLevel() >= 2) {
				attackBonus += this.proficiencyBonus();
			}
			if(weapon.usesStrength()) {
				attackBonus += this.strengthModifier();
			}else if(weapon.usesDexterity()){
				attackBonus += this.dexterityModifier();
			}else if(weapon.usesIntelligence()){
				attackBonus += this.intelligenceModifier();
			}
			amount += (int)(weapon.rangedDamageDice().roll())+attackBonus+weapon.upgradeLevel();
		}else {
			amount = 1+this.dexterityModifier();
		}
		
		int attackRoll = 0;
		if(weapon.usesDexterity()) {
			attackRoll += this.dexterityRoll()+weapon.upgradeLevel();
		}else if(weapon.usesStrength()) {
			attackRoll += this.strengthRoll()+weapon.upgradeLevel();
		}else if(weapon.usesIntelligence()) {
			attackRoll += this.intelligenceRoll()+weapon.upgradeLevel();
		}else {
			attackRoll += this.strengthRoll();
		}
		
		if(this.rangedWeaponsLevel() >= 1) {
			attackRoll += this.proficiencyBonus();
		}

		if(amount < 1) {
			amount = 1;
		}
		
		if(attackRoll >= 20) {
			amount *= 2;
		}

		DamageType damageType;
		if (weapon != null) {
			damageType = weapon.effectiveDamageType();
		}
		else {
			damageType = this.unarmedDamageType();
		}
		Damage damage = new Damage(amount, false, damageType, factory().effectFactory, true);


		if(other.affectedBy(Effect.invisible) == true) {
			attackRoll -= 5;
		}
		if(affectedBy(Effect.invisible) == true) {
			if(this.stealthLevel() >= 3) {
				attackRoll = 20;
			}else {
				attackRoll += 5;
			}
		}
		if(this.isPlayer()) {
			this.ammunition().modifyStackAmount(-1);
		}
		
		int electroAmount = this.proficiencyBonus();
		if(this.weapon() != null) {
			electroAmount += this.weapon().upgradeLevel();
		}
		Damage electroDamage = new Damage(electroAmount, false, DamageType.SHOCK, this.ai.factory.effectFactory, true);
		
		if(attackRoll >= other.armorClass() || attackRoll >= 20) {
			//doAction("fire the %s at the %s for %d damage", nameOf(weapon), other.name, damage.amount());
			doAction("fire the %s at the %s", nameOf(weapon), other.name);
			if(attackRoll >= 20) {
				notify("It's a critical hit!");
			}

			other.setLastHit(this);
			if(weapon != null) {
				if(this.rangedWeaponsLevel() >= 3 && attackRoll >= 20) {
					if(weapon.usesArrowAmmunition()) {
						Item arrows = this.factory().itemFactory.newArrows(0, 0);
						arrows.setStackAmount(1);
						other.inventory().add(arrows);
					}else if(weapon.usesBoltAmmunition()) {
						Item bolts = this.factory().itemFactory.newBolts(0, 0);
						bolts.setStackAmount(1);
						other.inventory().add(bolts);
					}else if(weapon.usesPowderAmmunition()) {
						Item powder = this.factory().itemFactory.newPowder(0, 0);
						powder.setStackAmount(1);
						other.inventory().add(powder);
					}
				}
				other.damage(damage, String.format("Killed by a %s using a %s", this.name, this.weaponName()));
				
			}else {
				other.damage(damage, String.format("Killed by a %s", this.name));
			}
			if(this.affectedBy(Effect.electrocharged)) {
				if(weapon != null) {
					other.damage(electroDamage, String.format("Killed by a %s using a %s", this.name, this.weaponName()));
				}else {
					other.damage(electroDamage, String.format("Killed by a %s", this.name));
				}
			}
			if(other.hp > 0){
				this.setLastTarget(other);
			}

			if(weapon != null && weapon.enchantment() != null && other.hp() >= 1) {
				if(weapon.enchantment().checkActivation(weapon.upgradeLevel()*this.intelligenceModifier())) {
					weapon.enchantment().applyToCreature(other);
				}
			}
			if(other.armor() != null && other.armor().enchantment() != null && other.hp() >= 1) {
				if(other.armor().enchantment().checkActivation(other.armor().upgradeLevel()*other.intelligenceModifier())) {
					other.armor().enchantment().applyToCreature(other);
				}
			}
			if(other.shield() != null && other.shield().enchantment() != null && other.hp() >= 1) {
				if(other.shield().enchantment().checkActivation(other.shield().upgradeLevel()*other.intelligenceModifier())) {
					other.shield().enchantment().applyToCreature(other);
				}
			}
			if(other.isAsleep() == true) {
				other.setIsAsleep(false);
				other.doAction("wake up");
			}
			if(other.isDisguised() == true) {
				other.reveal();
			}
		}else {
			doAction("fail to hit the %s", other.name);
		}


		if(affectedBy(Effect.invisible) == true) {
			this.cureEffectOfType(Effect.invisible);
		}

		//if(other.hp < 1) {
		//gainXP(other);
		//}
	}

	public void pickup() {
		Item item = world.item(x, y, z);	
		if((inventory.isFull() && !item.isGold()) || item == null) {
			doAction("grab fruitlessly at the ground");
		}else if(item.isGold()){
			this.modifyGold(item.currentGoldValue());
			doAction(String.format("pick up %d gold", item.currentGoldValue()));
			world.remove(item);
		}else {
			item.setOwner(this);
			if(item.stackAmount() > 1) {
				doAction(String.format("pick up %d %ss", item.stackAmount(), nameOf(item)));
			}else {
				doAction("pick up a %s", nameOf(item));
			}
			world.remove(x, y, z);
			if(nameOf(item) == item.name()) {
				item.setIsIdentified(true);
			}
			inventory.add(item);
			stackItems();
			if (item.isThrownWeapon()){
				if (item.getWasCreatureWepon() && this.weapon() == null && (item.getOwner().equals(this))) {
					doAction("wield a "+nameOf(item));
					this.equip(item);
					//weapon = item;
					//weaponName = item.name();
				}
				else {
					item.setWasCreatureWepon(false);
				}
			}
			if(item.isInQuickslot1() || item.isInQuickslot2() || item.isInQuickslot3() || item.isInQuickslot4() || item.isInQuickslot5() || item.isInQuickslot6()) {
				if(item.isInQuickslot1()) {
					if(quickslot_1 == null) {
						this.equipToQuickslot(item, 1);
					}else {
						item.removeQuickslotsFromMemory();
					}
				}else if(item.isInQuickslot2()) {
					if(quickslot_2 == null) {
						this.equipToQuickslot(item, 2);
					}else {
						item.removeQuickslotsFromMemory();
					}
				}else if(item.isInQuickslot3()) {
					if(quickslot_3 == null) {
						this.equipToQuickslot(item, 3);
					}else {
						item.removeQuickslotsFromMemory();
					}
				}else if(item.isInQuickslot4()) {
					if(quickslot_4 == null) {
						this.equipToQuickslot(item, 4);
					}else {
						item.removeQuickslotsFromMemory();
					}
				}else if(item.isInQuickslot5()) {
					if(quickslot_5 == null) {
						this.equipToQuickslot(item, 5);
					}else {
						item.removeQuickslotsFromMemory();
					}
				}else if(item.isInQuickslot6()) {
					if(quickslot_6 == null) {
						this.equipToQuickslot(item, 6);
					}else {
						item.removeQuickslotsFromMemory();
					}
				}
			}
		}
	}

	public void drop(Item item){
		if((item == weapon || item == armor || item == shield || item == ring || item == ammunition || item == quickslot_1 || item == quickslot_2 || item == quickslot_3 || item == quickslot_4 || item == quickslot_5 || item == quickslot_6) && item.curse() != null) {
			notify("The "+nameOf(item)+" is cursed! You can't let go of it!");
		}else if (world.addAtEmptySpace(item, x, y, z)){
			if(isDead) {
				doActionWhenDead("drop a " + nameOf(item));
			}
			if(item.stackAmount() > 1) {
				doAction(String.format("drops %d %ss", item.stackAmount(), nameOf(item)));
			}else {
				doAction("drop a " + nameOf(item));
			}
			unequip(item);
			inventory.remove(item);
		} else {
			notify("There's nowhere to drop the %s.", nameOf(item));
		}
	}

	public void getRidOf(Item item) {
		unequip(item);
		inventory.remove(item);
	}

	private void putAt(Item item, int wx, int wy, int wz) {
		unequip(item);
		inventory.remove(item);
		world.addAtEmptySpace(item, wx, wy, wz);
	}

	public void unequip(Item item) {
		if(item == null) {
			return;
		}//else
		if(item == weapon) {
			doAction("put away a "+nameOf(item));
			weapon = null;
			//weaponName = "None";
		}else if(item == armor) {
			doAction("remove a "+nameOf(item));
			armor = null;
			//armorName = "None";
		}else if(item == shield) {
			doAction("put away a "+nameOf(item));
			shield = null;
			//shieldName = "None";
		}else if(item == ring) {
			doAction("remove a "+nameOf(item));
			ring = null;
			//ringName = "None";
		}else if(item == ammunition) {
			doAction("put away a "+nameOf(item));
			ammunition = null;
			//ringName = "None";
		}
	}

	public void equip(Item item) {
		if(!item.isEquippable()) {
			return;
		}
		if(item.isWeapon()) {
			if(item == weapon) {
				if(weapon.curse() != null) {
					notify("Your "+nameOf(weapon)+" is cursed! You can't let go of it!");
				}else {
					unequip(weapon);
				}

			}else {
				if(weapon != null && weapon.curse() != null) {
					notify("Your "+nameOf(weapon)+" is cursed! You can't let go of it!");
				}else if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
					notify("You aren't skilled enough to use the "+nameOf(item)+".");
				}else if(item.isTwoHanded() && shield != null){
					notify("The "+nameOf(item)+" is too unwieldy to use alongside your "+nameOf(shield)+"!");
				}else {
					if(item.curse() != null) {
						if(item.isCurseKnown()) {
							notify("The "+nameOf(item)+" is cursed!");
							notify("It's probably best not to equip it.");
						}else if(this.intelligenceRoll() > 15) {
							notify("Your senses warn you of a foul magic lurking within the "+nameOf(item)+".");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}else {
							unequip(weapon);
							doAction("wield a "+nameOf(item));
							weapon = item;
							weaponName = item.name();
							notify("As you wield the "+nameOf(item)+", a foul magic grips you!");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}
					}else {
						unequip(weapon);
						doAction("wield a "+nameOf(item));
						weapon = item;
						weaponName = item.name();
						item.setWasCreatureWepon(true);
					}

				}

			}

		}
		else if(item.isArmor()) {
			if(item == armor) {
				if(armor.curse() != null) {
					notify("Your "+nameOf(armor)+" is cursed! You can't take it off!");
				}else {
					unequip(armor);
				}

			}else {
				if(armor != null && armor.curse() != null) {
					notify("Your "+nameOf(armor)+" is cursed! You can't take it off!");
				}else if((item.isMediumArmor() && this.armorTrainingLevel() < 1) || (item.isHeavyArmor() && this.armorTrainingLevel() < 2)) {
					notify("You aren't skilled enough to use the "+nameOf(item)+".");
				}else {
					if(item.curse() != null) {
						if(item.isCurseKnown()) {
							notify("The "+nameOf(item)+" is cursed!");
							notify("It's probably best not to equip it.");
						}else if(this.intelligenceRoll() > 15) {
							notify("Your senses warn you of a foul magic lurking within the "+nameOf(item)+".");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}else {
							unequip(armor);
							doAction("put on a "+nameOf(item));
							armor = item;
							armorName = item.name();
							notify("As you put on the "+nameOf(item)+", a foul magic grips you!");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}
					}else {
						unequip(armor);
						doAction("put on a "+nameOf(item));
						armor = item;
						armorName = item.name();
					}

				}

			}

		}else if(item.isShield()) {
			if(item == shield) {
				if(shield.curse() != null) {
					notify("Your "+nameOf(shield)+" is cursed! You can't put it down!");
				}else {
					unequip(shield);
				}

			}else {
				if(shield != null && shield.curse() != null) {
					notify("Your "+nameOf(shield)+" is cursed! You can't put it down!");
				}else if(((this.armorTrainingLevel() < 1) || (item.isTowerShield() && this.armorTrainingLevel() < 2))) {
					notify("You aren't skilled enough to use the "+nameOf(item)+".");
				}else if(weapon != null && weapon.isTwoHanded()){
					notify("The "+nameOf(item)+" is too unwieldy to use alongside your "+nameOf(weapon)+"!");
				}else {
					if(item.curse() != null) {
						if(item.isCurseKnown()) {
							notify("The "+nameOf(item)+" is cursed!");
							notify("It's probably best not to equip it.");
						}else if(this.intelligenceRoll() > 15) {
							notify("Your senses warn you of a foul magic lurking within the "+nameOf(item)+".");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}else {
							unequip(shield);
							doAction("ready a "+nameOf(item));
							shield = item;
							shieldName = item.name();
							notify("As you ready the "+nameOf(item)+", a foul magic grips you!");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}
					}else {
						unequip(shield);
						doAction("ready a "+nameOf(item));
						shield = item;
						shieldName = item.name();
					}

				}

			}

		}else if(item.isRing()) {
			if(item == ring) {
				if(ring.curse() != null) {
					notify("Your "+nameOf(ring)+" is cursed! You can't take it off!");
				}else {
					unequip(ring);
				}

			}else {
				if(ring != null && ring.curse() != null) {
					notify("Your "+nameOf(ring)+" is cursed! You can't take it off!");
				}else {
					if(item.curse() != null) {
						if(item.isCurseKnown()) {
							notify("The "+nameOf(item)+" is cursed!");
							notify("It's probably best not to equip it.");
						}else if(this.intelligenceRoll() > 15) {
							notify("Your senses warn you of a foul magic lurking within the "+nameOf(item)+".");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}else {
							unequip(ring);
							doAction("put on a "+nameOf(item));
							ring = item;
							ringName = item.name();
							notify("As you put on the "+nameOf(item)+", a foul magic grips you!");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}
					}else {
						unequip(ring);
						doAction("put on a "+nameOf(item));
						ring = item;
						ringName = item.name();
					}

				}

			}
		}else if(item.isAmmunition()) {
			if(item == ammunition) {
				if(ammunition.curse() != null) {
					notify("Your "+nameOf(ammunition)+" is cursed! You can't take it off!");
				}else {
					unequip(ammunition);
				}

			}else {
				if(ammunition != null && ammunition.curse() != null) {
					notify("Your "+nameOf(ammunition)+" is cursed! You can't put it down!");
				}else {
					if(item.curse() != null) {
						if(item.isCurseKnown()) {
							notify("The "+nameOf(item)+" is cursed!");
							notify("It's probably best not to equip it.");
						}else if(this.intelligenceRoll() > 15) {
							notify("Your senses warn you of a foul magic lurking within the "+nameOf(item)+".");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}else {
							unequip(ammunition);
							doAction("ready a "+nameOf(item));
							ammunition = item;
							ammunitionName = item.name();
							notify("As you ready the "+nameOf(item)+", a foul magic grips you!");
							notify("The "+nameOf(item)+" is cursed!");
							item.setCurseKnown(true);
						}
					}else {
						unequip(ammunition);
						doAction("ready a "+nameOf(item));
						ammunition = item;
						ammunitionName = item.name();
					}

				}

			}
		}
	}
	public void equipToQuickslot(Item item, int slot) {
		if(!item.isQuickslottable()) {
			return;
		}
		item.removeQuickslotsFromMemory();
		if(item == quickslot_1 && slot != 1) {
			quickslot_1 = null;
		}else if(item == quickslot_2 && slot != 2) {
			quickslot_2 = null;
		}else if(item == quickslot_3 && slot != 3) {
			quickslot_3 = null;
		}else if(item == quickslot_4 && slot != 4) {
			quickslot_4 = null;
		}else if(item == quickslot_5 && slot != 5) {
			quickslot_5 = null;
		}else if(item == quickslot_6 && slot != 6) {
			quickslot_6 = null;
		}
		switch(slot) {
		case 1:
			if(item == quickslot_1()) {
				item.removeQuickslotsFromMemory();
				quickslot_1 = null;
				return;
			}else if(quickslot_1() != null) {
				quickslot_1().removeQuickslotsFromMemory();
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot1();
				quickslot_1 = item;
				return;
			}else {
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot1();
				quickslot_1 = item;
				return;
			}
			
		case 2:
			if(item == quickslot_2()) {
				item.removeQuickslotsFromMemory();
				quickslot_2 = null;
				return;
			}if(quickslot_2() != null) {
				quickslot_2().removeQuickslotsFromMemory();
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot2();
				quickslot_2 = item;
				return;
			}else {
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot2();
				quickslot_2 = item;
				return;
			}
		case 3:
			if(item == quickslot_3()) {
				item.removeQuickslotsFromMemory();
				quickslot_3 = null;
				return;
			}if(quickslot_3() != null) {
				quickslot_3().removeQuickslotsFromMemory();
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot3();
				quickslot_3 = item;
				return;
			}else {
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot3();
				quickslot_3 = item;
				return;
			}
		case 4:
			if(item == quickslot_4()) {
				item.removeQuickslotsFromMemory();
				quickslot_4 = null;
				return;
			}if(quickslot_4() != null) {
				quickslot_4().removeQuickslotsFromMemory();
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot4();
				quickslot_4 = item;
				return;
			}else {
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot4();
				quickslot_4 = item;
				return;
			}
		case 5:
			if(item == quickslot_5()) {
				item.removeQuickslotsFromMemory();
				quickslot_5 = null;
				return;
			}if(quickslot_5() != null) {
				quickslot_5().removeQuickslotsFromMemory();
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot5();
				quickslot_5 = item;
				return;
			}else {
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot5();
				quickslot_5 = item;
				return;
			}
		case 6:
			if(item == quickslot_6()) {
				item.removeQuickslotsFromMemory();
				quickslot_6 = null;
				return;
			}if(quickslot_6() != null) {
				quickslot_6().removeQuickslotsFromMemory();
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot6();
				quickslot_6 = item;
				return;
			}else {
				item.removeQuickslotsFromMemory();
				item.setIsInQuickslot6();
				quickslot_6 = item;
				return;
			}
		default: return;
		}
	}
	
	public Screen useItemFromQuickslot(int slot, int sx, int sy) {
		switch(slot) {
		case 1: 
			if(quickslot_1() == null) {
				return null;
			}
			if(quickslot_1().isThrownWeapon()) {
				return new ThrowAtScreen(this, sx, sy, quickslot_1());
			}else if(quickslot_1().isPotion()) {
				if(quickslot_1().isNegativePotion() && quickslot_1().isIdentified()) {
					return new ThrowAtScreen(this, sx, sy, quickslot_1());
				}else {
					//this.quaff(quickslot_1());
					this.ai().playerAIQuaffItem(quickslot_1());
					return null;
				}
			}else if(quickslot_1().isWand() || quickslot_1().isScroll()) {
				if(quickslot_1().writtenSpells().size() == 1 && !quickslot_1().writtenSpells().get(0).isSelfCast()) {
					return new SpellTargetingScreen(this, String.format("Cast %s at?", quickslot_1().writtenSpells().get(0).name()), sx, sy, quickslot_1().writtenSpells().get(0), quickslot_1());
				}else if(quickslot_1().writtenSpells().size() == 1 && quickslot_1().writtenSpells().get(0).isSelfCast()){
					//this.castSpell(quickslot_1().writtenSpells().get(0), this.x(), this.y(), quickslot_1());
					this.ai().playerAICastSpell(quickslot_1(), quickslot_1().writtenSpells().get(0), this.x(), this.y());
					return null;
				}else {
					return new SpellSelectScreen(this, sx, sy, quickslot_1());
				}
			}else {
				return null;
			}
		case 2: 
			if(quickslot_2() == null) {
				return null;
			}
			if(quickslot_2().isThrownWeapon()) {
				return new ThrowAtScreen(this, sx, sy, quickslot_2());
			}else if(quickslot_2().isPotion()) {
				if(quickslot_2().isNegativePotion() && quickslot_2().isIdentified()) {
					return new ThrowAtScreen(this, sx, sy, quickslot_2());
				}else {
					//this.quaff(quickslot_1());
					this.ai().playerAIQuaffItem(quickslot_2());
					return null;
				}
			}else if(quickslot_2().isWand() || quickslot_2().isScroll()) {
				if(quickslot_2().writtenSpells().size() == 1 && !quickslot_2().writtenSpells().get(0).isSelfCast()) {
					return new SpellTargetingScreen(this, String.format("Cast %s at?", quickslot_2().writtenSpells().get(0).name()), sx, sy, quickslot_2().writtenSpells().get(0), quickslot_2());
				}else if(quickslot_2().writtenSpells().size() == 1 && quickslot_2().writtenSpells().get(0).isSelfCast()){
					//this.castSpell(quickslot_1().writtenSpells().get(0), this.x(), this.y(), quickslot_1());
					this.ai().playerAICastSpell(quickslot_2(), quickslot_2().writtenSpells().get(0), this.x(), this.y());
					return null;
				}else {
					return new SpellSelectScreen(this, sx, sy, quickslot_2());
				}
			}else {
				return null;
			}
		case 3: 
			if(quickslot_3() == null) {
				return null;
			}
			if(quickslot_3().isThrownWeapon()) {
				return new ThrowAtScreen(this, sx, sy, quickslot_3());
			}else if(quickslot_3().isPotion()) {
				if(quickslot_3().isNegativePotion() && quickslot_3().isIdentified()) {
					return new ThrowAtScreen(this, sx, sy, quickslot_3());
				}else {
					//this.quaff(quickslot_1());
					this.ai().playerAIQuaffItem(quickslot_3());
					return null;
				}
			}else if(quickslot_3().isWand() || quickslot_3().isScroll()) {
				if(quickslot_3().writtenSpells().size() == 1 && !quickslot_3().writtenSpells().get(0).isSelfCast()) {
					return new SpellTargetingScreen(this, String.format("Cast %s at?", quickslot_3().writtenSpells().get(0).name()), sx, sy, quickslot_3().writtenSpells().get(0), quickslot_3());
				}else if(quickslot_3().writtenSpells().size() == 1 && quickslot_3().writtenSpells().get(0).isSelfCast()){
					//this.castSpell(quickslot_1().writtenSpells().get(0), this.x(), this.y(), quickslot_1());
					this.ai().playerAICastSpell(quickslot_3(), quickslot_3().writtenSpells().get(0), this.x(), this.y());
					return null;
				}else {
					return new SpellSelectScreen(this, sx, sy, quickslot_3());
				}
			}else {
				return null;
			}
		case 4: 
			if(quickslot_4() == null) {
				return null;
			}
			if(quickslot_4().isThrownWeapon()) {
				return new ThrowAtScreen(this, sx, sy, quickslot_4());
			}else if(quickslot_4().isPotion()) {
				if(quickslot_4().isNegativePotion() && quickslot_4().isIdentified()) {
					return new ThrowAtScreen(this, sx, sy, quickslot_4());
				}else {
					//this.quaff(quickslot_1());
					this.ai().playerAIQuaffItem(quickslot_4());
					return null;
				}
			}else if(quickslot_4().isWand() || quickslot_4().isScroll()) {
				if(quickslot_4().writtenSpells().size() == 1 && !quickslot_4().writtenSpells().get(0).isSelfCast()) {
					return new SpellTargetingScreen(this, String.format("Cast %s at?", quickslot_4().writtenSpells().get(0).name()), sx, sy, quickslot_4().writtenSpells().get(0), quickslot_4());
				}else if(quickslot_4().writtenSpells().size() == 1 && quickslot_4().writtenSpells().get(0).isSelfCast()){
					//this.castSpell(quickslot_1().writtenSpells().get(0), this.x(), this.y(), quickslot_1());
					this.ai().playerAICastSpell(quickslot_4(), quickslot_4().writtenSpells().get(0), this.x(), this.y());
					return null;
				}else {
					return new SpellSelectScreen(this, sx, sy, quickslot_4());
				}
			}else {
				return null;
			}
		case 5: 
			if(quickslot_5() == null) {
				return null;
			}
			if(quickslot_5().isThrownWeapon()) {
				return new ThrowAtScreen(this, sx, sy, quickslot_5());
			}else if(quickslot_5().isPotion()) {
				if(quickslot_5().isNegativePotion() && quickslot_5().isIdentified()) {
					return new ThrowAtScreen(this, sx, sy, quickslot_5());
				}else {
					//this.quaff(quickslot_1());
					this.ai().playerAIQuaffItem(quickslot_5());
					return null;
				}
			}else if(quickslot_5().isWand() || quickslot_5().isScroll()) {
				if(quickslot_5().writtenSpells().size() == 1 && !quickslot_5().writtenSpells().get(0).isSelfCast()) {
					return new SpellTargetingScreen(this, String.format("Cast %s at?", quickslot_5().writtenSpells().get(0).name()), sx, sy, quickslot_5().writtenSpells().get(0), quickslot_5());
				}else if(quickslot_5().writtenSpells().size() == 1 && quickslot_5().writtenSpells().get(0).isSelfCast()){
					//this.castSpell(quickslot_1().writtenSpells().get(0), this.x(), this.y(), quickslot_1());
					this.ai().playerAICastSpell(quickslot_5(), quickslot_5().writtenSpells().get(0), this.x(), this.y());
					return null;
				}else {
					return new SpellSelectScreen(this, sx, sy, quickslot_5());
				}
			}else {
				return null;
			}
		case 6: 
			if(quickslot_6() == null) {
				return null;
			}
			if(quickslot_6().isThrownWeapon()) {
				return new ThrowAtScreen(this, sx, sy, quickslot_6());
			}else if(quickslot_6().isPotion()) {
				if(quickslot_6().isNegativePotion() && quickslot_6().isIdentified()) {
					return new ThrowAtScreen(this, sx, sy, quickslot_6());
				}else {
					//this.quaff(quickslot_1());
					this.ai().playerAIQuaffItem(quickslot_6());
				}
			}else if(quickslot_6().isWand() || quickslot_6().isScroll()) {
				if(quickslot_6().writtenSpells().size() == 1 && !quickslot_6().writtenSpells().get(0).isSelfCast()) {
					return new SpellTargetingScreen(this, String.format("Cast %s at?", quickslot_6().writtenSpells().get(0).name()), sx, sy, quickslot_6().writtenSpells().get(0), quickslot_6());
				}else if(quickslot_6().writtenSpells().size() == 1 && quickslot_6().writtenSpells().get(0).isSelfCast()){
					//this.castSpell(quickslot_1().writtenSpells().get(0), this.x(), this.y(), quickslot_1());
					this.ai().playerAICastSpell(quickslot_6(), quickslot_6().writtenSpells().get(0), this.x(), this.y());
					return null;
				}else {
					return new SpellSelectScreen(this, sx, sy, quickslot_6());
				}
			}else {
				return null;
			}
			
			//TODO update throw, quaff, and read to empty the quickslot, update get to automatically return items to their relevant quickslots
		
		default: return null;
		}
	}

	public void summon(Creature other) {
		world.add(other);
	}
	//jump
	public void gainXP(Creature other) {
		int amount = (int)((other.maxHP() + other.baseArmorClass())*0.75);
		if(other.hasNoXP()) {
			amount = 0;
		}
		if(amount > 0) {
			modifyXP(amount);
		}
	}
	
	private double scalingFactorWithDepth = 1.0;
	public double scalingFactorWithDepth() {
		return scalingFactorWithDepth;
	}
	public void setScalingFactorWithDepth(double value) {
		scalingFactorWithDepth = value;
	}
	public void scaleHPWithDepth(int depth) {
		this.setMaxHP((int) ((int) this.maxHP()+((depth+1)*this.scalingFactorWithDepth())));
	}
	public void scaleManaWithDepth(int depth) {
		this.setMaxMana((int) ((int) this.maxMana()+((depth+1)*this.scalingFactorWithDepth())));
	}
	public void scaleStrengthWithDepth(int depth) {
		this.setStrength((int) ((int) this.strength()+((depth+1)*this.scalingFactorWithDepth())));
	}
	public void scaleDexterityWithDepth(int depth) {
		this.setDexterity((int) ((int) this.dexterity()+((depth+1)*this.scalingFactorWithDepth())));
	}
	public void scaleIntelligenceWithDepth(int depth) {
		this.setIntelligence((int) ((int) this.intelligence()+((depth+1)*this.scalingFactorWithDepth())));
	}



	private void leaveCorpse() {
		if(!hasNoCorpse) {
			Item corpse = new Item('%', defaultColor, name + " corpse", null);
			corpse.modifyFoodValue(maxHP * 10);
			corpse.setIsStackable(true);
			corpse.setID(5000+1+this.id());
			corpse.setIsCorpse(true);
			world.addAtEmptySpace(corpse, x, y, z);
		}
		for(int i = 0; i < inventory.getItems().size(); i++) {
			if(inventory.getItems().get(i) != null) {
				drop(inventory.getItems().get(i));
			}
		}
	}

	public void triggerTrap(Item item) {
		doAction("trigger a trap!");
		addEffect(item.quaffEffect());
		world.remove(item);
	}

	public void quaff(Item item) {
		doAction("quaff a "+nameOf(item));
		//
		if(nameOf(item) != item.name()) {
			learnName(item);
		}
		//
		consume(item);
	}

	public void eat(Item item) {
		doAction("eat a "+nameOf(item));
		consume(item);
	}

	private void consume(Item item) {
		if(item.foodValue() < 0) {
			notify("Gross!");
		}
		addEffect(item.quaffEffect());
		modifyFood(item.foodValue());
		// TODO(dd): This looks like a hacky way of handling the heal-on-eat for players
		if(this instanceof Player && ((Player)this).playerAncestry() == "Orc") {
			heal((int) Math.ceil(item.foodValue()/100), true);
		}
		if(item.isCorpse()) {
			int effectChance = this.strengthRoll();
			if(this.fortitudeLevel() >= 2) {
				effectChance += this.proficiencyBonus();
			}
			if(effectChance < 16) {
				notify("That food tasted.. strange.");
				this.addEffect((Effect)this.ai().factory.effectFactory.corpseEffect().clone());
			}
		}
		item.modifyStackAmount(-1);
		if(item.stackAmount() <= 0 && (item == quickslot_1 || item == quickslot_2 || item == quickslot_3 || item == quickslot_4 || item == quickslot_5 || item == quickslot_6)) {
			if(item == quickslot_1) {
				quickslot_1 = null;
			}else if(item == quickslot_2) {
				quickslot_2 = null;
			}else if(item == quickslot_3) {
				quickslot_3 = null;
			}else if(item == quickslot_4) {
				quickslot_4 = null;
			}else if(item == quickslot_5) {
				quickslot_5 = null;
			}else if(item == quickslot_6) {
				quickslot_6 = null;
			}
		}
	}

	public void addEffect(Effect effect) {
		if(effect == null) {
			return;
		}
		if(effect.isNegative() && this.fortitudeLevel() >= 3) {
			int oldDuration = effect.duration();
			effect.setDuration((int)Math.ceil(oldDuration/2));
		}
		effect.start(this);
		effects.add(effect);
	}

	private void updateEffects() {
		List<Effect> done = new ArrayList<Effect>();
		for(Effect effect : effects) {
			effect.update(this);
			if(effect.isDone()) {
				effect.end(this);
				done.add(effect);
			}
		}
		effects.removeAll(done);
	}

	public void cureNegativeEffects() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isNegative()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}
	
	public void cureEffectOfType(String type) {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.type() == type) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}
	
	public void stackEffectOfType(String type) {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.type() == type) {
				stacked.add(effect);
			}
		}
		if(stacked.size() > 0) {
			Effect master = stacked.get(0);
			for(int i = 1; i < stacked.size(); i++) {
				master.modifyDuration(stacked.get(i).durationBase());
				effects.remove(stacked.get(i));
			}
		}
	}

	public void updateInvisibility() {
		if(!affectedBy(Effect.invisible) && color == ExtendedAsciiPanel.invisible) {
			changeColor(defaultColor);
		}
	}
	
	private int foodTimer = this.proficiencyBonus();
	
	public void modifyFood(int amount) {
		food += amount;
		if(food > maxFood) {
			//maxFood = maxFood + food / 2;
			food = maxFood;
			notify("You can't believe you can eat that much!");
			Damage overeatDamage = new Damage(2, false, DamageType.TRUE, factory().effectFactory, false);
			damage(overeatDamage, "Killed by overeating");

		}else if(food < 0 && isPlayer()) {
			if(this.fortitudeLevel() >= 1) {
				foodTimer--;
			}
			if((this.fortitudeLevel() >= 1 && foodTimer <= 0) || (this.fortitudeLevel() < 1)) {
				foodTimer = this.proficiencyBonus();
				notify("You are starving!");
				food = 0;
				Damage starveDamage = new Damage((int)(maxHP / 10), false, DamageType.TRUE, factory().effectFactory, false);
				damage(starveDamage, "Starved to death");
			}
			
		}

	}

	public boolean isPlayer() {
		return defaultGlyph == '@';
	}

	public void stackEffects() {
		for(String t : Effect.effectTypeList) {
			this.stackEffectOfType(t);
		}
	}
	
	public void stepInPit() {
		if(realTile(this.x(), this.y(), this.z()) == Tile.PIT && !this.isFlying() && !affectedBy(Effect.levitating)) {
			Effect fall = this.ai.factory.effectFactory.pitFall();
			addEffect(fall);
		}
	}
	
	public void stepInFire() {
		if(realSubtile(this.x(), this.y(), this.z()) == Tile.FIRE && !this.isFlying() && !affectedBy(Effect.levitating) && (ExtraMaths.d10() > 4)) {
			Effect fire = this.ai.factory.effectFactory.ignited(5);
			addEffect(fire);
		}
	}

	public void stepInParalysis() {
		if(realGastile(this.x(), this.y(), this.z()) == Tile.PARALYZE_GAS && (ExtraMaths.d10() > 4)) {
			Effect paralysis = this.ai.factory.effectFactory.paralysed(5);
			addEffect(paralysis);
		}
	}

	public void stepInCaustic() {
		if(realGastile(this.x(), this.y(), this.z()) == Tile.ACID_GAS && (ExtraMaths.d10() > 4)) {
			Effect caustic = this.ai.factory.effectFactory.corroded(5);
			addEffect(caustic);
		}
	}

	public void stepInConfusion() {
		if(realGastile(this.x(), this.y(), this.z()) == Tile.CONFUSE_GAS && (ExtraMaths.d10() > 4)) {
			Effect confusion = this.ai.factory.effectFactory.confused(5);
			addEffect(confusion);
		}
	}



	public void applyCurses() {
		if(weapon != null && weapon.curse() != null) {
			if(weapon.curse().checkActivation(-(weapon.upgradeLevel()*this.intelligenceModifier()))) {
				this.notify("The curse within your weapon takes hold!");
				weapon.curse().applyToCreature(this);
			}
		}
		if(armor != null && armor.curse() != null) {
			if(armor.curse().checkActivation(-(armor.upgradeLevel()*this.intelligenceModifier()))) {
				this.notify("The curse within your armor takes hold!");
				armor.curse().applyToCreature(this);
			}
		}
		if(shield != null && shield.curse() != null) {
			if(shield.curse().checkActivation(-(shield.upgradeLevel()*this.intelligenceModifier()))) {
				this.notify("The curse within your shield takes hold!");
				shield.curse().applyToCreature(this);
			}
		}
		if(ring != null && ring.curse() != null) {
			if(ring.curse().checkActivation(-(ring.upgradeLevel()*this.intelligenceModifier()))) {
				this.notify("The curse within your ring takes hold!");
				ring.curse().applyToCreature(this);
			}
		}
	}
	//jump
	public void stackItems() {
		for(int i = 0; i < 10000; i++) {
			List<Item> stacked = new ArrayList<Item>();
			List<Item> stackedDone = new ArrayList<Item>();

			for(Item item : inventory().getItems()) {
				try {
					if(item.isStackable() && item.id() == i) {
						//effect.end(this);
						stacked.add(item);
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
			if(stacked.size() > 0) {
				Item master = stacked.get(0);
				for(int z = 1; z < stacked.size(); z++) {
					master.modifyStackAmount(stacked.get(z).stackAmount());
					stackedDone.add(stacked.get(z));
					getRidOf(stacked.get(z));

				}
			}
		}
		//effects.removeAll(stackedDone);
	}
	//jump123
	public void removeItems() {
		
		for(int i = 0; i < inventory.getItems().size(); i++) {
			try {
				if(inventory.get(i).stackAmount() < 1) {
					getRidOf(inventory.get(i));
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		
		
		/*for(Item item : inventory().getItems()) {
			try {
				if(item.stackAmount() < 1) {
					getRidOf(item);
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}*/
	}

	//UPDATE
	public void update() {
		ai.onUpdate();
		//modifyFood(-1);
		//temp
		//if(hp <= 0) {
		//doAction("die");
		//if(lastHit != null) {
		//lastHit.gainXP(this);
		//}
		//leaveCorpse();
		//world.remove(this);
		//}
		regenerateHealth();
		regenerateMana();
		updateEffects();
		//temp
		stackEffects();
		applyCurses();
		stackItems();
		removeItems();
		stepInFire();
		stepInConfusion();
		stepInCaustic();
		stepInParalysis();
		stepInPit();

		updateMaxDepth();
		updateEquipmentNames();
		updateInvisibility();


		if(ammunition != null && ammunition.ammunitionAmount() < 1) {
			notify("Your "+ammunition.name()+" is empty!");
			getRidOf(ammunition);
		}else {

		}
		if(isPlayer() == true) {
			search(18, true);
		}
	}
	//jump
	private void regenerateHealth() {
		regenHPCooldown -= 1;
		if(regenHPCooldown <= 0) {
			int amount = (int)Math.ceil(this.maxHP()*this.healthRegenPercentage());
			heal(amount, true);
			modifyFood(-1);
			regenHPCooldown = 3;
		}
	}

	private void regenerateMana() {
		regenManaCooldown -= 1;
		if(regenManaCooldown <= 0) {
			int regen = (int)Math.ceil(this.maxHP()*this.manaRegenPercentage());
			gainMana(regen, true);
			regenManaCooldown = 3;
		}
	}

	private void updateEquipmentNames() {
		if(weapon != null) {
			weaponName = weapon.name();
		}
		if(armor != null) {
			armorName = armor.name();
		}
		if(shield != null) {
			shieldName = shield.name();
		}
		if(ring != null) {
			ringName = ring.name();
		}
		if(ammunition != null) {
			ammunitionName = ammunition.name();
		}
	}

	public boolean canEnter(int wx, int wy, int wz) {
		//fixed
		return world.tile(wx, wy, wz).isGround() && !world.tile(wx, wy, wz).isBars() && world.creature(wx, wy, wz) == null;
	}

	public void notify(String message, Object ... params) {
		ai.onNotify(String.format(message, params));
	}

	public void doAction(String message, Object ... params) {
		if(!isDead) {
			for(Creature other : getCreaturesWhoSeeMe()) {
				if(other == this) {
					other.notify("You " + message + ".", params);
				}else {
					other.notify(String.format("The %s %s.", name, makeThirdPerson(message)), params);
				}
			}
		}
	}

	public void doActionWhenDead(String message, Object ... params) {
		for(Creature other : getCreaturesWhoSeeMe()) {
			if(other == this) {
				other.notify("You " + message + ".", params);
			}else {
				other.notify(String.format("The %s %s.", name, makeThirdPerson(message)), params);
			}
		}
	}

	public void doAction(Item item, String message, Object ... params) {
		if(hp<1) {
			return;
		}

		for(Creature other : getCreaturesWhoSeeMe()) {
			if(other == this) {
				other.notify("You " + message + ".", params);
			}else {
				other.notify(String.format("The %s %s.", name, makeThirdPerson(message)), params);
			}
			//other.learnName(item);
			if(other.nameOf(item) != item.name()) {
				other.learnName(item);
			}
		}
	}

	private List<Creature> getCreaturesWhoSeeMe(){
		List<Creature> others = new ArrayList<Creature>();
		int r = 9;
		for (int ox = -r; ox < r+1; ox++){
			for (int oy = -r; oy < r+1; oy++){
				if (ox*ox + oy*oy > r*r)
					continue;

				Creature other = world.creature(x+ox, y+oy, z);

				if (other == null)
					continue;

				others.add(other);
			}
		}
		return others;
	}
	//jump
	public void castSpell(Spell spell, int x2, int y2, Item item) {
		Creature other = creature(x2, y2, z);

		if(spell.manaCost() > mana) {
			doAction("point and mutter to no avail...");
			return;
		}
		//cast on creature
		if(spell.castOnTile()) {
			//Creature tileSpell = ai().factory.newTileSpell(this.z(), this, 0);
			if(other != null) {
				doAction("point and mutter at nothing in particular...");
				if(spell.manaCost() > 0) {
					loseMana(spell.manaCost(), false);
				}
				return;
			}
			if(item != null) {
				if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
					notify("The spell housed within the "+nameOf(item)+" is too complex to understand...");
					return;
				}
			}
			Creature tileSpell = ai().factory.creatureFactory.newTileSpell(this.z(), this, false);
			ai().world.addCreatureAtLocation(tileSpell, x2, y2, z);
			tileSpell.addEffect(spell.effect());
			tileSpell.update();
			//
			//ai().world.remove(tileSpell);
			//
			//other.setLastHit(this);
			if(spell.manaCost() > 0) {
				loseMana(spell.manaCost(), false);
			}
			if(item != null) {
				if(item.isWand() && (this.nameOf(item) != item.name())) {
					this.learnName(item);
				}
				if(item.isScroll()) {
					if(this.nameOf(item) != item.name()) {
						this.learnName(item);
					}
					notify("The magic of the "+nameOf(item)+" fades away!");
					item.modifyStackAmount(-1);
					if(item.stackAmount() <= 0 && (item == quickslot_1 || item == quickslot_2 || item == quickslot_3 || item == quickslot_4 || item == quickslot_5 || item == quickslot_6)) {
						if(item == quickslot_1) {
							quickslot_1 = null;
						}else if(item == quickslot_2) {
							quickslot_2 = null;
						}else if(item == quickslot_3) {
							quickslot_3 = null;
						}else if(item == quickslot_4) {
							quickslot_4 = null;
						}else if(item == quickslot_5) {
							quickslot_5 = null;
						}else if(item == quickslot_6) {
							quickslot_6 = null;
						}
					}
				}
			}
			if(affectedBy(Effect.invisible) == true) {
				this.cureEffectOfType(Effect.invisible);
			}

		}else {
			if(other == null || other.isContainer() == true || other.isDisguised() == true) {
				doAction("point and mutter at nothing in particular...");
				if(spell.manaCost() > 0) {
					loseMana(spell.manaCost(), false);
				}
				return;
			}
			if(item != null) {
				if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
					notify("The spell housed within the "+nameOf(item)+" is too complex to understand...");
					return;
				}
			}

			other.addEffect(spell.effect());
			//other.setLastHit(this);
			if(spell.manaCost() > 0) {
				loseMana(spell.manaCost(), false);
			}
			if(item != null) {
				if(item.isWand() && (this.nameOf(item) != item.name())) {
					this.learnName(item);
				}
				if(item.isScroll()) {
					if(this.nameOf(item) != item.name()) {
						this.learnName(item);
					}
					notify("The magic of the "+nameOf(item)+" fades away!");
					item.modifyStackAmount(-1);
					if(item.stackAmount() <= 0 && (item == quickslot_1 || item == quickslot_2 || item == quickslot_3 || item == quickslot_4 || item == quickslot_5 || item == quickslot_6)) {
						if(item == quickslot_1) {
							quickslot_1 = null;
						}else if(item == quickslot_2) {
							quickslot_2 = null;
						}else if(item == quickslot_3) {
							quickslot_3 = null;
						}else if(item == quickslot_4) {
							quickslot_4 = null;
						}else if(item == quickslot_5) {
							quickslot_5 = null;
						}else if(item == quickslot_6) {
							quickslot_6 = null;
						}
					}
				}
			}
			if(affectedBy(Effect.invisible) == true) {
				this.cureEffectOfType(Effect.invisible);
			}
		}


	}
	
	public void learnSpell(Spell spell, Item item) {
		if(this.spellbook().isSpellKnown(spell)) {
			notify("You have already committed that spell to memory!");
		}else {
			if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
				notify("The spellbook is too complex to understand...");
			}else {
				notify("You learn how to cast "+spell.name()+"!");
				this.spellbook().add(spell);
				if(this.nameOf(item) != item.name()) {
					this.learnName(item);
				}
				this.notify("The magic of the "+this.nameOf(item)+" fades away!");
				item.modifyStackAmount(-1);
				if(item.stackAmount() <= 0 && (item == quickslot_1 || item == quickslot_2 || item == quickslot_3 || item == quickslot_4 || item == quickslot_5 || item == quickslot_6)) {
					if(item == quickslot_1) {
						quickslot_1 = null;
					}else if(item == quickslot_2) {
						quickslot_2 = null;
					}else if(item == quickslot_3) {
						quickslot_3 = null;
					}else if(item == quickslot_4) {
						quickslot_4 = null;
					}else if(item == quickslot_5) {
						quickslot_5 = null;
					}else if(item == quickslot_6) {
						quickslot_6 = null;
					}
				}
			}
			
		}
	}

	private String makeThirdPerson(String text) {
		String[] words = text.split(" ");
		words[0] = words[0] + "s";

		StringBuilder builder = new StringBuilder();
		for(String word : words) {
			builder.append(" ");
			builder.append(word);

		}
		return builder.toString().trim();

	}

	public void search(int successChance, boolean passive) {
		int searchRadius = (int)(visionRadius / 3);
		int success = 0;
		int bonus = 0;
		if(this.perceptionLevel() >= 1) {
			bonus = this.proficiencyBonus();
		}
		if(this.stealthLevel() >= 2 && !passive) {
			if(this.mana() >= (this.proficiencyBonus()*2)) {
				Effect sneak = (Effect)this.ai().factory.effectFactory.invisible(this.proficiencyBonus()).clone();
				this.loseMana(this.proficiencyBonus()*2, false);
				this.addEffect(sneak);
				notify("You blend into the shadows.");
			}else {
				notify("You don't have the energy to sneak..");
			}
		}
		for (int ox = -searchRadius; ox < searchRadius+1; ox++){
			for (int oy = -searchRadius; oy < searchRadius+1; oy++){
				int nx = x + ox;
				int ny = y + oy;
				Entity entity = world.entity(nx, ny, z);

				if (entity instanceof Trap) {
					Trap trap = (Trap) entity;
					if(this.dexterityRoll()+bonus >= successChance) {
						trap.reveal();
						notify("You spotted a "+trap.name()+"!");
						success++;
					}
				}
			}
		}
		if(!passive) {
			modifyFood(-10);
		}
		if(success == 0 && !passive) {
			notify("You didn't spot anything.");
		}
	}

	public boolean canSee(int wx, int wy, int wz) {
		//return (mindVision > 0 && world.creature(wx, wy, wz) !=null || ai.canSee(wx, wy, wz));
		if(affectedBy(Effect.mindVision) && world.creature(wx, wy, wz) !=null) {
			return true;
		}
		else {
			return ai.canSee(wx, wy, wz);
		}


	}								//creature

	public Tile realTile(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz);
	}

	public Tile realSubtile(int wx, int wy, int wz) {
		return world.subtile(wx, wy, wz);
	}

	public Tile realGastile(int wx, int wy, int wz) {
		return world.gastile(wx, wy, wz);
	}

	public Tile tile(int wx, int wy, int wz) {
		if(canSee(wx,wy,wz)) {
			return world.tile(wx, wy, wz);
		}else {
			return ai.rememberedTile(wx,wy,wz);
		}
	}

	public Creature creature(int wx, int wy, int wz) {
		if(canSee(wx,wy,wz)) {
			return world.creature(wx, wy, wz);
		}else {
			return null;
		}	
	}

	public Item item(int wx, int wy, int wz) {
		if(canSee(wx,wy,wz)) {
			try {
				return world.item(wx, wy, wz);
			} catch (Exception e) {
				return null;
				//e.printStackTrace();
			}
		}else {
			return null;
		}

	}

	public String hungerAsString() {
		if(food() <= maxFood() * 0.1) {
			return "Starving";
		}else if(food() <= maxFood() * 0.2) {
			return "Hungry";
		}else if(food() <= maxFood() * 0.5) {
			return "Satisfied";
		}else if(food() <= maxFood() * 0.8) {
			return "Stuffed";
		}else if(food() <= maxFood() * 0.9) {
			return "Full";
		}else if(food() <= maxFood()) {
			return "Full";
		}else{
			return "";
		}
	}
}
