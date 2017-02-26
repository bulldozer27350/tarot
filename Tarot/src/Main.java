import control.ApplicationControlServices;
import control.DoneControlServices;
import control.PlayerControlServices;
import control.internal.ApplicationControlServicesImpl;
import control.internal.DoneControlServicesImpl;
import control.internal.PlayerControlServicesImpl;
import model.Application;
import model.Bonus;
import model.Done;
import model.DoneType;
import model.Player;

public class Main {

	private static PlayerControlServices playerControlServices = new PlayerControlServicesImpl();
	private static DoneControlServices doneControlServices = new DoneControlServicesImpl(playerControlServices);
	private static ApplicationControlServices applicationControlServices = new ApplicationControlServicesImpl();
	
	public static void main(String args[]){
		
		Application application = new Application();
		
		Done done1 = new Done();
		applicationControlServices.addDone(application, done1);
		
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
		
		applicationControlServices.addPlayer(application, sebastien);
		applicationControlServices.addPlayer(application, jamal);
		applicationControlServices.addPlayer(application, virgine);
		applicationControlServices.addPlayer(application, marco);
		applicationControlServices.addPlayer(application, pierre);
		applicationControlServices.addPlayer(application, antoine);
		applicationControlServices.addPlayer(application, alexandre);

		Player done1Sebastien = new Player(sebastien);
		Player done1Jamal = new Player(jamal);
		Player done1Virgine = new Player(virgine);
		Player done1Marco = new Player(marco);
		Player done1Pierre = new Player(pierre);
		Player done1Antoine = new Player(antoine);
		Player done1Alexandre = new Player(alexandre);
		
		doneControlServices.addDead(done1, done1Antoine);
		doneControlServices.addDead(done1, done1Alexandre);
		doneControlServices.addPlayer(done1, done1Sebastien);
		doneControlServices.addPlayer(done1, done1Jamal);
		doneControlServices.addPlayer(done1, done1Virgine);
		doneControlServices.addPlayer(done1, done1Marco);
		doneControlServices.addPlayer(done1, done1Pierre);
		
		System.out.println("Début du jeu");
		done1.setTaker(done1Sebastien);
		done1.setCalled(done1Marco);
		done1.setExcuseForPlayer(true);
		done1.setTwentyOneForPlayer(true);
		done1.setPointsForTakerTeam(49);
		done1.setType(DoneType.GARDE);
		Bonus petitAuBout = Bonus.LITTLE_AT_END;
		petitAuBout.getWho().add(done1Sebastien);
		petitAuBout.getWho().add(done1Marco);
		done1.getBonuses().add(petitAuBout);
		
		Bonus poignee = Bonus.SIMPLE_HAND;
		poignee.getWho().add(done1Sebastien);
		poignee.getWho().add(done1Marco);
		done1.getBonuses().add(poignee);
		System.out.println("Fin du jeu");
		
		doneControlServices.computePoints(done1);
		doneControlServices.showResults(done1);
		
		Done done2 = new Done();
		applicationControlServices.addDone(application, done2);
		doneControlServices.showResults(done2);
		
		System.out.println("Début du jeu");
		
		// Need to duplicates players in order points for a done are saved. Otherwise, the points are erased by the next done, because done is mapping on same objects ...
		Player done2Sebastien = new Player(sebastien);
		Player done2Jamal = new Player(jamal);
		Player done2Virgine = new Player(virgine);
		Player done2Marco = new Player(marco);
		Player done2Pierre = new Player(pierre);
		Player done2Antoine = new Player(antoine);
		Player done2Alexandre = new Player(alexandre);
		
		doneControlServices.addPlayer(done2, done2Sebastien);
		doneControlServices.addPlayer(done2, done2Jamal);
		doneControlServices.addPlayer(done2, done2Virgine);
		doneControlServices.addPlayer(done2, done2Marco);
		doneControlServices.addPlayer(done2, done2Pierre);

		doneControlServices.addDead(done2, done2Antoine);
		doneControlServices.addDead(done2, done2Alexandre);
		
		done2.setTaker(done2Sebastien);
		done2.setCalled(done2Marco);
		done2.setExcuseForPlayer(true);
		done2.setTwentyOneForPlayer(true);
		done2.setLittleForTaker(true);
		done2.setPointsForTakerTeam(40);
		done2.setType(DoneType.GARDE_SANS);

		petitAuBout = Bonus.LITTLE_AT_END;
		petitAuBout.getWho().add(done2Jamal);
		petitAuBout.getWho().add(done2Virgine);
		petitAuBout.getWho().add(done2Pierre);
		done2.getBonuses().add(petitAuBout);
		System.out.println("Fin du jeu");
		
		doneControlServices.computePoints(done2);
		doneControlServices.showResults(done2);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		applicationControlServices.showResults(application);
//		
//		System.out.println("Début du jeu");
//		Done done3 = new Done();
//		applicationControlServices.addDone(application, done3);
//		
//		playerControlServices.addPlayer(done3, sebastien);
//		playerControlServices.addPlayer(done3, jamal);
//		playerControlServices.addPlayer(done3, virgine);
//		playerControlServices.addPlayer(done3, marco);
//		playerControlServices.addPlayer(done3, pierre);
//		playerControlServices.addPlayer(done3, antoine);
//		playerControlServices.addPlayer(done3, alexandre);
//		
//		doneControlServices.addDead(done3, antoine);
//		doneControlServices.addDead(done3, alexandre);
//		done3.setTaker(sebastien);
//		done3.setCalled(marco);
//		done3.setExcuseForPlayer(true);
//		done3.setTwentyOneForPlayer(true);
//		done3.setLittleForTaker(true);
//		done3.setPointsForTakerTeam(29);
//		done3.setType(DoneType.GARDE);
//		
//		petitAuBout = Bonus.LITTLE_AT_END;
//		petitAuBout.getWho().add(sebastien);
//		petitAuBout.getWho().add(marco);
//		done3.getBonuses().add(petitAuBout);
//		
//		poignee = Bonus.SIMPLE_HAND;
//		poignee.getWho().add(sebastien);
//		poignee.getWho().add(marco);
//		done3.getBonuses().add(poignee);
//		
//		System.out.println("Fin du jeu");
//		
//		doneControlServices.computePoints(done3);
//		doneControlServices.showResults(done3);
//		
//		System.out.println("Début du jeu");
//		Done done4 = new Done();
//		applicationControlServices.addDone(application, done4);
//		
//		playerControlServices.addPlayer(done4, sebastien);
//		playerControlServices.addPlayer(done4, jamal);
//		playerControlServices.addPlayer(done4, virgine);
//		playerControlServices.addPlayer(done4, marco);
//		playerControlServices.addPlayer(done4, pierre);
//		playerControlServices.addPlayer(done4, antoine);
//		playerControlServices.addPlayer(done4, alexandre);
//		
//		doneControlServices.addDead(done4, antoine);
//		doneControlServices.addDead(done4, alexandre);
//		done4.setTaker(sebastien);
//		done4.setCalled(marco);
//		done4.setExcuseForPlayer(true);
//		done4.setTwentyOneForPlayer(true);
//		done4.setLittleForTaker(true);
//		done4.setPointsForTakerTeam(47);
//		done4.setType(DoneType.GARDE);
//		
//		poignee = Bonus.SIMPLE_HAND;
//		poignee.getWho().add(virgine);
//		poignee.getWho().add(jamal);
//		poignee.getWho().add(pierre);
//		done4.getBonuses().add(poignee);
//		
//		System.out.println("Fin du jeu");
//		
//		doneControlServices.computePoints(done4);
//		doneControlServices.showResults(done4);
	}

}
