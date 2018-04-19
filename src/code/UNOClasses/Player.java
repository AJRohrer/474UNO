package code.UNOClasses;


import code.UNOClasses.Card.UNOCard;
import java.util.*;

public class Player {
    private boolean isHuman; //returns true for the interfacing user, false for computer players (AI)
    private PlayerHand _hand = new PlayerHand();
    private int position;
    private String name;

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


    //Somehow the game should check to see if a player has one card left and if they have emitted UNO! as a string
    //to determine if they should be penalized for not calling UNO!
    public String callUNO(){
        return "UNO!"; }

    //expose the hand object so we can use hand methods such as getting the hand total and printing the hand.
    public PlayerHand myHand(){ return _hand; }

    public String toString(){
        return "Human: " + this.isHuman + "\n" + "Position: " + this.position + "\n" + "Hand:\n" + this._hand.toString();
    }

    public void setPosition (int p) { this.position = p; }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return this.name;
    }

    public String chooseRandomName () {
        ArrayList<String> names = new ArrayList<>();
        Collections.addAll(names, "Liz", "Tyree", "Darya", "Andrew", "Pranjali", "Suchitra", "Roxeanne", "Bob", "Rocky",
                "Lisa", "Jesse", "Christopher", "Batman", "Sylvia", "Aldo", "Ethelene", "Tifany", "Beaulah", "Chantell", "Willard",
                "Marita", "Lavonda", "Calista", "Chelsey", "Cristi", "Delfina", "Tawny", "Shelly", "Chantel", "Delsie", "Valentine",
                "Bradford", "Sun", "Raphael", "Donatello", "Leonardo", "Michelangelo", "Splinter");

        Random rnd = new Random();
        int total = names.size();
        int index = rnd.nextInt(total);
        return names.get(index);

    }
}

