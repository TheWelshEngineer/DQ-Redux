package RogueLike.Main.AoE;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import RogueLike.Main.World;
import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.Utils.PointShapes.Point;
import RogueLike.Main.Utils.PointShapes.PointShape;

public class InstantiatedAoE {
	public final List<Point> points;

	public InstantiatedAoE(List<Point> points) {
		this.points = points.stream().filter(World::isInBounds).collect(Collectors.toList());
	}

	public InstantiatedAoE(PointShape shape) {
		this(shape.points());
	}

	public Stream<Creature> affectedCreatures() {
		return points.stream()
			.map(p -> World.creature(p.x, p.y, p.z))
			.filter(Objects::nonNull);
	}

	public Stream<Creature> affectedCreaturesExcept(Creature except) {
		return affectedCreatures().filter(c -> c != except);
	}

	public List<Point> points() {
		return points;
	}
}
