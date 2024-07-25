package RogueLike.Main.Utils.PointShapes;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class PointShape implements Iterable<Point> {
    private List<Point> points;

    protected abstract List<Point> generatePoints();

    public List<Point> points() {
        // generate the points if not already done, but reuse them if already generated
        if (points == null) {
            points = generatePoints();
        }
        return points;
    }

    @Override
    public Iterator<Point> iterator() {
        return points().iterator();
    }

    public Stream<Point> stream() {
        return points().stream();
    }
}
