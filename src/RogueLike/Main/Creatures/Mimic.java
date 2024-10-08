package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.Dice;
import RogueLike.Main.AI.MimicAI;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class Mimic extends Creature{
	
	private static int defaultMaxHP = 50;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 12;
	private static int defaultStrength = 17;
	private static int defaultDexterity = 12;
	private static int defaultIntelligence = 5;
	private static int defaultVisionRadius = 8;

	public Mimic(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new MimicAI(this, player, factory, factory.world);
		this.creatureTypes.add("Monstrosity");
		Item startItem = factory.randomMimicDrop(0, false, true, true, true);
		this.inventory().add(startItem);
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.hide();
	}

}