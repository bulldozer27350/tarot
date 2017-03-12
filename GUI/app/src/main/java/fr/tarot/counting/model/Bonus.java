package fr.tarot.counting.model;

import java.util.ArrayList;
import java.util.List;

public enum Bonus {

	SIMPLE_HAND(true, 20, false, false), 
	DOUBLE_HAND(true, 30, false, false), 
	TRIPLE_HAND(true, 40, false, false), 
	LITTLE_AT_END(true, 10, true, true), 
	MISERY(false, 10, false, false), 
	CHELEM(true, 200, true, false);

	/**
	 * If true, when a player loose a game with this {@link Bonus}, he wins the
	 * bonus points. Otherwise, the points are loosed.
	 */
	private final boolean malus;
	private final int points;
	private final boolean team;
	private final boolean additionnalToContract;
	private List<Player> who = new ArrayList<>();

	private Bonus(boolean malus, int points, boolean team, boolean additionnalToContract) {
		this.malus = malus;
		this.points = points;
		this.team = team;
		this.additionnalToContract = additionnalToContract;
	}

	public boolean isMalus() {
		return malus;
	}

	public int getPoints() {
		return points;
	}

	public boolean isTeam() {
		return team;
	}

	public boolean isAdditionnalToContract() {
		return additionnalToContract;
	}

	public List<Player> getWho() {
		return who;
	}

	public void setWho(List<Player> who) {
		this.who = who;
	}
	
	
}
