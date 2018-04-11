package code.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;

import java.util.Vector;

public class ComputerPlayer extends Player {
    public ComputerPlayer() {
        super(false);
    }

    public UNOCard makeMove(UNOCard lastCardPlayed){
        /** The implementation of the AI automated player would
         * make a move based on a set of circumstances.
         *
         * @author Darya Kiktenko
         */

        boolean playableCardFound = true;
        Vector<UNOCard> myCards = myHand().getUnoCardsList();

        Integer WildFourIndex = -1;
        Integer WildCardIndex = -1;
        Integer SkipCardIndex = -1;
        Integer ReverseCardIndex = -1;
        Integer DrawTwoCardIndex = -1;

        if (lastCardPlayed.isNumberCard()) {
            /* If the last card played is a numbered card  */

            UNOColor lastColor = lastCardPlayed.get_color();
            CardType lastType = lastCardPlayed.get_type();
            Vector<Integer> colorMatchIndices = new Vector<Integer>();
            Vector<Integer> typeMatchIndices = new Vector<Integer>();

            /* analyzing what cards are in the hand */

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

                if (cardAnalyzed.isWildDraw4()){
                    WildFourIndex = i;
                    playableCardFound = true;
                }
                else if (cardAnalyzed.isWild()){
                    WildCardIndex = i;
                    playableCardFound = true;
                }
                else if (cardAnalyzed.isSkip()){
                    SkipCardIndex = i;
                    playableCardFound = true;
                }
                else if (cardAnalyzed.isReverse()){
                    ReverseCardIndex = i;
                    playableCardFound = true;
                }
                else if (cardAnalyzed.isDraw2()){
                    DrawTwoCardIndex = i;
                    playableCardFound = true;
                }

                // if nothing else match, then the only option would be to draw a card


            }
        }
        else {
            /* TODO: Need to figure out the logic if the last card played is a special card
            */
        }
    }
}
