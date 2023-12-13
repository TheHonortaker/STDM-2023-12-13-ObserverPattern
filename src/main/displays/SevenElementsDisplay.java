package main.displays;

import main.DisplayData;

import java.awt.*;

public class SevenElementsDisplay extends Display {
    public SevenElementsDisplay(DisplayData data) {
        super(300, 300, data);
    }

    @Override
    public void draw(Graphics g) {
        String display = String.format("   %s%s", (int) data.getPercentage(), "%");
        display = display.substring(display.length() - 4);
        int digitWidth = getWidth() / 5;
        int digitHeight = digitWidth * 2;
        int drawY = getHeight() / 2 - digitHeight / 2;
        int drawX = 0;
        for (int i = 0; i < 4; i++) {
            drawX += digitWidth / 5;
            drawDigit(g, display.charAt(i), drawX, drawY, digitWidth, digitHeight);
            drawX += digitWidth;
        }
    }

    private void drawDigit(Graphics g, char c, int x, int y, int w, int h) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        if (c == '%') {
            g.setColor(Color.GREEN);
            int circleDiameter = w / 5;
            x += w / 6;
            y += h / 6;
            w -= w / 6 * 2;
            h -= h / 6 * 2;
            g.drawOval(x + circleDiameter / 2, y + circleDiameter / 2, circleDiameter, circleDiameter);
            g.drawOval((int) (x + w - circleDiameter * 1.5), (int) (y + h - circleDiameter * 1.5), circleDiameter, circleDiameter);
            var p = new Polygon();
            p.addPoint(x + w - w / 15, y);
            p.addPoint(x + w, y);
            p.addPoint(x + w / 15, y + h);
            p.addPoint(x, y + h);
            g.fillPolygon(p);
        }
    }
}
