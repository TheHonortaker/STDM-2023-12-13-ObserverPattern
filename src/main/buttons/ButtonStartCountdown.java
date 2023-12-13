package main.buttons;

import main.DisplayData;

public class ButtonStartCountdown extends CustomButton {
    public ButtonStartCountdown(DisplayData data) {
        super("Start Countdown Data Thread", data);
        addActionListener((actionEvent) -> {
            data.startCountDownAsync();
        });
    }

    @Override
    public void update(DisplayData observable) {
        setEnabled(!observable.isThreadRunning());
    }
}
