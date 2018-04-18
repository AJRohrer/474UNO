package code.UNOClasses;

import code.UNOClasses.Card.UNOCard;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    @FXML private ImageView discardPileImage;
    @FXML private ImageView drawPileImage;

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
        discardPileImage.setImage(null);
    }

    public void startNewGame() {
        gameObj= new Game(numberOfCompPlayers);
        System.out.println(gameObj.toString());
        viewHumanPlayerHand();
        viewDiscardPile();
        showCompPlayerCardNumberLabel.setText(viewCompPlayerCardNumber());
        cardsInHand();
        setDiscardPileImage();
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
        String remainingCards = "Player status\n";
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isHuman() == false) {
                remainingCards += i + ". " + players.get(i).getName() + ": " + players.get(i).myHand().handTotal() + " cards\n";
            }
            else if (players.get(i).isHuman() == true) {
                players.get(i).setName("You");
                remainingCards += i + ". " + players.get(i).getName() + ": " + players.get(i).myHand().handTotal() + " cards\n";
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

    public void updateCardView () {
        Vector hand = gameObj.getHumanPlayer().myHand().getUnoCardsList();
    }

    public void setDiscardPileImage () {
        String cardType = gameObj.viewLastDiscardPileCard().get_type().toString();
        String cardColor = gameObj.viewLastDiscardPileCard().get_color().toString();
        String path = "resources/images/" + cardColor + "_" + cardType + ".png";
        System.out.println(path);
        Image card = new Image(path);
        this.discardPileImage.setImage(card);
    }
}
