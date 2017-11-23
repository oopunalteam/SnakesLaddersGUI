package Data;

public class Player {

    private final char token;
    private boolean human;
    private Square position;

    public Player(char token, boolean human) {
        this.token = token;
        this.human = human;
    }

    public char getToken() {
        return token;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
        this.human = human;
    }

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
        this.position.setPlayer(this);
    }

    @Override
    public String toString() {
        return String.valueOf(this.token);
    }
}