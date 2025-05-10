package RogueLike.Main.Creatures.Gremlins;

import java.awt.Color;

import RogueLike.Main.AI.GremlinAI.GremlinAlchemistAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class GremlinAlchemist extends Creature{
	
	private static final long serialVersionUID = -6703815865341146854L;
	private static int defaultMaxHP = 15;
	private static int defaultMaxMana = 15;
	private static int defaultAC = 12;
	private static int defaultStrength = 10;
	private static int defaultDexterity = 12;
	private static int defaultIntelligence = 14;
	private static int defaultVisionRadius = 8;

	public GremlinAlchemist( Creature player, String name, char glyph, Color color, int id, int depth) {
		super(name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		new GremlinAlchemistAI(this, player);
		Item startWeapon = FactoryManager.getItemFactory().newDagger(0, false, true, true, false);
		Item startArmor = FactoryManager.getItemFactory().newLeatherArmor(0, false, true, true, false);
		this.inventory().add(startWeapon);
		this.inventory().add(startArmor);
		this.equip(startWeapon, true);
		this.equip(startArmor, true);
		this.creatureTypes.add("Gremlin");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}