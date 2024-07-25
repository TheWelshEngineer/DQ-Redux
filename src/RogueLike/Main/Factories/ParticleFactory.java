package RogueLike.Main.Factories;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Particle;

import java.awt.Color;

public class ParticleFactory {

    public Particle sunburst(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(15, 1));
    }

    public Particle heart(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(3, 1));
    }

    public Particle diamond(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(4, 1));
    }

    public Particle fire(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(167, 2));
    }

    public Particle frost(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(166, 2));
    }

    public Particle crossbones(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(165, 2));
    }

    public Particle shock(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(164, 2));
    }

    public Particle sparkle(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(163, 2));
    }

    public Particle vortex(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(162, 2));
    }

    public Particle droplet(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(161, 2));
    }

    public Particle blast(Color color, int duration) {
        return new Particle()
                .changeColor(color)
                .changeDuration(duration)
                .changeGlyph(ExtendedAsciiPanel.getGlyphFromPage(160, 2));
    }
}
