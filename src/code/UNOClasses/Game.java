package code.UNOClasses;
/* The following class, code.UNOClasses.Game, is responsible for uniting all classes for the unified,
 * coordinated functionality of what is expected from the game, UNO. */

import code.UNOClasses.Card.UNOCard;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Game {
    private Vector<Player> players; /* TODO: need to decide how player order is to be decided and kept track of */
    private Stack<UNOCard> discardPile;
    Scanner reader;
    Boolean GameOver = false;

    Deck deck;
    int aIPlayerCount = 0;

    public void initialize(){
        /* the caller function from main() which will be responsible for initializing the game instance, by calling:
        *   -   Prompting the user for the number of AI players they would like to play against
        *   -   Randomizing player order for the total number of players
        *   -   Shuffle card deck before dealing, maybe twice? (deck.shuffleDeck();)
        *   -   Deal cards to each player out of the total number of players
        *   etc.
        *   */

        // SRS - FR1.1 -- complete
    	reader = new Scanner(System.in);
    	System.out.println("Enter the number of AI players you would like to play against: ");
    	aIPlayerCount = reader.nextInt();
    	if(aIPlayerCount > 9) {
			System.out.println("Error!");
    		System.out.println("Maximum number of AI players allowed to play against one human player is 9.");
    		System.exit(0);
    	}

    	dealHand(); // SRS - FR1.2 & FR1.3 -- complete
    	initializeDiscardPile(); // SRS - FR1.4 & FR1.5 & FR1.6 -- complete

        // initializeDiscardPile() is to be considered to be the first move by the first player, player #0 in the players vector

    }
    /*
     * @author Pranjali Mishra @editor Darya Kiktenko
     * This function deals 7 cards each to the AI players and human player
     * returns collection of players with dealt hands
     */
    private void dealHand() { // SRS - FR1.2 & FR1.3 implementation
    	players= new Vector<Player>();
    	deck = new Deck();
    	discardPile = new Stack<UNOCard>();
    	deck.shuffleDeck();
    	for (int j=0; j<7; j++) {    		
    		if (j==0) {
    		// initializing AI players and giving them at least one card
	    		for (int i=0; i < aIPlayerCount; i++){
					Player player = new Player(false);
					player.addCardtoHand(deck.deal());
					players.add(player);
	    		}
	    	//Creating Human Player
	    		{
	    			Player player = new Player(true);
		    		player.addCardtoHand(deck.deal());
		    		players.add(player);
	    		}
    		}
    		else {
    			for(Player player :players) {
					player.addCardtoHand(deck.deal());
				}
    		}
    	}
    	Collections.shuffle(players); // SRS - FR1.3 -- complete
    	int k = 0;
    	for (Player player : players) { // TODO: What is the purpose of knowing one's position?
    		player.setPosition(k);
			k++;
		}
    	System.out.println("Remaining cards in deck " + deck.deckTotal());
    	System.out.println("Total Number of players including one human is :" + players.size());
		System.out.println("Cards Assigned to each player: ");
		for (int i =0; i < players.size(); i++){
			System.out.println(players.elementAt(i).toString());
		}
    }

    private void initializeDiscardPile(){ // SRS - FR1.4 & FR1.5 & FR1.6 implementation
        /* initializes the Discard Pile AFTER players have been dealt their hand
          * Obtains the top card from the deck & places
          * it on top of the discardPile
          * */
        UNOCard topCard = null;
        boolean validCard = false;
        while (!validCard){
            topCard = deck.deal();
            System.out.println("Discard Pile Card: " + topCard.toString());
            if (topCard.isWildDraw4()) {
                System.out.println("Card is a \"Wild Draw Four\" - adding back to the Deck.");
                deck.addCard(topCard);
            }
            else {
                discardPile.add(topCard);
                validCard = true;
            }
        }
    }

    public UNOCard drawCard(){
        /* returns an UNOCard from the top of the deck &
        * removes that card from the deck*/

        UNOCard returnCard = deck.deal();
        return returnCard;
    }

    private boolean validateMove(UNOCard card, Player currentPlayer){
        /* function verifies if the played card is a valid move against the game rules
         */
        boolean result = false;

        if (card.isWild() || card.isWildDraw4()){
            result = true; // SRS - FR2.3 complete
            UNOCard declareColorCard = discardPile.pop();
            if (!currentPlayer.isHuman()){
                //TODO: randomize the discard pile color declaration
            }
            else {
                //TODO: prompt the human player for what color they'd like to discard pile to be

            }
            discardPile.push(declareColorCard);
        }

        // TODO: implement logic for verifying if the move is valid

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
