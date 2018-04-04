package code.UNOClasses;
/* The following class, code.UNOClasses.Game, is responsible for uniting all classes for the unified,
 * coordinated functionality of what is expected from the game, UNO. */

import code.UNOClasses.Card.UNOCard;

import java.util.*;     // added for Vector/Stack type

public class Game {
    private Vector<Player> players; /* TODO: need to decide how player order is to be decided and kept track of */
    private Stack<UNOCard> discardPile;

    Deck deck = new Deck();


    public void initialize(){
        /* the caller function from main() which will be responsible for initializing the game instance, by calling:
        *   -   Prompting the user for the number of AI players they would like to play against
        *   -   Randomizing player order for the total number of players
        *   -   Shuffle card deck before dealing, maybe twice? (deck.shuffleDeck();)
        *   -   Deal cards to each player out of the total number of players
        *   etc.
        *   */
    	Scanner reader = new Scanner(System.in);
    	System.out.println("Enter the number of AI players you would like to play against: ");
    	int aIPlayerCount = reader.nextInt();
    	if(aIPlayerCount >14) {
    		System.out.println("Maximum number of AI players allowed to play against one human player is 14. ");
    		System.exit(0);
    	}
    	reader.close();
    	dealHand(aIPlayerCount);
        //initializeDiscardPile();
        // TODO: implement the initialize() class LAST (once all classes have been structured)
    }
    /*
     * @author Pranjali Mishra
     * This function deals 7 cards each to the AI players and human player
     * returns collection of players with dealt hands
     */
    public Vector<Player> dealHand(int playerCount) {
    	players= new Vector<Player>();
    	Deck deck = new Deck();
    	deck.shuffleDeck();
    	for (int j=0; j<7; j++) {    		
    	if (j==0) {
	    	for (int i=0; i <playerCount; i++){
		    	Player player = new Player(false);
		    	UNOCard uc =deck.deal();		    	
		    	player.addCardtoHand(uc);
		    	players.add(player);    	
	    		}
	    	//Creating Human Player
	    	{
	    		Player player = new Player(true);
		    	UNOCard uc =deck.deal();
		    	player.addCardtoHand(uc);
		    	players.add(player);
	    	}
    		}
    	else {
    		int playerIndex=0;
    		for(Player player :players) {    			
    			UNOCard uc =deck.deal();
    	    	player.addCardtoHand(uc);
    	    	players.set(playerIndex,player);
    	    	playerIndex++;
    			}    		
    		}
    	}
    	System.out.println("Remaining cards in deck " + deck.deckTotal());
    	System.out.println("Total Number of players including one human is :"+players.size());
    	System.out.println("Cards Assigned to each player :"+players);
    	return players;
    }
    

    private void initializeDiscardPile(){
        /* initializes the Discard Pile AFTER players have been dealt their hand
          * Obtains the top card from the deck & places
          * it on top of the discardPile
          * */
        UNOCard topCard = deck.deal();
        discardPile.push(topCard);

        // TODO: if topCard = Wild Draw Four, draw again
    }

    public UNOCard drawCard(){
        /* returns an UNOCard from the top of the deck &
        * removes that card from the deck*/

        UNOCard returnCard = deck.deal();
        return returnCard;
    }

    private boolean validateMove(UNOCard card){
        /* function verifies if the played card is a valid move against the game rules
         */
        boolean result = false;

        // TODO: implement logic for verifying if the move is valid

        return result;
    }

    private void addCardToDiscardPile(UNOCard card){
        discardPile.push(card);
    }

    public boolean playCard(UNOCard card){
        /* the player-specified card is attempted to add to the discard pile.
        Function does the following:
            [1] Verifies if card is a valid play, if yes then [2], if not then returns false
            [2] Adds the card to the discardPile & returns true
         */
        if (validateMove(card)){
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
    
    public static void main(String args[]) {
    	
    	Game gameObj= new Game();
    	gameObj.initialize();
    	
    }
}
