package lthopoly;

import lthopoly.spaces.BoardSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tank on 4/17/2016.
 */
public class GameBoard {

    /**
     * Menu constants
     */
    public static final int THROW_DICE = 0;
    public static final int DRAW_CARD = 1;
    public static final int BUY_PROPERTY = 2;
    public static final int PAY_RENT = 3;
    public static final int END_TURN = 4;
    public static final int DEFAULT_VIEW = 5;
    public static final int SHOW_BOARD = 6;
    public static final int EXIT_GAME = 7;


    /**
     * Attributes
     */
    private ArrayList<BoardSpace> spaces;
    private List<Player> players;
    private int currentPlayerIndex = 0; // Int for keeping track of current player
    private ArrayList<Integer> moneySum; // Arraylist for registering total money after every round
    private Random rand = new Random();


    /**
     * Creates a new board ready to play
     */
    public GameBoard(List<Player> players) {
        spaces = DocumentParser.getBoard();
        this.players = players;
        int money=0;
        for(Player p: players) money+=p.getMoney();
        moneySum.append(money);
    }

    /**
     * Returns an int array containing possible game actions.
     * A game action can be any of the static constants in
     * GameBoard
     */
    public int[] getPossibleActions() {
        return new int[]{THROW_DICE, DRAW_CARD, BUY_PROPERTY, PAY_RENT, END_TURN, DEFAULT_VIEW, SHOW_BOARD, EXIT_GAME};
    }

    /**
     * Checks whether the game is over or not
     */
    public boolean isGameOver() {
        for(Player p: players) if(p.getMoney() == 0) return true;
        return false;
    }

    /**
     * Returns the player with the most money
     */
    public Player getRichestPlayer() {
        Player richest = new Player("temp", 0, 0);
        for(Player p: players) if(p.getMoney() > richest.getMoney()) richest = p;
        return richest;
    }

    /**
     * Returns a list of all players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns a list of all BoardSpaces
     */
    public List<BoardSpace> getBoardSpaces() {
        return spaces;
    }

    /**
     * Performs an action for the current player
     */
    public void doAction(int action) {
        switch(action){
            case THROW_DICE: int diceRoll = rand.nextInt()%6+1;
                TextUi.addToLog(Integer.toString(diceRoll));
                moveCurrentPlayer(diceRoll);
                break;
            case DRAW_CARD: spaces[players[currentPlayerIndex].getPosition()].action(this, action);
            case BUY_PROPERTY:
            case PAY_RENT:
                break;
            case END_TURN: currentPlayerIndex = (currentPlayerIndex+1)%players.size();
                if(currentPlayerIndex == 0){
                    int money = 0;
                    for(Player p: players) money+=p.getMoney();
                    moneySum.append(money);
                }
                break;
            //case DEFAULT_VIEW: Don't know what the fuck this is supposed to do...?
            case SHOW_BOARD: TextUi.addToLog(this.toString());
                break;
            case EXIT_GAME: System.exit();
        }
    }

    /**
     * Returns the currently active player
     */
    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    /**
     * Returns the boardspace corresponding to the position of the current player.
     */
    public BoardSpace getCurrentBoardSpace() {
        return spaces[players[currentPlayerIndex].getPosition()];
    }

    /**
     * Moves the currently active player adjustments spaces forward. Negative adjustment moves the player backwards
     */
    public void moveCurrentPlayer(int adjustment) {
        int current = players[currentPlayerIndex].getPosition()+adjustment;
        if(current>=spaces.size()) current-=spaces.size();
        else if(current < 0) current += spaces.size();
        players[currentPlayerIndex].setPosition(current);
    }

    /**
     * Returns an ArrayList<Integer> where each element contains the total sum of all players' money
     * at the end of a round.
     * E.g. list.get(0) is the total amount of money in the game after the first round.
     */
    public ArrayList<Integer> getStatistics() {
        return moneySum;
    }

    /**
     * String Representation of the GameBoard
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Rutans Namn [Ägare] (Pris/Hyra) (Spelare, Pengar)*\n");
        out.append("--------------------------------------------------\n");
        for (int i = 0; i < spaces.size(); i++) {

            out.append(spaces.get(i).toString() + " ");

            for (int j = 0; j < players.size(); j++)
                if (players.get(j).getPosition() == i)
                    out.append("(" + players.get(j).toString() + "," + players.get(j).getMoney() + ")");//add name to row

            out.append("\n");
        }
        return out.toString();
    }
}
