package main.displays;

import main.DisplayData;

import java.awt.*;

public class SevenElementsDisplay extends Display {
    public SevenElementsDisplay(DisplayData data) {
        super(300, 300, data);
    }

    @Override
    public void draw(Graphics g) {
        int digitWidth = getWidth() / 5;
        int digitHeight = digitWidth * 2;
        int drawY = getHeight() / 2 - digitHeight / 2;
        int drawX = 0;
        for (int i = 0; i < 4; i++) {
            drawX += digitWidth / 5;
            g.setColor(Color.DARK_GRAY);
            g.fillRect(drawX, drawY, digitWidth, digitHeight);
            drawX += digitWidth;
        }
    }
}
