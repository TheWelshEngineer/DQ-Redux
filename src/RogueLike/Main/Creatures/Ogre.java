package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.AI.OgreAI;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class Ogre extends Creature{
	
	private static final long serialVersionUID = 1997088836516494935L;
	private static int defaultMaxHP = 40;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 14;
	private static int defaultStrength = 18;
	private static int defaultDexterity = 14;
	private static int defaultIntelligence = 7;
	private static int defaultVisionRadius = 8;

	public Ogre( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new OgreAI(this, player);
		Item startWeapon = FactoryManager.getItemFactory().newMaul(0, false, true, true, false);
		Item startArmor = FactoryManager.getItemFactory().newHideArmor(0, false, true, true, false);
		this.inventory().add(startWeapon);
		this.inventory().add(startArmor);
		this.equip(startWeapon, true);
		this.equip(startArmor, true);
		this.creatureTypes.add("Beast");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}