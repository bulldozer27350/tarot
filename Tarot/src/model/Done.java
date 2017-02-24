package model;

import java.util.ArrayList;
import java.util.List;

public class Done {

	private List<Player> players = new ArrayList<>();
	private List<Player> deads = new ArrayList<>();
	private Player giver;
	private Player taker;
	private Player called;
	private DoneType type;
	private KingColor calledColor;
	private List<Bonus> bonuses = new ArrayList<>();
	private boolean littleForTaker;
	private boolean twentyOneForPlayer;
	private boolean excuseForPlayer;
	private int pointsForTakerTeam;
	
	public boolean isLittleForTaker() {
		return littleForTaker;
	}
	public void setLittleForTaker(boolean littleForTaker) {
		this.littleForTaker = littleForTaker;
	}
	public boolean isTwentyOneForPlayer() {
		return twentyOneForPlayer;
	}
	public void setTwentyOneForPlayer(boolean twentyOneForPlayer) {
		this.twentyOneForPlayer = twentyOneForPlayer;
	}
	public boolean isExcuseForPlayer() {
		return excuseForPlayer;
	}
	public void setExcuseForPlayer(boolean excuseForPlayer) {
		this.excuseForPlayer = excuseForPlayer;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Player getGiver() {
		return giver;
	}
	public void setGiver(Player giver) {
		this.giver = giver;
	}
	public Player getTaker() {
		return taker;
	}
	public void setTaker(Player taker) {
		this.taker = taker;
	}
	public Player getCalled() {
		return called;
	}
	public void setCalled(Player called) {
		this.called = called;
	}
	public DoneType getType() {
		return type;
	}
	public void setType(DoneType type) {
		this.type = type;
	}
	public KingColor getCalledColor() {
		return calledColor;
	}
	public void setCalledColor(KingColor calledColor) {
		this.calledColor = calledColor;
	}
	public List<Bonus> getBonuses() {
		return bonuses;
	}
	public void setBonuses(List<Bonus> bonuses) {
		this.bonuses = bonuses;
	}
	public List<Player> getDeads() {
		return deads;
	}
	public void setDeads(List<Player> deads) {
		this.deads = deads;
	}
	public int getPointsForTakerTeam() {
		return pointsForTakerTeam;
	}
	public void setPointsForTakerTeam(int pointsForTakerTeam) {
		this.pointsForTakerTeam = pointsForTakerTeam;
	}
}
