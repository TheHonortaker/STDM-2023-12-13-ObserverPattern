package main.displays;

import main.DisplayData;
import main.util.IObserver;

import javax.swing.*;
import java.awt.*;

public class SliderDisplay extends JSlider implements IObserver<DisplayData> {
    public SliderDisplay(DisplayData data) {
        super(0, 100, (int) (100 * data.getPercentage()));
        setBackground(Color.DARK_GRAY);
        data.subscribe(this);
        addChangeListener((changeEvent) -> {
            data.setPercentage((double) getValue() / 100);
        });
    }

    @Override
    public void update(DisplayData observable) {
        setValue((int) (100 * observable.getPercentage()));
        setEnabled(!observable.isThreadRunning());
    }
}
