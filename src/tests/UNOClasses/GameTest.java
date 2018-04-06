package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Vector;


import code.UNOClasses.Game;
import code.UNOClasses.Player;

public class GameTest {

	@Test
	public void testDrawCard(){
		Game testGame = new Game();
		assertEquals(testGame.deck.deal(), testGame.drawCard());
	}

	@Test
	public void testCardColorCheck(){
		Game testGame = new Game();
		UNOCard card1 = new UNOCard(CardType.ZERO,UNOColor.BLUE);
		UNOCard card2 = new UNOCard(CardType.FIVE,UNOColor.BLUE);
		testGame.validateCardColorsMatch(card1,card2);
	}

	@Test void testCardTypeCheck(){
		Game testGame = new Game();
		UNOCard card1 = new UNOCard(CardType.EIGHT,UNOColor.YELLOW);
		UNOCard card2 = new UNOCard(CardType.EIGHT,UNOColor.BLUE);
		testGame.validateCardTypesMatch(card1,card2);
	}

	/*
	@Test
	public void testDealHandPlayers() {
		Game game = new Game();
		Vector<Player> playerList= game.dealHand(3); //TODO: Need to fix - dealHand() does not take any parameters
		assertEquals(4, playerList.size());
  @Test
	public void testDealHandPlayers() {
		Game game = new Game();
		Vector<Player> playerList= game.dealHand(3);
		assertEquals(4, playerList.size());
	}

	@Test
	public void testDealHandPlayersHuman() {
		Game game = new Game();
		Vector<Player> playerList= game.dealHand(3); //TODO: Need to fix - dealHand() does not take any parameters
	    assertTrue(playerList.get(3).isHuman());
	}
	*/
}
