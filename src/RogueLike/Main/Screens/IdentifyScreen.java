package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Items.Item;

public class IdentifyScreen extends InventoryBasedScreen{

	public IdentifyScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "identify";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return player.nameOf(item) != item.name();
	}

	@Override
	protected Screen use(Item item) {
		player.learnName(item);
		if(item.isCursed() && !item.isCurseKnown()) {
			item.setCurseKnown(true);
			player.notify("The "+player.nameOf(item)+" is cursed!");
		}
		//item.modifyIsIdentified(1);
		return null;
	}

}
