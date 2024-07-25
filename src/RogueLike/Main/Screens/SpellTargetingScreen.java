package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Spell;

public class SpellTargetingScreen extends TargetBasedScreen {
    private Spell spell;
    public Item item;

    public SpellTargetingScreen(Creature player, String caption, Spell spell, Item item) {
        super(player, caption, spell.aoe());
        this.spell = spell;
        this.item = item;
    }

    public void selectWorldCoordinate(int x, int y, int screenX, int screenY) {
        // player.castSpell(spell, x, y, item);
        player.ai().playerAICastSpell(item, spell, x, y);
    }
}
