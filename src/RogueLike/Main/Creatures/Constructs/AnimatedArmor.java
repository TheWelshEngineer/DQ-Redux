package RogueLike.Main.Creatures.Constructs;

import RogueLike.Main.AI.SkeletonAI.SkeletonAI;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

import java.awt.Color;

public class AnimatedArmor extends Creature {

    private static int defaultMaxHP = 35;
    private static int defaultMaxMana = 10;
    private static int defaultAC = 18;
    private static int defaultStrength = 14;
    private static int defaultDexterity = 11;
    private static int defaultIntelligence = 1;
    private static int defaultVisionRadius = 8;

    public AnimatedArmor(
            ObjectFactory factory,
            Creature player,
            String name,
            char glyph,
            Color color,
            int id,
            int depth) {
        super(
                factory.world,
                name,
                glyph,
                color,
                defaultMaxHP,
                defaultMaxMana,
                defaultAC,
                defaultStrength,
                defaultDexterity,
                defaultIntelligence,
                defaultVisionRadius,
                20);
        this.setID(id);
        new SkeletonAI(this, player, factory, factory.world);
        this.creatureTypes.add("Construct");
        Item startArmor = factory.randomArmor(0, false);
        this.inventory().add(startArmor);
        this.equip(startArmor);
        this.setHasNoCorpse(true);
        this.scaleHPWithDepth(depth);
        this.scaleManaWithDepth(depth);
        this.scaleStrengthWithDepth(depth);
        this.scaleDexterityWithDepth(depth);
        this.scaleIntelligenceWithDepth(depth);
    }
}
