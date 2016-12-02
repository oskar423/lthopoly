package lthopoly.spaces;

import lthopoly.GameBoard;
import lthopoly.cards.MoveCard;

/**
 * Created by Tank on 4/17/2016.
 */
public class MoveSpace extends BoardSpace {

    //private static MoveCard[] cards = DocumentParser.getMoveCards(); <- Better way to do it IMO

    private MoveCard[] cards;

    /**
     * Creates a new MoveSpace. When landing on this space a card from the card array will be drawn
     */
    public MoveSpace(MoveCard[] cards) {
        this.cards = cards;
    }

    /**
     * Returns an array of possible game actions permitted by this space
     */
    @Override
    public int[] getPossibleActions(GameBoard board) {
        return new int[]{board.DRAW_CARD, board.END_TURN, board.DEFAULT_VIEW, board.SHOW_BOARD, board.EXIT_GAME};
    }

    /**
     * Performs a MoveSpace-related action.
     */
    @Override
    public void action(GameBoard board, int action) {
        board.doAction(action);
    }

    /**
     * Returns a string representation of the MoveSpace
     */
    @Override
    public String toString() {
        return "Move space!\nDraw a card!";
    }
}
