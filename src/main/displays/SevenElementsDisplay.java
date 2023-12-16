package main.displays;

import main.DisplayData;
import main.util.SegmentDrawer;

import java.awt.*;
import java.beans.PropertyChangeListener;

public class SevenElementsDisplay extends Display {
    private SegmentDrawer[] segmentDrawers;

    public SevenElementsDisplay(DisplayData data) {
        super(300, 300, data);
        PropertyChangeListener changeListener = (propertyChangeEvent) -> {
            initSegmentDrawers();
        };
        addPropertyChangeListener("width", changeListener);
        addPropertyChangeListener("height", changeListener);
        initSegmentDrawers();
    }

    private void initSegmentDrawers() {
        segmentDrawers = new SegmentDrawer[4];
        int digitWidth = getWidth() / 5;
        int digitHeight = digitWidth * 2;
        int drawY = getHeight() / 2 - digitHeight / 2;
        int drawX = 0;
        for (int i = 0; i < segmentDrawers.length; i++) {
            drawX += digitWidth / 5;
            segmentDrawers[i] = new SegmentDrawer(drawX, drawY, digitWidth, digitHeight, new Color(40, 40, 40), Color.GREEN, Color.DARK_GRAY);
            drawX += digitWidth;
        }
    }

    @Override
    public void draw(Graphics g) {
        String display = "  " + data.getIntPercentage() + "%";
        display = display.substring(display.length() - 4);
        for (int i = 0; i < segmentDrawers.length; i++) {
            segmentDrawers[i].draw(g, display.charAt(i));
        }
    }
}
