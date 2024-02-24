package RogueLike.Main.Creatures.Gremlins;

import java.awt.Color;

import RogueLike.Main.AI.GremlinAI.GremlinAlchemistAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class GremlinAlchemist extends Creature{
	
	private static int defaultMaxHP = 15;
	private static int defaultMaxMana = 15;
	private static int defaultAC = 12;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 12;
	private static int defaultIntelligence = 14;
	private static int defaultVisionRadius = 8;

	public GremlinAlchemist(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new GremlinAlchemistAI(this, player, factory, factory.world);
		Item startWeapon = factory.itemFactory.newDagger(0, false);
		Item startArmor = factory.itemFactory.newLeatherArmor(0, false);
		this.inventory().add(startWeapon);
		this.inventory().add(startArmor);
		this.equip(startWeapon);
		this.equip(startArmor);
		this.creatureTypes.add("Gremlin");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}