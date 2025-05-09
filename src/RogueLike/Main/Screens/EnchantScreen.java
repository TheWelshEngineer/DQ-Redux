package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;

public class EnchantScreen extends InventoryBasedScreen{

	public EnchantScreen(Creature player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "enchant";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return (item.isWeapon() || item.isArmor() || item.isShield()) && item.enchantment() == null;
	}

	@Override
	protected Screen use(Item item) {
		if(item.isWeapon()) {
			FactoryManager.getObjectFactory().randomEnchantWeapon(item);
		}else {
			FactoryManager.getObjectFactory().randomEnchantArmor(item);
		}

		
		player.notify("The "+player.nameOf(item)+" shines with fresh magic!");
		return null;
	}

}
