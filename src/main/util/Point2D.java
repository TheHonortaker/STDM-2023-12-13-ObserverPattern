package main.util;

public class Point2D {
    public final double x;
    public final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D add(Point2D other) {
        return new Point2D(this.x + other.x, this.y + other.y);
    }

    public Point2D subtract(Point2D other) {
        return new Point2D(this.x - other.x, this.y - other.y);
    }

    public Point2D scale(double factor) {
        return new Point2D(x * factor, y * factor);
    }

    @Override
    public String toString() {
        return String.format("( %s | %s )", x, y);
    }
}
