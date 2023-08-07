package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Item;
import RogueLike.Main.ObjectFactory;

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
		return (item.isArmor() > 0 || item.isWeapon() > 0 || item.isShield() > 0) && (item.upgradeLevel() < 10) && (item.isWand() == 0);
	}

	@Override
	protected Screen use(Item item) {
		factory.upgradeItem(item, 1);
		player.notify("Your "+player.nameOf(item)+" looks much better!");
		if(item.isEnchanted() > 0 && item.isIdentified() > 0) {
			player.notify("The interaction of different types of magic");
			player.notify("has clouded your memory of the "+player.nameOf(item)+"!");
		}
		item.modifyIsIdentified(-1);
		return null;
	}

}
