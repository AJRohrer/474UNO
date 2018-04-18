package code.UNOClasses;

import code.UNOClasses.Card.UNOCard;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.*;

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
    @FXML private ImageView[] showPlayerHandImageView;
    @FXML private HBox playerHandHBox;
    @FXML private GridPane playerHandGridPane;

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
        setShowPlayerHandImageView();
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

    public List createHandImageArray () {
        Vector<UNOCard> hand = gameObj.getHumanPlayer().myHand().getUnoCardsList();
        List<Image> cardImages = new LinkedList();
        for (int i = 0; i < hand.size(); i++) {
            String cardType = hand.get(i).get_type().toString();
            String cardColor = hand.get(i).get_color().toString();
            String path = "resources/images/" + cardColor + "_" + cardType + ".png";
            System.out.println(path);
            Image card = new Image(path);
            cardImages.add(card);
        }
        return cardImages;
    }

    public void setShowPlayerHandImageView () {
        int handTotal = gameObj.getHumanPlayer().myHand().handTotal();
        showPlayerHandImageView = new ImageView[handTotal];
        List<Image> allCardImages = createHandImageArray();
        for (int i = 0; i < handTotal; i++) {
            showPlayerHandImageView[i] = new ImageView(allCardImages.get(i));
            showPlayerHandImageView[i].setFitHeight(100);
            showPlayerHandImageView[i].setFitWidth(100);
            showPlayerHandImageView[i].setSmooth(true);
            showPlayerHandImageView[i].setPreserveRatio(true);
            this.playerHandHBox.getChildren().add(showPlayerHandImageView[i]);
        }

    }
}
