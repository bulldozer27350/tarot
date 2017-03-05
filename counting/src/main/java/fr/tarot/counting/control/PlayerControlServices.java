package fr.tarot.counting.control;

import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.Player;

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
	
}
