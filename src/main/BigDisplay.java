package main;

import main.displays.*;

import javax.swing.*;

public class BigDisplay extends JFrame {
    public BigDisplay(Display display) {
        setTitle(display.getClass().getName());
        setSize(600, 628);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(display);

        setVisible(true);
    }
}
