package control;

import model.Done;
import model.Player;

/**
 * The Interface PlayerControlServices.
 */
public interface PlayerControlServices {

	/**
	 * Adds the player.
	 *
	 * @param done the done
	 * @param player the player
	 */
	public void addPlayer(Done done, Player player);
	
	/**
	 * Removes the player.
	 *
	 * @param done the done
	 * @param player the player
	 */
	public void removePlayer(Done done, Player player);
	
	/**
	 * Show player.
	 *
	 * @param done the done
	 * @param player the player
	 */
	public void showPlayer(Done done, Player player);
	
	/**
	 * Adds the dead.
	 *
	 * @param done the done
	 * @param player the player
	 */
	public void addDead(Done done, Player player);
	
}
