package Business;
import Data.Arc;
import Data.Board;
import Data.Player;
import UserInterface.UI;
import UserInterface.UIText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GamePlay {

	private static UI ui;
	private static Board board;
	private static ArrayList<Player> players = new ArrayList<>();
	private static Random random = new Random();

	public static void main (String[] args) {

		ui = new UIText();

		do {
			int	select = ui.printMenu();

			switch (select) {
				case 1:
					beginGame();
					break;
				case 2:
					ui.printInstructions();
					break;
				case 3:
					ui.printAbout();
					break;
				case 4:
					System.exit(0);
				default:
					ui.badFeedback();
			}
		} while (true);
	}

	public static void beginGame() {
		setBoard();

		setPlayers();

		setArcs();

		play();
	}

	public static void play () {

		ui.printBoard(board);

		boolean win = false;
		//Round
		while (!win) {
			//Turns
			for (int playerTurn = 0; playerTurn < players.size(); playerTurn++) {

				ui.askRoll(players.get(playerTurn));
				rollDice(players.get(playerTurn));

				// Testing only
				//movement(player, board);

				arcMovement(players.get(playerTurn));

				ui.printBoard(board);

				win = checkWin(players.get(playerTurn));
				if (win) {
					break;
				}
			}
		}
	}

	//Setting the game
	public static void setBoard() {
		int selectSize = ui.askSize();
		int size;

		switch (selectSize) {
			case 1:
				size = (int) Math.pow(8, 2);
				break;
			case 2:
				size = (int) Math.pow(10, 2);
				break;
			case 3:
				size = (int) Math.pow(12, 2);
				break;
			default:
				ui.badFeedback();
				size = ui.askSize();
		}
		board = new Board(size);
	}

	public static void setArcs() {
		//create collection of random numbers
		int numberDoors = random.nextInt(board.getSize()/2) + 2;

		ArrayList<Integer> doors = new ArrayList<>(numberDoors);
		for (int i = 0; i < board.getSize(); i++) {
			doors.add(i);
		}
		Collections.shuffle(doors);

		//Create the arcs that connect squares
		int entry, exit;
		for (int j = 0, l = 1; j < numberDoors; j+= 2, l++) {
			entry = j;
			exit = j+1;
			Arc arc = new Arc(l, board, doors.get(entry), doors.get(exit));

			board.getBoard()[doors.get(entry)].setArc(arc);

			board.getBoard()[doors.get(exit)].setArc(arc);
		}

		//Print ONLY for testing
		for (int k = 0, l = 1; k < numberDoors; k+=2, l++) {
			System.out.println(l +": "+ String.valueOf(doors.get(k)+1) + ", "+ String.valueOf(doors.get(k+1)+1));
		}
	}

	public static void setPlayers() {
		int playerNum = ui.askPlayerNum();

		for (int i = 0; i < playerNum; i++) {

			char selectToken = ui.askToken(i+1);

			Player player = new Player(selectToken);

			players.add(i, player);

			players.get(i).setPosition(board.getBoard()[0]);
		}
	}


	//GamePlay
	public static void rollDice (Player player) {

		int move = random.nextInt(5) + 1;

		try {
			player.setPosition(board.getBoard()[player.getPosition().getIndex() + move - 1]);

		} catch (ArrayIndexOutOfBoundsException winningMove) {
			player.setPosition(board.getBoard()[board.getBoard().length - 1]);
		}
		ui.turnFeedback(move, player, player.getPosition());
	}

	public static void arcMovement(Player player) {
		if (player.getPosition().getArc() != null) {

			int entry = player.getPosition().getIndex();
			int exit = player.getPosition().getArc().getExit().getIndex();

			//Feedback
			if (entry < exit) {
				ui.arcFeedback(true, entry, exit);
			} else if (entry > exit) {
				ui.arcFeedback(false, entry, exit);
			}
			//set new position
			player.setPosition(player.getPosition().getArc().getExit());
		}
	}

	public static boolean checkWin (Player player) {
		if (player.getPosition().getIndex() >= board.getBoard().length) {
			ui.playerWins();
			return true;
		} else {
			return false;
		}
	}
}