package RogueLike.Main.Factories;

import java.awt.Color;

import RogueLike.Main.Particle;

public class ParticleFactory {
	
	public Particle starburst(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)15);
	}
	
	public Particle heart(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)3);
	}
	
	public Particle diamond(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)4);
	}
	
	public Particle crossburst(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph(Character.toChars(0x203B)[0]);
	}
	
	public Particle sparkles(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph(Character.toChars(2728)[0]);
	}
	
	public Particle snowflake(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph(Character.toChars(2744)[0]);
	}
	
	public Particle poison(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph(Character.toChars(2620)[0]);
	}

}
