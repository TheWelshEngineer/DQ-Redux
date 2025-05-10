package RogueLike.Main.Creatures.Fungi;

import java.awt.Color;

import RogueLike.Main.AI.FungusAI.FungusAI;
import RogueLike.Main.Creatures.Creature;

public class Fungus extends Creature{
	
	private static final long serialVersionUID = 7506296502954424631L;
	private static int defaultMaxHP = 10;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 9;
	private static int defaultStrength = 3;
	private static int defaultDexterity = 3;
	private static int defaultIntelligence = 8;
	private static int defaultVisionRadius = 3;

	public Fungus( String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new FungusAI(this);
		this.creatureTypes.add("Plant");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);	
	}

}
