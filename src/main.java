import UNOClasses.Card.CardType;
import UNOClasses.Card.UNOCard;
import UNOClasses.Card.UNOColor;

public class main {

    public static void main(String args[]){
        UNOCard c = new UNOCard(CardType.DRAWTWO, UNOColor.BLUE);
        System.out.println(c);

        System.out.println("Draw 2: " + c.isDraw2());
        System.out.println("Reverse: " + c.isReverse());
        System.out.println("Skip: " + c.isSkip());
        System.out.println("Wild: " + c.isWild());
        System.out.println("Wild Draw 4: " + c.isWildDraw4());
    }

}
