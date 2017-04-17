package fr.tarot.counting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Application implements Serializable{

    private static Application instance;

    private Application(){

    }

    public static Application getInstance(){
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    };

    private List<Player> players = new ArrayList<>();
    private List<Done> dones = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Done> getDones() {
        return dones;
    }

    public void setDones(List<Done> dones) {
        this.dones = dones;
    }

}
