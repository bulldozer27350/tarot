package game.control;

import java.util.List;

import game.domain.Card;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;

/**
 * The Interface FoldControlServices.
 */
public interface FoldControlServices {

	/**
	 * Compute winner. It will be the most powerful card's player on the fold
	 *
	 * @param fold
	 *            the fold
	 */
	public void computeWinner(Fold fold);

	/**
	 * Computes the color of the fold. It is determined by the first card's color
	 * (the second if the first is the excuse)
	 *
	 * @param fold
	 *            the fold
	 */
	public void computeColor(Fold fold);

	/**
	 * Checks if the given card could win the fold. (no matter if an adversary
	 * is playing later in the current fold)
	 *
	 * @param fold
	 *            the fold
	 * @param card
	 *            the card
	 * @return true, if is master card with current fold
	 */
	public boolean canCardWinFold(Fold fold, Card card);

	/**
	 * Checks if the card is the most powerful with the fold's cards.
	 *
	 * @param folds
	 *            the folds
	 * @param card
	 *            the card
	 * @param done
	 *            the done
	 * @return true, if is card most powerful
	 */
	public boolean isCardMostPowerful(List<Fold> folds, Card card, Done done);

	/**
	 * Gets the players list in the fold's order.
	 *
	 * @param done
	 *            the done
	 * @param player
	 *            the player
	 * @param currentFold
	 *            the current fold
	 * @return the players order
	 */
	public List<Player> getPlayersOrder(Done done, Player player, Fold currentFold);

	/**
	 * Checks if the highest card of the fold is a teammate, in that way, we can
	 * give some points and discard points if no more fold color's cards
	 *
	 * @param fold
	 *            the fold
	 * @param player
	 *            the player
	 * @param done
	 *            the done
	 * @return true, if successful
	 */
	public boolean canGivePoints(Fold fold, Player player, Done done);
	
	/**
	 * Checks if is team play the last.
	 *
	 * @param done the done
	 * @param fold the fold
	 * @param player the player
	 * @return true, if is team play the last
	 */
	public boolean isTeamPlayTheLast(Done done, Fold fold, Player player);

}
