package RogueLike.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import RogueLike.Main.Factories.FactoryManager;
import RogueLike.Main.Utils.NotificationHistory;
import RogueLike.Main.Utils.PlayerBuildDetails;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Worldgen.Blueprint;
import RogueLike.Main.Worldgen.Structure;
import RogueLike.Main.Worldgen.WorldGenerationException;
import RogueLike.Main.Worldgen.Blueprints.CaveFloor;
import RogueLike.Main.Worldgen.Blueprints.MerchantFloor;

public class WorldBuilder {
	private int width;
	private int height;
	private int depth;
	private Tile[][][] tiles;
	private int[][][] regions;
	/** Tiles that have a structure placed on them. */
	private boolean[][][] structureReservedTiles;
	private int nextRegion;
	private final List<Integer> specialDepths = new ArrayList<>();
	private final List<Blueprint> blueprints = new ArrayList<>();
	private final List<Structure> structures = new ArrayList<>();

	public WorldBuilder(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.tiles = new Tile[width][height][depth];
		this.regions = new int[width][height][depth];
		this.structureReservedTiles = new boolean[width][height][depth]; // defaults to filled with False
		this.nextRegion = 1;
	}

	public int width() {return width;}
	public int height() {return height;}
	public int depth() {return depth;}
	public void markDepthAsSpecial(int depth) {specialDepths.add(depth);}

	public void build(NotificationHistory playerNotifications, PlayerBuildDetails playerDetails) {
		World world = World.getInstance();
		world.setWorld(tiles, specialDepths);
		world.addPlayer(playerNotifications, playerDetails);
		// Structures do their generation before blueprints, as structures are intended to have more specific
		// generation, and we don't want that to be stepped on by the more generic generation from blueprints
		structures.forEach(s -> s.onBuildWorld());
		structures.forEach(s -> s.onBuildWorldLate());
		blueprints.forEach(bp -> bp.onBuildWorld());
		

		// TODO: move the victory item to a VictoryFloor blueprint? or make it drop from the final boss?
		FactoryManager.getItemFactory().newVictoryItem(world.depth()-1, 1);
	}

	private void addBlueprint(Blueprint blueprint) {
		blueprints.add(blueprint);
		blueprint.onAdd();
	}

	public void addStructure(Structure structure) {
		structures.add(structure);
		structure.reserveArea();
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
		List<Point> candidates = findRegionOverlaps(z, r1, r2)
			.stream().filter(p -> !isStructureTileReserved(p)) // filter out any points that are in a reserved area
			.collect(Collectors.toList());

		if (candidates.isEmpty()) {
			throw new WorldGenerationException(
				String.format("Failed to generate World - could not generate downwards stairs on depth %d", z)
			);
		}
		
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
		} while (
			tiles[x][y][0] != Tile.FLOOR // exit stairs must only be placed on the floor
			|| structureReservedTiles[x][y][0] // exit stairs must not be placed within a structure's reserved area
		);
													
		tiles[x][y][0] = Tile.STAIRS_EXIT;
		System.out.println("Exit stairs added");
		return this;
	}

	/**
	 * Change all bars tiles to the appropriate one, depending on where other adjacent bars tiles are.
	 */
	private WorldBuilder rectifyBars() {
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				for (int z=0; z<depth; z++) {
					if (tiles[x][y][z].isBars() && !tiles[x][y][z].isBarsDoor()) {
						boolean barsN = isInBounds(x, y+1) && tiles[x][y+1][z].isBars();
						boolean barsS = isInBounds(x, y-1) && tiles[x][y-1][z].isBars();
						boolean barsE = isInBounds(x-1, y) && tiles[x-1][y][z].isBars();
						boolean barsW = isInBounds(x+1, y) && tiles[x+1][y][z].isBars();

						if (barsN && barsS && !barsW && !barsE) {
							tiles[x][y][z] = Tile.BARS_VERTICAL;
						}
						else if (barsW && barsE && !barsN && !barsS) {
							tiles[x][y][z] = Tile.BARS_HORIZONTAL;
						}
						else if (barsN && barsE && !barsW && !barsS) {
							tiles[x][y][z] = Tile.BARS_NE;
						}
						else if (barsN && barsW && !barsE && !barsS) {
							tiles[x][y][z] = Tile.BARS_NW;
						}
						else if (barsS && barsE && !barsW && !barsN) {
							tiles[x][y][z] = Tile.BARS_SE;
						}
						else if (barsS && barsW && !barsE && !barsN) {
							tiles[x][y][z] = Tile.BARS_SW;
						}
						else {
							tiles[x][y][z] = Tile.BARS_CROSS;
						}
					}
				}
			}
		}
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

	public boolean isInBounds(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	public boolean isInBoundsMargin(int x, int y, int margin) {
		return isInBoundsMargin(x, y, margin, margin);
	}

	public boolean isInBoundsMargin(int x, int y, int marginX, int marginY) {
		return x >= marginX && x < width - marginX && y >= marginY && y < height - marginY;
	}

	public Optional<Point> pickRandomLocation(int depth, Function<Point, Boolean> isAcceptable) {
		int MAX_ATTEMPTS = 100;
		Random rng = new Random();
		for (int i = 0; i < MAX_ATTEMPTS; i++) {
			Point point = new Point(rng.nextInt(width), rng.nextInt(height), depth);
			if (isAcceptable.apply(point)) {
				return Optional.of(point);
			}
		}
		return Optional.empty();
	}

	private WorldBuilder addAllBlueprints() {
		for (int z=0; z<depth; z++) {
			switch(z) {
				case 5:
				case 10: addBlueprint(new MerchantFloor(this, z)); break;
				default: addBlueprint(new CaveFloor(this, z)); break;
			}
		}
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

	private WorldBuilder buildStructures() {
		structures.forEach(Structure::onBuildStructures);
		return this;
	}
	
	public WorldBuilder generateWorld() {
		System.out.println("Generating World");
		return addAllBlueprints()
				.blueprintsGenerateTiles()
				.blueprintsPostGenerateTiles()
				.buildStructures()
				.createRegions()
				.connectRegions()
				.blueprintsPostRegionConnection()
				.addExitStairs()
				.rectifyBars();
	}


	public void setTile(int x, int y, int z, Tile tile) {
		tiles[x][y][z] = tile;
	}
	public void setTile(Point p, Tile tile) {
		setTile(p.x, p.y, p.z, tile);
	}

	public Tile getTile(int x, int y, int z) {
		return tiles[x][y][z];
	}
	public Tile getTile(Point p) {
		return getTile(p.x, p.y, p.z);
	}

	public void reserveStructureTile(Point p) {
		structureReservedTiles[p.x][p.y][p.z] = true;
	}
	public boolean isStructureTileReserved(Point p) {
		return structureReservedTiles[p.x][p.y][p.z];
	}
}
