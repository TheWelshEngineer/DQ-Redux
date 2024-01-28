package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Items.Item;

public class EnchantScreen extends InventoryBasedScreen{
	
	public ObjectFactory factory;

	public EnchantScreen(Creature player, ObjectFactory factory) {
		super(player);
		this.factory = factory;
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
			factory.randomEnchantWeapon(item);
		}else {
			factory.randomEnchantArmor(item);
		}

		
		player.notify("The "+player.nameOf(item)+" shines with fresh magic!");
		return null;
	}

}
