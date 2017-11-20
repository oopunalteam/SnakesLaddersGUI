package Data;

public class Board {

	private int size;
	private Square[] board;

	public Board(int size) {
		this.size = size;

		board = new Square[size];
		for (int i = 1; i <= size; i++) {
			Square square = new Square(i);
			board[i-1] = square;
		}
	}

	public int getSize() {
		return size;
	}

	public Square[] getBoard() {
		return board;
	}

	@Override
	public String toString() {

		int sideLength = (int) Math.sqrt(this.getBoard().length);

		String printBoard = "| ";
		for (int i = this.getBoard().length -1; i >= 0; --i) {

			printBoard = printBoard.concat(String.format("%10s",(this.getBoard()[i] + " | ")));

			if (i % sideLength == 0 && i!= 0) {
				printBoard = printBoard.concat("\n| ");
			}
		}
		printBoard.concat("\n");
		
		return printBoard;
	}
}