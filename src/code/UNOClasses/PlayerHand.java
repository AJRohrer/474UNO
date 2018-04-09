package code.UNOClasses;

import code.UNOClasses.Card.UNOCard;

import java.util.Collections;
import java.util.Vector;

public class PlayerHand
{
    private Vector<UNOCard> unoCardsList;
    private UNOCard unoCard;

    public PlayerHand()
    {
        this.unoCardsList = new Vector<UNOCard>();
    }

    // method will sort the vector of cards by color
    //based on the order in which the Enumerated colors
    //appear in the UNOColor Enum class
    public void sort()
    {
        Collections.sort(unoCardsList);
    }

    // method prints the total number of cards in the card list
    public int handTotal()
    {
        return unoCardsList.size();
    }

    // this method will print the information about each card
    // in the player hand
    public void printHand()
    {
        for (UNOCard unoCard : unoCardsList)
        {
            System.out.println(unoCard.get_type() + " " + unoCard.get_color());
        }
    }

    // will return the uno card list for each player hand
    public Vector<UNOCard> getUnoCardsList()
    {
        return unoCardsList;
    }

    // checks to see if there are no more cards in the player hand
    public boolean isEmpty()
    {
        if(unoCardsList.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // adds a card to the players hand
    public void addUNOCard(UNOCard unoCard)
    {
        unoCardsList.add(unoCard);
    }

    // checks first to see if there is a matching card in the player hand
    // and then removes the card if there is a match. If there is no match
    // an exception will be thrown.
    public void removeUNOCard(UNOCard unoCard)
    {
        try
        {
            if(unoCardsList.contains(unoCard))
            {
                unoCardsList.remove(unoCard);
            }
            else
            {
                throw new IllegalStateException();
            }
        }
        catch (IllegalStateException exception)
        {
            System.out.println("Card not found in player hand");
        }

    }

    // Checks first to see if there is one card left in the player hand.
    // If there is one card left, the player will be able to declare UNO.
    // If there is more than one card left an exception will be thrown.
    public String callUNO()
    {
        try
        {
            if (unoCardsList.size() == 1)
            {
                return "UNO!!";
            }
            else
            {
                throw new IllegalStateException();
            }
        }
        catch (IllegalStateException exception)
        {
            return "You can only call UNO when there " +
                    "is one card left in your hand ";
        }
    }

    public String toString(){
        String handstring = "";
        for (UNOCard uc : unoCardsList){
             handstring += uc.toString() + "\n";
        }
        return handstring;
    }
}
