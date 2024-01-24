package main.displays;

import main.BigDisplay;
import main.DisplayData;
import main.util.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Display extends JPanel implements IObserver<DisplayData> {
    public DisplayData data;

    public Display(int width, int height, DisplayData data) {
        setSize(width, height);
        data.subscribe(this);
        this.data = data;
        setBackground(Color.BLACK);
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isShiftDown()) {
                    openExternal();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        addMouseListener(ml);
    }

    @Override
    public void update(DisplayData observable) {
        repaint();
    }

    public abstract void draw(Graphics g);

    public abstract Display getExternalDisplay(int width, int height);

    public void openExternal() {
        SwingUtilities.invokeLater(() -> {
            new BigDisplay(getExternalDisplay(600, 600));
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        draw(g);
    }

}
