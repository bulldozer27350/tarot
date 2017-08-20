package game.domain;

import java.util.ArrayList;
import java.util.List;

public class Done {

    private List<Card> cards = new ArrayList<>();
    private List<Card> dog = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Player nextPlayer;
    private List<Fold> folds = new ArrayList<>();

    public List<Card> getCards() {
	return this.cards;
    }

    public void setCards(final List<Card> cards) {
	this.cards = cards;
    }

    public List<Player> getPlayers() {
	return this.players;
    }

    public void setPlayers(final List<Player> players) {
	this.players = players;
    }

    public List<Card> getDog() {
	return this.dog;
    }

    public void setDog(final List<Card> dog) {
	this.dog = dog;
    }

    public Player getNextPlayer() {
	return this.nextPlayer;
    }

    public void setNextPlayer(final game.domain.Player nextPlayer) {
	this.nextPlayer = nextPlayer;
    }

    public List<Fold> getFolds() {
	return this.folds;
    }

    public void setFolds(final List<Fold> folds) {
	this.folds = folds;
    }

}
