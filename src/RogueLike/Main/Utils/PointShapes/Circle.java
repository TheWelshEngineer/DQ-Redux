package RogueLike.Main.Utils.PointShapes;

import java.util.List;
import java.util.stream.Collectors;

public class Circle extends PointShape {
    private final Point center;
    private final int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    protected List<Point> generatePoints() {
        double delta =
                0.5; // increase the radius a smidgeon to make the generated circle look nicer
        double squareRadius = (radius + delta) * (radius + delta);
        return new Square(center, radius)
                .stream()
                        .filter(p -> (p.squareDistanceTo(center) <= squareRadius))
                        .collect(Collectors.toList());
    }
}
