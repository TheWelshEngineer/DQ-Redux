/*package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.util.List;

import RogueLike.Main.Creature;
import RogueLike.Main.Effect;
import RogueLike.Main.ExtraMaths;
import RogueLike.Main.Featbook;
import asciiPanel.AsciiPanel;

public class FeatbookScreen implements Screen{
	
	protected Creature player;
	protected List<Effect> effects;
	protected Featbook featbook;
	protected boolean casting;
	
	public int check = 0;
	public void setCheck(int value) {
		check = value;
	}
	
	public int featsPerPage = 30;
	public int pageNumber = 1;
	
	public FeatbookScreen(Creature player, int sx, int sy, boolean casting) {
		this.player = player;
		this.effects = player.effects();
		this.featbook = player.featbook();
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
	public void displayOutput(AsciiPanel terminal) {
		//
		//
		terminal.clear();
		terminal.writeCenter("== Spells ==", 1);	
		int y = 3;
		//
		pageNumber = ExtraMaths.spellbookPageNumber(check);
		//
		for(int i = (featsPerPage*(pageNumber-1)); i < featsPerPage*pageNumber; i++) {
			//System.out.println(String.format("i = %d", i));
			if(i < featbook.getFeats().size()) {//spellbook.get(0).name()
				if(checkIfSelected(i, check)) {
					terminal.writeCenter(String.format("> %s <", featbook.get(i).name(), check), y++);
				}else {
					terminal.writeCenter(String.format("%s", featbook.get(i).name(), check), y++);
				}
				
			}
		}
		//

    
        terminal.writeCenter("-- [UP / DOWN]: Move Selection | [ESCAPE]: Cancel --", 38);
		
	}
	


	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(check == 0) {
				check = featbook.getFeats().size()-1;
			}else {
				check--;
			}
			return this;
		case KeyEvent.VK_DOWN:
			if(check == featbook.getFeats().size()-1) {
				check = 0;
			}else {
				check++;
			}
			return this;
		case KeyEvent.VK_ESCAPE:
			return null;
		default: return this;
		}
	}

}*/