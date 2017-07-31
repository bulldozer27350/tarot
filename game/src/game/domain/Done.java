package game.domain;

import java.util.ArrayList;
import java.util.List;

public class Done {
	
	private List<Card> cards = new ArrayList<>();
	private List<Card> dog = new ArrayList<>();
	private List<Player> players = new ArrayList<>();
	private Player nextPlayer;

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Card> getDog() {
		return dog;
	}

	public void setDog(List<Card> dog) {
		this.dog = dog;
	}

	public Player getNextPlayer() {
		return this.nextPlayer;
	}
	
	public void setNextPlayer(game.domain.Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	
	
}
