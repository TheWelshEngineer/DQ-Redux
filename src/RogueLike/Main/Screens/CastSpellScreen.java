package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Spell;
import RogueLike.Main.Items.Item;

public class CastSpellScreen extends TargetBasedScreen{
	private Spell spell;
	public Item item;

	public CastSpellScreen(Creature player, String caption, int sx, int sy, Spell spell, Item item) {
		super(player, caption, sx, sy);
		this.spell = spell;
		this.item = item;
	}
	
	public void selectWorldCoordinate(int x, int y, int screenX, int screenY) {
		player.castSpell(spell, x, y, item);
	}

}
