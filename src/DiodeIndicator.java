import java.awt.*;

public class DiodeIndicator extends Display {
    private int circles;

    public DiodeIndicator(DisplayData data) {
        this(data, 5);
    }

    public DiodeIndicator(DisplayData data, int diodes) {
        super(300, 300, data);
        this.circles = diodes;
    }

    @Override
    public void draw(Graphics g) {
        int temp = getHeight() / (circles + 1);
        int y = 0;
        int x = getWidth() / 2 - temp / 2;
        for (int i = 0; i < circles; i++) {
            y += temp / (circles + 2);
            g.setColor(getColor(i));
            g.fillOval(x, y, temp, temp);
            y += temp;
        }
    }

    private Color getColor(int circle) {
        double circlePercentage = ((double) 1 / circles);
        double circleFill = data.getPercentage() + circle * circlePercentage;
        if (circleFill < 1 - circlePercentage) {
            return Color.RED;
        }
        if (circleFill < 1 - circlePercentage / 2) {
            return Color.YELLOW;
        }
        return Color.GREEN;
    }
}
