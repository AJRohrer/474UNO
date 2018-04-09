package code.UNOClasses;
/* The following class, code.UNOClasses.Game, is responsible for uniting all classes for the unified,
 * coordinated functionality of what is expected from the game, UNO. */

import code.UNOClasses.Card.UNOCard;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Game {
    private Vector<Player> players;
    private Stack<UNOCard> discardPile;
    Scanner reader;
    Boolean GameOver = false;

    public int totalNumberOfPlayers = 0;

    public Deck deck;
    int aIPlayerCount = 0;

    public Game(){
        /** <h1>Game class constructor</h1>
         *  <p><When Game class is declared, the function will be responsible for initializing the
         *  game class instance, by
         *  [1] obtaining total number of players,
         *  [2] randomizing the player order,
         *  [3] creating a deck,
         *  [4] shuffling the deck
         *  [5] deal cards to each play.
         *  [6] initializing the discard pile of cards from the deck (draw first card)
         *
         *  This will prepare the Game for gameplay. </p>
         *
         * @author Darya Kiktenko
         * @author Pranjali Mishra
         */

        setTotalNumberOfPlayers(getTotalNumberOfPlayers());
        initiatePlayersVector(totalNumberOfPlayers);
        shufflePlayerOrder(players);
        deck = new Deck();
        deck.shuffleDeck();
        dealHand(deck, players);
        initializeDiscardPile(deck);
    }

    public Label UNOWelcome;

    public void sayUNOWelcome(ActionEvent actionEvent) {
        UNOWelcome.setText("Welcome to UNO!");
    }
    public Vector<Player> shufflePlayerOrder(Vector<Player> playerVector) {
        /** Shuffles the players vector
         * Adapted from original dealHand(), separated for OOP & unit testing purposes
         * @author Darya Kiktenko
         * @author Pranjali Mishra
         */

        Collections.shuffle(playerVector);
        return playerVector;
    }

    public int getTotalNumberOfPlayers(){
        /** Obtains the total number of players in the game in addition to the human player
         * @author Darya Kiktenko
         * @author Pranjali Mishra
         */
        int result = 1; //default for the human player

        // TODO: To be changed with incorporation of the UI

        result += 3; // 3 AI

        return result;
    }

    public void setTotalNumberOfPlayers(int totalNumber){
        /** Sets the total number of players which will be playing the game (including the human user)
         * @author Darya Kiktenko
         */
        totalNumberOfPlayers = totalNumber;
    }

    public Vector<Player> initiatePlayersVector(int numberOfPlayers) {
        /** Initializes the players vector class member
         * by creating a new player object and adding that to the vector
         * @author Darya Kiktenko
         */
        players = new Vector<Player>();
        for (int i = 0; i < (numberOfPlayers-1); i++){ // minus 1, because human player will be separately initialized
            Player tempPlayer = new Player(false); //false, because all except human are AI players
            players.add(tempPlayer);
        }

        Player human = new Player(true);
        players.add(human);

        return players;
    }

    public Vector<Player> dealHand(Deck deckToDealFrom, Vector<Player> gamePlayers) { // SRS - FR1.2 & FR1.3 implementation
        /** deals 7 cards from the deck to each of the players in the gamePlayers vector,
         * including the AI and the human player.
         * Returns the players vector that now have been dealt cards.
         * Adapted from the original dealHand() function written by Pranjali Mishra
         * @author Pranjali Mishra
         */

    	for (int j=0; j<7; j++) {
    	    for(Player player : gamePlayers) {
    	        player.addCardtoHand(deckToDealFrom.deal());
    	    }
    	}
    	return gamePlayers;
    }

    public Stack<UNOCard> initializeDiscardPile(Deck deckToDealFrom){ // SRS - FR1.4 & FR1.5 & FR1.6 implementation
        /** initializes the Discard Pile AFTER players have been dealt their hand
         * Obtains the top card from the deck & places it on top of the discardPile.
         * Unless the drawn card is wild draw 4 card, then the card is returned to the bottom of the draw
         * deck and another card is drawn from the top of the draw deck.
         * Returns the new initialized stack of UNOCards that is the discardPile
         * Adapted from the original initializeDiscardPile() function written by Pranjali Mishra
         * @author Pranjali Mishra
         * @author Darya Kiktenko
         */
        discardPile = new Stack<UNOCard>();
        UNOCard topCard = null;
        boolean validCard = false;
        while (!validCard){
            topCard = deckToDealFrom.deal();
            if (topCard.isWildDraw4()) {
                System.out.println("First card drawn is a \"Wild Draw Four\"- adding back to the Deck & drawing another card.");
                deck.addCard(topCard);
            }
            else {
                discardPile.add(topCard);
                validCard = true;
            }
        }
        return discardPile;
    }

    public UNOCard drawCard(){
        /** returns the UNOCard from the top of the deck & removes that card from the deck
         * @author Darya Kiktenko
         * */

        return deck.deal();
    }

    

    public boolean validateCardColorsMatch(UNOCard playedCard, UNOCard discardPileTopCard){
        /** compares two cards based on the color
         * @author Darya Kiktenko*/

        boolean result = false;
        if (discardPileTopCard.get_color() == playedCard.get_color()){
            result = true;
        }
        return result;
    }

    public boolean validateCardTypesMatch(UNOCard playedCard, UNOCard discardPileTopCard){
        /** compares two cards based on their type.
         * @author Darya Kiktenko */

        boolean result = false;
        if (discardPileTopCard.get_type() == playedCard.get_type()){
            result = true;
        }
        return result;
    }

    public boolean challengeWildDraw4Card(Player challenger, Player challenged){
        /* NOTE: distinction between challenger and challenged.
            Challenger is the PERSON AFTER the challenged (turn-wise) who suspects
            that the challenged that they have a card in their hand which matched the discardPile's  top card
            before the challenged played the Wild 4 Draw card.
            Challenged is the player who played the Wild 4 Draw card.
         */

        /* function check's the challenged hand against the discardPile's top card BEFORE the challenged played
        * the Wild 4 Draw card. If the hand contains any color of the same COLOR as the discardPile's top card,
        * function returns true (& challenged will have to draw 4 cards).
        * If the challenged does NOT have any card with the same COLOR as the discardPile's top card,
        * the function will return false (& the challenger will have to draw 6 cards).
        * */

        // TODO: implement the challenge function
        return false;
    }

    private boolean validateMove(UNOCard playedCard, Player currentPlayer){
        /* function verifies if the played card is a valid move against the game rules
         */
        boolean result = false;
        UNOCard discardPileCard = discardPile.pop();
        if (playedCard.isWild() || playedCard.isWildDraw4()){
            /* if the played card is a wild card, then there's nothing to check except
            to prompt the player to what color they would like to change the game play to */

            result = true; // SRS - FR2.3 complete
            UNOCard declareColorCard = discardPile.pop();
            if (!currentPlayer.isHuman()){
                //TODO: randomize the discard pile color declaration & set it to declareColorCard
            }
            else {
                //TODO: prompt the human player for what color they'd like to discard pile to be & set it to declareColorCard
            }
            discardPile.push(declareColorCard); // taking top card & changing it's color
        }
        else {
            // otherwise, it's not a special card that is played and we just need to check if either the type or color match
            if (validateCardColorsMatch(playedCard, discardPileCard) || validateCardTypesMatch(playedCard, discardPileCard)){
                result = true;
            }
        }
        return result;
    }

    private void addCardToDiscardPile(UNOCard card){
        discardPile.push(card);
    }

    public boolean playCard(UNOCard card, Player currentPlayer){
        /* the player-specified card is attempted to add to the discard pile.
        Function does the following:
            [1] Verifies if card is a valid play, if yes then [2], if not then returns false
            [2] Adds the card to the discardPile & returns true
         */
        if (validateMove(card, currentPlayer)){
            addCardToDiscardPile(card);
            return true;
        }
        else { return false; }
    }

    public UNOCard viewLastDiscardPileCard(){
        /* returns the UNOCard object of the last card placed in the discard pile
        which can be seen by everyone playing BUT does NOT remove the card from the pile
         */
        return discardPile.peek();
    }

    public UNOCard getLastDiscardPileCard() {
        /* removes and returns the UNOCard object of the last card placed in the discard pile
         */
        return discardPile.pop();
    }

    private void makeMove(Player currentPlayer){
        /*

        The following snippet of code has been commented out on April 4 by DK due it being out of
        scope for the first sprint. However, this lays down the groundwork for the logical operation
        of how the game will run. So, for future sprints, bits and pieces of this work will be dissected
        when deemed relevant.

        if (currentPlayer.isHuman()) {
            // if the current player is human -- then user gets to choose what they play
            boolean invalidSelection = true;
            while (invalidSelection) {
                System.out.println("What would you like to do?");
                System.out.println("    [0] Exit");
                System.out.println("    [1] Show my hand");
                System.out.println("    [2] Get last card played");
                System.out.println("    [3] Play a card");
                System.out.println("    [4] Declare UNO");
                System.out.println("    [5] Pick up a card");
                System.out.println("Enter # from above list): ");
                int userSelection = reader.nextInt();
                switch (userSelection) {
                    case 0:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    case 1:
                        currentPlayer.myHand().printHand();
                        break;
                    case 2:
                        System.out.println("Discard Pile Card: " + discardPile.peek().toString());
                        break;
                    case 3:
                        Boolean invalidSelection2 = true;
                        while (invalidSelection2) {
                            System.out.println("What card would you like to play?");
                            Vector<UNOCard> cards = currentPlayer.myHand().getUnoCardsList();
                            System.out.println("    [0] Exit");
                            for (int q = 0; q < cards.size(); q++) {
                                System.out.println("    [" + (q + 1) + "] " + cards.elementAt(q).toString());
                            }
                            System.out.println("Enter # from above list): ");
                            int userSelection2 = reader.nextInt();
                            if (userSelection2 == 0){
                                System.out.println("Goodbye!");
                                System.exit(0);
                            }
                            UNOCard selectedCard = cards.elementAt(userSelection2-1);
                            if (playCard(selectedCard, currentPlayer)){
                                invalidSelection2 = false;
                                currentPlayer.myHand().removeUNOCard(selectedCard);
                            }
                            else {
                                System.out.println("Invalid selection. Try again.");
                            }
                            // TODO: Need to implement logic in this while loop if user changed their mind on playing card (& would like to draw a card instead, etc. - exit to the first while loop)
                        }
                        invalidSelection = false;
                        break;
                    case 4:
                        System.out.println(currentPlayer.callUNO());
                        invalidSelection = false;
                        break;
                    case 5:
                        UNOCard card = deck.deal();
                        System.out.println("You picked up: " + card.toString());
                        currentPlayer.addCardtoHand(card);
                        break;
                    default:
                        System.out.println("Invalid selection.");
                }
            }
        }
        else {
            AIMakeMove(currentPlayer);
        }
        */
    }

    private void AIMakeMove(Player currentPlayer){
        // TODO: Implement logic as to how the AI will decide to pick their card

    }

    public void play(){
        /* the main logical portion of the game which controls the order of moves
         */

        /*
        The following snippet of code has been commented out on April 4 by DK due it being out of
        scope for the first sprint. However, this lays down the groundwork for the logical operation
        of how the game will run. So, for future sprints, bits and pieces of this work will be dissected
        when deemed relevant.

        System.out.println("Starting the game!");

        boolean gameInProgress = true;
        boolean skipOccurred = false;
        while (gameInProgress) {
            for (int i = 0; i < players.size(); i++) {
                Player currentPlayer = players.elementAt(i);
                System.out.println("Discard Pile Card: " + discardPile.peek().toString());
                if (discardPile.peek().isWildDraw4()){
                    // if the previous player played a Wild Four card, the current player has to draw 4 cards from the deck
                    // and miss his or her turn
                    for (int j = 0; j < 4; j++) {
                        currentPlayer.addCardtoHand(deck.deal());
                    }
                }
                else if (discardPile.peek().isSkip()){
                    // if the previous player played a Skip card, the current player misses a turn
                    if (!skipOccurred) {
                        skipOccurred = true;
                    }
                    else {
                        makeMove(currentPlayer);
                    }
                }
                else if (discardPile.peek().isDraw2()){
                    // if the previous player played a Draw Two card, the current player has to draw 2 cards from the deck
                    // and miss his or her turn
                    for (int j = 0; j < 2; j++) {
                        currentPlayer.addCardtoHand(deck.deal());
                    }
                }
                else if (!discardPile.peek().isSkip())
                {
                    makeMove(currentPlayer);
                }
                if (currentPlayer.myHand().isEmpty()){
                    gameInProgress = false;
                }
            }
        }
        System.out.println("We have a winner!");
        */
    }
}
