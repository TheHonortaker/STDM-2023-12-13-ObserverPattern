package main.buttons;

import main.DisplayData;

public class ButtonCancel extends CustomButton {
    public ButtonCancel(DisplayData data) {
        super("Cancel Running Data Thread", data);
        addActionListener((actionEvent) -> {
            data.setThreadRunning(false);
        });
    }

    @Override
    public void update(DisplayData observable) {
        setEnabled(observable.isThreadRunning());
    }
}
