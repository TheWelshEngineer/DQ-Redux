package RogueLike.Main.Screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.World;
import RogueLike.Main.AoE.AoE;
import RogueLike.Main.AoE.SinglePointAoE;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Utils.PointShapes.Line;
import RogueLike.Main.Utils.PointShapes.Point;

public abstract class TargetBasedScreen implements Screen{
	
	protected Creature player;
	protected String caption;
	/** World-space x-coordinate of selected point */
	protected int wx;
	/** World-space y-coordinate of selected point */
	protected int wy;

	protected AoE highlightArea;
	
	public TargetBasedScreen(Creature player, String caption, AoE highlightArea) {
		this.player = player;
		this.caption = caption;
		this.wx = player.x();
		this.wy = player.y();
		this.highlightArea = Objects.requireNonNullElseGet(highlightArea, SinglePointAoE::new);
	}

	public TargetBasedScreen(Creature player, String caption) {
		this(player, caption, null);
	}
	
	public void displayOutput() {
		// Draw the highlighted area
		Set<Point> linePoints = Set.copyOf(new Line(player.x(), player.y(), wx, wy).points());
		Set<Point> aoePoints = Set.copyOf(highlightArea.instantiate(player.location(), new Point(wx, wy, player.z())).points());

		Set<Point> highlightedPoints = new HashSet<>();
		highlightedPoints.addAll(linePoints);
		highlightedPoints.addAll(aoePoints);

		for (Point p: highlightedPoints) {
			// Select colour based on location
			Color drawColor;
			if (p.x == player.x && p.y == player.y) {
				// don't draw over the player
				continue;
			} else if (p.x == wx && p.y == wy) {
				// endpoint gets bright red
				drawColor = ExtendedAsciiPanel.brightRed;
			} else if (linePoints.contains(p) && aoePoints.contains(p)) {
				// points in the AOE _and_ on the line get bright cyan
				drawColor = ExtendedAsciiPanel.brightGreen;
			} else if (linePoints.contains(p)) {
				// points along the line but not in the AoE get a less bright blue
				drawColor = ExtendedAsciiPanel.cyan;
			} else {
				// points in the highlighted area but not the endpoint or along the line get bright green
				drawColor = ExtendedAsciiPanel.apple;
			}

			// Select glyph based on what's at the selected location
			Creature creature = player.creature(p.x, p.y, player.z());
			Item item = player.item(p.x, p.y, player.z());
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
			ExtendedAsciiPanel.write(glyph, p.x - dx, p.y - dy, drawColor);
		}

		ExtendedAsciiPanel.clear(' ', 0, 24, 80, 1);
		ExtendedAsciiPanel.write(caption, 2, 24);
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
		
		if(!isAcceptable(wx, wy) || !World.isInBounds(wx, wy)) {
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
