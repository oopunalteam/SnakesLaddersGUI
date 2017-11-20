package Data;

public class Player {

	private char token;
	private Square position;

	public Player(char token) {
		this.token = token;
	}

	public Square getPosition() {
		return position;
	}

	public void setPosition(Square position) {
		this.position = position;
		position.setImage(this + position.getImage());
	}

	@Override
	public String toString() {
		return String.valueOf(this.token);
	}
}