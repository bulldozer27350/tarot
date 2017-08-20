package game.control.internal;

import java.util.ArrayList;
import java.util.Arrays;
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

    public TarotDeckControlServicesImpl(final PlayerControlServices playerControlServicesArg,
	    final DoneControlServices doneControlServicesArg, final FoldControlServices foldControlServicesArg) {
	this.doneControlServices = doneControlServicesArg;
	this.playerControlServices = playerControlServicesArg;
	this.foldControlServices = foldControlServicesArg;
    }

    @Override
    public void createDeck(final Done done) {
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.EXCUSE);

	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.UN_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DEUX_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.TROIS_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.QUATRE_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CINQ_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SIX_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SEPT_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.HUIT_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.NEUF_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.ONZE_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DOUZE_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.TREIZE_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.QUATORZE_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.QUINZE_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SEIZE_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_SEPT_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_HUIT_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_NEUF_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.VINGT_ATOUT);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.VINGT_ET_UN_ATOUT);

	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.AS_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DEUX_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.TROIS_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.QUATRE_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CINQ_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SIX_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SEPT_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.HUIT_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.NEUF_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.VALET_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CAVALIER_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DAME_PIQUE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.ROI_PIQUE);

	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.AS_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DEUX_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.TROIS_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.QUATRE_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CINQ_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SIX_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SEPT_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.HUIT_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.NEUF_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.VALET_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CAVALIER_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DAME_COEUR);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.ROI_COEUR);

	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.AS_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DEUX_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.TROIS_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.QUATRE_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CINQ_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SIX_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SEPT_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.HUIT_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.NEUF_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.VALET_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CAVALIER_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DAME_CARREAU);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.ROI_CARREAU);

	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.AS_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DEUX_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.TROIS_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.QUATRE_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CINQ_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SIX_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.SEPT_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.HUIT_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.NEUF_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DIX_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.VALET_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.CAVALIER_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.DAME_TREFLE);
	this.doneControlServices.addCard(done, TarotDeckControlServicesImpl.ROI_TREFLE);
    }

    @Override
    public void shuffle(final Done done) {
	Collections.shuffle(done.getCards());
    }

    @Override
    public void distribute(final Done done) {
	if (!this.isTest()) {
	    int dogCardsNumber = 6;
	    if (done.getPlayers().size() == 5) {
		dogCardsNumber = 3;
	    }
	    for (int i = 0; i < dogCardsNumber; i++) {
		done.getDog().add(done.getCards().remove(0));
	    }
	    this.doneControlServices.distribute(done);
	    for (final Player player : done.getPlayers()) {
		this.sortCards(player);
	    }
	} else {
	    this.testDistribute(done);
	}
	this.showPlayersCards(done);
    }

    private boolean isTest() {
	return true;
    }

    private void testDistribute(final Done done) {
	final Player sebastien = done.getPlayers().stream().filter(p -> p.getName().equals("Sebastien"))
		.collect(Collectors.toList()).get(0);
	final Player virginie = done.getPlayers().stream().filter(p -> p.getName().equals("Virginie"))
		.collect(Collectors.toList()).get(0);
	final Player alexandre = done.getPlayers().stream().filter(p -> p.getName().equals("Alexandre"))
		.collect(Collectors.toList()).get(0);
	final Player marco = done.getPlayers().stream().filter(p -> p.getName().equals("Marco"))
		.collect(Collectors.toList()).get(0);
	final Player antoine = done.getPlayers().stream().filter(p -> p.getName().equals("Antoine"))
		.collect(Collectors.toList()).get(0);

	sebastien.getHand().add(TarotDeckControlServicesImpl.CINQ_PIQUE);
	sebastien.getHand().add(TarotDeckControlServicesImpl.DEUX_COEUR);
	sebastien.getHand().add(TarotDeckControlServicesImpl.TROIS_COEUR);
	sebastien.getHand().add(TarotDeckControlServicesImpl.QUATRE_COEUR);
	sebastien.getHand().add(TarotDeckControlServicesImpl.ROI_COEUR);
	sebastien.getHand().add(TarotDeckControlServicesImpl.ROI_TREFLE);
	sebastien.getHand().add(TarotDeckControlServicesImpl.UN_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.TROIS_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.NEUF_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.ONZE_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.TREIZE_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.DIX_SEPT_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.DIX_HUIT_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.VINGT_ET_UN_ATOUT);
	sebastien.getHand().add(TarotDeckControlServicesImpl.EXCUSE);

	virginie.getHand().add(TarotDeckControlServicesImpl.DEUX_PIQUE);
	virginie.getHand().add(TarotDeckControlServicesImpl.VALET_PIQUE);
	virginie.getHand().add(TarotDeckControlServicesImpl.CAVALIER_COEUR);
	virginie.getHand().add(TarotDeckControlServicesImpl.DAME_COEUR);
	virginie.getHand().add(TarotDeckControlServicesImpl.SIX_CARREAU);
	virginie.getHand().add(TarotDeckControlServicesImpl.HUIT_CARREAU);
	virginie.getHand().add(TarotDeckControlServicesImpl.DAME_CARREAU);
	virginie.getHand().add(TarotDeckControlServicesImpl.AS_TREFLE);
	virginie.getHand().add(TarotDeckControlServicesImpl.SEPT_TREFLE);
	virginie.getHand().add(TarotDeckControlServicesImpl.NEUF_TREFLE);
	virginie.getHand().add(TarotDeckControlServicesImpl.DEUX_ATOUT);
	virginie.getHand().add(TarotDeckControlServicesImpl.SIX_ATOUT);
	virginie.getHand().add(TarotDeckControlServicesImpl.DIX_ATOUT);
	virginie.getHand().add(TarotDeckControlServicesImpl.DOUZE_ATOUT);
	virginie.getHand().add(TarotDeckControlServicesImpl.DIX_NEUF_ATOUT);

	alexandre.getHand().add(TarotDeckControlServicesImpl.AS_PIQUE);
	alexandre.getHand().add(TarotDeckControlServicesImpl.TROIS_PIQUE);
	alexandre.getHand().add(TarotDeckControlServicesImpl.SEPT_COEUR);
	alexandre.getHand().add(TarotDeckControlServicesImpl.AS_CARREAU);
	alexandre.getHand().add(TarotDeckControlServicesImpl.TROIS_CARREAU);
	alexandre.getHand().add(TarotDeckControlServicesImpl.SEPT_CARREAU);
	alexandre.getHand().add(TarotDeckControlServicesImpl.NEUF_CARREAU);
	alexandre.getHand().add(TarotDeckControlServicesImpl.VALET_CARREAU);
	alexandre.getHand().add(TarotDeckControlServicesImpl.ROI_CARREAU);
	alexandre.getHand().add(TarotDeckControlServicesImpl.TROIS_TREFLE);
	alexandre.getHand().add(TarotDeckControlServicesImpl.SIX_TREFLE);
	alexandre.getHand().add(TarotDeckControlServicesImpl.DIX_TREFLE);
	alexandre.getHand().add(TarotDeckControlServicesImpl.CAVALIER_TREFLE);
	alexandre.getHand().add(TarotDeckControlServicesImpl.CINQ_ATOUT);
	alexandre.getHand().add(TarotDeckControlServicesImpl.SEIZE_ATOUT);

	marco.getHand().add(TarotDeckControlServicesImpl.SIX_PIQUE);
	marco.getHand().add(TarotDeckControlServicesImpl.HUIT_PIQUE);
	marco.getHand().add(TarotDeckControlServicesImpl.DAME_PIQUE);
	marco.getHand().add(TarotDeckControlServicesImpl.ROI_PIQUE);
	marco.getHand().add(TarotDeckControlServicesImpl.AS_COEUR);
	marco.getHand().add(TarotDeckControlServicesImpl.CINQ_COEUR);
	marco.getHand().add(TarotDeckControlServicesImpl.HUIT_COEUR);
	marco.getHand().add(TarotDeckControlServicesImpl.VALET_COEUR);
	marco.getHand().add(TarotDeckControlServicesImpl.DEUX_CARREAU);
	marco.getHand().add(TarotDeckControlServicesImpl.CINQ_CARREAU);
	marco.getHand().add(TarotDeckControlServicesImpl.QUATRE_TREFLE);
	marco.getHand().add(TarotDeckControlServicesImpl.HUIT_TREFLE);
	marco.getHand().add(TarotDeckControlServicesImpl.SEPT_ATOUT);
	marco.getHand().add(TarotDeckControlServicesImpl.QUATORZE_ATOUT);
	marco.getHand().add(TarotDeckControlServicesImpl.VINGT_ATOUT);

	antoine.getHand().add(TarotDeckControlServicesImpl.QUATRE_PIQUE);
	antoine.getHand().add(TarotDeckControlServicesImpl.SEPT_PIQUE);
	antoine.getHand().add(TarotDeckControlServicesImpl.NEUF_PIQUE);
	antoine.getHand().add(TarotDeckControlServicesImpl.DIX_PIQUE);
	antoine.getHand().add(TarotDeckControlServicesImpl.CAVALIER_PIQUE);
	antoine.getHand().add(TarotDeckControlServicesImpl.SIX_COEUR);
	antoine.getHand().add(TarotDeckControlServicesImpl.DIX_COEUR);
	antoine.getHand().add(TarotDeckControlServicesImpl.DIX_CARREAU);
	antoine.getHand().add(TarotDeckControlServicesImpl.CAVALIER_CARREAU);
	antoine.getHand().add(TarotDeckControlServicesImpl.DEUX_TREFLE);
	antoine.getHand().add(TarotDeckControlServicesImpl.CINQ_TREFLE);
	antoine.getHand().add(TarotDeckControlServicesImpl.VALET_TREFLE);
	antoine.getHand().add(TarotDeckControlServicesImpl.DAME_TREFLE);
	antoine.getHand().add(TarotDeckControlServicesImpl.QUATRE_ATOUT);
	antoine.getHand().add(TarotDeckControlServicesImpl.HUIT_ATOUT);

	for (final Player player : done.getPlayers()) {
	    for (final Card card : player.getHand()) {
		card.setOwner(player);
	    }
	}

	done.getDog().add(TarotDeckControlServicesImpl.NEUF_COEUR);
	done.getDog().add(TarotDeckControlServicesImpl.QUATRE_CARREAU);
	done.getDog().add(TarotDeckControlServicesImpl.QUINZE_ATOUT);

	final List<Fold> folds = new ArrayList<>();

	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.DIX_SEPT_ATOUT, TarotDeckControlServicesImpl.DIX_NEUF_ATOUT,
			TarotDeckControlServicesImpl.CINQ_ATOUT, TarotDeckControlServicesImpl.VINGT_ATOUT,
			TarotDeckControlServicesImpl.HUIT_ATOUT),
		done);
	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.HUIT_TREFLE, TarotDeckControlServicesImpl.DEUX_TREFLE,
			TarotDeckControlServicesImpl.ROI_TREFLE, TarotDeckControlServicesImpl.AS_TREFLE,
			TarotDeckControlServicesImpl.TROIS_TREFLE),
		done);

	// Pourquoi Marco a jouer le 14 d'atout ici, et pas le 7 ?
	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.TREIZE_ATOUT, TarotDeckControlServicesImpl.DEUX_ATOUT,
			TarotDeckControlServicesImpl.SEIZE_ATOUT, TarotDeckControlServicesImpl.QUATORZE_ATOUT,
			TarotDeckControlServicesImpl.QUATRE_ATOUT),
		done);
	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.DIX_TREFLE, TarotDeckControlServicesImpl.QUATRE_TREFLE,
			TarotDeckControlServicesImpl.DAME_TREFLE, TarotDeckControlServicesImpl.VINGT_ET_UN_ATOUT,
			TarotDeckControlServicesImpl.NEUF_TREFLE),
		done);
	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.TROIS_COEUR, TarotDeckControlServicesImpl.CAVALIER_COEUR,
			TarotDeckControlServicesImpl.SEPT_COEUR, TarotDeckControlServicesImpl.AS_COEUR,
			TarotDeckControlServicesImpl.SIX_COEUR),
		done);
	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.DIX_ATOUT, TarotDeckControlServicesImpl.AS_PIQUE,
			TarotDeckControlServicesImpl.SEPT_ATOUT, TarotDeckControlServicesImpl.QUATRE_PIQUE,
			TarotDeckControlServicesImpl.ONZE_ATOUT),
		done);
	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.NEUF_ATOUT, TarotDeckControlServicesImpl.DOUZE_ATOUT,
			TarotDeckControlServicesImpl.AS_CARREAU, TarotDeckControlServicesImpl.SIX_PIQUE,
			TarotDeckControlServicesImpl.CINQ_TREFLE),
		done);
	this.addFold(folds,
		Arrays.asList(TarotDeckControlServicesImpl.SIX_ATOUT, TarotDeckControlServicesImpl.TROIS_PIQUE,
			TarotDeckControlServicesImpl.HUIT_PIQUE, TarotDeckControlServicesImpl.SEPT_PIQUE,
			TarotDeckControlServicesImpl.DIX_HUIT_ATOUT),
		done);

	for (final Fold fold : folds) {
	    for (final Card card : fold.getCards()) {
		card.getOwner().getHand().remove(card);
	    }
	    this.foldControlServices.computeWinner(fold);
	    done.setNextPlayer(fold.getWinner());
	}

    }

    private void addFold(final List<Fold> folds, final List<Card> foldCards, final Done done) {
	final Fold fold = new Fold();
	fold.setPlayedColor(foldCards.get(0).getColor());
	for (final Card card : foldCards) {
	    Card c = null;
	    for (final Player player : done.getPlayers()) {
		for (final Card playerCard : player.getHand()) {
		    if ((playerCard.getColor() == card.getColor()) && (playerCard.getName() == card.getName())) {
			c = playerCard;
		    }
		}
	    }
	    fold.getCards().add(c);
	}
	done.getFolds().add(fold);
	folds.add(fold);
    }

    private void showPlayersCards(final Done done) {
	for (final Player player : done.getPlayers()) {
	    System.out.println(player.getName());
	    for (final Card card : player.getHand()) {
		System.out.println(card);
	    }
	    System.out.println();
	}
    }

    private void sortCards(final Player player) {
	final List<Card> piques = new ArrayList<>(
		player.getHand().stream().filter(c -> c.getColor() == Color.PIQUE).collect(Collectors.toList()));
	final List<Card> coeurs = new ArrayList<>(
		player.getHand().stream().filter(c -> c.getColor() == Color.COEUR).collect(Collectors.toList()));
	final List<Card> carreaux = new ArrayList<>(
		player.getHand().stream().filter(c -> c.getColor() == Color.CARREAU).collect(Collectors.toList()));
	final List<Card> trefles = new ArrayList<>(
		player.getHand().stream().filter(c -> c.getColor() == Color.TREFLE).collect(Collectors.toList()));
	final List<Card> atouts = new ArrayList<>(
		player.getHand().stream().filter(c -> c.getColor() == Color.ATOUT).collect(Collectors.toList()));
	final List<Card> excuses = new ArrayList<>(
		player.getHand().stream().filter(c -> c.getColor() == Color.AUTRE).collect(Collectors.toList()));
	piques.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
	coeurs.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
	carreaux.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
	trefles.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
	atouts.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
	final List<Card> allPlayerCards = new ArrayList<>();
	allPlayerCards.addAll(piques);
	allPlayerCards.addAll(coeurs);
	allPlayerCards.addAll(carreaux);
	allPlayerCards.addAll(trefles);
	allPlayerCards.addAll(atouts);
	allPlayerCards.addAll(excuses);
	player.setHand(allPlayerCards);
    }

    @Override
    public void play(final Done done) {
	Fold fold;
	if (this.isTest()) {
	    fold = done.getFolds().get(done.getFolds().size() - 1);
	} else {
	    fold = new Fold();
	}
	Fold previousFold = new Fold();
	final List<Fold> previousFolds = new ArrayList<>();
	final int neededFoldNumber = done.getPlayers().get(0).getHand().size();
	final int playersNumber = done.getPlayers().size();
	for (int i = 0; i < neededFoldNumber; i++) {
	    previousFold = fold;
	    fold = new Fold();
	    done.getFolds().add(fold);
	    previousFolds.add(fold);
	    for (int j = 0; j < playersNumber; j++) {
		final Card playCard = this.playerControlServices.playCard(done,
			this.getNextPlayer(done, fold, previousFold), fold, previousFolds);
		System.out.println(playCard);
	    }
	    System.out.println("");
	}
	this.foldControlServices.computeWinner(fold);
    }

    private Player getNextPlayer(final Done done, final Fold fold, final Fold previousFold) {
	Player nextPlayer = null;
	// if the fold over
	if (!fold.getCards().isEmpty()) {
	    // We have to find last player index (fold's latest added card's
	    // owner)
	    final Card latestCard = fold.getCards().get(fold.getCards().size() - 1);
	    final Player latestPlayer = latestCard.getOwner();
	    int index = 0;
	    for (int i = 0; i < done.getPlayers().size(); i++) {
		if (done.getPlayers().get(i).equals(latestPlayer)) {
		    index = i;
		}
	    }
	    // We have found the latest player. The next one is i++%player
	    // size.
	    nextPlayer = done.getPlayers().get((index + 1) % done.getPlayers().size());
	} else if (previousFold.getCards().isEmpty()) {
	    nextPlayer = done.getNextPlayer();
	} else {
	    this.foldControlServices.computeWinner(previousFold);
	    // the fold is over, the next player is the fold winner
	    nextPlayer = previousFold.getWinner();
	    done.setNextPlayer(nextPlayer);
	}

	return nextPlayer;
    }

}
