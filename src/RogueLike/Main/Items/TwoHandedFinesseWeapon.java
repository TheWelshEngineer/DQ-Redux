package RogueLike.Main.Items;

import RogueLike.Main.Dice;

import java.awt.Color;

public class TwoHandedFinesseWeapon extends TwoHandedMeleeWeapon {

    public TwoHandedFinesseWeapon(
            char glyph,
            Color color,
            String name,
            String appearance,
            Dice damageDice,
            int goldValue,
            int id) {
        super(glyph, color, name, appearance, damageDice, goldValue, id);
        this.setIsFinesse(true);
    }
}
