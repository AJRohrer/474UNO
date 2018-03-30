package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

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
}