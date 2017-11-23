package Business;

import Data.Arc;
import Data.Board;
import Data.Player;
import UserInterface.UI;
import UserInterface.UISwing;
import UserInterface.UIText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;

public class GamePlay {

    private static UI ui;
    private static Board board;
    private static ArrayList<Player> players = new ArrayList<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        selectUI(args);
        menu();
    }

    public static void selectUI(String[] args) {
        /*
        if (args.length == 0) {
            ui = new UISwing();
        } else if (args[0].equals("text")) {
            ui = new UIText();
        } else {
            ui = new UISwing();
        }
         */
        ui = new UIText();
        //ui = new UISwing();
    }

    public static void menu() {
        int option;
        do {
            try {
                option = ui.printMenu();
            } catch (InputMismatchException wrongInput) {
                option = 1;
            }
            switch (option) {
                case 1:
                    setGame();
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
                    break;
            }
        } while (true);
    }

    //Game Setup
    public static void setGame() {
        setBoard();

        setPlayers();

        setArcs();

        play();
    }

    public static void setBoard() {
        int selectSize;
        try {
            selectSize = ui.askBoardSize();
        } catch (InputMismatchException wrongInput) {
            selectSize = 1;
        }
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
                size = ui.askBoardSize();
        }
        board = new Board(size);
    }

    public static void setPlayers() {
        int playerNum;

        try {
            playerNum = ui.askNumberOfPlayers();
        } catch (InputMismatchException wrongInput) {
            playerNum = 1;
        }
        for (int i = 0; i < playerNum; i++) {

            char selectToken = ui.askPlayerToken(i);

            //Check for repeated tokens
            for (int j = 0; j < players.size(); j++) {
                while (selectToken == players.get(j).getToken()) {
                    ui.badFeedback();
                    selectToken = ui.askPlayerToken(i);
                }
            }

            Player player = new Player(selectToken, true);

            players.add(i, player);

            movePlayer(players.get(i), 0);
        }
    }

    public static void setToken() {

    }

    public static void setArcs() {
        //create collection of random numbers
        int numberDoors = random.nextInt(board.getSize() / 3) + 2;

        ArrayList<Integer> doors = new ArrayList<>(numberDoors);
        for (int i = 0; i < board.getSize(); i++) {
            doors.add(i);
        }
        Collections.shuffle(doors);

        //Create the arcs that connect squares
        int entry, exit;
        for (int j = 0, l = 1; j < numberDoors; j += 2, l++) {
            entry = j;
            exit = j + 1;
            Arc arc = new Arc(l, board, doors.get(entry), doors.get(exit));

            board.getBoard()[doors.get(entry)].setArc(arc);

            board.getBoard()[doors.get(exit)].setArc(arc);
        }
        //Print ONLY for testing
        ui.printArcs(doors);         
    }

    //Game Play
    public static void play() {
        ui.printBoard(board);

        boolean win = false;
        //Round
        do {
            //Turns
            for (int playerTurn = 0; playerTurn < players.size(); playerTurn++) {

                ui.askRoll(players.get(playerTurn));

                rollDice(players.get(playerTurn));

                //movement(player, board);
                arcMovement(players.get(playerTurn));

                ui.printBoard(board);

                win = checkWin(players.get(playerTurn));
                if (win) {
                    break;
                }
            }
        } while (!win);
        menu();
    }

    public static void movePlayer(Player player, int moveSquare) {
        try {
            //Erase previous position
            player.getPosition().getPlayers().remove(player);
            player.setPosition(board.getBoard()[moveSquare]);
        }
        catch (NullPointerException FirstMove) {
            player.setPosition(board.getBoard()[moveSquare]);
        }catch (ArrayIndexOutOfBoundsException winningMove) {
            player.setPosition(board.getBoard()[board.getBoard().length - 1]);
        }
    }

    public static void rollDice(Player player) {

        int move = random.nextInt(5) + 1;

        movePlayer(player, move + player.getPosition().getIndex() - 1);

        ui.turnFeedback(move, player, player.getPosition());

    }

    public static void arcMovement(Player player) {
        
        if (player.getPosition().getArc() != null) {

            int entry = player.getPosition().getIndex();
            int exit = player.getPosition().getArc().getExit().getIndex();

            //Feedback
            boolean ladder = true;
            if (entry < exit) {
                ladder = true;
            } else if (entry > exit) {
                ladder = false;
            }
            ui.arcFeedback(ladder, entry, exit);

            //Set new position
            movePlayer(player, exit - 1);
        }
        
    }

    public static boolean checkWin(Player player) {
        
        if (player.getPosition().getIndex() >= board.getBoard().length) {
            ui.printPlayerWins(player);
            return true;
        } else {
            return false;
        }
    }
    
}
