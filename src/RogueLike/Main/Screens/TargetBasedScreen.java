package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.AoE.Line;
import RogueLike.Main.AoE.Point;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

public abstract class TargetBasedScreen implements Screen{
	
	protected Creature player;
	protected String caption;
	private int sx;
	private int sy;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int fx;
	private int fy;
	
	public TargetBasedScreen(Creature player, String caption, int sx, int sy) {
		this.player = player;
		this.caption = caption;
		this.sx = sx;
		this.sy = sy;
	}
	
	public void displayOutput(ExtendedAsciiPanel terminal) {
		for(Point p : new Line(sx, sy, sx+x, sy+y)) {
			//if(p.x < 0 || p.x >= 80 || p.y < 0 || p.y >= 24) {
				//continue;
			//}
			dx = p.x;
			dy = p.y;
			fx = player.gameplayScreen().getScrollX();
			fy = player.gameplayScreen().getScrollY();
			
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
			if(dx < 0) {
				dx = 0;
			}
			if(dx >= player.world().width()) {
				dx = player.world().width();
			}
			if(dy < 0) {
				dy = 0;
			}
			if(dy >= player.world().height()) {
				dy = player.world().height();
			}
			//terminal.write((char)177, p.x, p.y, AsciiPanel.brightCyan);
			//temp
			//
			if(p.x == sx+x && p.y == sy+y) {
				if(player.creature(player.x()+x, player.y()+y, player.z) != null) {
					char c = player.creature(player.x()+x, player.y()+y, player.z).glyph();
					terminal.write(c, p.x, p.y, ExtendedAsciiPanel.brightRed);
				}else if(player.item(player.x()+x, player.y()+y, player.z) != null && !player.item(player.x()+x, player.y()+y, player.z).isTrap()) {
					char c = player.item(player.x()+x, player.y()+y, player.z).glyph();
					terminal.write(c, p.x, p.y, ExtendedAsciiPanel.brightRed);
				}else{
					char c = (char)15;
					terminal.write(c, p.x, p.y, ExtendedAsciiPanel.brightRed);
				}
			}else if(p.x == sx && p.y == sy){
				terminal.write(player.glyph(), p.x, p.y, ExtendedAsciiPanel.brightWhite);
			}else {
				//System.out.println(dx + " " + (dy));
				if(player.creature(dx+fx, dy+fy, player.z) != null) {
					char c = player.creature(dx+fx, dy+fy, player.z).glyph();
					terminal.write(c, p.x, p.y, ExtendedAsciiPanel.brightCyan);
				}else if(player.item(dx+fx, dy+fy, player.z) != null && !player.item(dx+fx, dy+fy, player.z).isTrap()) {
					char c = player.item(dx+fx, dy+fy, player.z).glyph();
					terminal.write(c, p.x, p.y, ExtendedAsciiPanel.brightCyan);
				}else{
					char c = (char)15;
					terminal.write(c, p.x, p.y, ExtendedAsciiPanel.brightCyan);
				}
				
			}
			
		}
		terminal.clear(' ', 0, 24, 80, 1);
		terminal.write(caption, 2, 24);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		int px = x;
		int py = y;
		
		switch (key.getKeyCode()){
        case KeybindManager.movementWest: x--; dx--; break;
        case KeybindManager.movementEast: x++; dx++; break;
        case KeybindManager.movementNorth: y--; dy--; break;
        case KeybindManager.movementSouth: y++; dy++; break;
        case KeybindManager.movementNorthWest: x--; y--; dx--; dy--; break;
        case KeybindManager.movementNorthEast: x++; y--; dx++; dy--; break;
        case KeybindManager.movementSouthWest: x--; y++; dx--; dy++; break;
        case KeybindManager.movementSouthEast: x++; y++; dx++; dy++; break;
        case KeybindManager.navigateMenuConfirm: selectWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y); return null;
        case KeybindManager.navigateMenuBack: return null;
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
