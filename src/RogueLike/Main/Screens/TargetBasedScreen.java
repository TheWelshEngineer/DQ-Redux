package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;

import RogueLike.Main.Creature;
import RogueLike.Main.ExtraColors;
import RogueLike.Main.Line;
import RogueLike.Main.Point;
import asciiPanel.AsciiPanel;

public abstract class TargetBasedScreen implements Screen{
	
	protected Creature player;
	protected String caption;
	private int sx;
	private int sy;
	private int x;
	private int y;
	
	public TargetBasedScreen(Creature player, String caption, int sx, int sy) {
		this.player = player;
		this.caption = caption;
		this.sx = sx;
		this.sy = sy;
	}
	
	public void displayOutput(AsciiPanel terminal) {
		for(Point p : new Line(sx, sy, sx+x, sy+y)) {
			//if(p.x < 0 || p.x >= 80 || p.y < 0 || p.y >= 24) {
				//continue;
			//}
			if(p.x < 0) {
				p.x = 0;
				x++;
			}
			if(p.y < 0) {
				p.y = 0;
				y++;
			}
			
			if(p.x >= 120) {
				p.x = 120;
				x--;
			}
			if(p.y > 20) {
				p.y = 20; //48
				y--;
			}
			//terminal.write((char)177, p.x, p.y, AsciiPanel.brightCyan);
			//temp
			if(p.x == sx+x && p.y == sy+y) {
				terminal.write((char)15, p.x, p.y, ExtraColors.brightRed);
			}else if(p.x == sx && p.y == sy){
				terminal.write('@', p.x, p.y, ExtraColors.brightWhite);
			}else {
				terminal.write((char)15, p.x, p.y, AsciiPanel.brightCyan);
			}
			
		}
		terminal.clear(' ', 0, 24, 80, 1);
		terminal.write(caption, 2, 24);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		int px = x;
		int py = y;
		
		switch (key.getKeyCode()){
        case KeyEvent.VK_NUMPAD4: x--; break;
        case KeyEvent.VK_NUMPAD6: x++; break;
        case KeyEvent.VK_NUMPAD8: y--; break;
        case KeyEvent.VK_NUMPAD2: y++; break;
        case KeyEvent.VK_NUMPAD7: x--; y--; break;
        case KeyEvent.VK_NUMPAD9: x++; y--; break;
        case KeyEvent.VK_NUMPAD1: x--; y++; break;
        case KeyEvent.VK_NUMPAD3: x++; y++; break;
        case KeyEvent.VK_ENTER: selectWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y); return null;
        case KeyEvent.VK_ESCAPE: return null;
        }
		
		if(!isAcceptable(player.x+x, player.y+y)) {
			x = px;
			y = py;
		}
		
		enterWorldCoordinate(player.x+x, player.y+y, sx+x, sy+y);
		return this;
		
	}
	
	public boolean isAcceptable(int x, int y) {
		return true;
	}
	
	public void enterWorldCoordinate(int x, int y, int screenX, int screenY) {
		
    }
	
	public void selectWorldCoordinate(int x, int y, int screenX, int screenY){
		
    }
}
