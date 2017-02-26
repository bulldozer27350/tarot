package control;

import model.Done;
import model.Player;

/**
 * The Interface DoneControlServices.
 */
public interface DoneControlServices {

	/**
	 * Compute points.
	 *
	 * @param done the done
	 */
	public void computePoints(Done done);
	
	public void addPlayer(Done done, Player player);
	
	/**
	 * Adds the dead.
	 *
	 * @param done the done
	 * @param player the player
	 */
	public void addDead(Done done, Player player);
	
	
	public void showResults(Done done);
}
