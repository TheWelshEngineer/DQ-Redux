package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class UpgradeScreen extends InventoryBasedScreen{

	public UpgradeScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "upgrade";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return (item.isArmor() || item.isWeapon() || item.isShield()) && (item.upgradeLevel() < 10) && (!item.isWand());
	}

	@Override
	protected Screen use(Item item) {
		FactoryManager.getObjectFactory().upgradeItem(item, 1);
		player.notify("Your "+player.nameOf(item)+" looks much better!");
		if(item.enchantment() != null && item.isIdentified()) {
			player.notify("The interaction of different types of magic");
			player.notify("has clouded your memory of the "+player.nameOf(item)+"!");
			item.setIsIdentified(false);
		}
		return null;
	}

}
