package control;

import java.util.List;

import model.Bonus;
import model.Done;

public interface BonusControlServices {

	/**
	 * Retrieve all possibles bonuses
	 * @return all bonuses for a game type
	 */
	public List<Bonus> getAllBonuses();
	
	public void addBonus(Done done, Bonus bonus);
	
}
