package UserInterface;
import Data.Board;
import Data.Player;
import Data.Square;

public interface UI {

	//Setting the game
	int printMenu();

	void printInstructions();

	void printAbout();

	int askSize();

	int askPlayerNum();

	char askToken(int playerNum);


	//Printing the game
	void printBoard(Board board);

	void playerWins();


	//Request input
	void askRoll(Player player);


	//Feedback
	void turnFeedback(int move, Player player, Square position);

	void arcFeedback(boolean good, int entry, int exit);

	void badFeedback();

	//Testing only
	int askMovement(Board board);
}
