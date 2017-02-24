package control;

import model.Done;
import model.Player;

public interface PlayerControlServices {

	public void addPlayer(Done done, Player player);
	public void removePlayer(Done done, Player player);
	public void showPlayer(Done done, Player player);
	public void addDead(Done done, Player player);
	
}
