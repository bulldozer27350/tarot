package game.control;

import java.util.List;

import game.domain.Card;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;

public interface PlayerControlServices {

	public void addHand(Player player, Card card);
	public void addFold(Player player, Fold cards);
	public Card playCard(Done done, Player player, Fold fold, List<Fold> previousFolds);
	
}
