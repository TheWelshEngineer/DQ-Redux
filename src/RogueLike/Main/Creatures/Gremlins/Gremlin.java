package RogueLike.Main.Creatures.Gremlins;

import java.awt.Color;

import RogueLike.Main.AI.GremlinAI.GremlinAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class Gremlin extends Creature{
	
	private static int defaultMaxHP = 17;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 12;
	private static int defaultStrength = 8;
	private static int defaultDexterity = 14;
	private static int defaultIntelligence = 10;
	private static int defaultVisionRadius = 8;

	public Gremlin(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new GremlinAI(this, player, factory, factory.world);
		this.creatureTypes.add("Gremlin");
		Item startWeapon = factory.itemFactory.newShortbow(0, false, true, true, false);
		this.inventory().add(startWeapon);
		this.equip(startWeapon, true);
		Item startAmmo = factory.itemFactory.newArrows(0, 0);
		this.inventory().add(startAmmo);
		this.equip(startAmmo, true);
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}