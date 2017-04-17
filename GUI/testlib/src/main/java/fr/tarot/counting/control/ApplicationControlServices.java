package fr.tarot.counting.control;

import fr.tarot.counting.model.Application;
import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.Player;

/**
 * The Interface ApplicationControlServices.
 */
public interface ApplicationControlServices {

    /**
     * Adds the player.
     *
     * @param application the application
     * @param player      the player
     */
    public void addPlayer(Application application, Player player);

    /**
     * Removes the player.
     *
     * @param application the application
     * @param player      the player
     */
    public void removePlayer(Application application, Player player);

    /**
     * Adds the done.
     *
     * @param application the application
     * @param done        the done
     */
    public void addDone(Application application, Done done);

    public String showResults(Application application);
}
