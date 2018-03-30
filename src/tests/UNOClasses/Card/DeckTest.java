package tests.UNOClasses.Card;


import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Deck;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Vector;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @AfterEach
    void tearDown() {
        deck.clearDeck();
    }

    @Test
    void deckContains108Cards() {
        assertEquals(108, deck.deckTotal());
    }

    @Test
    void createDeck() {
        Deck test = new Deck();
        assertEquals(test.toString(), deck.toString());
    }

    @Test
    void shuffleDeck() {
        Deck test = new Deck();
        test.shuffleDeck();
        assertNotEquals(test.toString(), deck.toString());
    }

    @Test
    void deckTotal() {
        assertEquals(108, deck.deckTotal());
        deck.deal();
        deck.deal();
        deck.deal();
        deck.deal();
        assertEquals(104, deck.deckTotal());
    }

    @Test
    void deal() {
        UNOCard test = deck.deal();
        assertTrue(test.isWildDraw4());
    }

    //@Test
    //void toString() {
   // }

    @Test
    void isEmpty() {
        for (int i=0; i < 107; i++) {
            deck.deal();
        }
        assertFalse(deck.isEmpty());
        deck.deal();
        assertTrue(deck.isEmpty());
    }

    /*@Test
    void shuffleDiscardPile() {
    }*/
}