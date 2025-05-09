package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;


public abstract class InventoryBasedScreen implements Screen{
	
	protected Creature player;
	public String letters;
	
	protected abstract String getVerb();
	protected abstract boolean isAcceptable(Item item);
	protected abstract Screen use(Item item);
	
	public InventoryBasedScreen(Creature player) {
		this.player = player;
		this.letters = "abcdefghijklmnopqrstuvwxyz";
		
	}
	
	public void displayOutput() {
		ArrayList<String> lines = getList();
		
		int y = 26;
		int x = 4;
		
		if(lines.size() > 0) {
			ExtendedAsciiPanel.clear(' ', x, y, 20, lines.size());
		}
		for(String line : lines) {
			ExtendedAsciiPanel.write(line, x, y++);
		}
		
		ExtendedAsciiPanel.clear(' ', 0, 23, 80, 1);
		ExtendedAsciiPanel.write("What would you like to "+getVerb()+"?", 2, 24);
		
		ExtendedAsciiPanel.getInstance().repaint();
	}
	
	protected ArrayList<String> getList(){
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<Item> inventory = player.inventory().getItems();
		
		for(int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if(item == null || !isAcceptable(item)) {
				continue;
			}
			String itemCount = "";
			if(item.stackAmount() > 1) {
				itemCount = String.format(" x%d", item.stackAmount());
			}
			String line = letters.charAt(i) + " - " + item.glyph() + " " + player.nameOf(item) + itemCount;
			if(item.enchantment() != null) {
				line += " (enchanted)";
			}
			if(item.isCurseKnown()) {
				line += " (cursed)";
			}
			if(item == player.weapon() || item == player.armor() || item == player.shield() || item == player.ring() || item == player.ammunition()) {
				line += " (equipped)";
			}
			lines.add(line);
		}
		return lines;
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		char c = key.getKeyChar();
		
		ArrayList<Item> items = player.inventory().getItems();
		
		if(letters.indexOf(c) > - 1 
				&& items.size() > letters.indexOf(c)
				&& items.get(letters.indexOf(c)) != null 
				&& isAcceptable(items.get(letters.indexOf(c)))) {
			
			return use(items.get(letters.indexOf(c)));
		}
		else if(key.getKeyCode() == KeyEvent.VK_ESCAPE){
			return null;
		}
		else {
			return this;
		}
	}

}
