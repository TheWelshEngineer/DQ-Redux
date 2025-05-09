package RogueLike.Main.Creatures.Skeletons;

import java.awt.Color;

import RogueLike.Main.AI.SkeletonAI.SkeletonCryomancerAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Damage.DamageType;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class SkeletonCryomancer extends Creature{
	
	private static final long serialVersionUID = -8681357567453683622L;
	private static int defaultMaxHP = 16;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 14;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 8;
	private static int defaultIntelligence = 16;
	private static int defaultVisionRadius = 6;

	public SkeletonCryomancer( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new SkeletonCryomancerAI(this, player);
		Item startWeapon = FactoryManager.getItemFactory().newClub(0, false, true, true, false);
		this.inventory().add(startWeapon);
		this.equip(startWeapon, true);
		startWeapon = FactoryManager.getItemFactory().newIceKnifeWand(0, player, false);
		this.inventory().add(startWeapon);
		this.creatureTypes.add("Undead");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
		this.addImmunityTo(DamageType.FROST);
	}

}