package RogueLike.Main.Utils.PointShapes;

import java.util.List;
import java.util.stream.Collectors;

public class Cone extends PointShape {
    public final Point source;
    public final Point target;
    public final int length;
    public final double angle;

    public Cone(Point source, Point target, int length, double angle) {
        // Note that angle is in DEGREES here.
        this.source = source;
        this.target = target;
        this.length = length;
        this.angle = angle;

        if (angle > 360.0 || angle < 0) {
            throw new IllegalArgumentException(String.format("Invalid angle %f", angle));
        }
    }

    @Override
    protected List<Point> generatePoints() {
        Vec3 src = new Vec3(source);
        Vec3 targetDisplacement = new Vec3(target).difference(src);
        double cosine = Math.cos(Math.toRadians(angle / 2));

        return new Circle(source, length)
                .stream()
                        .filter(
                                p -> {
                                    Vec3 displacement = new Vec3(p).difference(src);
                                    return Vec3.cosine(displacement, targetDisplacement) >= cosine;
                                })
                        .collect(Collectors.toList());
    }

    static class Vec3 {
        public double x;
        public double y;
        public double z;

        public Vec3(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Vec3(Point p) {
            this(p.x, p.y, p.z);
        }

        public Vec3 difference(Vec3 other) {
            return new Vec3(x - other.x, y - other.y, z - other.z);
        }

        public double dot(Vec3 other) {
            return x * other.x + y * other.y + z * other.z;
        }

        public static double cosine(Vec3 a, Vec3 b) {
            return a.dot(b) / (a.magnitude() * b.magnitude());
        }

        public double magnitude() {
            return Math.sqrt(this.dot(this));
        }
    }
}
