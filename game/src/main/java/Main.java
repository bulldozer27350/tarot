import game.control.FoldControlServices;
import game.control.PlayerControlServices;
import game.control.internal.DoneControlServicesImpl;
import game.control.internal.FoldControlServicesImpl;
import game.control.internal.PlayerControlServicesImpl;
import game.control.internal.TarotDeckControlServicesImpl;

public class Main {

	public static void main(String[] args){
		FoldControlServices foldControlServices = new FoldControlServicesImpl();
		PlayerControlServices playerControlServices = new PlayerControlServicesImpl(foldControlServices);
		DoneControlServicesImpl doneControlServicesArg = new DoneControlServicesImpl(playerControlServices);
		MyGame game = new MyGame(doneControlServicesArg, new TarotDeckControlServicesImpl(playerControlServices, doneControlServicesArg, foldControlServices));
		game.initialize();
	}

	
}
