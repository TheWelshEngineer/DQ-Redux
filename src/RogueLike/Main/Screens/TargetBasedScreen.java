package RogueLike.Main.Screens;

import java.awt.*;
import java.awt.event.KeyEvent;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Utils.PointShapes.Line;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Managers.KeybindManager;

public abstract class TargetBasedScreen implements Screen{
	
	protected Creature player;
	protected String caption;
	/** world-space x-coordinate of selected point */
	protected int wx;
	/** world-space y-coordinate of selected point */
	protected int wy;
	
	public TargetBasedScreen(Creature player, String caption) {
		this.player = player;
		this.caption = caption;
		this.wx = player.x();
		this.wy = player.y();
	}
	
	public void displayOutput(ExtendedAsciiPanel terminal) {
		// Draw the line
		for (Point p: new Line(player.x(), player.y(), wx, wy)) {
			// Select colour based on location
			Color drawColor;
			if (p.x == player.x && p.y == player.y) {
				// don't draw over the player
				continue;
			} else if (p.x == wx && p.y == wy) {
				// endpoint gets bright red
				drawColor = ExtendedAsciiPanel.brightRed;
			} else {
				// points along the line get bright cyan
				drawColor = ExtendedAsciiPanel.brightCyan;
			}

			// Select glyph based on what's at the selected location
			Creature creature = player.creature(wx, wy, player.z());
			Item item = player.item(wx, wy, player.z());
			char glyph;
			if (creature != null) {
				glyph = creature.glyph();
			}
			else if (item != null) {
				glyph = item.glyph();
			}
			else {
				glyph = (char)15; //6-point burst icon
			}

			// World-space to screen-space corrections
			int dx = player.gameplayScreen().getScrollX();
			int dy = player.gameplayScreen().getScrollY();

			// Actually draw!
			terminal.write(glyph, p.x - dx, p.y - dy, drawColor);
		}

		terminal.clear(' ', 0, 24, 80, 1);
		terminal.write(caption, 2, 24);
	}
	
	public Screen respondToUserInput(KeyEvent key) {
		// Save previous position in case we can't move to the new tile
		int prevX = wx;
		int prevY = wy;
		// World-space to screen-space corrections
		int dx = player.gameplayScreen().getScrollX();
		int dy = player.gameplayScreen().getScrollY();

		switch (key.getKeyCode()){
			case KeybindManager.movementWest: wx--; break;
			case KeybindManager.movementEast: wx++; break;
			case KeybindManager.movementNorth: wy--; break;
			case KeybindManager.movementSouth: wy++; break;
			case KeybindManager.movementNorthWest: wx--; wy--; break;
			case KeybindManager.movementNorthEast: wx++; wy--; break;
			case KeybindManager.movementSouthWest: wx--; wy++; break;
			case KeybindManager.movementSouthEast: wx++; wy++; break;
			case KeybindManager.navigateMenuConfirm: selectWorldCoordinate(wx, wy, wx-dx, wy-dy); return null;
			case KeybindManager.navigateMenuBack: return null;
        }
		
		if(!isAcceptable(wx, wy) || !player.world().isInBounds(wx, wy)) {
			wx = prevX;
			wy = prevY;
		}
		
		enterWorldCoordinate(wx, wy, wx-dx, wy-dy);
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
