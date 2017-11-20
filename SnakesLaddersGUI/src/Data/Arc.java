package Data;

public class Arc {

	private Square entry;
	private Square exit;
	private String image;

	public Arc(int index, Board board, int entry, int exit) {
		this.entry = board.getBoard()[entry];
		this.exit = board.getBoard()[exit];

		if (entry > exit) {			//Snake
			this.image = " S";
		} else if (entry < exit) {	//Ladder
			this.image  = " L";
		}
		this.image = this.image + String.valueOf(index);
	}

	public Square getExit() {
		return exit;
	}

	@Override
	public String toString() {
		return image;
	}
}
