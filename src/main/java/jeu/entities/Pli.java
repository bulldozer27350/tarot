package jeu.entities;

import java.util.ArrayList;
import java.util.List;

import jeu.entities.Carte.Couleur;

public class Pli {

	private List<Carte> cards = new ArrayList<>();
	private Joueur gagnant;
	private Couleur couleurJouee;

	public List<Carte> getCartes() {
		return cards;
	}

	public void setCartes(List<Carte> cards) {
		this.cards = cards;
	}

	public Joueur getGagnant() {
		return gagnant;
	}

	public void setGagnant(Joueur gagnant) {
		this.gagnant = gagnant;
	}

	public Couleur getCouleurJouee() {
		return couleurJouee;
	}

	public void setCouleurJouee(Couleur couleurJouee) {
		this.couleurJouee = couleurJouee;
	}

	public List<Joueur> getListeJoueurOrdonnes(Done done, Joueur joueur) {
		List<Joueur> joueurs = new ArrayList<>();
		final List<Joueur> joueursDeLaDone = done.getJoueurs();
		int playerIndex = 0;
		// Rechercher l'index du joueur
		for (int i = 0; i < joueursDeLaDone.size(); i++) {
			Joueur p = joueursDeLaDone.get(i);
			if (p.getNom().equals(joueur.getNom())) {
				playerIndex = i;
			}
		}
		for (int i = 0; i < joueursDeLaDone.size(); i++) {
			joueurs.add(joueursDeLaDone.get((i + playerIndex) % joueursDeLaDone.size()));
		}
		// Supprimer les cartes d�j� jou�es
		this.getCartes().stream().forEach(e -> joueurs.remove(e.getProprietaireInitial()));
		return joueurs;
	}

	public boolean fautIlDonnerDesPoints(Joueur joueur, Done done) {
		boolean givePoints = false;
		if (this.getCartes().isEmpty()) {
			throw new UnsupportedOperationException("To know if we can give points, we must have a not empty fold !");
		}
		if (this.getCartes().stream().filter(card -> card.getCouleur() == Couleur.ATOUT).count() != 0) {
			// There is a cut. Is it possible to give points ?
			// Who has the highest trump on the fold ?
			List<Carte> foldCards = new ArrayList<>(this.getCartes());
			foldCards.sort((e1, e2) -> e2.getNom().getPuissance() - e1.getNom().getPuissance());
			if (joueur.isDansMemeEquipe(foldCards.get(0).getProprietaireInitial())) {
				givePoints = equipeJoueDerniere(done, joueur);
			}
		}
		return givePoints;
	}

	public boolean equipeJoueDerniere(Done done, Joueur joueur) {
		boolean derniere = false;

		// On ne peut pas prendre le risque de donner des points si on ne joue
		// pas en derni�re position. Il faut donc �tre s�r de jouer en dernier

		if (this.getCartes().size() == done.getJoueurs().size() - 1) {
			derniere = true;
		} else {
			// Consid�rons que les �quipes sont connues (il faudra g�rer le cas
			// o� les �quipes ne sont pas connues => actuellement le joueur
			// connait SON �quipe, mais il n'y a pas de code permettant de
			// conna�tre l'�quipe d'un autre joueur, sauf en trichant et en lui
			// demandant : joueur.getEquipe(), c'est de la triche)
			final int nombreAdversaires = (int) done.getJoueurs().stream().filter(e -> !joueur.isDansMemeEquipe(e))
					.count();
			int nombreDeCartesJoueesParAdversaires = 0;
			for (final Carte card : this.getCartes()) {
				if (!joueur.isDansMemeEquipe(card.getProprietaireInitial())) {
					nombreDeCartesJoueesParAdversaires++;
				}
			}
			// Si tous les adversaires ont jou� leur cartes, alors l'�quipe joue
			// derni�re
			derniere = nombreDeCartesJoueesParAdversaires == nombreAdversaires;
		}
		return derniere;
	}
	
	public boolean isCartePeutRemporterPli(Carte carte) {
		boolean peutElle = false;
		int plusGrandePuissanceDuPli = 0;
		Carte carteLaPlusForteDuPli = null;
		for (Carte card : this.getCartes()) {
			if (card.getNom().getPuissance() > plusGrandePuissanceDuPli
					&& (card.getCouleur() == this.getCouleurJouee() || card.getCouleur() == Couleur.ATOUT)) {
				plusGrandePuissanceDuPli = card.getNom().getPuissance();
				carteLaPlusForteDuPli = card;
			}
		}
		// Le pli est encore vide => la premi�re carte peut emporter le pli
		if (carteLaPlusForteDuPli == null) {
			peutElle = true;
		} else {
			peutElle = carte.getNom().getPuissance() >= carteLaPlusForteDuPli.getNom().getPuissance()
					&& (carte.getCouleur() == this.getCouleurJouee() || carte.getCouleur() == Couleur.ATOUT);
		}
		return peutElle;
	}

	public void calculeLeGagnant() {
		int highestValue = 0;
		Carte strongestCard = null;
		for (Carte card : this.getCartes()) {
			if (card.getNom().getPuissance() > highestValue
					&& (card.getCouleur() == this.getCouleurJouee() || card.getCouleur() == Couleur.ATOUT)) {
				highestValue = card.getNom().getPuissance();
				strongestCard = card;
			}
		}
		Joueur vainqueur = strongestCard.getProprietaireInitial();
		this.setGagnant(vainqueur);
		vainqueur.getPlis().add(this);
		this.getCartes().stream().forEach(carte->carte.setProprietaireFinal(vainqueur));
	}
	
	@Override
	public String toString() {
		String valeur = "[Nouveau pli] \n";
		for (Carte carte : this.getCartes()) {
			valeur += carte + "\n";
		}
		valeur += "Couleur jouee : " + getCouleurJouee() + "\n";
		valeur += "Pli remport� par : " + getGagnant().getNom() + "\n";
		return valeur;
	}
	
}
