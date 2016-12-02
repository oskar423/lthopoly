package lthopoly.parser;

import lthopoly.cards.MoneyCard;
import lthopoly.cards.MoveCard;
import lthopoly.spaces.BoardSpace;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * Created by Tank on 4/17/2016.
 */
public class DocumentParser {

    /**
     * Returns a ArrayList of Boardspaces loaded from a file
     */
    public static ArrayList<BoardSpace> getBoard() {
        MoneyCard[] moneyCards = getMoneyCards();
    	MoveCard[] moceCards = getMoveCards();
    	File f = new File("board.txt");
    	Scanner sc = new Scanner(f);
    	String[3] parts;
    	while(sc.hasNextLine()){
    		parts = sc.nextLine();
    		if (parts = "Money")
        return null;
    }


    /**
     * Returns a array of MoneyCards loaded from file
     */
    public static MoneyCard[] getMoneyCards() {
        ArrayList<MoneyCard> cards;
        File f = new File("moneycards.txt");
        Scanner sc = new Scanner(f);
        String[2] parts;
        while(sc.hasNextLine()){
            parts = sc.nextLine().split(";");
            cards.add(new MoneyCard(parts[0], Integer.parseInt(parts[1])));
        }
        return cards.toArray();
    }

    /**
     * Returns a array of MoveCards loaded from file
     */
    public static MoveCard[] getMoveCards() {
        ArrayList<MoneyCard> cards;
        File f = new File("movecards.txt");
        Scanner sc = new Scanner(f);
        String[2] parts;
        while(sc.hasNextLine()){
            parts = sc.nextLine().split(";");
            cards.add(new MoveCard(parts[0], Integer.parseInt(parts[1])));
        }
        return cards.toArray();
    }


}
