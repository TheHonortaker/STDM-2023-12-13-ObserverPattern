package main.util;

import java.awt.*;

public class SegmentDrawer {
    private int outerX, outerY, x, y, outerW, outerH, w, h, s, arc;
    private Color background, active, inactive;
    private static final char[] top = {'0', '2', '3', '5', '6', '7', '8', '9'};
    private static final char[] mid = {'2', '3', '4', '5', '6', '8', '9'};
    private static final char[] bot = {'0', '2', '3', '5', '6', '8', '9'};
    private static final char[] topL = {'0', '4', '5', '6', '8', '9'};
    private static final char[] topR = {'0', '1', '2', '3', '4', '7', '8', '9'};
    private static final char[] botL = {'0', '2', '6', '8'};
    private static final char[] botR = {'0', '1', '3', '4', '5', '6', '7', '8', '9'};

    public SegmentDrawer(int outerX, int outerY, int outerW, int outerH, Color background, Color active, Color inactive) {
        this.outerX = outerX;
        this.outerY = outerY;
        this.outerW = outerW;
        this.outerH = outerH;
        this.background = background;
        this.active = active;
        this.inactive = inactive;
        x = outerX + outerW / 6;
        y = outerY + outerH / 6;
        w = outerW - outerW / 6 * 2;
        h = outerH - outerH / 6 * 2;
        s = w / 10;
        arc = s / 2;
    }

    public void draw(Graphics g, char c) {
        g.setColor(background);
        g.fillRect(outerX, outerY, outerW, outerH);
        if (c == '%') {
            drawPercentSign(g);
        } else {
            drawTopSegment(g, c);
            drawMidSegment(g, c);
            drawBotSegment(g, c);
            drawTopLSegment(g, c);
            drawTopRSegment(g, c);
            drawBotLSegment(g, c);
            drawBotRSegment(g, c);
        }
    }

    private void drawTopSegment(Graphics g, char c) {
        g.setColor(getColor(c, top));
        g.fillRoundRect(x + s, y, w - s * 2, s, arc, arc);
    }

    private void drawMidSegment(Graphics g, char c) {
        g.setColor(getColor(c, mid));
        g.fillRoundRect(x + s, y + h / 2 - s, w - s * 2, s, arc, arc);
    }

    private void drawBotSegment(Graphics g, char c) {
        g.setColor(getColor(c, bot));
        g.fillRoundRect(x + s, y + h - s * 2, w - s * 2, s, arc, arc);
    }

    private void drawTopLSegment(Graphics g, char c) {
        g.setColor(getColor(c, topL));
        g.fillRoundRect(x, y + s, s, h / 2 - s * 2, arc, arc);
    }

    private void drawTopRSegment(Graphics g, char c) {
        g.setColor(getColor(c, topR));
        g.fillRoundRect(x + w - s, y + s, s, h / 2 - s * 2, arc, arc);
    }

    private void drawBotLSegment(Graphics g, char c) {
        g.setColor(getColor(c, botL));
        g.fillRoundRect(x, y + h / 2, s, h / 2 - s * 2, arc, arc);
    }

    private void drawBotRSegment(Graphics g, char c) {
        g.setColor(getColor(c, botR));
        g.fillRoundRect(x + w - s, y + h / 2, s, h / 2 - s * 2, arc, arc);
    }

    private void drawPercentSign(Graphics g) {
        g.setColor(active);
        int circleDiameter = outerW / 5;
        g.drawOval(x + circleDiameter / 2, y + circleDiameter / 2, circleDiameter, circleDiameter);
        g.drawOval((int) (x + w - circleDiameter * 1.5), (int) (y + h - circleDiameter * 1.5), circleDiameter, circleDiameter);
        var p = new Polygon();
        p.addPoint(x + w - s, y);
        p.addPoint(x + w, y);
        p.addPoint(x + s, y + h);
        p.addPoint(x, y + h);
        g.fillPolygon(p);
    }

    private Color getColor(char c, char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                return active;
            }
        }
        return inactive;
    }
}
