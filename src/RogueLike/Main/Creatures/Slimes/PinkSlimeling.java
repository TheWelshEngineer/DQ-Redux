package RogueLike.Main.Creatures.Slimes;

import java.awt.Color;

import RogueLike.Main.AI.SlimeAI.SlimelingAI;
import RogueLike.Main.Creatures.Creature;

public class PinkSlimeling extends Creature{
	
	private static final long serialVersionUID = 470562056209548978L;
	private static int defaultMaxHP = 6;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 8;
	private static int defaultStrength = 12;
	private static int defaultDexterity = 6;
	private static int defaultIntelligence = 1;
	private static int defaultVisionRadius = 6;

	public PinkSlimeling( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new SlimelingAI(this, player);
		this.creatureTypes.add("Ooze");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}
