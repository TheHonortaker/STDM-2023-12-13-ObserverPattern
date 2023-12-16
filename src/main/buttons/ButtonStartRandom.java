package main.buttons;

import main.DisplayData;

public class ButtonStartRandom extends CustomButton {
    public ButtonStartRandom(DisplayData data) {
        super("Start Sinus Curve Data Thread", data);
        addActionListener((actionEvent) -> {
            data.startSinusCurveAsync();
        });
    }

    @Override
    public void update(DisplayData observable) {
        setEnabled(!observable.isThreadRunning());
    }
}
