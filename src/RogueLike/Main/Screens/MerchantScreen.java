package RogueLike.Main.Screens;

import java.util.ArrayList;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;

public class MerchantScreen extends InventoryBasedScreen{
	
	private Creature merchant;
	private Creature player;

	public MerchantScreen(Creature merchant, Creature player) {
		super(merchant);
		this.merchant = merchant;
		this.player = player;
	}

	@Override
	protected String getVerb() {
		// TODO Auto-generated method stub
		return "buy";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected Screen use(Item item) {
		// TODO add items to player inventory
		return null;
	}
	
	public void displayOutput(ExtendedAsciiPanel terminal) {
		ArrayList<String> lines = getList();
		
		int y = 26;
		int x = 4;
		
		if(lines.size() > 0) {
			terminal.clear(' ', x, y, 20, lines.size());
		}
		for(String line : lines) {
			terminal.write(line, x, y++);
		}
		
		terminal.clear(' ', 0, 23, 80, 1);
		terminal.write("Greetings, "+player.playerClass()+"!", 2, 24);
		terminal.write("What would you like to "+getVerb()+"? You look to have "+player.gold()+" gold in your pouch, no?", 2, 25);
		
		terminal.repaint();
	}
	
	protected ArrayList<String> getList(){
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<Item> inventory = merchant.inventory().getItems();
		
		for(int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if(item == null || !isAcceptable(item)) {
				continue;
			}
			String itemCount = "";
			String itemPrice = " (" + item.currentGoldValue() + " gold)";
			if(item.stackAmount() > 1) {
				itemCount = String.format(" x%d", item.stackAmount());
				itemPrice = " (" + item.currentGoldValue() + " gold each)";
			}
			
			String line = letters.charAt(i) + " - " + item.glyph() + " " + merchant.nameOf(item) + itemCount + itemPrice;
			lines.add(line);
		}
		return lines;
	}

}
