package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.AI.ChestAI;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class Merchant extends Creature {

	private static final long serialVersionUID = 7268166820438872659L;
	private static int defaultMaxHP = 50;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 14;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 10;
	private static int defaultIntelligence = 10;
	private static int defaultVisionRadius = 8;

	public Merchant( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new ChestAI(this);
		this.creatureTypes.add("Merchant");
		
		Item startItem = FactoryManager.getObjectFactory().randomSimpleWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomMartialWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomFinesseWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomRangedWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomArmor(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomAmmunition(0, 0);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomFood(0, 0);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getItemFactory().newPotionOfHealing(0, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getItemFactory().newPotionOfMana(0, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getItemFactory().newScrollOfIdentify(0, player, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getItemFactory().newScrollOfRemoveCurse(0, player, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomMagicItem(0, player, false, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = FactoryManager.getObjectFactory().randomMagicItem(0, player, false, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		this.setIsMerchant(true);
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.inventory().shuffle();
	}

}
