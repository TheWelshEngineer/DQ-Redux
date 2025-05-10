package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Utils.PointShapes.Line;
import RogueLike.Main.Utils.PointShapes.Point;

public class ThrowAtScreen extends TargetBasedScreen{
	private Item item;

	public ThrowAtScreen(Creature player, Item item) {
		super(player, "Throw "+ player.nameOf(item)+ " at?");
		this.item = item;
	}
	
	public boolean isAcceptable(int x, int y) {
		if(!player.canSee(x, y, player.z)) {
			return false;
		}
		
		for(Point p : new Line(player.x, player.y, x, y)) {
			if(player.realTile(p.x, p.y, player.z).blocksRangedAttacks()) {
				return false;
			}
		}
		return true;
	}
	
	public void selectWorldCoordinate(int x, int y, int screenX, int screenY) {
		//player.throwItem(item, x, y, player.z);
		player.ai().playerAIThrowItem(item, x, y, player.z());
	}

}
