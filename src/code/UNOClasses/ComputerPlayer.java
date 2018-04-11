package code.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;

import java.util.Vector;

public class ComputerPlayer extends Player {
    public ComputerPlayer() {
        super(false);
    }

    public UNOCard makeMove(UNOCard lastCardPlayed) {
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

            if (!lastCardPlayed.isNumberCard()){
                if (lastCardPlayed.isWildDraw4()){
                    // TODO: What should AI do if the last card played is a Wild Draw 4?
                }
                else if (lastCardPlayed.isWild()){
                    // TODO: What should AI do if the last card played is a Wild card?
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
