package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Items.Item;

public class RemoveCurseScreen extends InventoryBasedScreen{

	public RemoveCurseScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "purify";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return item.isCursed() && item.isCurseKnown();
	}

	@Override
	protected Screen use(Item item) {
		item.setIsCursed(false);
		item.setCurseKnown(false);
		player.notify("The foul magic of the "+player.nameOf(item)+" vanishes!");
		return null;
	}

}
