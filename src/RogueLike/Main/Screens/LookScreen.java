package RogueLike.Main.Screens;

import RogueLike.Main.Entities.Entity;
import RogueLike.Main.Entities.Trap;
import RogueLike.Main.Tile;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;


public class LookScreen extends TargetBasedScreen{

	public LookScreen(Creature player, String caption) {
		super(player, caption);
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

		Entity entity = player.world().entity(x, y, player.z);
		if (entity != null) {
			if (!(entity instanceof Trap) || ((Trap) entity).isRevealed()) {
				caption = String.format("%c %s", entity.glyph(), entity.name());
				return;
			}
		}
		
		Tile tile = player.tile(x, y, player.z);
		if(tile != null) {
			caption = tile.glyph() + " " + tile.name() + " - " + tile.details();
		}
	}

}
