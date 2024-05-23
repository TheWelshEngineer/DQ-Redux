package RogueLike.Main.Creatures.Slimes;

import java.awt.Color;

import RogueLike.Main.AI.SlimeAI.ThundercloudSlimeAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Enums.DamageType;
import RogueLike.Main.Factories.ObjectFactory;

public class ThundercloudSlime extends Creature{
	
	private static int defaultMaxHP = 15;
	private static int defaultMaxMana = 20;
	private static int defaultAC = 12;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 6;
	private static int defaultIntelligence = 14;
	private static int defaultVisionRadius = 10;

	public ThundercloudSlime(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new ThundercloudSlimeAI(this, player, factory, factory.world);
		this.creatureTypes.add("Ooze");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.addImmunityTo(DamageType.SHOCK);
		this.setUnarmedDamageType(DamageType.SHOCK);
	}

}
