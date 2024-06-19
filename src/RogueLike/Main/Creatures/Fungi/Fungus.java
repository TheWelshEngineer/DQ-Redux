package RogueLike.Main.Creatures.Fungi;

import java.awt.Color;

import RogueLike.Main.AI.FungusAI.FungusAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class Fungus extends Creature{
	
	private static int defaultMaxHP = 10;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 9;
	private static int defaultStrength = 3;
	private static int defaultDexterity = 3;
	private static int defaultIntelligence = 8;
	private static int defaultVisionRadius = 3;

	public Fungus(ObjectFactory factory, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new FungusAI(this, factory, factory.world);
		this.creatureTypes.add("Plant");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);	
	}

}
