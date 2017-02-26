package control;

import model.Application;
import model.Done;
import model.Player;

/**
 * The Interface ApplicationControlServices.
 */
public interface ApplicationControlServices {
	
	/**
	 * Adds the player.
	 *
	 * @param application the application
	 * @param player the player
	 */
	public void addPlayer(Application application, Player player);
	
	/**
	 * Removes the player.
	 *
	 * @param application the application
	 * @param player the player
	 */
	public void removePlayer(Application application, Player player);
	
	/**
	 * Adds the done.
	 *
	 * @param application the application
	 * @param done the done
	 */
	public void addDone(Application application, Done done);
	
	public void showResults(Application application);
}
