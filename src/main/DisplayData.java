package main;

import main.util.IObservable;
import main.util.IObserver;

import java.util.ArrayList;
import java.util.Random;

public class DisplayData implements IObservable<DisplayData> {
    private ArrayList<IObserver<DisplayData>> observers = new ArrayList<>();
    private double percentage;
    private boolean threadRunning;

    public DisplayData() {
        this(1);
    }

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

    public boolean isThreadRunning() {
        return threadRunning;
    }

    public void setThreadRunning(boolean threadRunning) {
        this.threadRunning = threadRunning;
        notifyObservers();
    }

    public Thread startRandomizerAsync() {
        Thread thread = new Thread(() -> {
            setThreadRunning(true);
            while (threadRunning) {
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
            setThreadRunning(true);
            int intPercentage = 100;
            while (threadRunning && intPercentage > 0) {
                try {
                    setPercentage((double) intPercentage / 100);
                    intPercentage--;
                    Thread.sleep(100);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            setThreadRunning(false);
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
