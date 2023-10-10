package RogueLike.Main.Screens;

import RogueLike.Main.Creature;
import RogueLike.Main.Item;
import RogueLike.Main.ObjectFactory;

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
		return (item.isWeapon() || item.isArmor() || item.isShield()) && !item.isEnchanted();
	}

	@Override
	protected Screen use(Item item) {
		item.setIsEnchanted(true);
		item.modifyCurrentGoldValue(item.baseGoldValue());
		if(item.isWeapon()) {
			factory.randomEnchantWeapon(item);
		}else {
			factory.randomEnchantArmor(item);
		}

		
		player.notify("The "+player.nameOf(item)+" shines with fresh magic!");
		return null;
	}

}
