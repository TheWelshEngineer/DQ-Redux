package RogueLike.Main.Items;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Spell;

public class Scroll extends Item {

    /**
     * @param factory The ObjectFactory from which to reference the Scroll's appearances.
     * @param spellFactory The SpellFactory from which to reference the Scroll's spell.
     * @param glyph A Code Page 437 char which visually represents the Scroll.
     * @param name A string with the first letter of each word capitalised.
     * @param appearance An integer representing a key in factory.scrollAppearances.
     * @param spell The Spell to be contained within the Scroll.
     * @param goldValue The base value in gold of the Scroll.
     * @param id The unique identifying integer of the item.
     */
    public Scroll(
            ObjectFactory factory,
            char glyph,
            String name,
            int appearance,
            Spell spell,
            int goldValue,
            int id) {
        super(glyph, ExtendedAsciiPanel.white, name, null);
        this.setAppearance(factory.scrollAppearances.get(appearance));
        this.addWrittenSpell(spell);
        this.setColor(factory.scrollColors.get(this.getAppearance()));
        this.setBaseGoldValue(goldValue);
        this.setCurrentGoldValue(this.baseGoldValue());
        this.setIsStackable(true);
        this.setIsScroll(true);
        this.setQuickslottable(true);
        this.setID(id);
    }
}
