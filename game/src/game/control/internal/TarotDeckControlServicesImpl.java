package game.control.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import game.control.DoneControlServices;
import game.control.PlayerControlServices;
import game.control.TarotDeckControlServices;
import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Done;
import game.domain.Player;

public class TarotDeckControlServicesImpl implements TarotDeckControlServices {

	private DoneControlServices doneControlServices;
	private PlayerControlServices playerControlServices;

	public TarotDeckControlServicesImpl(PlayerControlServices playerControlServicesArg, DoneControlServices doneControlServicesArg) {
		this.doneControlServices = doneControlServicesArg;
		this.playerControlServices = playerControlServicesArg;
	}
	
	@Override
	public void createDeck(Done done) {
		doneControlServices.addCard(done,new Card(Card.Color.AUTRE, Card.Name.EXCUSE, 4.5, null));
		
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.UN_ATOUT, 4.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.DEUX_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.TROIS_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.QUATRE_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.CINQ_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.SIX_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.SEPT_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.HUIT_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.NEUF_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.DIX_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.ONZE_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.DOUZE_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.TREIZE_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.QUATORZE_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.QUINZE_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.SEIZE_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.DIX_SEPT_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.DIX_HUIT_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.DIX_NEUF_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.VINGT_ATOUT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.ATOUT, Card.Name.VING_ET_UN_ATOUT, 4.5, null));
		
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.AS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.DEUX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.TROIS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.QUATRE, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.CINQ, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.SIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.SEPT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.HUIT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.NEUF, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.DIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.VALET, 1.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.CAVALIER, 2.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.DAME, 3.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.PIQUE, Card.Name.ROI, 4.5, null));
		
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.AS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.DEUX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.TROIS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.QUATRE, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.CINQ, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.SIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.SEPT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.HUIT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.NEUF, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.DIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.VALET, 1.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.CAVALIER, 2.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.DAME, 3.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.COEUR, Card.Name.ROI, 4.5, null));
		
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.AS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.DEUX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.TROIS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.QUATRE, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.CINQ, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.SIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.SEPT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.HUIT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.NEUF, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.DIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.VALET, 1.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.CAVALIER, 2.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.DAME, 3.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.CARREAU, Card.Name.ROI, 4.5, null));
		
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.AS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.DEUX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.TROIS, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.QUATRE, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.CINQ, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.SIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.SEPT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.HUIT, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.NEUF, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.DIX, 0.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.VALET, 1.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.CAVALIER, 2.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.DAME, 3.5, null));
		doneControlServices.addCard(done,new Card(Card.Color.TREFLE, Card.Name.ROI, 4.5, null));
	}

	@Override
	public void shuffle(Done done) {
		Collections.shuffle(done.getCards());
	}
	
	@Override
	public void distribute(Done done) {
		int dogCardsNumber = 6;
		if (done.getPlayers().size() == 5){
			dogCardsNumber = 3;
		}
		for (int i = 0 ; i < dogCardsNumber ; i++){
			done.getDog().add(done.getCards().remove(0));
		}
		doneControlServices.distribute(done);
		for (Player player : done.getPlayers()){
			this.sortCards(player);
		}
		
		showPlayersCards(done);
	}

	private void showPlayersCards(Done done) {
		for (Player player : done.getPlayers()){
			System.out.println(player.getName());
			for (Card card : player.getHand()){
				System.out.println(card);
			}
			System.out.println();
		}
	}

	private void sortCards(Player player) {
		List<Card> piques = new ArrayList<>(player.getHand().stream().filter(c->c.getColor() == Color.PIQUE).collect(Collectors.toList()));
		List<Card> coeurs = new ArrayList<>(player.getHand().stream().filter(c->c.getColor() == Color.COEUR).collect(Collectors.toList()));
		List<Card> carreaux = new ArrayList<>(player.getHand().stream().filter(c->c.getColor() == Color.CARREAU).collect(Collectors.toList()));
		List<Card> trefles = new ArrayList<>(player.getHand().stream().filter(c->c.getColor() == Color.TREFLE).collect(Collectors.toList()));
		List<Card> atouts = new ArrayList<>(player.getHand().stream().filter(c->c.getColor() == Color.ATOUT).collect(Collectors.toList()));
		List<Card> excuses = new ArrayList<>(player.getHand().stream().filter(c->c.getColor() == Color.AUTRE).collect(Collectors.toList()));
		piques.sort((c1,c2)->c1.getName().getPower() - c2.getName().getPower());
		coeurs.sort((c1,c2)->c1.getName().getPower() - c2.getName().getPower());
		carreaux.sort((c1,c2)->c1.getName().getPower() - c2.getName().getPower());
		trefles.sort((c1,c2)->c1.getName().getPower() - c2.getName().getPower());
		atouts.sort((c1,c2)->c1.getName().getPower() - c2.getName().getPower());
		List<Card> allPlayerCards = new ArrayList<>();
		allPlayerCards.addAll(piques);
		allPlayerCards.addAll(coeurs);
		allPlayerCards.addAll(carreaux);
		allPlayerCards.addAll(trefles);
		allPlayerCards.addAll(atouts);
		allPlayerCards.addAll(excuses);
		player.setHand(allPlayerCards);
	}

	@Override
	public void play(Done done) {
		List<Card> fold = new ArrayList<>();
		List<Card> previousFold = new ArrayList<>();
		List<Card> previousFolds = new ArrayList<>();
		int neededFoldNumber = done.getPlayers().get(0).getHand().size();
		int playersNumber = done.getPlayers().size();
		for (int i = 0 ; i < neededFoldNumber ; i++){
			previousFold = fold;
			fold = new ArrayList<>();
			previousFolds.addAll(fold);
			for(int j = 0 ; j < playersNumber ; j++){
				Card playCard = playerControlServices.playCard(done, getNextPlayer(done, fold, previousFold), fold, previousFolds);
				System.out.println(playCard);
			}
			System.out.println("");
		}
		whoWon(fold);
	}
	
	private Player getNextPlayer(Done done, List<Card> fold, List<Card> previousFold){
		Player nextPlayer = null;
		// if the fold over
		if (!fold.isEmpty()) {
			// We have to find last player index (fold's latest added card's owner)
			Card latestCard = fold.get(fold.size()-1);
			Player latestPlayer = latestCard.getOwner();
			int index = 0;
			for (int i = 0 ; i < done.getPlayers().size() ; i++){
				if (done.getPlayers().get(i).equals(latestPlayer)){
					index = i;
				}
			}
			// We have found the latest player. The next one is i++%player size.
			nextPlayer = done.getPlayers().get((index + 1)%done.getPlayers().size());
		} else if (previousFold.isEmpty()){
			nextPlayer = done.getNextPlayer();
		} else {
			// the fold is over, the next player is the fold winner
			nextPlayer = this.whoWon(previousFold);
			done.setNextPlayer(nextPlayer);
		}
		
		return nextPlayer;
	}
	
	private Player whoWon(List<Card> fold){
		int highestValue = 0;
		Color foldColor = fold.get(0).getColor();
		if (foldColor == Color.AUTRE){
			foldColor = fold.get(1).getColor();
		}
		Card strongestCard = null;
		for (Card card : fold){
			if (card.getName().getPower() > highestValue && (card.getColor() == foldColor || card.getColor() == Color.ATOUT)){
				highestValue = card.getName().getPower();
				strongestCard = card;
			}
		}
		Player owner = strongestCard.getOwner();
		owner.getFolds().add(fold);
		return owner;
	}

}
