package code.UNOClasses;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class UNOTableController implements Initializable {

    @FXML private Spinner numberOfPlayersSpinner;
    @FXML private Button setNumberOfPlayersButton;
    @FXML private Label numberOfPlayersLabel;
    @FXML private Button startNewGame;
    @FXML private Label showHandLabel;
    @FXML private Label showDiscardPile;

    private int numberOfCompPlayers;
    private Game gameObj;

    public void setNumberOfCompPlayers() {
        this.numberOfCompPlayers = this.numberOfPlayersSpinner.getValue().hashCode();
        numberOfPlayersLabel.setText("You have chosen " + numberOfCompPlayers + " computer players.");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Configure the spinner with values of 1-9
        SpinnerValueFactory<Integer> playerNumberFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9 ,1);
        this.numberOfPlayersSpinner.setValueFactory(playerNumberFactory);
        //numberOfPlayersSpinner.setEditable(true);

        numberOfPlayersLabel.setText("");
        showHandLabel.setText("");
        showDiscardPile.setText("");
    }

    public void startNewGame() {
        gameObj= new Game(numberOfCompPlayers);
        System.out.println(gameObj.toString());
        viewHumanPlayerHand();
        viewDiscardPile();
    }

    public void viewHumanPlayerHand () {
        this.showHandLabel.setText("Your hand:\n" + gameObj.getHumanPlayer().myHand().toString());
        System.out.println(gameObj.getHumanPlayer().myHand().toString());
    }

    public void viewDiscardPile () {
        this.showDiscardPile.setText("Discard Pile: " + gameObj.viewLastDiscardPileCard().toString());
    }
}
