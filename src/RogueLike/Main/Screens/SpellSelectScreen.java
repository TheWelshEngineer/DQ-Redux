package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Spell;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;

public class SpellSelectScreen implements Screen{
	
	protected Creature player;
	private String letters;
	private Item item;
	private int sx;
	private int sy;
	
	public SpellSelectScreen(Creature player, int sx, int sy, Item item) {
		this.player = player;
		this.letters = "abcdefghijklmnopqrstuvwxyz";
		this.item = item;
		this.sx = sx;
		this.sy = sy;
	}

	@Override
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
        terminal.write("What would you like to read?", 2, 24);
        
        terminal.repaint();
		
	}
	
	private ArrayList<String> getList() {
        ArrayList<String> lines = new ArrayList<String>();
        
        for (int i = 0; i < item.writtenSpells().size(); i++){
            Spell spell = item.writtenSpells().get(i);
            
            String line = letters.charAt(i) + " - " + spell.name() + " (" + spell.manaCost() + " mana)";
            
            lines.add(line);
        }
        return lines;
    }

	@Override
	public Screen respondToUserInput(KeyEvent key) {
        char c = key.getKeyChar();
        //player.inventory().getItems();
        ArrayList<Item> items = player.inventory().getItems();
        //int spells = item.writtenSpells().size();
        
        // && items[letters.indexOf(c)] != null
        if (letters.indexOf(c) > -1 && items.size() > letters.indexOf(c)) {
            return use(item.writtenSpells().get(letters.indexOf(c)), item);
        } else if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
            return null;
        } else {
            return this;
        }
    }

    protected Screen use(Spell spell, Item item){
    	if(spell.isSelfCast()) {
    		//player.doAction(new TerminalText("check"));
    		//player.castSpell(spell, player.x(), player.y(), item);
    		player.ai().playerAICastSpell(item, item.writtenSpells().get(0), player.x(), player.y());
    		return null;
    	}else {
    		//return null;
    		return new SpellTargetingScreen(player, String.format("Cast %s at?", spell.name()), spell, item);
    	}
        //return new CastSpellScreen(player, "Cast spell at?", sx, sy, spell, item);
    }

}
