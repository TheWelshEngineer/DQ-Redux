package RogueLike.Main.Creatures.Slimes;

import java.awt.Color;

import RogueLike.Main.AI.SlimeAI.SlimelingAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;

public class MetalSlimeling extends Creature{
	
	private static int defaultMaxHP = 16;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 13;
	private static int defaultStrength = 12;
	private static int defaultDexterity = 6;
	private static int defaultIntelligence = 1;
	private static int defaultVisionRadius = 6;

	public MetalSlimeling(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new SlimelingAI(this, player, factory, factory.world);
		this.creatureTypes.add("Ooze");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.setResistsPhysicalDamage(true);
		this.setImmunePoisonDamage(true);
		this.setWeakToAcidDamage(true);
	}
}
