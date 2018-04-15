package code.UNOClasses;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class UNOTableController implements Initializable {

    @FXML private Spinner numberOfPlayersSpinner;
    @FXML private Button setTableButton;

    private int numberOfCompPlayers;

    public void setNumberOfCompPlayers() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configure the spinner with values of 1-9
        SpinnerValueFactory<Integer> playerNumberFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9 ,1);
        this.numberOfPlayersSpinner.setValueFactory(playerNumberFactory);
        //numberOfPlayersSpinner.setEditable(true);
    }

    public void getNumberOfCompPlayers () {
        //this.numberOfCompPlayers = this.numberOfPlayersSpinner.;
    }
}
