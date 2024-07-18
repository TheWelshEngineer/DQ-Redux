package RogueLike.Main.AoE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO: Refactor to use PointShapes.Line
public class Line extends AoE implements Iterable<Point>{
	private List<Point> points;
	public List<Point> getPoints(){
		return points;
	}
	
	public Line(int size) {
		this.size = size;
		sizeWord = "Line";
	}
	
	public Line(int x0, int y0, int x1, int y1) {
		points = new ArrayList<Point>();
		size = 0;
		sizeWord = "Line";

		int dx = Math.abs(x1-x0);
		int dy = Math.abs(y1-y0);

		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;

		int err = dx-dy;

		while(true) {
			points.add(new Point(x0, y0, 0));

			if(x0 == x1 && y0 == y1) {
				break;
			}

			int e2 = err*2;
			//potential error, exchange -dx for -dy if true
			if(e2 > -dy) {
				err -= dy;
				x0 += sx;
			}

			if(e2 < dx) {
				err += dx;
				y0 += sy;

			}

		}

	}
	
	@Override
	public Iterator<Point> iterator(){
		return points.iterator();
	}

}
