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
	
	private DoneControlServices doneControlServices;
	private GameControlServices gameControlServices;
	private Done done;
	
	public MyGame(DoneControlServices doneControlServicesArg, GameControlServices gameControlServicesArg) {
		doneControlServices = doneControlServicesArg;
		gameControlServices = gameControlServicesArg;
	}
	
	public void initialize(){
		int doneNumber = 1;
		done = new Done();
		
		Player sebastien = new Player("Sebastien");
		Player virginie = new Player("Virginie");
		Player alexandre = new Player("Alexandre");
		Player marco = new Player("Marco");
		Player antoine = new Player("Antoine");
		
		doneControlServices.addPlayer(done, sebastien);
		doneControlServices.addPlayer(done, virginie);
		doneControlServices.addPlayer(done, alexandre);
		doneControlServices.addPlayer(done, marco);
		doneControlServices.addPlayer(done, antoine);
		
		gameControlServices.createDeck(done);
		gameControlServices.shuffle(done);
		for (int j = 0 ; j < doneNumber; j++){
			for (Player player : done.getPlayers()){
				player.setTeam(Team.UNKNOWN);
			}

			// FIXME: later, we could imagine to
			// initialize these properties in an other way ...
			sebastien.setTeam(Team.ATTACK);
			virginie.setTeam(Team.ATTACK);
			alexandre.setTeam(Team.DEFENSE);
			marco.setTeam(Team.DEFENSE);
			antoine.setTeam(Team.DEFENSE);
			
			gameControlServices.distribute(done);
			
			// TODO : set this line in a control service layer
			done.setNextPlayer(sebastien);
			
//			for(Player player : done.getPlayers()){
//				System.out.println(player.getName());
//				player.getHand().stream().forEach(card->System.out.println(card));
//				System.out.println("");
//			}
			System.out.println("Chien : ");
			List<Card> dogCards = new ArrayList<>(done.getDog());
			dogCards.stream().forEach(card->System.out.println(card));
			
			gameControlServices.play(done);
			
			// Show final scores
			List<Card> doneCards = new ArrayList<>();
//			System.out.println("Scores finaux : ");
			
			double dogScore = 0;
			for (int i = 0 ; i < dogCards.size() ; i++){
				Card card = done.getDog().get(0);
				card.setOwner(null);
				dogScore += card.getPointsValue();
				doneCards.add(done.getDog().remove(0));
			}
			System.out.println("(chien : \t" + dogScore + "\t points)");
			System.out.println("");
			
			done.setCards(doneCards);

			double score = 0;
			List<Player> attackPlayers = new ArrayList<>();
			for (Player player : done.getPlayers().stream().filter(e->e.getTeam() == Team.ATTACK).collect(Collectors.toList())){
				attackPlayers.add(player);
				for (Fold fold : player.getFolds()){
					List<Card> playerFold = new ArrayList<>(fold.getCards());
					for (int i = 0 ; i < playerFold.size() ; i++){
						Card card = fold.getCards().get(0);
						score += card.getPointsValue();
						doneCards.add(fold.getCards().remove(0));
					}
				}
			}
			score += dogScore;
			System.out.println("Attack team " + Arrays.toString(attackPlayers.toArray()) + " : \t" + score + "\t points");
			
			score = 0;
			List<Player> defensePlayers = new ArrayList<>();
			for (Player player : done.getPlayers().stream().filter(e->e.getTeam() == Team.DEFENSE).collect(Collectors.toList())){
				defensePlayers.add(player);
				for (Fold fold : player.getFolds()){
					List<Card> playerFold = new ArrayList<>(fold.getCards());
					for (int i = 0 ; i < playerFold.size() ; i++){
						Card card = fold.getCards().get(0);
						score += card.getPointsValue();
						doneCards.add(fold.getCards().remove(0));
					}
				}
			}
			System.out.println("Defense team " + Arrays.toString(defensePlayers.toArray()) + " : \t" + score + "\t points");
		}
		
	}
	
}
