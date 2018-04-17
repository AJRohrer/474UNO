package code.UNOClasses;

import code.UNOClasses.Card.UNOCard;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

public class UNOTableController implements Initializable {

    @FXML private Spinner numberOfPlayersSpinner;
    @FXML private Button setNumberOfPlayersButton;
    @FXML private Label numberOfPlayersLabel;
    @FXML private Button startNewGame;
    @FXML private Label showHandLabel;
    @FXML private Label showDiscardPile;
    @FXML private Label showCompPlayerCardNumberLabel;
    @FXML private ChoiceBox chooseCardFromHandChoiceBox;

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
        showCompPlayerCardNumberLabel.setText("");
        chooseCardFromHandChoiceBox.setValue("");
    }

    public void startNewGame() {
        gameObj= new Game(numberOfCompPlayers);
        System.out.println(gameObj.toString());
        viewHumanPlayerHand();
        viewDiscardPile();
        showCompPlayerCardNumberLabel.setText(viewCompPlayerCardNumber());
        cardsInHand();
    }

    public void viewHumanPlayerHand () {
        this.showHandLabel.setText("Your hand:\n" + gameObj.getHumanPlayer().myHand().toString());
        System.out.println(gameObj.getHumanPlayer().myHand().toString());
    }

    public void viewDiscardPile () {
        this.showDiscardPile.setText("Discard Pile: " + gameObj.viewLastDiscardPileCard().toString());
    }

    public String viewCompPlayerCardNumber () {
        Vector<Player> players = gameObj.getPlayers();
        String remainingCards = "";
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isHuman() == false) {
                remainingCards += players.get(i).getName() + " has " + players.get(i).myHand().handTotal() + " cards remaining.\n";
            }
        }
        return remainingCards;
    }

    public void cardsInHand () {
        this.chooseCardFromHandChoiceBox.setItems(FXCollections.observableArrayList(gameObj.getHumanPlayer().myHand().getUnoCardsList()));
        System.out.println(chooseCardFromHandChoiceBox.getItems());
    }

    public void playCardButtonPushed () {

    }
}
