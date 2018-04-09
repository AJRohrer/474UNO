package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Stack;
import java.util.Vector;


import code.UNOClasses.Game;
import code.UNOClasses.Player;

public class GameTest {

	@Test
	public void testDrawCard(){
		Game testGame = new Game();
		int fullDeckSize = testGame.deck.deckTotal();
		testGame.drawCard();
		assertEquals(fullDeckSize-1, testGame.deck.deckTotal());
	}

	@Test
	public void testCardColorCheck(){
		Game testGame = new Game();
		UNOCard card1 = new UNOCard(CardType.ZERO,UNOColor.BLUE);
		UNOCard card2 = new UNOCard(CardType.FIVE,UNOColor.BLUE);
		assertTrue(testGame.validateCardColorsMatch(card1,card2));
	}

	@Test
	public void testCardTypeCheck(){
		Game testGame = new Game();
		UNOCard card1 = new UNOCard(CardType.EIGHT,UNOColor.YELLOW);
		UNOCard card2 = new UNOCard(CardType.EIGHT,UNOColor.BLUE);
		assertTrue(testGame.validateCardTypesMatch(card1,card2));
	}

	@Test
	public void testShufflePlayerOrder(){
		Game testGame = new Game();
		Vector<Player> testVector = new Vector<Player>();
		for (int i = 0; i < 4; i++) {
			testVector.add(new Player(false));
		}
		testVector.add(new Player(true));
		testGame.shufflePlayerOrder(testVector);
		assertFalse(testVector.elementAt(4).isHuman());
	}

	@Test
	public void testGetTotalNumberOfPlayers(){
		// TODO: testGetTotalNumberOfPlayers() to be written when updated to incorporate UI
	}

	@Test
	public void testSetTotalNumberOfPlayers(){
		Game testGame = new Game();
		testGame.setTotalNumberOfPlayers(5);
		assertEquals(5, testGame.totalNumberOfPlayers);
	}

	@Test
	public void testInitiatePlayersVector(){
		Game testGame = new Game();
		Vector<Player> testVector = new Vector<Player>();
		testVector.add(new Player(true));
		assertEquals(3, testGame.initiatePlayersVector(3).size());
	}

	@Test
	public void testDealHand(){
		Game testGame = new Game();
		Vector<Player> testVector = new Vector<Player>();
		testVector = testGame.initiatePlayersVector(3);
		Deck testDeck = new Deck();
		testVector = testGame.dealHand(testDeck, testVector);
		assertEquals(7, testVector.elementAt(1).myHand().handTotal());

	}

	@Test
	public void testInitializeDiscardPile(){
		Game testGame = new Game();
		Deck testDeck = new Deck();
		Stack<UNOCard> testStack = new Stack<UNOCard>();
		assertFalse(testGame.initializeDiscardPile(testDeck).empty());
	}

}
