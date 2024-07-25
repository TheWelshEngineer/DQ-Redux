package RogueLike.Main.Items;

import java.awt.Color;

public class LightArmor extends BasicArmor {

    public LightArmor(
            char glyph,
            Color color,
            String name,
            String appearance,
            int armorClass,
            int goldValue,
            int id) {
        super(glyph, color, name, appearance, armorClass, goldValue, id);
        this.setIsLightArmor(true);
    }
}
