package code.UNOClasses;


import code.UNOClasses.Card.UNOCard;
import code.UNOClasses.Hand.PlayerHand;

public class Player {
    private boolean isHuman; //returns true for the interfacing user, false for computer players (AI)
    private PlayerHand _hand = new PlayerHand();
    private int position;
    private String playerName;

    public Player (boolean isHumanPlayer, String name){
        this.isHuman = isHumanPlayer;
        this.playerName = name;

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

    //Somehow the game should check to see if a player has one card left and if they have emitted UNO! as a string
    //to determine if they should be penalized for not calling UNO!
    public String callUNO(){ return "UNO!"; }

    //Get Player's Name
    public String getName() {
        return playerName;
    }

    //expose the hand object so we can use hand methods such as getting the hand total and printing the hand.
    public PlayerHand myHand(){ return _hand; }

    public String toString(){
        return "Human: " + this.isHuman + "\n" + "Position: " + this.position + "\n" + "Hand:\n" + this._hand.toString();
    }

    public void setPosition (int p) { this.position = p; }
}
