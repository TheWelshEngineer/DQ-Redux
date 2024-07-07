package RogueLike.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import RogueLike.Main.AoE.Point;
import RogueLike.Main.Worldgen.Blueprint;
import RogueLike.Main.Worldgen.Blueprints.MerchantFloor;

public class WorldBuilder {
	private int width;
	private int height;
	private int depth;
	private Tile[][][] tiles;
	private int[][][] regions;
	private int nextRegion;
	private final List<Integer> specialDepths = new ArrayList<>();
	private final List<Blueprint> blueprints = new ArrayList<>();
	
	public WorldBuilder(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.tiles = new Tile[width][height][depth];
		this.regions = new int[width][height][depth];
		this.nextRegion = 1;
	}

	public int width() {return width;}
	public int height() {return height;}
	public int depth() {return depth;}
	public void markDepthAsSpecial(int depth) {specialDepths.add(depth);}

	public World build() {
		return new World(tiles, specialDepths);
	}

	private void addBlueprint(Blueprint blueprint) {
		blueprints.add(blueprint);
		blueprint.onAdd();
	}

	private WorldBuilder randomiseTiles() {
		System.out.println("Randomising inital generation");
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				for(int z = 0; z < depth; z++) {
					tiles[x][y][z] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
					
				}
				
			}
		}
		System.out.println("Initial generation randomised");
		return this;
	}
	
	private WorldBuilder smooth(int times) {
		System.out.println("Smoothing generation");
		Tile[][][] tiles2 = new Tile[width][height][depth];
		for (int time = 0; time < times; time++) {

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					for (int z = 0; z < depth; z++) {
						int floors = 0;
						int rocks = 0;
						for (int ox = -1; ox < 2; ox++) {
							for (int oy = -1; oy < 2; oy++) {
								if (x + ox < 0 || x + ox >= width || y + oy < 0 || y + oy >= height) {
									continue;
								}
								if (tiles[x + ox][y + oy][z] == Tile.FLOOR) {
									floors++;
								}
								else {
									rocks++;
								}	
							}
						}
						tiles2[x][y][z] = floors >= rocks ? Tile.FLOOR : Tile.WALL;
					}
				}
			}
			tiles = tiles2;
		}
		System.out.println("Generation smoothed");
		return this;
	}
	
	private WorldBuilder createRegions(){
		System.out.println("Creating regions");
		regions = new int[width][height][depth];
		for (int z = 0; z < depth; z++){
			for (int x = 0; x < width; x++){
				for (int y = 0; y < height; y++){
					if (tiles[x][y][z] != Tile.WALL && regions[x][y][z] == 0){
						int size = fillRegion(nextRegion++, x, y, z);
						if (size < 25)
							removeRegion(nextRegion - 1, z);
					}
				}
			}
		}
		System.out.println("Regions created");
		return this;
	}
	
	private void removeRegion(int region, int z) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(regions[x][y][z] == region) {
					regions[x][y][z] = 0;
					tiles[x][y][z] = Tile.WALL;
				}
			}
		}
	}
	
	private int fillRegion(int region, int x, int y, int z) {
		int size = 1;
		ArrayList<Point> open = new ArrayList<Point>();
		open.add(new Point(x,y,z));
		regions[x][y][z] = region;
		
		while (!open.isEmpty()){
			Point p = open.remove(0);

			for (Point neighbor : p.neighbors8()){
				if (neighbor.x < 0 || neighbor.y < 0 || neighbor.x >= width || neighbor.y >= height)
					continue;
				
				if (regions[neighbor.x][neighbor.y][neighbor.z] > 0
						|| tiles[neighbor.x][neighbor.y][neighbor.z] == Tile.WALL)
					continue;

				size++;
				regions[neighbor.x][neighbor.y][neighbor.z] = region;
				open.add(neighbor);
			}
		}
		return size;
	}
	
	public WorldBuilder connectRegions(){
		System.out.println("Connecting regions");
        for (int z = 0; z < depth-1; z++){
			if (!specialDepths.contains(z) && !specialDepths.contains(z+1)) {
				connectRegionsDown(z);
			}
        }
        return this;
    }
	
	private void connectRegionsDown(int z) {
		List<String> connected = new ArrayList<String>();
		System.out.println("Beginning to connect regions");
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				String region = regions[x][y][z] + "," + regions[x][y][z+1];
				if(tiles[x][y][z] == Tile.FLOOR && tiles[x][y][z+1] == Tile.FLOOR && !connected.contains(region)) {
					connected.add(region);
					connectRegionsDown(z, regions[x][y][z], regions[x][y][z+1]);
				}
			}
		}
		System.out.println("Regions connected");
	}
	
	private void connectRegionsDown(int z, int r1, int r2) {
		List<Point> candidates = findRegionOverlaps(z, r1, r2);
		
		int stairs = 0;
		
		do {
			Point p = candidates.remove(0);
			tiles[p.x][p.y][z] = Tile.STAIRS_DOWN;
			tiles[p.x][p.y][z+1] = Tile.STAIRS_UP;
			stairs++;
		}
		while(candidates.size() / stairs > 500);
		
	}
	
	public List<Point> findRegionOverlaps(int z, int r1, int r2){
		ArrayList<Point> candidates = new ArrayList<Point>();
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(tiles[x][y][z] == Tile.FLOOR && tiles[x][y][z+1] == Tile.FLOOR && regions[x][y][z] == r1 && regions[x][y][z+1] == r2) {
					candidates.add(new Point(x,y,z));
				}
			}
		}
		Collections.shuffle(candidates);
		return candidates;
	}
	
	private WorldBuilder addExitStairs() {
		System.out.println("Adding exit stairs");
		int x = -1;
		int y = -1;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}while(tiles[x][y][0] != Tile.FLOOR);
													
		tiles[x][y][0] = Tile.STAIRS_EXIT;
		System.out.println("Exit stairs added");
		return this;
	}

	/***
	 * Set tiles in a circle of radius r around a given center point.
	 * @param tile The tile type to fill the circle with.
	 * @param x The x-coordinate of the circle's center.
	 * @param y The x-coordinate of the circle's center.
	 * @param r The radius of the circle.
	 * @param depth The depth at which to generate the circle.
	 */
	public void setTilesCircle(Tile tile, int x, int y, int r, int depth) {
		for (int dx=-r; dx<=r; dx++) {
			for (int dy=-r; dy<=r; dy++) {
				if (isInBounds(x+dx, y+dy) && dx*dx + dy*dy <= r*r) {
					tiles[x+dx][y+dy][depth] = tile;
				}
			}
		}
	}

	private boolean isInBounds(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}
	
	private WorldBuilder addCustomDepthGeneration(int depth, Tile[][] blueprint) {
		System.out.println("Applying merchant blueprint");
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				tiles[x][y][depth] = blueprint[x][y];
			}
		}
		specialDepths.add(depth);
		System.out.println(String.format("Merchant blueprint applied to depth: %d", depth));
		return this;
	}

	private WorldBuilder addAllBlueprints() {
		addBlueprint(new MerchantFloor(this, 1));
		return this;
	}

	private WorldBuilder blueprintsGenerateTiles() {
		blueprints.forEach(Blueprint::onTileGeneration);
		return this;
	}

	private WorldBuilder blueprintsPostGenerateTiles() {
		blueprints.forEach(Blueprint::onPostTileGeneration);
		return this;
	}

	private WorldBuilder blueprintsPostRegionConnection() {
		blueprints.forEach(Blueprint::onPostRegionConnection);
		return this;
	}
	
	public WorldBuilder generateWorld() {
		System.out.println("Generating world");
		return addAllBlueprints()
				.randomiseTiles()
				.smooth(9)
				.blueprintsGenerateTiles()
				.blueprintsPostGenerateTiles()
				.createRegions()
				.connectRegions()
				.blueprintsPostRegionConnection()
				.addExitStairs();
				
	}


	public void setTile(int x, int y, int z, Tile tile) {
		tiles[x][y][z] = tile;
	}
	public Tile getTile(int x, int y, int z) {
		return tiles[x][y][z];
	}
}
