package UserInterface;
import Data.Board;
import Data.Player;
import Data.Square;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIText implements UI {

	private Scanner input = new Scanner(System.in);

	//Setting the game
	@Override
	public int printMenu() {
		System.out.println("\tSnakes & Ladders" + "\n1. Play" +"\n2. Instructions" + "\n3. About" + "\n4. Quit");
		return input.nextInt();
	}

	@Override
	public void printInstructions() {
		System.out.println("\tInstructions"+"\nWrite something to roll the dice\n");
	}

	@Override
	public void printAbout() {
		System.out.println("\tAbout"+"\nThis game is a text version of Snakes & Ladders, \na group proyect for OOP\n");
	}

	@Override
	public int askSize() {
		System.out.println("\tDifficulty:" + "\n1. 8x8" + "\n2. 10x10" + "\n3. 12x12");
		return input.nextInt();
	}

	@Override
	public char askToken(int playerNum) {
		System.out.println("Player " + playerNum + ". please select your token:");

		char token = input.next().charAt(0);

		if ((64 < token && token < 91) || (96 < token && token < 123)) {
			System.out.println("You selected the token: " + token + "\n");
		} else {
			this.badFeedback();
			token = this.askToken(playerNum);
		}
		return token;
	}

	@Override
	public int askPlayerNum() {
		System.out.println("How many players are going to play?");
		return input.nextInt();
	}


	//Printing the game
	@Override
	public void printBoard(Board board) {
		System.out.println(board);
	}

	@Override
	public void playerWins() {
		System.out.println("You win!");
	}


	//Request input
	@Override
	public void askRoll(Player player) {
		System.out.println("Player "+ player +", roll dice?");
		String order = input.next();
	}


	//Feedback
	@Override
	public void turnFeedback(int move, Player player, Square position) {
		switch (move) {
			case 1:
				System.out.print("Bummer");
				break;
			case 2:
				System.out.print("Meh");
				break;
			case 3:
				System.out.print("Ok");
				break;
			case 4:
				System.out.print("Good");
				break;
			case 5:
				System.out.print("Great");
				break;
			case 6:
				System.out.print("WOW");
				break;
		}
		System.out.println(", player "+player+" rolled a "+move+". You're now at square "+String.valueOf(position.getIndex()));
	}

	@Override
	public void arcFeedback (boolean good, int entry, int exit) {
		if (good) {
			System.out.println("You climbed a ladder! You moved from square "+ entry +" to square "+ exit);
		} else if (!good) {
			System.out.println("A snake attacked you! You moved from square "+ entry +" to square "+ exit);
		}

	}

	@Override
	public void badFeedback() {
		System.out.println("Select a valid option \n");
	}


	//Testing only
	@Override
	public int askMovement(Board board) {
		System.out.println("Select the square that you want to move to");
		int move = input.nextInt();
		if (move < 0 || move > board.getSize()) {
			badFeedback();
			askMovement(board);
		}
		return move;
	}
}
