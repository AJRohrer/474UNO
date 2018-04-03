package code.UNOClasses;
/* The following class, code.UNOClasses.Game, is responsible for uniting all classes for the unified,
 * coordinated functionality of what is expected from the game, UNO. */

import code.UNOClasses.Card.UNOCard;
import java.util.Random;

import java.util.*;     // added for Vector/Stack type

public class Game {
    private Vector<Player> players; /* TODO: need to decide how player order is to be decided and kept track of */
    private Stack<UNOCard> discardPile;

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
    	Scanner reader = new Scanner(System.in);
    	System.out.println("Enter the number of AI players you would like to play against: ");
    	aIPlayerCount = reader.nextInt();
    	if(aIPlayerCount > 9) {
			System.out.println("Error!");
    		System.out.println("Maximum number of AI players allowed to play against one human player is 9.");
    		System.exit(0);
    	}
    	reader.close();

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
            } else {
                discardPile.push(topCard);
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

}
