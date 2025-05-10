package RogueLike.Main.Creatures.Slimes;

import java.awt.Color;

import RogueLike.Main.AI.SlimeAI.MetalSlimeAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.DamageType;

public class MetalSlime extends Creature{
	
	private static final long serialVersionUID = 5796311868286496183L;
	private static int defaultMaxHP = 34;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 13;
	private static int defaultStrength = 12;
	private static int defaultDexterity = 6;
	private static int defaultIntelligence = 1;
	private static int defaultVisionRadius = 6;

	public MetalSlime( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new MetalSlimeAI(this, player);
		this.creatureTypes.add("Ooze");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.addResistanceTo(DamageType.PHYSICAL);
		this.addImmunityTo(DamageType.POISON);
		this.addWeaknessTo(DamageType.ACID);
	}

}
