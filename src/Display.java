import javax.swing.*;
import java.awt.*;

public abstract class Display extends JPanel implements IObserver<DisplayData> {
    public DisplayData data;

    public Display(int width, int height, DisplayData data) {
        setSize(width, height);
        data.subscribe(this);
        this.data = data;
        setBackground(Color.BLACK);
    }

    @Override
    public void update(DisplayData observable) {
        repaint();
    }

    public abstract void draw(Graphics g);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
