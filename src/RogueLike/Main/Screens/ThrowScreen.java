package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;

public class ThrowScreen extends InventoryBasedScreen{
	
	public ThrowScreen(Creature player) {
		super(player);
	}
	
	protected String getVerb() {
		return "throw";
	}
	
	protected boolean isAcceptable(Item item) {
		return true;
	}
	
	protected Screen use(Item item) {
		return new ThrowAtScreen(player, item);
	}

}
