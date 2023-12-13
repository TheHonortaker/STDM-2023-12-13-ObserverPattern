import javax.swing.*;
import java.awt.*;

public class SwingCanvasExample extends JFrame {
    public SwingCanvasExample(DisplayData data) {
        setTitle("Java Swing Canvas Beispiel");
        setSize(1220, 328);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisiere die Canvas und füge sie zur JFrame hinzu
        //JPanel controlPanel = new JPanel(); //TOOD: addFunctionality
        JSlider slider = new JSlider(0, 100, 100);
        slider.addChangeListener((changeEvent) -> {
            if (slider.getValue() == 0) {
                data.setPercentage(0);
            } else {
                data.setPercentage((double) slider.getValue() / 100);
            }
        });
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        mainPanel.setSize(this.getSize());
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.add(new DiodeDisplay(data));
        mainPanel.add(new SevenElementsDisplay(data));
        mainPanel.add(new AnalogDisplay(data));
        mainPanel.add(slider);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        var data = new DisplayData(1);
        data.startCountDownAsync();
        SwingUtilities.invokeLater(() -> {
            SwingCanvasExample example = new SwingCanvasExample(data);
        });
    }
}



