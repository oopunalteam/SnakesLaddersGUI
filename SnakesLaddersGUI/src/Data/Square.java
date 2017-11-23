package Data;

import java.util.ArrayList;

public class Square {

    private final int index;
    private ArrayList<Player> players;
    private Arc arc;
    String image;

    public Square(int index) {
        this.index = index;
        this.players = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Player player) {
        this.players.add(player);
    }

    public Arc getArc() {
        return arc;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }

    @Override
    public String toString() {
        image = String.valueOf(index);
        if (arc == null && players.isEmpty()) {
            image = String.valueOf(index);
        } else if (arc == null && !players.isEmpty()) {
            image = players.toString() + String.valueOf(index);
        } else if (arc != null && players.isEmpty()) {
            image = arc.toString() + String.valueOf(index);
        } else if (arc != null && !players.isEmpty()) {
            image = players.toString() + arc.toString() + String.valueOf(index);
        }
        return image;
    }
}