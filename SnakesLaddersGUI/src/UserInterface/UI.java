package UserInterface;

import Data.Board;
import Data.Player;
import Data.Square;
import java.util.ArrayList;

public interface UI {

    //Setting the game
    int printMenu();

    void printInstructions();

    void printAbout();

    int askBoardSize();

    int askNumberOfPlayers();

    char askPlayerToken(int playerNum);
    
    void printArcs(ArrayList<Integer> doors);

    void printBoard(Board board);

    void askRoll(Player player);
    
    void turnFeedback(int move, Player player, Square position);

    void arcFeedback(boolean good, int entry, int exit);
    
    void printPlayerWins(Player player);

    void badFeedback();
}