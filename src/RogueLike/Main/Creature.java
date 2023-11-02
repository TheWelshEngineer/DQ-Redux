package RogueLike.Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RogueLike.Main.AI.CreatureAI;
import RogueLike.Main.Damage.Damage;
import RogueLike.Main.Items.Item;

public class Creature implements Cloneable{

	private World world;
	public World world() {
		return world;
	}
	private CreatureAI ai;
	public CreatureAI ai() {
		return ai;
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

	private String playerClass;
	public String playerClass() {
		return playerClass;
	}

	public void setPlayerClass(String newClass) {
		playerClass = newClass;
	}
	
	/*private String playerSpecies;
	public String playerSpecies() {
		return playerSpecies;
	}

	public void modifyPlayerSpecies(String newSpecies) {
		playerSpecies = newSpecies;
	}*/

	private String playerName;
	public String playerName() {
		return playerName;
	}

	public void setPlayerName(String newName) {
		playerName = newName;
	}

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

	public void modifyHP(Damage damage, String causeOfDeath) {
		if(damage.isHealing() == true) {
			int amount = damage.amount();
			hp += amount;
			if(!damage.isSilent()) {
				if(this.isPlayer()) {
					this.notify("You recover %d health.", amount);
				}else {
					this.doAction("recover %d health", amount);
				}
			}
		}else {
			int amount = damage.amount();
			int amountTemp = damage.amount();

			if((this.resistances()).contains(damage.typeString())){
				amount = (int)Math.floor(amountTemp*0.5);
			}

			if((this.imumnities()).contains(damage.typeString())){
				amount = 0;
			}

			hp -= amount;
			if(amount > 0) {
				setHPCooldown(5);
			}
			if(!damage.isSilent()) {
				if(this.isPlayer()) {
					this.notify("You take %d %s damage.", amount, damage.typeString().toLowerCase());
				}else {
					this.doAction("take %d %s damage", amount, damage.typeString().toLowerCase());
				}
			}

		}
		//hp += damage.amount();
		this.causeOfDeath = causeOfDeath;

		if(hp > maxHP) {
			hp = maxHP;
		}else if(hp <= 0 && !isDead) {
			setIsDead(true);
			doActionWhenDead("die");
			if(lastHit != null) {
				lastHit.gainXP(this);
			}
			leaveCorpse();
			world.remove(this);
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

	public void modifyMana(Damage value) {
		int amount = value.amount();
		if(value.isHealing()) {
			mana += amount;
			if(mana > maxMana) {
				mana = maxMana;
			}
		}else {
			mana -= amount;
			if(amount > 0) {
				setManaCooldown(5);
			}
			if(mana < 0) {
				mana = 0;
			}
		}
		if(this.isPlayer() && !value.isSilent()) {
			if(value.isHealing()) {
				this.notify("You recover %d mana.", amount);
			}else {
				this.notify("You lose %d mana.", amount);
			}

		}else {

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

	/*private double attackValue;
	public double attackValue() {
		double returnAttackValue = attackValue + (weapon == null ? 0 : weapon.attackValue()) + (armor == null ? 0 : armor.attackValue()) + (ring == null ? 0 : ring.attackValue()) + (shield == null ? 0 : shield.attackValue());
		if(giantStrength() == true) {
			returnAttackValue += 0.5;
		}
		if(weapon != null && weapon.isVersatile() > 0 && shield == null) {
			returnAttackValue += weapon.versatileDamageDice();
		}
		return returnAttackValue; 
	}

	public double baseAttackValue() {
		return attackValue;
	}

	public void modifyAttackValue(double amount) {
		attackValue += amount;
	}

	public int attackValueAsInt() {
		return (int)((1-this.attackValue())*100);
	}

	private double defenseValue;
	public double defenseValue() {
		double returnDefenseValue = defenseValue - (weapon == null ? 0 : weapon.defenseValue()) - (armor == null ? 0 : armor.defenseValue()) - (ring == null ? 0 : ring.defenseValue()) - (shield == null ? 0 : shield.defenseValue());
		if(giantStrength() == true) {
			returnDefenseValue -= 0.5;
		}
		if(corroded() == true) {
			returnDefenseValue += 0.25;
		}
		if(returnDefenseValue < 0.01) {
			returnDefenseValue = 0.01;
		}
		return returnDefenseValue; 
	}

	public void modifyDefenseValue(double amount) {
		defenseValue += amount;
	}

	public int defenseValueAsInt() {
		return (int)((1-this.defenseValue())*100);
	}*/

	private int armorClass;
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

		if(hasGiantStrength() == true) {
			returnArmorClass += 4;
		}
		if(isCorroded() == true) {
			returnArmorClass -= 2;
		}
		if(returnArmorClass < 0) {
			returnArmorClass = 0;
		}
		return returnArmorClass; 
	}

	public void modifyArmorClass(int amount) {
		armorClass += amount;
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
		if(isBlinded() == true) {
			returnVisionRadius = 2;
		}
		if(hasBeastForm() == true) {
			returnVisionRadius += 4;
		}
		return returnVisionRadius; 
	}

	public void modifyVisionRadius(int amount) {
		visionRadius += amount;
	}

	private int strength;
	public int strength() {
		int returnStrength = strength + (weapon == null ? 0 : weapon.strength()) + (armor == null ? 0 : armor.strength()) + (ring == null ? 0 : ring.strength()) + (shield == null ? 0 : shield.strength());
		if(hasGiantStrength() == true) {
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
		if(hasBeastForm() == true) {
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

	private int saveBonusPoison;
	public int saveBonusPoison() {
		int returnSaveBonusPoison = saveBonusPoison + (weapon == null ? 0 : weapon.saveBonusPoison()) + (armor == null ? 0 : armor.saveBonusPoison()) + (ring == null ? 0 : ring.saveBonusPoison()) + (shield == null ? 0 : shield.saveBonusPoison());
		return returnSaveBonusPoison;
	}
	
	public void setSaveBonusPoison(int value) {
		saveBonusPoison = value;
	}

	public int poisonSave() {
		int roll = (ExtraMaths.d20())+saveBonusPoison();
		return roll;
	}

	private int saveBonusFire;
	public int saveBonusFire() {
		int returnSaveBonusFire = saveBonusFire + (weapon == null ? 0 : weapon.saveBonusFire()) + (armor == null ? 0 : armor.saveBonusFire()) + (ring == null ? 0 : ring.saveBonusFire()) + (shield == null ? 0 : shield.saveBonusFire());
		if(hasMagmaWard() == true) {
			returnSaveBonusFire += 5;
		}
		return returnSaveBonusFire;
	}
	
	public void setSaveBonusFire(int value) {
		saveBonusFire = value;
	}

	public int fireSave() {
		int roll = (ExtraMaths.d20())+saveBonusFire();
		return roll;
	}

	private int saveBonusFrost;
	public int saveBonusFrost() {
		int returnSaveBonusFrost = saveBonusFrost + (weapon == null ? 0 : weapon.saveBonusFrost()) + (armor == null ? 0 : armor.saveBonusFrost()) + (ring == null ? 0 : ring.saveBonusFrost()) + (shield == null ? 0 : shield.saveBonusFrost());
		if(hasChillWard() == true) {
			returnSaveBonusFrost += 5;
		}
		return returnSaveBonusFrost;
	}
	
	public void setSaveBonusFrost(int value) {
		saveBonusFrost = value;
	}

	public int frostSave() {
		int roll = (ExtraMaths.d20())+saveBonusFrost();
		return roll;
	}

	private int saveBonusShock;
	public int saveBonusShock() {
		int returnSaveBonusShock = saveBonusShock + (weapon == null ? 0 : weapon.saveBonusShock()) + (armor == null ? 0 : armor.saveBonusShock()) + (ring == null ? 0 : ring.saveBonusShock()) + (shield == null ? 0 : shield.saveBonusShock());
		if(hasArcWard() == true) {
			returnSaveBonusShock += 5;
		}
		return returnSaveBonusShock;
	}
	
	public void setSaveBonusShock(int value) {
		saveBonusShock = value;
	}

	public int shockSave() {
		int roll = (ExtraMaths.d20())+saveBonusShock();
		return roll;
	}
	
	private int saveBonusAcid;
	public int saveBonusAcid() {
		int returnSaveBonusAcid = saveBonusAcid + (weapon == null ? 0 : weapon.saveBonusAcid()) + (armor == null ? 0 : armor.saveBonusAcid()) + (ring == null ? 0 : ring.saveBonusAcid()) + (shield == null ? 0 : shield.saveBonusAcid());
		//if(arcWard() == true) {
			//returnSaveBonusShock += 5;
		//}
		return returnSaveBonusAcid;
	}
	
	public void setSaveBonusAcid(int value) {
		saveBonusAcid = value;
	}

	public int acidSave() {
		int roll = (ExtraMaths.d20())+saveBonusAcid();
		return roll;
	}
	
	private int saveBonusPhysical;
	public int saveBonusPhysical() {
		int returnSaveBonusPhysical = saveBonusPhysical + (weapon == null ? 0 : weapon.saveBonusPhysical()) + (armor == null ? 0 : armor.saveBonusPhysical()) + (ring == null ? 0 : ring.saveBonusPhysical()) + (shield == null ? 0 : shield.saveBonusPhysical());
		//if(arcWard() == true) {
			//returnSaveBonusShock += 5;
		//}
		return returnSaveBonusPhysical;
	}
	
	public void setSaveBonusPhysical(int value) {
		saveBonusPhysical = value;
	}

	public int physicalSave() {
		int roll = (ExtraMaths.d20())+saveBonusPhysical();
		return roll;
	}
	
	private int saveBonusMagic;
	public int saveBonusMagic() {
		int returnSaveBonusMagic = saveBonusMagic + (weapon == null ? 0 : weapon.saveBonusMagic()) + (armor == null ? 0 : armor.saveBonusMagic()) + (ring == null ? 0 : ring.saveBonusMagic()) + (shield == null ? 0 : shield.saveBonusMagic());
		//if(arcWard() == true) {
			//returnSaveBonusShock += 5;
		//}
		return returnSaveBonusMagic;
	}
	
	public void setSaveBonusMagic(int value) {
		saveBonusMagic = value;
	}

	public int magicSave() {
		int roll = (ExtraMaths.d20())+saveBonusMagic();
		return roll;
	}
	
	private int saveBonusChaos;
	public int saveBonusChaos() {
		int returnSaveBonusChaos = saveBonusChaos + (weapon == null ? 0 : weapon.saveBonusChaos()) + (armor == null ? 0 : armor.saveBonusChaos()) + (ring == null ? 0 : ring.saveBonusChaos()) + (shield == null ? 0 : shield.saveBonusChaos());
		//if(arcWard() == true) {
			//returnSaveBonusShock += 5;
		//}
		return returnSaveBonusChaos;
	}
	
	public void setSaveBonusChaos(int value) {
		saveBonusChaos = value;
	}

	public int chaosSave() {
		int roll = (ExtraMaths.d20())+saveBonusMagic();
		return roll;
	}
	

	public ArrayList<Item> getequipmentArrayList(){
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList.add(weapon);
		equipmentArrayList.add(armor);
		equipmentArrayList.add(ring);
		equipmentArrayList.add(shield);

		return equipmentArrayList;
	}

	private boolean resistsPhysicalDamage;
	public boolean resistsPhysicalDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsPhysicalDamage())){
				return true;
			}
		}
		return resistsPhysicalDamage;
	}

	public void setResistsPhysicalDamage(boolean value) {
		resistsPhysicalDamage = value;
	}

	private boolean immunePhysicalDamage;
	public boolean immunePhysicalDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immunePhysicalDamage())){
				return true;
			}
		}
		return immunePhysicalDamage;
	}

	public void setImmunePhysicalDamage(boolean value) {
		immunePhysicalDamage = value;
	}

	private boolean resistsFireDamage;
	public boolean resistsFireDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsFireDamage())){
				return true;
			}
		}
		return resistsFireDamage;
	}

	public void setResistsFireDamage(boolean value) {
		resistsFireDamage = value;
	}
	
	private boolean immuneFireDamage;
	public boolean immuneFireDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immuneFireDamage())){
				return true;
			}
		}
		return immuneFireDamage;
	}

	public void setImmuneFireDamage(boolean value) {
		immuneFireDamage = value;
	}

	private boolean resistsFrostDamage;
	public boolean resistsFrostDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsFrostDamage())){
				return true;
			}
		}
		return resistsFrostDamage;
	}

	public void setResistsFrostDamage(boolean value) {
		resistsFrostDamage = value;
	}
	private boolean immuneFrostDamage;
	public boolean immuneFrostDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immuneFrostDamage())){
				return true;
			}
		}
		return immuneFrostDamage;
	}

	public void setImmuneFrostDamage(boolean value) {
		immuneFrostDamage = value;
	}

	private boolean resistsShockDamage;
	public boolean resistsShockDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsShockDamage())){
				return true;
			}
		}
		return resistsShockDamage;
	}

	public void setResistsShockDamage(boolean value) {
		resistsShockDamage = value;
	}
	private boolean immuneShockDamage;
	public boolean immuneShockDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immuneShockDamage())){
				return true;
			}
		}
		return immuneShockDamage;
	}

	public void setImmuneShockDamage(boolean value) {
		immuneShockDamage = value;
	}

	private boolean resistsPoisonDamage;
	public boolean resistsPoisonDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsPoisonDamage())){
				return true;
			}
		}
		return resistsPoisonDamage;
	}

	public void setResistsPoisonDamage(boolean value) {
		resistsPoisonDamage = value;
	}
	private boolean immunePoisonDamage;
	public boolean immunePoisonDamage() {
		ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immunePoisonDamage())){
				return true;
			}
		}
		return immunePoisonDamage;
	}

	public void setImmunePoisonDamage(boolean value) {
		immunePoisonDamage = value;
	}

	private boolean resistsAcidDamage;
	public boolean resistsAcidDamage() {
			ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsAcidDamage())){
				return true;
			}
		}
		return resistsAcidDamage;
	}

	public void setResistsAcidDamage(boolean value) {
		resistsAcidDamage = value;
	}

	private boolean immuneAcidDamage;
	public boolean immuneAcidDamage() {
			ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immuneAcidDamage())){
				return true;
			}
		}
		return immuneAcidDamage;
	}

	public void setImmuneAcidDamage(boolean value) {
		immuneAcidDamage = value;
	}

	private boolean resistsMagicDamage;
	public boolean resistsMagicDamage() {
			ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsMagicDamage())){
				return true;
			}
		}
		return resistsMagicDamage;
	}

	public void setResistsMagicDamage(boolean value) {
		resistsMagicDamage = value;
	}
	
	private boolean immuneMagicDamage;
	public boolean immuneMagicDamage() {
			ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immuneMagicDamage())){
				return true;
			}
		}
		return immuneMagicDamage;
	}

	public void setImmuneMagicDamage(boolean value) {
		immuneMagicDamage = value;
	}

	private boolean resistsChaosDamage;
	public boolean resistsChaosDamage() {
			ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.resistsChaosDamage())){
				return true;
			}
		}
		return resistsChaosDamage;
	}

	public void setResistsChaosDamage(boolean value) {
		resistsChaosDamage = value;
	}
	private boolean immuneChaosDamage;
	public boolean immuneChaosDamage() {
			ArrayList<Item> equipmentArrayList = new ArrayList<Item>();
		equipmentArrayList = this.getequipmentArrayList();
		
		for (Item equipment: equipmentArrayList){
			if ((equipment != null) && (equipment.immuneChaosDamage())){
				return true;
			}
		}
		return immuneChaosDamage;
	}

	public void setImmuneChaosDamage(boolean value) {
		immuneChaosDamage = value;
	}

	public ArrayList<String> resistances(){
		ArrayList<String> resistances = new ArrayList<String>();	
		if (resistsPhysicalDamage()){
			resistances.add(Damage.physical);
		}
		if(resistsFireDamage()){
			resistances.add(Damage.fire);
		}
		if(resistsFrostDamage()){
			resistances.add(Damage.frost);
		}
		if (resistsShockDamage()){
			resistances.add(Damage.shock);
		}
		if (resistsPoisonDamage()){
			resistances.add(Damage.poison);
		}
		if (resistsAcidDamage()){
			resistances.add(Damage.acid);
		}
		if (resistsMagicDamage()){
			resistances.add(Damage.magic);
		}
		if (resistsChaosDamage()){
			resistances.add(Damage.chaos);
		}
		return resistances;
	}

	public ArrayList<String> imumnities(){
		ArrayList<String> imunites = new ArrayList<String>();	
		if (resistsPhysicalDamage()){
			imunites.add(Damage.physical);
		}
		if(immunePhysicalDamage()){
			imunites.add(Damage.fire);
		}
		if(immuneFrostDamage()){
			imunites.add(Damage.frost);
		}
		if (immuneFrostDamage()){
			imunites.add(Damage.shock);
		}
		if (immunePoisonDamage()){
			imunites.add(Damage.poison);
		}
		if (immuneAcidDamage()){
			imunites.add(Damage.acid);
		}
		if (immuneChaosDamage()){
			imunites.add(Damage.magic);
		}
		if (immuneChaosDamage()){
			imunites.add(Damage.chaos);
		}
		return imunites;
	}

	/*private int skillSimpleWeapons;
	public int skillSimpleWeapons() {
		return skillSimpleWeapons;
	}
	public void modifySkillSimpleWeapons(int amount) {
		skillSimpleWeapons += amount;
	}
	public void setSkillSimpleWeapons(int amount) {
		skillSimpleWeapons = amount;
	}
	public int rollSimpleWeapons() {
		return ExtraMaths.d20()+skillSimpleWeapons;
	}

	private int skillMartialWeapons;
	public int skillMartialWeapons() {
		return skillMartialWeapons;
	}
	public void modifySkillMartialWeapons(int amount) {
		skillMartialWeapons += amount;
	}
	public void setSkillMartialWeapons(int amount) {
		skillMartialWeapons = amount;
	}
	public int rollMartialWeapons() {
		return ExtraMaths.d20()+skillMartialWeapons;
	}

	private int skillFinesseWeapons;
	public int skillFinesseWeapons() {
		return skillFinesseWeapons;
	}
	public void modifySkillFinesseWeapons(int amount) {
		skillFinesseWeapons += amount;
	}
	public void setSkillFinesseWeapons(int amount) {
		skillFinesseWeapons = amount;
	}
	public int rollFinesseWeapons() {
		return ExtraMaths.d20()+skillFinesseWeapons;
	}

	private int skillRangedWeapons;
	public int skillRangedWeapons() {
		return skillRangedWeapons;
	}
	public void modifySkillRangedWeapons(int amount) {
		skillRangedWeapons += amount;
	}
	public void setSkillRangedWeapons(int amount) {
		skillRangedWeapons = amount;
	}
	public int rollRangedWeapons() {
		return ExtraMaths.d20()+skillRangedWeapons;
	}

	private int skillFortitude;
	public int skillFortitude() {
		return skillFortitude;
	}
	public void modifySkillFortitude(int amount) {
		skillFortitude += amount;
	}
	public void setSkillFortitude(int amount) {
		skillFortitude = amount;
	}
	public int rollFortitude() {
		return ExtraMaths.d20()+skillFortitude;
	}

	private int skillPerception;
	public int skillPerception() {
		return skillPerception;
	}
	public void modifySkillPerception(int amount) {
		skillPerception += amount;
	}
	public void setSkillPerception(int amount) {
		skillPerception = amount;
	}
	public int rollPerception() {
		return ExtraMaths.d20()+skillPerception;
	}

	private int skillStealth;
	public int skillStealth() {
		return skillStealth;
	}
	public void modifySkillStealth(int amount) {
		skillStealth += amount;
	}
	public void setSkillStealth(int amount) {
		skillStealth = amount;
	}
	public int rollStealth() {
		return ExtraMaths.d20()+skillStealth;
	}

	private int skillEvocation;
	public int skillEvocation() {
		return skillEvocation;
	}
	public void modifySkillEvocation(int amount) {
		skillEvocation += amount;
	}
	public void setSkillEvocation(int amount) {
		skillEvocation = amount;
	}
	public int rollEvocation() {
		return ExtraMaths.d20()+skillEvocation;
	}

	private int skillPyromancy;
	public int skillPyromancy() {
		return skillPyromancy;
	}
	public void modifySkillPyromancy(int amount) {
		skillPyromancy += amount;
	}
	public void setSkillPyromancy(int amount) {
		skillPyromancy = amount;
	}
	public int rollPyromancy() {
		return ExtraMaths.d20()+skillPyromancy;
	}

	private int skillCryomancy;
	public int skillCryomancy() {
		return skillCryomancy;
	}
	public void modifySkillCryomancy(int amount) {
		skillCryomancy += amount;
	}
	public void setSkillCryomancy(int amount) {
		skillCryomancy = amount;
	}
	public int rollCryomancy() {
		return ExtraMaths.d20()+skillCryomancy;
	}

	private int skillElectromancy;
	public int skillElectromancy() {
		return skillElectromancy;
	}
	public void modifySkillElectromancy(int amount) {
		skillElectromancy += amount;
	}
	public void setSkillElectromancy(int amount) {
		skillElectromancy = amount;
	}
	public int rollElectromancy() {
		return ExtraMaths.d20()+skillElectromancy;
	}

	private int skillAlchemancy;
	public int skillAlchemancy() {
		return skillAlchemancy;
	}
	public void modifySkillAlchemancy(int amount) {
		skillAlchemancy += amount;
	}
	public void setSkillAlchemancy(int amount) {
		skillAlchemancy = amount;
	}
	public int rollAlchemancy() {
		return ExtraMaths.d20()+skillAlchemancy;
	}*/

	private boolean dealsFireDamage;
	public boolean dealsFireDamage() {
		boolean weaponDeals = false;
		if(weapon != null) {
			weaponDeals = weapon.dealsFireDamage();
		}
		boolean armorDeals = false;
		if(armor != null) {
			armorDeals = armor.dealsFireDamage();
		}
		boolean ringDeals = false;
		if(ring != null) {
			ringDeals = ring.dealsFireDamage();
		}
		boolean shieldDeals = false;
		if(shield != null) {
			shieldDeals = shield.dealsFireDamage();
		}
		
		int returnDealsFire = (dealsFireDamage ? 1 : 0) + (weaponDeals ? 1 : 0) + (armorDeals ? 1 : 0) + (ringDeals ? 1 : 0) + (shieldDeals ? 1 : 0);
		if(returnDealsFire > 0) {
			return true;
		}else {
			return false;
		}
	}

	private boolean dealsFrostDamage;
	public boolean dealsFrostDamage() {
		boolean weaponDeals = false;
		if(weapon != null) {
			weaponDeals = weapon.dealsFrostDamage();
		}
		boolean armorDeals = false;
		if(armor != null) {
			armorDeals = armor.dealsFrostDamage();
		}
		boolean ringDeals = false;
		if(ring != null) {
			ringDeals = ring.dealsFrostDamage();
		}
		boolean shieldDeals = false;
		if(shield != null) {
			shieldDeals = shield.dealsFrostDamage();
		}
		
		int returnDealsFrost = (dealsFrostDamage ? 1 : 0) + (weaponDeals ? 1 : 0) + (armorDeals ? 1 : 0) + (ringDeals ? 1 : 0) + (shieldDeals ? 1 : 0);
		if(returnDealsFrost > 0) {
			return true;
		}else {
			return false;
		}
	}

	private boolean dealsShockDamage;
	public boolean dealsShockDamage() {
		boolean weaponDeals = false;
		if(weapon != null) {
			weaponDeals = weapon.dealsShockDamage();
		}
		boolean armorDeals = false;
		if(armor != null) {
			armorDeals = armor.dealsShockDamage();
		}
		boolean ringDeals = false;
		if(ring != null) {
			ringDeals = ring.dealsShockDamage();
		}
		boolean shieldDeals = false;
		if(shield != null) {
			shieldDeals = shield.dealsShockDamage();
		}
		
		int returnDealsShock = (dealsShockDamage ? 1 : 0) + (weaponDeals ? 1 : 0) + (armorDeals ? 1 : 0) + (ringDeals ? 1 : 0) + (shieldDeals ? 1 : 0);
		if(returnDealsShock > 0) {
			return true;
		}else {
			return false;
		}
	}

	private boolean dealsPoisonDamage;
	public boolean dealsPoisonDamage() {
		boolean weaponDeals = false;
		if(weapon != null) {
			weaponDeals = weapon.dealsPoisonDamage();
		}
		boolean armorDeals = false;
		if(armor != null) {
			armorDeals = armor.dealsPoisonDamage();
		}
		boolean ringDeals = false;
		if(ring != null) {
			ringDeals = ring.dealsPoisonDamage();
		}
		boolean shieldDeals = false;
		if(shield != null) {
			shieldDeals = shield.dealsPoisonDamage();
		}
		
		int returnDealsPoison = (dealsPoisonDamage ? 1 : 0) + (weaponDeals ? 1 : 0) + (armorDeals ? 1 : 0) + (ringDeals ? 1 : 0) + (shieldDeals ? 1 : 0);
		if(returnDealsPoison > 0) {
			return true;
		}else {
			return false;
		}
	}

	private boolean dealsAcidDamage;
	public boolean dealsAcidDamage() {
		boolean weaponDeals = false;
		if(weapon != null) {
			weaponDeals = weapon.dealsAcidDamage();
		}
		boolean armorDeals = false;
		if(armor != null) {
			armorDeals = armor.dealsAcidDamage();
		}
		boolean ringDeals = false;
		if(ring != null) {
			ringDeals = ring.dealsAcidDamage();
		}
		boolean shieldDeals = false;
		if(shield != null) {
			shieldDeals = shield.dealsAcidDamage();
		}
		
		int returnDealsAcid = (dealsAcidDamage ? 1 : 0) + (weaponDeals ? 1 : 0) + (armorDeals ? 1 : 0) + (ringDeals ? 1 : 0) + (shieldDeals ? 1 : 0);
		if(returnDealsAcid > 0) {
			return true;
		}else {
			return false;
		}
	}

	private boolean dealsMagicDamage;
	public boolean dealsMagicDamage() {
		boolean weaponDeals = false;
		if(weapon != null) {
			weaponDeals = weapon.dealsMagicDamage();
		}
		boolean armorDeals = false;
		if(armor != null) {
			armorDeals = armor.dealsMagicDamage();
		}
		boolean ringDeals = false;
		if(ring != null) {
			ringDeals = ring.dealsMagicDamage();
		}
		boolean shieldDeals = false;
		if(shield != null) {
			shieldDeals = shield.dealsMagicDamage();
		}
		
		int returnDealsMagic = (dealsMagicDamage ? 1 : 0) + (weaponDeals ? 1 : 0) + (armorDeals ? 1 : 0) + (ringDeals ? 1 : 0) + (shieldDeals ? 1 : 0);
		if(returnDealsMagic > 0) {
			return true;
		}else {
			return false;
		}
	}

	private boolean dealsChaosDamage;
	public boolean dealsChaosDamage() {
		boolean weaponDeals = false;
		if(weapon != null) {
			weaponDeals = weapon.dealsChaosDamage();
		}
		boolean armorDeals = false;
		if(armor != null) {
			armorDeals = armor.dealsChaosDamage();
		}
		boolean ringDeals = false;
		if(ring != null) {
			ringDeals = ring.dealsChaosDamage();
		}
		boolean shieldDeals = false;
		if(shield != null) {
			shieldDeals = shield.dealsChaosDamage();
		}
		
		int returnDealsChaos = (dealsChaosDamage ? 1 : 0) + (weaponDeals ? 1 : 0) + (armorDeals ? 1 : 0) + (ringDeals ? 1 : 0) + (shieldDeals ? 1 : 0);
		if(returnDealsChaos > 0) {
			return true;
		}else {
			return false;
		}
	}


	private String name;
	public String name() {
		return name;
	}

	private String defaultName;
	public String defaultName() {
		return defaultName;
	}

	public void changeName(String newName) {
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
			Damage levelUpHealth = new Damage(level*2, true, false, null, factory().effectFactory);
			modifyHP(levelUpHealth, "");
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
		int bonus = intelligenceModifier();
		if(bonus < 0) {
			bonus = 0;
		}
		maxMana += manaScaleAmount+bonus;
		mana += manaScaleAmount+bonus;
		//doAction("look healthier");
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

	private boolean hasMindVision;
	public boolean hasMindVision() {
		return hasMindVision;
	}
	public void setHasMindVision(boolean value) {
		hasMindVision = value;
	}

	private boolean hasGiantStrength;
	public boolean hasGiantStrength() {
		return hasGiantStrength;
	}
	public void setHasGiantStrength(boolean value) {
		hasGiantStrength = value;
	}
	
	private boolean hasBeastForm;
	public boolean hasBeastForm() {
		return hasBeastForm;
	}
	public void setHasBeastForm(boolean value) {
		hasBeastForm = value;
	}

	private boolean hasMagmaWard;
	public boolean hasMagmaWard() {
		return hasMagmaWard;
	}
	public void setHasMagmaWard(boolean value) {
		hasMagmaWard = value;
	}

	private boolean hasArcWard;
	public boolean hasArcWard() {
		return hasArcWard;
	}
	public void setHasArcWard(boolean value) {
		hasArcWard = value;
	}

	private boolean hasChillWard;
	public boolean hasChillWard() {
		return hasChillWard;
	}
	public void setHasChillWard(boolean value) {
		hasChillWard = value;
	}

	private boolean isInvisible;
	public boolean isInvisible() {
		return isInvisible;
	}
	public void setIsInvisible(boolean value) {
		isInvisible = value;
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

	private boolean isFrozen;
	public boolean isFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(boolean value) {
		isFrozen = value;
	}

	private boolean isParalyzed;
	public boolean isParalyzed() {
		return isParalyzed;
	}
	public void setIsParalyzed(boolean value) {
		isParalyzed = value;
	}

	private boolean isCorroded;
	public boolean isCorroded() {
		return isCorroded;
	}
	public void setIsCorroded(boolean value) {
		isCorroded = value;
	}

	private boolean isShocked;
	public boolean isShocked() {
		return isShocked;
	}
	public void setIsShocked(boolean value) {
		isShocked = value;
	}

	private boolean isPoisoned;
	public boolean isPoisoned() {
		return isPoisoned;
	}
	public void setIsPoisoned(boolean value) {
		isPoisoned = value;
	}

	private boolean isIgnited;
	public boolean isIgnited() {
		return isIgnited;
	}
	public void setIsIgnited(boolean value) {
		isIgnited = value;
	}

	private boolean isAsleep;
	public boolean isAsleep() {
		return isAsleep;
	}
	public void setIsAsleep(boolean value) {
		isAsleep = value;
	}

	private boolean hasTruesight;
	public boolean hasTruesight() {
		return hasTruesight;
	}
	public void setHasTruesight(boolean value) {
		hasTruesight = value;
	}

	private boolean isConfused;
	public boolean isConfused() {
		return isConfused;
	}
	public void setIsConfused(boolean value) {
		isConfused = value;
	}

	private boolean isLevitating;
	public boolean isLevitating() {
		return isLevitating;
	}
	public void setIsLevitating(boolean value) {
		isLevitating = value;
	}

	private boolean isDevoured;
	public boolean isDevoured() {
		return isDevoured;
	}
	public void setIsDevoured(boolean value) {
		isDevoured = value;
	}

	private boolean isBlinded;
	public boolean isBlinded() {
		return isBlinded;
	}
	public void setIsBlinded(boolean value) {
		isBlinded = value;
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
		return score;
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

	private int proficiencyBonus;
	public int proficiencyBonus() {
		return proficiencyBonus;
	}

	public void modifyProficiencyBonus(int amount) {
		proficiencyBonus += amount;
	}

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
	public int maxItemIndex() {
		return 86;
	}

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
		this.hasMindVision = false;
		this.isFlying = false;
		this.isFrozen = false;
		this.isParalyzed = false;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.proficiencyBonus = 2;
		this.hpScaleAmount = hpScaleMedium();
		this.manaScaleAmount = manaScaleMedium();
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
		if((isParalyzed == true) && isPlayer()) {
			if((int)(Math.random()*10) < 8) {
				doAction("struggle to move!");
				return;
			}else {
				doAction("move with difficulty");
			}
		}

		if((isFrozen == true) && isPlayer()) {
			doAction("struggle to move!");
			return;

		}

		if(isConfused == true) {
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
		modifyFood(-1);
		if(other == null) {
			ai.onEnter(x+mx, y+my, z+mz, tile);
		}
		else if(other.isContainer() == true){
			openContainer(other);
		}else {
			attack(other);
		}		
	}

	public void openContainer(Creature container) {
		container.leaveCorpse();
		world.remove(container);
	}

	public void reveal() {
		setIsDisguised(false);
		changeName(defaultName());
		changeColor(defaultColor());
		doAction("reveal itself!");
	}

	public void hide() {
		setIsDisguised(true);
		changeName("Chest");
		changeColor(ExtraColors.mimic);
	}

	public void sleep() {
		setIsAsleep(true);
	}

	public void wakeup() {
		setIsAsleep(false);
	}

	public void attack(Creature other) {

		int amount = 0;
		if(weapon != null) {
			int attackBonus = 0;
			if(weapon.usesStrength()) {
				attackBonus = this.strengthModifier();
			}else if(weapon.usesDexterity()){
				attackBonus = this.dexterityModifier();
			}else if(weapon.usesIntelligence()){
				attackBonus = this.intelligenceModifier();
			}
			if(weapon.isVersatile() && shield == null) {
				amount = (int)(weapon.versatileDamageDice().roll())+attackBonus+weapon.upgradeLevel();
			}else {
				amount = (int)(weapon.damageDice().roll())+attackBonus+weapon.upgradeLevel();
			}
		}else {
			amount = 1+strengthModifier();
		}

		if(amount < 1) {
			amount = 1;
		}

		Damage damage = new Damage(amount, false, false, Damage.physical, factory().effectFactory);
		if(this.isPlayer()) {
			if(weapon != null) {
				if(weapon.dealsFireDamage() && weapon != null) {
					damage.setTypeString(Damage.fire);
				}else if(weapon.dealsFrostDamage() && weapon != null) {
					damage.setTypeString(Damage.frost);
				}else if(weapon.dealsShockDamage() && weapon != null) {
					damage.setTypeString(Damage.shock);
				}else if(weapon.dealsPoisonDamage() && weapon != null) {
					damage.setTypeString(Damage.poison);
				}else if(weapon.dealsAcidDamage() && weapon != null) {
					damage.setTypeString(Damage.acid);
				}else if(weapon.dealsMagicDamage() && weapon != null) {
					damage.setTypeString(Damage.magic);
				}else if(weapon.dealsChaosDamage() && weapon != null) {
					damage.setTypeString(Damage.chaos);
				}else {
					damage.setTypeString(Damage.physical);
				}
			}else {
				damage.setTypeString(Damage.physical);
			}
		}else {
			if(this.dealsFireDamage()) {
				damage.setTypeString(Damage.fire);
			}else if(this.dealsFrostDamage()) {
				damage.setTypeString(Damage.frost);
			}else if(this.dealsShockDamage()) {
				damage.setTypeString(Damage.shock);
			}else if(this.dealsPoisonDamage()) {
				damage.setTypeString(Damage.poison);
			}else if(this.dealsAcidDamage()) {
				damage.setTypeString(Damage.acid);
			}else if(this.dealsMagicDamage()) {
				damage.setTypeString(Damage.magic);
			}else if(this.dealsChaosDamage()) {
				damage.setTypeString(Damage.chaos);
			}else {
				damage.setTypeString(Damage.physical);
			}
		}





		modifyFood(-5);


		int attackRoll = 0;
		if(weapon != null) {
			if(weapon.usesDexterity()) {
				attackRoll = this.dexterityRoll()+weapon.upgradeLevel();
			}else if(weapon.usesStrength()) {
				attackRoll = this.strengthRoll()+weapon.upgradeLevel();
			}else if(weapon.usesIntelligence()) {
				attackRoll = this.intelligenceRoll()+weapon.upgradeLevel();
			}else {
				attackRoll = this.strengthRoll();
			}
		}else {
			attackRoll = this.strengthRoll();
		}

		if(this.isInvisible() == true) {
			attackRoll += 5;
		}

		if(other.isInvisible() == true) {
			attackRoll -= 5;
		}

		if(attackRoll >= other.armorClass()) {
			//doAction("attack the %s for %d damage", other.name, damage.amount());
			doAction("attack the %s", other.name);

			//
			other.setLastHit(this);
			if(weapon != null) {
				other.modifyHP(damage, String.format("Killed by a %s using a %s", this.name, this.weaponName()));
			}else {
				other.modifyHP(damage, String.format("Killed by a %s", this.name));
			}

			if(other.hp > 0){
				this.setLastTarget(other);
			}

			if(weapon != null && weapon.isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= weapon.upgradeLevel()) {
					other.addEffect(weapon.enchantmentEffect());
				}else {

				}

			}

			//temp
			if(other.armor() != null && other.armor().isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= other.armor().upgradeLevel()) {
					other.addEffect(other.armor().enchantmentEffect());
				}else {

				}
			}
			if(other.shield() != null && other.shield().isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= other.shield().upgradeLevel()) {
					other.addEffect(other.shield().enchantmentEffect());
				}else {

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


		if(this.isInvisible() == true) {
			this.cureInvisible();
		}

		//if(other.hp < 1) {
		//gainXP(other);
		//}
		//notify("You attack the '%s' for %d damage.", other.glyph, amount);
		//other.notify("The '%s' attacks you for %d damage.", glyph, amount);
	}

	public void throwItem(Item item, int wx, int wy, int wz) {
		Point end = new Point(x, y, 0);

		for(Point p : new Line(x, y, wx, wy)) {
			if(!realTile(p.x, p.y, z).isGround()) {
				break;
			}
			end = p;
		}

		wx = end.x;
		wy = end.y;

		Creature c = creature(wx, wy, wz);
		if(item.isCursed() && (item == weapon || item == armor || item == shield || item == ring || item == ammunition)) {
			notify("Your "+nameOf(item)+" is cursed! You can't let go of it!");
		}else {
			Item item2 = (Item) item.clone();
			item.modifyStackAmount(-1);
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
		if(item.isThrownWeapon()) {
			int attackBonus = 0;
			if(weapon.usesStrength()) {
				attackBonus = this.strengthModifier();
			}else if(weapon.usesDexterity()){
				attackBonus = this.dexterityModifier();
			}else if(weapon.usesIntelligence()){
				attackBonus = this.intelligenceModifier();
			}
			amount = (int)(weapon.thrownDamageDice().roll())+attackBonus+weapon.upgradeLevel();
		}else {
			amount = 1+strengthModifier();
		}

		if(amount < 1) {
			amount = 1;
		}

		Damage damage = new Damage(amount, false, false, Damage.physical, factory().effectFactory);
		if(this.isPlayer()) {
			if(item.dealsFireDamage() && weapon != null) {
				damage.setTypeString(Damage.fire);
			}else if(item.dealsFrostDamage() && weapon != null) {
				damage.setTypeString(Damage.frost);
			}else if(item.dealsShockDamage() && weapon != null) {
				damage.setTypeString(Damage.shock);
			}else if(item.dealsPoisonDamage() && weapon != null) {
				damage.setTypeString(Damage.poison);
			}else if(item.dealsAcidDamage() && weapon != null) {
				damage.setTypeString(Damage.acid);
			}else if(item.dealsMagicDamage() && weapon != null) {
				damage.setTypeString(Damage.magic);
			}else if(item.dealsChaosDamage() && weapon != null) {
				damage.setTypeString(Damage.chaos);
			}else {
				damage.setTypeString(Damage.physical);
			}
		}else {
			if(this.dealsFireDamage()) {
				damage.setTypeString(Damage.fire);
			}else if(this.dealsFrostDamage()) {
				damage.setTypeString(Damage.frost);
			}else if(this.dealsShockDamage()) {
				damage.setTypeString(Damage.shock);
			}else if(this.dealsPoisonDamage()) {
				damage.setTypeString(Damage.poison);
			}else if(this.dealsAcidDamage()) {
				damage.setTypeString(Damage.acid);
			}else if(this.dealsMagicDamage()) {
				damage.setTypeString(Damage.magic);
			}else if(this.dealsChaosDamage()) {
				damage.setTypeString(Damage.chaos);
			}else {
				damage.setTypeString(Damage.physical);
			}
		}

		int attackRoll = 0;
		if(item.isThrownWeapon()) {
			if(weapon.usesDexterity()) {
				attackRoll = this.dexterityRoll()+weapon.upgradeLevel();
			}else if(weapon.usesStrength()) {
				attackRoll = this.strengthRoll()+weapon.upgradeLevel();
			}else if(weapon.usesIntelligence()) {
				attackRoll = this.intelligenceRoll()+weapon.upgradeLevel();
			}else {
				attackRoll = this.strengthRoll();
			}
		}else {
			attackRoll = this.strengthRoll();
		}

		if(this.isInvisible() == true) {
			attackRoll += 5;
		}

		if(other.isInvisible() == true) {
			attackRoll -= 5;
		}

		if(attackRoll >= other.armorClass()) {
			//doAction("throw a %s at the %s for %d damage", nameOf(item), other.name, damage.amount());
			doAction("throw a %s at the %s", nameOf(item), other.name);

			other.setLastHit(this);
			other.addEffect(item.quaffEffect());
			other.modifyHP(damage, String.format("Killed by a %s using a %s", this.name, item.name()));
			if(other.hp > 0){
				this.setLastTarget(other);
			}

			if(item.isThrownWeapon() && item.isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= item.upgradeLevel()) {
					other.addEffect(item.enchantmentEffect());
				}else {

				}

			}

			//temp
			if(other.armor() != null && other.armor().isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= other.armor().upgradeLevel()) {
					other.addEffect(other.armor().enchantmentEffect());
				}else {

				}
			}
			if(other.shield() != null && other.shield().isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= other.shield().upgradeLevel()) {
					other.addEffect(other.shield().enchantmentEffect());
				}else {

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

		if(this.isInvisible() == true) {
			this.cureInvisible();
		}

		//if(other.hp < 1) {
		//gainXP(other);
		//}
	}

	public void rangedWeaponAttack(Creature other) {
		modifyFood(-1);

		int amount = 0;
		if(weapon != null) {
			int attackBonus = 0;
			if(weapon.usesStrength()) {
				attackBonus = this.strengthModifier();
			}else if(weapon.usesDexterity()){
				attackBonus = this.dexterityModifier();
			}else if(weapon.usesIntelligence()){
				attackBonus = this.intelligenceModifier();
			}
			amount = (int)(weapon.rangedDamageDice().roll())+attackBonus+weapon.upgradeLevel();
		}else {
			amount = 1+this.dexterityModifier();
		}
		
		int attackRoll = 0;
		if(weapon.usesDexterity()) {
			attackRoll = this.dexterityRoll()+weapon.upgradeLevel();
		}else if(weapon.usesStrength()) {
			attackRoll = this.strengthRoll()+weapon.upgradeLevel();
		}else if(weapon.usesIntelligence()) {
			attackRoll = this.intelligenceRoll()+weapon.upgradeLevel();
		}else {
			attackRoll = this.strengthRoll();
		}

		if(amount < 1) {
			amount = 1;
		}

		Damage damage = new Damage(amount, false, false, Damage.physical, factory().effectFactory);
		if(this.isPlayer()) {
			if(weapon.dealsFireDamage() && weapon != null) {
				damage.setTypeString(Damage.fire);
			}else if(weapon.dealsFrostDamage() && weapon != null) {
				damage.setTypeString(Damage.frost);
			}else if(weapon.dealsShockDamage() && weapon != null) {
				damage.setTypeString(Damage.shock);
			}else if(weapon.dealsPoisonDamage() && weapon != null) {
				damage.setTypeString(Damage.poison);
			}else if(weapon.dealsAcidDamage() && weapon != null) {
				damage.setTypeString(Damage.acid);
			}else if(weapon.dealsMagicDamage() && weapon != null) {
				damage.setTypeString(Damage.magic);
			}else if(weapon.dealsChaosDamage() && weapon != null) {
				damage.setTypeString(Damage.chaos);
			}else {
				damage.setTypeString(Damage.physical);
			}
		}else {
			if(this.dealsFireDamage()) {
				damage.setTypeString(Damage.fire);
			}else if(this.dealsFrostDamage()) {
				damage.setTypeString(Damage.frost);
			}else if(this.dealsShockDamage()) {
				damage.setTypeString(Damage.shock);
			}else if(this.dealsPoisonDamage()) {
				damage.setTypeString(Damage.poison);
			}else if(this.dealsAcidDamage()) {
				damage.setTypeString(Damage.acid);
			}else if(this.dealsMagicDamage()) {
				damage.setTypeString(Damage.magic);
			}else if(this.dealsChaosDamage()) {
				damage.setTypeString(Damage.chaos);
			}else {
				damage.setTypeString(Damage.physical);
			}
		}

		if(this.isInvisible() == true) {
			attackRoll += 5;
		}

		if(other.isInvisible() == true) {
			attackRoll -= 5;
		}
		if(this.isPlayer()) {
			this.ammunition().modifyStackAmount(-1);
		}
		if(attackRoll >= other.armorClass()) {
			//doAction("fire the %s at the %s for %d damage", nameOf(weapon), other.name, damage.amount());
			doAction("fire the %s at the %s", nameOf(weapon), other.name);

			other.setLastHit(this);
			if(weapon != null) {
				other.modifyHP(damage, String.format("Killed by a %s using a %s", this.name, this.weaponName()));
			}else {
				other.modifyHP(damage, String.format("Killed by a %s", this.name));
			}
			if(other.hp > 0){
				this.setLastTarget(other);
			}

			if(weapon != null && weapon.isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= weapon.upgradeLevel()) {
					other.addEffect(weapon.enchantmentEffect());
				}else {

				}

			}

			if(other.armor() != null && other.armor().isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d20() <= other.armor().upgradeLevel()) {
					other.addEffect(other.armor().enchantmentEffect());
				}else {

				}
			}
			if(other.shield() != null && other.shield().isEnchanted() && other.hp() >= 1) {
				if(0 + ExtraMaths.d10() <= other.shield().upgradeLevel()) {
					other.addEffect(other.shield().enchantmentEffect());
				}else {

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


		if(this.isInvisible() == true) {
			this.cureInvisible();
		}

		//if(other.hp < 1) {
		//gainXP(other);
		//}
	}

	public void pickup() {
		Item item = world.item(x, y, z);
		if(inventory.isFull() || item == null || item.isTrap()) {
			doAction("grab fruitlessly at the ground");
		}else {
			doAction("pick up a %s", nameOf(item));
			world.remove(x, y, z);
			if(nameOf(item) == item.name()) {
				item.setIsIdentified(true);
			}
			inventory.add(item);
			stackItems();
		}
	}

	public void drop(Item item){
		if((item == weapon || item == armor || item == shield || item == ring || item == ammunition) && item.isCursed()) {
			notify("The "+nameOf(item)+" is cursed! You can't let go of it!");
		}else if (world.addAtEmptySpace(item, x, y, z)){
			if(isDead) {
				doActionWhenDead("drop a " + nameOf(item));
			}
			doAction("drop a " + nameOf(item));
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
		if(!item.equippable()) {
			return;
		}
		if(item.isWeapon()) {
			if(item == weapon) {
				if(weapon.isCursed()) {
					notify("Your "+nameOf(weapon)+" is cursed! You can't let go of it!");
				}else {
					unequip(weapon);
				}

			}else {
				if(weapon != null && weapon.isCursed()) {
					notify("Your "+nameOf(weapon)+" is cursed! You can't let go of it!");
				}else if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
					notify("You aren't skilled enough to use the "+nameOf(item)+".");
				}else if(item.isTwoHanded() && shield != null){
					notify("The "+nameOf(item)+" is too unwieldy to use alongside your "+nameOf(shield)+"!");
				}else {
					if(item.isCursed()) {
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
					}

				}

			}

		}
		else if(item.isArmor()) {
			if(item == armor) {
				if(armor.isCursed()) {
					notify("Your "+nameOf(armor)+" is cursed! You can't take it off!");
				}else {
					unequip(armor);
				}

			}else {
				if(armor != null && armor.isCursed()) {
					notify("Your "+nameOf(armor)+" is cursed! You can't take it off!");
				}else if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
					notify("You aren't skilled enough to use the "+nameOf(item)+".");
				}else {
					if(item.isCursed()) {
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
				if(shield.isCursed()) {
					notify("Your "+nameOf(shield)+" is cursed! You can't put it down!");
				}else {
					unequip(shield);
				}

			}else {
				if(shield != null && shield.isCursed()) {
					notify("Your "+nameOf(shield)+" is cursed! You can't put it down!");
				}else if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
					notify("You aren't skilled enough to use the "+nameOf(item)+".");
				}else if(weapon != null && weapon.isTwoHanded()){
					notify("The "+nameOf(item)+" is too unwieldy to use alongside your "+nameOf(weapon)+"!");
				}else {
					if(item.isCursed()) {
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
				if(ring.isCursed()) {
					notify("Your "+nameOf(ring)+" is cursed! You can't take it off!");
				}else {
					unequip(ring);
				}

			}else {
				if(ring != null && ring.isCursed()) {
					notify("Your "+nameOf(ring)+" is cursed! You can't take it off!");
				}else {
					if(item.isCursed()) {
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
				if(ammunition.isCursed()) {
					notify("Your "+nameOf(ammunition)+" is cursed! You can't take it off!");
				}else {
					unequip(ammunition);
				}

			}else {
				if(ammunition != null && ammunition.isCursed()) {
					notify("Your "+nameOf(ammunition)+" is cursed! You can't put it down!");
				}else {
					if(item.isCursed()) {
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
			modifyScore(amount);
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
			corpse.setID(maxItemIndex()+1+this.id());
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
		item.modifyStackAmount(-1);
		//getRidOf(item);
	}

	public void addEffect(Effect effect) {
		if(effect == null) {
			return;
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

	public void cureParalysis() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isParalysis()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackParalysis() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isParalysis()) {
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

	public void cureCorrosion() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isCorrosion()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackCorrosion() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isCorrosion()) {
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

	public void cureFrozen() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isFrozen()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackFrozen() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isFrozen()) {
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

	public void cureIgnited() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isIgnited()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackIgnited() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isIgnited()) {
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

	public void cureElectrified() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isElectrified()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackElectrified() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isElectrified()) {
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

	public void curePoison() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isPoison()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackPoison() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isPoison()) {
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

	public void cureInvisible() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isInvisible()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackInvisible() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isInvisible()) {
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

	public void cureGiantStrength() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isGiantStrength()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackGiantStrength() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isGiantStrength()) {
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
	
	public void cureBeastForm() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isBeastForm()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackBeastForm() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isBeastForm()) {
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

	public void cureMindVision() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isMindVision()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackMindVision() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isMindVision()) {
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

	public void cureArcWard() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isArcWard()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackArcWard() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isArcWard()) {
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

	public void cureMagmaWard() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isMagmaWard()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackMagmaWard() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isMagmaWard()) {
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

	public void cureChillWard() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isChillWard()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackChillWard() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isChillWard()) {
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

	public void cureConfused() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isConfused()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackConfused() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isConfused()) {
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

	public void cureLevitating() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isLevitating()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackLevitating() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isLevitating()) {
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

	public void cureDevoured() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isDevoured()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackDevoured() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isDevoured()) {
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

	public void cureBlinded() {
		List<Effect> cured = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isBlinded()) {
				effect.end(this);
				cured.add(effect);
			}
		}
		effects.removeAll(cured);
	}

	public void stackBlinded() {
		List<Effect> stacked = new ArrayList<Effect>();

		for(Effect effect : effects) {
			if(effect.isBlinded()) {
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
		if(isInvisible() == false && color == ExtraColors.invisible) {
			changeColor(defaultColor);
		}
	}

	public void modifyFood(int amount) {
		food += amount;
		if(food > maxFood) {
			//maxFood = maxFood + food / 2;
			food = maxFood;
			notify("You can't believe you can eat that much!");
			Damage damage = new Damage(2, false, false, Damage.physical, factory().effectFactory);
			modifyHP(damage, "Killed by overeating");

		}else if(food < 0 && isPlayer()) {
			notify("You are starving!");
			food = 0;
			Damage damage = new Damage((int)(maxHP / 10), false, false, Damage.physical, factory().effectFactory);
			modifyHP(damage, "Starved to death");
		}

	}

	public boolean isPlayer() {
		return defaultGlyph == '@';
	}

	public void stackEffects() {
		stackParalysis();
		stackCorrosion();
		stackFrozen();
		stackIgnited();
		stackElectrified();
		stackPoison();
		stackInvisible();
		stackGiantStrength();
		stackBeastForm();
		stackMindVision();
		stackArcWard();
		stackMagmaWard();
		stackChillWard();
		stackConfused();
		stackLevitating();
		stackDevoured();
		stackBlinded();
	}
	
	public void stepInPit() {
		if(realTile(this.x(), this.y(), this.z()) == Tile.PIT && !this.isFlying() && !this.isLevitating()) {
			Effect fall = this.ai.factory.effectFactory.pitFall();
			addEffect(fall);
		}
	}
	
	public void stepInFire() {
		if(realSubtile(this.x(), this.y(), this.z()) == Tile.FIRE && !this.isFlying() && !this.isLevitating() && (ExtraMaths.d10() > 4)) {
			Effect fire = this.ai.factory.effectFactory.ignited();
			addEffect(fire);
		}
	}

	public void stepInParalysis() {
		if(realGastile(this.x(), this.y(), this.z()) == Tile.PARALYZE_GAS && (ExtraMaths.d10() > 4)) {
			Effect paralysis = this.ai.factory.effectFactory.paralyzed();
			addEffect(paralysis);
		}
	}

	public void stepInCaustic() {
		if(realGastile(this.x(), this.y(), this.z()) == Tile.ACID_GAS && (ExtraMaths.d10() > 4)) {
			Effect caustic = this.ai.factory.effectFactory.corroded();
			addEffect(caustic);
		}
	}

	public void stepInConfusion() {
		if(realGastile(this.x(), this.y(), this.z()) == Tile.CONFUSE_GAS && (ExtraMaths.d10() > 4)) {
			Effect confusion = this.ai.factory.effectFactory.confused();
			addEffect(confusion);
		}
	}



	public void applyCurses() {
		if(weapon != null && weapon.isCursed() && ExtraMaths.diceRoll(1, 15) > 14) {
			this.notify("The curse within your weapon takes hold!");
			this.addEffect(weapon.curseEffect());
		}
		if(armor != null && armor.isCursed() && ExtraMaths.diceRoll(1, 15) > 14) {
			this.notify("The curse within your armour takes hold!");
			this.addEffect(armor.curseEffect());
		}
		if(shield != null && shield.isCursed() && ExtraMaths.diceRoll(1, 15) > 14) {
			this.notify("The curse within your shield takes hold!");
			this.addEffect(shield.curseEffect());
		}
		if(ring != null && ring.isCursed() && ExtraMaths.diceRoll(1, 15) > 14) {
			this.notify("The curse within your ring takes hold!");
			this.addEffect(ring.curseEffect());
		}
		if(ammunition != null && ammunition.isCursed() && ExtraMaths.diceRoll(1, 15) > 14) {
			this.notify("The curse within your ammunition takes hold!");
			this.addEffect(ammunition.curseEffect());
		}

	}
	//jump
	public void stackItems() {
		for(int i = 0; i < maxItemIndex()+17; i++) {
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
		Item trap = world.item(x, y, z);
		if(trap != null && trap.isTrap() && !isFlying && !isLevitating) {
			triggerTrap(trap);
		}else {

		}
		ai.onUpdate();
	}
	//jump
	private void regenerateHealth() {
		regenHPCooldown -= 1;
		if(regenHPCooldown <= 0) {
			int regen = (int)Math.ceil(this.maxHP()*this.healthRegenPercentage());
			Damage amount = new Damage(regen, true, true, null, factory().effectFactory);
			modifyHP(amount, "");
			modifyFood(-1);
			regenHPCooldown = 3;
		}
	}

	private void regenerateMana() {
		regenManaCooldown -= 1;
		if(regenManaCooldown <= 0) {
			int regen = (int)Math.ceil(this.maxHP()*this.manaRegenPercentage());
			Damage amount = new Damage(regen, true, true, null, factory().effectFactory);
			modifyMana(amount);
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
					Damage amount = new Damage(spell.manaCost(), false, false, null, factory().effectFactory);
					modifyMana(amount);
				}
				return;
			}
			if(item != null) {
				if((item.usesStrength() && this.strength() < item.strengthRequirement()) || (item.usesDexterity() && this.dexterity() < item.dexterityRequirement()) || (item.usesIntelligence() && this.intelligence() < item.intelligenceRequirement())) {
					notify("The spell housed within the "+nameOf(item)+" is too complex to understand...");
					return;
				}
			}
			Creature tileSpell = ai().factory.newTileSpell(this.z(), this, 0);
			ai().world.addAtGivenLocation(tileSpell, x2, y2, z);
			tileSpell.addEffect(spell.effect());
			tileSpell.update();
			//
			//ai().world.remove(tileSpell);
			//
			//other.setLastHit(this);
			if(spell.manaCost() > 0) {
				Damage amount = new Damage(spell.manaCost(), false, false, null, factory().effectFactory);
				modifyMana(amount);
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
				}
			}
			if(this.isInvisible() == true) {
				this.cureInvisible();
			}

		}else {
			if(other == null || other.isContainer() == true || other.isDisguised() == true) {
				doAction("point and mutter at nothing in particular...");
				if(spell.manaCost() > 0) {
					Damage amount = new Damage(spell.manaCost(), false, false, null, factory().effectFactory);
					modifyMana(amount);
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
				Damage amount = new Damage(spell.manaCost(), false, false, null, factory().effectFactory);
				modifyMana(amount);
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
				}
			}
			if(this.isInvisible() == true) {
				this.cureInvisible();
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
		int failure = 0;
		int success = 0;
		for (int ox = -searchRadius; ox < searchRadius+1; ox++){
			for (int oy = -searchRadius; oy < searchRadius+1; oy++){
				int nx = x + ox;
				int ny = y + oy;
				if (ox == 0 && oy == 0 || item(nx, ny, z) != null) {
					if(item(nx, ny, z) != null) {
						if(item(nx, ny, z).isTrap() && !item(nx, ny, z).isTrapFound()) {
							if(this.dexterityRoll() >= successChance) {
								item(nx, ny, z).setColor(item(nx, ny, z).defaultColor());
								item(nx, ny, z).changeGlyph(item(nx, ny, z).defaultGlyph());
								item(nx, ny, z).setIsTrapFound(true);
								notify("You spotted a "+item(nx, ny, z).name()+"!");
								failure = 0;
								success++;
							}else {
								failure++;
							}
						}else {
							
						}
					}else {
						failure++;
					}
				}

			}
		}
		if(passive == false) {
			modifyFood(-10);
		}
		if(failure > 0 && success == 0 && passive == false) {
			notify("You didn't spot anything.");
		}
	}

	public boolean canSee(int wx, int wy, int wz) {
		//return (mindVision > 0 && world.creature(wx, wy, wz) !=null || ai.canSee(wx, wy, wz));
		if(hasMindVision == true && world.creature(wx, wy, wz) !=null) {
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

	


}
