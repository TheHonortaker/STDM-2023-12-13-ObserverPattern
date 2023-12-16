package main.displays;

import main.DisplayData;
import main.util.IObserver;

import javax.swing.*;
import java.awt.*;

public class LabelDisplay extends JLabel implements IObserver<DisplayData> {
    public LabelDisplay(DisplayData data) {
        data.subscribe(this);
        update(data);
        setForeground(Color.WHITE);
    }

    @Override
    public void update(DisplayData observable) {
        setText(String.format("         Value: %s%s", observable.getIntPercentage(), "%"));
    }
}
