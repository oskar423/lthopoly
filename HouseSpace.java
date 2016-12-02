package lthopoly.spaces;


import lthopoly.GameBoard;

/**
 * Created by Tank on 4/17/2016.
 */
public class HouseSpace extends BoardSpace {

    private int rent;
    private String description;
    private int ownedBy = -1;

    /**
     * Creates a new housespace with rent and a description
     */
    public HouseSpace(int rent, String description) {
        this.rent = rent;
        this.description = description;
    }

    /**
     * Returns an array of possible game actions permitted by this space
     */
    @Override
    public int[] getPossibleActions(GameBoard board) {
        int buyOrSell;
        if(ownedBy == -1) buyOrSell = board.BUY_PROPERTY;
        else buyOrSell = board.PAY_RENT; 
        return new int[]{buyOrSell, board.END_TURN, board.DEFAULT_VIEW, board.SHOW_BOARD, board.EXIT_GAME};
    }

    /**
     * Performs a HouseSpace-related action.
     */
    @Override
    public void action(GameBoard board, int action) {
        board.doAction(action);
    }

    /**
     * Returns a string representation of the HouseSpace with the format "HouseName [Owner] (Rent)"
     */
    @Override
    public String toString() {
        return description + "\n" + Integer.toString(rent) + " SEK";
    }

}
