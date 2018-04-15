package tests.UNOClasses;

import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.ComputerPlayer;
import code.UNOClasses.Game;
import code.UNOClasses.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerPlayerTest {

    @Test
    public void testMakeMove(){
        ComputerPlayer AIPlayer = new ComputerPlayer();
        Player playerBefore = new Player(true);
        Player playerAfter = new Player(true);
        Player playerAfterNext = new Player(true);

        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.FIVE,UNOColor.GREEN));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.REVERSE, UNOColor.YELLOW));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.WILD, UNOColor.YELLOW));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.ONE, UNOColor.RED));
        AIPlayer.myHand().addUNOCard(new UNOCard(CardType.ONE, UNOColor.GREEN));

        for (int i = 0; i <= 6; i++){
            playerAfter.myHand().addUNOCard(null);
            if (i <= 3){
                playerBefore.myHand().addUNOCard(null);
                playerAfterNext.myHand().addUNOCard(null);
            }
        }

        UNOCard playedCard = AIPlayer.makeMove(new UNOCard(CardType.EIGHT,UNOColor.BLUE), playerBefore, playerAfter, playerAfterNext);
        System.out.println(playedCard.toString());


    }


}
