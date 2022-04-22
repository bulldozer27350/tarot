package jeu.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jeu.entities.Carte.Couleur;
import jeu.entities.Carte.Nom;
import jeu.entities.Joueur.Equipe;

public class Done {

	public enum Contrat {
		PASSE(1, true, false), PETITE(1, true, true), GARDE(2, true, true), GARDE_SANS(4, true, false), GARDE_CONTRE(6,
				false, false);

		int coefficientMultiplicateur;
		boolean gardeLeChien;
		boolean chienVisible;

		private Contrat(int coefficientMultiplicateur, boolean gardeLeChien, boolean chienVisible) {
			this.coefficientMultiplicateur = coefficientMultiplicateur;
			this.gardeLeChien = gardeLeChien;
			this.chienVisible = chienVisible;
		}
	}

	private List<Carte> cartes = new ArrayList<>();
	private List<Carte> chien = new ArrayList<>();
	private List<Joueur> joueurs = new ArrayList<>();
	private Joueur joueurSuivant;
	private List<Pli> plis = new ArrayList<>();
	private Contrat contrat;

	private double nombreDePointsEnAttaque;
	private double nombreDePointsEnDefense;

	public List<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}

	public List<Carte> getChien() {
		return chien;
	}

	public void setChien(List<Carte> chien) {
		this.chien = chien;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public Joueur getJoueurSuivant() {
		return joueurSuivant;
	}

	public void setJoueurSuivant(Joueur joueurSuivant) {
		this.joueurSuivant = joueurSuivant;
	}

	public List<Pli> getPlis() {
		return plis;
	}

	public void setPlis(List<Pli> plis) {
		this.plis = plis;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public void distribuer() {
		int cardsSize = this.getCartes().size();
		final int nombreDeCartesChacun = 3;
		for (int i = 0; i < cardsSize; i = i + nombreDeCartesChacun) {
			Joueur joueur = this.joueurs.get(i % this.joueurs.size());
			distribuerUneCarte(joueur, i);
			distribuerUneCarte(joueur, i+1);
			distribuerUneCarte(joueur, i+2);
		}
	}

	private void distribuerUneCarte(Joueur joueur, int indiceCarte) {
		Carte carteDistribuee = this.getCartes().get(indiceCarte);
		carteDistribuee.setProprietaireInitial(joueur);
		carteDistribuee.setProprietaireFinal(null);
		joueur.ajouterASaMain(carteDistribuee);
	}

	public boolean isLaCarteLaPlusForte(Carte carteAJouer, List<Pli> plis) {
		List<Carte> allFoldedCards = new ArrayList<>();
		plis.stream().forEach(f -> allFoldedCards.addAll(f.getCartes()));
		List<Nom> remainingNames = getRemainingCardsInGivenColor(allFoldedCards, carteAJouer.getCouleur());
		boolean good = false;
		// si cette carte est la dernière carte de cette couleur, c'est
		// nécessairement la plus puissante
		if (remainingNames.isEmpty()) {
			good = true;
		} else {
			// Est-ce que la carte à jouer est la plus puissante carte de cette
			// couleur ?
			boolean plusPuissante = remainingNames.get(remainingNames.size() - 1).getPuissance() <= carteAJouer.getNom()
					.getPuissance();

			// Est-ce que cette couleur a déjà été coupée dans les précédents
			// plis ?
			boolean aEteCoupee = false;
			for (Joueur joueur : this.getJoueurs()) {
				for (Pli pli : joueur.getPlis()) {
					if (pli.getCouleurJouee() == carteAJouer.getCouleur()
							&& pli.getCartes().stream().filter(c -> c.getCouleur() == Couleur.ATOUT).count() != 0) {
						aEteCoupee = true;
					}
				}
			}
			good = plusPuissante && !aEteCoupee;
		}
		return good;
	}

	private List<Nom> getRemainingCardsInGivenColor(List<Carte> foldedCards, Couleur selectedColor) {
		List<Nom> values = new ArrayList<>();
		values.add(Nom.AS);
		values.add(Nom.DEUX);
		values.add(Nom.TROIS);
		values.add(Nom.QUATRE);
		values.add(Nom.CINQ);
		values.add(Nom.SIX);
		values.add(Nom.SEPT);
		values.add(Nom.HUIT);
		values.add(Nom.NEUF);
		values.add(Nom.DIX);
		values.add(Nom.VALET);
		values.add(Nom.CAVALIER);
		values.add(Nom.DAME);
		values.add(Nom.ROI);
		List<Nom> remainingCards = new ArrayList<>();
		foldedCards.stream().filter(e -> e.getCouleur() == selectedColor).forEach(e -> remainingCards.add(e.getNom()));
		values.removeIf(e -> remainingCards.contains(e));
		return values;
	}

	public Carte getCarte(Carte carte) {
		Carte carteCherchee = null;
		for (Joueur joueur : this.getJoueurs()) {
			for (Carte carteEnMain : joueur.getMain()) {
				if (carteEnMain.getCouleur() == carte.getCouleur() && carteEnMain.getNom().equals(carte.getNom())) {
					carteCherchee = carteEnMain;
				}
			}
		}
		return carteCherchee;
	}

	public void calculerLesPoints() {
		this.calculerLesPointsDeLaDone();
		this.mettreAJourLesScoresDesJoueurs();

		System.out.println("Récapitulatif des scores");
		int total = 0;
		for (Joueur joueur : joueurs) {
			total += joueur.getNombreDePoints();
			System.out.println(joueur.getNom() + " a " + joueur.getNombreDePoints() + " points.");
		}
		if (total == 0) {
			System.out.println("Le total est bien égal à 0");
		} else {
			System.err.println("ALERTE : Le nombre de points n'est pas égal à 0 !! " + total);
		}
	}

	private void mettreAJourLesScoresDesJoueurs() {
		int scoreDeBase = 25;
		final double leNombreDePointsRequis = getLeNombreDePointsRequis();
		if (victoireDeLAttaque()) {
			System.out.println("Victoire de l'attaque");
			// Nombre de points requis = 36,41, 51 ou 56 fonction du nombre de
			// bouts
			double nombreDePointsBonus = nombreDePointsEnAttaque - leNombreDePointsRequis;
			int bonus = (int) Math.round(nombreDePointsBonus / 5) * 5;
			System.out.println("score requis : " + leNombreDePointsRequis + ". Contrat réussi de " + nombreDePointsBonus
					+ " points. Bonus arrondi à " + bonus);
			int primeParJoueur = scoreDeBase + bonus;
			Optional<Joueur> preneur = this.getJoueurs().stream().filter(Joueur::isPreneur).findFirst();
			if (preneur.isPresent()) {
				preneur.get().setNombreDePoints(preneur.get().getNombreDePoints() + primeParJoueur + primeParJoueur);
			}
			Optional<Joueur> equipier = this.getJoueurs().stream().filter(j -> !j.isPreneur())
					.filter(j -> j.getEquipe() == Equipe.ATTAQUE).findFirst();
			if (equipier.isPresent()) {
				equipier.get().setNombreDePoints(equipier.get().getNombreDePoints() + primeParJoueur);
			} else {
				// Si l'attaquant a pris tout seul, il récupère la part
				// qu'aurait eu son équipier
				preneur.get().setNombreDePoints(preneur.get().getNombreDePoints() + primeParJoueur);
			}
			// Réduit le score des défenseurs de la prime.
			this.getJoueurs().stream().filter(j -> !j.isPreneur()).filter(j -> j.getEquipe() == Equipe.DEFENSE)
					.collect(Collectors.toList())
					.forEach(j -> j.setNombreDePoints(j.getNombreDePoints() - primeParJoueur));
		} else {
			System.out.println("Défaite de l'attaque");
			double nombreDePointsMalus = leNombreDePointsRequis - nombreDePointsEnAttaque;
			int malus = (int) Math.round(nombreDePointsMalus / 5) * 5;
			System.out.println("score requis : " + leNombreDePointsRequis + ". Contrat manqué de " + nombreDePointsMalus
					+ " points. Malus arrondi à " + malus);
			int amendeParJoueur = scoreDeBase + malus;
			Optional<Joueur> preneur = this.getJoueurs().stream().filter(Joueur::isPreneur).findFirst();
			if (preneur.isPresent()) {
				preneur.get().setNombreDePoints(preneur.get().getNombreDePoints() - amendeParJoueur - amendeParJoueur);
			}
			Optional<Joueur> equipier = this.getJoueurs().stream().filter(j -> !j.isPreneur())
					.filter(j -> j.getEquipe() == Equipe.ATTAQUE).findFirst();
			if (equipier.isPresent()) {
				equipier.get().setNombreDePoints(equipier.get().getNombreDePoints() - amendeParJoueur);
			} else {
				// Si l'attaquant a pris tout seul, il perd aussi l'amende
				// qu'aurait eu son équipier
				preneur.get().setNombreDePoints(preneur.get().getNombreDePoints() - amendeParJoueur);
			}
			// Augmente le score des défenseurs de l'amende.
			this.getJoueurs().stream().filter(j -> j.getEquipe() == Equipe.DEFENSE).collect(Collectors.toList())
					.forEach(j -> j.setNombreDePoints(j.getNombreDePoints() + amendeParJoueur));
		}
	}

	public boolean victoireDeLAttaque() {
		return getLeNombreDePointsRequis() <= nombreDePointsEnAttaque;
	}

	private double getLeNombreDePointsRequis() {
		List<Carte> cartesAttaque = new ArrayList<>();
		if (contrat.gardeLeChien) {
			cartesAttaque.addAll(getChien());
		}
		// Une fois récupérées les cartes du chien, il faut lister l'ensemble
		// des cartes de l'attaque
		cartesAttaque.addAll(this.getJoueurs().stream().filter(e -> e.getEquipe() == Equipe.ATTAQUE)
				.flatMap(j -> j.getPlis().stream()).flatMap(p -> p.getCartes().stream()).collect(Collectors.toList()));
		int nombreDeBouts = (int) cartesAttaque.stream().filter(Carte::isBout).count();
		// Initialisation à 0 bout
		double nombreDePointsRequis = 56;
		if (nombreDeBouts == 1) {
			nombreDePointsRequis = 51;
		} else if (nombreDeBouts == 2) {
			nombreDePointsRequis = 41;
		} else if (nombreDeBouts == 3) {
			nombreDePointsRequis = 36;
		}
		return nombreDePointsRequis;
	}

	private void calculerLesPointsDeLaDone() {

		// FIXME : Penser ici à gérer l'excuse : la rendre si elle est
		// "remportée" par la mauvaise équipe, contre une autre carte de faible
		// valeur

		final List<Joueur> attackPlayers = new ArrayList<>();

		// System.out.println("*********************");
		System.out.println("Scores finaux : ");
		// System.out.println("#########################");
		// System.out.println("Liste des cartes en attaque : ");
		double scoreAttaque = 0;
		for (final Joueur player : this.getJoueurs().stream().filter(e -> e.getEquipe() == Equipe.ATTAQUE)
				.collect(Collectors.toList())) {
			attackPlayers.add(player);
			for (final Pli pli : player.getPlis()) {
				for (Carte card : pli.getCartes()) {
					// System.out.println(card);
					scoreAttaque = ajouterDoubles(scoreAttaque, card.getNombreDePoints());
				}
			}
		}
		this.nombreDePointsEnAttaque = scoreAttaque;

		double scoreDefense = 0;
		final List<Joueur> defensePlayers = new ArrayList<>();
		// System.out.println("#########################");
		// System.out.println("Liste des cartes en défense : ");
		for (final Joueur player : this.getJoueurs().stream().filter(e -> e.getEquipe() == Equipe.DEFENSE)
				.collect(Collectors.toList())) {
			defensePlayers.add(player);
			for (final Pli pli : player.getPlis()) {
				for (Carte card : pli.getCartes()) {
					// System.out.println(card);
					scoreDefense = ajouterDoubles(scoreDefense, card.getNombreDePoints());
				}
			}
		}
		this.nombreDePointsEnDefense = scoreDefense;

		double nombreDePointsAuChien = 0;
		for (int i = 0; i < this.getChien().size(); i++) {
			final Carte card = this.getChien().get(i);
			card.setProprietaireInitial(null);
			nombreDePointsAuChien = ajouterDoubles(nombreDePointsAuChien, card.getNombreDePoints());
		}
		if (contrat.gardeLeChien) {
			this.nombreDePointsEnAttaque = ajouterDoubles(this.nombreDePointsEnAttaque, nombreDePointsAuChien);
			System.out.println("(chien : \t" + nombreDePointsAuChien + "\t points déjà ajoutés à l'attaque)");
		} else {
			this.nombreDePointsEnDefense = ajouterDoubles(this.nombreDePointsEnDefense, nombreDePointsAuChien);
			System.out.println("(chien : \t" + nombreDePointsAuChien + "\t points déjà ajoutés à la défense)");
		}
		System.out.println("Equipe de l'attaque " + Arrays.toString(attackPlayers.toArray()) + " : \t"
				+ this.nombreDePointsEnAttaque + "\t points");
		System.out.println("Equipe de défense " + Arrays.toString(defensePlayers.toArray()) + " : \t" + scoreDefense
				+ "\t points");
		System.out.println("#########################");
		System.out.println(
				"Total : " + ajouterDoubles(this.nombreDePointsEnAttaque, this.nombreDePointsEnDefense) + "\t points");
	}

	private double ajouterDoubles(double d1, double d2) {
		int d1bis = (int) (d1 * 10);
		int d2bis = (int) (d2 * 10);
		int somme = d1bis + d2bis;
		double sommeFinale = ((double) somme) / 10;
		return sommeFinale;
	}

	public void reinitialiseLesJoueurs() {
		for (Joueur joueur : joueurs) {
			joueur.reinitialiseLesCartes();
			joueur.setPlis(new ArrayList<>());
			joueur.setMain(new ArrayList<>());
			joueur.setPreneur(false);
			joueur.setEquipe(Equipe.INCONNUE);
		}
		for (Carte carte : getChien()) {
			carte.setProprietaireInitial(null);
			carte.setProprietaireFinal(null);
		}
	}

	public void determineContrat() {
		Joueur preneur = null;
		// Ici, il faudra déterminer le contrat et le preneur

		// Ensuite il faut déterminer les équipes
		for (Joueur joueur : getJoueurs()) {
			if (!joueur.isPreneur()) {
				joueur.setEquipe(Equipe.DEFENSE);
			} else {
				joueur.setEquipe(Equipe.ATTAQUE);
				preneur = joueur;
			}
		}

		// Ensuite, il faut choisir un roi
		Carte roi = null;
		final List<Carte> rois = cartes.stream().filter(carte -> carte.getNom() == Nom.ROI)
				.collect(Collectors.toList());
		rois.addAll(chien.stream().filter(carte -> carte.getNom() == Nom.ROI)
				.collect(Collectors.toList()));
		roi = preneur.choisiUnRoi(rois, joueurs.size());

		// Et mettre à jour les équipes (l'appelé sera donc en attaque)
		final Joueur equipier = roi.getProprietaireInitial();
		if (equipier != null) {
			// Si l'équipier est nul, c'est que le roi est dans la main du joueur ou que le roi est au chien
			equipier.setEquipe(Equipe.ATTAQUE);
		}

		System.out.println(preneur + " appelle " + equipier + " au roi de " + roi.getCouleur());
	}

}
