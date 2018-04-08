package code.UNOClasses;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label UNOWelcome;

    public void sayUNOWelcome(ActionEvent actionEvent) {
        UNOWelcome.setText("Welcome to UNO!");
    }
}
