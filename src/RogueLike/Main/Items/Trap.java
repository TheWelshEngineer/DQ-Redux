package RogueLike.Main.Items;

import java.awt.Color;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtraColors;
import RogueLike.Main.Factories.ObjectFactory;

public class Trap extends Item{
	
	
	public static final String fireTrap = "Fire Trap";
	public static final String frostbiteTrap = "Frostbite Trap";
	public static final String lightningTrap = "Lightning Trap";
	public static final String blinkTrap = "Blink Trap";
	public static final String summoningTrap = "Summoning Trap";
	
	private String trapType;
	public String trapType() {
		return trapType;
	}
	public void setTrapType(String type) {
		trapType = type;
	}
	
	private ObjectFactory factory;
	
	public Trap(char glyph, Color color, String name, String appearance, String type, Effect effect, ObjectFactory factory) {
		super(glyph, color, name, appearance);
		this.setIsTrap(true);
		this.setQuaffEffect(effect);
		this.setColor(ExtraColors.trap);
		this.changeGlyph((char)250);
		this.factory = factory;
		
	}
	
	public Effect perceptionTrapEffect(String type) {
		switch(type) {
		case Trap.fireTrap: return factory.effectFactory.magmaWard(10);
		case Trap.frostbiteTrap: return factory.effectFactory.chillWard(10);
		case Trap.lightningTrap: return factory.effectFactory.arcWard(10);
		case Trap.blinkTrap: return factory.effectFactory.bounce();
		case Trap.summoningTrap: return factory.effectFactory.invisible(10);
		default: return factory.effectFactory.magmaWard(10);
		}
	}

}
