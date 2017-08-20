import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game.control.DoneControlServices;
import game.control.GameControlServices;
import game.domain.Card;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;
import game.domain.Player.Team;

public class MyGame {

    private final DoneControlServices doneControlServices;
    private final GameControlServices gameControlServices;
    private Done done;

    public MyGame(final DoneControlServices doneControlServicesArg, final GameControlServices gameControlServicesArg) {
	this.doneControlServices = doneControlServicesArg;
	this.gameControlServices = gameControlServicesArg;
    }

    public void initialize() {
	final int doneNumber = 100;
	this.done = new Done();

	final Player sebastien = new Player("Sebastien");
	final Player virginie = new Player("Virginie");
	final Player alexandre = new Player("Alexandre");
	final Player marco = new Player("Marco");
	final Player antoine = new Player("Antoine");

	this.doneControlServices.addPlayer(this.done, sebastien);
	this.doneControlServices.addPlayer(this.done, virginie);
	this.doneControlServices.addPlayer(this.done, alexandre);
	this.doneControlServices.addPlayer(this.done, marco);
	this.doneControlServices.addPlayer(this.done, antoine);

	this.gameControlServices.createDeck(this.done);
	this.gameControlServices.shuffle(this.done);
	for (int j = 0; j < doneNumber; j++) {
	    for (final Player player : this.done.getPlayers()) {
		player.setTeam(Team.UNKNOWN);
	    }

	    // FIXME: later, we could imagine to
	    // initialize these properties in an other way ...
	    sebastien.setTeam(Team.ATTACK);
	    virginie.setTeam(Team.ATTACK);
	    alexandre.setTeam(Team.DEFENSE);
	    marco.setTeam(Team.DEFENSE);
	    antoine.setTeam(Team.DEFENSE);

	    this.gameControlServices.distribute(this.done);

	    // TODO : set this line in a control service layer
	    this.done.setNextPlayer(sebastien);

	    // for(Player player : done.getPlayers()){
	    // System.out.println(player.getName());
	    // player.getHand().stream().forEach(card->System.out.println(card));
	    // System.out.println("");
	    // }
	    System.out.println("Chien : ");
	    final List<Card> dogCards = new ArrayList<>(this.done.getDog());
	    dogCards.stream().forEach(card -> System.out.println(card));

	    this.gameControlServices.play(this.done);

	    // Show final scores
	    final List<Card> doneCards = new ArrayList<>();
	    // System.out.println("Scores finaux : ");

	    double dogScore = 0;
	    for (int i = 0; i < dogCards.size(); i++) {
		final Card card = this.done.getDog().get(0);
		card.setOwner(null);
		dogScore += card.getPointsValue();
		doneCards.add(this.done.getDog().remove(0));
	    }
	    System.out.println("(chien : \t" + dogScore + "\t points)");
	    System.out.println("");

	    this.done.setCards(doneCards);

	    double score = 0;
	    final List<Player> attackPlayers = new ArrayList<>();
	    for (final Player player : this.done.getPlayers().stream().filter(e -> e.getTeam() == Team.ATTACK)
		    .collect(Collectors.toList())) {
		attackPlayers.add(player);
		for (final Fold fold : player.getFolds()) {
		    final List<Card> playerFold = new ArrayList<>(fold.getCards());
		    for (int i = 0; i < playerFold.size(); i++) {
			final Card card = fold.getCards().get(0);
			score += card.getPointsValue();
			doneCards.add(fold.getCards().remove(0));
		    }
		}
	    }
	    score += dogScore;
	    System.out
		    .println("Attack team " + Arrays.toString(attackPlayers.toArray()) + " : \t" + score + "\t points");

	    score = 0;
	    final List<Player> defensePlayers = new ArrayList<>();
	    for (final Player player : this.done.getPlayers().stream().filter(e -> e.getTeam() == Team.DEFENSE)
		    .collect(Collectors.toList())) {
		defensePlayers.add(player);
		for (final Fold fold : player.getFolds()) {
		    final List<Card> playerFold = new ArrayList<>(fold.getCards());
		    for (int i = 0; i < playerFold.size(); i++) {
			final Card card = fold.getCards().get(0);
			score += card.getPointsValue();
			doneCards.add(fold.getCards().remove(0));
		    }
		}
	    }
	    System.out.println(
		    "Defense team " + Arrays.toString(defensePlayers.toArray()) + " : \t" + score + "\t points");
	}

    }

}
