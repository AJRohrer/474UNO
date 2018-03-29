package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UNOCardTest {

    UNOCard c = new UNOCard(CardType.REVERSE, UNOColor.BLUE);
    @Test
    void get_type() {

        assertEquals(c.get_type(), CardType.REVERSE);
    }

    @Test
    void get_color() {
        assertEquals(c.get_color(), UNOColor.BLUE);
    }

    @Test
    void isWild() {
        assertEquals(c.isWild(), false);
    }

    @Test
    void isWildDraw4() {
        assertEquals(c.isWildDraw4(), false);
    }

    @Test
    void isSkip() {
        assertEquals(c.isSkip(), false);
    }

    @Test
    void isDraw2() {
        assertEquals(c.isDraw2(), false);
    }

    @Test
    void isReverse() {
        assertEquals(c.isReverse(), true);
    }

    @Test
    void isNumberCard(){
        assertEquals(c.isNumberCard(), false);
        UNOCard numbercard = new UNOCard(CardType.FIVE, UNOColor.GREEN);
        assertEquals(numbercard.isNumberCard(), true);
    }

}