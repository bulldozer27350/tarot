package gui.counting.tarot.fr.gui;

import fr.tarot.counting.control.ApplicationControlServices;
import fr.tarot.counting.control.DoneControlServices;
import fr.tarot.counting.control.PlayerControlServices;
import fr.tarot.counting.control.internal.ApplicationControlServicesImpl;
import fr.tarot.counting.control.internal.DoneControlServicesImpl;
import fr.tarot.counting.control.internal.PlayerControlServicesImpl;
import fr.tarot.counting.model.Application;
import fr.tarot.counting.model.Bonus;
import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.DoneType;
import fr.tarot.counting.model.Player;

public class TarotLauncher {

	private final PlayerControlServices playerControlServices;
	private final DoneControlServices doneControlServices;
	private final ApplicationControlServices applicationControlServices;
	
	public TarotLauncher() {
		playerControlServices = new PlayerControlServicesImpl();
		doneControlServices = new DoneControlServicesImpl(playerControlServices);
		applicationControlServices = new ApplicationControlServicesImpl();
	}
	
	public String launch(){
		StringBuffer scores = new StringBuffer();
		
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
		
		scores.append("Début du jeu").append("\n");
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
		
		doneControlServices.computePoints(done1);
		scores.append(doneControlServices.showResults(done1));

		scores.append("Fin du jeu").append("\n");
		
		return scores.toString();
	}
	
}
