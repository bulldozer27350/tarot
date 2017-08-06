package game.control;

import java.util.List;

import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Done;
import game.domain.Fold;

public interface FoldControlServices {

	public void computeWinner(Fold fold);
	public Color getColor(Fold fold);
	
	/**
	 * Checks if the given card could win the fold. (no matter if an adversary
	 * is playing later in the current fold)
	 *
	 * @param wantedCard
	 *            the wanted card
	 * @param fold
	 *            the fold
	 * @return true, if is master card with current fold
	 */
	public boolean canCardWinFold(Fold fold, Card card);
	public boolean isCardMostPowerful(List<Fold> folds, Card card, Done done);
	
}
