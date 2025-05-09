package RogueLike.Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Creatures.Player;
import RogueLike.Main.Entities.Entity;
import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Items.Item;
import RogueLike.Main.Utils.NotificationHistory;
import RogueLike.Main.Utils.PlayerBuildDetails;
import RogueLike.Main.Utils.PointShapes.Point;

public enum World {
	
	INSTANCE;
	private static Tile[][][] tiles;
	//
	private static Tile[][][] subtiles;
	//
	private static Tile[][][] gastiles;
	//
	private static Item[][][] items;
	public static List<Creature> creatures;
	public static List<Entity> entities;
	//
	private transient static Particle[][][] particles;
	
	private static int width;
	public static int width() {
		return width;
	}
	
	private static int height;
	public static int height() {
		return height;
	}
	
	private static int depth;
	public static int depth() {
		return depth;
	}

	private static Player player;
	public static Player player() {
		if (player == null) {
			// defensive programming! we don't want things to pick up a dead reference to the player.
			// anything that needs a reference should only need to get one after Worldgen is complete
			// and the player has been spawned.
			throw new NullPointerException();
		}
		return player;
	}

	private static List<Integer> specialDepths;
	public List<Integer> specialDepths() {return specialDepths;}

	private static int turnNumber;
	public static int turnNumber() {return turnNumber; }

	private static final FactoryManager factory = FactoryManager.INSTANCE;
	public FactoryManager factory() {return factory;}
	
	private World() {};
	
	public static void setWorld(Tile[][][] tiles, List<Integer> specialDepths) {
		World.tiles = tiles;
		World.width = tiles.length;
		World.height = tiles[0].length;
		World.depth = tiles[0][0].length;
		World.creatures = new ArrayList<>();
		World.entities = new ArrayList<>();
		World.items = new Item[width][height][depth];
		//
		World.subtiles = new Tile[width][height][depth];
		//
		World.gastiles = new Tile[width][height][depth];
		//
		World.particles = new Particle[width][height][depth];
		World.specialDepths = specialDepths;
	}
	public static Tile tile(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return tiles[x][y][z];
		
	}
	
	public static Tile subtile(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return subtiles[x][y][z];
		
	}
	
	public static Tile gastile(int x, int y, int z) {
		if(x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return gastiles[x][y][z];
		
	}
	
	public static Item item(int x, int y, int z) {
		return items[x][y][z];
	}
	
	public static Particle particle(int x, int y, int z) {
		return particles[x][y][z];
	}
	
	public static char glyph(int x, int y, int z) {
		if(particle(x,y,z) != null) {
			return particle(x,y,z).glyph();
		}
		Creature creature = creature(x,y,z);
		if(creature != null) {
			return creature.glyph();
		}
		if(item(x,y,z) != null) {
			return item(x,y,z).glyph();
		}
		Entity entity = entity(x,y,z);
		if (entity != null) {
			return entity.glyph();
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
	
	public  static Color color(int x, int y, int z) {
		Creature creature = creature(x,y,z);
		//
		if(gastile(x,y,z) != null) {
			return gastile(x,y,z).color();
		}
		//
		if(particle(x,y,z) != null) {
			return particle(x,y,z).color();
		}
		if(creature != null) {
			return creature.color();
		}
		Entity entity = entity(x,y,z);
		if (entity != null) {
			return entity.color();
		}
		if(item(x,y,z) != null) {
			return item(x,y,z).color();
		}
		if(subtile(x,y,z) != null) {
			return subtile(x,y,z).color();
		}
		return tile(x,y,z).color();
	}
	
	public static void dig(int x, int y, int z) {
		if(tile(x,y,z).isDiggable())
			tiles[x][y][z] = Tile.FLOOR;
	}
	
	public static void changeTile(int x, int y, int z, Tile tile) {
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
			//e.printStackTrace();
		}
	}
	
	public static void changeSubTile(int x, int y, int z, Tile tile) {
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
			//e.printStackTrace();
		}
	}
	
	public static void changeGasTile(int x, int y, int z, Tile tile) {
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
			//e.printStackTrace();
		}
	}

	public static Creature creature(int x, int y, int z){
		for (Creature c : creatures){
			if (c.x == x && c.y == y && c.z == z)
				return c;
		}
		return null;
	}

	public static Entity entity(int x, int y, int z){
		for (Entity e : entities){
			if (e.x == x && e.y == y && e.z == z)
				return e;
		}
		return null;
	}

	public static void addAtEmptyLocation(Creature creature, int z){
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

	private static Point getPlayerSpawnPoint() {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (tiles[x][y][0].isStairsExit()) {
					return new Point(x, y, 0);
				}
			}
		}
		throw new IllegalStateException("Spawn point not found.");
	}

	public static void addPlayer(
		NotificationHistory notificationHandle,
		PlayerBuildDetails playerDetails
	) {
		if (player == null) {
			player = FactoryManager.getCreatureFactory().newPlayer(new FieldOfView(), notificationHandle, playerDetails);
		} 
		Point spawnpoint = getPlayerSpawnPoint();
		player.x = spawnpoint.x;
		player.y = spawnpoint.y;
		player.z = spawnpoint.z;

		System.out.println("Player spawned");
	}
	
	private static Point getMerchantSpawnPoint(int depth) {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				if (tiles[x][y][depth] == Tile.MERCHANT_FLOOR) {
					return new Point(x, y, depth);
				}
			}
		}
		throw new IllegalStateException("Spawn point not found.");
	}
	
	public static void addMerchant(Creature merchant, int depth){
		Point spawnpoint = getMerchantSpawnPoint(depth);
		merchant.x = spawnpoint.x;
		merchant.y = spawnpoint.y;
		merchant.z = spawnpoint.z;
		creatures.add(merchant);
		System.out.println("Merchant spawned");
	}
	
	public static void add(Creature pet) {
		//temp
		if(creature(pet.x, pet.y, pet.z) == null) {
			creatures.add(pet);
		}else {
			
		}
		//creatures.add(pet);
	}

	public static Point getEmptyLocationForTrap(int depth) {
		int x;
		int y;

		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}
		while (tile(x,y,depth).noItems() || tile(x,y,depth).isBars() || creature(x,y,depth) != null || item(x,y,depth) != null);

		return new Point(x, y, depth);
	}

	public static void addAtEmptyLocation(Item item, int depth){
		int x;
		int y;

		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}
		//!tile(x,y,depth).isGround()
		while (tile(x,y,depth).noItems() || tile(x,y,depth).isBars() || creature(x,y,depth) != null || item(x,y,depth) != null);
		
		items[x][y][depth] = item;
	}
	
	public static void addCreatureAtLocation(Creature creature, int x, int y, int z){
		if(tile(x,y,z).isBars() || tile(x,y,z).isStairs() || creature(x,y,z) != null || tile(x,y,z).isWall()) {
			
		}else {
			creature.x = x;
			creature.y = y;
			creature.z = z;
			creatures.add(creature);
		}
		
		
	}
	
	public static void replaceCreature(Creature target, Creature replacement) {
		int rx = target.x();
		int ry = target.y();
		int rz = target.z();
		creatures.remove(target);
		replacement.x = rx;
		replacement.y = ry;
		replacement.z = rz;
		creatures.add(replacement);
	}

	public static boolean addAtEmptySpace(Item item, int x, int y, int z){
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
				Creature c = World.creature(p.x, p.y, p.z);
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

	public static void add(Entity entity) {
		entities.add(entity);
	}
	
	public static void setParticleAtLocation(Particle particle, int x, int y, int z) {
		particles[x][y][z] = particle;
	}
	
	public static void removeParticleAtLocation(int x, int y, int z) {
		particles[x][y][z] = null;
	}
	
	public static void removeParticleByReference(Particle particle) {
		for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                for (int z = 0; z < depth; z++){
                	if (particles[x][y][z] == particle) {
                		particles[x][y][z] = null;
                		return;
                	}
                }
            }
        }
	}
	
	public static void remove(int x, int y, int z) {
		items[x][y][z] = null;
	}
	
	public static void remove(Item item) {
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

	public static void remove(Creature other) {
		creatures.remove(other);
	}

	public static void remove(Entity other) {
		entities.remove(other);
	}
	
	public static void update() {
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
	
	public static void generateActionsOnCurrentFloor(Creature player) {
		List<Creature> toUpdate = new ArrayList<Creature>(creatures);
	    for (Creature creature : toUpdate){
	    	if(creature.z() == player.z()) {
	    		creature.ai().selectAction();
	    	}
	        
	    }
	}
	
	public static void updateOnCurrentFloor(Creature player) {
		if(player.ai().actionQueue().isEmpty()) {
			// the player isn't doing anything - don't update
			return;
		}
		List<Creature> toUpdate = new ArrayList<Creature>(creatures);
		turnNumber++;
		System.out.printf(" ----- [TURN %d] ----- %n", turnNumber);
		Collections.sort(toUpdate, Comparator.comparing(Creature::getActionSpeed));
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
                if (subtiles[x][y][player.z()] != null && subtiles[x][y][player.z()].isDecaying() && ExtraMaths.d10() > 9) {
                	changeSubTile(x,y,player.z(),null);
                }
                if (gastiles[x][y][player.z()] != null && gastiles[x][y][player.z()].isDecaying() && ExtraMaths.d10() > 9) {
                	changeGasTile(x,y,player.z(),null);
                }
                if(particles[x][y][player.z()] != null) {
                	particles[x][y][player.z()].update();
                	if(particles[x][y][player.z()].isExpired()) {
                		removeParticleByReference(particles[x][y][player.z()]);
                	}
                }
            }
        }
	}


	public static boolean isInBounds(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	public static boolean isInBounds(Point p) {
		return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height && p.z >= 0 && p.z < depth;
	}
}
