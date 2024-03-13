package RogueLike.Main.Factories;

import java.awt.Color;

import RogueLike.Main.Particle;

public class ParticleFactory {
	
	public Particle sunburst(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)15);
	}
	
	public Particle heart(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)3);
	}
	
	public Particle diamond(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)4);
	}
	
	public Particle fire(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(167+256));
	}
	
	public Particle frost(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(166+256));
	}
	
	public Particle crossbones(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(165+256));
	}
	
	public Particle shock(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(164+256));
	}
	
	public Particle sparkle(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(163+256));
	}
	
	public Particle vortex(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(162+256));
	}
	
	public Particle droplet(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(161+256));
	}
	
	public Particle blast(Color color, int duration) {
		return new Particle().changeColor(color).changeDuration(duration).changeGlyph((char)(160+256));
	}
	


}
