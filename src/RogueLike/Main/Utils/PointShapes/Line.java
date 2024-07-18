package RogueLike.Main.Utils.PointShapes;

import RogueLike.Main.AoE.Point;

import java.util.ArrayList;
import java.util.List;

public class Line extends PointShape{
	public final int x0;
	public final int y0;
	public final int x1;
	public final int y1;

	public Line(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	public List<Point> generatePoints(){
		List<Point> points = new ArrayList<>();

		int dx = Math.abs(x1-x0);
		int dy = Math.abs(y1-y0);
		
		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;
		
		int err = dx-dy;

		int x = x0;
		int y = y0;
		
		while(true) {
			points.add(new Point(x, y, 0));
			
			if(x == x1 && y == y1) {
				break;
			}
			
			int e2 = err*2;
			//potential error, exchange -dx for -dy if true
			if(e2 > -dy) {
				err -= dy;
				x += sx;
			}
			
			if(e2 < dx) {
				err += dx;
				y += sy;
			}
		}

		return points;
	}
}
