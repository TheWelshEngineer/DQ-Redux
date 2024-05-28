package RogueLike.Main.Items;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

import RogueLike.Main.Description;
import RogueLike.Main.Dice;
import RogueLike.Main.Effect;
import RogueLike.Main.Enums.DamageType;
import RogueLike.Main.Spell;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Enchantments.Enchantment;
import RogueLike.Main.Factories.ObjectFactory;



public class Item implements Cloneable{
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
	
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	private String name;
	public String name() {
		String returnname = name;
		String upgradeLevel = "";
		String enchantmentName = "";
		String curseName = "";
		
		if(this.upgradeLevel() > 0) {
			upgradeLevel = String.format("+%d ", this.upgradeLevel());
		}
		
		if(this.curse() != null) {
			curseName = String.format("%s ", this.curse().name());
		}
		
		if(this.enchantment() != null) {
			if(this.isArmor() || this.isShield()) {
				enchantmentName = String.format("%c%s", (char)255, this.enchantment().name());
			}
			if(this.isWeapon()) {
				if(this.curse() != null) {
					enchantmentName = String.format("%s%c", this.enchantment().name(), (char)255);
				}else {
					enchantmentName = String.format("%s ", this.enchantment().name());
				}
				
			}
		}
		
		
		
		if(this.isArmor() || this.isShield()) {
			returnname = String.format("%s%s%s%s", upgradeLevel, curseName, name, enchantmentName);
		}
		
		if(this.isWeapon()) {
			returnname = String.format("%s%s%s%s", upgradeLevel, curseName, enchantmentName, name);
		}
		return returnname;
	}
	
	public void changeName(String newName) {
		name = newName;
	}
	
	private String defaultName;
	public String defaultName() {
		return defaultName;
	}
	
	private String appearance;
	public String getAppearance() {
		if(appearance == null) {
			return name;
		}
		return appearance;
	}
	
	public void setAppearance(String newAppearance) {
		appearance = newAppearance;
	}
	
	private String potionName;
	public String potionName() {
		return potionName;
	}
	
	public void setPotionName(String newName) {
		potionName = newName;
	}
	
	private int id;
	public int id() {
		return id;
	}
	
	public void setID(int value) {
		id = value;
	}
	
	private boolean isStackable;
	public boolean isStackable() {
		return isStackable;
	}
	
	public void setIsStackable(boolean value) {
		isStackable = value;
	}
	
	private int stackAmount;
	public int stackAmount() {
		return stackAmount;
	}
	
	public void modifyStackAmount(int amount) {
		stackAmount += amount;
	}
	
	public void setStackAmount(int amount) {
		stackAmount = amount;
	}
	
	private int baseGoldValue;
	public int baseGoldValue() {
		return baseGoldValue;
	}
	
	public void setBaseGoldValue(int amount) {
		baseGoldValue = amount;
	}
	
	private int currentGoldValue;
	public int currentGoldValue() {
		return currentGoldValue;
	}
	
	public void setCurrentGoldValue(int amount) {
		currentGoldValue = amount;
	}
	
	public void modifyCurrentGoldValue(int amount) {
		currentGoldValue += amount;
	}
	
	private int foodValue = 0;
	public int foodValue() {
		return foodValue;
	}
	
	public void modifyFoodValue(int amount) {
		foodValue += amount;
	}
	
	private int visionRadius;
	public int visionRadius() {
		return visionRadius;
	}
	
	public void modifyVisionRadius(int amount) {
		visionRadius += amount;
	}
	
	/*private double attackValue;
	public double attackValue() {
		return attackValue;
	}
	
	public void modifyAttackValue(double amount) {
		attackValue += amount;
	}
	
	public void setAttackValue(double amount) {
		attackValue = amount;
	}*/
	
//	private int damageDiceLowerBound = 1;
//	public int damageDiceLowerBound() {
//		return damageDiceLowerBound;
//	}
//	
//	public void modifyDamageDiceLowerBound(int amount) {
//		damageDiceLowerBound += amount;
//	}
//	
//	public void setDamageDiceLowerBound(int amount) {
//		damageDiceLowerBound = amount;
//	}
	
	private Dice damageDice;
	public Dice damageDice() {
		return damageDice;
	}
	
//	public void modifyDamageDice(int amount) {
//		damageDice += amount;
//	}
	
	public void setDamageDice(Dice dice) {
		damageDice = dice;
	}
	
	private Dice thrownDamageDice;
	public Dice thrownDamageDice() {
		return thrownDamageDice;
	}
	
//	public void modifyThrownDamageDice(int amount) {
//		thrownDamageDice += amount-1;
//	}
	
	public void setThrownDamageDice(Dice dice) {
		thrownDamageDice = dice;
	}
	
	
	// Roland code in the works
	private boolean wasCreatureWepon = false;
	
	public void setWasCreatureWepon(boolean weponState){
		wasCreatureWepon = weponState;
	}
	public boolean getWasCreatureWepon(){
		return wasCreatureWepon;
	}
	
	private Creature owner = null; 
	
	public void setOwner(Creature lowner) {
		owner = lowner;
	}
	
	public Creature getOwner() {
		return owner;
	}
	
	
	private Dice rangedDamageDice;
	public Dice rangedDamageDice() {
		return rangedDamageDice;
	}
//	
//	public void modifyRangedDamageDice(int amount) {
//		rangedDamageDice += amount;
//	}
	
	public void setRangedDamageDice(Dice dice) {
		rangedDamageDice = dice;
	}


	private DamageType baseDamageType;
	public DamageType baseDamageType() {
		return baseDamageType;
	}
	public DamageType effectiveDamageType() {
		// enchantment damage type supersedes base weapon damage type
		if (this.enchantment != null && this.enchantment.damageType != null) {
			return this.enchantment.damageType;
		}
		return this.baseDamageType;
	}
	public void setBaseDamageType(DamageType baseDamageType) {
		this.baseDamageType = baseDamageType;
	}
	
	/*private double defenseValue;
	public double defenseValue() {
		return defenseValue;
	}
	
	public void modifyDefenseValue(double amount) {
		defenseValue += amount;
	}
	
	public void setDefenseValue(double amount) {
		defenseValue = amount;
	}
	
	private double fireDefenseValue;
	public double fireDefenseValue() {
		return fireDefenseValue;
	}
	
	public void modifyFireDefenseValue(double amount) {
		fireDefenseValue += amount;
	}
	
	private double iceDefenseValue;
	public double iceDefenseValue() {
		return iceDefenseValue;
	}
	
	public void modifyIceDefenseValue(double amount) {
		iceDefenseValue += amount;
	}
	
	private double shockDefenseValue;
	public double shockDefenseValue() {
		return shockDefenseValue;
	}
	
	public void modifyShockDefenseValue(double amount) {
		shockDefenseValue += amount;
	}*/
	
	private final EnumMap<DamageType, Integer> saveBonuses;
	public int saveBonus(DamageType damageType) {
		return saveBonuses.getOrDefault(damageType, 0);
	}
	public void setSaveBonus(DamageType damageType, int bonus) {
		saveBonuses.put(damageType, bonus);
	}

	private final EnumSet<DamageType> resistances;
	private final EnumSet<DamageType> weaknesses;
	private final EnumSet<DamageType> immunities;

	public boolean grantsResistanceTo(DamageType damageType) {
		return resistances.contains(damageType);
	}
	public boolean grantsWeaknessTo(DamageType damageType) {
		return weaknesses.contains(damageType);
	}
	public boolean grantsImmunityTo(DamageType damageType) {
		return immunities.contains(damageType);
	}

	public void addResistance(DamageType damageType) {
		resistances.add(damageType);
	}
	public void addWeakness(DamageType damageType) {
		weaknesses.add(damageType);
	}
	public void addImmunity(DamageType damageType) {
		immunities.add(damageType);
	}
	
	private Effect quaffEffect;
	public Effect quaffEffect() {
		return quaffEffect;
	}
	
	public void setQuaffEffect(Effect effect) {
		this.quaffEffect = effect;
	}
	
//	public Effect enchantmentEffect;
//	public Effect enchantmentEffect() {
//		return (Effect) enchantmentEffect.clone();
//	}
//	
//	public void setEnchantmentEffect(Effect effect) {
//		//this.enchantmentEffect = effect;
//		this.enchantmentEffect = (Effect) effect.clone();
//	}
//	
//	public Effect curseEffect;
//	public Effect curseEffect() {
//		return (Effect) curseEffect.clone();
//	}
//	
//	public void setCurseEffect(Effect effect) {
//		//this.enchantmentEffect = effect;
//		this.curseEffect = (Effect) effect.clone();
//	}
//	
//	
//	
//	private boolean isEnchanted;
//	public boolean isEnchanted() {
//		return isEnchanted;
//	}
//	
//	public void setIsEnchanted(boolean value) {
//		isEnchanted = value;
//	}
	
	private Enchantment enchantment;
	public Enchantment enchantment() {
		return enchantment;
	}
	public void setEnchantment(Enchantment enchantment) {
		this.enchantment = enchantment;
	}
	
	private Enchantment curse;
	public Enchantment curse() {
		return curse;
	}
	public void setCurse(Enchantment curse) {
		this.curse = curse;
	}
	
	private List<Spell> writtenSpells;
	public List<Spell> writtenSpells(){
		return writtenSpells;
	}
	
	public void addWrittenSpell(Spell spell) {
		writtenSpells.add(spell);
	}
	
//	private boolean isDegraded;
//	public boolean isDegraded() {
//		return isDegraded;
//	}
//	public void setIsDegraded(boolean value) {
//		isDegraded = value;
//	}
	
	private boolean isCurseKnown;
	public boolean isCurseKnown() {
		return isCurseKnown;
	}
	public void setCurseKnown(boolean value) {
		isCurseKnown = value;
	}
	
	private boolean isWeapon;
	public boolean isWeapon() {
		return isWeapon;
	}
	public void setIsWeapon(boolean value) {
		isWeapon = value;
	}
	
	private boolean isRangedWeapon;
	public boolean isRangedWeapon() {
		return isRangedWeapon;
	}
	public void setIsRangedWeapon(boolean value) {
		isRangedWeapon = value;
	}
	
	private boolean isMeleeWeapon;
	public boolean isMeleeWeapon() {
		return isMeleeWeapon;
	}
	public void setIsMeleeWeapon(boolean value) {
		isMeleeWeapon = value;
	}
	
	private boolean isThrownWeapon;
	public boolean isThrownWeapon() {
		return isThrownWeapon;
	}
	public void setIsThrownWeapon(boolean value) {
		isThrownWeapon = value;
	}
	
	private boolean isTwoHanded;
	public boolean isTwoHanded() {
		return isTwoHanded;
	}
	public void setIsTwoHanded(boolean value) {
		isTwoHanded = value;
	}
	
	private boolean isExtraAttack;
	public boolean isExtraAttack() {
		return isExtraAttack;
	}
	public void setIsExtraAttack(boolean value) {
		isExtraAttack = value;
	}
	
	private boolean isVersatile;
	public boolean isVersatile() {
		return isVersatile;
	}
	public void setIsVersatile(boolean value) {
		isVersatile = value;
	}
	
	private Dice versatileDamageDice;
	public Dice versatileDamageDice() {
		return versatileDamageDice;
	}
	public void setVersatileDamageDice(Dice dice) {
		versatileDamageDice = dice;
	}
	
	private boolean isGold;
	public boolean isGold() {
		return isGold;
	}
	public void setIsGold(boolean value) {
		isGold = value;
	}
	
	private boolean isArmor;
	public boolean isArmor() {
		return isArmor;
	}
	public void setIsArmor(boolean value) {
		isArmor = value;
	}
	
	private boolean isLightArmor;
	public boolean isLightArmor() {
		return isLightArmor;
	}
	public void setIsLightArmor(boolean value) {
		isLightArmor = value;
	}
	
	private boolean isMediumArmor;
	public boolean isMediumArmor() {
		return isMediumArmor;
	}
	public void setIsMediumArmor(boolean value) {
		isMediumArmor = value;
	}
	
	private boolean isHeavyArmor;
	public boolean isHeavyArmor() {
		return isHeavyArmor;
	}
	public void setIsHeavyArmor(boolean value) {
		isHeavyArmor = value;
	}
	
	private boolean isTowerShield;
	public boolean isTowerShield() {
		return isTowerShield;
	}
	public void setIsTowerShield(boolean value) {
		isTowerShield = value;
	}
	
	private boolean isFinesse;
	public boolean isFinesse() {
		return isFinesse;
	}
	
	public void setIsFinesse(boolean value) {
		isFinesse = value;
	}
	private boolean isSimple;
	public boolean isSimple() {
		return isSimple;
	}
	
	public void modifyIsSimple(boolean value) {
		isSimple = value;
	}
	
	private boolean isMartial;
	public boolean isMartial() {
		return isMartial;
	}
	
	public void modifyIsMartial(boolean value) {
		isMartial = value;
	}
	/*
	private int isFortitude;
	public int isFortitude() {
		return isFortitude;
	}
	
	public void modifyIsFortitude(int amount) {
		isFortitude += amount;
	}
	
	private int isEvocation;
	public int isEvocation() {
		return isEvocation;
	}
	
	public void modifyIsEvocation(int amount) {
		isEvocation += amount;
	}
	
	private int isPyromancy;
	public int isPyromancy() {
		return isPyromancy;
	}
	
	public void modifyIsPyromancy(int amount) {
		isPyromancy += amount;
	}
	
	private int isCryomancy;
	public int isCryomancy() {
		return isCryomancy;
	}
	
	public void modifyIsCryomancy(int amount) {
		isCryomancy += amount;
	}
	
	private int isElectromancy;
	public int isElectromancy() {
		return isElectromancy;
	}
	
	public void modifyIsElectromancy(int amount) {
		isElectromancy += amount;
	}
	
	private int isAlchemancy;
	public int isAlchemancy() {
		return isAlchemancy;
	}
	
	public void modifyIsAlchemancy(int amount) {
		isAlchemancy += amount;
	}
	
	private int skillRestriction;
	public int skillRestriction() {
		return skillRestriction;
	}
	
	public void modifySkillRestriction(int amount) {
		skillRestriction += amount;
	}
	public void setSkillRestriction(int amount) {
		skillRestriction = amount;
	}*/
	
	private int armorClass;
	public int armorClass() {
		return armorClass;
	}
	public void modifyArmorClass(int amount) {
		armorClass += amount;
	}
	
	private int strength;
	public int strength() {
		return strength;
	}
	public void modifyStrength(int amount) {
		strength += amount;
	}
	
	private int dexterity;
	public int dexterity() {
		return dexterity;
	}
	public void modifyDexterity(int amount) {
		dexterity += amount;
	}
	
	private int intelligence;
	public int intelligence() {
		return intelligence;
	}
	public void modifyIntelligence(int amount) {
		intelligence += amount;
	}
	
	private boolean isRing;
	public boolean isRing() {
		return isRing;
	}
	public void setIsRing(boolean value) {
		isRing = value;
	}
	// AMERICANO
	private boolean isShield;
	public boolean isShield() {
		return isShield;
	}
	public void setIsShield(boolean value) {
		isShield = value;
	}
	
	private boolean isPotion;
	public boolean isPotion() {
		return isPotion;
	}
	public void setIsPotion(boolean value) {
		isPotion = value;
	}
	
	private boolean isNegativePotion;
	public boolean isNegativePotion() {
		return isNegativePotion;
	}
	public void setIsNegativePotion(boolean value) {
		isNegativePotion = value;
	}
	
	private boolean isCorpse;
	public boolean isCorpse() {
		return isCorpse;
	}
	public void setIsCorpse(boolean value) {
		isCorpse = value;
	}
	
	private boolean isEquippable;
	public boolean isEquippable() {
		return isEquippable;
	}
	public void setIsEquippable(boolean value) {
		isEquippable = value;
	}
	
	private boolean isQuickslottable = false;
	public boolean isQuickslottable() {
		return isQuickslottable || isWand() || isScroll() || isPotion() || isThrownWeapon();
	}
	public void setQuickslottable(boolean value) {
		isQuickslottable = value;
	}
	
	private boolean isInQuickslot1;
	private boolean isInQuickslot2;
	private boolean isInQuickslot3;
	private boolean isInQuickslot4;
	private boolean isInQuickslot5;
	private boolean isInQuickslot6;
	
	public boolean isInQuickslot1() {
		return isInQuickslot1;
	}
	public void setIsInQuickslot1() {
		isInQuickslot1 = true;
		isInQuickslot2 = false;
		isInQuickslot3 = false;
		isInQuickslot4 = false;
		isInQuickslot5 = false;
		isInQuickslot6 = false;
	}
	public boolean isInQuickslot2() {
		return isInQuickslot2;
	}
	public void setIsInQuickslot2() {
		isInQuickslot1 = false;
		isInQuickslot2 = true;
		isInQuickslot3 = false;
		isInQuickslot4 = false;
		isInQuickslot5 = false;
		isInQuickslot6 = false;
	}
	public boolean isInQuickslot3() {
		return isInQuickslot3;
	}
	public void setIsInQuickslot3() {
		isInQuickslot1 = false;
		isInQuickslot2 = false;
		isInQuickslot3 = true;
		isInQuickslot4 = false;
		isInQuickslot5 = false;
		isInQuickslot6 = false;
	}
	public boolean isInQuickslot4() {
		return isInQuickslot4;
	}
	public void setIsInQuickslot4() {
		isInQuickslot1 = false;
		isInQuickslot2 = false;
		isInQuickslot3 = false;
		isInQuickslot4 = true;
		isInQuickslot5 = false;
		isInQuickslot6 = false;
	}
	public boolean isInQuickslot5() {
		return isInQuickslot5;
	}
	public void setIsInQuickslot5() {
		isInQuickslot1 = false;
		isInQuickslot2 = false;
		isInQuickslot3 = false;
		isInQuickslot4 = false;
		isInQuickslot5 = true;
		isInQuickslot6 = false;
	}
	public boolean isInQuickslot6() {
		return isInQuickslot6;
	}
	public void setIsInQuickslot6() {
		isInQuickslot1 = false;
		isInQuickslot2 = false;
		isInQuickslot3 = false;
		isInQuickslot4 = false;
		isInQuickslot5 = false;
		isInQuickslot6 = true;
	}
	
	public void removeQuickslotsFromMemory() {
		isInQuickslot1 = false;
		isInQuickslot2 = false;
		isInQuickslot3 = false;
		isInQuickslot4 = false;
		isInQuickslot5 = false;
		isInQuickslot6 = false;
	}
	
	private boolean isTrap;
	public boolean isTrap() {
		return isTrap;
	}
	public void setIsTrap(boolean value) {
		isTrap = value;
	}
	
	private boolean isTrapFound;
	public boolean isTrapFound() {
		return isTrapFound;
	}
	public void setIsTrapFound(boolean value) {
		isTrapFound = value;
	}
	
	private boolean isWand;
	public boolean isWand() {
		return isWand;
	}
	public void setIsWand(boolean value) {
		isWand = value;
	}
	
	private boolean isSpellbook;
	public boolean isSpellbook() {
		return isSpellbook;
	}
	public void setIsSpellbook(boolean value) {
		isSpellbook = value;
	}
	
	private boolean isIronKey;
	public boolean isIronKey() {
		return isIronKey;
	}
	public void setIsIronKey(boolean value) {
		isIronKey = value;
	}
	
	private boolean isGoldKey;
	public boolean isGoldKey() {
		return isGoldKey;
	}
	public void setIsGoldKey(boolean value) {
		isGoldKey = value;
	}
	
	private boolean isCrystalKey;
	public boolean isCrystalKey() {
		return isCrystalKey;
	}
	public void setIsCrystalKey(boolean value) {
		isCrystalKey = value;
	}
	
	private int keyDepth;
	public int keyDepth() {
		return keyDepth;
	}
	public void setKeyDepth(int amount) {
		keyDepth = amount;
	}
	
	private boolean isAmmunition;
	public boolean isAmmunition() {
		return isAmmunition;
	}
	public void setIsAmmunition(boolean value) {
		isAmmunition = value;
	}
	
	private int ammunitionAmount;
	public int ammunitionAmount() {
		return ammunitionAmount;
	}
	public void modifyAmmunitionAmount(int amount) {
		ammunitionAmount += amount;
	}
	
	private boolean isArrowAmmunition;
	public boolean isArrowAmmunition() {
		return isArrowAmmunition;
	}
	public void setIsArrowAmmunition(boolean value) {
		isArrowAmmunition = value;
	}
	
	private boolean isBoltAmmunition;
	public boolean isBoltAmmunition() {
		return isBoltAmmunition;
	}
	public void setIsBoltAmmunition(boolean value) {
		isBoltAmmunition = value;
	}
	
	private boolean isPowderAmmuniton;
	public boolean isPowderAmmunition() {
		return isPowderAmmuniton;
	}
	public void setIsPowderAmmunition(boolean value) {
		isPowderAmmuniton = value;
	}
	
	private boolean usesArrowAmmunition;
	public boolean usesArrowAmmunition() {
		return usesArrowAmmunition;
	}
	public void setUsesArrowAmmunition(boolean value) {
		usesArrowAmmunition = value;
	}
	
	private boolean usesBoltAmmunition;
	public boolean usesBoltAmmunition() {
		return usesBoltAmmunition;
	}
	public void setUsesBoltAmmunition(boolean value) {
		usesBoltAmmunition = value;
	}
	
	private boolean usesPowderAmmunition;
	public boolean usesPowderAmmunition() {
		return usesPowderAmmunition;
	}
	public void setUsesPowderAmmunition(boolean value) {
		usesPowderAmmunition = value;
	}
	private boolean isScroll;
	public boolean isScroll() {
		return isScroll;
	}
	public void setIsScroll(boolean value) {
		isScroll = value;
	}
	
	private boolean isIdentified;
	public boolean isIdentified() {
		return isIdentified;
	}
	public void setIsIdentified(boolean value) {
		isIdentified = value;
	}
	
	private int upgradeLevel = 0;
	public int upgradeLevel() {
		return upgradeLevel;
	}
	public void modifyUpgradeLevel(int amount) {
		upgradeLevel += amount;
	}
	
	private boolean usesStrength;
	public boolean usesStrength() {
		return usesStrength;
	}
	public void setUsesStrength(boolean value) {
		usesStrength = value;
	}
	private int strengthRequirement;
	public int strengthRequirement() {
		return strengthRequirement;
	}
	public void setStrengthRequirement(int amount) {
		strengthRequirement = amount;
	}
	
	private boolean usesDexterity;
	public boolean usesDexterity() {
		return usesDexterity;
	}
	public void setUsesDexterity(boolean value) {
		usesDexterity = value;
	}
	private int dexterityRequirement;
	public int dexterityRequirement() {
		return dexterityRequirement;
	}
	public void setDexterityRequirement(int amount) {
		dexterityRequirement = amount;
	}
	
	private boolean usesIntelligence;
	public boolean usesIntelligence() {
		return usesIntelligence;
	}
	public void setUsesIntelligence(boolean value) {
		usesIntelligence = value;
	}
	private int intelligenceRequirement;
	public int intelligenceRequirement() {
		return intelligenceRequirement;
	}
	public void setIntelligenceRequirement(int amount) {
		intelligenceRequirement = amount;
	}
	
	private Description description;
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	
	
	public String details() {
		String details = "";
		if(currentGoldValue > 0) {
			details += "   Value: "+currentGoldValue+" Gold";
		}
		if(damageDice != null) {
			details += "   Damage: "+damageDice.toString();
		}
		if(rangedDamageDice != null) {
			details += "   Ranged Damage: "+rangedDamageDice.toString();
		}
		if(thrownDamageDice != null && thrownDamageDice != Dice.d1) {
			details += "   Thrown Damage: "+thrownDamageDice.toString();
		}
		if(armorClass != 0) {
			details += "   Armor Class: "+armorClass;
		}
		if(foodValue != 0) {
			details += "   Food: "+foodValue;
		}
		if(ammunitionAmount != 0) {
			details += "   Ammunition: "+ammunitionAmount;
		}
		
		return details;
	}
	
	private String trapType;
	public String trapType() {
		return trapType;
	}
	public void setTrapType(String type) {
		trapType = type;
	}
	
	public Effect perceptionTrapEffect(ObjectFactory factory, String type) {
		switch(type) {
		case Trap.fireTrap: return factory.effectFactory.magmaWard(10);
		case Trap.frostbiteTrap: return factory.effectFactory.chillWard(10);
		case Trap.lightningTrap: return factory.effectFactory.arcWard(10);
		case Trap.blinkTrap: return factory.effectFactory.bounce();
		case Trap.summoningTrap: return factory.effectFactory.invisible(10);
		default: return factory.effectFactory.magmaWard(10);
		}
	}
	
	public Item(char glyph, Color color, String name, String appearance) {
		this.glyph = glyph;
		this.defaultGlyph = glyph;
		this.color = color;
		this.defaultColor = color;
		this.name = name;
		this.defaultName = name;
		this.thrownDamageDice = Dice.d1;
		this.baseDamageType = DamageType.PHYSICAL;
		this.writtenSpells = new ArrayList<Spell>();
		this.appearance = appearance;
		this.stackAmount = 1;
		this.weaknesses = EnumSet.noneOf(DamageType.class);
		this.resistances = EnumSet.noneOf(DamageType.class);
		this.immunities = EnumSet.noneOf(DamageType.class);
		this.saveBonuses = new EnumMap<>(DamageType.class);
	}
	
	public Object clone(){
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException e) {
			// This should never happen
			throw new InternalError(e.toString());
		}
	}
	
	
	
}
