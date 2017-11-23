package Data;

public class Square {

    private final int index;
    private Player player;
    private Arc arc;

    public Square(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Arc getArc() {
        return arc;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }

    @Override
    public String toString() {
        String image = String.valueOf(index);
        if (arc == null && player == null) {
            image = String.valueOf(index);
        } else if (arc == null && player != null) {
            image = player.toString() + String.valueOf(index);
        } else if (arc != null && player == null) {
            image = arc.toString() + String.valueOf(index);
        } else if (arc != null && player != null) {
            image = player.toString() + arc.toString() + String.valueOf(index);
        }
        return image;
    }
}