package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.ObjectFactory;
import RogueLike.Main.Items.Item;

public class UpgradeScreen extends InventoryBasedScreen{
	
	public ObjectFactory factory;

	public UpgradeScreen(Creature player, ObjectFactory factory) {
		super(player);
		this.factory = factory;
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
		factory.upgradeItem(item, 1);
		player.notify("Your "+player.nameOf(item)+" looks much better!");
		if(item.isEnchanted() && item.isIdentified()) {
			player.notify("The interaction of different types of magic");
			player.notify("has clouded your memory of the "+player.nameOf(item)+"!");
		}
		item.setIsIdentified(false);
		return null;
	}

}
