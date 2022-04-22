package jeu.entities;

public class Carte {
	public enum Couleur {
		PIQUE, COEUR, CARREAU, TREFLE, ATOUT, AUTRE
	}

	public enum Nom {
		AS(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7), HUIT(8), NEUF(9), DIX(10), VALET(11), CAVALIER(
				12), DAME(13), ROI(14), PETIT(15), DEUX_ATOUT(16), TROIS_ATOUT(17), QUATRE_ATOUT(
						18), CINQ_ATOUT(19), SIX_ATOUT(20), SEPT_ATOUT(21), HUIT_ATOUT(22), NEUF_ATOUT(23), DIX_ATOUT(
								24), ONZE_ATOUT(25), DOUZE_ATOUT(26), TREIZE_ATOUT(27), QUATORZE_ATOUT(
										28), QUINZE_ATOUT(29), SEIZE_ATOUT(30), DIXSEPT_ATOUT(31), DIXHUIT_ATOUT(
												32), DIXNEUF_ATOUT(33), VINGT_ATOUT(34), VINGTETUN_ATOUT(35), EXCUSE(0);

		int puissance;

		private Nom(int puissanceArg) {
			puissance = puissanceArg;
		}

		public int getPuissance() {
			return puissance;
		}


	}

	private Couleur couleur;
	private Nom nom;
	private double nombreDePoints;
	private Joueur proprietaireInitial;
	private Joueur proprietaireFinal;

	public Carte() {
	}

	public Carte(Couleur couleurCarte, Nom nomCarte, double nombreDePointsDeLaCarte, Joueur ownerArg) {
		this.couleur = couleurCarte;
		this.nom = nomCarte;
		this.nombreDePoints = nombreDePointsDeLaCarte;
		this.proprietaireInitial = ownerArg;
	}

	public boolean isBout() {
		return getNom() == Nom.EXCUSE || getNom() == Nom.VINGTETUN_ATOUT || getNom() == Nom.PETIT;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}

	public void setCouleur(Couleur color) {
		this.couleur = color;
	}

	public Nom getNom() {
		return this.nom;
	}

	public void setNom(Nom name) {
		this.nom = name;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		String cardName = "";
		switch (nom) {
		case AS:
			cardName = "A";
			break;
		case CAVALIER:
			cardName = "C";
			break;
		case CINQ:
			cardName = "5";
			break;
		case DAME:
			cardName = "D";
			break;
		case DEUX:
			cardName = "2";
			break;
		case DIX:
			cardName = "10";
			break;
		case DIXHUIT_ATOUT:
			cardName = "18";
			break;
		case DIXNEUF_ATOUT:
			cardName = "19";
			break;
		case DIXSEPT_ATOUT:
			cardName = "17";
			break;
		case DOUZE_ATOUT:
			cardName = "12";
			break;
		case EXCUSE:
			cardName = "*";
			break;
		case HUIT:
			cardName = "8";
			break;
		case NEUF:
			cardName = "9";
			break;
		case ONZE_ATOUT:
			cardName = "11";
			break;
		case QUATORZE_ATOUT:
			cardName = "14";
			break;
		case QUATRE:
			cardName = "4";
			break;
		case QUINZE_ATOUT:
			cardName = "15";
			break;
		case ROI:
			cardName = "R";
			break;
		case SEIZE_ATOUT:
			cardName = "16";
			break;
		case SEPT:
			cardName = "7";
			break;
		case SIX:
			cardName = "6";
			break;
		case TREIZE_ATOUT:
			cardName = "13";
			break;
		case TROIS:
			cardName = "3";
			break;
		case PETIT:
			cardName = "1";
			break;
		case VALET:
			cardName = "V";
			break;
		case VINGT_ATOUT:
			cardName = "20";
			break;
		case VINGTETUN_ATOUT:
			cardName = "21";
			break;
		case DEUX_ATOUT:
			cardName = "2";
			break;
		case TROIS_ATOUT:
			cardName = "3";
			break;
		case QUATRE_ATOUT:
			cardName = "4";
			break;
		case CINQ_ATOUT:
			cardName = "5";
			break;
		case SIX_ATOUT:
			cardName = "6";
			break;
		case SEPT_ATOUT:
			cardName = "7";
			break;
		case HUIT_ATOUT:
			cardName = "8";
			break;
		case NEUF_ATOUT:
			cardName = "9";
			break;
		case DIX_ATOUT:
			cardName = "10";
			break;
		default:
			break;
		}
		str.append(cardName).append(" ").append(this.couleur.name()).append(" (").append(nombreDePoints)
				.append(" points) -> distribuée à ");
		if (proprietaireInitial != null) {
			str.append(proprietaireInitial.getNom());
			str.append(" - ");
			str.append(proprietaireInitial.getEquipe());
		} else {
			str.append("X");
		}
		str.append(" et remportée par ");
		if (proprietaireFinal != null) {
			str.append(proprietaireFinal.getNom());
			str.append(" - ");
			str.append(proprietaireFinal.getEquipe());
		} else {
			str.append("X");
		}
		return str.toString();
	}

	public double getNombreDePoints() {
		return nombreDePoints;
	}

	public void setNombreDePoints(double nombreDePoints) {
		this.nombreDePoints = nombreDePoints;
	}

	public Joueur getProprietaireInitial() {
		return proprietaireInitial;
	}

	public void setProprietaireInitial(Joueur proprietaire) {
		this.proprietaireInitial = proprietaire;
	}

	public Joueur getProprietaireFinal() {
		return proprietaireFinal;
	}

	public void setProprietaireFinal(Joueur proprietaireFinal) {
		this.proprietaireFinal = proprietaireFinal;
	}

}
