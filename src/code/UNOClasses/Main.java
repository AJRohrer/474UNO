package code.UNOClasses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UNO.fxml"));
        primaryStage.setTitle("UNO");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        /*UNOCard c = new UNOCard(CardType.DRAWTWO, UNOColor.BLUE);
        System.out.println(c);

        System.out.println("Draw 2: " + c.isDraw2());
        System.out.println("Reverse: " + c.isReverse());
        System.out.println("Skip: " + c.isSkip());
        System.out.println("Wild: " + c.isWild());
        System.out.println("Wild Draw 4: " + c.isWildDraw4());

        Player p = new Player(true);
        //will be replaced by deal functionality soon.
        p.addCardtoHand(c);
        p.addCardtoHand(c);
        System.out.println(p.toString());

        Deck deck = new Deck();
        System.out.println("*******Deck in order\n" + deck);
        System.out.println("*******Deck total: " + deck.deckTotal());
        deck.shuffleDeck();
        deck.shuffleDeck();
        System.out.println("*******Deck shuffled\n" + deck);
        System.out.println("*******Deck total: " + deck.deckTotal());
        Stack test = new Stack();
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        System.out.println("*******Mock player hand\n" + test);
        System.out.println("*******Deck without player cards\n" + deck);
        System.out.println("*******New deck total: " + deck.deckTotal());

        // entry for the project, which will launch a single instance of the game
        Game gameObj= new Game();
        gameObj.play();*/
    }
}
