package RogueLike.Main.Creatures;

import java.awt.Color;
import java.util.Collections;

import RogueLike.Main.Dice;
import RogueLike.Main.World;
import RogueLike.Main.AI.ChestAI;
import RogueLike.Main.AI.MimicAI;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class Merchant extends Creature {

	private static int defaultMaxHP = 50;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 14;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 10;
	private static int defaultIntelligence = 10;
	private static int defaultVisionRadius = 8;

	public Merchant(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new ChestAI(this, factory, factory.world);
		this.creatureTypes.add("Merchant");
		
		Item startItem = factory.randomSimpleWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomMartialWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomFinesseWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomRangedWeapon(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomArmor(0, false, true, true, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomAmmunition(0, 0);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomFood(0, 0);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.itemFactory.newPotionOfHealing(0, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.itemFactory.newPotionOfMana(0, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.itemFactory.newScrollOfIdentify(0, player, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.itemFactory.newScrollOfRemoveCurse(0, player, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomMagicItem(0, player, false, false);
		this.learnNameQuiet(startItem);
		this.inventory().add(startItem);
		
		startItem = factory.randomMagicItem(0, player, false, false);
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
