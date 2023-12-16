package main.displays;

import main.DisplayData;
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
        p.addPoint(midX - radius, midY + radius);
        p.addPoint(midX + radius, midY + radius);
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

            Polygon p = new Vector2D(midX, midY, x, y).getPolygon(0.9, 1.05, thickness / 2);
            g.fillPolygon(p);
            //g.drawLine(midX, midY, x, y);

            currentAngle += anglePer10;
        }
    }
}
