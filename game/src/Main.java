import game.control.PlayerControlServices;
import game.control.internal.DoneControlServicesImpl;
import game.control.internal.PlayerControlServicesImpl;
import game.control.internal.TarotDeckControlServicesImpl;

public class Main {

	public static void main(String[] args){
		PlayerControlServices playerControlServices = new PlayerControlServicesImpl();
		DoneControlServicesImpl doneControlServicesArg = new DoneControlServicesImpl(playerControlServices);
		MyGame game = new MyGame(doneControlServicesArg, new TarotDeckControlServicesImpl(playerControlServices, doneControlServicesArg));
		game.initialize();
	}

	
}
