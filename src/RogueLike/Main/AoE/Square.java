package RogueLike.Main.AoE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import RogueLike.Main.Creatures.Creature;

public class Square extends AoE implements Iterable<Point>{
	private List<Point> points;
	public List<Point> getPoints(){
		return points;
	}

	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}
	
	public Square(int x, int y, int z, int radius) {
		points = new ArrayList<Point>();
		size = radius;
		sizeWord = "Square";
		for(int dy = -radius; dy < radius+1; dy++) {
			for(int dx = -radius; dx < radius+1; dx++) {
				points.add(new Point(x+dx, y+dy, z));
			}
		}
	}
	
	public Square(int radius) {
		size = radius;
		sizeWord = "Square";
	}
	
	public ArrayList<Creature> affectedCreatures(Creature reference){
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		for(Point p : points) {
			if(reference.creature(p.x, p.y, p.z) != null) {
				creatures.add(reference.creature(p.x, p.y, p.z));
			}
		}
		return creatures;
	}
	
	public ArrayList<Creature> affectedCreaturesExceptCenter(Creature reference){
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		for(Point p : points) {
			if(reference.creature(p.x, p.y, p.z) != null && reference.creature(p.x, p.y, p.z) != reference) {
				creatures.add(reference.creature(p.x, p.y, p.z));
			}
		}
		return creatures;
	}

}
