package game.domain;

import java.util.ArrayList;
import java.util.List;

import game.domain.Card.Color;

public class Fold {

	private List<Card> cards = new ArrayList<>();
	private Player winner;
	private Color playedColor;
	
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public Color getPlayedColor() {
		return playedColor;
	}
	public void setPlayedColor(Color playedColor) {
		this.playedColor = playedColor;
	}
	
	
}
