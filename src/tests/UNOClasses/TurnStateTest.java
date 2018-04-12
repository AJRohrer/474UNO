package tests.UNOClasses;

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


}
