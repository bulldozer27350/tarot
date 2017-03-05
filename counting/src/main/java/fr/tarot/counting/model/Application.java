package fr.tarot.counting.model;

import java.util.ArrayList;
import java.util.List;

public class Application {

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
