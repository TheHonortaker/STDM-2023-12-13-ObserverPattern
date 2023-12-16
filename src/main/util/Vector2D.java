package main.util;

import java.awt.*;

public class Vector2D {
    public final Point2D from;
    public final Point2D to;
    private final Vector2D zeroVector;

    public Vector2D(double fromX, double fromY, double toX, double toY) {
        this(new Point2D(fromX, fromY), new Point2D(toX, toY));
    }

    public Vector2D(Point2D from, Point2D to) {
        this.from = from;
        this.to = to;
        if (from.x == 0 && from.y == 0) {
            zeroVector = this;
        } else {
            zeroVector = new Vector2D(new Point2D(0, 0), new Point2D(to.x - from.x, to.y - from.y));
        }
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.from, this.to.add(other.to));
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.from, this.to.subtract(other.to));
    }

    public Vector2D multiply(double scalar) {
        return new Vector2D(this.from, this.from.add(zeroVector.to.scale(scalar)));
    }

    public double getLength() {
        return Math.sqrt(zeroVector.to.x * zeroVector.to.x + zeroVector.to.y * zeroVector.to.y);
    }

    public Vector2D normalize() {
        Vector2D orthogonal = new Vector2D(0, 0, -zeroVector.to.y, zeroVector.to.x);
        double length = Math.sqrt(orthogonal.to.x * orthogonal.to.x + orthogonal.to.y * orthogonal.to.y);
        return new Vector2D(0, 0, orthogonal.to.x / length, orthogonal.to.y / length);
    }

    public Polygon getPolygon(double begin, double end, int thickness) {
        Vector2D boxVector = new Vector2D(this.multiply(begin).to, this.multiply(end).to);
        Vector2D normalized = boxVector.normalize().multiply(thickness / 2);

        Vector2D[] v = new Vector2D[]{
                new Vector2D(boxVector.from, boxVector.from.add(normalized.to)),
                new Vector2D(boxVector.from, boxVector.from.subtract(normalized.to)),
                new Vector2D(boxVector.to, boxVector.to.subtract(normalized.to)),
                new Vector2D(boxVector.to, boxVector.to.add(normalized.to))
        };
        Polygon p = new Polygon();
        for (int i = 0; i < v.length; i++) {
            p.addPoint((int) v[i].to.x, (int) v[i].to.y);
        }
        return p;
    }

    @Override
    public String toString() {
        return String.format("Vector { %s -> %s }", from, to);
    }
}
