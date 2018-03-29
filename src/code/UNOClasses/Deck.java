package code.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import java.util.Vector;
import java.util.Collections;
import java.util.stream.Collectors;

public class Deck {
    private Vector<UNOCard> deck;

    public static final int numberAndActionCards = 2;

    public static final int zeroCards = 1;

    public static final int regularWildCards = 4;

    public static final int drawFourWildCards = 4;

    public Deck() {
        this.deck = new Vector<UNOCard>();
        createDeck();
        //shuffleDeck();
    }

    public void createDeck() {
        for (int i=1; i<=12; i++) {
            for (int j=0; j<numberAndActionCards; j++) {
                deck.add(new UNOCard(CardType.values()[i],UNOColor.RED));
                deck.add(new UNOCard(CardType.values()[i],UNOColor.YELLOW));
                deck.add(new UNOCard(CardType.values()[i],UNOColor.BLUE));
                deck.add(new UNOCard(CardType.values()[i],UNOColor.GREEN));
            }
        }
        // There are fewer "0" cards than other numbers.
        for (int j=0; j<zeroCards; j++) {
            deck.add(new UNOCard(CardType.ZERO,UNOColor.RED));
            deck.add(new UNOCard(CardType.ZERO,UNOColor.YELLOW));
            deck.add(new UNOCard(CardType.ZERO,UNOColor.BLUE));
            deck.add(new UNOCard(CardType.ZERO,UNOColor.GREEN));
        }

        for (int i=0; i<regularWildCards; i++) {
            deck.add(new UNOCard(CardType.WILD,UNOColor.WILD));
        }
        for (int i=0; i<drawFourWildCards; i++) {
            deck.add(new UNOCard(CardType.WILDDRAW4,UNOColor.WILD));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    public int deckTotal() {
        return deck.size();
    }

    public UNOCard deal() {
        return deck.remove(0);
    }

    public String toString() {
        return deck.stream().map(b -> b.toString()).collect(Collectors.joining("\n"));
    }

    public boolean isEmpty() {
        return this.deck.isEmpty();
    }

    public void shuffleDiscardPile(Vector<UNOCard> cards) {
        this.deck.addAll(cards);
        shuffleDeck();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);
        System.out.println(deck.deckTotal());
        //deck.shuffleDeck();
        //System.out.println(deck);
        Vector testdeck = new Vector();
        testdeck.add(deck.deal());
        System.out.println(testdeck);
        System.out.println(deck);
        System.out.println(deck.deckTotal());
    }
}