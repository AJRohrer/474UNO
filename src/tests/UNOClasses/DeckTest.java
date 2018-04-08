package tests.UNOClasses;


import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.Deck;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

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

    @Test
    void clearDeck() {
        deck.clearDeck();
        assertEquals(0, deck.deckTotal());
    }

    @Test
    void shuffleDiscardPile() {
        deck.clearDeck();
        Stack<UNOCard> discardPile = new Stack<>();
        discardPile.add(new UNOCard(CardType.ZERO,UNOColor.RED));
        discardPile.add(new UNOCard(CardType.ZERO,UNOColor.YELLOW));
        discardPile.add(new UNOCard(CardType.ZERO,UNOColor.BLUE));
        discardPile.add(new UNOCard(CardType.ZERO,UNOColor.GREEN));
        discardPile.add(new UNOCard(CardType.ONE,UNOColor.RED));
        discardPile.add(new UNOCard(CardType.ONE,UNOColor.YELLOW));
        discardPile.add(new UNOCard(CardType.ONE,UNOColor.BLUE));
        discardPile.add(new UNOCard(CardType.ONE,UNOColor.GREEN));
        //System.out.println(discardPile);
        deck.shuffleDiscardPile(discardPile);
        assertEquals(8, deck.deckTotal());
        System.out.println(deck);
    }
}