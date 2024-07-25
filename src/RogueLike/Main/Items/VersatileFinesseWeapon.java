package RogueLike.Main.Items;

import RogueLike.Main.Dice;

import java.awt.Color;

public class VersatileFinesseWeapon extends VersatileMeleeWeapon {

    public VersatileFinesseWeapon(
            char glyph,
            Color color,
            String name,
            String appearance,
            Dice damageDice,
            Dice versatileDice,
            int goldValue,
            int id) {
        super(glyph, color, name, appearance, damageDice, versatileDice, goldValue, id);
        this.setIsFinesse(true);
    }
}
