package RogueLike.Main.Creatures.Skeletons;

import java.awt.Color;

import RogueLike.Main.AI.SkeletonAI.SkeletonMageAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class SkeletonMage extends Creature{
	
	private static int defaultMaxHP = 16;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 14;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 8;
	private static int defaultIntelligence = 16;
	private static int defaultVisionRadius = 6;

	public SkeletonMage(ObjectFactory factory, Creature player, String name, char glyph, Color color, int id, int depth) {
		super(factory.world, name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new SkeletonMageAI(this, player, factory, factory.world);
		Item startWeapon = factory.itemFactory.newClub(0, false);
		this.inventory().add(startWeapon);
		this.equip(startWeapon);
		this.creatureTypes.add("Undead");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.setImmuneFrostDamage(true);
	}

}