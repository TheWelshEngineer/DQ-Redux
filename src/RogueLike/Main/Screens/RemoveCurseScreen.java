package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Item;

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
		return item.isCursed() > 0 && item.curseKnown() > 0;
	}

	@Override
	protected Screen use(Item item) {
		item.modifyIsCursed(-1);
		item.modifyCurseKnown(-1);
		player.notify("The foul magic of the "+player.nameOf(item)+" vanishes!");
		return null;
	}

}
