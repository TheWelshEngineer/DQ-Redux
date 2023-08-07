package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Item;

public class EquipScreen extends InventoryBasedScreen{

	public EquipScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "wear or wield";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return item.equippable() > 0;
	}

	@Override
	protected Screen use(Item item) {
		player.equip(item);
		return null;
	}

}
