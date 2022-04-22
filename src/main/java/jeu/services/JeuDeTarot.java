package jeu.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import jeu.entities.Carte;
import jeu.entities.Carte.Couleur;
import jeu.entities.Done;
import jeu.entities.Joueur;
import jeu.entities.Pli;

public class JeuDeTarot {

	public static final Carte EXCUSE = new Carte(Carte.Couleur.AUTRE, Carte.Nom.EXCUSE, 4.5, null);

	public static final Carte UN_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.PETIT, 4.5, null);
	public static final Carte DEUX_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.DEUX_ATOUT, 0.5, null);
	public static final Carte TROIS_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.TROIS_ATOUT, 0.5, null);
	public static final Carte QUATRE_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.QUATRE_ATOUT, 0.5, null);
	public static final Carte CINQ_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.CINQ_ATOUT, 0.5, null);
	public static final Carte SIX_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.SIX_ATOUT, 0.5, null);
	public static final Carte SEPT_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.SEPT_ATOUT, 0.5, null);
	public static final Carte HUIT_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.HUIT_ATOUT, 0.5, null);
	public static final Carte NEUF_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.NEUF_ATOUT, 0.5, null);
	public static final Carte DIX_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.DIX_ATOUT, 0.5, null);
	public static final Carte ONZE_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.ONZE_ATOUT, 0.5, null);
	public static final Carte DOUZE_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.DOUZE_ATOUT, 0.5, null);
	public static final Carte TREIZE_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.TREIZE_ATOUT, 0.5, null);
	public static final Carte QUATORZE_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.QUATORZE_ATOUT, 0.5, null);
	public static final Carte QUINZE_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.QUINZE_ATOUT, 0.5, null);
	public static final Carte SEIZE_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.SEIZE_ATOUT, 0.5, null);
	public static final Carte DIX_SEPT_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.DIXSEPT_ATOUT, 0.5, null);
	public static final Carte DIX_HUIT_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.DIXHUIT_ATOUT, 0.5, null);
	public static final Carte DIX_NEUF_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.DIXNEUF_ATOUT, 0.5, null);
	public static final Carte VINGT_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.VINGT_ATOUT, 0.5, null);
	public static final Carte VINGT_ET_UN_ATOUT = new Carte(Carte.Couleur.ATOUT, Carte.Nom.VINGTETUN_ATOUT, 4.5, null);

	public static final Carte AS_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.AS, 0.5, null);
	public static final Carte DEUX_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.DEUX, 0.5, null);
	public static final Carte TROIS_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.TROIS, 0.5, null);
	public static final Carte QUATRE_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.QUATRE, 0.5, null);
	public static final Carte CINQ_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.CINQ, 0.5, null);
	public static final Carte SIX_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.SIX, 0.5, null);
	public static final Carte SEPT_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.SEPT, 0.5, null);
	public static final Carte HUIT_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.HUIT, 0.5, null);
	public static final Carte NEUF_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.NEUF, 0.5, null);
	public static final Carte DIX_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.DIX, 0.5, null);
	public static final Carte VALET_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.VALET, 1.5, null);
	public static final Carte CAVALIER_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.CAVALIER, 2.5, null);
	public static final Carte DAME_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.DAME, 3.5, null);
	public static final Carte ROI_PIQUE = new Carte(Carte.Couleur.PIQUE, Carte.Nom.ROI, 4.5, null);

	public static final Carte AS_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.AS, 0.5, null);
	public static final Carte DEUX_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.DEUX, 0.5, null);
	public static final Carte TROIS_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.TROIS, 0.5, null);
	public static final Carte QUATRE_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.QUATRE, 0.5, null);
	public static final Carte CINQ_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.CINQ, 0.5, null);
	public static final Carte SIX_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.SIX, 0.5, null);
	public static final Carte SEPT_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.SEPT, 0.5, null);
	public static final Carte HUIT_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.HUIT, 0.5, null);
	public static final Carte NEUF_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.NEUF, 0.5, null);
	public static final Carte DIX_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.DIX, 0.5, null);
	public static final Carte VALET_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.VALET, 1.5, null);
	public static final Carte CAVALIER_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.CAVALIER, 2.5, null);
	public static final Carte DAME_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.DAME, 3.5, null);
	public static final Carte ROI_COEUR = new Carte(Carte.Couleur.COEUR, Carte.Nom.ROI, 4.5, null);

	public static final Carte AS_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.AS, 0.5, null);
	public static final Carte DEUX_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.DEUX, 0.5, null);
	public static final Carte TROIS_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.TROIS, 0.5, null);
	public static final Carte QUATRE_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.QUATRE, 0.5, null);
	public static final Carte CINQ_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.CINQ, 0.5, null);
	public static final Carte SIX_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.SIX, 0.5, null);
	public static final Carte SEPT_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.SEPT, 0.5, null);
	public static final Carte HUIT_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.HUIT, 0.5, null);
	public static final Carte NEUF_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.NEUF, 0.5, null);
	public static final Carte DIX_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.DIX, 0.5, null);
	public static final Carte VALET_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.VALET, 1.5, null);
	public static final Carte CAVALIER_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.CAVALIER, 2.5, null);
	public static final Carte DAME_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.DAME, 3.5, null);
	public static final Carte ROI_CARREAU = new Carte(Carte.Couleur.CARREAU, Carte.Nom.ROI, 4.5, null);

	public static final Carte AS_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.AS, 0.5, null);
	public static final Carte DEUX_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.DEUX, 0.5, null);
	public static final Carte TROIS_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.TROIS, 0.5, null);
	public static final Carte QUATRE_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.QUATRE, 0.5, null);
	public static final Carte CINQ_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.CINQ, 0.5, null);
	public static final Carte SIX_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.SIX, 0.5, null);
	public static final Carte SEPT_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.SEPT, 0.5, null);
	public static final Carte HUIT_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.HUIT, 0.5, null);
	public static final Carte NEUF_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.NEUF, 0.5, null);
	public static final Carte DIX_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.DIX, 0.5, null);
	public static final Carte VALET_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.VALET, 1.5, null);
	public static final Carte CAVALIER_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.CAVALIER, 2.5, null);
	public static final Carte DAME_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.DAME, 3.5, null);
	public static final Carte ROI_TREFLE = new Carte(Carte.Couleur.TREFLE, Carte.Nom.ROI, 4.5, null);

	public JeuDeTarot() {
	}

	public void creerDeck(final Done done) {
		done.getCartes().add(JeuDeTarot.EXCUSE);

		done.getCartes().add(JeuDeTarot.UN_ATOUT);
		done.getCartes().add(JeuDeTarot.DEUX_ATOUT);
		done.getCartes().add(JeuDeTarot.TROIS_ATOUT);
		done.getCartes().add(JeuDeTarot.QUATRE_ATOUT);
		done.getCartes().add(JeuDeTarot.CINQ_ATOUT);
		done.getCartes().add(JeuDeTarot.SIX_ATOUT);
		done.getCartes().add(JeuDeTarot.SEPT_ATOUT);
		done.getCartes().add(JeuDeTarot.HUIT_ATOUT);
		done.getCartes().add(JeuDeTarot.NEUF_ATOUT);
		done.getCartes().add(JeuDeTarot.DIX_ATOUT);
		done.getCartes().add(JeuDeTarot.ONZE_ATOUT);
		done.getCartes().add(JeuDeTarot.DOUZE_ATOUT);
		done.getCartes().add(JeuDeTarot.TREIZE_ATOUT);
		done.getCartes().add(JeuDeTarot.QUATORZE_ATOUT);
		done.getCartes().add(JeuDeTarot.QUINZE_ATOUT);
		done.getCartes().add(JeuDeTarot.SEIZE_ATOUT);
		done.getCartes().add(JeuDeTarot.DIX_SEPT_ATOUT);
		done.getCartes().add(JeuDeTarot.DIX_HUIT_ATOUT);
		done.getCartes().add(JeuDeTarot.DIX_NEUF_ATOUT);
		done.getCartes().add(JeuDeTarot.VINGT_ATOUT);
		done.getCartes().add(JeuDeTarot.VINGT_ET_UN_ATOUT);

		done.getCartes().add(JeuDeTarot.AS_PIQUE);
		done.getCartes().add(JeuDeTarot.DEUX_PIQUE);
		done.getCartes().add(JeuDeTarot.TROIS_PIQUE);
		done.getCartes().add(JeuDeTarot.QUATRE_PIQUE);
		done.getCartes().add(JeuDeTarot.CINQ_PIQUE);
		done.getCartes().add(JeuDeTarot.SIX_PIQUE);
		done.getCartes().add(JeuDeTarot.SEPT_PIQUE);
		done.getCartes().add(JeuDeTarot.HUIT_PIQUE);
		done.getCartes().add(JeuDeTarot.NEUF_PIQUE);
		done.getCartes().add(JeuDeTarot.DIX_PIQUE);
		done.getCartes().add(JeuDeTarot.VALET_PIQUE);
		done.getCartes().add(JeuDeTarot.CAVALIER_PIQUE);
		done.getCartes().add(JeuDeTarot.DAME_PIQUE);
		done.getCartes().add(JeuDeTarot.ROI_PIQUE);

		done.getCartes().add(JeuDeTarot.AS_COEUR);
		done.getCartes().add(JeuDeTarot.DEUX_COEUR);
		done.getCartes().add(JeuDeTarot.TROIS_COEUR);
		done.getCartes().add(JeuDeTarot.QUATRE_COEUR);
		done.getCartes().add(JeuDeTarot.CINQ_COEUR);
		done.getCartes().add(JeuDeTarot.SIX_COEUR);
		done.getCartes().add(JeuDeTarot.SEPT_COEUR);
		done.getCartes().add(JeuDeTarot.HUIT_COEUR);
		done.getCartes().add(JeuDeTarot.NEUF_COEUR);
		done.getCartes().add(JeuDeTarot.DIX_COEUR);
		done.getCartes().add(JeuDeTarot.VALET_COEUR);
		done.getCartes().add(JeuDeTarot.CAVALIER_COEUR);
		done.getCartes().add(JeuDeTarot.DAME_COEUR);
		done.getCartes().add(JeuDeTarot.ROI_COEUR);

		done.getCartes().add(JeuDeTarot.AS_CARREAU);
		done.getCartes().add(JeuDeTarot.DEUX_CARREAU);
		done.getCartes().add(JeuDeTarot.TROIS_CARREAU);
		done.getCartes().add(JeuDeTarot.QUATRE_CARREAU);
		done.getCartes().add(JeuDeTarot.CINQ_CARREAU);
		done.getCartes().add(JeuDeTarot.SIX_CARREAU);
		done.getCartes().add(JeuDeTarot.SEPT_CARREAU);
		done.getCartes().add(JeuDeTarot.HUIT_CARREAU);
		done.getCartes().add(JeuDeTarot.NEUF_CARREAU);
		done.getCartes().add(JeuDeTarot.DIX_CARREAU);
		done.getCartes().add(JeuDeTarot.VALET_CARREAU);
		done.getCartes().add(JeuDeTarot.CAVALIER_CARREAU);
		done.getCartes().add(JeuDeTarot.DAME_CARREAU);
		done.getCartes().add(JeuDeTarot.ROI_CARREAU);

		done.getCartes().add(JeuDeTarot.AS_TREFLE);
		done.getCartes().add(JeuDeTarot.DEUX_TREFLE);
		done.getCartes().add(JeuDeTarot.TROIS_TREFLE);
		done.getCartes().add(JeuDeTarot.QUATRE_TREFLE);
		done.getCartes().add(JeuDeTarot.CINQ_TREFLE);
		done.getCartes().add(JeuDeTarot.SIX_TREFLE);
		done.getCartes().add(JeuDeTarot.SEPT_TREFLE);
		done.getCartes().add(JeuDeTarot.HUIT_TREFLE);
		done.getCartes().add(JeuDeTarot.NEUF_TREFLE);
		done.getCartes().add(JeuDeTarot.DIX_TREFLE);
		done.getCartes().add(JeuDeTarot.VALET_TREFLE);
		done.getCartes().add(JeuDeTarot.CAVALIER_TREFLE);
		done.getCartes().add(JeuDeTarot.DAME_TREFLE);
		done.getCartes().add(JeuDeTarot.ROI_TREFLE);
	}

	public void melanger(final Done done) {
		int min = 0;
		int max = 10000;
		Random rand = new Random();
		long seed = (long)rand.nextInt(max - min + 1) + min;;
		seed = 6045;
		System.out.println("Seed utilisé pour le mélange : " + seed);
		Random random = new Random(seed);
		Collections.shuffle(done.getCartes(), random);
	}

	public void distribue(final Done done) {
		if (!this.isTest()) {
			int dogCartesNumber = 6;
			if (done.getJoueurs().size() == 5) {
				dogCartesNumber = 3;
			}
			for (int i = 0; i < dogCartesNumber; i++) {
				done.getChien().add(done.getCartes().remove(0));
			}
			done.distribuer();
			for (final Joueur player : done.getJoueurs()) {
				this.trierCartes(player);
			}
		} else {
			this.testDistribute(done);
		}
		// this.montrerCartesJoueurs(done);
	}

	private boolean isTest() {
		return false;
	}

	private void testDistribute(final Done done) {
		final Joueur sebastien = done.getJoueurs().stream().filter(p -> p.getNom().equals("Sebastien"))
				.collect(Collectors.toList()).get(0);
		final Joueur virginie = done.getJoueurs().stream().filter(p -> p.getNom().equals("Virginie"))
				.collect(Collectors.toList()).get(0);
		final Joueur alexandre = done.getJoueurs().stream().filter(p -> p.getNom().equals("Alexandre"))
				.collect(Collectors.toList()).get(0);
		final Joueur marco = done.getJoueurs().stream().filter(p -> p.getNom().equals("Marco"))
				.collect(Collectors.toList()).get(0);
		final Joueur antoine = done.getJoueurs().stream().filter(p -> p.getNom().equals("Antoine"))
				.collect(Collectors.toList()).get(0);

		sebastien.getMain().add(JeuDeTarot.CINQ_PIQUE);
		sebastien.getMain().add(JeuDeTarot.DEUX_COEUR);
		sebastien.getMain().add(JeuDeTarot.TROIS_COEUR);
		sebastien.getMain().add(JeuDeTarot.QUATRE_COEUR);
		sebastien.getMain().add(JeuDeTarot.ROI_COEUR);
		sebastien.getMain().add(JeuDeTarot.ROI_TREFLE);
		sebastien.getMain().add(JeuDeTarot.UN_ATOUT);
		sebastien.getMain().add(JeuDeTarot.TROIS_ATOUT);
		sebastien.getMain().add(JeuDeTarot.NEUF_ATOUT);
		sebastien.getMain().add(JeuDeTarot.ONZE_ATOUT);
		sebastien.getMain().add(JeuDeTarot.TREIZE_ATOUT);
		sebastien.getMain().add(JeuDeTarot.DIX_SEPT_ATOUT);
		sebastien.getMain().add(JeuDeTarot.DIX_HUIT_ATOUT);
		sebastien.getMain().add(JeuDeTarot.VINGT_ET_UN_ATOUT);
		sebastien.getMain().add(JeuDeTarot.EXCUSE);

		virginie.getMain().add(JeuDeTarot.DEUX_PIQUE);
		virginie.getMain().add(JeuDeTarot.VALET_PIQUE);
		virginie.getMain().add(JeuDeTarot.CAVALIER_COEUR);
		virginie.getMain().add(JeuDeTarot.DAME_COEUR);
		virginie.getMain().add(JeuDeTarot.SIX_CARREAU);
		virginie.getMain().add(JeuDeTarot.HUIT_CARREAU);
		virginie.getMain().add(JeuDeTarot.DAME_CARREAU);
		virginie.getMain().add(JeuDeTarot.AS_TREFLE);
		virginie.getMain().add(JeuDeTarot.SEPT_TREFLE);
		virginie.getMain().add(JeuDeTarot.NEUF_TREFLE);
		virginie.getMain().add(JeuDeTarot.DEUX_ATOUT);
		virginie.getMain().add(JeuDeTarot.SIX_ATOUT);
		virginie.getMain().add(JeuDeTarot.DIX_ATOUT);
		virginie.getMain().add(JeuDeTarot.DOUZE_ATOUT);
		virginie.getMain().add(JeuDeTarot.DIX_NEUF_ATOUT);

		alexandre.getMain().add(JeuDeTarot.AS_PIQUE);
		alexandre.getMain().add(JeuDeTarot.TROIS_PIQUE);
		alexandre.getMain().add(JeuDeTarot.SEPT_COEUR);
		alexandre.getMain().add(JeuDeTarot.AS_CARREAU);
		alexandre.getMain().add(JeuDeTarot.TROIS_CARREAU);
		alexandre.getMain().add(JeuDeTarot.SEPT_CARREAU);
		alexandre.getMain().add(JeuDeTarot.NEUF_CARREAU);
		alexandre.getMain().add(JeuDeTarot.VALET_CARREAU);
		alexandre.getMain().add(JeuDeTarot.ROI_CARREAU);
		alexandre.getMain().add(JeuDeTarot.TROIS_TREFLE);
		alexandre.getMain().add(JeuDeTarot.SIX_TREFLE);
		alexandre.getMain().add(JeuDeTarot.DIX_TREFLE);
		alexandre.getMain().add(JeuDeTarot.CAVALIER_TREFLE);
		alexandre.getMain().add(JeuDeTarot.CINQ_ATOUT);
		alexandre.getMain().add(JeuDeTarot.SEIZE_ATOUT);

		marco.getMain().add(JeuDeTarot.SIX_PIQUE);
		marco.getMain().add(JeuDeTarot.HUIT_PIQUE);
		marco.getMain().add(JeuDeTarot.DAME_PIQUE);
		marco.getMain().add(JeuDeTarot.ROI_PIQUE);
		marco.getMain().add(JeuDeTarot.AS_COEUR);
		marco.getMain().add(JeuDeTarot.CINQ_COEUR);
		marco.getMain().add(JeuDeTarot.HUIT_COEUR);
		marco.getMain().add(JeuDeTarot.VALET_COEUR);
		marco.getMain().add(JeuDeTarot.DEUX_CARREAU);
		marco.getMain().add(JeuDeTarot.CINQ_CARREAU);
		marco.getMain().add(JeuDeTarot.QUATRE_TREFLE);
		marco.getMain().add(JeuDeTarot.HUIT_TREFLE);
		marco.getMain().add(JeuDeTarot.SEPT_ATOUT);
		marco.getMain().add(JeuDeTarot.QUATORZE_ATOUT);
		marco.getMain().add(JeuDeTarot.VINGT_ATOUT);

		antoine.getMain().add(JeuDeTarot.QUATRE_PIQUE);
		antoine.getMain().add(JeuDeTarot.SEPT_PIQUE);
		antoine.getMain().add(JeuDeTarot.NEUF_PIQUE);
		antoine.getMain().add(JeuDeTarot.DIX_PIQUE);
		antoine.getMain().add(JeuDeTarot.CAVALIER_PIQUE);
		antoine.getMain().add(JeuDeTarot.SIX_COEUR);
		antoine.getMain().add(JeuDeTarot.DIX_COEUR);
		antoine.getMain().add(JeuDeTarot.DIX_CARREAU);
		antoine.getMain().add(JeuDeTarot.CAVALIER_CARREAU);
		antoine.getMain().add(JeuDeTarot.DEUX_TREFLE);
		antoine.getMain().add(JeuDeTarot.CINQ_TREFLE);
		antoine.getMain().add(JeuDeTarot.VALET_TREFLE);
		antoine.getMain().add(JeuDeTarot.DAME_TREFLE);
		antoine.getMain().add(JeuDeTarot.QUATRE_ATOUT);
		antoine.getMain().add(JeuDeTarot.HUIT_ATOUT);

		for (final Joueur player : done.getJoueurs()) {
			for (final Carte card : player.getMain()) {
				card.setProprietaireInitial(player);
			}
		}

		done.getChien().add(JeuDeTarot.NEUF_COEUR);
		done.getChien().add(JeuDeTarot.QUATRE_CARREAU);
		done.getChien().add(JeuDeTarot.QUINZE_ATOUT);

		final List<Pli> folds = new ArrayList<>();

		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.DIX_SEPT_ATOUT, JeuDeTarot.DIX_NEUF_ATOUT,
				JeuDeTarot.CINQ_ATOUT, JeuDeTarot.VINGT_ATOUT, JeuDeTarot.HUIT_ATOUT), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.HUIT_TREFLE, JeuDeTarot.DEUX_TREFLE, JeuDeTarot.ROI_TREFLE,
				JeuDeTarot.AS_TREFLE, JeuDeTarot.TROIS_TREFLE), done);

		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.TREIZE_ATOUT, JeuDeTarot.DEUX_ATOUT, JeuDeTarot.SEIZE_ATOUT,
				JeuDeTarot.QUATORZE_ATOUT, JeuDeTarot.QUATRE_ATOUT), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.DIX_TREFLE, JeuDeTarot.QUATRE_TREFLE, JeuDeTarot.DAME_TREFLE,
				JeuDeTarot.VINGT_ET_UN_ATOUT, JeuDeTarot.NEUF_TREFLE), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.TROIS_COEUR, JeuDeTarot.CAVALIER_COEUR, JeuDeTarot.SEPT_COEUR,
				JeuDeTarot.AS_COEUR, JeuDeTarot.SIX_COEUR), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.DIX_ATOUT, JeuDeTarot.AS_PIQUE, JeuDeTarot.SEPT_ATOUT,
				JeuDeTarot.QUATRE_PIQUE, JeuDeTarot.ONZE_ATOUT), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.NEUF_ATOUT, JeuDeTarot.DOUZE_ATOUT, JeuDeTarot.AS_CARREAU,
				JeuDeTarot.SIX_PIQUE, JeuDeTarot.CINQ_TREFLE), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.SIX_ATOUT, JeuDeTarot.TROIS_PIQUE, JeuDeTarot.HUIT_PIQUE,
				JeuDeTarot.SEPT_PIQUE, JeuDeTarot.DIX_HUIT_ATOUT), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.CINQ_PIQUE, JeuDeTarot.DEUX_PIQUE, JeuDeTarot.TROIS_CARREAU,
				JeuDeTarot.ROI_PIQUE, JeuDeTarot.CAVALIER_PIQUE), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.DEUX_CARREAU, JeuDeTarot.DIX_CARREAU, JeuDeTarot.UN_ATOUT,
				JeuDeTarot.SIX_CARREAU, JeuDeTarot.SEPT_CARREAU), done);
		this.ajouterPli(folds, Arrays.asList(JeuDeTarot.TROIS_ATOUT, JeuDeTarot.VALET_PIQUE, JeuDeTarot.NEUF_CARREAU,
				JeuDeTarot.CINQ_COEUR, JeuDeTarot.NEUF_PIQUE), done);

		for (final Pli fold : folds) {
			for (final Carte card : fold.getCartes()) {
				card.getProprietaireInitial().getMain().remove(card);
			}
			fold.calculeLeGagnant();
			done.setJoueurSuivant(fold.getGagnant());
		}

		System.out.println("Reste dans les mains : ");
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||");

		for (final Joueur player : done.getJoueurs()) {
			for (final Carte carte : player.getMain()) {
				if (carte.getProprietaireFinal() == null) {
					System.out.println(carte);
				}
			}
		}
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||");
	}

	private void ajouterPli(final List<Pli> folds, final List<Carte> foldCartes, final Done done) {
		final Pli pli = new Pli();
		pli.setCouleurJouee(foldCartes.get(0).getCouleur());
		for (final Carte card : foldCartes) {
			Carte c = null;
			for (final Joueur player : done.getJoueurs()) {
				for (final Carte playerCarte : player.getMain()) {
					if ((playerCarte.getCouleur() == card.getCouleur()) && (playerCarte.getNom() == card.getNom())) {
						c = playerCarte;
					}
				}
			}
			pli.getCartes().add(c);
		}
		done.getPlis().add(pli);
		folds.add(pli);
	}

	private void trierCartes(final Joueur joueur) {
		final List<Carte> piques = new ArrayList<>(
				joueur.getMain().stream().filter(c -> c.getCouleur() == Couleur.PIQUE).collect(Collectors.toList()));
		final List<Carte> coeurs = new ArrayList<>(
				joueur.getMain().stream().filter(c -> c.getCouleur() == Couleur.COEUR).collect(Collectors.toList()));
		final List<Carte> carreaux = new ArrayList<>(
				joueur.getMain().stream().filter(c -> c.getCouleur() == Couleur.CARREAU).collect(Collectors.toList()));
		final List<Carte> trefles = new ArrayList<>(
				joueur.getMain().stream().filter(c -> c.getCouleur() == Couleur.TREFLE).collect(Collectors.toList()));
		final List<Carte> atouts = new ArrayList<>(
				joueur.getMain().stream().filter(c -> c.getCouleur() == Couleur.ATOUT).collect(Collectors.toList()));
		final List<Carte> excuses = new ArrayList<>(
				joueur.getMain().stream().filter(c -> c.getCouleur() == Couleur.AUTRE).collect(Collectors.toList()));
		piques.sort((c1, c2) -> c1.getNom().getPuissance() - c2.getNom().getPuissance());
		coeurs.sort((c1, c2) -> c1.getNom().getPuissance() - c2.getNom().getPuissance());
		carreaux.sort((c1, c2) -> c1.getNom().getPuissance() - c2.getNom().getPuissance());
		trefles.sort((c1, c2) -> c1.getNom().getPuissance() - c2.getNom().getPuissance());
		atouts.sort((c1, c2) -> c1.getNom().getPuissance() - c2.getNom().getPuissance());
		final List<Carte> toutesCartesDuJoueur = new ArrayList<>();
		toutesCartesDuJoueur.addAll(piques);
		toutesCartesDuJoueur.addAll(coeurs);
		toutesCartesDuJoueur.addAll(carreaux);
		toutesCartesDuJoueur.addAll(trefles);
		toutesCartesDuJoueur.addAll(atouts);
		toutesCartesDuJoueur.addAll(excuses);
		joueur.setMain(toutesCartesDuJoueur);
	}

	public void joue(final Done done) {
		Pli pli;
		if (this.isTest()) {
			pli = done.getPlis().get(done.getPlis().size() - 1);
		} else {
			pli = new Pli();
		}
		Pli pliPrecedent = new Pli();
		final List<Pli> plisPrecedents = new ArrayList<>();
		final int nombreDePlisRequis = done.getJoueurs().get(0).getMain().size();
		final int nombreDeJoueurs = done.getJoueurs().size();
		// Il faut 15 tours de jeu
		for (int i = 0; i < nombreDePlisRequis; i++) {
			pliPrecedent = pli;
			pli = new Pli();
			done.getPlis().add(pli);
			// Dans chaque tour, chacun des joueurs joue
			for (int j = 0; j < nombreDeJoueurs; j++) {
				final Joueur joueur = this.getJoueurSuivant(done, pli, pliPrecedent);
				joueur.jouerUneCarte(done, pli, plisPrecedents);
			}
			plisPrecedents.add(pli);
			pli.calculeLeGagnant();
			System.out.println(pli);
		}
	}

	private Joueur getJoueurSuivant(final Done done, final Pli pli, final Pli previousPli) {
		Joueur prochainJoueur = null;
		// si le pli n'est pas commencé
		if (!pli.getCartes().isEmpty()) {
			// Il faut trouver l'index du dernier joueur ayant joué
			final Carte derniereCarteJouee = pli.getCartes().get(pli.getCartes().size() - 1);
			final Joueur proprietaireDeLaDerniereCarte = derniereCarteJouee.getProprietaireInitial();
			int index = 0;
			for (int i = 0; i < done.getJoueurs().size(); i++) {
				if (done.getJoueurs().get(i).equals(proprietaireDeLaDerniereCarte)) {
					index = i;
				}
			}
			// On a trouvé l'index du dernier joueur.
			// Le suivant est i+1%(nombre de joueurs)
			prochainJoueur = done.getJoueurs().get((index + 1) % done.getJoueurs().size());
		} else if (previousPli.getCartes().isEmpty()) {
			prochainJoueur = done.getJoueurSuivant();
		} else {
//			previousPli.calculeLeGagnant();
			// le pli est terminé, the next player is the fold winner
			prochainJoueur = previousPli.getGagnant();
			done.setJoueurSuivant(prochainJoueur);
		}

		return prochainJoueur;
	}

}
