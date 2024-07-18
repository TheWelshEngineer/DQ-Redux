package RogueLike.Main.Worldgen.Blueprints;

import RogueLike.Main.*;
import RogueLike.Main.AoE.Point;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Factories.ObjectFactory;
import RogueLike.Main.Worldgen.Blueprint;
import RogueLike.Main.Worldgen.Structure;
import RogueLike.Main.Worldgen.Structures.*;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class CaveFloor extends Blueprint {
	private final int depth;
	private final int numSmoothingIterations = 9;
	private final int numStructuresToSpawn = 20;

	public CaveFloor(WorldBuilder builder, int depth) {
		super(builder);
		this.depth = depth;
	}

	@Override
	public void onAdd() {}

	@Override
	public void onTileGeneration() {
		// Randomly fill floor with tiles
		Tile[][] tiles = new Tile[builder.width()][builder.height()];
		for(int x = 0; x < builder.width(); x++) {
			for(int y = 0; y < builder.height(); y++) {
				tiles[x][y] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
			}
		}

		// Smoothing
		for (int i = 0; i < numSmoothingIterations; i++) {
			tiles = smoothTiles(tiles);
			System.out.printf("Smoothing iteration %d done for depth %d.%n", i, depth);
		}

		// Apply the generated tiles
		for(int x = 0; x < builder.width(); x++) {
			for(int y = 0; y < builder.height(); y++) {
				builder.setTile(x, y, depth, tiles[x][y]);
			}
		}

		// Add structures
		for (int i = 0; i < numStructuresToSpawn; i++) {
			randomStructure().ifPresentOrElse(
				builder::addStructure,
				() -> System.out.printf("Failed to place a structure at depth %d%n", depth)
			);
		}
	}

	private Tile[][] smoothTiles(Tile[][] tiles) {
		Tile[][] tiles2 = new Tile[builder.width()][builder.height()];
		for (int x = 0; x < builder.width(); x++) {
			for (int y = 0; y < builder.height(); y++) {
				int floors = 0;
				int rocks = 0;
				for (int ox = -1; ox < 2; ox++) {
					for (int oy = -1; oy < 2; oy++) {
						if (!builder.isInBounds(x+ox, y+oy)) {continue;}
						if (tiles[x+ox][y+oy] == Tile.FLOOR) {
							floors++;
						}
						else {
							rocks++;
						}
					}
				}
				tiles2[x][y] = floors >= rocks ? Tile.FLOOR : Tile.WALL;
			}
		}
		return tiles2;
	}

	@Override
	public void onPostTileGeneration() {

	}

	@Override
	public void onPostRegionConnection() {

	}

	@Override
	public void onBuildWorld(World world) {
		createCreatures(world);
		createItems(world);
	}

	public void createCreatures(World world) {
		for(int i = 0; i < 18; i++) {
			world.factory().randomChest(depth, world.player(), true);
		}

		for(int i = 0; i < 85; i++) {
			world.factory().randomLesserMonster(depth, world.player(), true);
		}
		if(depth > 5) {
			for(int i = 0; i < 50; i++) {
				world.factory().randomMediumMonster(depth, world.player(), true);
			}
		}
		if(depth > 10) {
			for(int i = 0; i < 30; i++) {
				world.factory().randomGreaterMonster(depth, world.player(), true);
			}
		}
	}

	private void createItems(World world) {
		for(int i = 0; i < world.width() * world.height() / 25; i++) {
			world.factory().itemFactory.newRock(depth, 1);
		}
		for(int i = 0; i < 35; i++) {
			Point where = world.getEmptyLocationForTrap(depth);
			world.factory().createRandomTrap(where);
		}
		for(int i = 0; i < 6; i++) {//6
			world.factory().randomFood(depth, 1);
		}
		for(int j = 0; j < 4; j++) {
			world.factory().randomPotion(depth, true);
		}
		for(int k = 0; k < 1; k++) {
			world.factory().randomArmor(depth, true);
		}
		for(int l = 0; l < 1; l++) {
			world.factory().randomShield(depth, true);
		}
		for(int m = 0; m < 2; m++) {
			world.factory().randomWeapon(depth, true);
		}
		for(int l = 0; l < 1; l++) {
			world.factory().randomRing(depth, true);
		}
		for(int l = 0; l < 1; l++) {
			world.factory().randomScroll(depth, world.player(), true);
		}
	}

	private Optional<Structure> randomStructureAttempt() {
		switch (ExtraMaths.diceRoll(1, 6)) {
			case 1: // same as case 2
			case 2:
				return new GrassPatch(builder, 0, 0, depth).randomisePosition();
			case 3:
				return new SmallCell(builder, 0, 0, depth).randomisePosition();
			case 4:
				return new PotionRoom(builder, 0, 0, depth).randomisePosition();
			case 5:
				return new Pit(builder, 0, 0, depth).randomisePosition();
			case 6:
				return new PitItem(builder, 0, 0, depth).randomisePosition();
			default:
				throw new IllegalArgumentException("Roll out of bounds");
		}
	}

	private Optional<Structure> randomStructure() {
		// Attempt 10 times to generate a structure. If none can be generated, return Empty.
		// Else return the generated structure.
		return IntStream.range(0, 10)
			.mapToObj(i -> randomStructureAttempt())
			.flatMap(Optional::stream) //i.e. map to a stream containing only Structures, and no Empty instances
			.findFirst();
	}
}
