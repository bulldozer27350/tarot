package fr.tarot.counting.model;

import java.io.Serializable;

public class Player  implements Serializable {

    private String name;
    private int points;

    public Player() {
    }

    public Player(Player player) {
        this.name = player.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
