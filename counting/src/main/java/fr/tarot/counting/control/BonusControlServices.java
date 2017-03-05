package fr.tarot.counting.control;

import java.util.List;

import fr.tarot.counting.model.Bonus;
import fr.tarot.counting.model.Done;

/**
 * The Interface BonusControlServices.
 */
public interface BonusControlServices {

	/**
	 * Retrieve all possibles bonuses.
	 *
	 * @return all bonuses for a game type
	 */
	public List<Bonus> getAllBonuses();
	
	/**
	 * Adds the bonus.
	 *
	 * @param done the done
	 * @param bonus the bonus
	 */
	public void addBonus(Done done, Bonus bonus);
	
}
