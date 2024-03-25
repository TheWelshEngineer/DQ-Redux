package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.ObjectFactory;

public class Trap extends Item{
	
	
	public static final String fireTrap = "Fire Trap";
	public static final String frostbiteTrap = "Frostbite Trap";
	public static final String lightningTrap = "Lightning Trap";
	public static final String blinkTrap = "Blink Trap";
	public static final String summoningTrap = "Summoning Trap";
	
	public Trap(char glyph, Color color, String name, String appearance, String type, Effect effect, ObjectFactory factory) {
		super(glyph, color, name, appearance);
		this.setIsTrap(true);
		this.setQuaffEffect(effect);
		this.setTrapType(type);
		this.setColor(ExtendedAsciiPanel.trap);
		this.changeGlyph((char)250);
		
	}
	
	

}
