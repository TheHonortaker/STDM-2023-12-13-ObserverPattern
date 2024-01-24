package main.displays;

import main.DisplayData;

import java.awt.*;

public class DiodeDisplay extends Display {
    private int circles;

    public DiodeDisplay(DisplayData data) {
        this(data, 5);
    }

    public DiodeDisplay(DisplayData data, int diodes) {
        this(data, diodes, 300, 300);
    }

    public DiodeDisplay(DisplayData data, int diodes, int width, int height) {
        super(width, height, data);
        this.circles = diodes;
        addMouseWheelListener((mouseWheelEvent) -> {
            if (mouseWheelEvent.getUnitsToScroll() < 0 && circles < 95) {
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

    @Override
    public Display getExternalDisplay(int width, int height) {
        return new DiodeDisplay(data, circles, width, height);
    }

    private Color getColor(int circle) {
        double circlePercentage = 1d / circles;
        double circleFill = data.getPercentage() + circle * circlePercentage;
        if (data.getPercentage() == 0 || circleFill < 1 - circlePercentage) { // TODO: mach mal diesen shitcode besser pls
            return Color.RED;
        }
        if (circleFill < 1 - circlePercentage / 2) {
            return Color.YELLOW;
        }
        return Color.GREEN;
    }
}
