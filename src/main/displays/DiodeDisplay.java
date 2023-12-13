package main.displays;

import main.DisplayData;

import java.awt.*;

public class DiodeDisplay extends Display {
    private int circles;

    public DiodeDisplay(DisplayData data) {
        this(data, 5);
    }

    public DiodeDisplay(DisplayData data, int diodes) {
        super(300, 300, data);
        this.circles = diodes;
        addMouseWheelListener((mouseWheelEvent) -> {
            if (mouseWheelEvent.getUnitsToScroll() < 0 && circles < 100) {
                circles++;
            } else if (mouseWheelEvent.getUnitsToScroll() > 0 && circles > 1) {
                circles--;
            } else {
                return;
            }
            repaint();
        });
    }

    @Override
    public void draw(Graphics g) {
        int temp = getHeight() / (circles + 1);
        int drawWidth = Math.max(temp, getWidth() / 8);
        int y = 0;
        int x = getWidth() / 2 - drawWidth / 2;
        for (int i = 0; i < circles; i++) {
            y += temp / (circles + 1);
            g.setColor(getColor(i));
            g.fillOval(x, y, drawWidth, temp);
            y += temp;
        }
    }

    private Color getColor(int circle) {
        double circlePercentage = ((double) 1 / circles);
        double circleFill = data.getPercentage() + circle * circlePercentage;
        if (circleFill < 1 - circlePercentage) {
            return Color.RED;
        }
        if (circleFill < 1 - circlePercentage / 2) {
            return Color.YELLOW;
        }
        return Color.GREEN;
    }
}
