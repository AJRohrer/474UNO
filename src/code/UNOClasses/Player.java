package code.UNOClasses;


import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Hand.PlayerHand;

public class Player {
    private boolean isHuman; //returns true for the interfacing user, false for computer players (AI)
    private PlayerHand _hand;

    public Player (boolean isHumanPlayer){
        this.isHuman = isHumanPlayer;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void addCardtoHand(UNOCard uc){
        _hand.addUNOCard(uc);
    }

    public UNOCard discardCard(UNOCard uc){
        _hand.removeUNOCard(uc);
        return uc;
    }

    public String callUNO(){ return "UNO!"; }
}
