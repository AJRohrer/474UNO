package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player p = new Player(true,"Player1");

    @Test
    void isHuman() {
        assertEquals(p.isHuman(), true);
    }

    @Test
    void testConstructor() {
        // Initially the hand is empty
        //Set the name for the player
        assertEquals("Player1",p.getName());
        assertTrue(p.myHand().isEmpty());
    }

    @Test
    void addCardtoHand() {
        //TODO: figure out how we want to create a hand to test this.
        //Adding cards to the hand
        UNOCard testcard1 = new UNOCard(CardType.FIVE,UNOColor.GREEN);
        p.addCardtoHand(testcard1);
        UNOCard uchand = p.myHand().getUnoCardsList().elementAt(0);
        assertEquals(uchand.get_color(), testcard1.get_color());
        assertEquals(uchand.get_type(), testcard1.get_type());

        UNOCard testcard2 = new UNOCard(CardType.THREE,UNOColor.YELLOW);
        p.gainCard(testcard2);
        UNOCard uchand1 = p.myHand().getUnoCardsList().elementAt(1);
        assertEquals(uchand1.get_color(), testcard2.get_color());
        assertEquals(uchand1.get_type(), testcard2.get_type());


    }

    @Test
    void discardCard() {
        //Add card
        UNOCard testaddcard = new UNOCard(CardType.FIVE,UNOColor.GREEN);
        p.addCardtoHand(testaddcard);
        UNOCard uchand = p.myHand().getUnoCardsList().elementAt(0);
        assertEquals(uchand.get_color(), testaddcard.get_color());
        assertEquals(uchand.get_type(), testaddcard.get_type());

        // Remove card by checking empty
        p.discardCard(uchand);
        assertTrue(p.myHand().isEmpty());
        //TODO: figure out how we want to create a hand to test this.
    }

    @Test
    void callUNO() {
        assertEquals(p.callUNO(),"UNO!");
    }

    @Test
    void myHand() {
        //get total no.of cards from the
        assertEquals(2, p.myHand().handTotal());
        //TODO: figure out how we want to create a hand to test this.
    }
}