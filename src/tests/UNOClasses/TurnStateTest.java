package tests.UNOClasses;

import code.UNOClasses.Player;
import code.UNOClasses.PlayerTurnState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnStateTest {

    @Test
    void testMoveNext(){
        PlayerTurnState pts = PlayerTurnState.getInstance();
        pts.initialize(6,3);
        assertEquals(pts.getCurrentTurn(), 3);
        assertEquals(pts.moveNextPlayer(),4);
        assertEquals(pts.moveNextPlayer(),5);
        assertEquals(pts.moveNextPlayer(),0);
    }

    @Test
    void testReverse(){
        PlayerTurnState pts = PlayerTurnState.getInstance();
        pts.initialize(6,3);
        assertEquals(pts.getCurrentTurn(), 3);
        assertEquals(pts.moveNextPlayer(),4);
        pts.reverseTurnOrder();
        assertEquals(pts.getCurrentTurn(), 4);
        assertEquals(pts.moveNextPlayer(), 3);
        assertEquals(pts.moveNextPlayer(), 2);
        pts.reverseTurnOrder();
        assertEquals(pts.moveNextPlayer(),3);
        assertEquals(pts.moveNextPlayer(), 4);
    }

    @Test
    void testSkip(){
        PlayerTurnState pts = PlayerTurnState.getInstance();
        pts.initialize(6,3);
        pts.skipNextPlayer();
        assertEquals(pts.getCurrentTurn(),5);
        pts.reverseTurnOrder();
        pts.skipNextPlayer();
        assertEquals(pts.getCurrentTurn(),3);

    }

    @Test
    void testGetCurrentTurn(){
        PlayerTurnState pts = PlayerTurnState.getInstance();
        pts.initialize(6,3);
        assertEquals(pts.getCurrentTurn(),3);
    }

    @Test
    void testPeekNextTurn(){
        PlayerTurnState pts = PlayerTurnState.getInstance();
        pts.initialize(6,0);
        assertEquals(pts.peekNextTurn(), 1);
        pts.moveNextPlayer();
        assertEquals(pts.peekNextTurn(),2);
        pts.reverseTurnOrder();
        assertEquals(pts.peekNextTurn(), 0);
        pts.moveNextPlayer();
        assertEquals(pts.peekNextTurn(),5);
    }

    @Test
    void testPeekLastTurn() {
        PlayerTurnState pts = PlayerTurnState.getInstance();
        pts.initialize(6,5);
        assertEquals(pts.peekLastTurn(), 4);
        pts.moveNextPlayer();
        assertEquals(pts.peekLastTurn(), 5);
        pts.moveNextPlayer();
        assertEquals(pts.peekLastTurn(),0);
        pts.reverseTurnOrder();
        assertEquals(pts.peekLastTurn(), 2);
        pts.moveNextPlayer();
        assertEquals(pts.peekLastTurn(),1);
    }

    @Test
    void testPeekTwoPlayers() {
        PlayerTurnState pts = PlayerTurnState.getInstance();
        pts.initialize(4,3);
        assertEquals(pts.peekTwoPlayers(), 1);
        pts.moveNextPlayer();
        assertEquals(pts.peekTwoPlayers(), 2);
        pts.moveNextPlayer();
        assertEquals(pts.peekTwoPlayers(),3);
        pts.reverseTurnOrder();
        assertEquals(pts.peekTwoPlayers(), 3);
        pts.moveNextPlayer();
        assertEquals(pts.peekTwoPlayers(), 2);
    }


}
