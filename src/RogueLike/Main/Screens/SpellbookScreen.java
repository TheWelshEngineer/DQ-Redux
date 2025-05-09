package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Effect;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Spell;
import RogueLike.Main.Spellbook;
import RogueLike.Main.Creatures.Creature;

public class SpellbookScreen implements Screen{
	
	protected Creature player;
	protected List<Effect> effects;
	protected Spellbook spellbook;
	protected boolean casting;
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public int spellsPerPage = 30;
	public int pageNumber = 1;
	
	public SpellbookScreen(Creature player, boolean casting) {
		this.player = player;
		this.effects = player.effects();
		this.spellbook = player.spellbook();
		this.casting = casting;
	}
	
	public boolean checkIfSelected(int index, int check) {
		if(index == check) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void displayOutput() {
		//
		//
		ExtendedAsciiPanel.clear();
		ExtendedAsciiPanel.writeCenter("== Spells ==", 1);	
		int y = 3;
		//
		pageNumber = ExtraMaths.scrollingScreenPageNumber(check);
		//
		for(int i = (spellsPerPage*(pageNumber-1)); i < spellsPerPage*pageNumber; i++) {
			//System.out.println(String.format("i = %d", i));
			if(i < spellbook.getSpells().size()) {//spellbook.get(0).name()
				if(checkIfSelected(i, check)) {
					ExtendedAsciiPanel.writeCenter(String.format("> %s (%d mana) <", spellbook.get(i).name(), spellbook.get(i).manaCost(), check), y++);
				}else {
					ExtendedAsciiPanel.writeCenter(String.format("%s (%d mana)", spellbook.get(i).name(), spellbook.get(i).manaCost(), check), y++);
				}
				
			}
		}
		//

    
        ExtendedAsciiPanel.writeCenter("-- [UP / DOWN]: Move Selection | [ENTER] Select Spell | [ESCAPE]: Cancel --", 38);
		
	}
	
    protected Screen use(Spell spell){
    	if(spell.isSelfCast()) {
    		//player.doAction(new TerminalText("check"));
    		player.castSpell(spell, player.x(), player.y(), null);
    		return null;
    	}else {
    		//return null;
    		return new SpellTargetingScreen(player, String.format("Cast %s at?", spell.name()), spell, null);
    	}
        //return new CastSpellScreen(player, "Cast spell at?", sx, sy, spell, item);
    }

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = spellbook.getSpells().size()-1;
			}else {
				check--;
			}
			return this;
		case KeyEvent.VK_DOWN:
			if(check == spellbook.getSpells().size()-1) {
				check = 0;
			}else {
				check++;
			}
			return this;
		case KeyEvent.VK_ESCAPE:
			return null;
		case KeyEvent.VK_ENTER:
			if(spellbook.getSpells().size() > 0) {
				return use(spellbook.get(check));
			}
			return null;
		default: return this;
		}
	}

}

