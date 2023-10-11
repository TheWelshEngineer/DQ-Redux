package RogueLike.Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import RogueLike.Main.Items.Item;

public class World {
	private Tile[][][] tiles;
	//
	private Tile[][][] subtiles;
	//
	private Tile[][][] gastiles;
	//
	private Item[][][] items;
	public List<Creature> creatures;
	
	private int width;
	public int width() {
		return width;
	}
	
	private int height;
	public int height() {
		return height;
	}
	
	private int depth;
	public int depth() {
		return depth;
	}
	
	public World(Tile[][][] tiles2) {
		this.tiles = tiles2;
		this.width = tiles2.length;
		this.height = tiles2[0].length;
		this.depth = tiles[0][0].length;
		this.creatures = new ArrayList<Creature>();
		this.items = new Item[width][height][depth];
		//
		this.subtiles = new Tile[width][height][depth];
		//
		this.gastiles = new Tile[width][height][depth];
		
	}
	
	public Tile tile(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return tiles[x][y][z];
		
	}
	
	public Tile subtile(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return subtiles[x][y][z];
		
	}
	
	public Tile gastile(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return gastiles[x][y][z];
		
	}
	
	public Item item(int x, int y, int z) {
		return items[x][y][z];
		
	}
	
	public char glyph(int x, int y, int z) {
		Creature creature = creature(x,y,z);
		if(creature != null) {
			return creature.glyph();
		}
		if(item(x,y,z) != null) {
			return item(x,y,z).glyph();
		}
		if(subtile(x,y,z) != null) {
			return subtile(x,y,z).glyph();
		}
		//
		if(gastile(x,y,z) != null && tile(x,y,z).displayGas()) {
			return gastile(x,y,z).glyph();
		}
		//
		return tile(x,y,z).glyph();
	}
	
	public Color color(int x, int y, int z) {
		Creature creature = creature(x,y,z);
		//
		if(gastile(x,y,z) != null) {
			return gastile(x,y,z).color();
		}
		//
		if(creature != null) {
			return creature.color();
		}
		if(item(x,y,z) != null) {
			return item(x,y,z).color();
		}
		if(subtile(x,y,z) != null) {
			return subtile(x,y,z).color();
		}
		return tile(x,y,z).color();
	}
	
	public void dig(int x, int y, int z) {
		if(tile(x,y,z).isDiggable())
			tiles[x][y][z] = Tile.FLOOR;
	}
	
	public void changeTile(int x, int y, int z, Tile tile) {
		if(y < 0) {
			y = 0;
		}
		if(x < 0) {
			x = 0;
		}
		if(y > height) {
			y = height;
		}
		if(x > width) {
			x = width;
		}
		try {
			tiles[x][y][z] = tile;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void changeSubTile(int x, int y, int z, Tile tile) {
		if(y < 0) {
			y = 0;
		}
		if(x < 0) {
			x = 0;
		}
		if(y > height) {
			y = height;
		}
		if(x > width) {
			x = width;
		}
		try {
			if(tile != null) {
				subtiles[x][y][z] = tile;
				if(tile == Tile.FIRE && gastiles[x][y][z].isGas()) {
					gastiles[x][y][z] = null;
				}
			}else {
				subtiles[x][y][z] = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void changeGasTile(int x, int y, int z, Tile tile) {
		if(y < 0) {
			y = 0;
		}
		if(x < 0) {
			x = 0;
		}
		if(y > height) {
			y = height;
		}
		if(x > width) {
			x = width;
		}
		try {
			if(tile != null) {
				if(gastiles[x][y][z] != null && gastiles[x][y][z] != tile && tile.isGas()) {
					gastiles[x][y][z] = Tile.MIXED_GAS;
				}else {
					gastiles[x][y][z] = tile;
				}
			}else {
				gastiles[x][y][z] = null;
			}
			
			//gastiles[x][y][z] = tile;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public Creature creature(int x, int y, int z){
		for (Creature c : creatures){
			if (c.x == x && c.y == y && c.z == z)
				return c;
		}
		return null;
	}
	
	public void addAtEmptyLocation(Creature creature, int z){
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		//|| !tile(x,y,z).isStairs() BUGGED
		while (tile(x,y,z).noItems() || tile(x,y,z).isStairs() || creature(x,y,z) != null || tile(x,y,z).isPit() || tile(x,y,z).isBars());
		
		creature.x = x;
		creature.y = y;
		creature.z = z;
		creatures.add(creature);
	}
	
	public void addAtSpawnLocation(Creature creature, int z){
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		//|| !tile(x,y,z).isStairs() BUGGED
		while (!tile(x,y,z).isStairsExit());
		
		creature.x = x;
		creature.y = y;
		creature.z = z;
		creatures.add(creature);
	}
	
	public void add(Creature pet) {
		//temp
		if(creature(pet.x, pet.y, pet.z) == null) {
			creatures.add(pet);
		}else {
			
		}
		//creatures.add(pet);
	}
	
	public void addAtEmptyLocation(Item item, int depth){
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		//!tile(x,y,depth).isGround()
		while (tile(x,y,depth).noItems() || creature(x,y,depth) != null || item(x,y,depth) != null);
		
		items[x][y][depth] = item;
	}
	
	public void addAtGivenLocation(Creature creature, int x, int y, int z){
		if(tile(x,y,z).isBars() || tile(x,y,z).isStairs() || creature(x,y,z) != null || tile(x,y,z).isWall()) {
			
		}else {
			creature.x = x;
			creature.y = y;
			creature.z = z;
			creatures.add(creature);
		}
		
		
	}
	
	public void replaceCreature(Creature target, Creature replacement) {
		int rx = target.x();
		int ry = target.y();
		int rz = target.z();
		creatures.remove(target);
		replacement.x = rx;
		replacement.y = ry;
		replacement.z = rz;
		creatures.add(replacement);
	}
	
	public boolean addAtEmptySpace(Item item, int x, int y, int z){
		if (item == null)
			return true;
		
		List<Point> points = new ArrayList<Point>();
		List<Point> checked = new ArrayList<Point>();
		
		points.add(new Point(x, y, z));
		
		while (!points.isEmpty()){
			Point p = points.remove(0);
			checked.add(p);
			
			if (!tile(p.x, p.y, p.z).isGround())
				continue;
			//if (tile(p.x, p.y, p.z).isStairs())
				//continue;
			//if (tile(p.x, p.y, p.z).isGrass())
				//continue;
				
			if (items[p.x][p.y][p.z] == null && !tile(p.x, p.y, p.z).isStairs() && !tile(p.x, p.y, p.z).noItems()){
				items[p.x][p.y][p.z] = item;
				Creature c = this.creature(p.x, p.y, p.z);
				if (c != null)
					c.notify("A %s lands between your feet.", c.nameOf(item));
				return true;
			} else {
				List<Point> neighbors = p.neighbors8();
				neighbors.removeAll(checked);
				points.addAll(neighbors);
			}
		}
		return false;
	}
	
	public void remove(int x, int y, int z) {
		items[x][y][z] = null;
	}
	
	public void remove(Item item) {
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                for (int z = 0; z < depth; z++){
                	if (items[x][y][z] == item) {
                		items[x][y][z] = null;
                		return;
                	}
                }
            }
        }
	}
	
	public void remove(Creature other) {
		creatures.remove(other);
	}
	
	public void update() {
		List<Creature> toUpdate = new ArrayList<Creature>(creatures);
	    for (Creature creature : toUpdate){
	        creature.update();
	    }
	    //
	    for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                for (int z = 0; z < depth; z++){
                	if (tiles[x][y][z].isDecaying() && ExtraMaths.d10() > 9) {
                		changeTile(x,y,z,Tile.FLOOR);
                	}
                	if (subtiles[x][y][z].isDecaying() && ExtraMaths.d10() > 9) {
                		changeSubTile(x,y,z,null);
                	}
                	if (gastiles[x][y][z].isDecaying() && ExtraMaths.d10() > 9) {
                		changeGasTile(x,y,z,null);
                	}
                }
            }
        }
	    //
	}
	
	public void updateOnCurrentFloor(Creature player) {
		List<Creature> toUpdate = new ArrayList<Creature>(creatures);
	    for (Creature creature : toUpdate){
	    	if(creature.z() == player.z()) {
	    		creature.update();
	    	}
	        
	    }
	    //
	    for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                	if (tiles[x][y][player.z()].isDecaying() && ExtraMaths.d10() > 9) {
                		changeTile(x,y,player.z(),Tile.FLOOR);
                	}
            }
        }
	    
	    for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                	if (subtiles[x][y][player.z()] != null && subtiles[x][y][player.z()].isDecaying() && ExtraMaths.d10() > 9) {
                		changeSubTile(x,y,player.z(),null);
                	}
            }
        }
	    
	    for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                	if (gastiles[x][y][player.z()] != null && gastiles[x][y][player.z()].isDecaying() && ExtraMaths.d10() > 9) {
                		changeGasTile(x,y,player.z(),null);
                	}
            }
        }
	    //
	}
	

}
