package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.AI.MimicAI;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class Mimic extends Creature{
	
	private static final long serialVersionUID = -5875056221948907226L;
	private static int defaultMaxHP = 50;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 12;
	private static int defaultStrength = 17;
	private static int defaultDexterity = 12;
	private static int defaultIntelligence = 5;
	private static int defaultVisionRadius = 8;

	public Mimic( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new MimicAI(this, player);
		this.creatureTypes.add("Monstrosity");
		Item startItem = FactoryManager.getObjectFactory().randomMimicDrop(0, false, true, true, true);
		this.inventory().add(startItem);
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.hide();
	}

}