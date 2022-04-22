import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import jeu.entities.Carte;
import jeu.entities.Done;
import jeu.entities.Joueur;
import jeu.entities.Joueur.Equipe;
import jeu.entities.Pli;
import jeu.entities.Done.Contrat;
import jeu.services.JeuDeTarot;

public class MyTarotGame {

	private Done done;

	public MyTarotGame() {
	}

	public void initialize(JeuDeTarot jeuDeTarot) {
		final int nombreDeDones = 1;
		int victoires = 0;
		final Joueur sebastien = new Joueur("Sebastien");
		final Joueur virginie = new Joueur("Virginie");
		final Joueur alexandre = new Joueur("Alexandre");
		final Joueur marco = new Joueur("Marco");
		final Joueur antoine = new Joueur("Antoine");

		for (int j = 0; j < nombreDeDones; j++) {
			this.done = new Done();

			this.done.getJoueurs().add(sebastien);
			this.done.getJoueurs().add(virginie);
			this.done.getJoueurs().add(alexandre);
			this.done.getJoueurs().add(marco);
			this.done.getJoueurs().add(antoine);

			this.done.reinitialiseLesJoueurs();
			
			this.done.setContrat(Contrat.GARDE);

			jeuDeTarot.creerDeck(this.done);
			jeuDeTarot.melanger(this.done);
			jeuDeTarot.distribue(this.done);

			// FIXME: later, we could imagine to
			// initialize these properties in an other way ...
			sebastien.setPreneur(true);

			this.done.determineContrat();

			this.done.setJoueurSuivant(sebastien);

			System.out.println("////////////////////////////////////////////////////////////");
			System.out.println("////////////////////////////////////////////////////////////");
			System.out.println("////////////////////////////////////////////////////////////");
			System.out.println("");
			System.out.println("Cartes distribuées : ");
			for (Joueur player : this.done.getJoueurs()) {
				System.out.println(player.getNom());
				player.getMain().stream().forEach(card -> System.out.println(card));
				System.out.println("");
			}
			System.out.println("Chien : ");
			final List<Carte> dogCards = new ArrayList<>(this.done.getChien());
			dogCards.stream().forEach(card -> System.out.println(card));
			System.out.println("");
			System.out.println("Début du jeu");

			jeuDeTarot.joue(this.done);

			System.out.println("");
			System.out.println("Fin du jeu");
			// Show final scores
			System.out.println("*********************");
//			for (Pli pli : this.done.getPlis()) {
//				System.out.println(pli);
//			}
			this.done.calculerLesPoints();
			if(this.done.victoireDeLAttaque()) {
				victoires++;
			}
		}
		System.err.println("Nombre de victoires de l'attaque : " + victoires + " sur " + nombreDeDones + " dones");

	}

}
