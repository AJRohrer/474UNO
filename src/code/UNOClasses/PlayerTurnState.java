package code.UNOClasses;

//This is a singleton class throughout the whole game so we don't have to worry about passing the object around all the time
//Calling initialize with the new game should be enough to reset the game if another game is played.
public class PlayerTurnState {

    private int numberOfPlayers = 0;
    private int currentPlayerTurn = 0;
    private boolean nextPlayer = true; //if this is true the order increments, if false the order is in reverse.

    private static PlayerTurnState tsInstance = null;

    public static PlayerTurnState getInstance() {
        if (tsInstance == null) {
            tsInstance = new PlayerTurnState();
        }
        return tsInstance;
    }

    private PlayerTurnState() {
    }

    //Make sure that the starting player is the position in the array, not the player number.
    public void initialize(int numofPlayers, int startingPlayer){
        this.numberOfPlayers = numofPlayers;
        this.currentPlayerTurn = startingPlayer;
        this.nextPlayer = true;
    }

    public int moveNextPlayer(){
        //if the game is not reversed order
        if (nextPlayer){
            //if the current player is no the last in the array we want to increment which players turn it is.
            //if the current player is the last in the list we want to set the next player to the first player in the list.
            if(currentPlayerTurn < numberOfPlayers - 1) {
                currentPlayerTurn++;
            } else {
                currentPlayerTurn = 0;
            }
        }
        //if the game is reverse order
        else {
            //if the current player is not the first in the array we want to move to the next player in reverse order
            //if the current player is the first player in the array we want to switch the next player to be the last in the list.
            if(currentPlayerTurn > 0) {
                currentPlayerTurn--;
            } else {
                currentPlayerTurn = numberOfPlayers -1;
            }
        }
        return currentPlayerTurn;
    }

    public int getCurrentTurn(){ return currentPlayerTurn; }

    public int peekNextTurn() {
        if(nextPlayer){
            if(currentPlayerTurn < numberOfPlayers - 1){
                return currentPlayerTurn + 1;
            } else {
                return 0;
            }
        } else {
            if(currentPlayerTurn > 0){
                return currentPlayerTurn - 1;
            } else {
                return numberOfPlayers -1;
            }
        }
    }

    public int peekLastTurn() {
        if (nextPlayer){
            if (currentPlayerTurn > 0){
                return currentPlayerTurn - 1;
            } else {
                return numberOfPlayers - 1;
            }
        } else {
            if (currentPlayerTurn < numberOfPlayers - 1){
                return currentPlayerTurn + 1;
            } else {
                return 0;
            }
        }
    }

    public int peekTwoPlayers(){
        int tempPosition = getCurrentTurn();
        for (int i = 0; i < 2; i++){
            if (nextPlayer) {
                if (tempPosition < numberOfPlayers - 1){
                    tempPosition = tempPosition + 1;
                } else {
                    tempPosition = 0;
                }
            } else  {
                if (tempPosition > 0){
                    tempPosition = tempPosition - 1;
                } else {
                    tempPosition = numberOfPlayers - 1;
                }
            }
        }
        return tempPosition;
    }

    public void reverseTurnOrder(){ nextPlayer = !nextPlayer; }

    public void skipNextPlayer(){
        //if the order is not reversed.
        if (nextPlayer){
            //skipping one player is actually incrementing the next player 2 times.
            for (int i = 0; i < 2; i++) {
                //if the current player is the last player in the array we want to wrap to the beginning of the array.
                if (currentPlayerTurn < numberOfPlayers - 1) {
                    currentPlayerTurn++;
                } else {
                    currentPlayerTurn = 0; //wrapped around to the beginning of the array or players.
                }
            }
        }
        //the order is reversed.
        else {
            for(int i = 0; i < 2; i++) {
                if (currentPlayerTurn > 0) {
                    currentPlayerTurn--;
                } else {
                    currentPlayerTurn = numberOfPlayers - 1; //set player to last player in the array.
                }
            }
        }
    }
}
