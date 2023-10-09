package RogueLike.Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
	
	public void changeColor(Color newColor) {
		color = newColor;
	}
	
	private String name;
	public String name() {
		String returnname = name;
		String upgradeLevel = "";
		String enchantmentName = "";
		
		if(this.upgradeLevel() > 0) {
			upgradeLevel = String.format("+%d ", this.upgradeLevel());
		}
		
		if(this.isEnchanted() > 0) {
			if(this.isArmor() || this.isShield()) {
				enchantmentName = String.format("%s", this.enchantedName());
			}
			if(this.isWeapon()) {
				enchantmentName = String.format("%s", this.enchantedName());
			}
		}
		
		if(this.isArmor() || this.isShield()) {
			returnname = String.format("%s%s%s", upgradeLevel, name, enchantmentName);
		}
		
		if(this.isWeapon()) {
			returnname = String.format("%s%s%s", upgradeLevel, enchantmentName, name);
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
	
	private String enchantedName;
	public String enchantedName() {
		return enchantedName;
	}
	
	public void setEnchantedName(String newName) {
		enchantedName = newName;
	}
	
	private String upgradeName;
	public String upgradeName() {
		return upgradeName;
	}
	
	public void setUpgradeName(String newName) {
		upgradeName = newName;
	}
	
	private String appearance;
	public String appearance() {
		if(appearance == null) {
			return name;
		}
		return appearance;
	}
	
	public void changeAppearance(String newAppearance) {
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
	
	public void setID(int amount) {
		id = amount;
	}
	
	private int isStackable;
	public int isStackable() {
		return isStackable;
	}
	
	public void modifyIsStackable(int amount) {
		isStackable += amount;
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
	
	private int damageDiceLowerBound = 1;
	public int damageDiceLowerBound() {
		return damageDiceLowerBound;
	}
	
	public void modifyDamageDiceLowerBound(int amount) {
		damageDiceLowerBound += amount;
	}
	
	public void setDamageDiceLowerBound(int amount) {
		damageDiceLowerBound = amount;
	}
	
	private int damageDice;
	public int damageDice() {
		return damageDice;
	}
	
	public void modifyDamageDice(int amount) {
		damageDice += amount;
	}
	
	public void setDamageDice(int amount) {
		damageDice = amount;
	}
	
	private int thrownDamageDice;
	public int thrownDamageDice() {
		return thrownDamageDice;
	}
	
	public void modifyThrownDamageDice(int amount) {
		thrownDamageDice += amount-1;
	}
	
	public void setThrownDamageDice(int amount) {
		thrownDamageDice = amount;
	}
	
	private int rangedDamageDice;
	public int rangedDamageDice() {
		return rangedDamageDice;
	}
	
	public void modifyRangedDamageDice(int amount) {
		rangedDamageDice += amount;
	}
	
	public void setRangedDamageDice(int amount) {
		rangedDamageDice = amount;
	}
	
	private int dealsFire;
	public int dealsFire() {
		return dealsFire;
	}
	public void modifyDealsFire(int amount) {
		dealsFire += amount;
	}
	
	private int dealsFrost;
	public int dealsFrost() {
		return dealsFrost;
	}
	public void modifyDealsFrost(int amount) {
		dealsFrost += amount;
	}
	
	private int dealsShock;
	public int dealsShock() {
		return dealsShock;
	}
	public void modifyDealsShock(int amount) {
		dealsShock += amount;
	}
	
	private int dealsPoison;
	public int dealsPoison() {
		return dealsPoison;
	}
	public void modifyDealsPoison(int amount) {
		dealsPoison += amount;
	}
	
	private int dealsAcid;
	public int dealsAcid() {
		return dealsAcid;
	}
	public void modifyDealsAcid(int amount) {
		dealsAcid += amount;
	}
	
	private int dealsMagic;
	public int dealsMagic() {
		return dealsMagic;
	}
	public void modifyDealsMagic(int amount) {
		dealsMagic += amount;
	}
	
	private int dealsChaos;
	public int dealsChaos() {
		return dealsChaos;
	}
	public void modifyDealsChaos(int amount) {
		dealsChaos += amount;
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
	
	private int saveBonusPoison;
	public int saveBonusPoison() {
		return saveBonusPoison;
	}
	
	public void modifySaveBonusPoison(double amount) {
		saveBonusPoison += amount;
	}
	
	private int saveBonusFire;
	public int saveBonusFire() {
		return saveBonusFire;
	}
	
	public void modifySaveBonusFire(double amount) {
		saveBonusFire += amount;
	}
	
	private int saveBonusFrost;
	public int saveBonusFrost() {
		return saveBonusFrost;
	}
	
	public void modifySaveBonusFrost(double amount) {
		saveBonusFrost += amount;
	}
	
	private int saveBonusShock;
	public int saveBonusShock() {
		return saveBonusShock;
	}
	
	public void modifySaveBonusShock(double amount) {
		saveBonusShock += amount;
	}
	
	private int saveBonusAcid;
	public int saveBonusAcid() {
		return saveBonusAcid;
	}
	
	public void modifySaveBonusAcid(double amount) {
		saveBonusAcid += amount;
	}
	
	private int saveBonusPhysical;
	public int saveBonusPhysical() {
		return saveBonusPhysical;
	}
	
	public void modifySaveBonusPhysical(double amount) {
		saveBonusPhysical += amount;
	}
	
	private int saveBonusMagic;
	public int saveBonusMagic() {
		return saveBonusMagic;
	}
	
	public void modifySaveBonusMagic(double amount) {
		saveBonusMagic += amount;
	}
	
	private int saveBonusChaos;
	public int saveBonusChaos() {
		return saveBonusChaos;
	}
	
	public void modifySaveBonusChaos(double amount) {
		saveBonusChaos += amount;
	}
	
	private int resistsPhysical;
	public int resistsPhysical() {
		return resistsPhysical;
	}
	public void modifyResistsPhysical(int amount) {
		resistsPhysical += amount;
	}
	private int immunePhysical;
	public int immunePhysical() {
		return immunePhysical;
	}
	public void modifyImmunePhysical(int amount) {
		immunePhysical += amount;
	}
	
	private int resistsFire;
	public int resistsFire() {
		return resistsFire;
	}
	public void modifyResistsFire(int amount) {
		resistsFire += amount;
	}
	private int immuneFire;
	public int immuneFire() {
		return immuneFire;
	}
	public void modifyImmuneFire(int amount) {
		immuneFire += amount;
	}
	
	private int resistsFrost;
	public int resistsFrost() {
		return resistsFrost;
	}
	public void modifyResistsFrost(int amount) {
		resistsFrost += amount;
	}
	private int immuneFrost;
	public int immuneFrost() {
		return immuneFrost;
	}
	public void modifyImmuneFrost(int amount) {
		immuneFrost += amount;
	}
	
	private int resistsShock;
	public int resistsShock() {
		return resistsShock;
	}
	public void modifyResistsShock(int amount) {
		resistsShock += amount;
	}
	private int immuneShock;
	public int immuneShock() {
		return immuneShock;
	}
	public void modifyImmuneShock(int amount) {
		immuneShock += amount;
	}
	
	private int resistsPoison;
	public int resistsPoison() {
		return resistsPoison;
	}
	public void modifyResistsPoison(int amount) {
		resistsPoison += amount;
	}
	private int immunePoison;
	public int immunePoison() {
		return immunePoison;
	}
	public void modifyImmunePoison(int amount) {
		immunePoison += amount;
	}
	
	private int resistsAcid;
	public int resistsAcid() {
		return resistsAcid;
	}
	public void modifyResistsAcid(int amount) {
		resistsAcid += amount;
	}
	private int immuneAcid;
	public int immuneAcid() {
		return immuneAcid;
	}
	public void modifyImmuneAcid(int amount) {
		immuneAcid += amount;
	}
	
	private int resistsMagic;
	public int resistsMagic() {
		return resistsMagic;
	}
	public void modifyResistsMagic(int amount) {
		resistsMagic += amount;
	}
	private int immuneMagic;
	public int immuneMagic() {
		return immuneMagic;
	}
	public void modifyImmuneMagic(int amount) {
		immuneMagic += amount;
	}
	
	private int resistsChaos;
	public int resistsChaos() {
		return resistsChaos;
	}
	public void modifyResistsChaos(int amount) {
		resistsChaos += amount;
	}
	private int immuneChaos;
	public int immuneChaos() {
		return immuneChaos;
	}
	public void modifyImmuneChaos(int amount) {
		immuneChaos += amount;
	}
	
	private Effect quaffEffect;
	public Effect quaffEffect() {
		return quaffEffect;
	}
	
	public void setQuaffEffect(Effect effect) {
		this.quaffEffect = effect;
	}
	
	public Effect enchantmentEffect;
	public Effect enchantmentEffect() {
		return (Effect) enchantmentEffect.clone();
	}
	
	public void setEnchantmentEffect(Effect effect) {
		//this.enchantmentEffect = effect;
		this.enchantmentEffect = (Effect) effect.clone();
	}
	
	public Effect curseEffect;
	public Effect curseEffect() {
		return (Effect) curseEffect.clone();
	}
	
	public void setCurseEffect(Effect effect) {
		//this.enchantmentEffect = effect;
		this.curseEffect = (Effect) effect.clone();
	}
	
	private int isEnchanted;
	public int isEnchanted() {
		return isEnchanted;
	}
	
	public void modifyIsEnchanted(int amount) {
		isEnchanted += amount;
	}
	
	private List<Spell> writtenSpells;
	public List<Spell> writtenSpells(){
		return writtenSpells;
	}
	
	public void addWrittenSpell(Spell spell) {
		writtenSpells.add(spell);
	}
	
	private int isDegraded;
	public int isDegraded() {
		return isDegraded;
	}
	public void modifyIsDegraded(int amount) {
		isDegraded += amount;
	}
	
	private int isCursed;
	public int isCursed() {
		return isCursed;
	}
	public void modifyIsCursed(int amount) {
		isCursed += amount;
	}
	
	private int curseKnown;
	public int curseKnown() {
		return curseKnown;
	}
	public void modifyCurseKnown(int amount) {
		curseKnown += amount;
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
	
	private boolean isVersatile;
	public boolean isVersatile() {
		return isVersatile;
	}
	public void setIsVersatile(boolean value) {
		isVersatile = value;
	}
	
	private int versatileDamageDice;
	public int versatileDamageDice() {
		return versatileDamageDice;
	}
	public void modifyVersatileDamageDice(int amount) {
		versatileDamageDice += amount;
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
	
	/*private int isSimple;
	public int isSimple() {
		return isSimple;
	}
	
	public void modifyIsSimple(int amount) {
		isSimple += amount;
	}
	
	private int isMartial;
	public int isMartial() {
		return isMartial;
	}
	
	public void modifyIsMartial(int amount) {
		isSimple += amount;
	}
	
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
	
	private boolean equippable;
	public boolean equippable() {
		return equippable;
	}
	public void setEquippable(boolean value) {
		equippable = value;
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
	//FLAT_WHITE
	private int isScroll;
	public int isScroll() {
		return isScroll;
	}
	public void modifyIsScroll(int amount) {
		isScroll += amount;
	}
	
	private int isIdentified;
	public int isIdentified() {
		return isIdentified;
	}
	public void modifyIsIdentified(int amount) {
		isIdentified += amount;
	}
	
	private int upgradeLevel;
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
		details += "   Value: "+currentGoldValue+" Gold";
		if(damageDice != 0) {
			details += "   Damage: "+damageDice;
		}
		if(rangedDamageDice != 0) {
			details += "   Ranged Damage: "+rangedDamageDice;
		}
		if(thrownDamageDice != 0) {
			details += "   Thrown Damage: "+thrownDamageDice;
		}
		if(armorClass != 0) {
			details += "   Armor Class: "+armorClass;
		}
		/*if(fireDefenseValue != 0) {
			details += "   Fire Resistance: "+fireDefenseValue;
		}
		if(iceDefenseValue != 0) {
			details += "   Frost Resistance: "+iceDefenseValue;
		}
		if(shockDefenseValue != 0) {
			details += "   Shock Resistance: "+shockDefenseValue;
		}*/
		if(foodValue != 0) {
			details += "   Food: "+foodValue;
		}
		if(ammunitionAmount != 0) {
			details += "   Ammunition: "+ammunitionAmount;
		}
		
		return details;
	}
	
	public Item(char glyph, Color color, String name, String appearance) {
		this.glyph = glyph;
		this.defaultGlyph = glyph;
		this.color = color;
		this.defaultColor = color;
		this.name = name;
		this.defaultName = name;
		this.upgradeName = "";
		this.enchantedName = "";
		this.thrownDamageDice = 1;
		this.writtenSpells = new ArrayList<Spell>();
		this.appearance = appearance;
		this.stackAmount = 1;
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
