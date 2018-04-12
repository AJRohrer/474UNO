package code.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;

import java.util.Vector;

public class ComputerPlayer extends Player {
    public ComputerPlayer() {
        super(false);
    }

    public UNOCard makeMove(UNOCard lastCardPlayed, Vector<Player> players) {
        /** The implementation of the AI automated player would
         * make a move based on a set of circumstances.
         *
         * Returns null if no card can be played & a card must be drawn
         *
         * @author Darya Kiktenko
         */

        boolean playableCardFound = true;
        Vector<UNOCard> myCards = myHand().getUnoCardsList();
        UNOCard cardToPlay = null;

        Integer WildFourIndex = -1;
        Integer WildCardIndex = -1;
        Integer SkipCardIndex = -1;
        Integer ReverseCardIndex = -1;
        Integer DrawTwoCardIndex = -1;

        UNOColor lastColor = lastCardPlayed.get_color();
        CardType lastType = lastCardPlayed.get_type();
        Vector<Integer> colorMatchIndices = new Vector<Integer>();
        Vector<Integer> typeMatchIndices = new Vector<Integer>();

        for (int i = 0; i < myCards.size(); i++) {
            UNOCard cardAnalyzed = myCards.elementAt(i);
            if (cardAnalyzed.get_color() == lastColor) {
                colorMatchIndices.add(i);
                playableCardFound = true;
            }

            if (cardAnalyzed.get_type() == lastType) {
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

        if (playableCardFound){ // if no playable card was found, then why do any additional work - just return null

            /* here is where we analyze what possible moves can be made.
            First, we consider circumstances if a special card was last played,
            then, we apply logic if a number card was played (hence the if-else) */

            /* checking if UNO has been previously called & we want to know how many cards the person after
             * us has and the person after them has, so we know circumstances if we play a special card
              * like skip or draw */

            boolean UNOCalled = false;
            int nextPlayerHandSize = -1;
            int playerAfterNextHandSize = -1;
            for (int i = 0; i < players.size(); i++){
                if (players.elementAt(i).UNOCalled){
                    UNOCalled = true;
                }
                if (players.elementAt(i).getPosition() == (this.getPosition()+1)){
                    nextPlayerHandSize = players.elementAt(i).myHand().handTotal();
                }
                if (players.elementAt(i).getPosition() == (this.getPosition()+2)){
                    playerAfterNextHandSize = players.elementAt(i).myHand().handTotal();
                }
            }

            if ((colorMatchIndices.size() == 0) && (nextPlayerHandSize < 3) && (WildFourIndex != -1)){
                // no colors match & we have a wild draw 4 card, we want to place that so next player
                cardToPlay = myCards.elementAt(WildCardIndex);
                // TODO: Remove card from my hand
            }


            if (!lastCardPlayed.isNumberCard()){
                if (lastCardPlayed.isWildDraw4()){
                    // TODO: What should AI do if the last card played is a Wild Draw 4?
                    // TODO: How do we determine what color the player who played the card chose?
                    // TODO: Does PlayerTurn control that the person after the player who played the card is skipped or do we have to implement in Player class?
                }
                else if (lastCardPlayed.isWild()){
                    // TODO: What should AI do if the last card played is a Wild card?
                    // TODO: How do we determine what color the player who played the card chose?
                }
                else if (lastCardPlayed.isDraw2()){
                    // TODO: What should AI do if the last card played is a a Draw 2 card?
                }
                else if (lastCardPlayed.isReverse()){
                    // TODO: What should AI do if the last card played is a Reverse card?
                }
                else if (lastCardPlayed.isSkip()){
                    // TODO: What should AI do if the last card played is a Skip card?
                }
            }
            else {
                // TODO: What should AI do if the last card played is a numbered card?
            }
        }

        return cardToPlay;
    }
}
