package tests.UNOClasses;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import code.UNOClasses.Game;
import code.UNOClasses.Player;

public class GameTest {

	@Test
	public void testDealHandPlayers() {
		Game game = new Game();
		Vector<Player> playerList= game.dealHand(3);
		assertEquals(4, playerList.size());
	}
	
	@Test
	public void testDealHandPlayersHuman() {
		Game game = new Game();
		Vector<Player> playerList= game.dealHand(3);
	    assertTrue(playerList.get(3).isHuman());
	}

}
