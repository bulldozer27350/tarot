package game.control;

import game.domain.Card;
import game.domain.Done;
import game.domain.Player;

public interface DoneControlServices {

	public void distribute(Done done);
	
	public void addCard(Done done, Card card);

	public void addPlayer(Done done, Player player);
	
}
