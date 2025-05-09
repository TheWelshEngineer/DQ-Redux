package RogueLike.Main.Creatures.Fungi;

import java.awt.Color;

import RogueLike.Main.AI.FungusAI.BloodFungusAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.DamageType;

public class BloodFungus extends Creature{
	
	private static final long serialVersionUID = -3739976433394102874L;
	private static int defaultMaxHP = 15;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 12;
	private static int defaultStrength = 13;
	private static int defaultDexterity = 3;
	private static int defaultIntelligence = 8;
	private static int defaultVisionRadius = 2;

	public BloodFungus( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new BloodFungusAI(this, player);
		this.creatureTypes.add("Plant");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);	
		this.setUnarmedDamageType(DamageType.POISON);
	}

}
