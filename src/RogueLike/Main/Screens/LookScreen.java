package RogueLike.Main.Screens;

import RogueLike.Main.Tile;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;


public class LookScreen extends TargetBasedScreen{

	public LookScreen(Creature player, String caption, int sx, int sy) {
		super(player, caption, sx, sy);
	}
	
	public void enterWorldCoordinate(int x, int y, int screenX, int screenY) {
		Creature creature = player.creature(x, y, player.z);
		if(creature != null) {
			caption = creature.glyph() + " " + creature.name() + creature.details();
			return;
		}
		
		Item item = player.item(x, y, player.z);
		if(item != null) {
			caption = item.glyph() + " " + player.nameOf(item) + item.details();
			return;
		}
		
		Tile tile = player.tile(x, y, player.z);
		if(tile != null) {
			caption = tile.glyph() + " " + tile.name() + " - " + tile.details();
		}
	}

}
