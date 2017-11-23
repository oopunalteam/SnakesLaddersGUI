package UserInterface;

import Data.Board;
import Data.Player;
import Data.Square;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIText implements UI {

    private Scanner input = new Scanner(System.in);

    //Game menu
    @Override
    public int printMenu() throws InputMismatchException {
        System.out.println("\tSnakes & Ladders" + "\n1. Play" + "\n2. Instructions" + "\n3. About" + "\n4. Quit");
        return input.nextInt();
    }

    @Override
    public void printInstructions() {
        System.out.println("\tInstructions" + "\nWrite something to roll the dice\n");
    }

    @Override
    public void printAbout() {
        System.out.println("\tAbout" + "\nThis game is a text version of Snakes & Ladders, a group project for OOP");
    }

    //Game setup
    @Override
    public int askBoardSize() throws InputMismatchException {
        System.out.println("\tBoard size:" + "\n1. 8x8" + "\n2. 10x10" + "\n3. 12x12");
        return input.nextInt();
    }

    @Override
    public int askNumberOfPlayers() throws InputMismatchException {
        System.out.println("How many players are going to play?");
        return input.nextInt();
    }

    @Override
    public char askPlayerToken(int playerNum) {
        System.out.println("Player " + (playerNum + 1) + ". please select your token:");

        char token = input.next().charAt(0);

        if ((64 < token && token < 91) || (96 < token && token < 123)) {
            System.out.println("You selected the token: " + token + "\n");
        } else {
            this.badFeedback();
            token = this.askPlayerToken(playerNum);
        }
        return token;
    }

    //Printing the game
    @Override
    public void printArcs(ArrayList<Integer> doors) {
        System.out.println("List of Snakes (S) and Ladders(L)");
        for (int k = 0, l = 1; k < doors.size(); k += 2, l++) {
            System.out.println(l + ": " + String.valueOf(doors.get(k) + 1) + ", " + String.valueOf(doors.get(k + 1) + 1));
        }
    }

    @Override
    public void printBoard(Board board) {
        System.out.println(board);
    }

    //Request input
    @Override
    public void askRoll(Player player) {
        System.out.println("Player " + player + ", roll dice?");
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
        System.out.println(", player " + player + " rolled a " + move + ". You're now at square " + String.valueOf(position.getIndex()));
    }

    @Override
    public void arcFeedback(boolean good, int entry, int exit) {
        String feedback = "";
        if (good) {
            feedback = "You climbed a ladder!";
        } else if (!good) {
            feedback = "A snake attacked you!";
        }
        System.out.println(feedback + " You moved from square " + entry + " to square " + exit);
    }

    @Override
    public void printPlayerWins(Player player) {
        System.out.println("Player " + player + " you win!");
    }

    @Override
    public void badFeedback() {
        System.out.println("Select a valid option\n");
    }
}
