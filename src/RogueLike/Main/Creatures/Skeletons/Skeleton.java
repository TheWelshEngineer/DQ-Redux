package RogueLike.Main.Creatures.Skeletons;

import java.awt.Color;

import RogueLike.Main.AI.SkeletonAI.SkeletonAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class Skeleton extends Creature{
	
	private static final long serialVersionUID = -4850062311212379903L;
	private static int defaultMaxHP = 20;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 14;
	private static int defaultStrength = 14;
	private static int defaultDexterity = 8;
	private static int defaultIntelligence = 8;
	private static int defaultVisionRadius = 6;

	public Skeleton( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new SkeletonAI(this, player);
		Item startWeapon = FactoryManager.getItemFactory().newShortsword(0, false, true, true, false);
		this.inventory().add(startWeapon);
		this.equip(startWeapon, true);
		this.creatureTypes.add("Undead");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}