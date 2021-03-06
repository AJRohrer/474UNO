package code.UNOClasses;


import code.UNOClasses.Card.UNOCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Player {
    private boolean isHuman; //returns true for the interfacing user, false for computer players (AI)
    private PlayerHand _hand = new PlayerHand();
    private int position;
    private String name;

    public Player(boolean isHumanPlayer) {
        this.isHuman = isHumanPlayer;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void addCardtoHand(UNOCard uc) {
        _hand.addUNOCard(uc);
    }

    public UNOCard discardCard(UNOCard uc) {
        _hand.removeUNOCard(uc);
        return uc;
    }


    //Somehow the game should check to see if a player has one card left and if they have emitted UNO! as a string
    //to determine if they should be penalized for not calling UNO!
    public String callUNO() {
        return "UNO!";
    }

    //expose the hand object so we can use hand methods such as getting the hand total and printing the hand.
    public PlayerHand myHand() {
        return _hand;
    }

    public String toString() {
        return "Human: " + this.isHuman + "\n" + "Position: " + this.position + "\n" + "Hand:\n" + this._hand.toString();
    }

    public void setPosition(int p) {
        this.position = p;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String chooseRandomName() {
        ArrayList<String> names = new ArrayList<>();
        Collections.addAll(names, "Liz", "Tyree", "Darya", "Andrew", "Pranjali", "Suchitra", "Roxeanne", "Bob", "Rocky",
                "Lisa", "Jesse", "Christopher", "Batman", "Sylvia", "Aldo", "Ethelene", "Tifany", "Beaulah", "Chantell", "Willard",
                "Marita", "Lavonda", "Calista", "Chelsey", "Cristi", "Delfina", "Tawny", "Shelly", "Chantel", "Delsie", "Valentine",
                "Bradford", "Sun", "Raphael", "Donatello", "Leonardo", "Michelangelo", "Splinter");

        Random rnd = new Random();
        int total = names.size();
        int index = rnd.nextInt(total);
        return names.get(index);

    }

    public UNOCard makeMove(UNOCard lastCardPlayed, int playerBeforeHandSize, int nextPlayerHandSize, int playerAfterNextHandSize) {
        /** The implementation of the AI automated player would
         * make a move based on a set of circumstances.
         *
         * Returns null if no card can be played & a card must be drawn
         *
         * @author Darya Kiktenko
         */

        /* First, figure out what cards are currently in our hand */

        boolean playableCardFound = false;
        Vector<UNOCard> myCards = myHand().getUnoCardsList();
        for (int i = 0; i < myCards.size(); i++) {
        }
        UNOCard cardToPlay = null; // the card we will return -- if null, a card must be drawn

        Integer WildFourIndex = -1;
        Integer WildCardIndex = -1;
        Integer SkipCardIndex = -1;
        Integer ReverseCardIndex = -1;
        Integer DrawTwoCardIndex = -1;

        Vector<Integer> colorMatchIndices = new Vector<Integer>();
        Vector<Integer> typeMatchIndices = new Vector<Integer>();

        for (int i = 0; i < myCards.size(); i++) {
            UNOCard cardAnalyzed = myCards.elementAt(i);
            if (cardAnalyzed.get_color() == lastCardPlayed.get_color()) {
                colorMatchIndices.add(i);
                playableCardFound = true;
            }

            if (cardAnalyzed.get_type() == lastCardPlayed.get_type()) {
                typeMatchIndices.add(i);
                playableCardFound = true;
            }

            if (cardAnalyzed.isWildDraw4()) {
                WildFourIndex = i;
                playableCardFound = true;
            } else if (cardAnalyzed.isWild()) {
                WildCardIndex = i;
                playableCardFound = true;
            } else if (cardAnalyzed.isSkip()) {
                SkipCardIndex = i;
                playableCardFound = true;
            } else if (cardAnalyzed.isReverse()) {
                ReverseCardIndex = i;
                playableCardFound = true;
            } else if (cardAnalyzed.isDraw2()) {
                DrawTwoCardIndex = i;
                playableCardFound = true;
            }

            /* if nothing else match, then the only option would be to draw a card
                and that is why playableCardFound boolean flag remains false */
        }

        if (playableCardFound) { // if no playable card was found, then why do any additional work - just return null
            /* here is where we analyze what possible moves can be made.
            First, we consider circumstances if a special card was last played,
            then, we apply logic if a number card was played (hence the if-else) */

            /* Playing special cards should be considered first before numbered cards.
            Priority is given that the "less special" cards should be played before hand,
            hence the if-else loop being ordered.

            Priority from least important to most important special cards:

                Skip Card
                Reverse Card
                Draw Two
                Wild Card
                Wild Draw 4

            Criteria for playing each special card:

            	                                                Wild Draw 4	 Wild Card	Skip Card	Reverse Card	Draw Two
                No color matched    	                            Y	        Y	        -	        -	            -
                Next player has less than 3 cards   	            Y	        -	        Y	        Y	            Y
                Player before you has less than 3 cards            	-	        -	        -	        N	            -
                Player after next has less than 3 cards     	    N	        -	        N	        -	            N
                No type matched                                     -           Y           -           -               -

             */

            boolean cardToPlayFound = true;

            if ((nextPlayerHandSize <= 3) &&
                    (playerAfterNextHandSize >= 3) &&
                    (SkipCardIndex != -1)) { // they actually have the Draw Two card

                cardToPlay = myCards.elementAt(SkipCardIndex);
            } else if ((nextPlayerHandSize <= 3) &&
                    (playerBeforeHandSize > 3) &&
                    (ReverseCardIndex != -1)) { // they actually have the Reverse card

                cardToPlay = myCards.elementAt(ReverseCardIndex);
            } else if ((nextPlayerHandSize <= 3) &&
                    (playerAfterNextHandSize >= 3) &&
                    (DrawTwoCardIndex != -1)) { // they actually have the Draw Two card

                cardToPlay = myCards.elementAt(DrawTwoCardIndex);
            } else if ((colorMatchIndices.size() == 0) && // no color match found
                    (typeMatchIndices.size() == 0) && // no type match found
                    (WildCardIndex != -1)) { // they actually have the Draw Two card

                cardToPlay = myCards.elementAt(WildCardIndex);
            } else if ((colorMatchIndices.size() == 0) && // mo color match found
                    (nextPlayerHandSize <= 3) &&
                    (playerAfterNextHandSize >= 3) &&
                    (WildFourIndex != -1)) { // they actually have the Wild Four card

                cardToPlay = myCards.elementAt(WildFourIndex);

            } else {
                // none of the criteria to play a special card was met ...

                int randomIndex = -1;
                int cardIndex = -1;
                if ((!colorMatchIndices.isEmpty()) &&
                        (!typeMatchIndices.isEmpty())) {

                    /* if a color match and a type match card is present, then we
                    "flip a coin" on which match to go off of (color or type) -
                    else, based on whichever match is found, we're going to randomize
                    a card pick from the available list of indices to pick the card we're
                    going to play
                     */
                    int randomChoice = randomNumberPick(2);
                    if (randomChoice == 0) {

                        randomIndex = randomNumberPick(colorMatchIndices.size());
                        cardIndex = colorMatchIndices.elementAt(randomIndex);
                        cardToPlay = myCards.elementAt(cardIndex);
                    } else {
                        randomIndex = randomNumberPick(typeMatchIndices.size());
                        cardIndex = typeMatchIndices.elementAt(randomIndex);
                        cardToPlay = myCards.elementAt(cardIndex);
                    }
                } else if (!colorMatchIndices.isEmpty()) {
                    randomIndex = randomNumberPick(colorMatchIndices.size());
                    cardIndex = colorMatchIndices.elementAt(randomIndex);

                    cardToPlay = myCards.elementAt(cardIndex);
                } else if (!typeMatchIndices.isEmpty()) {

                    randomIndex = randomNumberPick(typeMatchIndices.size());
                    cardIndex = typeMatchIndices.elementAt(randomIndex);

                    cardToPlay = myCards.elementAt(cardIndex);                }

            }

        }
        System.out.println("AI played: " + cardToPlay);
        return cardToPlay;
    }

    public int randomNumberPick(int max) {
        /** Function will randomize a number between 0 and the max boundry (exclusive)
         *
         * Example: call with max of 10, will produce a number between 0 and 10, including 0 as a possibility,
         * but NOT 10 (will produce 9 as max int)
         *
         * @author Darya Kiktenko
         */
        Random rand = new Random();
        return (rand.nextInt(max));
    }

}

