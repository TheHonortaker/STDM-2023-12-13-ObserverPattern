import java.util.ArrayList;
import java.util.Random;

public class DisplayData implements IObservable<DisplayData> {
    private ArrayList<IObserver<DisplayData>> observers = new ArrayList<>();
    private double percentage;

    public DisplayData(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
        notifyObservers();
    }

    public void increasePercentage(double increase) {
        setPercentage(getPercentage() + increase);
    }

    public Thread startRandomizerAsync() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Random rdm = new Random();
                    setPercentage(rdm.nextDouble(1));
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
        return thread;
    }

    public Thread startCountDownAsync() {
        Thread thread = new Thread(() -> {
            while (percentage > 0) {
                try {
                    increasePercentage(-0.01);
                    Thread.sleep(100);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
        return thread;
    }

    @Override
    public void subscribe(IObserver<DisplayData> observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this);
        }
    }
}
