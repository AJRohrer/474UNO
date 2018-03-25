import UNOClasses.Card.CardType;
import UNOClasses.Card.UNOCard;
import UNOClasses.Card.UNOColor;

public class main {

    public static void main(String args[]){
        UNOCard c = new UNOCard(CardType.DRAWTWO, UNOColor.BLUE);
        System.out.println(c);
    }

}
