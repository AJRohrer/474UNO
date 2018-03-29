package code.UNOClasses;
/* The following class, code.UNOClasses.Game, is responsible for uniting all classes for the unified,
 * coordinated functionality of what is expected from the game, UNO. */

import code.UNOClasses.Card.UNOCard;

import java.util.*;     // added for Vector type

public class Game {
    private Vector<Player> Players; /* TODO: need to decide how player order is to be decided and kept track of */
    private Vector<UNOCard> discardPile;
    private Vector<Player> players;
    Deck deck = new Deck();

    public void initialize(){
        /* the caller function from main() which will be responsible for initializing the game instance, by calling:
        *   -   Prompting the user for the number of AI players they would like to play against
        *   -   Randomizing player order for the total number of players
        *   -   Initialize card deck
        *   -   Deal cards to each player out of the total number of players
        *   etc.
        *   */


        initializeDiscardPile();

        // TODO: implement the initialize() class LAST (once all classes have been structured)
    }

    private void initializeDiscardPile(){
        /* initializes the Discard Pile AFTER players have been dealt their hand
          * Obtains the top card (last in vector) from the deck & places
          * it in discardPile
          * */
        UNOCard topCard = deck.deal();
        discardPile.add(topCard);
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
        discardPile.add(card);
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

    public UNOCard getLastDiscardPileCard(){
        /* returns the UNOCard object of the last card placed in the discard pile
        which can be seen by everyone playing BUT does NOT remove the card from the pile
         */
        return discardPile.get(discardPile.size()-1);
    }




}
