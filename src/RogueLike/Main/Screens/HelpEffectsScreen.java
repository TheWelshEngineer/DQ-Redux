package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;

public class HelpEffectsScreen implements Screen{
	
	private boolean fromMenu;
	
	public HelpEffectsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput(ExtendedAsciiPanel terminal) {
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Help: Status Effects ==", 1);
		
		int y = 3;
		int x = 27;
		terminal.writeCenter("-- Positive Effects --", y++);
		y++;
        terminal.write("Giant Strength: Temporarily increases Strength and Armour Class.", x, y++);
        terminal.write("Mind Vision: Temporarily allows you to see creatures through walls.", x, y++);
        terminal.write("Levitating: Temporarily lets you float over ground hazards and see over tall grass.", x, y++);
        terminal.write("Chill Ward: Applies Frozen to nearby enemies and temporarily grants frost", x, y++);
        terminal.write("resistance, as well as improving your saving throws vs. frost effects.", x+12, y++);
        terminal.write("Magma Ward: Applies Ignited to nearby enemies and temporarily grants fire", x, y++);
        terminal.write("resistance, as well as improving your saving throws vs. fire effects.", x+12, y++);
        terminal.write("Arc Ward: Deals shock damage to nearby enemies and temporarily grants shock", x, y++);
        terminal.write("resistance, as well as improving your saving throws vs. shock effects.", x+10, y++);
        terminal.write("Invisible: Temporarily makes you harder to see and attack.", x, y++);
        y++;
        terminal.writeCenter("-- Negative Effects --", y++);
        y++;
        terminal.write("Poisoned: Deals poison damage over time.", x, y++);
        terminal.write("Corroded: Deals acid damage over time and temporarily reduces Armour Class.", x, y++);
        terminal.write("Blinded: Temporarily reduces your range of vision.", x, y++);
        terminal.write("Devoured: Deals chaos damage over time, and drains mana over time.", x, y++);
        terminal.write("Confused: Temporarily makes you move erratically.", x, y++);
        terminal.write("Paralyzed: Temporarily makes you unable to move.", x, y++);
        terminal.write("Frozen: Deals frost damage, then temporarily makes you unable to move.", x, y++);
        terminal.write("Electrified: Deals shock damage, then drains mana over time.", x, y++);
        terminal.write("Ignited: Deals fire damage over time.", x, y++);

    
        terminal.writeCenter("-- [ESCAPE]: Back --", 38);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
