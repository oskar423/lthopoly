package lthopoly.spaces;

import lthopoly.GameBoard;
import lthopoly.cards.MoneyCard;

import java.util.Random;

/**
 * Created by Tank on 4/17/2016.
 */
public class MoneySpace extends BoardSpace {

    //private static MoneyCard[] cards = DocumentParser.getMoneyCards(); <- Better way to do it IMO
    private MoneyCard[] cards;
    Random rand = new Random();

    /**
     * Creates a new MoneySpace. When landing on this space a card from the card array will be drawn
     */
    public MoneySpace(MoneyCard[] cards) {
        this.cards = cards;
    }

    /**
     * Returns an array of possible game actions permitted by this space
     */
    @Override
    public int[] getPossibleActions() {
        return new int[]{GameBoard.DRAW_CARD, GameBoard.END_TURN, GameBoard.DEFAULT_VIEW, GameBoard.SHOW_BOARD, GameBoard.EXIT_GAME};
    }

    /**
     * Performs a MoneySpace-related action.
     */
    public void action(GameBoard board, int action) {
      if(action == GameBoard.DRAW_CARD){
                int cardIndex = rand.nextInt()%cards.size();
                TextUI.addToLog(cards[cardIndex].getDescription());
                GameBoard.getCurrentPlayer.adjustMoney(cards[cardIndex].getMoney); 
      }
        else return;
        
    }

    /**
     * Returns a string representation of the MoneySpace
     */
    @Override
    public String toString() {
        return "Money space!\nGamble!";
    }
}
