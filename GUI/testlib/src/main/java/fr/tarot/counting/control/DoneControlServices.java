package fr.tarot.counting.control;

import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.Player;

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
     * @param done   the done
     * @param player the player
     */
    public void addDead(Done done, Player player);


    public String showResults(Done done);


    /**
     * Retrieves a player by its name
     * @param done the done where the looked player must be present
     * @param name the player's name
     * @return the player matching the name
     */
    public Player getPlayer(Done done, String name);
}
