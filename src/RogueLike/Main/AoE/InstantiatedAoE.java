package RogueLike.Main.AoE;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Utils.PointShapes.PointShape;
import RogueLike.Main.World;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InstantiatedAoE {
	public final List<Point> points;
	private final World world;

	public InstantiatedAoE(List<Point> points, World world) {
		this.points = points.stream().filter(world::isInBounds).collect(Collectors.toList());
		this.world = world;
	}

	public InstantiatedAoE(PointShape shape, World world) {
		this(shape.points(), world);
	}

	public Stream<Creature> affectedCreatures() {
		return points.stream()
			.map(p -> world.creature(p.x, p.y, p.z))
			.filter(Objects::nonNull);
	}

	public Stream<Creature> affectedCreaturesExcept(Creature except) {
		return affectedCreatures().filter(c -> c != except);
	}

	public List<Point> points() {
		return points;
	}
}
