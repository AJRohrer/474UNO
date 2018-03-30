import code.UNOClasses.Card.CardType;
import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Card.UNOColor;
import code.UNOClasses.Player;

import java.util.Vector;

public class main {

    public static void main(String args[]){
        UNOCard c = new UNOCard(CardType.DRAWTWO, UNOColor.BLUE);
        System.out.println(c);

        System.out.println("Draw 2: " + c.isDraw2());
        System.out.println("Reverse: " + c.isReverse());
        System.out.println("Skip: " + c.isSkip());
        System.out.println("Wild: " + c.isWild());
        System.out.println("Wild Draw 4: " + c.isWildDraw4());

        Player p = new Player(true);
        //will be replaced by deal functionality soon.
        p.addCardtoHand(c);
        p.addCardtoHand(c);
        System.out.println(p.toString());
    }

}
