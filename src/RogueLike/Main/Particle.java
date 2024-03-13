package RogueLike.Main;

import java.awt.Color;

public class Particle {
	
	private String name;
	public String name() {
		return name;
	}
	public void setName(String value) {
		name = value;
	}
	
	private char glyph;
	public char glyph() {
		return glyph;
	}
	public void setGlyph(char value) {
		glyph = value;
	}
	public Particle changeGlyph(char value) {
		glyph = value;
		return this;
	}
	
	private Color color;
	public Color color() {
		return color;
	}
	public void setColor(Color value) {
		color = value;
	}
	public Particle changeColor(Color value) {
		color = value;
		return this;
	}
	
	private int duration;
	public int duration() {
		return duration;
	}
	public void setDuration(int value) {
		duration = value;
	}
	public Particle changeDuration(int value) {
		duration = value;
		return this;
	}
	
	public Particle(String name, char glyph, Color color, int duration) {
		this.name = name;
		this.glyph = glyph;
		this.color = color;
		this.duration = duration;
	}
	
	public Particle() {
		
	}
	
	public void update() {
		duration--;
	}
	
	public boolean isExpired() {
		return duration <= 0;
	}

}
