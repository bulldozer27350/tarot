import control.PlayerControlServices;

import java.util.ArrayList;
import java.util.List;

import control.BonusControlServices;
import control.DoneControlServices;
import control.internal.BonusControlServicesImpl;
import control.internal.DoneControlServicesImpl;
import control.internal.PlayerControlServicesImpl;
import model.Bonus;
import model.Done;
import model.DoneType;
import model.Player;

public class Main {

	private static PlayerControlServices playerControlServices = new PlayerControlServicesImpl();
	private static DoneControlServices doneControlServices = new DoneControlServicesImpl();
	private static BonusControlServices bonusControlServices = new BonusControlServicesImpl();
	
	public static void main(String args[]){
		
		Done done = new Done();
		Player sebastien = new Player();
		sebastien.setName("Sebastien");
		
		Player jamal = new Player();
		jamal.setName("Jamal");
		
		Player virgine = new Player();
		virgine.setName("Virginie");
		
		Player marco = new Player();
		marco.setName("Marco");
		
		Player pierre = new Player();
		pierre.setName("Pierre");
		
		Player antoine = new Player();
		antoine.setName("Antoine");
		
		Player alexandre = new Player();
		alexandre.setName("Alexandre");
		
		playerControlServices.addPlayer(done, sebastien);
		playerControlServices.addPlayer(done, jamal);
		playerControlServices.addPlayer(done, virgine);
		playerControlServices.addPlayer(done, marco);
		playerControlServices.addPlayer(done, pierre);
		playerControlServices.addPlayer(done, antoine);
		playerControlServices.addPlayer(done, alexandre);

		playerControlServices.addDead(done, antoine);
		playerControlServices.addDead(done, alexandre);
		
		System.out.println("Début du jeu");
		done.setTaker(sebastien);
		done.setCalled(marco);
		done.setExcuseForPlayer(true);
		done.setTwentyOneForPlayer(true);
		done.setPointsForTakerTeam(49);
		done.setType(DoneType.GARDE);
		Bonus petitAuBout = Bonus.LITTLE_AT_END;
		petitAuBout.getWho().add(sebastien);
		petitAuBout.getWho().add(marco);
		done.getBonuses().add(petitAuBout);
		
		Bonus poignee = Bonus.SIMPLE_HAND;
		poignee.getWho().add(sebastien);
		poignee.getWho().add(marco);
		done.getBonuses().add(poignee);
		System.out.println("Fin du jeu");
		
		doneControlServices.computePoints(done);
		showResults(done);
		
		init(done);
		showResults(done);
		
		System.out.println("Début du jeu");
		done = new Done();
		
		playerControlServices.addPlayer(done, sebastien);
		playerControlServices.addPlayer(done, jamal);
		playerControlServices.addPlayer(done, virgine);
		playerControlServices.addPlayer(done, marco);
		playerControlServices.addPlayer(done, pierre);
		playerControlServices.addPlayer(done, antoine);
		playerControlServices.addPlayer(done, alexandre);

		playerControlServices.addDead(done, antoine);
		playerControlServices.addDead(done, alexandre);
		done.setTaker(sebastien);
		done.setCalled(marco);
		done.setExcuseForPlayer(true);
		done.setTwentyOneForPlayer(true);
		done.setLittleForTaker(true);
		done.setPointsForTakerTeam(40);
		done.setType(DoneType.GARDE_SANS);

		petitAuBout = Bonus.LITTLE_AT_END;
		petitAuBout.getWho().add(jamal);
		petitAuBout.getWho().add(virgine);
		petitAuBout.getWho().add(pierre);
		done.getBonuses().add(petitAuBout);
		System.out.println("Fin du jeu");
		
		doneControlServices.computePoints(done);
		showResults(done);
		
		init(done);
		System.out.println("Début du jeu");
		done = new Done();
		
		playerControlServices.addPlayer(done, sebastien);
		playerControlServices.addPlayer(done, jamal);
		playerControlServices.addPlayer(done, virgine);
		playerControlServices.addPlayer(done, marco);
		playerControlServices.addPlayer(done, pierre);
		playerControlServices.addPlayer(done, antoine);
		playerControlServices.addPlayer(done, alexandre);
		
		playerControlServices.addDead(done, antoine);
		playerControlServices.addDead(done, alexandre);
		done.setTaker(sebastien);
		done.setCalled(marco);
		done.setExcuseForPlayer(true);
		done.setTwentyOneForPlayer(true);
		done.setLittleForTaker(true);
		done.setPointsForTakerTeam(29);
		done.setType(DoneType.GARDE);
		
		petitAuBout = Bonus.LITTLE_AT_END;
		petitAuBout.getWho().add(sebastien);
		petitAuBout.getWho().add(marco);
		done.getBonuses().add(petitAuBout);
		
		poignee = Bonus.SIMPLE_HAND;
		poignee.getWho().add(sebastien);
		poignee.getWho().add(marco);
		done.getBonuses().add(poignee);
		
		System.out.println("Fin du jeu");
		
		doneControlServices.computePoints(done);
		showResults(done);
		
		init(done);
		System.out.println("Début du jeu");
		done = new Done();
		
		playerControlServices.addPlayer(done, sebastien);
		playerControlServices.addPlayer(done, jamal);
		playerControlServices.addPlayer(done, virgine);
		playerControlServices.addPlayer(done, marco);
		playerControlServices.addPlayer(done, pierre);
		playerControlServices.addPlayer(done, antoine);
		playerControlServices.addPlayer(done, alexandre);
		
		playerControlServices.addDead(done, antoine);
		playerControlServices.addDead(done, alexandre);
		done.setTaker(sebastien);
		done.setCalled(marco);
		done.setExcuseForPlayer(true);
		done.setTwentyOneForPlayer(true);
		done.setLittleForTaker(true);
		done.setPointsForTakerTeam(47);
		done.setType(DoneType.GARDE);
		
		poignee = Bonus.SIMPLE_HAND;
		poignee.getWho().add(virgine);
		poignee.getWho().add(jamal);
		poignee.getWho().add(pierre);
		done.getBonuses().add(poignee);
		
		System.out.println("Fin du jeu");
		
		doneControlServices.computePoints(done);
		showResults(done);
	}

	private static void init(Done done) {
		for (Player player : done.getPlayers()){
			player.setPoints(0);
		}
		for (Bonus bonus : bonusControlServices.getAllBonuses()){
			bonus.setWho(new ArrayList<>());
		}
	}

	private static void showResults(Done done) {
		int total = 0;
		
		for (Player player : done.getPlayers()){
			playerControlServices.showPlayer(done, player);
			if (!done.getDeads().contains(player)){
				total += player.getPoints();
			}
		}
		System.out.println("Total : " + total);
	}
	
}
