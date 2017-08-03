package game.control;

import game.domain.Done;

public interface GameControlServices {
	
	public void createDeck(Done done);
	
	public void shuffle(Done done);

	public void distribute(Done done);
	
	public void play(Done done);
}
