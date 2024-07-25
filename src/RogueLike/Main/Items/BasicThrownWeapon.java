package RogueLike.Main.Items;

import RogueLike.Main.Dice;

import java.awt.Color;

public class BasicThrownWeapon extends BasicMeleeWeapon {

    public BasicThrownWeapon(
            char glyph,
            Color color,
            String name,
            String appearance,
            Dice damageDice,
            Dice thrownDice,
            int goldValue,
            int id) {
        super(glyph, color, name, appearance, damageDice, goldValue, id);
        this.setIsThrownWeapon(true);
        this.setThrownDamageDice(thrownDice);
    }
}
