package main.buttons;

import main.DisplayData;

public class ButtonStartRandom extends CustomButton {
    public ButtonStartRandom(DisplayData data) {
        super("Start Randomizer Data Thread", data);
        addActionListener((actionEvent) -> {
            data.startRandomizerAsync();
        });
    }

    @Override
    public void update(DisplayData observable) {
        setEnabled(!observable.isThreadRunning());
    }
}
