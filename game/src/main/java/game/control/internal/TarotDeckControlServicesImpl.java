package game.control.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import game.control.DoneControlServices;
import game.control.FoldControlServices;
import game.control.PlayerControlServices;
import game.control.TarotDeckControlServices;
import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;

public class TarotDeckControlServicesImpl implements TarotDeckControlServices {

	private final DoneControlServices doneControlServices;
	private final PlayerControlServices playerControlServices;
	private final FoldControlServices foldControlServices;

	public static final Card EXCUSE = new Card(Card.Color.AUTRE, Card.Name.EXCUSE, 4.5, null);

	public static final Card UN_ATOUT = new Card(Card.Color.ATOUT, Card.Name.UN_ATOUT, 4.5, null);
	public static final Card DEUX_ATOUT = new Card(Card.Color.ATOUT, Card.Name.DEUX_ATOUT, 0.5, null);
	public static final Card TROIS_ATOUT = new Card(Card.Color.ATOUT, Card.Name.TROIS_ATOUT, 0.5, null);
	public static final Card QUATRE_ATOUT = new Card(Card.Color.ATOUT, Card.Name.QUATRE_ATOUT, 0.5, null);
	public static final Card CINQ_ATOUT = new Card(Card.Color.ATOUT, Card.Name.CINQ_ATOUT, 0.5, null);
	public static final Card SIX_ATOUT = new Card(Card.Color.ATOUT, Card.Name.SIX_ATOUT, 0.5, null);
	public static final Card SEPT_ATOUT = new Card(Card.Color.ATOUT, Card.Name.SEPT_ATOUT, 0.5, null);
	public static final Card HUIT_ATOUT = new Card(Card.Color.ATOUT, Card.Name.HUIT_ATOUT, 0.5, null);
	public static final Card NEUF_ATOUT = new Card(Card.Color.ATOUT, Card.Name.NEUF_ATOUT, 0.5, null);
	public static final Card DIX_ATOUT = new Card(Card.Color.ATOUT, Card.Name.DIX_ATOUT, 0.5, null);
	public static final Card ONZE_ATOUT = new Card(Card.Color.ATOUT, Card.Name.ONZE_ATOUT, 0.5, null);
	public static final Card DOUZE_ATOUT = new Card(Card.Color.ATOUT, Card.Name.DOUZE_ATOUT, 0.5, null);
	public static final Card TREIZE_ATOUT = new Card(Card.Color.ATOUT, Card.Name.TREIZE_ATOUT, 0.5, null);
	public static final Card QUATORZE_ATOUT = new Card(Card.Color.ATOUT, Card.Name.QUATORZE_ATOUT, 0.5, null);
	public static final Card QUINZE_ATOUT = new Card(Card.Color.ATOUT, Card.Name.QUINZE_ATOUT, 0.5, null);
	public static final Card SEIZE_ATOUT = new Card(Card.Color.ATOUT, Card.Name.SEIZE_ATOUT, 0.5, null);
	public static final Card DIX_SEPT_ATOUT = new Card(Card.Color.ATOUT, Card.Name.DIX_SEPT_ATOUT, 0.5, null);
	public static final Card DIX_HUIT_ATOUT = new Card(Card.Color.ATOUT, Card.Name.DIX_HUIT_ATOUT, 0.5, null);
	public static final Card DIX_NEUF_ATOUT = new Card(Card.Color.ATOUT, Card.Name.DIX_NEUF_ATOUT, 0.5, null);
	public static final Card VINGT_ATOUT = new Card(Card.Color.ATOUT, Card.Name.VINGT_ATOUT, 0.5, null);
	public static final Card VINGT_ET_UN_ATOUT = new Card(Card.Color.ATOUT, Card.Name.VINGT_ET_UN_ATOUT, 4.5, null);

	public static final Card AS_PIQUE = new Card(Card.Color.PIQUE, Card.Name.AS, 0.5, null);
	public static final Card DEUX_PIQUE = new Card(Card.Color.PIQUE, Card.Name.DEUX, 0.5, null);
	public static final Card TROIS_PIQUE = new Card(Card.Color.PIQUE, Card.Name.TROIS, 0.5, null);
	public static final Card QUATRE_PIQUE = new Card(Card.Color.PIQUE, Card.Name.QUATRE, 0.5, null);
	public static final Card CINQ_PIQUE = new Card(Card.Color.PIQUE, Card.Name.CINQ, 0.5, null);
	public static final Card SIX_PIQUE = new Card(Card.Color.PIQUE, Card.Name.SIX, 0.5, null);
	public static final Card SEPT_PIQUE = new Card(Card.Color.PIQUE, Card.Name.SEPT, 0.5, null);
	public static final Card HUIT_PIQUE = new Card(Card.Color.PIQUE, Card.Name.HUIT, 0.5, null);
	public static final Card NEUF_PIQUE = new Card(Card.Color.PIQUE, Card.Name.NEUF, 0.5, null);
	public static final Card DIX_PIQUE = new Card(Card.Color.PIQUE, Card.Name.DIX, 0.5, null);
	public static final Card VALET_PIQUE = new Card(Card.Color.PIQUE, Card.Name.VALET, 1.5, null);
	public static final Card CAVALIER_PIQUE = new Card(Card.Color.PIQUE, Card.Name.CAVALIER, 2.5, null);
	public static final Card DAME_PIQUE = new Card(Card.Color.PIQUE, Card.Name.DAME, 3.5, null);
	public static final Card ROI_PIQUE = new Card(Card.Color.PIQUE, Card.Name.ROI, 4.5, null);

	public static final Card AS_COEUR = new Card(Card.Color.COEUR, Card.Name.AS, 0.5, null);
	public static final Card DEUX_COEUR = new Card(Card.Color.COEUR, Card.Name.DEUX, 0.5, null);
	public static final Card TROIS_COEUR = new Card(Card.Color.COEUR, Card.Name.TROIS, 0.5, null);
	public static final Card QUATRE_COEUR = new Card(Card.Color.COEUR, Card.Name.QUATRE, 0.5, null);
	public static final Card CINQ_COEUR = new Card(Card.Color.COEUR, Card.Name.CINQ, 0.5, null);
	public static final Card SIX_COEUR = new Card(Card.Color.COEUR, Card.Name.SIX, 0.5, null);
	public static final Card SEPT_COEUR = new Card(Card.Color.COEUR, Card.Name.SEPT, 0.5, null);
	public static final Card HUIT_COEUR = new Card(Card.Color.COEUR, Card.Name.HUIT, 0.5, null);
	public static final Card NEUF_COEUR = new Card(Card.Color.COEUR, Card.Name.NEUF, 0.5, null);
	public static final Card DIX_COEUR = new Card(Card.Color.COEUR, Card.Name.DIX, 0.5, null);
	public static final Card VALET_COEUR = new Card(Card.Color.COEUR, Card.Name.VALET, 1.5, null);
	public static final Card CAVALIER_COEUR = new Card(Card.Color.COEUR, Card.Name.CAVALIER, 2.5, null);
	public static final Card DAME_COEUR = new Card(Card.Color.COEUR, Card.Name.DAME, 3.5, null);
	public static final Card ROI_COEUR = new Card(Card.Color.COEUR, Card.Name.ROI, 4.5, null);

	public static final Card AS_CARREAU = new Card(Card.Color.CARREAU, Card.Name.AS, 0.5, null);
	public static final Card DEUX_CARREAU = new Card(Card.Color.CARREAU, Card.Name.DEUX, 0.5, null);
	public static final Card TROIS_CARREAU = new Card(Card.Color.CARREAU, Card.Name.TROIS, 0.5, null);
	public static final Card QUATRE_CARREAU = new Card(Card.Color.CARREAU, Card.Name.QUATRE, 0.5, null);
	public static final Card CINQ_CARREAU = new Card(Card.Color.CARREAU, Card.Name.CINQ, 0.5, null);
	public static final Card SIX_CARREAU = new Card(Card.Color.CARREAU, Card.Name.SIX, 0.5, null);
	public static final Card SEPT_CARREAU = new Card(Card.Color.CARREAU, Card.Name.SEPT, 0.5, null);
	public static final Card HUIT_CARREAU = new Card(Card.Color.CARREAU, Card.Name.HUIT, 0.5, null);
	public static final Card NEUF_CARREAU = new Card(Card.Color.CARREAU, Card.Name.NEUF, 0.5, null);
	public static final Card DIX_CARREAU = new Card(Card.Color.CARREAU, Card.Name.DIX, 0.5, null);
	public static final Card VALET_CARREAU = new Card(Card.Color.CARREAU, Card.Name.VALET, 1.5, null);
	public static final Card CAVALIER_CARREAU = new Card(Card.Color.CARREAU, Card.Name.CAVALIER, 2.5, null);
	public static final Card DAME_CARREAU = new Card(Card.Color.CARREAU, Card.Name.DAME, 3.5, null);
	public static final Card ROI_CARREAU = new Card(Card.Color.CARREAU, Card.Name.ROI, 4.5, null);

	public static final Card AS_TREFLE = new Card(Card.Color.TREFLE, Card.Name.AS, 0.5, null);
	public static final Card DEUX_TREFLE = new Card(Card.Color.TREFLE, Card.Name.DEUX, 0.5, null);
	public static final Card TROIS_TREFLE = new Card(Card.Color.TREFLE, Card.Name.TROIS, 0.5, null);
	public static final Card QUATRE_TREFLE = new Card(Card.Color.TREFLE, Card.Name.QUATRE, 0.5, null);
	public static final Card CINQ_TREFLE = new Card(Card.Color.TREFLE, Card.Name.CINQ, 0.5, null);
	public static final Card SIX_TREFLE = new Card(Card.Color.TREFLE, Card.Name.SIX, 0.5, null);
	public static final Card SEPT_TREFLE = new Card(Card.Color.TREFLE, Card.Name.SEPT, 0.5, null);
	public static final Card HUIT_TREFLE = new Card(Card.Color.TREFLE, Card.Name.HUIT, 0.5, null);
	public static final Card NEUF_TREFLE = new Card(Card.Color.TREFLE, Card.Name.NEUF, 0.5, null);
	public static final Card DIX_TREFLE = new Card(Card.Color.TREFLE, Card.Name.DIX, 0.5, null);
	public static final Card VALET_TREFLE = new Card(Card.Color.TREFLE, Card.Name.VALET, 1.5, null);
	public static final Card CAVALIER_TREFLE = new Card(Card.Color.TREFLE, Card.Name.CAVALIER, 2.5, null);
	public static final Card DAME_TREFLE = new Card(Card.Color.TREFLE, Card.Name.DAME, 3.5, null);
	public static final Card ROI_TREFLE = new Card(Card.Color.TREFLE, Card.Name.ROI, 4.5, null);

	public TarotDeckControlServicesImpl(PlayerControlServices playerControlServicesArg,
			DoneControlServices doneControlServicesArg, FoldControlServices foldControlServicesArg) {
		this.doneControlServices = doneControlServicesArg;
		this.playerControlServices = playerControlServicesArg;
		this.foldControlServices = foldControlServicesArg;
	}

	@Override
	public void createDeck(Done done) {
		doneControlServices.addCard(done, EXCUSE);

		doneControlServices.addCard(done, UN_ATOUT);
		doneControlServices.addCard(done, DEUX_ATOUT);
		doneControlServices.addCard(done, TROIS_ATOUT);
		doneControlServices.addCard(done, QUATRE_ATOUT);
		doneControlServices.addCard(done, CINQ_ATOUT);
		doneControlServices.addCard(done, SIX_ATOUT);
		doneControlServices.addCard(done, SEPT_ATOUT);
		doneControlServices.addCard(done, HUIT_ATOUT);
		doneControlServices.addCard(done, NEUF_ATOUT);
		doneControlServices.addCard(done, DIX_ATOUT);
		doneControlServices.addCard(done, ONZE_ATOUT);
		doneControlServices.addCard(done, DOUZE_ATOUT);
		doneControlServices.addCard(done, TREIZE_ATOUT);
		doneControlServices.addCard(done, QUATORZE_ATOUT);
		doneControlServices.addCard(done, QUINZE_ATOUT);
		doneControlServices.addCard(done, SEIZE_ATOUT);
		doneControlServices.addCard(done, DIX_SEPT_ATOUT);
		doneControlServices.addCard(done, DIX_HUIT_ATOUT);
		doneControlServices.addCard(done, DIX_NEUF_ATOUT);
		doneControlServices.addCard(done, VINGT_ATOUT);
		doneControlServices.addCard(done, VINGT_ET_UN_ATOUT);

		doneControlServices.addCard(done, AS_PIQUE);
		doneControlServices.addCard(done, DEUX_PIQUE);
		doneControlServices.addCard(done, TROIS_PIQUE);
		doneControlServices.addCard(done, QUATRE_PIQUE);
		doneControlServices.addCard(done, CINQ_PIQUE);
		doneControlServices.addCard(done, SIX_PIQUE);
		doneControlServices.addCard(done, SEPT_PIQUE);
		doneControlServices.addCard(done, HUIT_PIQUE);
		doneControlServices.addCard(done, NEUF_PIQUE);
		doneControlServices.addCard(done, DIX_PIQUE);
		doneControlServices.addCard(done, VALET_PIQUE);
		doneControlServices.addCard(done, CAVALIER_PIQUE);
		doneControlServices.addCard(done, DAME_PIQUE);
		doneControlServices.addCard(done, ROI_PIQUE);

		doneControlServices.addCard(done, AS_COEUR);
		doneControlServices.addCard(done, DEUX_COEUR);
		doneControlServices.addCard(done, TROIS_COEUR);
		doneControlServices.addCard(done, QUATRE_COEUR);
		doneControlServices.addCard(done, CINQ_COEUR);
		doneControlServices.addCard(done, SIX_COEUR);
		doneControlServices.addCard(done, SEPT_COEUR);
		doneControlServices.addCard(done, HUIT_COEUR);
		doneControlServices.addCard(done, NEUF_COEUR);
		doneControlServices.addCard(done, DIX_COEUR);
		doneControlServices.addCard(done, VALET_COEUR);
		doneControlServices.addCard(done, CAVALIER_COEUR);
		doneControlServices.addCard(done, DAME_COEUR);
		doneControlServices.addCard(done, ROI_COEUR);

		doneControlServices.addCard(done, AS_CARREAU);
		doneControlServices.addCard(done, DEUX_CARREAU);
		doneControlServices.addCard(done, TROIS_CARREAU);
		doneControlServices.addCard(done, QUATRE_CARREAU);
		doneControlServices.addCard(done, CINQ_CARREAU);
		doneControlServices.addCard(done, SIX_CARREAU);
		doneControlServices.addCard(done, SEPT_CARREAU);
		doneControlServices.addCard(done, HUIT_CARREAU);
		doneControlServices.addCard(done, NEUF_CARREAU);
		doneControlServices.addCard(done, DIX_CARREAU);
		doneControlServices.addCard(done, VALET_CARREAU);
		doneControlServices.addCard(done, CAVALIER_CARREAU);
		doneControlServices.addCard(done, DAME_CARREAU);
		doneControlServices.addCard(done, ROI_CARREAU);

		doneControlServices.addCard(done, AS_TREFLE);
		doneControlServices.addCard(done, DEUX_TREFLE);
		doneControlServices.addCard(done, TROIS_TREFLE);
		doneControlServices.addCard(done, QUATRE_TREFLE);
		doneControlServices.addCard(done, CINQ_TREFLE);
		doneControlServices.addCard(done, SIX_TREFLE);
		doneControlServices.addCard(done, SEPT_TREFLE);
		doneControlServices.addCard(done, HUIT_TREFLE);
		doneControlServices.addCard(done, NEUF_TREFLE);
		doneControlServices.addCard(done, DIX_TREFLE);
		doneControlServices.addCard(done, VALET_TREFLE);
		doneControlServices.addCard(done, CAVALIER_TREFLE);
		doneControlServices.addCard(done, DAME_TREFLE);
		doneControlServices.addCard(done, ROI_TREFLE);
	}

	@Override
	public void shuffle(Done done) {
		Collections.shuffle(done.getCards());
	}

	@Override
	public void distribute(Done done) {
		if (!isTest()) {
			int dogCardsNumber = 6;
			if (done.getPlayers().size() == 5) {
				dogCardsNumber = 3;
			}
			for (int i = 0; i < dogCardsNumber; i++) {
				done.getDog().add(done.getCards().remove(0));
			}
			doneControlServices.distribute(done);
			for (Player player : done.getPlayers()) {
				this.sortCards(player);
			}
		} else {
			testDistribute(done);
		}
		showPlayersCards(done);
	}

	private boolean isTest() {
		return true;
	}

	private void testDistribute(Done done) {
		Player sebastien = done.getPlayers().stream().filter(p -> p.getName().equals("Sebastien"))
				.collect(Collectors.toList()).get(0);
		Player virginie = done.getPlayers().stream().filter(p -> p.getName().equals("Virginie"))
				.collect(Collectors.toList()).get(0);
		Player alexandre = done.getPlayers().stream().filter(p -> p.getName().equals("Alexandre"))
				.collect(Collectors.toList()).get(0);
		Player marco = done.getPlayers().stream().filter(p -> p.getName().equals("Marco")).collect(Collectors.toList())
				.get(0);
		Player antoine = done.getPlayers().stream().filter(p -> p.getName().equals("Antoine"))
				.collect(Collectors.toList()).get(0);

		sebastien.getHand().add(CINQ_PIQUE);
		sebastien.getHand().add(DEUX_COEUR);
		sebastien.getHand().add(TROIS_COEUR);
		sebastien.getHand().add(QUATRE_COEUR);
		sebastien.getHand().add(ROI_COEUR);
		sebastien.getHand().add(ROI_TREFLE);
		sebastien.getHand().add(UN_ATOUT);
		sebastien.getHand().add(TROIS_ATOUT);
		sebastien.getHand().add(NEUF_ATOUT);
		sebastien.getHand().add(ONZE_ATOUT);
		sebastien.getHand().add(TREIZE_ATOUT);
		sebastien.getHand().add(DIX_SEPT_ATOUT);
		sebastien.getHand().add(DIX_HUIT_ATOUT);
		sebastien.getHand().add(VINGT_ET_UN_ATOUT);
		sebastien.getHand().add(EXCUSE);

		virginie.getHand().add(DEUX_PIQUE);
		virginie.getHand().add(VALET_PIQUE);
		virginie.getHand().add(CAVALIER_COEUR);
		virginie.getHand().add(DAME_COEUR);
		virginie.getHand().add(SIX_CARREAU);
		virginie.getHand().add(HUIT_CARREAU);
		virginie.getHand().add(DAME_CARREAU);
		virginie.getHand().add(AS_TREFLE);
		virginie.getHand().add(SEPT_TREFLE);
		virginie.getHand().add(NEUF_TREFLE);
		virginie.getHand().add(DEUX_ATOUT);
		virginie.getHand().add(SIX_ATOUT);
		virginie.getHand().add(DIX_ATOUT);
		virginie.getHand().add(DOUZE_ATOUT);
		virginie.getHand().add(DIX_NEUF_ATOUT);

		alexandre.getHand().add(AS_PIQUE);
		alexandre.getHand().add(TROIS_PIQUE);
		alexandre.getHand().add(SEPT_COEUR);
		alexandre.getHand().add(AS_CARREAU);
		alexandre.getHand().add(TROIS_CARREAU);
		alexandre.getHand().add(SEPT_CARREAU);
		alexandre.getHand().add(NEUF_CARREAU);
		alexandre.getHand().add(VALET_CARREAU);
		alexandre.getHand().add(ROI_CARREAU);
		alexandre.getHand().add(TROIS_TREFLE);
		alexandre.getHand().add(SIX_TREFLE);
		alexandre.getHand().add(DIX_TREFLE);
		alexandre.getHand().add(CAVALIER_TREFLE);
		alexandre.getHand().add(CINQ_ATOUT);
		alexandre.getHand().add(SEIZE_ATOUT);

		marco.getHand().add(SIX_PIQUE);
		marco.getHand().add(HUIT_PIQUE);
		marco.getHand().add(DAME_PIQUE);
		marco.getHand().add(ROI_PIQUE);
		marco.getHand().add(AS_COEUR);
		marco.getHand().add(CINQ_COEUR);
		marco.getHand().add(HUIT_COEUR);
		marco.getHand().add(VALET_COEUR);
		marco.getHand().add(DEUX_CARREAU);
		marco.getHand().add(CINQ_CARREAU);
		marco.getHand().add(QUATRE_TREFLE);
		marco.getHand().add(HUIT_TREFLE);
		marco.getHand().add(SEPT_ATOUT);
		marco.getHand().add(QUATORZE_ATOUT);
		marco.getHand().add(VINGT_ATOUT);

		antoine.getHand().add(QUATRE_PIQUE);
		antoine.getHand().add(SEPT_PIQUE);
		antoine.getHand().add(NEUF_PIQUE);
		antoine.getHand().add(DIX_PIQUE);
		antoine.getHand().add(CAVALIER_PIQUE);
		antoine.getHand().add(SIX_COEUR);
		antoine.getHand().add(DIX_COEUR);
		antoine.getHand().add(DIX_CARREAU);
		antoine.getHand().add(CAVALIER_CARREAU);
		antoine.getHand().add(DEUX_TREFLE);
		antoine.getHand().add(CINQ_TREFLE);
		antoine.getHand().add(VALET_TREFLE);
		antoine.getHand().add(DAME_TREFLE);
		antoine.getHand().add(QUATRE_ATOUT);
		antoine.getHand().add(HUIT_ATOUT);
		

		for (Player player : done.getPlayers()) {
			for (Card card : player.getHand()) {
				card.setOwner(player);
			}
		}

		done.getDog().add(NEUF_COEUR);
		done.getDog().add(QUATRE_CARREAU);
		done.getDog().add(QUINZE_ATOUT);
	}

	private void showPlayersCards(Done done) {
		for (Player player : done.getPlayers()) {
			System.out.println(player.getName());
			for (Card card : player.getHand()) {
				System.out.println(card);
			}
			System.out.println();
		}
	}

	private void sortCards(Player player) {
		List<Card> piques = new ArrayList<>(
				player.getHand().stream().filter(c -> c.getColor() == Color.PIQUE).collect(Collectors.toList()));
		List<Card> coeurs = new ArrayList<>(
				player.getHand().stream().filter(c -> c.getColor() == Color.COEUR).collect(Collectors.toList()));
		List<Card> carreaux = new ArrayList<>(
				player.getHand().stream().filter(c -> c.getColor() == Color.CARREAU).collect(Collectors.toList()));
		List<Card> trefles = new ArrayList<>(
				player.getHand().stream().filter(c -> c.getColor() == Color.TREFLE).collect(Collectors.toList()));
		List<Card> atouts = new ArrayList<>(
				player.getHand().stream().filter(c -> c.getColor() == Color.ATOUT).collect(Collectors.toList()));
		List<Card> excuses = new ArrayList<>(
				player.getHand().stream().filter(c -> c.getColor() == Color.AUTRE).collect(Collectors.toList()));
		piques.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
		coeurs.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
		carreaux.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
		trefles.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
		atouts.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
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
		Fold fold = new Fold();
		Fold previousFold = new Fold();
		List<Fold> previousFolds = new ArrayList<>();
		int neededFoldNumber = done.getPlayers().get(0).getHand().size();
		int playersNumber = done.getPlayers().size();
		for (int i = 0; i < neededFoldNumber; i++) {
			previousFold = fold;
			fold = new Fold();
			previousFolds.add(fold);
			for (int j = 0; j < playersNumber; j++) {
				Card playCard = playerControlServices.playCard(done, getNextPlayer(done, fold, previousFold), fold,
						previousFolds);
				System.out.println(playCard);
			}
			System.out.println("");
		}
		this.foldControlServices.computeWinner(fold);
	}

	private Player getNextPlayer(Done done, Fold fold, Fold previousFold) {
		Player nextPlayer = null;
		// if the fold over
		if (!fold.getCards().isEmpty()) {
			// We have to find last player index (fold's latest added card's
			// owner)
			Card latestCard = fold.getCards().get(fold.getCards().size() - 1);
			Player latestPlayer = latestCard.getOwner();
			int index = 0;
			for (int i = 0; i < done.getPlayers().size(); i++) {
				if (done.getPlayers().get(i).equals(latestPlayer)) {
					index = i;
				}
			}
			// We have found the latest player. The next one is i++%player size.
			nextPlayer = done.getPlayers().get((index + 1) % done.getPlayers().size());
		} else if (previousFold.getCards().isEmpty()) {
			nextPlayer = done.getNextPlayer();
		} else {
			foldControlServices.computeWinner(previousFold);
			// the fold is over, the next player is the fold winner
			nextPlayer = previousFold.getWinner();
			done.setNextPlayer(nextPlayer);
		}

		return nextPlayer;
	}


}
