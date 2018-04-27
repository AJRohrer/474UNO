package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player p = new Player(true);
    @Test
    void isHuman() {
        assertEquals(p.isHuman(), true);
    }

    @Test
    void addCardtoHand() {
        //TODO: figure out how we want to create a hand to test this.
        UNOCard testcard = new UNOCard(CardType.FIVE,UNOColor.GREEN);
        p.addCardtoHand(testcard);
        UNOCard uchand = p.myHand().getUnoCardsList().elementAt(0);
        assertEquals(uchand.get_color(), testcard.get_color());
        assertEquals(uchand.get_type(), testcard.get_type());
    }

    @Test
    void discardCard() {
        //TODO: figure out how we want to create a hand to test this.
    }

    @Test
    void callUNO() {
        assertEquals(p.callUNO(),"UNO!");
    }

    @Test
    void myHand() {
        //TODO: figure out how we want to create a hand to test this.
    }

    @Test
    public void testMakeMove1(){
        Player AIPlayer = new Player(false);
        Player playerBefore = new Player(true);
        Player playerAfter = new Player(true);
        Player playerAfterNext = new Player(true);

        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.FIVE,UNOColor.GREEN));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.REVERSE, UNOColor.YELLOW));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.WILD, UNOColor.YELLOW));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.ONE, UNOColor.RED));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.ONE, UNOColor.GREEN));

        for (int i = 0; i <= 6; i++){
            playerAfter.myHand().addUNOCard(null);
            if (i < 3){
                playerBefore.myHand().addUNOCard(null);
                playerAfterNext.myHand().addUNOCard(null);
            }
        }

        UNOCard playedCard = AIPlayer.makeMove(new UNOCard(CardType.EIGHT,UNOColor.BLUE), playerBefore.myHand().handTotal(), playerAfter.myHand().handTotal(), playerAfterNext.myHand().handTotal());
        assertEquals(CardType.WILD, playedCard.get_type());
        assertEquals(UNOColor.YELLOW, playedCard.get_color());
    }
}