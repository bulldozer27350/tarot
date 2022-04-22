package jeu.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jeu.entities.Carte.Couleur;
import jeu.entities.Carte.Nom;

public class Joueur {

	public enum Equipe {
		ATTAQUE, DEFENSE, INCONNUE
	}

	private List<Carte> main = new ArrayList<>();
	private List<Pli> plis = new ArrayList<>();
	private String nom;
	private Equipe equipe;
	private boolean preneur;

	private int nombreDePoints;

	public Joueur() {
	}

	public Joueur(String nameArg) {
		this.nom = nameArg;
	}

	public List<Carte> getMain() {
		return main;
	}

	public void setMain(List<Carte> main) {
		this.main = main;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe team) {
		this.equipe = team;
	}

	@Override
	public String toString() {
		return this.getNom();
	}

	public List<Pli> getPlis() {
		return plis;
	}

	public void setPlis(List<Pli> plis) {
		this.plis = plis;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isPreneur() {
		return preneur;
	}

	public void setPreneur(boolean preneur) {
		this.preneur = preneur;
	}

	public int getNombreDePoints() {
		return nombreDePoints;
	}

	public void setNombreDePoints(int nombreDePoints) {
		this.nombreDePoints = nombreDePoints;
	}

	public void ajouterASaMain(Carte carteDistribuee) {
		this.main.add(carteDistribuee);
	}

	public boolean isDansMemeEquipe(Joueur autreJoueur) {
		return autreJoueur.getEquipe() == this.getEquipe();
	}

	public Carte jouerUneCarte(final Done done, final Pli pli, final List<Pli> previousPlis) {
		Carte carte = null;
		if (pli.getCartes().isEmpty()) {
			pli.setCouleurJouee(null);
		} else if (pli.getCartes().get(0).getCouleur() == Couleur.AUTRE) {
			if (pli.getCartes().size() > 1) {
				pli.setCouleurJouee(pli.getCartes().get(1).getCouleur());
			} else {
				pli.setCouleurJouee(Couleur.AUTRE);
			}
		}
		// Ce joueur est le premier à jouer, il n'y a pas de restriction sur ses
		// choix
		// Le cas est le même si la couleur du pli est "autre". Cela signifie
		// que seule l'excuse a été jouée pour le moment
		if (pli.getCartes().isEmpty() || pli.getCouleurJouee() == Couleur.AUTRE) {
			// Choisi une carte au hasard
			carte = this.choisiLaPremiereCarteAJouer(done, pli, previousPlis);
			pli.setCouleurJouee(carte.getCouleur());
		} else {
			Couleur couleurDuPli = pli.getCouleurJouee();
			if (couleurDuPli != Couleur.ATOUT) {
				// ce n'est pas un tour à l'atout
				List<Carte> cartesDeLaBonneCouleur = main.stream().filter(c -> c.getCouleur() == couleurDuPli)
						.collect(Collectors.toList());
				// Joueur n'a plus de carte de la bonne couleur.
				// Il doit couper
				if (cartesDeLaBonneCouleur.isEmpty()) {
					cartesDeLaBonneCouleur = main.stream().filter(c -> c.getCouleur() == Couleur.ATOUT)
							.collect(Collectors.toList());
					// Il n'a plus d'atout, il doit "pisser"
					if (cartesDeLaBonneCouleur.isEmpty()) {
						cartesDeLaBonneCouleur = main;
						carte = this.choisiParmiLesCartesDeLaBonneCouleur(done, cartesDeLaBonneCouleur, pli,
								previousPlis);
					} else {
						carte = this.selectionneUnAtout(done, main, pli, previousPlis);
					}
				} else {
					// Il reste des cartes de cette couleur.
					carte = this.choisiParmiLesCartesDeLaBonneCouleur(done, cartesDeLaBonneCouleur, pli, previousPlis);
				}
			} else {
				List<Carte> goodCouleurCartes = main.stream().filter(c -> c.getCouleur() == Couleur.ATOUT)
						.collect(Collectors.toList());
				if (goodCouleurCartes.isEmpty()) {
					goodCouleurCartes = main;
					carte = this.choisiParmiLesCartesDeLaBonneCouleur(done, goodCouleurCartes, pli, previousPlis);
				} else {
					carte = this.selectionneUnAtout(done, main, pli, previousPlis);
				}
			}
		}
		// Ajouter la carte au pli
		pli.getCartes().add(carte);
		// Supprimer la carte de la main du joueur
		main.remove(carte);
		return carte;
	}

	private Carte selectionneUnAtout(Done done, List<Carte> main, Pli pli, List<Pli> plisPrecedents) {
		Carte card = null;
		int puissanceDeCarteLaPlusForteDesPlisPrecedents = 0;
		for (final Carte carteDuPli : pli.getCartes()) {
			if (carteDuPli.getNom().getPuissance() > puissanceDeCarteLaPlusForteDesPlisPrecedents) {
				puissanceDeCarteLaPlusForteDesPlisPrecedents = carteDuPli.getNom().getPuissance();
			}
		}

		final List<Carte> atoutsEnMain = main.stream().filter(c -> c.getCouleur() == Couleur.ATOUT)
				.collect(Collectors.toList());
		// tri les atouts par puissance croissante
		atoutsEnMain.sort((c1, c2) -> c1.getNom().getPuissance() - c2.getNom().getPuissance());
		for (final Carte atout : atoutsEnMain) {
			if (atout.getNom().getPuissance() > puissanceDeCarteLaPlusForteDesPlisPrecedents) {
				card = atout;
				break;
			}
		}
		// Aucun atout plus fort n'a été trouvé
		if (card == null) {
			if (atoutsEnMain.isEmpty()) {
				card = this.choisiParmiLesCartesDeLaBonneCouleur(done, main, pli, plisPrecedents);
			} else {
				// choisir un atout plus petit
				card = atoutsEnMain.get(0);
				// Si c'est le petit, essayez d'en donner une autre si c'est
				// possible. Exception : un partenaire a la main et est sur de
				// remporter le pli, auquel cas, il faut donner le petit pour le
				// sauver
				if (card.getNom() == Nom.PETIT && !equipeFeraLePli(pli, plisPrecedents)) {
					// pour ça, on supprimer le petit de la liste d'atouts
					atoutsEnMain.removeIf(c -> c.getNom() == Nom.PETIT);
					if (atoutsEnMain.size() != 0) {
						// S'il reste encore des atouts sans le petit, en
						// sélectionner une autre, sinon, le petit sera
						// sélectionné
						card = this.choisiParmiLesCartesDeLaBonneCouleur(done, atoutsEnMain, pli, plisPrecedents);
					}
				}
			}
		}
		return card;
	}

	private boolean equipeFeraLePli(Pli pli, List<Pli> plisPrecedents) {
		// L'équipe fera le pli si un joueur ayant déjà joué une carte dans le
		// pli en cours est la plus forte étant donné tous les autres plis
		// passés
		List<Carte> cartesDejaJouees = plisPrecedents.stream().flatMap(p -> p.getCartes().stream())
				.collect(Collectors.toList());
		cartesDejaJouees.removeIf(carte -> carte.getCouleur() != Couleur.ATOUT);
		cartesDejaJouees.stream().sorted((c1, c2) -> c2.getNom().getPuissance() - c1.getNom().getPuissance());

		// A partir de maintenant, il faut trouver le trou (un trou = une carte
		// qui n'a pas été jouée) de la plus grande puissance possible. Ce trou
		// sera la carte maître des atouts parmi les cartes encore à jouer avant
		// le pli en cours
		boolean trouTrouve = false;
		int puissanceDeLaPlusForteCarteQuiResteAJouer = Nom.VINGTETUN_ATOUT.getPuissance();
		while (!trouTrouve) {
			boolean carteTrouvee = false;
			for (int i = 0; i < cartesDejaJouees.size() && !carteTrouvee; i++) {
				if (cartesDejaJouees.get(i).getNom().getPuissance() == puissanceDeLaPlusForteCarteQuiResteAJouer) {
					puissanceDeLaPlusForteCarteQuiResteAJouer--;
					carteTrouvee = true;
				}
			}
			if (!carteTrouvee) {
				trouTrouve = true;
			}
		}
		// Maintenant qu'on sait quelle est la carte qui serait maître dans un
		// tour à l'atout, il faut vérifier si un joueur allié ne l'aurait pas
		// joué à ce tour.
		boolean equipeFeraLePli = false;
		for (Carte carteJouee : pli.getCartes()) {
			if (carteJouee.getCouleur() == Couleur.ATOUT
					&& carteJouee.getNom().getPuissance() == puissanceDeLaPlusForteCarteQuiResteAJouer
					&& isDansMemeEquipe(carteJouee.getProprietaireInitial())) {
				equipeFeraLePli = true;
			}
		}
		return equipeFeraLePli;
	}

	private Carte choisiParmiLesCartesDeLaBonneCouleur(final Done done, final List<Carte> cards, final Pli pli,
			final List<Pli> plisPrecedents) {
		Carte carteAJouer = null;
		final Optional<Carte> excuse = this.getMain().stream().filter(c -> c.getCouleur() == Couleur.AUTRE).findAny();

		// Cas particulier de l'excuse : si c'est l'avant dernière carte dans la
		// main : il faut la jouer pour éviter qu'elle ne parte avec le dernier
		// pli
		if ((this.getMain().size() == 2) && (excuse.isPresent())) {
			carteAJouer = excuse.get();
		}
		// S'il y a une coupe (présence d'un atout dans le pli en cours)
		// Note qu'un tour d'atout est géré différemment de cette méthode
		else if (pli.getCartes().stream().filter(c -> c.getCouleur() == Couleur.ATOUT).count() != 0) {
			// Faut-il "donner" des points ?
			if (pli.fautIlDonnerDesPoints(this, done)) {
				carteAJouer = cards.get(cards.size() - 1);
			} else {
				List<Carte> sortedCards = cards.stream()
						.sorted((c1, c2) -> ((Double) c1.getNombreDePoints()).compareTo(c2.getNombreDePoints()))
						.collect(Collectors.toList());
				carteAJouer = sortedCards.get(0);
			}
		}
		// Si le joueur joue en dernier, et qu'il n'y a pas de coupe, il peut
		// jouer la carte la plus forte pour espérer remporter le pli
		else if (pli.equipeJoueDerniere(done, this)) {
			// Il faudrait jouer la carte la plus puissante si
			// - Cette carte est maîtresse sur le pli
			// - L'équipe est maîtresse et le joueur peut donner des points (si
			// pas de point à donner, il vaut mieux conserver les cartes les
			// plus puissantes)
			if (pli.isCartePeutRemporterPli(cards.get(cards.size() - 1))
					|| (this.isEquipeEstMaitre(pli, plisPrecedents, done)
							&& (cards.get(cards.size() - 1).getNombreDePoints() > 0.5))) {
				carteAJouer = cards.get(cards.size() - 1);
			} else {
				// Dans le cas contraire, choisir la moins puissante des cartes
				carteAJouer = cards.get(0);
			}
		}
		// Ce n'est pas le dernier joueur
		else {
			// Est-ce que la carte la plus puissante de la main est une bonne
			// carte à jouer ?
			// - Oui si la carte est maîtresse dans la couleur (Roi, ou Dame si
			// le Roi est déjà passé, ...)
			// - L'équipe est maître et il reste des points à donner

			if (done.isLaCarteLaPlusForte(cards.get(cards.size() - 1), plisPrecedents)
					|| (this.isEquipeEstMaitre(pli, plisPrecedents, done)
							&& (cards.get(cards.size() - 1).getNombreDePoints() > 0.5))) {
				carteAJouer = cards.get(cards.size() - 1);
			} else {
				carteAJouer = cards.get(0);
			}
		}
		// Rien ne semble être un bon choix, on peut essayer de trouver la plus
		// petite carte pour perdre le moins de points
		cards.sort((c1, c2) -> c1.getNom().getPuissance() - c2.getNom().getPuissance());
		if (carteAJouer == null) {
			carteAJouer = cards.get(0);
		}
		return carteAJouer;
	}

	private boolean isEquipeEstMaitre(Pli pli, List<Pli> plisPrecedents, Done done) {
		boolean winning = false;
		if (!pli.getCartes().isEmpty()) {
			// Est-ce que les équipes sont connues
			final Equipe playerTeam = this.getEquipe();
			if (playerTeam != Equipe.INCONNUE) {
				// Est-ce que les co-équipiers ont déjà joué
				boolean alreadyPlayed = false;
				final List<Carte> teammateCards = new ArrayList<>();
				// Vérifier le propriétaire de chaque carte
				for (final Carte playedCard : pli.getCartes()) {
					final Equipe cardTeam = playedCard.getProprietaireInitial().getEquipe();
					if ((cardTeam == playerTeam) && (cardTeam != Equipe.INCONNUE)) {
						alreadyPlayed = true;
						teammateCards.add(playedCard);
					}
				}
				if (alreadyPlayed) {
					for (final Carte card : teammateCards) {
						if (pli.isCartePeutRemporterPli(card) && done.isLaCarteLaPlusForte(card, plisPrecedents)) {
							winning = true;
						}
					}
				}
			}

		}
		return winning;
	}

	private Carte choisiLaPremiereCarteAJouer(Done done, Pli pli, List<Pli> plisPrecedents) {
		Carte card = null;
		final Optional<Carte> excuse = this.getMain().stream().filter(c -> c.getCouleur() == Couleur.AUTRE).findAny();

		// Cas particulier de l'excuse : si c'est l'avant dernière carte dans la
		// main : il faut la jouer pour éviter qu'elle ne parte avec le dernier
		// pli
		if ((this.getMain().size() == 2) && (excuse.isPresent())) {
			card = excuse.get();
		} else if (this.getMain().size() == 1) {
			card = this.getMain().get(0);
		} else {
			// Combien de cartes de chaque couleur reste-t-il dans la main ?
			final Map<Couleur, Integer> nombreDeCartesParCouleur = new HashMap<>();
			nombreDeCartesParCouleur.put(Couleur.AUTRE,
					(int) getMain().stream().filter(c -> c.getCouleur() == Couleur.AUTRE).count());
			nombreDeCartesParCouleur.put(Couleur.PIQUE,
					(int) getMain().stream().filter(c -> c.getCouleur() == Couleur.PIQUE).count());
			nombreDeCartesParCouleur.put(Couleur.COEUR,
					(int) getMain().stream().filter(c -> c.getCouleur() == Couleur.COEUR).count());
			nombreDeCartesParCouleur.put(Couleur.CARREAU,
					(int) getMain().stream().filter(c -> c.getCouleur() == Couleur.CARREAU).count());
			nombreDeCartesParCouleur.put(Couleur.TREFLE,
					(int) getMain().stream().filter(c -> c.getCouleur() == Couleur.TREFLE).count());
			nombreDeCartesParCouleur.put(Couleur.ATOUT,
					(int) getMain().stream().filter(c -> c.getCouleur() == Couleur.ATOUT).count());
			final List<Joueur> autresJoueurs = pli.getListeJoueurOrdonnes(done, this);
			List<Carte> cartesInteressantes = new ArrayList<>();
			List<Couleur> couleursInteressantes = new ArrayList<>();
			boolean ouvreur = false;
			// Si le joueur suivant (idéal) ou le suivant est dans l'autre
			// équipe, on peut essayer d'ouvrir une couleur à condition d'être
			// en défense
			if ((!this.isDansMemeEquipe(autresJoueurs.get(1))) || (!this.isDansMemeEquipe(autresJoueurs.get(2)))) {
				couleursInteressantes = this.getCouleursNonOuvertes(plisPrecedents);
				// Trie les couleurs du plus petit nombre de cartes au plus
				// grand
				ouvreur = true;
			} else {
				couleursInteressantes = this.getCouleursDejaOuvertes(plisPrecedents);
			}
			final List<Couleur> couleursOrdonnees = nombreDeCartesParCouleur.keySet().stream()
					.sorted((e1, e2) -> nombreDeCartesParCouleur.get(e2) - nombreDeCartesParCouleur.get(e1))
					.collect(Collectors.toList());
			couleursOrdonnees.removeIf(e -> nombreDeCartesParCouleur.get(e) == 0);
			for (final Couleur couleurOrdonnee : couleursOrdonnees) {
				for (final Couleur couleur : couleursInteressantes) {
					final List<Carte> cartesDeCouleurInteressante = this.getMain().stream()
							.filter(c -> c.getCouleur() == couleur).collect(Collectors.toList());
					for (final Carte carteDeCouleurInteressante : cartesDeCouleurInteressante) {
						if (carteDeCouleurInteressante.getCouleur() == couleurOrdonnee) {
							if (ouvreur && getEquipe() == Equipe.DEFENSE) {
								// Si le joueur veut ouvrir, il ne faut
								// peut-être
								// pas donner de points
								// FIXME: Si la coupe a déjà été trouvée, on
								// peut
								// peut-être tenter un roi
								if (carteDeCouleurInteressante.getNombreDePoints() == 0.5) {
									cartesInteressantes.add(carteDeCouleurInteressante);
								}
							} else {
								// On ne veut pas ouvrir, autant jouer une
								// petite
								// carte ou bien la plus forte si elle est dans
								// la
								// main
								if (done.isLaCarteLaPlusForte(carteDeCouleurInteressante, plisPrecedents)
										|| (carteDeCouleurInteressante.getNombreDePoints() == 0.5)) {
									cartesInteressantes.add(carteDeCouleurInteressante);
								}
							}
						}
					}
				}
			}
			if (cartesInteressantes.isEmpty()) {
				// Hum ... pas terrible, mais rien ne semble intéressant à
				// jouer. Autant essayer de sélectionner une carte sans valeur
				cartesInteressantes = this.getMain().stream()
						.filter(c -> done.isLaCarteLaPlusForte(c, plisPrecedents) || c.getNombreDePoints() == 0.5)
						.collect(Collectors.toList());
				if (cartesInteressantes.isEmpty()) {
					cartesInteressantes = this.getMain();
				}
			}
			card = cartesInteressantes.stream()
					.sorted((c1, c2) -> (int) (c1.getNombreDePoints()) - (int) (c2.getNombreDePoints()))
					.collect(Collectors.toList()).get(0);
			// card = cartesInteressantes.get(cardIndex);
		}
		return card;
	}

	private List<Couleur> getCouleursDejaOuvertes(List<Pli> plisPrecedents) {
		final List<Couleur> toutesLesCouleurs = new ArrayList<>();
		toutesLesCouleurs.add(Couleur.PIQUE);
		toutesLesCouleurs.add(Couleur.COEUR);
		toutesLesCouleurs.add(Couleur.CARREAU);
		toutesLesCouleurs.add(Couleur.TREFLE);
		toutesLesCouleurs.removeAll(this.getCouleursNonOuvertes(plisPrecedents));
		return toutesLesCouleurs;
	}

	private List<Couleur> getCouleursNonOuvertes(List<Pli> plisPrecedents) {
		final List<Couleur> toutesLesCouleurs = new ArrayList<>();
		toutesLesCouleurs.add(Couleur.PIQUE);
		toutesLesCouleurs.add(Couleur.COEUR);
		toutesLesCouleurs.add(Couleur.CARREAU);
		toutesLesCouleurs.add(Couleur.TREFLE);
		for (final Pli pli : plisPrecedents) {
			toutesLesCouleurs.remove(pli.getCouleurJouee());
		}
		return toutesLesCouleurs;
	}

	public void reinitialiseLesCartes() {
		for (Carte carte : main) {
			carte.setProprietaireInitial(null);
			carte.setProprietaireFinal(null);
		}
	}

	public Carte choisiUnRoi(List<Carte> rois, int nombreDeJoueurs) {
		// On commence par exclure les rois qu'on a en main.
		rois.removeIf(roi -> roi.getProprietaireInitial() == this);

		Carte roi = null;
		// les cartes de l'As au 10 + V + C + D + R
		int moyenneDeCarteParJoueur = (int) (14 / nombreDeJoueurs) + 1;

		List<Triple> triples = new ArrayList<>();
		
		Triple triple = null;
		for (Couleur couleur : rois.stream().map(Carte::getCouleur).collect(Collectors.toList())) {
			triple = new Triple();
			triple.setCouleur(couleur);
			double nombreDePoints = 0d;
			final List<Carte> cartesDeLaBonneCouleur = this.main.stream().filter(carte -> carte.getCouleur() == couleur)
					.collect(Collectors.toList());
			int nombreDeCartes = cartesDeLaBonneCouleur.size();
			triple.setNombreDeCartes(nombreDeCartes);

			for (Carte carte : cartesDeLaBonneCouleur) {
				if (carte.getNombreDePoints() > 0.5) {
					nombreDePoints += carte.getNombreDePoints();
				}
			}
			triple.setNombreDePoints(nombreDePoints);
			triples.add(triple);
		}
		
		Collections.sort(triples);
		triples.removeIf(t->t.getNombreDeCartes() == 0);
		if (triples.isEmpty()) {
			triples.add(triple);
		}
		roi = rois.stream().filter(carte->carte.getCouleur() == triples.get(0).getCouleur()).findFirst().get();
		
		return roi;
	}

	private class Triple implements Comparable<Triple>{
		Couleur couleur;
		int nombreDeCartes;
		double nombreDePoints;

		public Triple() {
		}

		public Couleur getCouleur() {
			return couleur;
		}

		public void setCouleur(Couleur couleur) {
			this.couleur = couleur;
		}

		public int getNombreDeCartes() {
			return nombreDeCartes;
		}

		public void setNombreDeCartes(int nombreDeCartes) {
			this.nombreDeCartes = nombreDeCartes;
		}

		public double getNombreDePoints() {
			return nombreDePoints;
		}

		public void setNombreDePoints(double nbPoints) {
			this.nombreDePoints = nbPoints;
		}

		@Override
		public int compareTo(Triple autre) {
			if (autre.getNombreDeCartes() < this.nombreDeCartes) {
				return 1;
			} if (autre.getNombreDeCartes() == this.nombreDeCartes) {
				if (autre.getNombreDePoints() < this.nombreDePoints) {
					return 1;
				} else if (autre.getNombreDePoints() > this.nombreDePoints) {
					return -1;
				} else {
					return 0;
				}
			}
			return 0;
		}
		
		public void calculeValeur() {
			
		}
		
		@Override
		public String toString() {
			return nombreDeCartes + " " + couleur + " pour " + nombreDePoints + " points";
		}
		
	}
}
