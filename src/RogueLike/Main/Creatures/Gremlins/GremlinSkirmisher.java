package RogueLike.Main.Creatures.Gremlins;

import java.awt.Color;

import RogueLike.Main.AI.GremlinAI.GremlinSkirmisherAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class GremlinSkirmisher extends Creature{
	
	private static int defaultMaxHP = 22;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 12;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 14;
	private static int defaultIntelligence = 10;
	private static int defaultVisionRadius = 8;

	public GremlinSkirmisher(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new GremlinSkirmisherAI(this, player, factory, factory.world);
		this.creatureTypes.add("Gremlin");
		Item startWeapon = factory.itemFactory.newShortsword(0, false, true, true, false);
		this.inventory().add(startWeapon);
		this.equip(startWeapon, true);
		Item startArmor = factory.itemFactory.newLeatherArmor(0, false, true, true, false);
		this.inventory().add(startArmor);
		this.equip(startArmor, true);
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}