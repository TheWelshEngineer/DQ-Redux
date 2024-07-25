package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;

public class EatScreen extends InventoryBasedScreen {

    public EatScreen(Creature player) {
        super(player);
    }

    @Override
    protected String getVerb() {
        return "eat";
    }

    @Override
    protected boolean isAcceptable(Item item) {
        return item.foodValue() != 0;
    }

    @Override
    protected Screen use(Item item) {
        // player.eat(item);
        player.ai().playerAIEatItem(item);
        /*
        if(player.playerClass() == "Warrior") {
        	player.modifyHP((int)(player.maxHP() / 5), "");
        }
        if(player.playerClass() == "Mage") {
        	player.modifyMana((int)(player.maxMana() / 5));
        }
        */
        return null;
    }
}
