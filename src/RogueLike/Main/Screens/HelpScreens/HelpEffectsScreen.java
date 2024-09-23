package RogueLike.Main.Screens.HelpScreens;

import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.Screen;

public class HelpEffectsScreen implements Screen{
	
	private boolean fromMenu;
	
	private int checkX = 0;
	private int checkY = 0;
	
	public char borderVertical = 186;
	public char borderHorizontal = 205;
	public char borderCorner = 206;
	public char borderCornerNW = 201;
	public char borderCornerNE = 187;
	public char borderCornerSW = 200;
	public char borderCornerSE = 188;
	
	//Positive markers
	public char beastLeft = '>';
	public char beastRight = '<';
	public char electroLeft = '>';
	public char electroRight = '<';
	public char giantLeft = '>';
	public char giantRight = '<';
	public char hasteLeft = '>';
	public char hasteRight = '<';
	public char illuminatedLeft = '>';
	public char illuminatedRight = '<';
	public char invisibleLeft = '>';
	public char invisibleRight = '<';
	public char levitatingLeft = '>';
	public char levitatingRight = '<';
	public char mindLeft = '>';
	public char mindRight = '<';
	
	//Positive Wards
	public char bladeLeft = '>';
	public char bladeRight = '<';
	public char magmaLeft = '>';
	public char magmaRight = '<';
	public char chillLeft = '>';
	public char chillRight = '<';
	public char arcLeft = '>';
	public char arcRight = '<';
	public char venomLeft = '>';
	public char venomRight = '<';
	public char causticLeft = '>';
	public char causticRight = '<';
	public char arcaneLeft = '>';
	public char arcaneRight = '<';
	public char eldritchLeft = '>';
	public char eldritchRight = '<';
	
	//Negative markers
	public char bleedingLeft = '>';
	public char bleedingRight = '<';
	public char blindedLeft = '>';
	public char blindedRight = '<';
	public char confusedLeft = '>';
	public char confusedRight = '<';
	public char corrodedLeft = '>';
	public char corrodedRight = '<';
	public char devouredLeft = '>';
	public char devouredRight = '<';
	public char electrifiedLeft = '>';
	public char electrifiedRight = '<';
	public char frozenLeft = '>';
	public char frozenRight = '<';
	public char ignitedLeft = '>';
	public char ignitedRight = '<';
	public char paralysedLeft = '>';
	public char paralysedRight = '<';
	public char poisonedLeft = '>';
	public char poisonedRight = '<';
	public char sunderedLeft = '>';
	public char sunderedRight = '<';
	
	
	
	public HelpEffectsScreen(boolean fromMenu) {
		this.fromMenu = fromMenu;
	}
	
	public void displayOutput(ExtendedAsciiPanel terminal) {
		terminal.clear();
		Screen.generateBorders(terminal);
		terminal.writeCenter("== Help: Status Effects ==", 1);
		
		int y = 3;
		int x = 27;
		
		if(checkX == 0) {
			terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
			terminal.write(String.format("%c %c Beast Form %c     %c", borderVertical, beastLeft, beastRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Electrocharged %c %c", borderVertical, electroLeft, electroRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Giant Strength %c %c", borderVertical, giantLeft, giantRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Haste %c          %c", borderVertical, hasteLeft, hasteRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Illuminated %c    %c", borderVertical, illuminatedLeft, illuminatedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Invisible %c      %c", borderVertical, invisibleLeft, invisibleRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Levitating %c     %c", borderVertical, levitatingLeft, levitatingRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                    %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Mind Vision %c    %c", borderVertical, mindLeft, mindRight, borderVertical), 5, y++);
			terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		}else if(checkX == 1){
			terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
			terminal.write(String.format("%c %c Bleeding %c    %c", borderVertical, bleedingLeft, bleedingRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Blinded %c     %c", borderVertical, blindedLeft, blindedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Confused %c    %c", borderVertical, confusedLeft, confusedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Corroded %c    %c", borderVertical, corrodedLeft, corrodedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Devoured %c    %c", borderVertical, devouredLeft, devouredRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Electrified %c %c", borderVertical, electrifiedLeft, electrifiedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Frozen %c      %c", borderVertical, frozenLeft, frozenRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Ignited %c     %c", borderVertical, ignitedLeft, ignitedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Paralysed %c   %c", borderVertical, paralysedLeft, paralysedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Poisoned %c    %c", borderVertical, poisonedLeft, poisonedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                 %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Sundered %c    %c", borderVertical, sunderedLeft, sunderedRight, borderVertical), 5, y++);
			terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		}else if(checkX == 2) {
			terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerNW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerNE), 5, y++);
			terminal.write(String.format("%c %c Blade Ward %c    %c", borderVertical, bladeLeft, bladeRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Magma Ward %c    %c", borderVertical, magmaLeft, magmaRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Chill Ward %c    %c", borderVertical, chillLeft, chillRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Arc Ward %c      %c", borderVertical, arcLeft, arcRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Venomous Ward %c %c", borderVertical, venomLeft, venomRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Caustic Ward %c  %c", borderVertical, causticLeft, causticRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Arcane Ward %c   %c", borderVertical, arcaneLeft, arcaneRight, borderVertical), 5, y++);
			terminal.write(String.format("%c                   %c", borderVertical, borderVertical), 5, y++);
			terminal.write(String.format("%c %c Eldritch Ward %c %c", borderVertical, eldritchLeft, eldritchRight, borderVertical), 5, y++);
			terminal.write(String.format("%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c%c", borderCornerSW, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderHorizontal, borderCornerSE), 5, y++);
		}

        terminal.writeCenter(String.format("-- [%s / %s]: Move Selection --", KeybindManager.keybindText(KeybindManager.navigateMenuUp), KeybindManager.keybindText(KeybindManager.navigateMenuDown)), 34);
        if(checkX == 0) {
        	terminal.writeCenter(String.format("-- [%s]: Ward Effects | [%s]: Negative Effects --", KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 36);
        }else if(checkX == 1) {
        	terminal.writeCenter(String.format("-- [%s]: Positive Effects | [%s]: Ward Effects --", KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 36);
        }else if(checkX == 2) {
        	terminal.writeCenter(String.format("-- [%s]: Negative Effects | [%s]: Positive Effects --", KeybindManager.keybindText(KeybindManager.navigateMenuLeft), KeybindManager.keybindText(KeybindManager.navigateMenuRight)), 36);
        }
        terminal.writeCenter(String.format("-- [%s]: Back --", KeybindManager.keybindText(KeybindManager.navigateMenuBack)), 38);
        
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeybindManager.navigateMenuLeft:
			if(checkX == 0) {
				checkX = 2;
			}else {
				checkX--;
			}
			break;
			
		case KeybindManager.navigateMenuRight:
			if(checkX == 2) {
				checkX = 0;
			}else {
				checkX++;
			}
			break;
		
        case KeybindManager.navigateMenuBack: return new HelpScreen(fromMenu);
		}
		return this;
	}
	
}
