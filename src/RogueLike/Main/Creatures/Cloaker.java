package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.ExtraColors;
import RogueLike.Main.AI.CloakerAI;
import RogueLike.Main.Factories.ObjectFactory;

public class Cloaker extends Creature{
	
	private static int defaultMaxHP = 22;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 11;
	private static int defaultStrength = 14;
	private static int defaultDexterity = 18;
	private static int defaultIntelligence = 8;
	private static int defaultVisionRadius = 8;

	public Cloaker(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		CloakerAI ai = new CloakerAI(this, player, factory, factory.world);
		ai.becomeInvisible();
		this.changeColor(ExtraColors.invisible);
		this.creatureTypes.add("Monstrosity");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}