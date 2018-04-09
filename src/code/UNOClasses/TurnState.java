package code.UNOClasses;

public class TurnState {

    private int numberOfPlayers = 0;
    private int currentPlayerTurn = 0;
    private boolean nextPlayer = true; //if this is true the order increments, if false the order is in reverse.

    private static TurnState ourInstance = new TurnState();

    public static TurnState getInstance() {
        return ourInstance;
    }

    private TurnState() {
    }

    public void initialize(int numofPlayers, int startingPlayer){
        this.numberOfPlayers = numofPlayers;
        this.currentPlayerTurn = startingPlayer;
    }

    public int getCurrentTurn(){ return currentPlayerTurn; }

    public void reverseTurnOrder(){ nextPlayer = !nextPlayer; }

    public void skip(int howMany){
        //if the order is not reversed.
        if (nextPlayer){
            for(int i = 0; i < howMany; i++){
                //if the current player is the last player in the array we want to wrap to the beginning of the array.
                if(currentPlayerTurn < numberOfPlayers - 1){
                    currentPlayerTurn++;
                } else {
                    currentPlayerTurn = 0; //wrapped around to the beginning of the array or players.
                }
            }
        }
        //the order is reversed.
        else {
            for(int i = 0; i < howMany; i++){
                if (currentPlayerTurn > 0){
                    currentPlayerTurn--;
                } else {
                    currentPlayerTurn = numberOfPlayers-1; //set player to last player in the array.
                }
            }
        }
    }
}
