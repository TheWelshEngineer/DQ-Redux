package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.AI.BatAI;

public class Bat extends Creature{
	
	private static final long serialVersionUID = -8250546444769674540L;
	private static int defaultMaxHP = 8;
	private static int defaultMaxMana = 1;
	private static int defaultAC = 12;
	private static int defaultStrength = 2;
	private static int defaultDexterity = 15;
	private static int defaultIntelligence = 2;
	private static int defaultVisionRadius = 6;

	public Bat( String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new BatAI(this);
		this.creatureTypes.add("Beast");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.setIsFlying(true);
		//bat.setSocial(true);
	}

}
