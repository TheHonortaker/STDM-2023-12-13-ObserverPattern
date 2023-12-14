package main.buttons;

import main.DisplayData;
import main.util.IObserver;

import javax.swing.*;
import java.awt.*;

public abstract class CustomButton extends JButton implements IObserver<DisplayData> {
    public CustomButton(String text, DisplayData data) {
        super(text);
        data.subscribe(this);
        setBackground(Color.DARK_GRAY);

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            setForeground(Color.WHITE);
        } else {
            setForeground(Color.BLACK);
        }

    }

    @Override
    public abstract void update(DisplayData observable);
}
