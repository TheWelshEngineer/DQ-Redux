package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;

public class EquipScreen extends InventoryBasedScreen {

    public EquipScreen(Creature player) {
        super(player);
    }

    @Override
    protected String getVerb() {
        return "wear or wield";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        return item.isEquippable();
    }

    @Override
    protected Screen use(Item item) {
        // player.equip(item);
        player.ai().playerAIEquipItem(item);
        return null;
    }
}
