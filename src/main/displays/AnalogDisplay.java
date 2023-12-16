package main.displays;

import main.DisplayData;
import main.util.Point2D;
import main.util.Vector2D;

import java.awt.*;

public class AnalogDisplay extends Display {
    private final int fromNo;
    private final int toNo;

    public AnalogDisplay(DisplayData data) {
        this(data, 0, 100);
    }

    public AnalogDisplay(DisplayData data, int fromNo, int toNo) {
        super(300, 300, data);
        this.fromNo = fromNo;
        this.toNo = toNo;
    }

    @Override
    public void draw(Graphics g) {
        final int thickness = getWidth() / 25;
        final int radius = getWidth() / 3;
        drawBase(g, radius, thickness);
        drawScala(g, radius, thickness);
        drawNeedle(g, radius, thickness);
    }

    private void drawBase(Graphics g, final int radius, final int thickness) {
        g.setColor(Color.WHITE);
        g.fillOval(radius / 2, radius / 2, radius * 2, radius * 2);
        g.setColor(Color.BLACK);
        g.fillOval(radius / 2 + thickness / 2, radius / 2 + thickness / 2, radius * 2 - thickness, radius * 2 - thickness);
        Polygon p = new Polygon();
        final int midX = getWidth() / 2;
        final int midY = getHeight() / 2;
        p.addPoint(midX, midY);
        p.addPoint(midX - radius - thickness, midY + radius + thickness);
        p.addPoint(midX + radius + thickness, midY + radius + thickness);
        g.fillPolygon(p);
    }

    private void drawScala(Graphics g, final int radius, final int thickness) {
        g.setColor(Color.WHITE);
        final int midX = getWidth() / 2;
        final int midY = getHeight() / 2;
        final int steps = (toNo - fromNo) / 10 + ((toNo - fromNo) % 10 > 0 ? 2 : 1); // Zero & +1 if not dividable by 10
        final double anglePer10 = 270d / (steps - 1);
        double currentAngle = 135;
        for (int i = 0; i < steps; i++) {
            final double angleBow = currentAngle * Math.PI / 180;
            final int x = (int) (radius * Math.cos(angleBow) + midX);
            final int y = (int) (radius * Math.sin(angleBow) + midY);

            Vector2D v = new Vector2D(midX, midY, x, y);
            Polygon p = v.getPolygon(0.9, 1.05, thickness / 2);
            g.fillPolygon(p);
            Point2D t = v.multiply(1.25).to;
            g.drawString(Integer.toString(fromNo + i * (steps - 1)), (int) t.x - thickness, (int) t.y + thickness);
            //g.drawLine(midX, midY, x, y);

            currentAngle += anglePer10;
        }
    }

    private void drawNeedle(Graphics g, final int radius, final int thickness) {
        final int midX = getWidth() / 2;
        final int midY = getHeight() / 2;
        final double value = getScaledPercentageValue();
        final double angle = 135 + value * 270;

        final double angleBow = angle * Math.PI / 180;
        final int x = (int) (radius * Math.cos(angleBow) + midX);
        final int y = (int) (radius * Math.sin(angleBow) + midY);

        Vector2D v = new Vector2D(midX, midY, x, y);
        Vector2D normalized = v.normalize().multiply(thickness / 2);
        Polygon p = new Polygon();
        p.addPoint((int) v.to.x, (int) v.to.y);
        p.addPoint((int) v.from.add(normalized.to).x, (int) v.from.add(normalized.to).y);
        p.addPoint((int) v.from.subtract(normalized.to).x, (int) v.from.subtract(normalized.to).y);

        g.setColor(Color.RED);
        g.fillPolygon(p);

        g.setColor(Color.WHITE);
        g.fillOval(midX - thickness / 2, midY - thickness / 2, thickness, thickness);
    }

    private double getScaledPercentageValue() {
        double value = data.getPercentage() * 100;
        value = Math.min(value, toNo);
        value = Math.max(value, fromNo);
        value -= fromNo;
        value = value / (toNo - fromNo);
        return value;
    }
}
