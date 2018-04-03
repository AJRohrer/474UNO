package code.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import java.util.*;
import java.util.Collections;
import java.util.stream.Collectors;

public class Deck {
    //create empty Stack of Uno cards named deck
    private Stack<UNOCard> deck;

    //the number of cards 1-9 and action cards (skip, draw two, reverse) per color
    public static final int numberAndActionCards = 2;

    //the number of zero cards per color
    public static final int zeroCards = 1;

    //the number of regular wild cards
    public static final int regularWildCards = 4;

    //the number of wild draw four cards
    public static final int drawFourWildCards = 4;

    //Constructor for a new, full, shuffled UNO deck
    public Deck() {
        this.deck = new Stack<UNOCard>();
        createDeck();
        //shuffleDeck();
    }

    //Create deck is used to fill the deck with 108 UNO cards, the same set every time
    public void createDeck() {
        //create number(1-9) and action cards, two of each for each color
        for (int i=1; i<=12; i++) {
            for (int j=0; j<numberAndActionCards; j++) {
                deck.add(new UNOCard(CardType.values()[i],UNOColor.RED));
                deck.add(new UNOCard(CardType.values()[i],UNOColor.YELLOW));
                deck.add(new UNOCard(CardType.values()[i],UNOColor.BLUE));
                deck.add(new UNOCard(CardType.values()[i],UNOColor.GREEN));
            }
        }

        //create one zero card for each color
        for (int j=0; j<zeroCards; j++) {
            deck.add(new UNOCard(CardType.ZERO,UNOColor.RED));
            deck.add(new UNOCard(CardType.ZERO,UNOColor.YELLOW));
            deck.add(new UNOCard(CardType.ZERO,UNOColor.BLUE));
            deck.add(new UNOCard(CardType.ZERO,UNOColor.GREEN));
        }

        //create four regular wild cards
        for (int i=0; i<regularWildCards; i++) {
            deck.add(new UNOCard(CardType.WILD,UNOColor.WILD));
        }

        //create four draw four wild cards
        for (int i=0; i<drawFourWildCards; i++) {
            deck.add(new UNOCard(CardType.WILDDRAW4,UNOColor.WILD));
        }
    }

    //shuffles the existing cards in the deck
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    //returns the number of cards left in the deck
    public int deckTotal() { return deck.size(); }

    //takes the "top" card of the deck off and returns it, used in dealing and drawing
    public UNOCard deal() {
        if (!deck.isEmpty()) {
            return deck.pop();
        }
        else {
            return null;
        }
    }

    //overrides toString to create a print method for the deck
    public String toString() {
        return deck.stream().map(UNOCard::toString).collect(Collectors.joining(", "));
    }

    //returns true if deck is empty
    public boolean isEmpty() { return this.deck.empty(); }

    //takes a vector of cards (any size) and reintroduces them to the deck, then shuffles the deck
    public void shuffleDiscardPile(Stack<UNOCard> cards) {
        this.deck.addAll(cards);
        shuffleDeck();
    }

    //removes all cards from deck
    public void clearDeck() { this.deck.removeAllElements(); }

    //adds a specific card back to the deck (like the Wild4 if drawn in FR1.5)
    public void addCard(UNOCard card){
        deck.add(0, card);
        //deck.add(card);
    }


    //print out the deck and deck total in the terminal, then shuffle and print out the deck and total again
    //create mock player hand with deal function and print hand and new deck total
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println("Deck in order\n" + deck);
        System.out.println("Deck total: " + deck.deckTotal());
        deck.shuffleDeck();
        deck.shuffleDeck();
        System.out.println("Deck shuffled\n" + deck);
        System.out.println("Deck total: " + deck.deckTotal());
        Stack test = new Stack();
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        test.push(deck.deal());
        System.out.println("Mock player hand\n" + test);
        System.out.println("Deck without player cards\n" + deck);
        System.out.println("New deck total: " + deck.deckTotal());
    }
}