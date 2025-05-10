package RogueLike.Main.Creatures;

import java.awt.Color;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.AI.CloakerAI;

public class Cloaker extends Creature{
	
	private static final long serialVersionUID = 4263134700956135756L;
	private static int defaultMaxHP = 22;
	private static int defaultMaxMana = 10;
	private static int defaultAC = 11;
	private static int defaultStrength = 14;
	private static int defaultDexterity = 18;
	private static int defaultIntelligence = 8;
	private static int defaultVisionRadius = 8;

	public Cloaker( Creature player, String name, char glyph, Color color, int id, int depth) {
		super( name, glyph, color, defaultMaxHP, defaultMaxMana, defaultAC, defaultStrength, defaultDexterity, defaultIntelligence, defaultVisionRadius, 20);
		this.setID(id);
		CloakerAI ai = new CloakerAI(this, player);
		ai.becomeInvisible();
		this.changeColor(ExtendedAsciiPanel.invisible);
		this.creatureTypes.add("Monstrosity");
		this.scaleHPWithDepth(depth);
		this.scaleManaWithDepth(depth);	
		this.scaleStrengthWithDepth(depth);	
		this.scaleDexterityWithDepth(depth);	
		this.scaleIntelligenceWithDepth(depth);
	}

}