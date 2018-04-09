package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.PlayerHand;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Player Hand Test Class
 */

public class PlayerHandTest
{
    /**
     * Creating PlayerHand and UNOCard objects for test class
     */

    private PlayerHand playerHand = new PlayerHand();

    private UNOCard unoCard1 = new UNOCard(CardType.ZERO, UNOColor.YELLOW);
    private UNOCard unoCard2 = new UNOCard(CardType.ZERO, UNOColor.WILD);
    private UNOCard unoCard3 = new UNOCard(CardType.ZERO, UNOColor.RED);
    private UNOCard unoCard4 = new UNOCard(CardType.THREE, UNOColor.RED);
    private UNOCard unoCard5 = new UNOCard(CardType.ZERO, UNOColor.YELLOW);
    private UNOCard unoCard6 = new UNOCard(CardType.ZERO, UNOColor.BLUE);
    private UNOCard unoCard7 = new UNOCard(CardType.ZERO, UNOColor.GREEN);
    private UNOCard unoCard8 = new UNOCard(CardType.ZERO, UNOColor.BLUE);

    /**
     * Testing sort method. The order of the cards should be sorted
     * in the same order they appear in the UNOColor Enum class
     */

    @Test
    public void sortTest()
    {
        playerHand.addUNOCard(unoCard1);
        playerHand.addUNOCard(unoCard2);
        playerHand.addUNOCard(unoCard3);
        playerHand.addUNOCard(unoCard4);
        playerHand.addUNOCard(unoCard5);
        playerHand.addUNOCard(unoCard6);
        playerHand.addUNOCard(unoCard7);
        playerHand.addUNOCard(unoCard8);

        Collections.sort(playerHand.getUnoCardsList());

        assertEquals(playerHand.getUnoCardsList().get(0).get_color(), playerHand.getUnoCardsList().get(1).get_color());
        assertEquals(playerHand.getUnoCardsList().get(2).get_color(), playerHand.getUnoCardsList().get(3).get_color());
        assertEquals(playerHand.getUnoCardsList().get(4).get_color(), playerHand.getUnoCardsList().get(5).get_color());
    }

    /**
     * Testing that the size of the PlayerHand vector is correct
     */
    @Test
    public void handTotalTest()
    {
        playerHand.addUNOCard(unoCard1);
        playerHand.addUNOCard(unoCard2);
        playerHand.addUNOCard(unoCard3);

        assertEquals(3, playerHand.handTotal());
    }

    /**
     * Testing that the PlayHand Vector list has been initialized
     */
    @Test
    public void getUnoCardsListTest()
    {
        playerHand.addUNOCard(unoCard1);

        assertNotNull(playerHand.getUnoCardsList());
    }

    /**
     * Testing the false condition of isEmpty method in PlayerHand class
     */
    @Test
    public void isEmptyTestFalse()
    {
        playerHand.addUNOCard(unoCard1);

        assertFalse(playerHand.isEmpty());
    }

    /**
     * Testing the true condition of the isEmpty method in PlayerHand class
     */
    @Test
    public void isEmptyTestTrue()
    {
        playerHand.addUNOCard(unoCard1);

        assertTrue(playerHand.getUnoCardsList().size() == 1);
    }

    /**
     * Testing that a card was correctly added to the PlayerHand vector
     */
    @Test
    public void addUNOCardTest()
    {
        playerHand.addUNOCard(unoCard1);

        assertTrue(playerHand.getUnoCardsList().size() == 1);
    }

    /**
     * Testing the false condition of the removeUNOCard method
     * in PlayerHand class
     */
    @Test
    public void removeUNOCardTestFalse()
    {
        playerHand.addUNOCard(unoCard1);
        playerHand.removeUNOCard(unoCard1);

        assertFalse(playerHand.getUnoCardsList().contains(unoCard1));
    }

    /**
     * Testing the true condition of the removeUNOCard method
     * in the PlayerHand class
     */
    @Test
    public void removeUNOCardTestTrue()
    {
        playerHand.addUNOCard(unoCard1);

        assertTrue(playerHand.getUnoCardsList().contains(unoCard1));
    }

    /**
     * Testing the true condition of the CallUNO method
     * in the PlayerHand class
     */
    @Test
    public void callUNOTestTrue()
    {
        playerHand.addUNOCard(unoCard1);
        playerHand.addUNOCard(unoCard2);
        playerHand.removeUNOCard(unoCard1);

        assertEquals("UNO!!", playerHand.callUNO());
    }

    /**
     * Testing the false condition of the CallUNO method
     * in the PlayerHand class
     */
    @Test
    public void callUNOTestFalse()
    {
        playerHand.addUNOCard(unoCard1);
        playerHand.addUNOCard(unoCard2);

        assertEquals("You can only call UNO when there " +
                "is one card left in your hand ", playerHand.callUNO());
    }
}
