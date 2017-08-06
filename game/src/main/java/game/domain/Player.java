package game.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

	public enum Team {ATTACK, DEFENSE, UNKNOWN}
	
	private List<Card> hand = new ArrayList<>();
	private List<Fold> folds= new ArrayList<>();
	private String name;
	private Team team;

	public Player() {
	}

	public Player(String nameArg){
		this.name = nameArg;
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	
	public List<Fold> getFolds() {
		return folds;
	}
	
	public void setFolds(List<Fold> folds) {
		this.folds = folds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
