package main;

import main.buttons.ButtonCancel;
import main.buttons.ButtonStartCountdown;
import main.buttons.ButtonStartRandom;
import main.displays.*;

import javax.swing.*;
import java.awt.*;

public class SwingCanvasExample extends JFrame {
    public static void main(String[] args) {
        var data = new DisplayData();
        SwingUtilities.invokeLater(() -> {
            SwingCanvasExample example = new SwingCanvasExample(data);
        });
    }

    public SwingCanvasExample(DisplayData data) {
        setTitle("Java Swing Canvas Beispiel");
        setSize(1210, 328);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(getMainPanel(data));

        setVisible(true);
    }

    private JPanel getMainPanel(DisplayData data) {
        // CONTROL PANEL
        JPanel controlPanel = new JPanel(new GridLayout(0, 1));
        controlPanel.setBackground(Color.DARK_GRAY);
        controlPanel.add(new LabelDisplay(data));
        controlPanel.add(new SliderDisplay(data));
        controlPanel.add(new ButtonStartCountdown(data));
        controlPanel.add(new ButtonStartRandom(data));
        controlPanel.add(new ButtonCancel(data));
        // MAIN PANEL
        JPanel mainPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        mainPanel.setSize(this.getSize());
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.add(new DiodeDisplay(data));
        mainPanel.add(new SevenElementsDisplay(data));
        mainPanel.add(new AnalogDisplay(data));
        mainPanel.add(controlPanel);
        return mainPanel;
    }
}



